/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年7月18日
 * 修改日期：2018年7月18日
 */
package com.viewhigh.framework.common.sms.captcha;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description  发送验证码 配置bean
 * @author zhangxm 
 * @version v1.0
 * @since 2018年7月18日
 */
@Component
@ConfigurationProperties(prefix = "viewhigh.sms.captcha")
public class CaptchaConfig {
	
	private String url = "http://218.245.7.7:58423/mcapi/sendsmsmsg";
	
	private String appid = "test";
	
	
	private String customerId = "1";

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the appid
	 */
	public String getAppid() {
		return appid;
	}

	/**
	 * @param appid the appid to set
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}


	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
