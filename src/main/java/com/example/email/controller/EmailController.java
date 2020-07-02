/**
 * Copyright 2019 SinoSoft. All Rights Reserved.
 */
package com.example.email.controller;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.example.email.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;


/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 */
@RequestMapping("email")
@RestController
public class EmailController{
	
	@Autowired
	SendMailService sendMailService;

	/**
	 *
	 * <B>方法名称：</B>sendEmail<BR>
	 * <B>概要说明：</B>邮件发送<BR>
	 * @param receives  接收用户
	 * @param title  邮件标题
	 * @param content 邮件内容
	 * @return
	 */
	@GetMapping
	public JSONObject sendEmail( String[] receives, String title, String content) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		try {
			boolean flag = sendMailService.sendEmail(receives, title, content);
			if(flag){
			   json.put("msg", "发送成功！");	
			   json.put("code", "0");
			}else{
			   json.put("msg", "发送失败！");	
			   json.put("code", "-1");
			}
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

}
