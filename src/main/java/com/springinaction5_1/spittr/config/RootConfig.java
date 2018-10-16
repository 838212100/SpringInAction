package com.springinaction5_1.spittr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages= {"com.springinaction5_1.spittr"}, 
	excludeFilters= {@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)})
public class RootConfig {

}
