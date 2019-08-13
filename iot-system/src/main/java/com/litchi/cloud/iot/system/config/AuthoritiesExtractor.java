package com.litchi.cloud.iot.system.config;

import java.util.List;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;

/** 
 * 类说明
 * @author: wjhf-litchi
 * @date: 2019年8月13日 下午2:26:37
 * @vesion: 0.0.1
 */
public interface AuthoritiesExtractor {

	/**
	 * Extract the authorities from the resource server's response.
	 * @param map the response
	 * @return the extracted authorities
	 */
	List<GrantedAuthority> extractAuthorities(Map<String, Object> map);

}