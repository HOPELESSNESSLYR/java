package com.degson.fire.service;

import java.util.List;
import com.degson.fire.domain.SysFirealert;

/**
 * 预警报警Service接口
 * 
 * @author yara
 * @date 2025-02-07
 */
public interface ISysFirealertService 
{
    /**
     * 查询预警报警
     * 
     * @param id 预警报警主键
     * @return 预警报警
     */
    public SysFirealert selectSysFirealertById(Long id);

    /**
     * 查询预警报警列表
     * 
     * @param sysFirealert 预警报警
     * @return 预警报警集合
     */
    public List<SysFirealert> selectSysFirealertList(SysFirealert sysFirealert);

    /**
     * 新增预警报警
     * 
     * @param sysFirealert 预警报警
     * @return 结果
     */
    public int insertSysFirealert(SysFirealert sysFirealert);

    /**
     * 修改预警报警
     * 
     * @param sysFirealert 预警报警
     * @return 结果
     */
    public int updateSysFirealert(SysFirealert sysFirealert);

    /**
     * 批量删除预警报警
     * 
     * @param ids 需要删除的预警报警主键集合
     * @return 结果
     */
    public int deleteSysFirealertByIds(Long[] ids);

    /**
     * 删除预警报警信息
     * 
     * @param id 预警报警主键
     * @return 结果
     */
    public int deleteSysFirealertById(Long id);
}
