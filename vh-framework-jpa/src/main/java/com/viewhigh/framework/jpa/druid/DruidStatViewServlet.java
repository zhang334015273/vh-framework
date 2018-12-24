/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年5月15日
 * 修改日期：2018年5月15日
 */
package com.viewhigh.framework.jpa.druid;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年5月15日
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns="/druid/*",    
initParams={    
     @WebInitParam(name="allow",value=""),// IP白名单(没有配置或者为空，则允许所有访问)    
     @WebInitParam(name="deny",value=""),// IP黑名单 (存在共同时，deny优先于allow)    
     @WebInitParam(name="loginUsername",value="root"),// 用户名    
     @WebInitParam(name="loginPassword",value="123456"),// 密码    
     @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能    
}) 
public class DruidStatViewServlet extends StatViewServlet{

}
