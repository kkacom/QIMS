package com.colin.http;

/**
 * Created by colin on 2018/12/7 9:52 .
 */
public class Ip {
	private static String ip;//从sp小数据库拿值赋值，ip地址

	public static String getIp() {
		return ip;
	}

	public static void setIp(String ip) {
		Ip.ip = ip;
	}
}
