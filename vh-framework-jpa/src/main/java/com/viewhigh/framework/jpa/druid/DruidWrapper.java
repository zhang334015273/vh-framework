/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年7月23日
 * 修改日期：2018年7月23日
 */
package com.viewhigh.framework.jpa.druid;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.viewhigh.framework.jpa.config.DruidDbConfig;

/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年7月23日
 */
@Configuration
public class DruidWrapper {
	
	@Autowired
	private DruidDbConfig dbConfig;
	
	@Bean
	@Primary
	public DataSource datasource(){
		DruidDataSource datasource = new DruidDataSource();    
        
        datasource.setUrl(dbConfig.getUrl());    
        datasource.setUsername(dbConfig.getUsername());    
        datasource.setPassword(dbConfig.getPassword());    
        datasource.setDriverClassName(dbConfig.getDriverClassName());    
            
        //configuration    
        datasource.setInitialSize(dbConfig.getInitialSize());    
        datasource.setMinIdle(dbConfig.getMinIdle());    
        datasource.setMaxActive(dbConfig.getMaxActive());    
        datasource.setMaxWait(dbConfig.getMaxWait());    
        datasource.setTimeBetweenEvictionRunsMillis(60000);    
        datasource.setMinEvictableIdleTimeMillis(300000);    
        datasource.setValidationQuery("SELECT 1 FROM DUAL");    
        datasource.setTestWhileIdle(true);    
        datasource.setTestOnBorrow(false);    
        datasource.setTestOnReturn(false);    
        datasource.setPoolPreparedStatements(true);    
        datasource.setMaxPoolPreparedStatementPerConnectionSize(20);    
            
        return datasource;    
	}

}
