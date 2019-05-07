package com.colin.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.BuildConfig;
import android.support.v4.content.FileProvider;

import java.io.File;

import static com.colin.constant.ResConstants.MIME_MapTable;

/**
 * Created by colin on 2018/1/19.
 */

public class FileUtil {
	private static String APP_DIR_NAME = "rf_qims";
	private static String FILE_DIR_NAME = "files";
	private static String mRootDir;
	private static String mAppRootDir;
	private static String mFileDir;
	private static FileUtil instance =new FileUtil();
	public static FileUtil init() {
		mRootDir = getRootPath();
		if (mRootDir != null && !"".equals(mRootDir)) {
			mAppRootDir = mRootDir + "/" + APP_DIR_NAME;
			mFileDir = mAppRootDir + "/" + FILE_DIR_NAME;
			File appDir = new File(mAppRootDir);
			if (!appDir.exists()) {
				appDir.mkdirs();
			}
			File fileDir = new File(mAppRootDir + "/" + FILE_DIR_NAME);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}

		} else {
			mRootDir = "";
			mAppRootDir = "";
			mFileDir = "";
		}
		return instance;
	}

	public static String getRootPath() {
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			return Environment.getExternalStorageDirectory().getAbsolutePath(); // filePath:  /sdcard/
		} else {
			return Environment.getDataDirectory().getAbsolutePath() + "/data"; // filePath:  /data/data/
		}
	}
	public String getFileDir(){
		return mFileDir;
	}

	public static Uri getUriByFile(Context context,File file) {
		if (file == null) {
			throw new NullPointerException();
		}
		Uri uri;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			uri = FileProvider.getUriForFile(context, context.getPackageName()+".fileprovider", file);
		} else {
			uri = Uri.fromFile(file);
		}
		return uri;
	}

	public static Uri getUriForFile(Context context,File file) {
		if (file == null) {
			throw new NullPointerException();
		}
		Uri uri;
		String packageName = context.getPackageName();
		LogUtil.e("packageName:"+packageName);
		if (Build.VERSION.SDK_INT >= 24) {
			uri = FileProvider.getUriForFile(context, packageName+".FileProvider", file);
			context.grantUriPermission(packageName,uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
			context.grantUriPermission(packageName,uri,Intent.FLAG_GRANT_READ_URI_PERMISSION);
		} else {
			uri = Uri.fromFile(file);
		}
		LogUtil.e("uri:"+uri);
		return uri;
	}
	//获取文件实际存储地址
	public static String getPathByUri(final Context context, final Uri uri) {
		if ( null == uri ) return null;
		final String scheme = uri.getScheme();
		String data = null;
		if ( scheme == null )
			data = uri.getPath();
		else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
			data = uri.getPath();
		} else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
			Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
			if ( null != cursor ) {
				if ( cursor.moveToFirst() ) {
					int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
					if ( index > -1 ) {
						data = cursor.getString( index );
					}
				}
				cursor.close();
			}
		}
		return data;
	}

	//获取文件类型
	public static String getMIMEType(File file) {

		String type = "*/*";
		String fName = file.getName();
		//获取后缀名前的分隔符"."在fName中的位置。
		int dotIndex = fName.lastIndexOf(".");
		if (dotIndex < 0) {
			return type;
		}
	/* 获取文件的后缀名*/
		String end = fName.substring(dotIndex, fName.length()).toLowerCase();
		if (end == "") return type;
		//在MIME和文件类型的匹配表中找到对应的MIME类型。
		for (int i = 0; i < MIME_MapTable.length; i++) { //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
			if (end.equals(MIME_MapTable[i][0]))
				type = MIME_MapTable[i][1];
		}
		return type;
	}
	public static void openFile(Context context,File file){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri localUri = FileUtil.getUriByFile(context,file);
		intent.setDataAndType(localUri, FileUtil.getMIMEType(file));
		context.startActivity(intent);
	}




	public static String generateDownloadPath(Context context, String fileName){
		return context.getApplicationContext().getExternalFilesDir(null).getPath()+File.separator+fileName;
	}

}
