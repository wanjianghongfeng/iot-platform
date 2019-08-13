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
public class ResourceController {
	
	@Autowired
	private IResourceService resourceService;
	
	@GetMapping("/treeList")
	public Result<List<ResourceVO>> getResourceTreeList() {
		// 获取当前登录用户ID WebContextUtil.getCurrUid()
        Integer userId = 0;
        return resourceService.getResourceListByUserId(userId);
	}
}

