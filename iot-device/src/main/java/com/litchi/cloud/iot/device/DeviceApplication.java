package com.litchi.cloud.iot.device;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/** 
 * 设备服务启动类
 * @author: wjhf-litchi
 * @date: 2019年8月18日 上午7:16:57
 * @vesion: 0.0.1
 */ 
@SpringCloudApplication
@EnableDiscoveryClient
@EnableOAuth2Client
@EnableFeignClients
@EnableSwagger2
@MapperScan("com.litchi.cloud.iot.device.mapper")
public class DeviceApplication 
{
	public static void main(String[] args) {
		SpringApplication.run(DeviceApplication.class, args);
	}
}

