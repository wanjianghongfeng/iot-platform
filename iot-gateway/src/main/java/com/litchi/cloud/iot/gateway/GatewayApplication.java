package com.litchi.cloud.iot.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

/** 
 * 网关服务
 * @author: wjhf
 * @date: 2019年3月5日 下午1:42:23
 * @vesion: 1.0
 */ 
@EnableZuulProxy
@SpringCloudApplication
public class GatewayApplication {
	
	//https相关配置。暂时不考虑
	/*@Value("${https.port}")
    private Integer httpsport;
	
	@Value("${server.port}")
    private Integer port;
	
	@Value("${https.ssl.key-store-password}")
    private String key_store_password;

    @Value("${https.ssl.key-password}")
    private String key_password;*/
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	@ConfigurationProperties("zuul")
	@RefreshScope
	public ZuulProperties zuulProperties() {
		return new ZuulProperties();
	}
	
/*	@Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addAdditionalTomcatConnectors(createSslConnector()); // 添加http
        return tomcat;
    }
    
	
    private Connector createSslConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        File keystore;
		try {
			keystore = new ClassPathResource("zhsq.jks").getFile();
	        connector.setScheme("https");
	        connector.setSecure(true);
	        connector.setPort(httpsport);
	        protocol.setSSLEnabled(true);
	        protocol.setKeystoreFile(keystore.getAbsolutePath());
	        protocol.setKeystorePass(key_store_password);
	        protocol.setKeyPass(key_password);
		} catch (IOException e) {
			throw new IllegalStateException("can't access keystore: [" + "keystore"
                    + "] or truststore: [" + "keystore" + "]", e);
		}
        return connector;
    }*/
}
