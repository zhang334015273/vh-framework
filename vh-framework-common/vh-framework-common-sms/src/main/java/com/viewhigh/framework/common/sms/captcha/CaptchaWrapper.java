/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年7月18日
 * 修改日期：2018年7月18日
 */
package com.viewhigh.framework.common.sms.captcha;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSONObject;
import com.viewhigh.framework.httpclient.HttpClientWrapper;

/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年7月18日
 */
@Configuration
public class CaptchaWrapper {
	
	@Autowired
	private CaptchaConfig captchaConfig;
	
	@Autowired
	private HttpClientWrapper httpClientWrapper;
	/**
	 * 
	 * @param phone  手机号  多个逗号隔开
	 * @param randomCode  短信模板参数,多个 逗号隔开
	 * @param smsTemplateType   模板id枚举
	 * @return
	 */
	public String send(String phone, String randomCode,SmsTemplateType smsTemplateType ){
		JSONObject json = new JSONObject();
		json.put("appid", captchaConfig.getAppid());
		json.put("phonenumbers", phone);
		json.put("template_id", smsTemplateType.getInfo());
		json.put("template_params", randomCode);
		json.put("customer_id", captchaConfig.getCustomerId());
		return httpClientWrapper.doPostJson(captchaConfig.getUrl(), json.toJSONString());
	}
	
	public String send(Map<String, String> map){
		JSONObject json = new JSONObject();
		json.put("appid", captchaConfig.getAppid());
		for(Entry<String, String> entry : map.entrySet()){
			json.put(entry.getKey(), entry.getValue());
		}
		return httpClientWrapper.doPostJson(captchaConfig.getUrl(), json.toJSONString());
	}
	
	public String genRandomCode(int length){
		String captcha = "";
        for (int i = 0; i < length; i++) {
        	captcha += String.valueOf(randomInt());
        }
        return captcha;
	}
	
	/**
	 * 生成一个0-9的随机数
	 * @param from
	 * @param to
	 * @return
	 */
	private int randomInt() {
        Random r = new Random();
        return r.nextInt(10);
    }

}
