/**
 * Copyright 2019 SinoSoft. All Rights Reserved.
 */
package com.example.email.service.impl;

import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.example.email.service.SendMailService;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B>邮件发送<BR>
 */
@Slf4j
@Service
public class SendMailServiceImpl implements SendMailService {
	
	private static String account ="EMAIL_ACCOUNT";
	private static String password ="EMAIL_PASSWORD";
	private static String host ="SMTP_HOST";
	private static String port ="SMTP_PORT";
	/**
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：</B><BR>
	 * @see SendMailService#sendEmail(String[], String, String)
	 */
	@Override
	public boolean sendEmail(String[] list, String title, String content) 
			throws AddressException, MessagingException {
		String emailAccount = "";
		String emailPassword = "";
		String smtpHost = "";
		String smtpPort = "";

		Properties properties = System.getProperties();
    	properties.setProperty("mail.smtp.host", smtpHost);
    	properties.setProperty("mail.smtp.port", smtpPort);
    	properties.setProperty("mail.smtp.socketFactory.port", smtpPort);
    	properties.setProperty("mail.smtp.auth", "true");
    	properties.setProperty("mail.smtp.socketFactory.class", "SSL_FACTORY");
    	/**
    	 * Could not connect to SMTP host: smtp.163.com, port: 465, response: -1
    	 */
    	properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // 获取默认session对象 用户名密码读取配置
    	String count = emailAccount;
    	String password = emailPassword;
    	log.info("count:"+count);
    	log.info("password:"+password);
    	log.info("smtpHost:"+smtpHost);
    	log.info("smtpPort:"+smtpPort);
        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    @Override
                    public PasswordAuthentication getPasswordAuthentication() {
                    	// 发件人邮件用户名、密码
                        return new PasswordAuthentication(count,password);                       		
                    }
                });

        try {       	
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(emailAccount));
            for(int i= 0;i<list.length;i++){           	
            	// Set To: 头部头字段
            	 message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                         list[i]));	
            }
            // Set Subject: 头部头字段
            message.setSubject(title);
            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(content, "text/html; charset=utf-8");   
            //进行文本内容保存
            multipart.addBodyPart(contentPart);          
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();
            // 发送消息
            Transport.send(message);
            log.info("发送邮件成功!!!");
            return true;
        }
        catch (MessagingException mex) {
            log.error("Message:"+mex);
            return false;
        }
	}
	
	/**
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：</B><BR>
	 * @see SendMailService#sendCode(String[])
	 */
	@Override
	public String sendCode(String[] email) {
		// TODO Auto-generated method stub
		String code = getMsgCode();
        String content = "您的验证码为：" + code;
        String title = "验证码";
        try {
            if (sendEmail(email, title, content)) {
            	log.info("接收验证码成功！！！");
                return "发送成功！";
            }
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return "发送失败！";
        }
        return "发送失败！";
	}
	
	
    /**
     * 
     * <B>方法名称：</B>getMsgCode<BR>
     * <B>概要说明：</B>验证码<BR>
     * @return
     */
     public String getMsgCode() {
         int n = 6;
         StringBuilder code = new StringBuilder();
         Random ran = new Random();
         for (int i = 0; i < n; i++) {
             code.append(Integer.valueOf(ran.nextInt(10)).toString());
         }
         return code.toString();
     }

}
