package com.degson.fire.mapper;

import java.util.List;
import com.degson.fire.domain.SysFirerecord;

/**
 * 检查记录Mapper接口
 * 
 * @author yara
 * @date 2024-12-31
 */
public interface SysFirerecordMapper 
{
    /**
     * 查询检查记录
     * 
     * @param recordId 检查记录主键
     * @return 检查记录
     */
    public SysFirerecord selectSysFirerecordByRecordId(Long recordId);

    /**
     * 查询检查记录列表
     * 
     * @param sysFirerecord 检查记录
     * @return 检查记录集合
     */
    public List<SysFirerecord> selectSysFirerecordList(SysFirerecord sysFirerecord);

    /**
     * 新增检查记录
     * 
     * @param sysFirerecord 检查记录
     * @return 结果
     */
    public int insertSysFirerecord(SysFirerecord sysFirerecord);

    /**
     * 修改检查记录
     * 
     * @param sysFirerecord 检查记录
     * @return 结果
     */
    public int updateSysFirerecord(SysFirerecord sysFirerecord);

    /**
     * 删除检查记录
     * 
     * @param recordId 检查记录主键
     * @return 结果
     */
    public int deleteSysFirerecordByRecordId(Long recordId);

    /**
     * 批量删除检查记录
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysFirerecordByRecordIds(Long[] recordIds);

    public int uni(String qrContent, String image, String currentDateString);
}
