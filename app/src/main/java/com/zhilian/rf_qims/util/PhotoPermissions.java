package com.zhilian.rf_qims.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by colin on 2018/11/13 15:43 .
 * 判断拍照可否打开
 */
public class PhotoPermissions {
	/*public static boolean isCameraUseable() {
		boolean canUse =true;
		Camera mCamera =null;
		try{
			mCamera = Camera.open();
			// setParameters 是针对魅族MX5。MX5通过Camera.open()拿到的Camera对象不为null
			Camera.Parameters mParameters = mCamera.getParameters();
			mCamera.setParameters(mParameters);
		}catch(Exception e) {
			canUse =false;
		}
		if(mCamera !=null) {
			mCamera.release();
		}
		return canUse;
	}*/
}
