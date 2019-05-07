package com.zhilian.rf_qims.mvp.launcher;

import com.colin.constant.Constants;
import com.colin.http.HttpServiceManager;
import com.colin.http.HttpUtil;
import com.colin.utils.LogUtil;
import com.google.gson.Gson;
import com.zhilian.rf_qims.bean.UpdateBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by colin on 2018/3/26 15:25 .
 */

public class LauncherModel {
	public void checkVersion(final Callback callback) {
		Map<String,String> map = new HashMap<>();
		map.put("app_type", String.valueOf(1));// 这里传的是类型 0: ios  1: android
		HttpServiceManager.getInstance()
			.getHttpService()
			.getServerData(HttpUtil.initUrl(), HttpUtil.initQueryParams(Constants.TYPE_QUERY, Constants.QUERY_VERSION, map))
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Consumer<ResponseBody>() {
				@Override
				public void accept(@NonNull ResponseBody responseBody) throws Exception {
					//TODO
					String json = responseBody.string();
					UpdateBean model = new Gson().fromJson(json, UpdateBean.class);
					callback.onCheckVersion(model);
				}
			}, new Consumer<Throwable>() {
				@Override
				public void accept(Throwable throwable) throws Exception {
					LogUtil.e("下载版本信息：" + throwable.getMessage());
				}
			});
	}

	interface Callback{
		void onCheckVersion(UpdateBean model);
	}
}
