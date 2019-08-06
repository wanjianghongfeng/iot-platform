package com.litchi.cloud.iot.authentication.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;

public class OauthErrorHandler extends DefaultWebResponseExceptionTranslator {

	@Override
	public ResponseEntity<OAuth2Exception> translate(Exception e) {
		ResponseEntity<OAuth2Exception> responseEntity = null;
		try {
			responseEntity = super.translate(e);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		OAuth2Exception body = responseEntity.getBody();
		body.addAdditionalInformation("status", "-5");
		body.addAdditionalInformation("errors", "[{\"message\":\"" + body.getLocalizedMessage() + "\"}]");
		body.addAdditionalInformation("message", "认证失败了,请检查用户名密码!");
		HttpHeaders headers = new HttpHeaders();
		headers.setAll(responseEntity.getHeaders().toSingleValueMap());
		return new ResponseEntity<>(body, headers, responseEntity.getStatusCode());
	}

}
