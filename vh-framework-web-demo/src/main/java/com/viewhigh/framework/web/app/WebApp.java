/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年7月17日
 * 修改日期：2018年7月17日
 */
package com.viewhigh.framework.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.viewhigh.framework.jpa.dao.BaseDaoFactoryBean;

/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年7月17日
 */
@EnableJpaRepositories(basePackages = {"com.viewhigh"}, repositoryFactoryBeanClass = BaseDaoFactoryBean.class)
@EntityScan(basePackages = {"com.viewhigh.framework"})
@SpringBootApplication(scanBasePackages="com.viewhigh")
public class WebApp {
	
	public static void main(String[] args) throws Exception {
		try {
			SpringApplication.run(WebApp.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
