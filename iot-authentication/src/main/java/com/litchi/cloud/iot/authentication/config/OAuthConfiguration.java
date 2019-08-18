package com.litchi.cloud.iot.authentication.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import com.litchi.cloud.iot.authentication.service.security.CustomUserDetailsService;

@Configuration
@EnableAuthorizationServer
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	@Qualifier("authenticationManagerBean")   
    private AuthenticationManager authenticationManager;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private OAuth2WebResponseExceptionTranslator oAuth2WebResponseExceptionTranslator;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public static final String PREFIX = "iot-";
	private static final int TOKEN_VALIDITY_SECONDS = 60 * 60 * 60 * 12;//12小时
	private static final int TOKEN_REFRESH_SECONDS = 60 * 60 * 60 * 24 * 7;//7天

	@Value("${security.oauth2.client.client-secret:litchiStudio}")
	private String SECURITY_OAUTH2_CLIENT_CLIENTSECRET;
	
	@Bean
	public RedisTokenStore tokenStore() {
		RedisTokenStore redisTokenStore = new RedisTokenStore(redisTemplate.getConnectionFactory());
		redisTokenStore.setPrefix(PREFIX);
		return redisTokenStore;
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("browser").secret(bCryptPasswordEncoder.encode(SECURITY_OAUTH2_CLIENT_CLIENTSECRET)).authorizedGrantTypes("refresh_token", "password").scopes("ui")
				.accessTokenValiditySeconds(TOKEN_VALIDITY_SECONDS).refreshTokenValiditySeconds(TOKEN_REFRESH_SECONDS)
			.and().withClient("iot-business").secret(bCryptPasswordEncoder.encode(SECURITY_OAUTH2_CLIENT_CLIENTSECRET)) .authorizedGrantTypes("client_credentials", "refresh_token").scopes("server")
			.and().withClient("iot-system").secret(bCryptPasswordEncoder.encode(SECURITY_OAUTH2_CLIENT_CLIENTSECRET)) .authorizedGrantTypes("client_credentials", "refresh_token").scopes("server")
			.and().withClient("iot-device").secret(bCryptPasswordEncoder.encode(SECURITY_OAUTH2_CLIENT_CLIENTSECRET)) .authorizedGrantTypes("client_credentials", "refresh_token").scopes("server")
			.and().withClient("iot-bimserver").secret(bCryptPasswordEncoder.encode(SECURITY_OAUTH2_CLIENT_CLIENTSECRET)) .authorizedGrantTypes("client_credentials", "refresh_token").scopes("server")
		    .and().withClient("common-client").secret(bCryptPasswordEncoder.encode(SECURITY_OAUTH2_CLIENT_CLIENTSECRET)) .authorizedGrantTypes("client_credentials", "refresh_token").scopes("server");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.userDetailsService(customUserDetailsService).authenticationManager(authenticationManager)
		.tokenStore(tokenStore()).exceptionTranslator(oAuth2WebResponseExceptionTranslator);
	}
}
