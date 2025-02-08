package com.degson.fire.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.degson.common.annotation.Excel;
import com.degson.common.core.domain.BaseEntity;

/**
 * 预警报警对象 sys_firealert
 * 
 * @author yara
 * @date 2025-02-07
 */
public class SysFirealert extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 预警主键 */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "消防设备编号")
    private Long fireId;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String deviceName;

    /** 设备型号 */
    @Excel(name = "设备型号")
    private String deviceModel;

    /** 放置位置 */
    @Excel(name = "放置位置")
    private String locate;


    /** 预警记录 */
    @Excel(name = "预警记录")
    private String alertRecord;

    /** 预警记录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预警记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date alertTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFireId(Long fireId) 
    {
        this.fireId = fireId;
    }

    public Long getFireId() 
    {
        return fireId;
    }
    public void setDeviceName(String deviceName) 
    {
        this.deviceName = deviceName;
    }

    public String getDeviceName() 
    {
        return deviceName;
    }
    public void setDeviceModel(String deviceModel) 
    {
        this.deviceModel = deviceModel;
    }

    public String getDeviceModel() 
    {
        return deviceModel;
    }
    public void setLocate(String locate) 
    {
        this.locate = locate;
    }

    public String getLocate() 
    {
        return locate;
    }
    public void setAlertRecord(String alertRecord) 
    {
        this.alertRecord = alertRecord;
    }

    public String getAlertRecord() 
    {
        return alertRecord;
    }
    public void setAlertTime(Date alertTime) 
    {
        this.alertTime = alertTime;
    }

    public Date getAlertTime() 
    {
        return alertTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fireId", getFireId())
            .append("deviceName", getDeviceName())
            .append("deviceModel", getDeviceModel())
            .append("locate", getLocate())
            .append("alertRecord", getAlertRecord())
            .append("alertTime", getAlertTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
