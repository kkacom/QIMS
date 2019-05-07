package com.colin.download.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;

/**
 * Created by colin on 2019/3/7 10:04 .
 */
public class JudgeVersion extends Activity{
	private static Activity mActivity;
	public static String path = "";
	//判断是否为8.0或以上版本
	public static void judgeVersion(Activity activity){
		mActivity = activity;
		// 如果系统版本是8.0以上，需要打开未知来源才能更新安装
		int nowVersion = Build.VERSION.SDK_INT; //现在的系统版本
		int planVersion = Build.VERSION_CODES.O; //预计需要的系统版本26以上，8.0
		if (nowVersion >= planVersion){
			boolean haveInstallPermission;
			haveInstallPermission = mActivity.getPackageManager().canRequestPackageInstalls(); //获取是否打开未知来源
			if (!haveInstallPermission){ //没有权限
				new AlertDialog.Builder(mActivity).setMessage("更新安装应用需要打开未知来源权限，请去设置中开启权限")
					.setPositiveButton("去打开", new DialogInterface.OnClickListener() {
						@RequiresApi(api = Build.VERSION_CODES.O)
						@Override
						public void onClick(DialogInterface dialog, int which) {
							startInstallPermissionSettingActivity();
						}
					}).show();
			}
		}
	}

	public static void judgeVersion(Activity activity, String apkPath){
		mActivity = activity;
		path = apkPath;
		// 如果系统版本是8.0以上，需要打开未知来源才能更新安装
		int nowVersion = Build.VERSION.SDK_INT; //现在的系统版本
		int planVersion = Build.VERSION_CODES.O; //预计需要的系统版本26以上，8.0
		if (nowVersion >= planVersion){
			boolean haveInstallPermission;
			haveInstallPermission = mActivity.getPackageManager().canRequestPackageInstalls(); //获取是否打开未知来源
			if (!haveInstallPermission){ //没有权限
				new AlertDialog.Builder(mActivity).setMessage("更新安装应用需要打开未知来源权限，请去设置中开启权限")
					.setPositiveButton("去打开", new DialogInterface.OnClickListener() {
						@RequiresApi(api = Build.VERSION_CODES.O)
						@Override
						public void onClick(DialogInterface dialog, int which) {
							startInstallPermissionSettingActivity();
						}
					}).show();
			}else {
				if (!path.equals("")){
					String apkPath1 = path;
					path = "";
					SystemManager.setPermission(apkPath1);//提升读写权限,否则可能出现解析异常
					InstallUtil.install(mActivity,apkPath1,false);
				}
			}
		}else {
			if (!path.equals("")){
				String apkPath1 = path;
				path = "";
				SystemManager.setPermission(apkPath1);//提升读写权限,否则可能出现解析异常
				InstallUtil.install(mActivity,apkPath1,false);
			}
		}
	}

	@RequiresApi(api = Build.VERSION_CODES.O)
	public static void startInstallPermissionSettingActivity() {
		Uri packageURI = Uri.parse("package:" + mActivity.getPackageName());
		//注意这个是8.0新API
		Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
		mActivity.startActivityForResult(intent, 10086); //打开软件就判断打开未知来源
	}

}
