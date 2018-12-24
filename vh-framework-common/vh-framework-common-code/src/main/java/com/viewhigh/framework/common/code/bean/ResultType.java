/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年5月9日
 * 修改日期：2018年5月9日
 */
package com.viewhigh.framework.common.code.bean;

/**
 * 
 * @Description  json返回结果code
 * @author zhangxm 
 * @version v1.0
 * @since 2018年5月8日
 */
public enum ResultType {

	SUCCESS(1000, "成功"), //
	WRONG(3000, "未知错误"), //
	INVALID_DATA(2000, "数据错误"), //
	;

	private int code;
	private String info;

	private ResultType(int code, String info) {
		this.code = code;
		this.info = info;
	}

	public int getCode() {
		return code;
	}

	public String getInfo() {
		return info;
	}

}
