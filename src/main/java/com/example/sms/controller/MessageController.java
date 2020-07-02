/**
 * Copyright 2019 SinoSoft. All Rights Reserved.
 */
package com.example.sms.controller;



import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.extension.api.R;
//import com.example.config.RedisCache;
import com.example.sms.constant.MessageConstant;
import com.example.sms.domain.Message;
import com.example.sms.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;


import lombok.extern.slf4j.Slf4j;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 */
@Slf4j
@RestController
@RequestMapping("/sms")
public class MessageController{

	@Autowired
	MessageService messageService;

//	@Autowired
//	RedisCache redisCache;

	/**
	 *
	 * <B>方法名称：</B>send_register_message<BR>
	 * <B>概要说明：</B>短信验证码发送<BR>
	 * @param req
	 * @param phone
	 * @return
	 */
	@RequestMapping(value={ "/code" },method= RequestMethod.POST)
	@ResponseBody
	public JSONObject sendMessage(String phone,HttpServletRequest req) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
        log.info("infoMsg:--- 用户注册短信验证码发送开始");
        try {
            //设置超时时间-可自行调整
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");        
            //初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", MessageConstant.ACCESSKEYID,
            		MessageConstant.ACCESSKEYSECRET);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", MessageConstant.PRODUCT, MessageConstant.DOMAIN);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //获取验证码
            String code = getMsgCode();
            //组装请求对象
	        SendSmsRequest request = new SendSmsRequest();
	        //使用post提交
	        request.setMethod(MethodType.POST);
	        //必填:待发送手机号。
	        request.setPhoneNumbers(phone);
	        //短信签名
	        request.setSignName(MessageConstant.SIGNNAME);
	        //短信模板
	        request.setTemplateCode(MessageConstant.TEMPLATECODE);
	        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
	        //request.setTemplateParam("{\"name\":"+name+",\"code\":"+code+"}");
	        request.setTemplateParam("{\"code\":\""+code+"\"}");	 	       
	        //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals(MessageConstant.OK)) {
                //请求成功 
                json.put("code", "0");
                json.put("msg", "验证码发送成功,请及时接收！");
                //保存短信信息 
                Message mes = new Message();
                mes.setCode(String.valueOf(code));
                mes.setPhone(phone);
                mes.setType(MessageConstant.CODE);
                messageService.insertSms(mes);
                //存储redis
				//redisCache.setCacheObject(phone,code);
                //存库任务结束  
                log.info("infoMsg:--- 用户注册短信验证码发送结束");  
             }else{
             	json.put("code", "-1");
             	json.put("msg", sendSmsResponse.getMessage());
             }
        } catch (Exception e) {
        	json.put("code", "-1");
       	    json.put("msg", "验证码发送失败,请重试！");      	    
            log.error("send msgcode error :{}" + e.getMessage(),e);              
        }
        return json;
	}
	
	/**
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：</B><BR>
	 */
	@GetMapping("/get/{phone}")
	public R getDetail(@PathVariable("phone")  String phone) {
		// TODO Auto-generated method stub		
		try {
	//		Message message = messageService.getByPhone(phone);
	//		String code = redisCache.getCacheObject(phone);
			return R.ok(null);
		} catch (Exception e) {
			// TODO: handle exception
			return R.failed("查询失败！");
		}
		
	}
	
	 /**
	  * 
	  * <B>方法名称：</B>getMsgCode<BR>
	  * <B>概要说明：</B>验证码<BR>
	  * @return
	  */
    private  String getMsgCode() {
        int n = 6;
        StringBuilder code = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < n; i++) {
            code.append(Integer.valueOf(ran.nextInt(10)).toString());
        }
        return code.toString();
    }

	
    

}
