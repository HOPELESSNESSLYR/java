package com.degson.fire.service.impl;

import java.util.List;
import com.degson.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.degson.fire.mapper.SysFirealertMapper;
import com.degson.fire.domain.SysFirealert;
import com.degson.fire.service.ISysFirealertService;

/**
 * 预警报警Service业务层处理
 * 
 * @author yara
 * @date 2025-02-07
 */
@Service
public class SysFirealertServiceImpl implements ISysFirealertService 
{
    @Autowired
    private SysFirealertMapper sysFirealertMapper;

    /**
     * 查询预警报警
     * 
     * @param id 预警报警主键
     * @return 预警报警
     */
    @Override
    public SysFirealert selectSysFirealertById(Long id)
    {
        return sysFirealertMapper.selectSysFirealertById(id);
    }

    /**
     * 查询预警报警列表
     * 
     * @param sysFirealert 预警报警
     * @return 预警报警
     */
    @Override
    public List<SysFirealert> selectSysFirealertList(SysFirealert sysFirealert)
    {
        return sysFirealertMapper.selectSysFirealertList(sysFirealert);
    }

    /**
     * 新增预警报警
     * 
     * @param sysFirealert 预警报警
     * @return 结果
     */
    @Override
    public int insertSysFirealert(SysFirealert sysFirealert)
    {
        sysFirealert.setAlertTime(DateUtils.getNowDate());
        sysFirealert.setCreateTime(DateUtils.getNowDate());
        return sysFirealertMapper.insertSysFirealert(sysFirealert);
    }

    /**
     * 修改预警报警
     * 
     * @param sysFirealert 预警报警
     * @return 结果
     */
    @Override
    public int updateSysFirealert(SysFirealert sysFirealert)
    {
        sysFirealert.setAlertTime(DateUtils.getNowDate());
        sysFirealert.setUpdateTime(DateUtils.getNowDate());
        return sysFirealertMapper.updateSysFirealert(sysFirealert);
    }

    /**
     * 批量删除预警报警
     * 
     * @param ids 需要删除的预警报警主键
     * @return 结果
     */
    @Override
    public int deleteSysFirealertByIds(Long[] ids)
    {
        return sysFirealertMapper.deleteSysFirealertByIds(ids);
    }

    /**
     * 删除预警报警信息
     * 
     * @param id 预警报警主键
     * @return 结果
     */
    @Override
    public int deleteSysFirealertById(Long id)
    {
        return sysFirealertMapper.deleteSysFirealertById(id);
    }
}
