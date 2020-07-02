/**
 * Copyright 2019 SinoSoft. All Rights Reserved.
 */
package com.example.sms.service;


import com.example.sms.domain.Message;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 */
public interface MessageService {

	
	
	/**
	 * 
	 * <B>方法名称：</B>insertSms<BR>
	 * <B>概要说明：</B>保存短信信息<BR>
	 * @param message
	 */
	void insertSms(Message message);
	
	/**
	 * 
	 * <B>方法名称：</B>getByPhone<BR>
	 * <B>概要说明：</B>根据手机号码获取最新时间的验证码信息<BR>
	 * @param phone
	 * @return
	 */
	Message  getByPhone(String phone);
	
}
