package com.degson.fire.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.degson.common.annotation.Excel;
import com.degson.common.core.domain.BaseEntity;

/**
 * 检查记录对象 sys_firerecord
 * 
 * @author yara
 * @date 2024-12-31
 */
public class SysFirerecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检查流水主键 */
    private Long recordId;

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
    /** 是否点检 */
    @Excel(name = "是否点检")
    private String pointCheck;

    /** 检查记录 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检查记录", width = 30, dateFormat = "yyyy-MM-dd")
    private String checkRecords;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
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
    public void setPointCheck(String pointCheck) 
    {
        this.pointCheck = pointCheck;
    }

    public String getPointCheck() 
    {
        return pointCheck;
    }
    public void setCheckRecords(String checkRecords) 
    {
        this.checkRecords = checkRecords;
    }

    public String getCheckRecords() 
    {
        return checkRecords;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordId", getRecordId())
            .append("fireId", getFireId())
            .append("deviceName", getDeviceName())
                .append("deviceModel", getDeviceModel())
                .append("locate", getLocate())
            .append("pointCheck", getPointCheck())
            .append("checkRecords", getCheckRecords())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
