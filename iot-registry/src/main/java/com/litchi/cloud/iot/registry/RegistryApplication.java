package com.litchi.cloud.iot.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/** 
 * 类说明
 * @author: wjhf
 * @date: 2019年3月5日 上午10:53:46
 * @vesion: 1.0
 */ 
@SpringBootApplication
@EnableEurekaServer
public class RegistryApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(RegistryApplication.class, args);
    }
}
