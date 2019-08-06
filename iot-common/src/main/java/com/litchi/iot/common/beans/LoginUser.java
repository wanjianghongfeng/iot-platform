package com.litchi.iot.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class LoginUser implements Serializable {
	private static final long serialVersionUID = -1585751857310491427L;
	private Integer id;
	private String name;
	private String loginName;
	private String password;
	private Integer userType;// 用户类型，1：web；2：app
	private String purviews;// 权限值用 ,分割
	private Integer organId;
	private Integer organType;
	private String organCode;
	private String organPath;
	private String organName;
	private BigDecimal x;
	private BigDecimal y;
}
