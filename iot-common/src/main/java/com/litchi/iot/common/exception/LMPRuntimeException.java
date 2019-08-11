package com.litchi.iot.common.exception;

import com.litchi.iot.common.enums.ErrorCodeEnum;

/** 
 * 运行时异常
 * @author: tievd(wjhf)
 * @date: 2019年8月10日 下午4:30:56
 * @vesion: 0.0.1
 */
public class LMPRuntimeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private ErrorCodeEnum exceptionEnums;

	public LMPRuntimeException(ErrorCodeEnum exceptionEnums) {
		super(exceptionEnums.getMsg());
		this.exceptionEnums = exceptionEnums;
	}

	public LMPRuntimeException(String message, Throwable cause) {
		super(message, cause);
		this.exceptionEnums = ErrorCodeEnum.getErrorCodeEnum(message);
	}

	public LMPRuntimeException(ErrorCodeEnum exceptionEnums, String message, Throwable cause) {
		super(message, cause);
		this.exceptionEnums = ErrorCodeEnum.getErrorCodeEnum(message);
	}

	public LMPRuntimeException(ErrorCodeEnum exceptionEnums, String message) {
		super(message);
		this.exceptionEnums = ErrorCodeEnum.getErrorCodeEnum(message);
	}

	public LMPRuntimeException(String message) {
		super(message);
		this.exceptionEnums = ErrorCodeEnum.getErrorCodeEnum(message);
	}

	public ErrorCodeEnum getExceptionEnums() {
		return exceptionEnums;
	}

	public void setExceptionEnums(ErrorCodeEnum exceptionEnums) {
		this.exceptionEnums = exceptionEnums;
	}
}
