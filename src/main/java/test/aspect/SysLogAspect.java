package test.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SysLogAspect {
	
	@Pointcut("@annotation(test.aspect.OperationLogger)")
	public void controllerAspect() {}
	
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {

		//获取注解相应的描述
		Map<String, Object> map = getValues(joinPoint);
		System.out.println(map.toString());

		Object[] obj = joinPoint.getArgs();

		if(obj.length > 0) {
			System.out.println("我是一个前置通知");
			System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
			System.out.println("目标方法所属类的简单类名:" + joinPoint.getSignature().getDeclaringType().getSimpleName());
			System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
			System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
			System.out.println("被代理的对象:" + joinPoint.getTarget());
			System.out.println("代理对象自己:" + joinPoint.getThis());
		}

	}
	
	/**
	 * 获取注解描述
	 * @param joinPoint
	 * @return
	 */
	private Map<String, Object> getValues(JoinPoint joinPoint){
		
		Map<String, Object> map = new HashMap<String, Object>();
		//getList
		String methodName = joinPoint.getSignature().getName();
		//test.aspect.TestController
		String classType = joinPoint.getTarget().getClass().getName();
		Class<?> clazz;
		try {
			clazz = Class.forName(classType);
			Method[] methods = clazz.getDeclaredMethods();//获取本类所有方法(包括私有的)、 getMethods()获取本类以及父类或者父接口中所有的公共方法
			for (Method method : methods){
				//如果以这个方法上面的注解值日志注解并且方法的名称是之前截取到的方法名
				if (method.isAnnotationPresent(OperationLogger.class) && method.getName().equals(methodName)){
					OperationLogger annotation = method.getAnnotation(OperationLogger.class);//获取指定类型的注解
					if (annotation != null) {
						Method[] annotMethods = OperationLogger.class.getDeclaredMethods();
						for(Method annotMethod : annotMethods) {
							String key = annotMethod.getName();
							map.put(key, annotMethod.invoke(annotation));
						}
					}
				}
			}
			return map;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取 方法参数列表中的某些值
	 * @param joinPoint
	 * @param type test.aspect.User
	 */
	public void getSomeValue(JoinPoint joinPoint, String type) {
		try {
			for (int i = 0, n = joinPoint.getArgs().length; i < n; i++) {
				if(joinPoint.getArgs()[i].getClass().getTypeName().equalsIgnoreCase(Class.forName(type).getName())) {
					System.out.println(joinPoint.getArgs()[i].getClass().getName());
					System.out.println("第" + (i+1) + "个参数为:" + joinPoint.getArgs()[i]);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Pointcut("execution(* test.aspect.TestController.getName(..))")
	public void getName() {}
	
	@Before("getName()")
	public void getName(JoinPoint joinPoint) {
		
		System.out.println("------------------------------------------------------------------------");
		System.out.println("我是getName的切入点");
		System.out.println("------------------------------------------------------------------------");
		
	}

}
