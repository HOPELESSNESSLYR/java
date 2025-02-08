package com.degson.fire.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.degson.common.annotation.Excel;
import com.degson.common.core.domain.BaseEntity;

/**
 * 消防设施管理对象 sys_firefighting
 *
 * @author yara
 * @date 2024-11-01
 */
public class SysFirefighting extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 消防设施ID */
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

    /** 生产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productDate;

    /** 安装时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "安装时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date installTime;

    /** 到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expiryDate;

    /** 状态是否有效 */
    @Excel(name = "状态是否有效")
    private String status;

    /** 是否点检 */
    @Excel(name = "是否点检")
    private String pointCheck;

    /** 设备图片 */
    @Excel(name = "设备图片")
    private String image;

    /** 设备图片 */
    @Excel(name = "二维码内容")
    private String qrContent;

    /** 灭火器ID */
    @Excel(name = "灭火器ID")
    private String killFireId;

    /** 消火栓ID */
    @Excel(name = "消火栓ID")
    private String fireHydrantId;

    /** 水泵房ID */
    @Excel(name = "水泵房ID")
    private String pumpRoomId;

    /** 阀组间ID */
    @Excel(name = "阀组间ID")
    private String groupRoomId;

    /** 设备分组（灭火器 消火栓 水泵房 阀组间） */
    @Excel(name = "设备分组", readConverterExp = "灭=火器,消=火栓,水=泵房,阀=组间")
    private String deviceGroup;

    /** 检查记录 */
    @Excel(name = "检查记录")
    private String checkRecords;

    /** 维护记录 */
    @Excel(name = "维护记录")
    private String maintainRecords;

    /** 报警数据 */
    @Excel(name = "报警数据")
    private String alarmData;

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
    public void setProductDate(Date productDate)
    {
        this.productDate = productDate;
    }

    public Date getProductDate()
    {
        return productDate;
    }
    public void setInstallTime(Date installTime)
    {
        this.installTime = installTime;
    }

    public Date getInstallTime()
    {
        return installTime;
    }
    public void setExpiryDate(Date expiryDate) {this.expiryDate = expiryDate;}

    public Date getExpiryDate() {return expiryDate;}
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setPointCheck(String pointCheck)
    {
        this.pointCheck = pointCheck;
    }

    public String getPointCheck()
    {
        return pointCheck;
    }
    public void setImage(String image)
    {
        this.image = image;
    }

    public String getImage()
    {
        return image;
    }
    public void setQrContent(String qrContent) {this.qrContent = qrContent;}

    public String getQrContent()
    {
        return qrContent;
    }
    public void setKillFireId(String killFireId)
    {
        this.killFireId = killFireId;
    }

    public String getKillFireId()
    {
        return killFireId;
    }
    public void setFireHydrantId(String fireHydrantId)
    {
        this.fireHydrantId = fireHydrantId;
    }

    public String getFireHydrantId()
    {
        return fireHydrantId;
    }
    public void setPumpRoomId(String pumpRoomId)
    {
        this.pumpRoomId = pumpRoomId;
    }

    public String getPumpRoomId()
    {
        return pumpRoomId;
    }
    public void setGroupRoomId(String groupRoomId)
    {
        this.groupRoomId = groupRoomId;
    }

    public String getGroupRoomId()
    {
        return groupRoomId;
    }
    public void setDeviceGroup(String deviceGroup)
    {
        this.deviceGroup = deviceGroup;
    }

    public String getDeviceGroup()
    {
        return deviceGroup;
    }
    public void setCheckRecords(String checkRecords)
    {
        this.checkRecords = checkRecords;
    }

    public String getCheckRecords()
    {
        return checkRecords;
    }
    public void setMaintainRecords(String maintainRecords)
    {
        this.maintainRecords = maintainRecords;
    }

    public String getMaintainRecords()
    {
        return maintainRecords;
    }
    public void setAlarmData(String alarmData)
    {
        this.alarmData = alarmData;
    }

    public String getAlarmData()
    {
        return alarmData;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("fireId", getFireId())
                .append("deviceName", getDeviceName())
                .append("deviceModel", getDeviceModel())
                .append("locate", getLocate())
                .append("productDate", getProductDate())
                .append("installTime", getInstallTime())
                .append("expiryDate", getExpiryDate())
                .append("status", getStatus())
                .append("pointCheck", getPointCheck())
                .append("image", getImage())
                .append("killFireId", getKillFireId())
                .append("fireHydrantId", getFireHydrantId())
                .append("pumpRoomId", getPumpRoomId())
                .append("groupRoomId", getGroupRoomId())
                .append("deviceGroup", getDeviceGroup())
                .append("checkRecords", getCheckRecords())
                .append("maintainRecords", getMaintainRecords())
                .append("alarmData", getAlarmData())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("qrcontent", getQrContent())
                .toString();
    }
}
