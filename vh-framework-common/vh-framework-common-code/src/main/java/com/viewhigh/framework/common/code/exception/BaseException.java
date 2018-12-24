/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年5月15日
 * 修改日期：2018年5月15日
 */
package com.viewhigh.framework.common.code.exception;

import com.viewhigh.framework.common.code.bean.JsonResult;
import com.viewhigh.framework.common.code.bean.ResultType;

/**
 * @Description  saas异常父类
 * @author zhangxm 
 * @version v1.0
 * @since 2018年5月15日
 */
public class BaseException extends Exception{

	private static final long serialVersionUID = 779926292834525954L;
	
	//返回前台的信息
	private JsonResult<String> result;
	

	public BaseException(ResultType type) {
		this.result = new JsonResult<String>(type);
	}
	
	/**
	 * 
	 * @param type 错误枚举
	 * @param errStr 自定义错误描述
	 */
	public BaseException(ResultType type,String errStr) {
		this.result = new JsonResult<String>(type);
		this.result.setDesc(errStr);
	}

	/**
	 * @return the result
	 */
	public JsonResult<String> getResult() {
		return result;
	}
	
	

}
