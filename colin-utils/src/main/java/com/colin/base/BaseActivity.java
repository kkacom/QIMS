package com.colin.base;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;

import com.colin.dispatcher.NeedsPermission;
import com.colin.dispatcher.OnShowRationale;
import com.colin.dispatcher.PermissionRequest;
import com.colin.dispatcher.PermissionsDispatcher;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by colin on 2018/1/23.
 */

public abstract class BaseActivity extends Activity {
	protected String mCurrentPhotoPath;
	protected static final int REQUEST_TAKE_PHOTO = 1;
	@NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
	public void startCamera() {
		try {
			dispatchTakePictureIntent();
		} catch (IOException e) {
		}
	}

	@OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
	public void showRationaleForCamera(final PermissionRequest request) {
		new AlertDialog.Builder(this)
			.setMessage("Access to External Storage is required")
			.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
					request.proceed();
				}
			})
			.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
					request.cancel();
				}
			})
			.show();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		PermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
	}

	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = new File(Environment.getExternalStoragePublicDirectory(
			Environment.DIRECTORY_DCIM), "Camera");
		File image = File.createTempFile(
			imageFileName,  /* prefix */
			".jpg",         /* suffix */
			storageDir      /* directory */
		);

		// Save a file: path for use with ACTION_VIEW intents
		mCurrentPhotoPath = "file:" + image.getAbsolutePath();
		return image;
	}

	private void dispatchTakePictureIntent() throws IOException {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// Ensure that there's a camera activity to handle the intent
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			// Create the File where the photo should go
			File photoFile = null;
			try {
				photoFile = createImageFile();
			} catch (IOException ex) {
				// Error occurred while creating the File
				return;
			}
			// Continue only if the File was successfully created
			if (photoFile != null) {
				Uri photoURI = Uri.fromFile(createImageFile());
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
				startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
			}
		}
	}
}
