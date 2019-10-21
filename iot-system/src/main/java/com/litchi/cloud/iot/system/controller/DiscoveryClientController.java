package com.litchi.cloud.iot.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;

import lombok.extern.slf4j.Slf4j;

/** 
 * 类说明
 * @author: wjhf-litchi
 * @date: 2019年10月21日 上午11:00:21
 * @vesion: 0.0.1
 */
@RestController
@RequestMapping("/discovery")
@Slf4j
public class DiscoveryClientController {

	@Autowired
	private ApplicationInfoManager client;
	    
	@GetMapping(value = "/offline")
	public void offLine(){
		log.info("服务离线");
		client.getInfo().setStatus(InstanceStatus.OUT_OF_SERVICE);
	}
    
	@GetMapping(value = "/down")
	public void down(){
		log.info("服务下线");
		client.getInfo().setStatus(InstanceStatus.DOWN);
	}
    
	@GetMapping(value = "/discovery/upline")
	public void upLine(){
		log.info("服务上线");
		client.getInfo().setStatus(InstanceStatus.UP);
	}
}
