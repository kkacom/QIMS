package com.zhilian.rf_qims.util;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.entity.PhotoPath;

import java.util.ArrayList;

/**
 * Created by colin on 2018/12/1 20:05 .
 * 拍照或录像
 */
public class PhotoOrVideo {
	//拍照
	public static void photo(Activity activity, Long id, String type, String status, int uploadStatus, String index){
		Album.image(activity) // Image selection.
			.singleChoice()
			.camera(true)
			.columnCount(2)
			.onResult(new Action<ArrayList<AlbumFile>>() {
				@Override
				public void onAction(@NonNull ArrayList<AlbumFile> result) {
					if (result != null){
                        /*BitmapFactory.Options options = new BitmapFactory.Options();
                        int inSampleSize = 4;
                        //Options 只保存图片尺寸大小，不保存图片到内存
                        //options.inJustDecodeBounds = true;//为true的时候不会真正加载图片，而是得到图片的宽高信息。
                        options.inSampleSize = inSampleSize;
                        mBitmap = BitmapFactory.decodeFile(result.get(0).getPath(), options);
                        SPUtil.putBitmap(getContext(),"khxc" + GetDate.getDate(),mBitmap);*/
						PhotoPath photoPath = new PhotoPath();
						photoPath.setId(id);
						photoPath.setStatus(status);
						photoPath.setUploadStatus(uploadStatus);
						photoPath.setType(type);
						photoPath.setRelid(index);
						photoPath.setPhoto(type + GetDate.getDate());
						photoPath.setPhotoPath(result.get(0).getPath());
						GreenDaoManager.getInstance().getNewSession().getPhotoPathDao().insert(photoPath);
						ToastUtils.show("照片保存完成");
					}
				}
			}).start();
	}
	//录像
	public static void video(Activity activity, Long id, String type, String status, int uploadStatus, String index){
		Album.video(activity) // Video selection.
			.singleChoice()
			.camera(true)
			.columnCount(2)
			.onResult(new Action<ArrayList<AlbumFile>>() {
				@Override
				public void onAction(@NonNull ArrayList<AlbumFile> result) {
					if (result != null){
						PhotoPath photoPath = new PhotoPath();
						photoPath.setId(id);
						photoPath.setStatus(status);
						photoPath.setUploadStatus(uploadStatus);
						photoPath.setType(type);
						photoPath.setRelid(index);
						photoPath.setPhoto(type + GetDate.getDate());
						photoPath.setPhotoPath(result.get(0).getPath());
						GreenDaoManager.getInstance().getNewSession().getPhotoPathDao().insert(photoPath);
						ToastUtils.show("录像保存完成");
					}
				}
			}).start();
	}
}
