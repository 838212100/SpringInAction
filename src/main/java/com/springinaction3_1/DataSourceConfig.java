package com.springinaction3_1;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;

/**
 * 将两个配置bean放至一个配置类中
 * 每个DataSource bean都被声明在一个profile中,并且只有当规定的profile激活时,相应的bean才会被创建
 * 其他bean如没有声明在一个给定的profile范围内,那么该bean始终都会被创建,与激活那个profile没有关系
 * @author YR
 *
 */
@Configuration
public class DataSourceConfig {
	
	@Bean(destroyMethod="shutdown")
	@Profile("dev")//为dev profile装配的 bean
	public DataSource embeddedDataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:schema.sql")
				.addScript("classpath:test-data.sql")
				.build();
	}
	
	@Bean
	@Profile("prod")//为prod profile装配的 bean
	public DataSource jndiDataSource() {
		JndiObjectFactoryBean jndiObjectFactoryBean = 
				new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("jdbc/myDS");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
		return (DataSource) jndiObjectFactoryBean.getObject();
	}

}
