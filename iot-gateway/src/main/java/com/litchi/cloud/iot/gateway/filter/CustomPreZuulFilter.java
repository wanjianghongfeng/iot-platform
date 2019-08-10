package com.litchi.cloud.iot.gateway.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

/** 
 * zuul拦截
 * @author: tievd(wjhf) 
 * @date: 2019年8月9日 下午7:11:03
 * @vesion: 0.0.1
 */ 
@Component
@Slf4j
public class CustomPreZuulFilter extends ZuulFilter {
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		if (request.getRequestURI().contains("/oauth/token")) {
			if (ctx.getRequest().getMethod().equals(HttpMethod.POST.name())) {
				byte[] encoded = null;
				try {
					encoded = Base64.encode("browser:".getBytes("UTF-8"));
					ctx.addZuulRequestHeader("Authorization", "Basic " + new String(encoded));
					log.info("用户认证登录");
				} catch (UnsupportedEncodingException e) {
					log.error(e.getMessage());
				}
			}
		}
//		else {
//			Object accessToken = request.getParameter("accessToken");
//			if (accessToken == null) {
//				log.warn("access token is empty");
//				// 过滤该请求，不往下级服务去转发请求，到此结束
//				ctx.setSendZuulResponse(false);
//				ctx.setResponseStatusCode(401);
//				ctx.setResponseBody("{\"result\":\"accessToken为空!\"}");
//				ctx.getResponse().setContentType("text/html;charset=UTF-8");
//				return null;
//			}
//		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return -1;
	}

	@Override
	public String filterType() {
		return "pre";
	}
}