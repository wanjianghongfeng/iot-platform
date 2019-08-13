package com.litchi.cloud.iot.authentication.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litchi.cloud.iot.authentication.service.security.CustomUserDetailsService;
import com.litchi.iot.common.beans.LoginUser;
import com.litchi.iot.common.result.Result;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

	@Autowired
	@Qualifier("consumerTokenServices")
	private ConsumerTokenServices consumerTokenServices;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@GetMapping("/userinfo")
	public Principal currentUser(Principal user) {
		return user;
	}
	
	@GetMapping("/current/{userType}")
	public Result<LoginUser> user(Principal user, @PathVariable("userType") Integer userType) {
		return customUserDetailsService.getUserByLoginName(user.getName(), userType);
	}
	
	@GetMapping("/uaa/users/current")
	public Principal getUser(Principal user) {
		return user;
	}

	@PutMapping("/logouting")
	public Result<String> logouting(@RequestParam("accessToken") String accessToken) {
		try {
			log.info("logout start ......");
			consumerTokenServices.revokeToken(accessToken);
			log.info("logout end ......");
			return Result.ok("退出系统成功");
		} catch (Exception e) {
			log.error("logout error ....", e);
			return Result.error(e.getMessage());
		}
	}
}
