/**
 * Copyright 2019 SinoSoft. All Rights Reserved.
 */
package com.example.sms.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 */
@Data
@TableName("t_sms")
public class Message implements Serializable{

	/**  */
	private static final long serialVersionUID = 3767006154991129035L;

	@TableId(value = "ID", type = IdType.AUTO)
	private Long id;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 短信类型
	 */
	private String type;
	/**
	 * 短信内容
	 */
	private String content;
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 短信生成时间
	 */
	private Date createTime;
}
