package com.degson.fire.service;

import java.io.IOException;
import java.util.List;
import com.degson.fire.domain.SysFirefighting;
import com.google.zxing.WriterException;

/**
 * 灭火器管理Service接口
 *
 * @author yara
 * @date 2024-10-30
 */
public interface ISysFirefightingService
{

    /**
     * 查询灭火器管理
     *
     * @param fireId 灭火器管理主键
     * @return 灭火器管理
     */
    public SysFirefighting selectSysFirefightingByFireId(Long fireId);

    /**
     * 查询灭火器管理列表
     *
     * @param sysFirefighting 灭火器管理
     * @return 灭火器管理集合
     */
    public List<SysFirefighting> selectSysFirefightingList(SysFirefighting sysFirefighting);

    /**
     * 新增灭火器管理
     *
     * @param sysFirefighting 灭火器管理
     * @return 结果
     */
    public int insertSysFirefighting(SysFirefighting sysFirefighting);

    /**
     * 修改灭火器管理
     *
     * @param sysFirefighting 灭火器管理
     * @return 结果
     */
    public int updateSysFirefighting(SysFirefighting sysFirefighting);

    /**
     * 批量删除灭火器管理
     *
     * @param fireIds 需要删除的灭火器管理主键集合
     * @return 结果
     */
    public int deleteSysFirefightingByFireIds(Long[] fireIds);

    /**
     * 删除灭火器管理信息
     *
     * @param fireId 灭火器管理主键
     * @return 结果
     */
    public int deleteSysFirefightingByFireId(Long fireId);

//    public int generateFile(SysFirefighting sysFirefighting) throws WriterException;

    public String generateStream(String qrContent) throws WriterException;
    public List<String> generateStreamList(List<String> texts) throws IOException, WriterException;

    int uni(String qrContent, String image);
//    BufferedImage generateImage(String qrContent) throws WriterException;


    //    @Override
    //    public BufferedImage generateImage(String qrContent) throws WriterException {
    //        return QrCodeUtil.getBufferedImage(qrContent);
    ////        return 0;
    //    }

    public String importSysFirefightinginfor(List<SysFirefighting> sysFirefightingList, boolean isUpdateSupport, String operName) ;

}
