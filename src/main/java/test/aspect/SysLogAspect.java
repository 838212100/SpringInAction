package test.aspect;

import java.lang.reflect.Field;
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
	
	public static Map<String, String> process(Class clazz) {
		Map<String, String> map = new HashMap<String, String>();
		//找方法
		String modelName=null;
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			OperationLogger annotation = method.getAnnotation(OperationLogger.class);//获取指定类型的注解
			//动态代理对象，
			if (annotation != null) {
				System.out.println(method.getName());
				//说明有注解
//				map.put("modelName", value)
				modelName = annotation.modelName();
				System.out.println(modelName);
				String user = annotation.user();
			}
		}
		return map;
	}
	
	@Pointcut("@annotation(test.aspect.OperationLogger)")
	public void controllerAspect() {
		System.out.println("我是一个切入点");
	}
	
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		// 获取目标方法的签名

		String signature = joinPoint.getSignature().toString();

		//根据的带的签名去截取方法名
		//在目标方法的签名当中以最后一个点加1开始，以包裹参数的第一个尖括号结尾截取方法名

		String methodName =signature.substring(signature.lastIndexOf(".")+1,signature.indexOf("("));

		String classType = joinPoint.getTarget().getClass().getName();
		Class<?> clazz;
		try {
			clazz = Class.forName(classType);
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods){
				//如果以这个方法上面的注解值日志注解并且方法的名称是之前截取到的方法名
				if (method.isAnnotationPresent(OperationLogger.class) && method.getName().equals(methodName)){
					String clazzName = clazz.getName();
					System.out.println("clazzName: " + clazzName + ", methodName: "  + methodName);
					Map<String, String> map = process(clazz);
					System.out.println(map.toString());
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String str = joinPoint.getSignature().toLongString();
		System.out.println(joinPoint.getTarget().toString());
		Object[] obj = joinPoint.getArgs();
		
		System.out.println("obj[] length = " + obj.length);
		if(obj.length > 0) {
			
			System.out.println(obj[1].getClass().getName());
			System.out.println("str is : "+str);
			System.out.println("我是一个前置通知");
		}
		/*for (int i = 0; i < joinPoint.getArgs().length; i++) {
			System.out.println(joinPoint.getArgs()[i]);
		}
		System.out.println(joinPoint.getSignature().getName());*/
		
		System.out.println("--------------------------------");
		
		System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" +        joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i+1) + "个参数为:" + args[i]);
        }
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());

	}
	
	
	@Pointcut("execution(* test.aspect.TestController.getName(..))")
	public void getName() {
		System.out.println("我是另一个切入点");
	}
	
	@Before("getName()")
	public void getName(JoinPoint joinPoint) {
		System.out.println("------------------------------------");
		System.out.println("我是getName的切入点");
		/*String str = joinPoint.getSignature().toLongString();
		System.out.println(joinPoint.getTarget().toString());
		Object[] obj = joinPoint.getArgs();
		
		System.out.println("obj[] length = " + obj.length);
		if(obj.length > 0) {
			
			System.out.println(obj[1].getClass().getName());
			System.out.println("str is : "+str);
			System.out.println("我是一个前置通知");
		}
		for (int i = 0; i < joinPoint.getArgs().length; i++) {
			System.out.println(joinPoint.getArgs()[i]);
		}
		System.out.println(joinPoint.getSignature().getName());
		
		System.out.println("--------------------------------");
		
		System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" +        joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i+1) + "个参数为:" + args[i]);
        }
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());*/

	}

}
