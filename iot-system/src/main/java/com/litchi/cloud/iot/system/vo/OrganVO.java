package com.litchi.cloud.iot.system.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

/** 
 * 机构VO类
 * @author: wjhf-litchi
 * @date: 2019年8月11日 下午7:12:44
 * @vesion: 0.0.1
 */
@Data
public class OrganVO {

	private Integer id;

    private String name;

    private String organCode;

    private Date createTime;

    private Date updateTime;

    private String path;

    private Integer parentId;

    private Integer createUserId;

    private Integer organType;

    private String longitude;

    private String latitude;
    
    private List<OrganVO> children;
}
