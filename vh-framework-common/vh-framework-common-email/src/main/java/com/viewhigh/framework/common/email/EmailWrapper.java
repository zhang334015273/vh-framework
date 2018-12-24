/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年7月18日
 * 修改日期：2018年7月18日
 */
package com.viewhigh.framework.common.email;

import java.io.File;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Template;

/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年7月18日
 */
@Component
public class EmailWrapper {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String sender;
	
	/**
	 * 发送简单文本邮件
	 * @param receiver 接受者
	 * @param subject 主题
	 * @param text 内容
	 * @throws Exception
	 */
	public void sendMail(String receiver, String subject, String text) throws Exception{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sender);
		message.setTo(receiver);
		message.setText(text);
		message.setSubject(subject);
		mailSender.send(message);
	}
	
	/**
	 * 群发简单文本邮件
	 * @param receivers
	 * @param subject
	 * @param text
	 * @throws Exception
	 */
	public void sendMail(String[] receivers, String subject, String text) throws Exception{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sender);
		message.setTo(receivers);
		message.setText(text);
		message.setSubject(subject);
		mailSender.send(message);
	}
	
	/**
	 * 发送html邮件
	 * @param receiver
	 * @param subject
	 * @param text
	 * @throws Exception
	 */
	public void sendHtmlMail(String receiver, String subject, String text) throws Exception{
		MimeMessage message = mailSender.createMimeMessage(); 
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(sender);
		helper.setTo(receiver);
		helper.setSubject(subject);
		helper.setText(text, true);
		mailSender.send(message);
	}
	
	/**
	 * 群发html邮件
	 * @param receivers
	 * @param subject
	 * @param text
	 * @throws Exception
	 */
	public void sendHtmlMail(String[] receivers, String subject, String text) throws Exception{
		MimeMessage message = mailSender.createMimeMessage(); 
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(sender);
		helper.setTo(receivers);
		helper.setSubject(subject);
		helper.setText(text, true);
		mailSender.send(message);
	}
	
	/**
	 * 发送 带附件的邮件
	 * @param receiver
	 * @param subject
	 * @param text
	 * @param isHtml
	 * @param files 附件
	 * @throws Exception
	 */
	public void sendAttachmentsMail(String receiver, String subject, String text, String isHtml, File[] files) throws Exception{
		MimeMessage message = mailSender.createMimeMessage(); 
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(sender);
		helper.setTo(receiver);
		helper.setSubject(subject);
		helper.setText(text, isHtml);
		for(File file : files){
			helper.addAttachment(file.getName(), file);
		}
		mailSender.send(message);
	}
	/**
	 * 群发 带附件的邮件
	 * @param receivers
	 * @param subject
	 * @param text
	 * @param isHtml
	 * @param files 附件
	 * @throws Exception
	 */
	public void sendAttachmentsMail(String[] receivers, String subject, String text, String isHtml, File[] files) throws Exception{
		MimeMessage message = mailSender.createMimeMessage(); 
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(sender);
		helper.setTo(receivers);
		helper.setSubject(subject);
		helper.setText(text, isHtml);
		for(File file : files){
			helper.addAttachment(file.getName(), file);
		}
		mailSender.send(message);
	}
	
	/**
	 * 发送模板邮件
	 * @param receiver
	 * @param subject
	 * @param template 模板
	 * @param params 替换参数
	 * @throws Exception
	 */
	public void sendTemplateMail(String receiver, String subject, Template template, Map<String, Object> params) throws Exception {
		MimeMessage message = mailSender.createMimeMessage(); 
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(sender);
		helper.setTo(receiver);
		helper.setSubject(subject);
		helper.setText(FreeMarkerTemplateUtils.processTemplateIntoString(template, params), true);
		mailSender.send(message);
	}
	
	/**
	 * 群发模板邮件
	 * @param receivers
	 * @param subject
	 * @param template 模板
	 * @param params 替换参数
	 * @throws Exception
	 */
	public void sendTemplateMail(String[] receivers, String subject, Template template, Map<String, Object> params) throws Exception {
		MimeMessage message = mailSender.createMimeMessage(); 
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(sender);
		helper.setTo(receivers);
		helper.setSubject(subject);
		helper.setText(FreeMarkerTemplateUtils.processTemplateIntoString(template, params), true);
		mailSender.send(message);
	}

}
