package com.degson.fire.controller;

import com.degson.fire.domain.Uniapp;
import com.degson.fire.mapper.SysFirefightingMapper;
import com.degson.fire.service.impl.SysFirefightingServiceImpl;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.WriterException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.degson.common.annotation.Log;
import com.degson.common.core.controller.BaseController;
import com.degson.common.core.domain.AjaxResult;
import com.degson.common.enums.BusinessType;
import com.degson.fire.domain.SysFirefighting;
import com.degson.fire.service.ISysFirefightingService;
import com.degson.common.utils.poi.ExcelUtil;
import com.degson.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 灭火器管理Controller
 * @author yara
 * @date 2024-10-30
 */
@RestController
@RequestMapping("/fire/firefighting")
public class SysFirefightingController extends BaseController
{
    @Autowired
    private ISysFirefightingService sysFirefightingService;
    @Autowired
    private SysFirefightingServiceImpl sysFirefightingServiceImpl;
//    @Autowired
//    private QRCodeUtil qrCodeUtil;
    @Autowired
    private SysFirefightingMapper sysFirefightingMapper;

    /*** 查询列表*/
    @PreAuthorize("@ss.hasPermi('fire:firefighting:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysFirefighting sysFirefighting)
    {
        startPage();
        List<SysFirefighting> list = sysFirefightingService.selectSysFirefightingList(sysFirefighting);
        return getDataTable(list);
    }

    /*** 导出列表*/
    @PreAuthorize("@ss.hasPermi('fire:firefighting:export')")
    @Log(title = "消防器材管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysFirefighting sysFirefighting)
    {
        List<SysFirefighting> list = sysFirefightingService.selectSysFirefightingList(sysFirefighting);
        ExcelUtil<SysFirefighting> util = new ExcelUtil<SysFirefighting>(SysFirefighting.class);
        util.exportExcel(response, list, "灭火器管理数据");
    }

    /* 获取详细信息*/
    @PreAuthorize("@ss.hasPermi('fire:firefighting:query')")
    @GetMapping(value = "/{fireId}")
    public AjaxResult getInfo(@PathVariable("fireId") Long fireId)
    {
        return success(sysFirefightingService.selectSysFirefightingByFireId(fireId));
    }

    /* 新增*/
    @PreAuthorize("@ss.hasPermi('fire:firefighting:add')")
    @Log(title = "消防器材管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysFirefighting sysFirefighting)
    {
        return toAjax(sysFirefightingService.insertSysFirefighting(sysFirefighting));
    }

    /*修改*/
    @PreAuthorize("@ss.hasPermi('fire:firefighting:edit')")
    @Log(title = "消防器材管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysFirefighting sysFirefighting)
    {
        return toAjax(sysFirefightingService.updateSysFirefighting(sysFirefighting));
    }


    @PostMapping("/update")
    public int update(@RequestBody SysFirefighting sysFirefighting)
    {
        return sysFirefightingMapper.updateSysFire(sysFirefighting);
    }

    /* 删除*/
    @PreAuthorize("@ss.hasPermi('fire:firefighting:remove')")
    @Log(title = "消防器材管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fireIds}")
    public AjaxResult remove(@PathVariable Long[] fireIds)
    {
        return toAjax(sysFirefightingService.deleteSysFirefightingByFireIds(fireIds));
    }


    /* 生成二维码 */
    @PostMapping("/code/toStream")
    public String generateStream(@RequestBody String qrContent) throws WriterException {
        return sysFirefightingService.generateStream(qrContent);
    }
    @PreAuthorize("@ss.hasPermi('fire:firefighting:qrcode')")
    @PostMapping("/code/list")
    public List<String> generateStreamList(@RequestBody List<String> texts) throws WriterException, IOException {
        return sysFirefightingService.generateStreamList(texts);
    }

//    @PostMapping("/code/toImg")
//    public BufferedImage generateImage(@RequestBody String qrContent) throws WriterException {
//        return sysFirefightingServiceImpl.generateImage(qrContent);
//    }

    /**
     * 小程序
     */
    @PostMapping("uni")
    public int uni(@RequestBody Uniapp uniapp) throws IOException {
        String qrContent = uniapp.getQrContent();
        String image = uniapp.getImage();
        System.out.println("image = " + image);
        System.out.println("qrContent = " + qrContent);
        return sysFirefightingService.uni(qrContent, image);
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<SysFirefighting> util = new ExcelUtil<SysFirefighting>(SysFirefighting.class);
        util.importTemplateExcel(response, "消防设备数据");
    }
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean isUpdateSupport) throws Exception
    {
        ExcelUtil<SysFirefighting> util = new ExcelUtil<SysFirefighting>(SysFirefighting.class);
        List<SysFirefighting> SysFirefightingList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = sysFirefightingService.importSysFirefightinginfor(SysFirefightingList, isUpdateSupport, operName);
        return AjaxResult.success(message);
    }
    // 新写接口 post 列表
    @GetMapping("/getlist")
//    public AjaxResult getlist(@RequestBody SysFirefighting sysFirefighting)
//    {
//        List<SysFirefighting> list = sysFirefightingService.selectSysFirefightingList(sysFirefighting);
//        return AjaxResult.success(list);
//    }
    public List<SysFirefighting> getlist(@RequestParam("qrContent") String qrContent)
    {
        SysFirefighting sysFirefighting = new SysFirefighting();
        sysFirefighting.setQrContent(qrContent);
        List<SysFirefighting> list = sysFirefightingService.selectSysFirefightingList(sysFirefighting);
        return list;
    }
}
