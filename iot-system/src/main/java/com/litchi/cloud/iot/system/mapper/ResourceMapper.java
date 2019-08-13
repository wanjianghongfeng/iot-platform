package com.litchi.cloud.iot.system.mapper;

import com.litchi.cloud.iot.system.domain.Resource;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 菜单资源 Mapper 接口
 * </p>
 *
 * @author wjhf
 * @since 2019-08-10
 */
public interface ResourceMapper extends BaseMapper<Resource> {

	/** 
	 * 根据用户id获取资源列表
	 * @param userId
	 * @return 
	 * 返回类型  List<Resource> 
	 */
	List<Resource> getResourceListByUserId(Integer userId);

}
