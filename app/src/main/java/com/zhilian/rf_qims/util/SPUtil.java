package com.zhilian.rf_qims.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;

public class SPUtil {

		private Context context;
		private SharedPreferences sp;
		private SharedPreferences.Editor editor;
		public static final String MY_PREFERENCE = "pf";

	public SPUtil(Context context) {
			this.context = context;
			sp = this.context.getSharedPreferences("common", Context.MODE_PRIVATE);
			editor = sp.edit();
		}

	private static void paraCheck(SharedPreferences sp, String key) {
		if (sp == null) {
			throw new IllegalArgumentException();
		}
		if (TextUtils.isEmpty(key)) {
			throw new IllegalArgumentException();
		}
	}

	public void getInstance(Context context, String filename) {
			this.context = context;
			sp = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
			editor = sp.edit();
		}

		public void putBoolean(String key, Boolean value) {
			editor.putBoolean(key, value);
			editor.commit();
		}

		public boolean getBoolean(String key, Boolean defValue) {
			return sp.getBoolean(key, defValue);
		}

		public void putString(String key, String value) {
			if (key == null) {
				return;
			}
			editor.putString(key, value);
			editor.commit();
		}

		public String getString(String key, String defValue) {
			return sp.getString(key, defValue);
		}

		public void putInt(String key, int value) {
			editor.putInt(key, value);
			editor.commit();
		}

		public int getInt(String key, int defValue) {
			return sp.getInt(key, defValue);
		}

		public Map<String, ?> getAll() {
			return sp.getAll();
		}

	public static boolean putBitmap(Context context, String key, Bitmap bitmap) {
		SharedPreferences sp = context.getSharedPreferences(MY_PREFERENCE,
				Context.MODE_PRIVATE);

		paraCheck(sp, key);
		if (bitmap == null || bitmap.isRecycled()) {
			return false;
		} else {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
			String imageBase64 = new String(Base64.encode(baos.toByteArray(),
					Base64.DEFAULT));
			SharedPreferences.Editor e = sp.edit();
			e.putString(key, imageBase64);
			return e.commit();
		}
	}

	public static Bitmap getBitmap(Context context, String key,
								   Bitmap defaultValue) {
		SharedPreferences sp = context.getSharedPreferences(MY_PREFERENCE,
				Context.MODE_PRIVATE);

		paraCheck(sp, key);
		String imageBase64 = sp.getString(key, "");
		if (TextUtils.isEmpty(imageBase64)) {
			return defaultValue;
		}

		byte[] base64Bytes = Base64.decode(imageBase64.getBytes(),
				Base64.DEFAULT);
		ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
		Bitmap ret = BitmapFactory.decodeStream(bais);
		if (ret != null) {
			return ret;
		} else {
			return defaultValue;
		}
	}
	public  void clearData (Context context){
		SharedPreferences sp = context.getSharedPreferences("loginUser", Context.MODE_PRIVATE);
		editor.clear();
		editor.commit();
	}
}