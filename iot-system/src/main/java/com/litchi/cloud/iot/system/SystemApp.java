package com.litchi.cloud.iot.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/** 
 * 启动类
 * @author: tievd(wjhf) 
 * @date: 2019年8月8日 下午7:43:10
 * @vesion: 0.0.1
 */ 
@SpringCloudApplication
@EnableDiscoveryClient
@EnableOAuth2Client
@EnableFeignClients
@EnableSwagger2
@MapperScan("com.litchi.cloud.iot.system.mapper")
public class SystemApp 
{
	public static void main(String[] args) {
		SpringApplication.run(SystemApp.class, args);
	}
}
