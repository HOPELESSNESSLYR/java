package com.degson.fire.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.degson.common.annotation.Excel;
import com.degson.common.core.domain.BaseEntity;

/**
 * 上车乘客对象 sys_qrcode
 * 
 * @author yara
 * @date 2024-12-17
 */
public class SysQrcode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String username;

    /** 工号 */
    @Excel(name = "工号")
    private String jobnumber;

    /** 上车时间记录 */
    @Excel(name = "上车时间记录")
    private String record;

    /** openID */
    @Excel(name = "openID")
    private String openid;


    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setJobnumber(String jobnumber) 
    {
        this.jobnumber = jobnumber;
    }

    public String getJobnumber() 
    {
        return jobnumber;
    }
    public void setRecord(String record)
    {
        this.record = record;
    }

    public String getRecord()
    {
        return record;
    }
    public void setOpenid(String openid)
    {
        this.openid = openid;
    }

    public String getOpenid()
    {
        return openid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("username", getUsername())
            .append("jobnumber", getJobnumber())
                .append("record", getRecord())
                .append("openid", getOpenid())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
            .toString();
    }
}
