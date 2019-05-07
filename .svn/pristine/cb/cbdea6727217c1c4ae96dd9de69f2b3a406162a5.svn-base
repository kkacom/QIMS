package com.zhilian.rf_qims.mvp.login.view;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.colin.constant.Constants;
import com.colin.http.HttpConstants;
import com.colin.http.Ip;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.api.Pwd;
import com.zhilian.rf_qims.base.BaseActivity;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.entity.UserInfo;
import com.zhilian.rf_qims.mvp.launcher.LauncherActivity;
import com.zhilian.rf_qims.mvp.login.presenter.LoginPresenter;
import com.colin.download.utils.JudgeVersion;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 名称：登录页面（Activity）
 * 描述：这里是用户登录的页面
 * 功能：1、输入用户名和密码，点击登录后，登录
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView<UserInfo>, EasyPermissions.PermissionCallbacks {
	@BindView(R.id.loginForgetPwd)
	public TextView tv_forgetPwd;// 忘记密码
	@BindView(R.id.loginUser)
	public EditText et_user;// 输入用户名
	@BindView(R.id.loginPassword)
	public EditText et_password;// 输入密码
	@BindView(R.id.loginButton)
	public Button login_button;// 登录
	@BindView(R.id.eye)
	public ImageView eye;// 查看密码
	@BindView(R.id.clear1)
	ImageView clear1;// 清除账号
	@BindView(R.id.clear2)
	ImageView clear2;// 清除密码
	@BindView(R.id.cloud_services) // 互联网
	RadioButton mCloudServices;
	@BindView(R.id.lan) // 局域网
	RadioButton mLan;
	@BindView(R.id.off_line_login)//离线登录
	public Button mOffLineLogin;
	@BindView(R.id.loginCopyright) // 底部公司与版本号
	TextView mLoginCopyright;

	SharedPreferences sp;
	SharedPreferences.Editor mEditor;
	private boolean flag = false;
	private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
	String[] perms = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
		Manifest.permission.WRITE_EXTERNAL_STORAGE};

	@Override
	protected int loadLayoutResource() {
		return R.layout.activity_login;
	}

	@Override
	protected LoginPresenter createPresenter() {
		return new LoginPresenter(this);
	}

	@Override
	protected void initView() {
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                // TODO: show explanation
//            } else {
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
//            }
//        }
		List<UserInfo> userInfo = GreenDaoManager.getInstance().getNewSession().getUserInfoDao().loadAll();
		mLoginCopyright.setText("开发商：惠州市智联科技有限公司			版本号："+ Constants.localVersion);//间隔是3个tab
		if (userInfo.size() > 0){
			et_user.setText(userInfo.get(0).getRealName());
			et_password.setText(Pwd.decrypt(userInfo.get(0).getPwd()));
		}
		sp = getSharedPreferences("Ip", MODE_PRIVATE);// 存放ip地址（用于互联网和局域网）
		mEditor = sp.edit();
		String base_url = sp.getString("ip", "");
		if (base_url != null && !base_url.equals("")) {
			Ip.setIp(base_url); //赋值ip地址
			Log.e("loadIp", "本地存储地址：{" + Ip.getIp() + "}");
		}

		if (!EasyPermissions.hasPermissions(this, perms)) {
			EasyPermissions.requestPermissions(this, "需要访问手机存储权限！", 10086, perms);
		} else {
			/*Log.d("permission", "已获取权限");
			if (mobNetInfoActivity == null || !mobNetInfoActivity.isAvailable()) {  //网络是否有效
				presenter.loadCacheData();
			}*/
		}
		JudgeVersion.judgeVersion(this);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		//把申请权限的回调交由EasyPermissions处理
		EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
	}

	//下面两个方法是实现EasyPermissions的EasyPermissions.PermissionCallbacks接口
	//分别返回授权成功和失败的权限
	@Override
	public void onPermissionsGranted(int requestCode, List<String> perms) {
		Log.d("permission", "获取成功的权限" + perms);
		/*if (mobNetInfoActivity == null || !mobNetInfoActivity.isAvailable()) {  //网络是否有效
			presenter.loadCacheData();
		}*/
	}

	@Override
	public void onPermissionsDenied(int requestCode, List<String> perms) {
		Log.d("permission", "获取失败的权限" + perms);
	}

	@OnClick(R.id.eye)
	void eye() {
		if (flag == false) {
			et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
			eye.setImageResource(R.mipmap.icon_eye2);
			flag = true;
		} else {
			et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
			eye.setImageResource(R.mipmap.icon_eye1);
			flag = false;
		}
	}

	@OnClick(R.id.loginButton)
	void onLogin() {
//        login1();
		final String loginName = et_user.getText().toString();
		final String pwd = et_password.getText().toString();
		if (loginName.equals("") && pwd.equals("") && loginName.trim().equals("") && pwd.trim().equals("")) {
			Toast.makeText(this, "请输入账号和密码！", Toast.LENGTH_SHORT).show();
		} else if (loginName.equals("") && !pwd.trim().equals("")) {
			Toast.makeText(this, "请输入账号！", Toast.LENGTH_SHORT).show();
		} else if (pwd.trim().equals("") && !loginName.trim().equals("")) {
			Toast.makeText(this, "请输入密码！", Toast.LENGTH_SHORT).show();
		} else {
//            LogUtil.e("onLogin()");
			UserInfo user = new UserInfo();
			user.setLoginName(loginName);
			user.setPwd(pwd);
			if (mCloudServices.isChecked()) {
				mEditor.putString("ip", HttpConstants.CLOUD_SERVICES);
				mEditor.commit();
				Ip.setIp(HttpConstants.CLOUD_SERVICES);
				//Log.e("点击登录", Ip.getIp());
			} else {
				mEditor.putString("ip", HttpConstants.LAN);
				mEditor.commit();
				Ip.setIp(HttpConstants.LAN);
				//Log.e("点击登录", Ip.getIp());
			}
			presenter.login(user);
		}
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	@OnClick(R.id.off_line_login)
	public void offLineLogin() {
		if (mCloudServices.isChecked()) {
			mEditor.putString("ip", HttpConstants.CLOUD_SERVICES);
			mEditor.commit();
			Ip.setIp(HttpConstants.CLOUD_SERVICES);
		} else {
			mEditor.putString("ip", HttpConstants.LAN);
			mEditor.commit();
			Ip.setIp(HttpConstants.LAN);
		}
		Intent intent1 = new Intent(LoginActivity.this, LauncherActivity.class);
		startActivity(intent1);
		finish();
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	private void login1() {
		Intent intent1 = new Intent(LoginActivity.this, LauncherActivity.class);
		startActivity(intent1);
		finish();
	}

	@OnClick(R.id.loginForgetPwd)
	void forgetPwd() {

	}

	@OnClick(R.id.clear1)
	void clear1() {
		et_user.setText("");
	}

	@OnClick(R.id.clear2)
	void clear2() {
		et_password.setText("");
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	@Override
	public void login() {
		Intent intent1 = new Intent(LoginActivity.this, LauncherActivity.class);
		startActivity(intent1);
		finish();
	}

	@Override
	public void loginErrMsg(String errmsg) {
		Toast.makeText(LoginActivity.this, errmsg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void forgotPwd() {

	}

//    @Override
//    public void exception() {
//
//    }

	@Override
	public void showDiaProgress() {
		dialogProgress.show();
	}

	@Override
	public void hideDiaProgress() {
		dialogProgress.dismiss();
	}


	@Override
	public void onRequestSuccessData(UserInfo data) {
		if (null != data) {
			et_user.setText(data.getLoginName());
			et_password.setText(data.getPwd());
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}
}

