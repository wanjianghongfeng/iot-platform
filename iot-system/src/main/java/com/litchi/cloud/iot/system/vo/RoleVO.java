package com.litchi.cloud.iot.system.vo;

import com.litchi.iot.common.beans.MyPage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
 * 角色类VO
 * @author: wjhf-litchi
 * @date: 2019年8月11日 下午3:28:33
 * @vesion: 0.0.1
 */
@Data
@ApiModel(value="RoleVO",description="角色对象VO")
public class RoleVO extends MyPage{

	private Integer id;

	@ApiModelProperty(value="角色名称", name="name", required=true)
    private String name;

    private String remark;

    private Integer seq;

    private Integer pid;
}
