package org.creativebee.schedule.aop;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(0)
@Component
public class TraceLoggingAdvice {

	protected Log log = LogFactory.getLog(this.getClass());

	@Around("execution(* org.creativebee.schedule.*.controller.*Controller.*Form(..))")
	public Object invoke(ProceedingJoinPoint pjp) throws Throwable {

		long startTime = System.currentTimeMillis();

		Object ret = null;
		try {
			System.out.printf("[AOP at before] invoke, params = %s, %s#%s%n", Arrays.toString(pjp.getArgs()),
					pjp.getTarget().getClass(), pjp.getSignature().getName());

			ret = pjp.proceed();
			return ret;
		} finally {
			System.out.printf("[AOP at after ] invoke, result = %s, %s#%s%n", ret, pjp.getTarget().getClass(),
					pjp.getSignature().getName());

			System.out.println(startTime - System.currentTimeMillis());
		}
	}
}
