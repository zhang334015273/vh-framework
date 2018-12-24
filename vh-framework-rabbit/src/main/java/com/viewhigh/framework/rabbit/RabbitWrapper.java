/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年7月23日
 * 修改日期：2018年7月23日
 */
package com.viewhigh.framework.rabbit;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @Description  rabbit 封装类
 * @author zhangxm 
 * @version v1.0
 * @since 2018年7月23日
 */
@Configuration
public class RabbitWrapper {
	
	@Autowired
	private RabbitConfig rabbitConfig;
	
	@Bean
	public CachingConnectionFactory initCachingConnectionFactory(){
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitConfig.getHost());
        connectionFactory.setUsername(rabbitConfig.getUsername());
        connectionFactory.setPassword(rabbitConfig.getPassword());
        connectionFactory.setPort(rabbitConfig.getPort());
        connectionFactory.setVirtualHost(rabbitConfig.getVirtualHost());
        return connectionFactory;
	}
	
	@Bean
	@Primary
	public RabbitTemplate initTemplate(){
		return new RabbitTemplate(initCachingConnectionFactory());
	}
	
	
}
