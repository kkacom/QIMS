package com.zhilian.rf_qims.mvp.work_assessment.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.colin.utils.LogUtil;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.api.Pwd;
import com.zhilian.rf_qims.entity.IntegrityUserDao;
import com.zhilian.rf_qims.entity.IntegrityUserJson;
import com.zhilian.rf_qims.inter.BaseUrl;
import com.zhilian.rf_qims.util.CommonUtils;
import com.zhilian.rf_qims.util.PreferenceUtils;
import com.zhilian.rf_qims.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 登录界面
 * 可优化：1、添加登录中效果
 *         2、更改离线验证方式（拿到后台的用户表数据）
 * 描述：1、无用户注册和更改密码功能，统一发放账号
 *       2、离线情况下不能切换用户
 *       3、在线一次只能验证一个用户，验证的用户会把上个用户替换掉
 */
public class LoginActivity extends Activity {
    @BindView(R.id.loginUser)
	EditText mLoginUser;// 账号
    @BindView(R.id.clear1)
	ImageView mClear1;// 清除
    @BindView(R.id.loginPassword)
	EditText mLoginPassword;// 密码
    @BindView(R.id.clear2)
	ImageView mClear2;// 清除
    @BindView(R.id.loginButton)
	Button mLoginButton;// 登录按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        setContentView(R.layout.activity_login);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉信息栏
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        // 从SP中拿到账号和密码
        final String name = PreferenceUtils.getString(this, "userName");
        final String password = PreferenceUtils.getString(this, "userPassword");
        // 将拿到的账号和密码放到对应的输入框上
        mLoginUser.setText(name);
        mLoginPassword.setText(password);
        // 实现自动登录
        if(name.isEmpty() || password.isEmpty()){// 判断账号和密码是否是空的
            //Log.d("LoginActivity", "账号或密码有空值！");
        }else{
            if(CommonUtils.isNetworkAvailable(LoginActivity.this)){// 在线
                new Thread() {
                    public void run() {
                        try {
                            // 验证账号密码
                            if (confirmUser(name, password)) {
                                Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent1);
                                finish();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }else{// 离线
                if(!name.isEmpty()){
                    // 根据账号从本地查找对应用户数据
                    List<IntegrityUserJson> userList = IntegrityUserDao.query(name);
                    // 上一次保存的账号
                    String inputName = userList.get(0).getDlid();
                    // 上一次保存的密码（加密）
                    String pwd1 = Pwd.encrypt(password);
                    // 本地数据库的密码（加密）
                    String pwd2 = userList.get(0).getPwd1();
                    //Log.d("输入的密码：", pwd1);
                    //Log.d("本地数据库的密码：", pwd2);
                    // 将上一次保存的账号和密码，跟本地账号和本地加密密码验证
                    if(name.equals(inputName) && pwd1.equals(pwd2)){// 验证成功
                        saveToSp(name, password);// 保存账号和密码到SP
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{// 验证失败
                        //Log.d("自动登录的离线验证：", "验证失败:账号或密码不正确");
                    }
                }
            }
        }

        // 登录按钮的点击事件
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = mLoginUser.getText().toString().trim();// 拿到输入的账号
                final String userPassword = mLoginPassword.getText().toString().trim();// 拿到输入的密码
                loginEmpty(userName, userPassword);// 账号密码为空时的提示

                if(CommonUtils.isNetworkAvailable(LoginActivity.this)){// 在线登录
                    new Thread() {
                        public void run() {
                            try {
                                // 验证账号密码
                                if (confirmUser(userName, userPassword)) {// 登录成功
                                    saveToSp(userName, userPassword);// 保存用户名和密码到SP
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {// 登录失败
                                    // 这里登录失败后台会有反馈信息
                                    //Log.d("有网登录：", "登录失败,请检查用户名或密码");

                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }else{// 离线登录
                    // TODO 验证用户是否是第一次登录的逻辑待优化
                    String name = PreferenceUtils.getString(LoginActivity.this, "userName");// 从SP中拿到账号
                    // 必须在有网的情况下登录一次，才能进行无网登录（根据账号是否为空来判断）
                    if(!name.isEmpty()){
                        // 根据账号查找对应用户数据
                        List<IntegrityUserJson> userList = IntegrityUserDao.query(name);
                        // 上一次保存的账号
                        String inputName = userList.get(0).getDlid();
                        // 输入的密码（加密）
                        String pwd1 = Pwd.encrypt(userPassword);
                        // 本地数据库的密码（加密）
                        String pwd2 = userList.get(0).getPwd1();
                        LogUtil.d(pwd1);
                        LogUtil.d(pwd2);
                        // 将输入账号和密码，跟本地账号验证和本地加密密码验证
                        if(userName.equals(inputName) && pwd1.equals(pwd2)){// 验证成功
                            saveToSp(userName, userPassword);// 保存用户名和密码到SP
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{// 验证失败
                            ToastUtils.show("提示：账号或密码不正确");
                        }
                    }else{
                        /*List<IntegrityUserJson> userList = IntegrityUserDao.query(name);
                        if(userList.size() > 0){
                            if(!userList.get(0).getDlid().equals(userName)){
                                ToastUtils.show("提示：此用户第一次登录，需在有网的情况下验证一次");
                            }
                        }*/
                        ToastUtils.show("提示：此用户第一次登录，需在有网的情况下验证一次");
                    }
                }
            }
        });

        // 清除账号
        mClear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginUser.setText("");
            }
        });
        // 清除密码
        mClear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPassword.setText("");
            }
        });
        // 账号输入框的焦点监听
        mLoginUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    mClear1.setVisibility(View.GONE);
                }
            }
        });
        // 账号的文字变化监听
        mLoginUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(start != before){
                    mClear1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // 密码输入框的焦点监听
        mLoginPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    mClear2.setVisibility(View.GONE);
                }
            }
        });
        // 密码的文字变化监听
        mLoginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(start != before){
                    mClear2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 保存到Sp
     * @param userName 用户名
     * @param userPassword 密码
     */
    private void saveToSp(String userName, String userPassword) {
        PreferenceUtils.putString(this, "userName", userName);
        PreferenceUtils.putString(this, "userPassword", userPassword);
    }

    /**
     * 判断账号密码的输入提示
     */
    private void loginEmpty(String userName, String userPassword){
        if (userName.equals("") && userPassword.equals("")) {
            ToastUtils.show("请输入账号和密码");
        } else if (userName.equals("")) {
            ToastUtils.show("请输入用户名");
        } else if (userPassword.equals("")) {
            ToastUtils.show("请输入密码");
        }
    }

    /**
     * 验证用户名和密码（登录请求）
     * @param user_id 用户名
     * @param pwd 密码
     */
    public boolean confirmUser(String user_id, String pwd) {
        // TODO 这里的网络请求待优化
        boolean is_exist = false;
        String target = BaseUrl.loginUrl  + user_id + "-" + pwd;
        try {       // 要访问的URL地址
            URL url;
            url = new URL(target);
            // 一个HTTP连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(5 * 1000);
            urlConn.connect();
            if (urlConn.getResponseCode() == 200) {
                Log.i("ss", "Get方式请求成功");
            } else {
                Log.i("ss", "Get方式请求失败");
            }
            InputStream in = urlConn.getInputStream(); // 获得读取的内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "/n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                JSONObject dataJson = new JSONObject(sb.toString());
                String status = dataJson.getString("status");// 登录状态（0 失败 1 成功）
                String msg = dataJson.getString("msg");// 登录成功或失败返回的信息
                LogUtil.d("登录返回数据："+sb.toString());

                if (status.equals("1")) {// 登录成功
                    is_exist = true;
                    String name = dataJson.getString("name");// 用户名
                    // 将用户数据保存到本地数据库
                    IntegrityUserJson userJson = new IntegrityUserJson();
                    userJson.setId((long)1);
                    userJson.setDlid(mLoginUser.getText().toString());// 账号
                    userJson.setPwd1(Pwd.encrypt(mLoginPassword.getText().toString()));// 密码（自己生成的）
                    userJson.setPwd2(msg);// 密码（保存后台的）
                    userJson.setName(name);// 用户名
                    IntegrityUserDao.insertOrReplace(userJson);
                    Log.d("boolean",is_exist+"");
                } else {// 登录失败
                    Looper.prepare();
                    ToastUtils.show(msg);
                    Looper.loop();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            in.close();// 关闭字符输入流对象
            urlConn.disconnect();// 断开连接
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Looper.prepare();
            ToastUtils.show("连接服务失败");
            Looper.loop();
            e.printStackTrace();
        }

        return is_exist;
    }
}
