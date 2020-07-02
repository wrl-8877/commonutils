/**
 * Copyright 2019 SinoSoft. All Rights Reserved.
 */
package com.example.email.util;

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
import javax.net.ssl.SSLSocketFactory;


import com.example.email.constant.EmailConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B>qq邮箱邮件发送<BR>
 */
@Slf4j
public class SendMailUtil {
	
	   
    /**
     * 
     * <B>方法名称：sendMail</B><BR>
     * <B>概要说明：邮件发送</B><BR>
     * 
     * @param to 收件邮箱号
     * @param title 邮件标题
     * @param content 邮件内容
     * @return Boolean 发送成功返回true 失败返回false
     * @throws AddressException
     * @throws MessagingException
     */
    public static Boolean sendMail(String to, String title, String content) throws AddressException,
            MessagingException {
    	
    	Properties properties = System.getProperties();
    	properties.setProperty("mail.smtp.host", EmailConstant.SMTP_HOST);
    	properties.setProperty("mail.smtp.port", EmailConstant.SMTP_PORT);
    	properties.setProperty("mail.smtp.socketFactory.port", EmailConstant.SMTP_PORT);
    	properties.setProperty("mail.smtp.auth", "true");
    	properties.setProperty("mail.smtp.socketFactory.class", "SSL_FACTORY");
    	/**
    	 * Could not connect to SMTP host: smtp.163.com, port: 465, response: -1
    	 */
    	properties.put("mail.smtp.socketFactory.class", SSLSocketFactory.class);
    	//这里填上你的邮箱（发送方）  
    	/*
    	 * 用来解决【•554 DT:SPM 发送的邮件内容包含了未被许可的信息，或被系统识别为垃圾邮件。请检查是否有用户发送病毒或者垃圾邮件；】问题
    	 */
    	properties.put("userName",EmailConstant.MY_EMAIL_ACCOUNT); 
        // 获取默认session对象 用户名密码读取配置
        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    @Override
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                        		// 发件人邮件用户名、密码
                        		EmailConstant.EMAIL_ACCOUNT, EmailConstant.EMAIL_PASSWORD);
                    }
                });

        try {
        	
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(EmailConstant.EMAIL_ACCOUNT));           
            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //根据发送人的账号和接收人的账号进行是否抄送判断
     		if (EmailConstant.MY_EMAIL_ACCOUNT.contains(EmailConstant.WANGYIYUN)) {
     			if (to.contains(EmailConstant.QQ)) {
     				 /*
     	             * 抄送(163邮箱发送qq邮箱遇到的问题)
     	        	 * 用来解决【•554 DT:SPM 发送的邮件内容包含了未被许可的信息，或被系统识别为垃圾邮件。
     	        	 * 请检查是否有用户发送病毒或者垃圾邮件；】问题
     	        	 */
     	             message.addRecipients(MimeMessage.RecipientType.CC, 
     	               	InternetAddress.parse(properties.getProperty("userName")));
     	 		}
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
            log.error(mex.getMessage());
            return false;
        }
    }

    /**
     * 
     * <B>方法名称：sendCode</B><BR>
     * <B>概要说明：邮箱发送验证码</B><BR>
     * 
     * @param email 接收邮箱号
     * @return 验证码
     * @throws javax.mail.MessagingException
     * @throws AddressException
     */
    public static String sendCode(String email) {
        String code = getMsgCode();
        String content = "您的验证码为：" + code;
        String title = "验证码";
        try {
            if (sendMail(email, title, content)) {
            	log.info("接收验证码成功！！！");
                return code;
            }
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
        return "";
    }    
    /**
     * 
     * <B>方法名称：</B>getMsgCode<BR>
     * <B>概要说明：</B>验证码<BR>
     * @return
     */
     private static String getMsgCode() {
         int n = 6;
         StringBuilder code = new StringBuilder();
         Random ran = new Random();
         for (int i = 0; i < n; i++) {
             code.append(Integer.valueOf(ran.nextInt(10)).toString());
         }
         return code.toString();
     }
 
    public static void main(String[] args) {
    	//923596883@qq.com,Fwangruilin@163.com，lin923596883@163.com,403594380@qq.com
		String email = "18326893152@sina.cn";  
		
		//sendCode(email);
    	String title="纠纷平台";
    	String content ="尊敬的客户，你好！欢迎登录金融纠纷平台，谢谢你的体验！";
    	try {
			sendMail(email,title,content);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
