package com.litchi.cloud.iot.system.vo;

import java.util.List;

import lombok.Data;

/** 
 * 类说明
 * @author: wjhf-litchi
 * @date: 2019年8月12日 上午9:13:42
 * @vesion: 0.0.1
 */
@Data
public class ResourceVO {

	/**
     * ID
     */
    private Integer id;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 说明
     */
    private String remark;

    /**
     * 顺序
     */
    private Integer seq;

    /**
     * URL
     */
    private String url;

    /**
     * 调用方式
     */
    private String method;

    /**
     * çˆ¶ID
     */
    private Integer pid;

    /**
     * 状态
     */
    private Integer type;

    /**
     * 页面地址
     */
    private String pageUrl;
    
    private List<ResourceVO> chil;
}
