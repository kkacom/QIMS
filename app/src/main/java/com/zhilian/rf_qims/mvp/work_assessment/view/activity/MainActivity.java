package com.zhilian.rf_qims.mvp.work_assessment.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.githang.statusbar.StatusBarCompat;
import com.zhilian.api.InQueryMsg;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.constant.BusinessContant;
import com.zhilian.rf_qims.entity.IntegrityUserDao;
import com.zhilian.rf_qims.entity.IntegrityUserJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.MyFragmentAdapter;
import com.zhilian.rf_qims.mvp.work_assessment.view.fragment.ContactsFragment;
import com.zhilian.rf_qims.mvp.work_assessment.view.fragment.EnterpriseFragment;
import com.zhilian.rf_qims.mvp.work_assessment.view.fragment.MessageFragment;
import com.zhilian.rf_qims.mvp.work_assessment.view.fragment.MessageFragmentDome;
import com.zhilian.rf_qims.mvp.work_assessment.view.fragment.UserFragment;
import com.zhilian.rf_qims.mvp.work_assessment.view.fragment.WorkbenchFragment;
import com.zhilian.rf_qims.update.UpdateService1;
import com.zhilian.rf_qims.update.UpdateVersion;
import com.zhilian.rf_qims.util.CommonUtils;
import com.zhilian.rf_qims.util.PreferenceUtils;
import com.zhilian.rf_qims.util.ToastUtils;
import com.zhilian.rf_qims.widget.CustomDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 主界面
 */
public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {
    @BindView(R.id.realtabcontent)
	FrameLayout mRealtabcontent;
    @BindView(R.id.tabcontent)
	FrameLayout mTabcontent;
    @BindView(R.id.tabhost)
	FragmentTabHost mTabhost;
    @BindView(R.id.viewpager)
	ViewPager mViewpager;

    private static int REQUESTPERMISSION = 110 ;
    private Intent updateService;
    private CustomDialog mDialog = null;// 版本更新的弹窗
    private String DownUrl = null;// 版本更新Url地址
    private FragmentTabHost mFragmentTabHost;

    private String mTextArray[] = {"消息","工作台","联系人", "我"};// Fragment的文字界面
    // Fragment数组界面
    private Class mFragmentArray[] = {WorkbenchFragment.class, MessageFragment.class,
            EnterpriseFragment.class, UserFragment.class};
    // 存放图片数组
    private int mImageArray[] = {R.drawable.message_selector, R.drawable.azjc_selector,
            R.drawable.cynl_selector, R.drawable.cxpg_selector};
    // 存放Fragment的数组
    List<Fragment> mFragmentList = new ArrayList<>();
    // 管理Fragment的适配器
    MyFragmentAdapter mMyFragmentAdapter;
    int mCounts = mFragmentArray.length;// 获取Fragment的个数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor(BusinessContant.InfoTitle), true);
        ButterKnife.bind(this);
        initViewPager();// 初始化ViewPager
        initTabHost();  // 初始化TabHost
        initView();     // 给TabHost设置属性
        intiPage();     // 初始化页面

        if(CommonUtils.isNetworkAvailable(this)){
            checkVersion();// 检测版本更新
        }
    }

    /**实现setOnTabChangedListener接口,监听界面切换，实现TabHost里面图片文字的选中状态切换
     * 为了当点击下面菜单时,上面的ViewPager能滑动到对应的Fragment
     * */
    private void initTabHost() {
        mTabhost.setOnTabChangedListener(this);
    }

    /**实现OnPageChangeListener接口,监听Tab选项卡的变化，然后通知ViewPager适配器切换界面
     * 让ViewPager滑动的时候能够带着底部菜单联动
     * */
    private void initViewPager() {
        mViewpager.addOnPageChangeListener(this);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.viewpager);
    }

    private void intiPage() {
        mFragmentList.add(new MessageFragmentDome());// 消息 MessageFragment
        mFragmentList.add(new WorkbenchFragment());// 工作台
        mFragmentList.add(new ContactsFragment());// 联系人 EnterpriseFragment
        mFragmentList.add(new UserFragment());// 用户
        //mFragmentList.add(new FeedBackFragment());
        // 绑定Fragment适配器
        mMyFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), mFragmentList);
        mViewpager.setAdapter(mMyFragmentAdapter);
        mTabhost.getTabWidget().setDividerDrawable(null);
        mTabhost.setCurrentTab(1);// 设置进入主界面时显示的tab页
    }

    private void initView() {
        for (int i = 0; i < mCounts; i++) {
            // 给每个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(mTextArray[i])
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中，并绑定Fragment
            mTabhost.addTab(tabSpec, mFragmentArray[i],null);
            mTabhost.setTag(i);
           /* mTabhost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.drawable.selector_tab_background);//设置Tab被选中的时候颜色改变*/
        }
    }

    private View getTabItemView(int index) {
        View view = CommonUtils.getView(R.layout.tab_item_view);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        imageView.setImageResource(mImageArray[index]);
        textView.setText(mTextArray[index]);
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        TabWidget widget = mTabhost.getTabWidget();
        int oldFocusability = widget.getDescendantFocusability();
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);// 设置View覆盖子类控件而直接获得焦点
        mTabhost.setCurrentTab(position);// 根据位置position设置当前的Tab
        widget.setDescendantFocusability(oldFocusability);// 设置取消分割线
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String tabId) {
        int position = mTabhost.getCurrentTab();
        mViewpager.setCurrentItem(position);// 把选中的Tab的位置赋给适配器，让它控制页面切换
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        try {
            // Checks the orientation of the screen
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
               /* toolbarLayout.setVisibility(View.GONE);*/
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
               /* toolbarLayout.setVisibility(View.VISIBLE);*/
            }
        } catch (Exception ex) {

        }
    }

    /**
     * 检测版本更新
     */
    private void checkVersion() {
        // TODO 这里版本更新，和拿key的方式可优化
        String name = PreferenceUtils.getString(MainActivity.this, "userName");// 拿到账号
        List<IntegrityUserJson> userList = IntegrityUserDao.query(name);// 根据账号，获取当前用户的数据
        InQueryMsg inQueryMsg = new InQueryMsg(1348831860, "query", userList.get(0).getPwd2());// 再拿到key
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
                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.show("网络访问失败,请检查当前网络");
                    }
                });*/
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
                    if(app_version > CommonUtils.getVersionCode(MainActivity.this)){
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
                                //LogUtil.d("主界面的版本检查：", "当前已是最新版本");
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
        mDialog = new CustomDialog(MainActivity.this);
        mDialog.setTitle("发现新版本");
        mDialog.setContent(content);
        mDialog.setOKButton("立即更新", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateService = new Intent(MainActivity.this,  UpdateService1.class);
                updateService.putExtra("url", DownUrl);
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    // 申请权限
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUESTPERMISSION);
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
