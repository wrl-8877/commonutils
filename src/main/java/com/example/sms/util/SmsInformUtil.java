/**
 * Copyright 2019 SinoSoft. All Rights Reserved.
 */
package com.example.sms.util;

import java.util.HashMap;
import java.util.Map;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;


import com.example.sms.constant.MessageConstant;
import com.example.sms.domain.Message;
import lombok.extern.slf4j.Slf4j;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 */
@Slf4j
public class SmsInformUtil {
	
	
	/**
	 * 
	 * <B>方法名称：</B>sendSmInform<BR>
	 * <B>概要说明：</B>短信通知<BR>
	 * @param message
	 * @return
	 * @throws ClientException
	 */
    public static Map<String,String> sendSmInform(Message message) throws ClientException {
    	Map<String,String> map = new HashMap<>(4);
        /* 超时时间，可自主调整 */
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        /* 初始化acsClient,暂不支持region化 */
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", MessageConstant.ACCESSKEYID, MessageConstant.ACCESSKEYSECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", MessageConstant.PRODUCT, MessageConstant.DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);
       
        /* 组装请求对象-具体描述见控制台-文档部分内容 */
        SendSmsRequest request = new SendSmsRequest();
        /* 必填:待发送手机号 */
        request.setPhoneNumbers(message.getPhone());
        /* 必填:短信签名-可在短信控制台中找到 */
        //你短信签名  
        request.setSignName(MessageConstant.SIGNNAMEINFO); 
        /* 必填:短信模板code-可在短信控制台中找到 */
        //短信模板code     
        request.setTemplateCode(MessageConstant.TEMPLATECODEINFO);         
        //通知短信类型：您有新的订单待处理，当前状态：${status}，订单摘要:${remark}，请及时处理。
        //request.setTemplateParam("{\"status\":\"未处理\", \"remark\":\"123\"}");
        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        
        if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals(MessageConstant.OK)){
           log.info("短信发送成功！");
           log.info("Code=" + sendSmsResponse.getCode());
           log.info("Message=" + sendSmsResponse.getMessage());
           map.put("content", "模板内容");
          
        }else {
        	log.info("短信发送失败！");
        	log.info("ErrorCode=" + sendSmsResponse.getCode());
        	log.info("ErrorMessage=" + sendSmsResponse.getMessage());
        }
        return map;
        
    }
}
