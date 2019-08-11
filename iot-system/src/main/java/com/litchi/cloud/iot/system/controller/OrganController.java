package com.litchi.cloud.iot.system.controller;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.cloud.iot.system.service.IOrganService;
import com.litchi.cloud.iot.system.vo.OrganVO;
import com.litchi.iot.common.beans.MyPage;
import com.litchi.iot.common.result.PageResult;
import com.litchi.iot.common.result.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/** 
 * 机构控制器类
 * @author: wjhf-litchi
 * @date: 2019年8月11日 下午7:21:31
 * @vesion: 0.0.1
 */ 
@RestController
@RequestMapping("/organ")
@Api(value = "OrganController|机构操作相关的控制器", tags= {"机构操作相关接口"})
public class OrganController {

	@Resource
	private IOrganService organService;

	@ApiOperation(value="新建机构", notes="根据OrganVO新建机构")
	@ApiImplicitParam(name="OrganVO", value="机构类VO", required=true, dataType="OrganVO")
	@PostMapping
	public Result<String> save(@RequestBody OrganVO organVO) {
		return organService.save(organVO);
	}
	
	@ApiOperation(value="更新机构", notes="根据OrganVO更新机构")
	@ApiImplicitParam(name="OrganVO", value="机构类VO", required=true, dataType="OrganVO")
	@PutMapping
	public Result<String> update(@RequestBody OrganVO organVO) {
		return organService.update(organVO);
	}

	@ApiOperation(value="删除机构", notes="根据id删除机构")
	@ApiImplicitParam(name="id", value="机构id", required=true, dataType="int", paramType="path")
	@DeleteMapping("/{id}")
	public Result<String> delete(@PathVariable Integer id) {
		return organService.delete(id);
	}

	@ApiOperation(value="获取机构信息", notes="根据id获取机构信息")
	@ApiImplicitParam(name="id", value="机构id", required=true, dataType="int", paramType="path")
	@GetMapping("/{id}")
	public Result<OrganVO> getById(@PathVariable Integer id) {
		return organService.getOrganById(id);
	}

	@ApiOperation(value="分页获取机构信息", notes="分页获取机构信息")
	@ApiImplicitParam(name="OrganVO", value="机构类VO", dataType="OrganVO")
	@GetMapping("/page")
	public PageResult<OrganVO> getPageList(MyPage search) {
		return organService.getPageList(search);
	}
}

