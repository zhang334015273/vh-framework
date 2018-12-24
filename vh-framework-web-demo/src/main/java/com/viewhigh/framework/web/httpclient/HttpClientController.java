/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年7月17日
 * 修改日期：2018年7月17日
 */
package com.viewhigh.framework.web.httpclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viewhigh.framework.httpclient.HttpClientWrapper;

/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年7月17日
 */
@RestController
@RequestMapping(value = "/httpclient")
public class HttpClientController {
	
	@Autowired
	private HttpClientWrapper httpClientWrapper;
	
	@RequestMapping(value = "/get")
	public String doGet(){
		String url = "http://www.baidu.com/";
		String result = httpClientWrapper.doGet(url);
		System.out.println(result);
		return "成功";
	}

}
