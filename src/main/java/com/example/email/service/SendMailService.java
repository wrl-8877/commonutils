/**
 * Copyright 2019 SinoSoft. All Rights Reserved.
 */
package com.example.email.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B>邮件发送<BR>
 */
public interface SendMailService {

	/**
	 * 
	 * <B>方法名称：</B>sendEmail<BR>
	 * <B>概要说明：</B>发送邮件<BR>
	 * @param list
	 * @param title
	 * @param content
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 */
	boolean  sendEmail(String[] list ,String title,String content)throws AddressException,MessagingException;
    
	/**
	 * 
	 * <B>方法名称：</B>sendCode<BR>
	 * <B>概要说明：</B>获取邮件验证码<BR>
	 * @param email
	 * @return
	 */
	String sendCode(String[] email);
}
