package com.litchi.cloud.iot.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/** 
 * 类说明
 * @author: wjhf
 * @date: 2019年3月5日 上午10:53:36
 * @vesion: 1.0
 */ 
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
	}
}
