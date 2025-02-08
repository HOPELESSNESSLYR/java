package com.degson.fire.mapper;

import java.util.List;
import com.degson.fire.domain.SysQrcode;

/**
 * 上车乘客Mapper接口
 * 
 * @author yara
 * @date 2024-12-17
 */
public interface SysQrcodeMapper 
{
    public SysQrcode getForm(String openid);

//    public SysQrcode getPreviousForm(String openid) ;

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
     * 删除上车乘客
     * 
     * @param id 上车乘客主键
     * @return 结果
     */
    public int deleteSysQrcodeById(Long id);

    /**
     * 批量删除上车乘客
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysQrcodeByIds(Long[] ids);
}
