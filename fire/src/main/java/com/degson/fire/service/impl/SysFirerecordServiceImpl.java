package com.degson.fire.service.impl;

import java.time.LocalDate;
import java.util.List;
import com.degson.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.degson.fire.mapper.SysFirerecordMapper;
import com.degson.fire.domain.SysFirerecord;
import com.degson.fire.service.ISysFirerecordService;

/**
 * 检查记录Service业务层处理
 * 
 * @author yara
 * @date 2024-12-31
 */
@Service
public class SysFirerecordServiceImpl implements ISysFirerecordService 
{
    @Autowired
    private SysFirerecordMapper sysFirerecordMapper;

    /**
     * 查询检查记录
     * 
     * @param recordId 检查记录主键
     * @return 检查记录
     */
    @Override
    public SysFirerecord selectSysFirerecordByRecordId(Long recordId)
    {
        return sysFirerecordMapper.selectSysFirerecordByRecordId(recordId);
    }

    /**
     * 查询检查记录列表
     * 
     * @param sysFirerecord 检查记录
     * @return 检查记录
     */
    @Override
    public List<SysFirerecord> selectSysFirerecordList(SysFirerecord sysFirerecord)
    {
        return sysFirerecordMapper.selectSysFirerecordList(sysFirerecord);
    }

    /**
     * 新增检查记录
     * 
     * @param sysFirerecord 检查记录
     * @return 结果
     */
    @Override
    public int insertSysFirerecord(SysFirerecord sysFirerecord)
    {
        sysFirerecord.setCreateTime(DateUtils.getNowDate());
        return sysFirerecordMapper.insertSysFirerecord(sysFirerecord);
    }

    /**
     * 修改检查记录
     * 
     * @param sysFirerecord 检查记录
     * @return 结果
     */
    @Override
    public int updateSysFirerecord(SysFirerecord sysFirerecord)
    {
        sysFirerecord.setUpdateTime(DateUtils.getNowDate());
        return sysFirerecordMapper.updateSysFirerecord(sysFirerecord);
    }

    /**
     * 批量删除检查记录
     * 
     * @param recordIds 需要删除的检查记录主键
     * @return 结果
     */
    @Override
    public int deleteSysFirerecordByRecordIds(Long[] recordIds)
    {
        return sysFirerecordMapper.deleteSysFirerecordByRecordIds(recordIds);
    }

    /**
     * 删除检查记录信息
     * 
     * @param recordId 检查记录主键
     * @return 结果
     */
    @Override
    public int deleteSysFirerecordByRecordId(Long recordId)
    {
        return sysFirerecordMapper.deleteSysFirerecordByRecordId(recordId);
    }

    @Override
    public int uni(String qrContent, String image) {
        LocalDate currentDate = LocalDate.now();
        String currentDateString = currentDate.toString();

        return sysFirerecordMapper.uni(qrContent,image,currentDateString);
    }
}
