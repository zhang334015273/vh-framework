///**
// * 版权所属：东软望海科技有限公司
// * 作者：张晓明 
// * 版本：V1.0
// * 创建日期：2018年7月18日
// * 修改日期：2018年7月18日
// */
//package com.viewhigh.framework.web.sms;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.viewhigh.framework.common.sms.captcha.CaptchaWrapper;
//
///**
// * @Description  
// * @author zhangxm 
// * @version v1.0
// * @since 2018年7月18日
// */
//@RestController
//@RequestMapping(value = "/captcha")
//public class CaptchaController {
//	
//	@Autowired
//	private CaptchaWrapper captchaWrapper;
//	
//	@RequestMapping("/sms")
//	public String sms(String phone){
//		String code = captchaWrapper.genRandomCode(4);
//		System.out.println(code);
//		String result = captchaWrapper.send(phone, code);
//		System.out.println(result);
//		return "成功";
//	}
//
//}
