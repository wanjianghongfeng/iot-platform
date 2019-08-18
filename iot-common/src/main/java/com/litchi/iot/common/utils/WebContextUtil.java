package com.litchi.iot.common.utils;

import java.util.LinkedHashMap;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/** 
 * 类说明
 * @author: wjhf-litchi
 * @date: 2019年8月12日 上午8:57:00
 * @vesion: 0.0.1
 */
public final class WebContextUtil {

	/**
	 * 获取当前上下文授权信息
	 * @return
	 */
	public static Authentication getAuthentication() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			return authentication;
		}
		throw new AuthenticationServiceException("authentication not found");
	}

	/**
	 * 获取当前上下文token的信息
	 * @return
	 */
	public static OAuth2AuthenticationDetails getDetails() {
		Authentication authentication = getAuthentication();
		if (authentication == null) {
			return null;
		}
		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
		return details;
	}

	/**
	 * 获取当前登入用户账号
	 * @return
	 */
	public static String getUsername() {
		return getAuthentication().getName();
	}

	/**
	 * 获取当前登入用户的访问accessToken
	 * @return
	 */
	public static String getAccessToken() {
		if (getDetails() == null) {
			return null;
		}
		return getDetails().getTokenValue();
	}

	/**
	 * 获取当前登录用户id
	 * @return 返回类型 Long
	 */
	public static Integer getCurrUid() {
		LinkedHashMap<String, Object> map = null;
		map = getCurrPrincipal();
		if (map != null) {
			return Integer.valueOf(map.get("id").toString());
		}
		return null;
	}

	/**
	 * 获取当前登录用户organId
	 * @return 返回类型 Long
	 */
	public static Integer getOrganId() {
		LinkedHashMap<String, Object> map = null;
		map = getCurrPrincipal();
		if (map != null) {
			return Integer.valueOf(map.get("organId").toString());
		}
		return null;
	}

	/**
	 * 判断是否是管理员
	 * @return 返回类型 boolean
	 */
	public static boolean isAdmin() {
		LinkedHashMap<String, Object> map = null;
		map = getCurrPrincipal();
		if (map != null) {
			return Long.valueOf(map.get("id").toString()) == 1;
		}
		return false;
	}

	/**
	 * 获取当前登录用户信息
	 * @return 返回类型 LinkedHashMap<String,Object>
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, Object> getCurrPrincipal() {
		LinkedHashMap<String, Object> map = null;
		OAuth2Authentication auth2Authentication = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null)
			return null;
		Authentication authentication = context.getAuthentication();
		if (authentication == null) {
			return null;
		}
		if (authentication instanceof OAuth2Authentication) {
			auth2Authentication = (OAuth2Authentication) authentication;
		}
		Authentication userAuthentication = auth2Authentication.getUserAuthentication();
		if (userAuthentication == null) {
			return null;
		}
		Object details = userAuthentication.getDetails();
		if (details != null && details instanceof LinkedHashMap) {
			map = (LinkedHashMap<String, Object>) details;
		}
		map = (LinkedHashMap<String, Object>) map.get("principal");
		if (map != null) {
			return map;
		}
		return null;
	}

	public static void clear() {
		SecurityContextHolder.clearContext();
	}
}
