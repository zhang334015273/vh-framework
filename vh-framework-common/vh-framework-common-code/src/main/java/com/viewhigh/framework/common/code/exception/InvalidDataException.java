/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年5月10日
 * 修改日期：2018年5月10日
 */
package com.viewhigh.framework.common.code.exception;

import com.viewhigh.framework.common.code.bean.ResultType;

/**
 * @Description 数据校验异常 
 * @author zhangxm 
 * @version v1.0
 * @since 2018年5月10日
 */
public class InvalidDataException extends BaseServiceException{

	private static final long serialVersionUID = -2890067561004863151L;
	
	public InvalidDataException(String string) {
		super(ResultType.INVALID_DATA,string);
	}


}
