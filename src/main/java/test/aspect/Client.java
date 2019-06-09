package test.aspect;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Client {
	public static void main(String[] args) {
		
		
		ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:knights.xml");
		
		
		TestController testController = (TestController)factory.getBean("testController");
		
		testController.getList("name", 1, new User());
		testController.getName("name", 1, new User());
		testController.getValues("name", 1, new User());
		
	}
	public static HttpServletRequest getHttpServletRequest(){  
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder  
                .getRequestAttributes())  
                .getRequest();  
        return request;  
	} 
}
