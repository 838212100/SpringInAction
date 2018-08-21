package com.springinaction3_1;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * 嵌入式数据DataSource配置
 * @author YR
 *
 */
@Configuration
@Profile("dev")//告诉Spring这个配置类中的bean只有在dev profile激活时才会创建,如果dev profile没有激活 那么带有@Bean注解的方法都会被忽略掉
public class DevelopmentProfileConfig {
	
	@Bean(destroyMethod="shutdown")
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:schema.sql")
				.addScript("classpath:test-data.sql")
				.build();
	}

}
