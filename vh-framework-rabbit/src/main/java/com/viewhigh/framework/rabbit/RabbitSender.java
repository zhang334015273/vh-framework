/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年7月23日
 * 修改日期：2018年7月23日
 */
package com.viewhigh.framework.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @Description  rabbit 发送类   Exchange 业务系统自定义
 * @author zhangxm 
 * @version v1.0
 * @since 2018年7月23日
 */
@Configuration
public class RabbitSender {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/**
	 * 向topic 发送message
	 * @param topic
	 * @param message
	 */
	public void send(String topic,Message message){
		rabbitTemplate.send(topic, message);
	}
	
	/**
	 * 向topic 下发送自定义对象
	 * @param topic
	 * @param message
	 */
	public void send(String topic, Object message){
		rabbitTemplate.convertAndSend(topic, message);
	}
	
	
	/**
	 * exchange向所有匹配topic 下发送自定义对象 （前提需要业务做bind 操作）
	 * @param topic
	 * @param message
	 */
	public void send(String exchange,String topic, Object message){
		rabbitTemplate.convertAndSend(exchange, topic, message);
	}
	
	/**
	 * exchange向所有匹配topic 下发送自定义对象 （前提需要业务做bind 操作）
	 * @param topic
	 * @param message
	 */
	public void send(String exchange,String topic, Message message){
		rabbitTemplate.send(exchange, topic, message);
	}

}
