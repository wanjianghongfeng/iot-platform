package com.litchi.cloud.iot.authentication;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringCloudApplication
@MapperScan("com.litchi.cloud.iot.authentication.mapper") 
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
public class AuthenticationApp 
{
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApp.class, args);
	}
}
