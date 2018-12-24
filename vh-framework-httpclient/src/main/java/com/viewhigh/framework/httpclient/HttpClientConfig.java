/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年7月17日
 * 修改日期：2018年7月17日
 */
package com.viewhigh.framework.httpclient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年7月17日
 */
@Component
@ConfigurationProperties( prefix = "viewhigh.httpclient")
public class HttpClientConfig {
	
	//设置连接超时时间
	private Integer connectTimeout = 60 * 1000;
	
	//设置请求超时时间
	private Integer connectRequestTimeout = 5 * 60 * 1000;
	
	//设置重定向
	private Boolean redirectEnabled = false;

	/**
	 * @return the connectTimeout
	 */
	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	/**
	 * @param connectTimeout the connectTimeout to set
	 */
	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/**
	 * @return the connectRequestTimeout
	 */
	public Integer getConnectRequestTimeout() {
		return connectRequestTimeout;
	}

	/**
	 * @param connectRequestTimeout the connectRequestTimeout to set
	 */
	public void setConnectRequestTimeout(Integer connectRequestTimeout) {
		this.connectRequestTimeout = connectRequestTimeout;
	}

	/**
	 * @return the redirectEnabled
	 */
	public Boolean getRedirectEnabled() {
		return redirectEnabled;
	}

	/**
	 * @param redirectEnabled the redirectEnabled to set
	 */
	public void setRedirectEnabled(Boolean redirectEnabled) {
		this.redirectEnabled = redirectEnabled;
	} 

	
	
}
