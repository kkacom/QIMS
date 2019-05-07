package com.colin.system;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by colin on 2018/1/24.
 */

public class CameraUtil {
	public static final int REQUEST_CAMERA = 0x201;
	public static void useCamera(Activity activity, Uri uri){
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		//添加权限
		intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		activity.startActivityForResult(intent, REQUEST_CAMERA);
	}
}
