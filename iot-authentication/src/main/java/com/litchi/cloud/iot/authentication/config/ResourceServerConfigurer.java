package com.litchi.cloud.iot.authentication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器
 * https://blog.csdn.net/u013825231/article/details/80556221
 * Created by hwj on 2018/9/11.
 */
@EnableResourceServer
@Configuration
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {

    /**
     * 这里对资源的权限配置可以用@EnableGlobalMethodSecurity(prePostEnabled = true)和@PreAuthorize进行代替
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
    			.antMatchers(
    					"/webjars/**",
    	          		"/resources/**",
    	          		"/swagger-ui.html",
    	          		"/swagger-resources/**",
    	            	"/v2/api-docs",
    	            	"/druid/**",
    	            	"/health/**",
    	            	"/info",
    	            	"/logouting")
    			.permitAll()
                .anyRequest().authenticated()//其他任何请求都需要授权
        ;
    }

    private static final String DEMO_RESOURCE_ID = "resource1";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
    }
}
