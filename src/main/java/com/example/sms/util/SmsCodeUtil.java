package com.example.sms.util;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;


import com.example.sms.constant.MessageConstant;
import lombok.extern.slf4j.Slf4j;



/**
 * 
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B>短信<BR>
 */
@Slf4j
public class SmsCodeUtil {
	  
    /* 此处需要替换成开发者自己的accessKeyId和accessKeySecret(在阿里云访问控制台寻找) */
    /**
     * 自己注册生成的
     */
    private static final String ACCESS_KEY_ID = "LTAIcd6y20YnsKBG"; 
    /**
     * 自己注册生成的
     */
    private static final String ACCESS_KEY_SECRET = "lfSFOfcQJeMO5vbKsNLvlW4P9JRHM8";
   /**
    * 
    * <B>方法名称：</B>sendSmCode <BR>
    * <B>概要说明：</B>短信验证码发送 <BR>
    * @param phone
    * @return
    * @throws ClientException
    */
    public static Map<String, String> sendSmCode(String phone) throws ClientException {
    	Map<String, String>  map = new HashMap<>(4);
        /* 超时时间，可自主调整 */
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        /* 初始化acsClient,暂不支持region化 */
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", MessageConstant.PRODUCT, MessageConstant.DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        /* 组装请求对象-具体描述见控制台-文档部分内容 */
        SendSmsRequest request = new SendSmsRequest();
        /* 必填:待发送手机号 */
        request.setPhoneNumbers(phone);
        /* 必填:短信签名-可在短信控制台中找到 */
        //TODO: 这里是你短信签名  
        request.setSignName("纠纷平台"); 
        /* 必填:短信模板code-可在短信控制台中找到 */
        //TODO: 这里是你的模板code     
        request.setTemplateCode("SMS_168730397"); 
        /* 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为 */
        String code = getMsgCode();
        request.setTemplateParam("{\"code\":\"" + code+ "\"}");
        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        log.info("短信消息："+sendSmsResponse.toString());
        if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals(MessageConstant.OK)){
           log.info("短信发送成功！");
           log.info("你的验证码为："+code);
           log.info("Code=" + sendSmsResponse.getCode());
           log.info("Message=" + sendSmsResponse.getMessage());
           map.put("code", code);
        }else {       	
        	log.info("短信发送失败！");
        	log.info("ErrorCode=" + sendSmsResponse.getCode());
        	log.info("ErrorMessage=" + sendSmsResponse.getMessage());
        }
        return map;
        
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
    
   public static void main(String[] args) throws Exception {
	     //18326893152
	     //13546002751
		 String phone ="13546002751"; 
		 sendSmCode(phone);
		//sendSmInform(phone);
   }


}
