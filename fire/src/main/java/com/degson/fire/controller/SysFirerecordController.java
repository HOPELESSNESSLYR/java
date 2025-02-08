package com.degson.fire.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.degson.fire.domain.Uniapp;
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
import com.degson.fire.domain.SysFirerecord;
import com.degson.fire.service.ISysFirerecordService;
import com.degson.common.utils.poi.ExcelUtil;
import com.degson.common.core.page.TableDataInfo;

/**
 * 检查记录Controller
 * 
 * @author yara
 * @date 2024-12-31
 */
@RestController
@RequestMapping("/record/record")
public class SysFirerecordController extends BaseController
{
    @Autowired
    private ISysFirerecordService sysFirerecordService;

    /**
     * 查询检查记录列表
     */
    @PreAuthorize("@ss.hasPermi('record:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysFirerecord sysFirerecord)
    {
        startPage();
        List<SysFirerecord> list = sysFirerecordService.selectSysFirerecordList(sysFirerecord);
        return getDataTable(list);
    }

    /**
     * 导出检查记录列表
     */
    @PreAuthorize("@ss.hasPermi('record:record:export')")
    @Log(title = "检查记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysFirerecord sysFirerecord)
    {
        List<SysFirerecord> list = sysFirerecordService.selectSysFirerecordList(sysFirerecord);
        ExcelUtil<SysFirerecord> util = new ExcelUtil<SysFirerecord>(SysFirerecord.class);
        util.exportExcel(response, list, "检查记录数据");
    }

    /**
     * 获取检查记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('record:record:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(sysFirerecordService.selectSysFirerecordByRecordId(recordId));
    }

    /**
     * 新增检查记录
     */
    @PreAuthorize("@ss.hasPermi('record:record:add')")
    @Log(title = "检查记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysFirerecord sysFirerecord)
    {
        return toAjax(sysFirerecordService.insertSysFirerecord(sysFirerecord));
    }

    /**
     * 修改检查记录
     */
    @PreAuthorize("@ss.hasPermi('record:record:edit')")
    @Log(title = "检查记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysFirerecord sysFirerecord)
    {
        return toAjax(sysFirerecordService.updateSysFirerecord(sysFirerecord));
    }

    /**
     * 删除检查记录
     */
    @PreAuthorize("@ss.hasPermi('record:record:remove')")
    @Log(title = "检查记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(sysFirerecordService.deleteSysFirerecordByRecordIds(recordIds));
    }


    /**
     * 小程序
     */
    @PostMapping("uni")
    public int uni(@RequestBody Uniapp uniapp) throws IOException {
        String qrContent = uniapp.getQrContent();
        String image = uniapp.getImage();
        System.out.println("image = " + image);
        System.out.println("qrContent = " + qrContent);
        return sysFirerecordService.uni(qrContent, image);
    }

    @PostMapping("add")
    public AjaxResult addd(@RequestBody SysFirerecord sysFirerecord)
    {
        return toAjax(sysFirerecordService.insertSysFirerecord(sysFirerecord));
    }

}
