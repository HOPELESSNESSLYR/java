package com.degson.fire.mapper;

import java.util.List;
import com.degson.fire.domain.SysFirefighting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 灭火器管理Mapper接口
 *
 * @author yara
 * @date 2024-10-30
 */
@Mapper
public interface SysFirefightingMapper
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
     * 删除灭火器管理
     *
     * @param fireId 灭火器管理主键
     * @return 结果
     */
    public int deleteSysFirefightingByFireId(Long fireId);

    /**
     * 批量删除灭火器管理
     *
     * @param fireIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysFirefightingByFireIds(Long[] fireIds);

    public int uni(@Param("qrContent") String qrContent, @Param("image") String image, @Param("currentDateString") String currentDateString);

    public int updateSysFire(SysFirefighting sysFirefighting);
}
