package com.litchi.cloud.iot.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/** 
 * 服务注册的相关操作
 * @author: wjhf-litchi
 * @date: 2019年10月21日 上午11:00:21
 * @vesion: 0.0.1
 */
@RestController
@RequestMapping("/discovery")
@Api(value = "DiscoveryClientController|注册服务控制器", tags= {"注册服务相关接口"})
@Slf4j
public class DiscoveryClientController {

	@Autowired
	private ApplicationInfoManager client;

	@ApiOperation(value="离线", notes="服务离线")
	@GetMapping(value = "/offline")
	public void offLine(){
		log.info("服务离线");
		client.getInfo().setStatus(InstanceStatus.OUT_OF_SERVICE);
	}

	@ApiOperation(value="下线", notes="服务下线")
	@GetMapping(value = "/down")
	public void down(){
		log.info("服务下线");
		client.getInfo().setStatus(InstanceStatus.DOWN);
	}

	@ApiOperation(value="上线", notes="服务上线")
	@GetMapping(value = "/upline")
	public void upLine(){
		log.info("服务上线");
		client.getInfo().setStatus(InstanceStatus.UP);
	}
}
