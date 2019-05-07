package com.zhilian.rf_qims.mvp.work_assessment.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.githang.statusbar.StatusBarCompat;
import com.zhilian.api.InQueryMsg;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.constant.BusinessContant;
import com.zhilian.rf_qims.entity.IntegrityUserDao;
import com.zhilian.rf_qims.entity.IntegrityUserJson;
import com.zhilian.rf_qims.update.UpdateService1;
import com.zhilian.rf_qims.update.UpdateVersion;
import com.zhilian.rf_qims.util.CommonUtils;
import com.zhilian.rf_qims.util.PreferenceUtils;
import com.zhilian.rf_qims.util.ToastUtils;
import com.zhilian.rf_qims.widget.CustomDialog;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 关于本软件
 * 待设计：1、使用帮助
 *         2、联系我们
 *         3、意见反馈
 */
public class AboutUsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mUseHelp;// 使用帮助
    private TextView mContactUs;// 联系我们
    private TextView mFeedback;// 意见反馈
    private TextView mUpdate;// 检测更新
    private String DownUrl = null;// App Url
    private TextView mVersionInfo;// 版本信息

    private static int REQUESTPERMISSION = 110 ;
    private Intent updateService ;
    private CustomDialog mDialog = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us_layout);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor(BusinessContant.InfoTitle), true);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);

        initView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        mUseHelp = (TextView) findViewById(R.id.use_help);
        mContactUs = (TextView) findViewById(R.id.contact_us);
        mFeedback = (TextView) findViewById(R.id.feedback);
        mUpdate = (TextView) findViewById(R.id.update);
        mVersionInfo = (TextView) findViewById(R.id.version_info);

        mUseHelp.setOnClickListener(this);
        mContactUs.setOnClickListener(this);
        mFeedback.setOnClickListener(this);
        mUpdate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.use_help:// 使用帮助
            case R.id.contact_us:// 联系我们
            case R.id.feedback:// 意见反馈
				Toast.makeText(AboutUsActivity.this,"开发中，敬请期待...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.update:// 检查更新
                if(CommonUtils.isNetworkAvailable(this)){
                    checkVersion();
                }else{
                    ToastUtils.show("提示：网络访问失败,请检查当前网络");
                }
                break;
        }
    }

	/**
     * 检测版本更新
     */
    private void checkVersion() {
        String name = PreferenceUtils.getString(AboutUsActivity.this, "userName");// 拿到账号
        List<IntegrityUserJson> userList = IntegrityUserDao.query(name);// 根据账号，获取当前用户的数据
        InQueryMsg inQueryMsg = new InQueryMsg(1348831860, "query", userList.get(0).getPwd2());
        inQueryMsg.setQueryName("updateVersion");
        HashMap<String, String> map = new HashMap<>();
        map.put("app_type", String.valueOf(1));// 这里传的是类型 0: ios  1: android
        inQueryMsg.setQueryPara(map);
        String postData = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            postData = mapper.writeValueAsString(inQueryMsg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        OkHttpClient mOkHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, postData);

        Request request = new Request.Builder()
                                  .url("http://192.168.9.126:8085/Mobile/Api")
                                  .post(requestBody)
                                  .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.show("网络访问失败,请检查当前网络");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                UpdateVersion model = com.alibaba.fastjson.JSON.parseObject(response.body().string(), UpdateVersion.class);
                //System.out.println("数据——》"+response.body().string());

                DownUrl = model.getDown_url();// url地址
                final String version_desc = model.getVersion_desc();// 更新说明
                int app_version = Integer.parseInt(model.getApp_version());// 版本号
                int force_update = model.getForce_update();// 是否强制更新

                try {
                    // 判断服务器版本号是否大于当前版本号
                    if(app_version > CommonUtils.getVersionCode(AboutUsActivity.this)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showDialog(version_desc);
                            }
                        });

                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AboutUsActivity.this,"当前已是最新版本", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 提示版本更新的Dialog
     * @param content 更新内容
     */
    private void showDialog(String content){
        mDialog = new CustomDialog(AboutUsActivity.this);
        mDialog.setTitle("发现新版本");
        mDialog.setContent(content);
        mDialog.setOKButton("立即更新", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateService = new Intent(AboutUsActivity.this,  UpdateService1.class);
                updateService.putExtra("url", DownUrl);
                if(ContextCompat.checkSelfPermission(AboutUsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    // 申请权限
                    ActivityCompat.requestPermissions(AboutUsActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUESTPERMISSION);
                    ToastUtils.show("请允许权限进行下载安装");
                }else{
                    startService(updateService);
                    mDialog.dismiss();
                }
            }
        });
        mDialog.setCancelButton("下次再说", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESTPERMISSION) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (updateService != null)
                        startService(updateService);
                } else {
                    ToastUtils.show("提示：没有权限无法安装");
                }
            }
        }
    }

}
