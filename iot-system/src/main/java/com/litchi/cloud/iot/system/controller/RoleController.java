package com.litchi.cloud.iot.system.controller;


import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.cloud.iot.system.service.IRoleService;
import com.litchi.cloud.iot.system.vo.RoleVO;
import com.litchi.iot.common.beans.MyPage;
import com.litchi.iot.common.result.PageResult;
import com.litchi.iot.common.result.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/** 
 * 角色操作相关控制器
 * @author: wjhf-litchi
 * @date: 2019年8月11日 下午3:22:46
 * @vesion: 0.0.1
 */ 
@RequestMapping("/role")
@Api(value = "RoleController|角色操作相关的控制器", tags= {"角色操作相关接口"})
@RestController
public class RoleController {

	@Resource
	private IRoleService roleService;

	@ApiOperation(value="新建角色", notes="根据RoleVO新建角色")
	@ApiImplicitParam(name="RoleVO", value="角色类VO", required=true, dataType="RoleVO")
	@PostMapping
	public Result<String> save(@RequestBody RoleVO roleVO) {
		return roleService.save(roleVO);
	}
	
	@ApiOperation(value="更新角色", notes="根据RoleVO更新角色")
	@ApiImplicitParam(name="RoleVO", value="角色类VO", required=true, dataType="RoleVO")
	@PutMapping
	public Result<String> update(@RequestBody RoleVO roleVO) {
		return roleService.update(roleVO);
	}

	@ApiOperation(value="删除角色", notes="根据id删除角色")
	@ApiImplicitParam(name="id", value="角色id", required=true, dataType="int", paramType="path")
	@DeleteMapping("/{id}")
	public Result<String> delete(@PathVariable Integer id) {
		return roleService.delete(id);
	}

	@ApiOperation(value="获取角色信息", notes="根据id获取角色信息")
	@ApiImplicitParam(name="id", value="角色id", required=true, dataType="int", paramType="path")
	@GetMapping("/{id}")
	public Result<RoleVO> getById(@PathVariable Integer id) {
		return roleService.getRoleById(id);
	}

	@ApiOperation(value="分页获取角色信息", notes="分页获取角色信息")
	@ApiImplicitParam(name="RoleVO", value="角色类VO", dataType="RoleVO")
	@PostMapping("/page")
	public PageResult<RoleVO> getPageList(@RequestBody MyPage search) {
		return roleService.getPageList(search);
	}

    /**
      * 授权
     *
     * @param roleId 角色ID
     * @param roleVO 资源
     * @return
     */
    @PutMapping(value = "/grant/{roleId}")
    public Result<String> grant(@PathVariable("roleId") Integer roleId, @RequestBody RoleVO roleVO) {
        Set<Integer> resourceIdSet = roleVO.getResourceIdSet();
        if (resourceIdSet == null || resourceIdSet.size() <= 0) {
            return Result.error("资源不能为空");
        }
        roleService.grant(roleId, resourceIdSet);
        return Result.ok();
    }

    /**
     * 获取角色资源
     *
     * @return
     */
    @GetMapping("/resources/{roleId}")
    public Result<List<Integer>> getRoleResources(@PathVariable("roleId") Integer roleId) {
        List<Integer> roleResources = roleService.getRoleResources(roleId);
        return Result.ok(roleResources);
    }
}

