package com.degson.fire.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.degson.fire.mapper.SysQrcodeMapper;
import com.degson.fire.domain.SysQrcode;
import com.degson.fire.service.ISysQrcodeService;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.*;
/**
/**
 * 上车乘客Service业务层处理
 * 
 * @author yara
 * @date 2024-12-17
 */
@Service
public class SysQrcodeServiceImpl implements ISysQrcodeService 
{
    @Autowired
    private SysQrcodeMapper sysQrcodeMapper;

    /**
     * 查询上车乘客
     * 
     * @param id 上车乘客主键
     * @return 上车乘客
     */
    @Override
    public SysQrcode selectSysQrcodeById(Long id)
    {
        return sysQrcodeMapper.selectSysQrcodeById(id);
    }

    /**
     * 查询上车乘客列表
     * 
     * @param sysQrcode 上车乘客
     * @return 上车乘客
     */
    @Override
    public List<SysQrcode> selectSysQrcodeList(SysQrcode sysQrcode)
    {
        return sysQrcodeMapper.selectSysQrcodeList(sysQrcode);
    }

    /**
     * 新增上车乘客
     * 
     * @param sysQrcode 上车乘客
     * @return 结果
     */
    @Override
    public int insertSysQrcode(SysQrcode sysQrcode)
    {
        return sysQrcodeMapper.insertSysQrcode(sysQrcode);
    }

    /**
     * 修改上车乘客
     * 
     * @param sysQrcode 上车乘客
     * @return 结果
     */
    @Override
    public int updateSysQrcode(SysQrcode sysQrcode)
    {
        return sysQrcodeMapper.updateSysQrcode(sysQrcode);
    }

    /**
     * 批量删除上车乘客
     * 
     * @param ids 需要删除的上车乘客主键
     * @return 结果
     */
    @Override
    public int deleteSysQrcodeByIds(Long[] ids)
    {
        return sysQrcodeMapper.deleteSysQrcodeByIds(ids);
    }

    /**
     * 删除上车乘客信息
     * 
     * @param id 上车乘客主键
     * @return 结果
     */
    @Override
    public int deleteSysQrcodeById(Long id)
    {
        return sysQrcodeMapper.deleteSysQrcodeById(id);
    }

    @Override
    public String verify(String username, String jobnumber) {
//        :8810 测试环境
        String url = "http://119.13.89.192:8811/sap/bc/srt/rfc/sap/zhr_o_employee_informationws/800/zhr_o_employee_informationws/zhr_o_employee_informationws";
        int timeout = 100000000;
        HttpClient client = new HttpClient();
        JSONObject xmlobj = new JSONObject();
        //如果需要用户名密码验证；不需要验证登录则不需要以下4行
//        UsernamePasswordCredentials creds = new UsernamePasswordCredentials("L20891", "882158");
//        client.getState().setCredentials(AuthScope.ANY, creds);
        //webservice 地址
        PostMethod postMethod = new PostMethod(url);
        // 设置连接超时
        client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
        // 设置读取时间超时
        client.getHttpConnectionManager().getParams().setSoTimeout(timeout);
        // 然后把Soap请求数据添加到PostMethod中
        RequestEntity  requestEntity = null;
        try {
            requestEntity = new StringRequestEntity(getXMLVerify(username, jobnumber),"text/xml", "UTF-8");
//            requestEntity = new StringRequestEntity(getXMLOne(supply,materialNo,purchaseNo,productDay,createDay,page),"text/xml", "UTF-8");
        } catch (UnsupportedEncodingException e) {
//            log.error(e.toString());
        }
        // 设置请求头部，否则可能会报 “no SOAPAction header” 的错误
        postMethod.setRequestHeader("SOAPAction", "");
        // 设置请求体
        postMethod.setRequestEntity(requestEntity);
        int status = 0;
        try {
            status = client.executeMethod(postMethod);
            InputStream is = postMethod.getResponseBodyAsStream();
            // 获取请求结果字符串
            String result = IOUtils.toString(is);
            xmlobj = XML.toJSONObject(result);
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return xmlobj.toString();
    }

    public String getXMLVerify(String username, String jobnumber) {
        String soapXML=
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:sap-com:document:sap:rfc:functions\">\n" +
                        "   <soapenv:Header/>\n" +
                        "   <soapenv:Body>\n" +
                        "      <urn:ZHR_O_EMPLOYEE_INFORMATION>\n" +
                        "         <!--Optional:-->\n" +
                        "         <I_ICNUM></I_ICNUM>\n" +
                        "         <!--Optional:-->\n" +
                        "         <I_NACHN>"+username+"</I_NACHN>\n" +
                        "         <!--Optional:-->\n" +
                        "         <I_PERNR>"+jobnumber+"</I_PERNR>\n" +
                        "         <OUT_STATUS>\n" +
                        "            <!--Zero or more repetitions:-->\n" +
                        "            <item>\n" +
                        "               <E_TYPE></E_TYPE>\n" +
                        "               <E_MSG></E_MSG>\n" +
                        "            </item>\n" +
                        "         </OUT_STATUS>\n" +
                        "         <OUT_TAB>\n" +
                        "            <!--Zero or more repetitions:-->\n" +
                        "            <item>\n" +
                        "               <PERNR></PERNR>\n" +
                        "               <NACHN></NACHN>\n" +
                        "               <GESCH></GESCH>\n" +
                        "               <ZONE_STEXT></ZONE_STEXT>\n" +
                        "               <ZTWO_STEXT></ZTWO_STEXT>\n" +
                        "               <ZTHREE_STEXT></ZTHREE_STEXT>\n" +
                        "               <ZFOUR_STEXT></ZFOUR_STEXT>\n" +
                        "               <ZFIVE_STEXT></ZFIVE_STEXT>\n" +
                        "               <ZSIX_STEXT></ZSIX_STEXT>\n" +
                        "               <GBDAT></GBDAT>\n" +
                        "               <FTEXT></FTEXT>\n" +
                        "               <ZRZ_DATE></ZRZ_DATE>\n" +
                        "               <BEGDA></BEGDA>\n" +
                        "               <ZGL></ZGL>\n" +
                        "               <ZPLANS_TEXT></ZPLANS_TEXT>\n" +
                        "               <TEL></TEL>\n" +
                        "               <ICNUM></ICNUM>\n" +
                        "               <STAT1></STAT1>\n" +
                        "            </item>\n" +
                        "         </OUT_TAB>\n" +
                        "      </urn:ZHR_O_EMPLOYEE_INFORMATION>\n" +
                        "   </soapenv:Body>\n" +
                        "</soapenv:Envelope>";
        return soapXML;
    }

    // 获取上次表单信息
//    public SysQrcode getPreviousForm(String openid) {
//
//        return SysQrcodeMapper.getPreviousForm(openid);
//    }
//    public SysQrcode getPreviousForm(String openId) {
//        SysQrcode userForm = SysQrcodeMapper.getPreviousForm(openId);
//        if (userForm != null) {
//            return userForm; // 假设你有一个方法可以将JSON字符串转换为Map
//        }
//        return new SysQrcode();
//    }

    public SysQrcode getForm(String openid) {
        return sysQrcodeMapper.getForm(openid);
    }
}
