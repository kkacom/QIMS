package com.colin.dispatcher;

import android.support.v4.app.ActivityCompat;


import com.colin.base.BaseActivity;

import java.lang.ref.WeakReference;


/**
 * Created by colin on 2018/1/18.
 */

public class PermissionsDispatcher {
	private static final int REQUEST_STARTCAMERA = 0;

	private static final String[] PERMISSION_STARTCAMERA = new String[] {"android.permission.WRITE_EXTERNAL_STORAGE"};

	private PermissionsDispatcher() {
	}

	public static void startCameraWithCheck(BaseActivity target) {
		if (PermissionUtils.hasSelfPermissions(target, PERMISSION_STARTCAMERA)) {
			target.startCamera();
		} else {
			if (PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_STARTCAMERA)) {
				target.showRationaleForCamera(new PermissionsDispatcher.StartCameraPermissionRequest(target));
			} else {
				ActivityCompat.requestPermissions(target, PERMISSION_STARTCAMERA, REQUEST_STARTCAMERA);
			}
		}
	}

	public static void onRequestPermissionsResult(BaseActivity target, int requestCode, int[] grantResults) {
		switch (requestCode) {
			case REQUEST_STARTCAMERA:
				if (PermissionUtils.getTargetSdkVersion(target) < 23 && !PermissionUtils.hasSelfPermissions(target, PERMISSION_STARTCAMERA)) {
					return;
				}
				if (PermissionUtils.verifyPermissions(grantResults)) {
					target.startCamera();
				}
				break;
			default:
				break;
		}
	}

	private static final class StartCameraPermissionRequest implements PermissionRequest {
		private final WeakReference<BaseActivity> weakTarget;

		private StartCameraPermissionRequest(BaseActivity target) {
			this.weakTarget = new WeakReference<>(target);
		}

		@Override
		public void proceed() {
			BaseActivity target = weakTarget.get();
			if (target == null) return;
			ActivityCompat.requestPermissions(target, PERMISSION_STARTCAMERA, REQUEST_STARTCAMERA);
		}

		@Override
		public void cancel() {
		}
	}
}
