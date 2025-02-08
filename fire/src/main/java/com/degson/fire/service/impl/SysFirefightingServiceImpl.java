package com.degson.fire.service.impl;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.degson.common.exception.ServiceException;
import com.degson.common.utils.DateUtils;
import com.degson.common.utils.StringUtils;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.degson.fire.mapper.SysFirefightingMapper;
import com.degson.fire.domain.SysFirefighting;
import com.degson.fire.service.ISysFirefightingService;
import com.degson.fire.utils.QRCodeUtil;

import javax.annotation.Resource;
import javax.validation.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.degson.common.utils.bean.BeanValidators;
/**
 * 灭火器管理Service业务层处理
 *
 * @author yara
 * @date 2024-10-30
 */
@Service
public class SysFirefightingServiceImpl implements ISysFirefightingService
{
    @Autowired
    private SysFirefightingMapper sysFirefightingMapper;
    @Autowired
    protected Validator validator;
    /**
     * 查询灭火器管理
     *
     * @param fireId 灭火器管理主键
     * @return 灭火器管理
     */
    @Override
    public SysFirefighting selectSysFirefightingByFireId(Long fireId)
    {
        return sysFirefightingMapper.selectSysFirefightingByFireId(fireId);
    }

    /**
     * 查询灭火器管理列表
     *
     * @param sysFirefighting 灭火器管理
     * @return 灭火器管理
     */
    @Override
    public List<SysFirefighting> selectSysFirefightingList(SysFirefighting sysFirefighting)
    {

//        Date bb=sysFirefighting.getExpiryDate();
//        Date now = new Date();
//        if ((String.valueOf(bb).compareTo(String.valueOf(now))) <0 ) {
//            System.out.println("当前日期和时间在特定日期之后。");
//            sysFirefighting.setStatus("否");
//        } else if ((String.valueOf(bb).compareTo(String.valueOf(now))) > 0 ) {
//            System.out.println("当前日期和时间在特定日期之前。");
//            sysFirefighting.setStatus("是");
//        } else {
//            System.out.println("当前日期和时间与特定日期相同。");
//            sysFirefighting.setStatus("无");
//        }

        sysFirefighting.setCreateTime(DateUtils.getNowDate());
        return sysFirefightingMapper.selectSysFirefightingList(sysFirefighting);
    }

    /**
     * 新增灭火器管理
     *
     * @param sysFirefighting 灭火器管理
     * @return 结果
     */
    @Override
    public int insertSysFirefighting(SysFirefighting sysFirefighting)
    {
        sysFirefighting.setCreateTime(DateUtils.getNowDate());
        return sysFirefightingMapper.insertSysFirefighting(sysFirefighting);
    }

    /**
     * 修改灭火器管理
     *
     * @param sysFirefighting 灭火器管理
     * @return 结果
     */
    @Override
    public int updateSysFirefighting(SysFirefighting sysFirefighting)
    {
        sysFirefighting.setUpdateTime(DateUtils.getNowDate());
        return sysFirefightingMapper.updateSysFirefighting(sysFirefighting);
    }

    /**
     * 批量删除灭火器管理
     *
     * @param fireIds 需要删除的灭火器管理主键
     * @return 结果
     */
    @Override
    public int deleteSysFirefightingByFireIds(Long[] fireIds)
    {
        return sysFirefightingMapper.deleteSysFirefightingByFireIds(fireIds);
    }

    /**
     * 删除灭火器管理信息
     *
     * @param fireId 灭火器管理主键
     * @return 结果
     */
    @Override
    public int deleteSysFirefightingByFireId(Long fireId)
    {
        return sysFirefightingMapper.deleteSysFirefightingByFireId(fireId);
    }

    @Resource
    private QRCodeUtil QrCodeUtil;
//    @Override
//    public int generateFile(SysFirefighting sysFirefighting) throws WriterException {
//        String content= sysFirefighting.getQrContent();
//        QrCodeUtil.getBufferedImage(content);
//        return 0;
//    }
    @Override
    public String generateStream(String qrContent) throws WriterException {
        return QrCodeUtil.createCodeToOutputStream(qrContent);
    }
    @Override
    public List<String> generateStreamList(List<String> texts) throws IOException, WriterException {
        return QrCodeUtil.generateStreamList(texts);
    }

    @Override
    public int uni(String qrContent, String image) {
        LocalDate currentDate = LocalDate.now();
        String currentDateString = currentDate.toString();
        System.out.println("currentDateString = " + currentDateString);

        return sysFirefightingMapper.uni(qrContent,image,currentDateString);
    }


//    @Override
//    public BufferedImage generateImage(String qrContent) throws WriterException {
//        return QrCodeUtil.getBufferedImage(qrContent);
////        return 0;
//    }

    /*
     * 导入消防设备信息数据
     * @param sysFirefightingList 列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     */
    @Override
    public String importSysFirefightinginfor(List<SysFirefighting> sysFirefightingList, boolean isUpdateSupport, String operName) {

        if (StringUtils.isNull(sysFirefightingList) || sysFirefightingList.size() == 0)
        {
            throw new ServiceException("导入消防设备数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (SysFirefighting sysFirefighting : sysFirefightingList)
        {
            try
            {

                if (isUpdateSupport==false)
                {
                    BeanValidators.validateWithException(validator, sysFirefighting);

                    sysFirefighting.setCreateBy(operName);
                    this.insertSysFirefighting(sysFirefighting);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、消防设备 " + sysFirefighting.getDeviceName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    BeanValidators.validateWithException(validator, sysFirefighting);
                    sysFirefighting.setUpdateBy(operName);
                    this.updateSysFirefighting(sysFirefighting);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、消防设备 " + sysFirefighting.getDeviceName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、消防设备 " + sysFirefighting.getDeviceName() + " 导入失败");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、消防设备 " + sysFirefighting.getDeviceName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
//                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

}
