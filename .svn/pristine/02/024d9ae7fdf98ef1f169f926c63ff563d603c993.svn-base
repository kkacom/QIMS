package com.colin.system;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;


/**
 * Created by colin on 2018/1/24.
 */

public class PermissionUtil {
	public static boolean applyWritePermission(Activity activity) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			int check = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
			// 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
			if (check == PackageManager.PERMISSION_GRANTED) {
				//授权成功
				return true;
			} else {
				activity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
			}
		} else {
			//授权成功
			return true;
		}
		return false;
	}
	public static boolean applyReadPermission(Activity activity) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			int check = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
			// 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
			if (check == PackageManager.PERMISSION_GRANTED) {
				//授权成功
				return true;
			} else {
				activity.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
			}
		} else {
			//授权成功
			return true;
		}
		return false;
	}
}
