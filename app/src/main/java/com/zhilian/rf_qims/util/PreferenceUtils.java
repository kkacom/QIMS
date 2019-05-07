package com.zhilian.rf_qims.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceUtils {
	public static void putBoolean(Context context, String key, boolean value) {
		// * 1 获取SharePreference
		// * 2 获取Editor
		// * 3 通过放key-value数据
		// * 4 提交
		// 1 获取SharePreference
		SharedPreferences sp = context.getSharedPreferences("config",
				context.MODE_PRIVATE);
		// 获取Editor
		Editor editor = sp.edit();
		// 通过edit放key-value数据
		editor.putBoolean(key, value);
		editor.commit();
	}

	// 取Boolean的值
	public static boolean getBoolean(Context context, String key,
									 Boolean defValue) {
		// 获取SharePreferences
		SharedPreferences sp = context.getSharedPreferences("config",
				context.MODE_PRIVATE);

		return sp.getBoolean(key, defValue);

	}
	//取Boolean的值
	public static boolean getBoolean(Context context, String key) {
		return getBoolean(context, key, false);
		
	}

	// 存String的值
	public static void putString(Context context, String key, String value) {
		SharedPreferences sp = context.getSharedPreferences("config",
				context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();

	}

	// 取String的值
	public static String getString(Context context, String key, String value) {
		SharedPreferences sp = context.getSharedPreferences("config",
				context.MODE_PRIVATE);
		return sp.getString(key, value);

	}
	public static String getString(Context context, String key) {
		return getString(context, key, "");
		
	}

	// 存int的值
	public static void putInt(Context context, String key, int value) {
		SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putInt(key, value);
		editor.commit();

	}
	//取int的值
	public static int getInt(Context context, String key, int value) {
		SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		
		return sp.getInt(key, value);
		
	}
	public static int getInt(Context context, String key) {
		return getInt(context, key, -1);
		
	}
}
