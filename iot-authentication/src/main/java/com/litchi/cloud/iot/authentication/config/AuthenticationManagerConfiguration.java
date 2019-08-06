package com.litchi.cloud.iot.authentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.litchi.cloud.iot.authentication.service.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class AuthenticationManagerConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
