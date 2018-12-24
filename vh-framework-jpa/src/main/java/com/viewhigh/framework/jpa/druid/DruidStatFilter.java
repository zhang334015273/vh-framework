/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年5月15日
 * 修改日期：2018年5月15日
 */
package com.viewhigh.framework.jpa.druid;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年5月15日
 */
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",    
initParams={    
    @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源    
}    
) 
public class DruidStatFilter extends WebStatFilter{

}
