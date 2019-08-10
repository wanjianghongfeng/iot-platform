package com.litchi.cloud.iot.system.vo;

import java.util.Date;

import com.litchi.iot.common.beans.Page;

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
public class UserVO extends Page{

    @ApiModelProperty(value="用户id", name="id")
    private Integer id;
    private Date createTime;
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
}
