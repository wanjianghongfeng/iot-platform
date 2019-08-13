package com.litchi.cloud.iot.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/** 
 * 类说明
 * @author: tievd(wjhf) 
 * @date: 2019年8月8日 上午11:30:11
 * @vesion: 0.0.1
 */ 
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
        http.csrf().disable();
        http.authorizeRequests()
        .anyRequest().permitAll();
    }

}