package com.litchi.cloud.iot.gateway;

//import org.springframework.boot.autoconfigure.web.ResourceProperties;
//import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
//import org.springframework.context.annotation.Bean;
import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import lombok.extern.slf4j.Slf4j;

/**
 * 设置静态资源访问
 * 
 */
@RefreshScope
@Configuration
@Slf4j
public class WebConfig extends WebMvcConfigurationSupport {
	//@Value("${init.uploadPath}")
	private String uploadPath;
	//@Value("${init.web_res_path}")
	private String web_res_path="";
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("外部资源加载路径："+uploadPath);
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/uploadFile/**").addResourceLocations("file:" + uploadPath);
		super.addResourceHandlers(registry);
	}

	/**
	 * 跨域问题
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedHeaders("*").allowedMethods("*").allowedOrigins("*");
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("redirect:");
		log.info("跳转路径："+web_res_path);
		registry.addRedirectViewController("/", "/static/"+web_res_path+"view/login.html");
		registry.addRedirectViewController("/admin", "/admin/web/views/login/login.html");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}
	@Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }
}