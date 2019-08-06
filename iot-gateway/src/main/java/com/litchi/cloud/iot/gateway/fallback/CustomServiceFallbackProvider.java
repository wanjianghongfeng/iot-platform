package com.litchi.cloud.iot.gateway.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomServiceFallbackProvider implements FallbackProvider {
	@Override
	public String getRoute() {
		// 表明是为哪个微服务提供回退，*表示为所有微服务提供回退
		return "*";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		return new ClientHttpResponse() {
			@Override
			public HttpStatus getStatusCode() throws IOException {
				// fallback时的状态码
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				// 数字类型的状态码，本例返回的其实就是200，详见HttpStatus
				return this.getStatusCode().value();
			}

			@Override
			public String getStatusText() throws IOException {
				// 状态文本，本例返回的其实就是OK，详见HttpStatus
				return this.getStatusCode().getReasonPhrase();
			}

			@Override
			public void close() {
			}

			@Override
			public InputStream getBody() throws IOException {
				// 响应体
				return new ByteArrayInputStream("{\"code\":\"500\",\"msg\":\"微服务不可用，请稍后再试。\"}".getBytes());
			}

			@Override
			public HttpHeaders getHeaders() {
				// headers设定
				HttpHeaders headers = new HttpHeaders();
				MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
				headers.setContentType(mt);
				return headers;
			}
		};
	}
}
