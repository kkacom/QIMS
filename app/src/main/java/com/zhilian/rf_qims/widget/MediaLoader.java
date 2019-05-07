package com.zhilian.rf_qims.widget;

import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;

/**
 * Created by colin on 2018/11/12 9:47 .
 */
public class MediaLoader implements AlbumLoader {
	@Override
	public void load(ImageView imageView, AlbumFile albumFile) {
		load(imageView, albumFile.getPath());
	}

	@Override
	public void load(ImageView imageView, String url) {
		Glide.with(imageView.getContext())
			.load(url)
			.into(imageView);
	}

}
