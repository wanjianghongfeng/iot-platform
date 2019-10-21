package com.litchi.cloud.iot.system.vo;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.litchi.iot.common.beans.MyPage;
import com.litchi.iot.common.utils.DateUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/** 
 * 类说明
 * @author: tievd(wjhf)
 * @date: 2019年8月10日 下午4:38:00
 * @vesion: 0.0.1
 */
@Data
@ApiModel(value="UserVO",description="用户对象VO")
public class UserVO extends MyPage{

    @ApiModelProperty(value="用户id", name="id")
    private Integer id;
    
    @JsonFormat(pattern=DateUtils.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS)
    private Date createTime;
    
    @JsonFormat(pattern=DateUtils.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS)
    private Date modifyTime;
    
    @ApiModelProperty(value="用户姓名", name="name")
    private String name;
    
    @ApiModelProperty(value="用户密码", name="password")
    private String password;
    
    @ApiModelProperty(value="用户名", name="username")
    private String username;
    
    private String purviews;
    
    @ApiModelProperty(value="机构id", name="organId")
    private Integer organId;

    @ApiModelProperty(value="角色id", name="roleIdSet")
    private Set<Integer> roleIdSet;
}
