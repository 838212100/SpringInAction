package com.springinaction4_1;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience_Around {
	
	@Pointcut("execution(** com.springinaction4_1.Preformance.perform(..)")
	public void performance() {}
	
	@Around("performance()")
	public void watchPerformance(ProceedingJoinPoint jp) {
		try {
			System.out.println("Silenciong cell phones");
			System.out.println("Taking seats");
			jp.proceed();
			System.out.println("CLAP CLAP CLAP!!!");
		} catch (Exception e) {
			System.out.println("Demanding a refund");
		}
	}

}
