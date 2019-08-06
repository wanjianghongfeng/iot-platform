package com.litchi.iot.common.beans;

import java.util.Map;

import lombok.Data;

@Data
public class Page {

	private int pageSize;//页面大小
	private int pageNo; //当前页数
	private Map<String, Object> param;
	
}
