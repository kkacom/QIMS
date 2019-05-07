package com.zhilian.rf_qims.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by colin on 2018/12/28 17:35 .
 * 判断是否有网络
 */
public class NetJudge {
	public static boolean judge(Activity activity){
		ConnectivityManager connectivityManager = (ConnectivityManager) activity
			.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobNetInfoActivity = connectivityManager
			.getActiveNetworkInfo();
		if (mobNetInfoActivity == null || !mobNetInfoActivity.isAvailable()) {  //网络是否有效
			return false;
		}else {
			return true;
		}
	}
}
