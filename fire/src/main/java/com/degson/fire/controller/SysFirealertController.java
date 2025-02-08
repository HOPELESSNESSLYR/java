package com.degson.fire.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.degson.common.annotation.Log;
import com.degson.common.core.controller.BaseController;
import com.degson.common.core.domain.AjaxResult;
import com.degson.common.enums.BusinessType;
import com.degson.fire.domain.SysFirealert;
import com.degson.fire.service.ISysFirealertService;
import com.degson.common.utils.poi.ExcelUtil;
import com.degson.common.core.page.TableDataInfo;

/**
 * 预警报警Controller
 * 
 * @author yara
 * @date 2025-02-07
 */
@RestController
@RequestMapping("/alert/alert")
public class SysFirealertController extends BaseController
{
    @Autowired
    private ISysFirealertService sysFirealertService;

    /**
     * 查询预警报警列表
     */
    @PreAuthorize("@ss.hasPermi('alert:alert:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysFirealert sysFirealert)
    {
        startPage();
        List<SysFirealert> list = sysFirealertService.selectSysFirealertList(sysFirealert);
        return getDataTable(list);
    }

    /**
     * 导出预警报警列表
     */
    @PreAuthorize("@ss.hasPermi('alert:alert:export')")
    @Log(title = "预警报警", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysFirealert sysFirealert)
    {
        List<SysFirealert> list = sysFirealertService.selectSysFirealertList(sysFirealert);
        ExcelUtil<SysFirealert> util = new ExcelUtil<SysFirealert>(SysFirealert.class);
        util.exportExcel(response, list, "预警报警数据");
    }

    /**
     * 获取预警报警详细信息
     */
    @PreAuthorize("@ss.hasPermi('alert:alert:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysFirealertService.selectSysFirealertById(id));
    }

    /**
     * 新增预警报警
     */
    @PreAuthorize("@ss.hasPermi('alert:alert:add')")
    @Log(title = "预警报警", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysFirealert sysFirealert)
    {
        return toAjax(sysFirealertService.insertSysFirealert(sysFirealert));
    }

    /**
     * 修改预警报警
     */
    @PreAuthorize("@ss.hasPermi('alert:alert:edit')")
    @Log(title = "预警报警", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysFirealert sysFirealert)
    {
        return toAjax(sysFirealertService.updateSysFirealert(sysFirealert));
    }

    /**
     * 删除预警报警
     */
    @PreAuthorize("@ss.hasPermi('alert:alert:remove')")
    @Log(title = "预警报警", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysFirealertService.deleteSysFirealertByIds(ids));
    }
}
