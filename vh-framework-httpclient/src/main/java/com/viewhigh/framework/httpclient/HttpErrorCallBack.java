/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年7月17日
 * 修改日期：2018年7月17日
 */
package com.viewhigh.framework.httpclient;

import org.apache.http.HttpResponse;

/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年7月17日
 */
public interface HttpErrorCallBack {

	public void callback(int statusCode, HttpResponse httpResponse, Exception e);
	
}
