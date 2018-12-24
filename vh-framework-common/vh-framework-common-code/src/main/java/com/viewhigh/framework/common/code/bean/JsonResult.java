/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年5月9日
 * 修改日期：2018年5月9日
 */
package com.viewhigh.framework.common.code.bean;

import java.io.Serializable;
/**
 * 
 * @Description  接口返回的统一格式
 * @author zhangxm 
 * @version v1.0
 * @since 2018年5月9日
 */
public class JsonResult<T> implements Serializable {

	private static final long serialVersionUID = 1717488731978480120L;
	private int resultCode;
	private T data;
	private String desc;
	private Page page;

	public JsonResult() {

	}
	
	public JsonResult(ResultType resultType) {
		this.resultCode = resultType.getCode();
		this.desc = resultType.getInfo();
	}

	public JsonResult(T data) {
		this.data = data;
	}

	public int getResultCode() {
		return resultCode;
	}

	public JsonResult<T> setResultCode(int resultCode) {
		this.resultCode = resultCode;
		return this;
	}

	public JsonResult<T> setCode(ResultType resultType) {
		this.resultCode = resultType.getCode();
		this.desc = resultType.getInfo();
		return this;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}
	
	

}
