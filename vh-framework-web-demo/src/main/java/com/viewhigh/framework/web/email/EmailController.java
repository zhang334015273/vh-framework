/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年7月18日
 * 修改日期：2018年7月18日
 */
package com.viewhigh.framework.web.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viewhigh.framework.common.email.EmailWrapper;

/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年7月18日
 */
@RestController
@RequestMapping(value = "/email")
public class EmailController {
	
	@Autowired
	private EmailWrapper emailWrapper;
	
	@RequestMapping(value = "/send")
	public String sendEmail() throws Exception{
		emailWrapper.sendMail("zxmlh_334015273@qq.com", "测试邮件", "这是一封测试邮件");
		return "成功";
	}

}
