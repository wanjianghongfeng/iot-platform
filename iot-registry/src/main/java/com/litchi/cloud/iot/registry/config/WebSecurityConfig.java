package com.litchi.cloud.iot.registry.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/** 
 * security配置类
 * @author: wjhf
 * @date: 2019年3月5日 下午4:29:15
 * @vesion: 1.0
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	/* 取消eureka 的csrf校验（此为springcloud2.0以上才需要）
	 * @param http
	 * @throws Exception 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity) 
	 */ 
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        super.configure(http);
    }
}
