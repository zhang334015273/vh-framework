/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年5月15日
 * 修改日期：2018年5月15日
 */
package com.viewhigh.framework.jpa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年5月15日
 */
@Component
@ConfigurationProperties(prefix = "viewhigh.datasource")
public class DruidDbConfig {
	
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	//连接池最大连接数
	private int maxActive;
	//初始化连接数
	private int initialSize;
	//获取连接的最大等待时长
	private int maxWait;
	//最小连接数
	private int minIdle;
	
	/**
	 * @return the driverClassName
	 */
	public String getDriverClassName() {
		return driverClassName;
	}
	/**
	 * @param driverClassName the driverClassName to set
	 */
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
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
	 * @return the maxActive
	 */
	public int getMaxActive() {
		return maxActive;
	}
	/**
	 * @param maxActive the maxActive to set
	 */
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}
	/**
	 * @return the initialSize
	 */
	public int getInitialSize() {
		return initialSize;
	}
	/**
	 * @param initialSize the initialSize to set
	 */
	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}
	/**
	 * @return the maxWait
	 */
	public int getMaxWait() {
		return maxWait;
	}
	/**
	 * @param maxWait the maxWait to set
	 */
	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}
	/**
	 * @return the minIdle
	 */
	public int getMinIdle() {
		return minIdle;
	}
	/**
	 * @param minIdle the minIdle to set
	 */
	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}
	
	

}
