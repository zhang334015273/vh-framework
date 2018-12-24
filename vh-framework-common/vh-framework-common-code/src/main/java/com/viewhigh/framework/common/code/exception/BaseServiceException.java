/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年5月22日
 * 修改日期：2018年5月22日
 */
package com.viewhigh.framework.common.code.exception;

import com.viewhigh.framework.common.code.bean.ResultType;

/**
 * @Description  平台业务异常 父类
 * @author zhangxm 
 * @version v1.0
 * @since 2018年5月22日
 */
@SuppressWarnings("serial")
public class BaseServiceException extends BaseException{

	public BaseServiceException(ResultType type) {
		super(type);
	}
	
	public BaseServiceException(ResultType type,String desc) {
		super(type,desc);
	}

}
