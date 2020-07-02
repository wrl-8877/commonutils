/**
 * Copyright 2019 SinoSoft. All Rights Reserved.
 */
package com.example.sms.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sms.domain.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：</B><BR>
	 * @param phone
	 * @return
	 */
	//List<Message> getByPhone(@Param("phone") String phone,@Param("type") String type);
	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：</B><BR>
	 * @param phone
	 * @param type
	 * @param time
	 * @return
	 */
	Message getByPhoneAndType(@Param("phone") String phone, @Param("type") String type, @Param("time") String time);
	
	/**
	 * 
	 * <B>方法名称：</B>getByPhone<BR>
	 * <B>概要说明：</B>根据号码获取最新时间的验证码<BR>
	 * @param phone
	 * @return
	 */
	Message getByPhone(@Param("phone") String phone);
}
