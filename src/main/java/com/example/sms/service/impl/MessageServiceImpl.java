/**
 * Copyright 2019 SinoSoft. All Rights Reserved.
 */
package com.example.sms.service.impl;

import java.util.Date;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sms.dao.MessageMapper;
import com.example.sms.domain.Message;
import com.example.sms.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

	@Autowired
	MessageMapper messageMapper;
	
	/**
	 * <B>方法名称：</B>insertSms<BR>
	 * <B>概要说明：</B>短信信息保存<BR>
	 */
	@Override
	public void insertSms(Message message) {
		// TODO Auto-generated method stub	
		message.setCreateTime(new Date());
		messageMapper.insert(message);		
	}

	/**
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：</B><BR>
	 * @see MessageService#getByPhone(String)
	 */
	@Override
	public Message getByPhone(String phone) {
		// TODO Auto-generated method stub
		Message msg =  messageMapper.getByPhone(phone);
		return msg;
	}



}
