package com.litchi.cloud.iot.authentication.config;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

@Component
public class OAuth2WebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {

	@Override
	public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
		return super.translate(e);
	}
}
