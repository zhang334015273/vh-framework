/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年7月23日
 * 修改日期：2018年7月23日
 */
package com.viewhigh.framework.rabbit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description  rabbit 配置类
 * @author zhangxm 
 * @version v1.0
 * @since 2018年7月23日
 */
@Component
@ConfigurationProperties(prefix = "viewhigh.rabbitmq")
public class RabbitConfig {
	
	private String host;

    private Integer port;

    private String username;

    private String password;
    
    private String virtualHost;

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the virtualHost
	 */
	public String getVirtualHost() {
		return virtualHost;
	}

	/**
	 * @param virtualHost the virtualHost to set
	 */
	public void setVirtualHost(String virtualHost) {
		this.virtualHost = virtualHost;
	}
    
}
