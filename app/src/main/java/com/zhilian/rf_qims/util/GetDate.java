package com.zhilian.rf_qims.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by colin on 2018/11/19 16:57 .
 * 获取当前时间  年月日时分秒
 */
public class GetDate {
	public static String getDate(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// HH:mm:ss
		//获取当前时间
		Date date = new Date(System.currentTimeMillis());
		return simpleDateFormat.format(date);
	}

	public static String getDateLine(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
		//获取当前时间
		Date date = new Date(System.currentTimeMillis());
		return simpleDateFormat.format(date);
	}

	public static String getDateSplit(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
		//获取当前时间
		Date date = new Date(System.currentTimeMillis());
		return simpleDateFormat.format(date);
	}
}
