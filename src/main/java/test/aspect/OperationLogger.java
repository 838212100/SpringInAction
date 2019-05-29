package test.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OperationLogger {

	// 首先是被调用的方法的名称，其默认值是“”
	String modelName() default "";

	// 接下了一个就是当前使用这个方法的用户是谁
	String user();
//
//	// 之后就是这个用户所做的是什么操作
//	String option();

}
