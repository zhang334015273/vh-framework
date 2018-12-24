/**
 * 版权所属：东软望海科技有限公司
 * 作者：张银宝 
 * 版本：V1.0
 * 创建日期：2018年8月30日
 * 修改日期：2018年8月30日
 */
package com.viewhigh.framework.common.sms.captcha;

/**
 * @Description  
 * @author zhangyinbao
 * @version v1.0
 * @since 2018年8月30日
 */
public enum SmsTemplateType {
	
	TEMPLATE1(1,"91554513"),    //模板一 发送给运维人员的应用系统异常信息的短信模板
	TEMPLATE2(2,"91554514"),	//模板二 发送给运维人员的应用系统回复正常的短信模板
	TEMPLATE3(3,"91554515"),	//模板三 发送给租户的应用系统异常信息的短信模板
	TEMPLATE4(4,"91554516"),	//模板四 发送给租户的应用系统恢复正常的短信模板
	TEMPLATE5(5,"91554517"),	//模板五 工单
	TEMPLATE6(6,"91554518"),	//模板六 短信验证码
	TEMPLATE7(7,"91554533");	//模板七 发送给租户的应用系统即将到期的短信模板
	
	
	private int code;
	private String info;
	
	private SmsTemplateType(int code , String info){
		this.code=code;
		this.info=info;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
