package com.litchi.cloud.iot.authentication.domain;

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
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String remark;
    private Integer seq;
    private Long pid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Role{" +
        ", id=" + id +
        ", name=" + name +
        ", remark=" + remark +
        ", seq=" + seq +
        ", pid=" + pid +
        "}";
    }
}
