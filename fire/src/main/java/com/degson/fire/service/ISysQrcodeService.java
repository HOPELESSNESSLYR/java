package com.degson.fire.service;

import java.util.List;
import com.degson.fire.domain.SysQrcode;

/**
 * 上车乘客Service接口
 * 
 * @author yara
 * @date 2024-12-17
 */
public interface ISysQrcodeService 
{


    /**
     * 查询上车乘客
     * 
     * @param id 上车乘客主键
     * @return 上车乘客
     */
    public SysQrcode selectSysQrcodeById(Long id);

    /**
     * 查询上车乘客列表
     * 
     * @param sysQrcode 上车乘客
     * @return 上车乘客集合
     */
    public List<SysQrcode> selectSysQrcodeList(SysQrcode sysQrcode);

    /**
     * 新增上车乘客
     * 
     * @param sysQrcode 上车乘客
     * @return 结果
     */
    public int insertSysQrcode(SysQrcode sysQrcode);

    /**
     * 修改上车乘客
     * 
     * @param sysQrcode 上车乘客
     * @return 结果
     */
    public int updateSysQrcode(SysQrcode sysQrcode);

    /**
     * 批量删除上车乘客
     * 
     * @param ids 需要删除的上车乘客主键集合
     * @return 结果
     */
    public int deleteSysQrcodeByIds(Long[] ids);

    /**
     * 删除上车乘客信息
     * 
     * @param id 上车乘客主键
     * @return 结果
     */
    public int deleteSysQrcodeById(Long id);

    public String verify(String username, String jobnumber);

    public SysQrcode getForm(String openid);
}
