package com.springinaction4_1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy	//启动AspectJ自动代理
@ComponentScan
public class ConcertConfig {
	
	@Bean
	public Audience audience() {
		return new Audience();
	}

}
