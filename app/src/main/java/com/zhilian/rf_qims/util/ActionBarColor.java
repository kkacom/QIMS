package com.zhilian.rf_qims.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.zhilian.rf_qims.R;

/**
 * Created by colin on 2018/11/13 11:11 .
 */
public class ActionBarColor {
	/**
	 * 改变通知栏颜色
	 */
	public static void setActionBar(Context context){
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(context, true);
		}
		SystemBarTintManager tintManager = new SystemBarTintManager((Activity) context);
		tintManager.setStatusBarTintEnabled(true);
		//这里设置自己需要改变通知栏的颜色
		tintManager.setStatusBarTintResource(R.color.colorPrimary);
	}

	@TargetApi(19)
	public static void setTranslucentStatus(Context context, boolean on) {
		Window win = ((Activity)context).getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}
}
