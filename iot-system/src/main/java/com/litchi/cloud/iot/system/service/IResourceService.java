package com.litchi.cloud.iot.system.service;

import com.litchi.cloud.iot.system.domain.Resource;
import com.litchi.cloud.iot.system.vo.ResourceVO;
import com.litchi.iot.common.result.Result;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜单资源 服务类
 * </p>
 *
 * @author wjhf
 * @since 2019-08-10
 */
public interface IResourceService extends IService<Resource> {

	/** 
	 * 获取资源列表（树形结构）
	 * @param userId
	 * @return 
	 * 返回类型  Result<List<ResourceVO>> 
	 */
	Result<List<ResourceVO>> getResourceListByUserId(Integer userId);

}
