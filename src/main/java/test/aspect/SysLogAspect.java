package test.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SysLogAspect {
	
	@Pointcut("@annotation(test.aspect.OperationLogger)")
	public void controllerAspect() {
		System.out.println("我是一个切入点");
	}
	
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		String str = joinPoint.getSignature().toLongString();
		System.out.println("str is : "+str);
		System.out.println("我是一个前置通知");
	}
	

}
