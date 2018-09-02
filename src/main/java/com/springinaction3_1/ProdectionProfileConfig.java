package com.springinaction3_1;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jndi.JndiObjectFactoryBean;

/**
 * 适用于生产环境的配置
 * @author YR
 *
 */
@Configuration
@Profile("prod")
public class ProdectionProfileConfig {
	
	@Bean
	public DataSource dataSource() {
		JndiObjectFactoryBean jndiObjectFactoryBean = 
				new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("jdbc/myDS");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
		return (DataSource) jndiObjectFactoryBean.getObject();
	}
	
	@Bean
	@Conditional(MacgicExistsCondition.class)
	public MagicBean magicBean() {
		return new MagicBean();
	}

}
