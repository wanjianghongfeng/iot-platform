package com.litchi.iot.common.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.litchi.iot.common.enums.ErrorCodeEnum;
import com.litchi.iot.common.exception.LMPRuntimeException;
import com.litchi.iot.common.result.Result;

import lombok.extern.slf4j.Slf4j;

/** 
 * 异常切面
 * @author: tievd(wjhf)
 * @date: 2019年8月10日 下午4:25:12
 * @vesion: 0.0.1
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class GlobalAspect {

	@Around(value = "execution(* com.litchi.cloud.iot.*.controller.*.*(..))")
	public Object Around(ProceedingJoinPoint pjp) throws InstantiationException, IllegalAccessException {
		Result<?> response = Result.ok(ErrorCodeEnum.RESULT_SUCCESS.getMsg());
		StopWatch watch = new StopWatch();
		watch.start();
		long cost = 0;
		try {
			response = (Result<?>) pjp.proceed(pjp.getArgs());
		} catch (Throwable e) {
			log.error("出现错误：", e.getMessage(), e);
			Signature signature = pjp.getSignature();
			Class<?> returnType = ((MethodSignature) signature).getReturnType();
			Object object = returnType.newInstance();
			if (object instanceof Result) {
				response = (Result<?>) object;
				if (e instanceof LMPRuntimeException) {
					LMPRuntimeException apiException = (LMPRuntimeException) e;
					response = Result.error(apiException.getExceptionEnums().getCode(), apiException.getExceptionEnums().getMsg());
				} else {
					response = Result.error(ErrorCodeEnum.SYSTEM_ERROR.getCode(), ErrorCodeEnum.SYSTEM_ERROR.getMsg());
				}
			} else {
				response = Result.error(ErrorCodeEnum.BUSINESS_FAIL.getCode(), ErrorCodeEnum.BUSINESS_FAIL.getMsg());
			}
		}
		watch.stop();
		cost = watch.getTotalTimeMillis();
		log.info("Request Processing completed, cost :" + cost);
		return response;
	}
}
