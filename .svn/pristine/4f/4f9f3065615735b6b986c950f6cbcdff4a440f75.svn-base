package com.zhilian.rf_qims.mvp.main.view;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.colin.constant.Constants;
import com.colin.download.service.DownloadService;
import com.colin.download.utils.InstallUtil;
import com.colin.download.utils.JudgeVersion;
import com.colin.download.utils.SystemManager;
import com.colin.http.HttpServiceManager;
import com.colin.http.HttpUtil;
import com.colin.http.Ip;
import com.colin.utils.LogUtil;
import com.google.gson.Gson;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.adapter.WorkGridAdapter;
import com.zhilian.rf_qims.base.BaseActivity;
import com.zhilian.rf_qims.bean.AreaBean;
import com.zhilian.rf_qims.bean.UpdateBean;
import com.zhilian.rf_qims.bean.WorkResBean;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.entity.AreaMes;
import com.zhilian.rf_qims.entity.CheckUser;
import com.zhilian.rf_qims.entity.Model;
import com.zhilian.rf_qims.mvp.login.view.LoginActivity;
import com.zhilian.rf_qims.mvp.main.presenter.MainPresenter;
import com.zhilian.rf_qims.mvp.project_manager.view.ProjectManagerActivity;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessment.WorkAssessmentFieldActivity;
import com.zhilian.rf_qims.util.ToastUtils;
import com.zhilian.rf_qims.widget.DialogProgress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2017-10-9.
 */

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView<WorkResBean> {
	@BindView(R.id.function_list)
	GridView mFunctionList;
	@BindView(R.id.toolbar)
	Toolbar mToolbar;
	@BindView(R.id.company_number)  //公司，版本
	TextView mCompanyNumber;
	DialogProgress dialogProgress;

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
	protected int loadLayoutResource() {
		return R.layout.activity_main;
	}

	@Override
	protected void initView() {
		mCompanyNumber.setText("开发商：惠州市智联科技有限公司			版本号："+ Constants.localVersion);//间隔是3个tab

//        user = (UserInfo) getIntent().getExtras().getSerializable("user");
		dialogProgress = new DialogProgress(MainActivity.this, "正在初始化数据");
		dialogProgress.setCanceledOnTouchOutside(false);
		initToolBar();
		presenter.loadView();
		presenter.loadCacheData();
		mFunctionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				switch (i) {
					case 0:
						startActivity(new Intent(MainActivity.this, ProjectManagerActivity.class),
							ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this).toBundle());
						// finish();
						break;
					case 1:
						getFileName();
						break;
					case 2:
						startActivity(new Intent(MainActivity.this, WorkAssessmentFieldActivity.class));
						break;
					case 3:

						break;
					case 4:

						break;
					default:
						break;
				}
			}
		});
		List<AreaMes> areaMesList = GreenDaoManager.getInstance().getNewSession().getAreaMesDao().loadAll();
		if (areaMesList.size() == 0) {
			dialogProgress.show();

			getAreaList();
		}
//        getModelList();

		if (!EasyPermissions.hasPermissions(this, Constants.PERMISSIONS)) {
			EasyPermissions.requestPermissions(this, "需要访问手机存储权限！", 10086, Constants.PERMISSIONS);
		}
		Intent intent = new Intent(this, DownloadService.class);
		startService(intent);
		bindService(intent, mConnection, BIND_AUTO_CREATE);//绑定服务
		checkVersion();
	}

	//企业所属地市
	public void getAreaList() {
		Map<String, String> params = new HashMap<>();
		HttpServiceManager.getInstance()
			.getHttpService()
			.getServerData(HttpUtil.initUrl(), HttpUtil.initQueryParams("query", "getAreaList", params))
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Consumer<ResponseBody>() {
				@Override
				public void accept(ResponseBody responseBody) throws Exception {
					String json = responseBody.string();
					Log.d("server", "area--" + json);
					List<AreaBean> areaBeens = JSON.parseArray(json, AreaBean.class);
					for (int i = 0; i < areaBeens.size(); i++) {
						AreaMes areaMes = new AreaMes(areaBeens.get(i).getID(), areaBeens.get(i).getFID(),
							areaBeens.get(i).getSTATUS(), areaBeens.get(i).getLEVEL(), areaBeens.get(i).getNAME());
						GreenDaoManager.getInstance().getNewSession().getAreaMesDao().insert(areaMes);
					}
					getModelList();
				}
			}, new Consumer<Throwable>() {
				@Override
				public void accept(Throwable throwable) throws Exception {
					dialogProgress.dismiss();
					Log.d("server", "area--" + throwable.getMessage());
				}
			});
	}

	public void getModelList() {
		Map<String, String> params = new HashMap<>();
		HttpServiceManager.getInstance()
			.getHttpService()
			.getServerData(HttpUtil.initUrl(), HttpUtil.initQueryParams("query", "getModelList", params))
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Consumer<ResponseBody>() {
				@Override
				public void accept(ResponseBody responseBody) throws Exception {
					String json = responseBody.string();
					List<Model> modelBeens = JSON.parseArray(json, Model.class);
					for (int i = 0; i < modelBeens.size(); i++) {
						GreenDaoManager.getInstance().getNewSession().getModelDao().insert(modelBeens.get(i));
					}
//                        dialogProgress.dismiss();

					//Log.d("server", "modellist--" + json);
					getCheckUserList();
				}
			}, new Consumer<Throwable>() {
				@Override
				public void accept(Throwable throwable) throws Exception {
					Log.d("server", "modellist--" + throwable.getMessage());
					dialogProgress.dismiss();
				}
			});
	}

	private void getCheckUserList() {
		Map<String, String> params = new HashMap<>();
		HttpServiceManager.getInstance()
			.getHttpService()
			.getServerData(HttpUtil.initUrl(), HttpUtil.initQueryParams("query", "getUserList", params))
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Consumer<ResponseBody>() {
				@Override
				public void accept(ResponseBody responseBody) throws Exception {
					String json = responseBody.string();
					List<CheckUser> checkUsers = JSON.parseArray(json, CheckUser.class);
					for (int i = 0; i < checkUsers.size(); i++) {
						GreenDaoManager.getInstance().getNewSession().getCheckUserDao().insert(checkUsers.get(i));
					}
					dialogProgress.dismiss();
					Log.d("server", "userllist--" + json);
				}
			}, new Consumer<Throwable>() {
				@Override
				public void accept(Throwable throwable) throws Exception {
					Log.d("server", "userlist--" + throwable.getMessage());
					dialogProgress.dismiss();
				}
			});
	}

	private void initToolBar() {
		setSupportActionBar(mToolbar);
		getSupportActionBar().setTitle(R.string.login_app_name);
		mToolbar.setLogo(R.mipmap.icon_logo_toolbar);
		mToolbar.setNavigationIcon(null);
		mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
					case R.id.destroy:
						presenter.destroyUserPwd();
						break;
					case R.id.exit:
						System.exit(0);
						break;
				}
				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.work_toolbar, menu);
		return true;
	}

	@Override
	protected MainPresenter createPresenter() {
		return new MainPresenter(this);
	}


	@Override
	public void onRequestSuccessData(Object data) {

	}


	@Override
	public void initAdapter(List<WorkResBean> list) {
		WorkGridAdapter adapter = new WorkGridAdapter(list);
		mFunctionList.setAdapter(adapter);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	@Override
	public void reLogin() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}

	public void getFileName() {
		Map<String, String> params = new HashMap<>();
		params.put("type", "2");//1是报告 //2是原始记录
		params.put("id", "21");//样品id
		HttpServiceManager.getInstance()
			.getHttpService()
			.getServerData(HttpUtil.initUrl(), HttpUtil.initQueryParams("query", "getFileName", params))
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Consumer<ResponseBody>() {
				@Override
				public void accept(ResponseBody responseBody) throws Exception {
					Log.d("upload", "获取名字" + responseBody.string());
				}
			}, new Consumer<Throwable>() {
				@Override
				public void accept(Throwable throwable) throws Exception {
					Log.d("upload", "获取名字" + throwable.getMessage());
				}
			});
	}

	public void checkVersion() {
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
					onCheckVersion(model);
				}
			}, new Consumer<Throwable>() {
				@Override
				public void accept(Throwable throwable) throws Exception {
					LogUtil.e("下载版本信息：" + throwable.getMessage());
				}
			});
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
			View view = LayoutInflater.from(this).inflate(R.layout.layout_checkversion, null);
			TextView currentVersion = (TextView) view.findViewById(R.id.tv_current_version);
			TextView updateVersion = (TextView) view.findViewById(R.id.tv_update_version);
			TextView updateDescription = (TextView) view.findViewById(R.id.tv_update_description);
			progressBar = (ProgressBar) view.findViewById(R.id.progress);
			currentVersion.setText("当前版本：" + Constants.localVersion);
			updateVersion.setText("升级版本：" + model.getAPP_VERSION());
			updateDescription.setText(version_desc);

			String ip =  Ip.getIp();
			if (force_update == 1) {
				downloadId = mDownloadBinder.startDownload(ip + DownUrl);
				startCheckProgress(downloadId);
				AlertDialog.Builder builder = new AlertDialog.Builder(this)
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
				AlertDialog.Builder builder = new AlertDialog.Builder(this)
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
			.map(i -> mDownloadBinder.getProgress(downloadId, this))//获得下载进度
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
			Toast.makeText(MainActivity.this, "出错", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onComplete() {
			progressBar.setProgress(100);
			Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 10086){ //处理在更新下载完毕判断是否有未知来源权限
			boolean haveInstallPermission;
			haveInstallPermission = this.getPackageManager().canRequestPackageInstalls(); //获取是否打开未知来源
			if (haveInstallPermission) { //有未知来源权限
				if (!JudgeVersion.path.equals("")){
					String apkPath1 = JudgeVersion.path;
					JudgeVersion.path = "";
					SystemManager.setPermission(apkPath1);//提升读写权限,否则可能出现解析异常
					InstallUtil.install(this,apkPath1,false);
				}
			}else {
				ToastUtils.show("未开启未知来源权限，无法更新程序！");
			}
		}
	}
}
