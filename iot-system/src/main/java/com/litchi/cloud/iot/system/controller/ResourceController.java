package com.litchi.cloud.iot.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.cloud.iot.system.service.IResourceService;
import com.litchi.cloud.iot.system.vo.ResourceVO;
import com.litchi.iot.common.result.Result;
import com.litchi.iot.common.utils.WebContextUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wjhf
 * @since 2019-08-08
 */
@RestController
@RequestMapping("/resource")
@Api(value = "ResourceController|资源菜单相关的控制器", tags= {"资源菜单相关接口"})
public class ResourceController {
	
	@Autowired
	private IResourceService resourceService;
	
	@ApiOperation(value="获取树形资源列表", notes="获取树形资源列表")
	@GetMapping("/treeList")
	public Result<List<ResourceVO>> getResourceTreeList() {
		// 获取当前登录用户ID 
        Integer userId = WebContextUtil.getCurrUid();
        return resourceService.getResourceListByUserId(userId);
	}
}

