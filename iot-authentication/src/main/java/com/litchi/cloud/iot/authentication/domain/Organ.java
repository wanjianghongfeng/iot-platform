package com.litchi.cloud.iot.authentication.domain;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wjhf
 * @since 2019-08-05
 */
public class Organ extends Model<Organ> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * æœºæž„åç§°
     */
    private String name;
    /**
     * æž„æœºç¼–ç 
     */
    @TableField("organ_code")
    private String organCode;
    /**
     * å»ºåˆ›æ—¶é—´
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * æ–°æ›´æ—¶é—´
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * æž„æœºpath
     */
    private String path;
    /**
     * çˆ¶æœºæž„id
     */
    @TableField("parent_id")
    private Integer parentId;
    /**
     * åˆ›å»ºç”¨æˆ·
     */
    @TableField("create_user_id")
    private Integer createUserId;
    /**
     * æž„æœºç±»åž‹
     */
    @TableField("organ_type")
    private Integer organType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getOrganType() {
        return organType;
    }

    public void setOrganType(Integer organType) {
        this.organType = organType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Organ{" +
        ", id=" + id +
        ", name=" + name +
        ", organCode=" + organCode +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", path=" + path +
        ", parentId=" + parentId +
        ", createUserId=" + createUserId +
        ", organType=" + organType +
        "}";
    }
}
