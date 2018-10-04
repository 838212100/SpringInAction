package com.springinaction3_5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/com/soundsystem/app.properties")
public class ExpressiveConfig {
	
	@Autowired 
	private Environment env;
	
	@Bean
	public BlankDisc disc() {
		return new BlankDisc(
				env.getProperty("disc.title"),
				//env.getProperty("disc.title","默认值"), 当值不存在的时候 放入一个默认值
				env.getProperty("disc.artist"));
	}
	
	/*
	 * properties文件里属性为
	 * disc.title=get
	 * disc.artist=set
	 * 
	 * getProperty()方法并不是获取属性的唯一的方法,一共4个重载方法
	 * 我们从文件中获取的是一个String类型的值,而该值所代表的含义是连接池中所维持的连接数量,那么就需要将其转换为Integer类型
	 * getProperty("disc.connection.count",Integer.class,30);
	 * 
	 * SpEL表达式
	 * public BlankDisc disc(@Value("#{systemProperties['disc.title']}") String title){}
	 *  
	 */
	
	public static void main(String[] args) {
		//T(java.lang.Math).random();
	}

}
