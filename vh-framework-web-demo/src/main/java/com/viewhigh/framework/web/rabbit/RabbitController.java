/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年7月24日
 * 修改日期：2018年7月24日
 */
package com.viewhigh.framework.web.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viewhigh.framework.rabbit.RabbitSender;

/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年7月24日
 */
@RestController
@RequestMapping(value = "rabbit")
public class RabbitController {
	
	@Autowired
	RabbitSender sender;

	@RequestMapping(value = "message")
	public String message(){
		sender.send("hello", "work");
		return "成功";
	}
	
}
