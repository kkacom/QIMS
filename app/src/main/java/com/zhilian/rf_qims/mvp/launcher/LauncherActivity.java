package com.zhilian.rf_qims.mvp.launcher;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.colin.constant.Constants;
import com.colin.download.service.DownloadService;
import com.colin.http.Ip;
import com.colin.utils.LogUtil;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.bean.UpdateBean;
import com.zhilian.rf_qims.mvp.main.view.MainActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by colin on 2018/3/26 15:19 .
 * 原作者在此活动检测更新程序，但会有无法弹出版本提示窗体的bug，后面放到MainActivity主页解决问题
 */

public class LauncherActivity extends AppCompatActivity {
	private Class clazz = MainActivity.class;
	private LauncherActivity context = this;
	private long exitTime = 0;
	private LauncherPresenter presenter;
	/**
	 * 检测版本更新
	 */
	ProgressBar progressBar;
	long downloadId;
	private String DownUrl = null;// 版本更新Url地址
	private DownloadService.DownloadBinder mDownloadBinder;
	private Disposable mDisposable;//可以取消观察者
	private ServiceConnection mConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mDownloadBinder = (DownloadService.DownloadBinder) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mDownloadBinder = null;
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!EasyPermissions.hasPermissions(this, Constants.PERMISSIONS)) {
			EasyPermissions.requestPermissions(this, "需要访问手机存储权限！", 10086, Constants.PERMISSIONS);
		}
		Intent intent = new Intent(LauncherActivity.this, DownloadService.class);
		startService(intent);
		bindService(intent, mConnection, BIND_AUTO_CREATE);//绑定服务
		/*presenter = new LauncherPresenter(this);
		presenter.checkVersion();*/
		startActivity(new Intent(context,clazz));
		finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			// 按下的如果是BACK，同时没有重复.
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
			}
		}
		return true;
	}

	public void onCheckVersion(UpdateBean model) {
		DownUrl = model.getDOWN_URL();// url地址
		final String version_desc = model.getVERSION_DESC();// 更新说明
		double app_version = model.getAPP_VERSION();// 版本号
		Constants.APP_VERSION = model.getVERSION_CODE();
		int force_update = model.getFORCE_UPDATE();// 强制更新
		LogUtil.e("force_update = "+force_update);
		// 判断服务版本号是否大于当前版本号
		//String[] arr = app_version.split("\\.");
		LogUtil.e("BusinessContant.localVersion = "+ Constants.localVersion);
		if (app_version > Constants.localVersion) {
			View view = LayoutInflater.from(LauncherActivity.this).inflate(R.layout.layout_checkversion, null);
			TextView currentVersion = (TextView) view.findViewById(R.id.tv_current_version);
			TextView updateVersion = (TextView) view.findViewById(R.id.tv_update_version);
			TextView updateDescription = (TextView) view.findViewById(R.id.tv_update_description);
			progressBar = (ProgressBar) view.findViewById(R.id.progress);
			currentVersion.setText("当前版本：v0.0." + Constants.localVersion);
			updateVersion.setText("升级版本：" + model.getAPP_VERSION());
			updateDescription.setText(version_desc);

			String ip =  Ip.getIp();
			if (force_update == 1) {
				downloadId = mDownloadBinder.startDownload(ip + DownUrl);
				startCheckProgress(downloadId);
				AlertDialog.Builder builder = new AlertDialog.Builder(LauncherActivity.this)
					.setView(view)
					.setTitle("软件升级");
				builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
					@Override
					public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
						if (keyCode == KeyEvent.KEYCODE_BACK){
							dialog.cancel();
							dialog.dismiss();
						}
						return true;
					}
				});
				AlertDialog dialog = builder.create();
				dialog.setCancelable(false);

				dialog.show();
			} else if (force_update == 0){
				AlertDialog.Builder builder = new AlertDialog.Builder(LauncherActivity.this)
					.setView(view)
					.setTitle("软件升级")
					.setPositiveButton("立即更新", null)
					.setNegativeButton("下次再说", null);
				builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
					@Override
					public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
						if (keyCode == KeyEvent.KEYCODE_BACK){
							dialog.cancel();
							dialog.dismiss();
						}
						return true;
					}
				});
				AlertDialog dialog = builder.create();
				dialog.setCancelable(false);
				dialog.show();
				dialog.getButton(DialogInterface.BUTTON_POSITIVE)
					.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setVisibility(View.INVISIBLE);
							dialog.getButton(DialogInterface.BUTTON_POSITIVE).setVisibility(View.INVISIBLE);
							downloadId = mDownloadBinder.startDownload(ip + DownUrl);
							startCheckProgress(downloadId);
						}
					});
			}
		}
	}


	//开始监听进度
	private void startCheckProgress(long downloadId) {
		Observable
			.interval(100, 200, TimeUnit.MILLISECONDS, Schedulers.io())//无限轮询,准备查询进度,在io线程执行
			.filter(times -> mDownloadBinder != null)
			.map(i -> mDownloadBinder.getProgress(downloadId,this))//获得下载进度
			.takeUntil(progress -> progress >= 100)//返回true就停止了,当进度>=100就是下载完成了
			.distinct()//去重复
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new ProgressObserver());
	}

	//观察者
	private class ProgressObserver implements Observer<Integer> {

		@Override
		public void onSubscribe(Disposable d) {
			mDisposable = d;
		}

		@Override
		public void onNext(Integer progress) {
			progressBar.setProgress(progress);//设置进度
		}

		@Override
		public void onError(Throwable throwable) {
			throwable.printStackTrace();
			Toast.makeText(context, "出错", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onComplete() {
			progressBar.setProgress(100);
			Toast.makeText(context, "下载完成", Toast.LENGTH_SHORT).show();
		}
	}
}
