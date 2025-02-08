package com.degson.fire.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSONObject;
import com.degson.fire.domain.Message;
import com.degson.fire.service.impl.SysQrcodeServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.degson.common.annotation.Log;
import com.degson.common.core.controller.BaseController;
import com.degson.common.core.domain.AjaxResult;
import com.degson.common.enums.BusinessType;
import com.degson.fire.domain.SysQrcode;
import com.degson.fire.service.ISysQrcodeService;
import com.degson.common.utils.poi.ExcelUtil;
import com.degson.common.core.page.TableDataInfo;
import org.springframework.web.client.RestTemplate;

/**
 * 上车乘客Controller
 * 
 * @author yara
 * @date 2024-12-17
 */
@RestController
@RequestMapping("/qrcode/qrcode")
public class SysQrcodeController extends BaseController {
    @Autowired
    private ISysQrcodeService sysQrcodeService;

    /*  查询上车乘客列表 */
    @PreAuthorize("@ss.hasPermi('qrcode:qrcode:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysQrcode sysQrcode) {
        startPage();
        List<SysQrcode> list = sysQrcodeService.selectSysQrcodeList(sysQrcode);
        return getDataTable(list);
    }

    /*  导出上车乘客列表  */
    @PreAuthorize("@ss.hasPermi('qrcode:qrcode:export')")
    @Log(title = "上车乘客", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysQrcode sysQrcode) {
        List<SysQrcode> list = sysQrcodeService.selectSysQrcodeList(sysQrcode);
        ExcelUtil<SysQrcode> util = new ExcelUtil<SysQrcode>(SysQrcode.class);
        util.exportExcel(response, list, "上车乘客数据");
    }

    /*  获取上车乘客详细信息  */
    @PreAuthorize("@ss.hasPermi('qrcode:qrcode:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(sysQrcodeService.selectSysQrcodeById(id));
    }

    /*  新增上车乘客  */
    @PreAuthorize("@ss.hasPermi('qrcode:qrcode:add')")
    @Log(title = "上车乘客", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysQrcode sysQrcode) {
        return toAjax(sysQrcodeService.insertSysQrcode(sysQrcode));
    }

    /* 修改上车乘客  */
    @PreAuthorize("@ss.hasPermi('qrcode:qrcode:edit')")
    @Log(title = "上车乘客", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysQrcode sysQrcode) {
        return toAjax(sysQrcodeService.updateSysQrcode(sysQrcode));
    }

    /*  删除上车乘客  */
    @PreAuthorize("@ss.hasPermi('qrcode:qrcode:remove')")
    @Log(title = "上车乘客", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {

        return toAjax(sysQrcodeService.deleteSysQrcodeByIds(ids));
    }


    /*  检验 上车员工信息 SAP  */
    @PostMapping("/verify")
    public String verify(@RequestBody SysQrcode sysQrcode) {
        String username = sysQrcode.getUsername();
        String jobnumber = sysQrcode.getJobnumber();
        return sysQrcodeService.verify(username, jobnumber);
    }

    /*  提交 上车员工信息 后台  */
    @PostMapping("/addXinxi")
    public int addd(@RequestBody SysQrcode sysQrcode) {
        return sysQrcodeService.insertSysQrcode(sysQrcode);
    }

//    入职系统
//    public String appId = "wx3eb78fd8a139abca";
//    public String appSecret = "9b082d8bfc241fb6bd134f29a20fa49d";

//baidu 小程序获取openid
    private String APPID = "wx6896dd3c9cc28ede";
    private String APPSECRET = "102651a78edea93a0b526bda3d48d1e8";
    private String GET_OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    @RequestMapping("/getOpenid")
    public String getOpenid(@RequestParam("code") String code) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(GET_OPENID_URL, APPID, APPSECRET, code);
        String response = restTemplate.getForObject(url, String.class);
        // 解析response获取openid
        // 例如：{"access_token":"ACCESS_TOKEN","expires_in":7200,"refresh_token":"REFRESH_TOKEN","openid":"OPENID","scope":"SCOPE"}
        String openid = parseOpenidFromResponse(response);
        return openid;
    }

    private String parseOpenidFromResponse(String response) {
        // 这里需要实现从response中解析出openid的逻辑
        // 示例解析代码（具体解析依赖于微信API返回的格式）
        String openid = ""; // 假设已经解析出openid
        return openid;
    }

    /* 通过 appid & secret & code 获取 openid
     * @param code  */
    @GetMapping("/getOpenid/{code}")
    public String getOpenidd(@PathVariable String code) throws IOException {
        System.out.println("==========================code:"+ code);
        //wx接口路径
        String url = "https://api.weixin.qq.com/sns/jscode2session?grant_type=authorization_code&" +
                "appid=" + APPID + "&secret=" + APPSECRET + "&js_code=" + code;
        //使用HttpClient发送请求
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //发送Get请求
        HttpGet request = new HttpGet(url);
        request.addHeader("Content-Type", "application/json");
        //获得响应
        CloseableHttpResponse response = httpclient.execute(request);
        //拿到响应体
        HttpEntity httpEntity = response.getEntity();
        //使用工具转换
        String result = EntityUtils.toString(httpEntity, "UTF-8");// 转成string
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println(jsonObject);//拿到的所有内容
        String openid = jsonObject.get("openid").toString();
        System.out.println(openid);//拿到的openid
        return openid;
    }


//    ru zhi
@GetMapping("/wxGetUserInfor")
public AjaxResult wxGetUserInfor(HttpServletRequest request) throws IOException {
    //通过code换取网页授权access_token
    //从request里面获取code参数(当微信服务器访问回调地址的时候，会把code参数传递过来)
    String code = request.getParameter("code");
    System.out.println("==========================code:"+ code);

    //获取code后，请求以下链接获取access_token
    String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID
            + "&secret=" + APPSECRET
            + "&code=" + code
            + "&grant_type=authorization_code";
    //通过网络请求方法来请求上面这个接口
    JSONObject access_token = JSONObject.parseObject(get(url),JSONObject.class);
    System.out.println("==========================access_token"+access_token);

    //从返回的JSON数据中取出access_token和openid，拉取用户信息时用
    String token = access_token.getString("access_token");
    String openid = access_token.getString("openid");
    if(openid == null || openid.equals("")) {
        return AjaxResult.success("未返回openid,操作失败！");
    }
    //刷新access_token（如果需要）
    //拉取用户信息(需scope为 snsapi_userinfo)
    String infoUrl ="https://api.weixin.qq.com/sns/userinfo?access_token=" + token + "&openid=" + openid + "&lang=zh_CN";
    //通过网络请求方法来请求上面这个接口
    JSONObject userinfo = JSONObject.parseObject(get(infoUrl),JSONObject.class);
    System.out.println("==========================userinfo:"+userinfo);
    return AjaxResult.success(userinfo);
}
    public static String get(String url){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode==200){
                org.apache.http.HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity,"UTF-8");
                return result;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    baidu
    // 获取上次表单信息
    @GetMapping("/getForm")
    public SysQrcode getForm(@RequestParam String openid) {
        return sysQrcodeService.getForm(openid);
    }
}
