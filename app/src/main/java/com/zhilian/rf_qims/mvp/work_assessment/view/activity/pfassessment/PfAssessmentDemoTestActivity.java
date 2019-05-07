package com.zhilian.rf_qims.mvp.work_assessment.view.activity.pfassessment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.githang.statusbar.StatusBarCompat;
import com.zhilian.api.InQueryMsg;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.api.InSaveMsg;
import com.zhilian.rf_qims.constant.BusinessContant;
import com.zhilian.rf_qims.entity.EnterpriseCreditDao;
import com.zhilian.rf_qims.entity.EnterpriseCreditJson;
import com.zhilian.rf_qims.entity.EvaluateAddDao;
import com.zhilian.rf_qims.entity.EvaluateAddJson;
import com.zhilian.rf_qims.entity.EvaluateDeductDao;
import com.zhilian.rf_qims.entity.EvaluateDeductJson;
import com.zhilian.rf_qims.entity.EvaluatePackage;
import com.zhilian.rf_qims.entity.EvaluateStandardDao;
import com.zhilian.rf_qims.entity.EvaluateStandardJson;
import com.zhilian.rf_qims.entity.EvaluateTestDao;
import com.zhilian.rf_qims.entity.EvaluateTestJson;
import com.zhilian.rf_qims.entity.IntegrityUserDao;
import com.zhilian.rf_qims.entity.IntegrityUserJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.fragment.asessment.FragmentDefenseDemo;
import com.zhilian.rf_qims.mvp.work_assessment.view.fragment.asessment.FragmentDesignDemo;
import com.zhilian.rf_qims.mvp.work_assessment.view.fragment.asessment.FragmentSuvDemo;
import com.zhilian.rf_qims.mvp.work_assessment.view.fragment.asessment.PfassmentAdapter;
import com.zhilian.rf_qims.util.EventUtil;
import com.zhilian.rf_qims.util.Global;
import com.zhilian.rf_qims.util.PreferenceUtils;
import com.zhilian.rf_qims.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
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
 * 诚信评估企业名单
 */
public class PfAssessmentDemoTestActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.ll_dow_data)
	LinearLayout mLlDowData;// 下载
    @BindView(R.id.ll_update_data)
	LinearLayout ll_update_data;// 上传
    private TextView[] mTabs = new TextView[3];// 选项卡
    private TextView mCurrentTab;// 当前选中的选项卡
    private View mTabIndicator;// 选项卡指示线
    private int mTabWidth;// 选项卡的宽度
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pf_assessment_demo);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor(BusinessContant.InfoTitle), true);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);                        // 用toolbar替换原来的ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// 这句代码使启用Activity回退功能，并显示Toolbar上的左侧回退图标
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

    // 暂时不用
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);// 指定Toolbar上的视图文件
        return true;
    }*/

    public void initView() {
        // 用TextView来取代TabLayout的作用
        mTabs[0] = (TextView) findViewById(R.id.tv_defense);// 防护企业
        mTabs[1] = (TextView) findViewById(R.id.tv_supervision);// 监理企业
        mTabs[2] = (TextView) findViewById(R.id.tv_design);// 设计企业

        // 默认选中第一个选项
        mCurrentTab = mTabs[0];

        mCurrentTab.setSelected(true);
        // 选中的时候,textView变大,颜色变绿色
        mCurrentTab.setScaleX(1.2f);
        mCurrentTab.setScaleY(1.2f);
        initViewPager();
        initTabIndicator();
    }

    private void initTabIndicator() {
        mTabWidth = Global.mScreenWidth / 2;

        // 设置选项卡的宽度
        mTabIndicator = findViewById(R.id.tab_indicator);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mTabIndicator.getLayoutParams();
        layoutParams.width = mTabWidth;
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new FragmentDefenseDemo());// 防护企业 FragmentDefense
        fragmentArrayList.add(new FragmentSuvDemo());// 监理企业 FragmentSuv
        fragmentArrayList.add(new FragmentDesignDemo());// 设计企业 FragmentDesign
        mViewPager.setAdapter(new PfassmentAdapter(getSupportFragmentManager(), fragmentArrayList));
        initListener();
    }

    public void initListener() {
        findViewById(R.id.tv_defense).setOnClickListener(this);
        findViewById(R.id.tv_supervision).setOnClickListener(this);
        findViewById(R.id.tv_design).setOnClickListener(this);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 滚动ViewPager同时滚动指示线
                scrollTabLayout(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                // 点击顶部TextView切换ViewPager
                changeViewPager(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 下载按钮的事件监听
        mLlDowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 企业名单表（所有企业）
                List<EnterpriseCreditJson> creditList = EnterpriseCreditDao.loadAll();
                // 企业名单不为空，直接下载
                if(creditList.size() > 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(PfAssessmentDemoTestActivity.this);
                    AlertDialog dialog = null;// 对话框对象

                    builder.setMessage("下载将会覆盖上次企业资料和打分数据，确认继续吗？");
                    builder.setCancelable(false);// 不能取消这个对话框，点击了对话框里的按钮会自动关闭

                    // 确定
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dowloadData();
                            EventBus.getDefault().post(new EventUtil(1));
                        }
                    });
                    // 取消
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    dialog = builder.create();
                    dialog.show();
                }else{
                    dowloadData();
                    EventBus.getDefault().post(new EventUtil(1));
                }
            }
        });

        // 上传的事件监听
        ll_update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 企业名单表（所有企业）
                List<EnterpriseCreditJson> creditList1 = EnterpriseCreditDao.loadAll();
                // 企业名单表（已考评企业）
                List<EnterpriseCreditJson> creditList2 = EnterpriseCreditDao.query2();
                if(creditList1.size() == 0){
                    ToastUtils.show("提示：企业名单是空的");
                }else{
                    // 上传考核状态为已考核的企业
                    if(creditList1.size() == creditList2.size()){
                        // 判断是否已上传（0 为上传 1 已上传）
                        if(creditList1.get(0).getStatus() == 1){
                            ToastUtils.show("提示：您已上传过所有企业考评数据，不能重复上传");
                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(PfAssessmentDemoTestActivity.this);
                            AlertDialog dialog = null;// 对话框对象

                            builder.setMessage("您确定要上传全部企业的考评数据吗");
                            builder.setCancelable(false);// 不能取消这个对话框，点击了对话框里的按钮会自动关闭

                            // 确定
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    uploadAll();
                                }
                            });
                            // 取消
                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            dialog = builder.create();
                            dialog.show();
                        }

                    }else{
                        ToastUtils.show("提示：您还有未考核完的企业，不能全部上传");
                    }
                }

            }
        });
    }

    /**
     * 用户点击，选项卡切换了
     * @param position 选中的选项卡
     */
    private void changeViewPager(int position) {
        // 没被选中的时候,显示
        mCurrentTab.setSelected(false);
        // 取消选中状态，TextView设置为原来的大小
        mCurrentTab.animate().scaleX(1f).scaleY(1f);

        // 当选中之后,默认被选中的textView修改为postion位置
        mCurrentTab = mTabs[position];
        // 被选中的选项,要修改样式
        mCurrentTab.setSelected(true);
        mCurrentTab.animate().scaleX(1.2f).scaleY(1.2f);
    }

    private void scrollTabLayout(int position, float percent) {
        // 设置指示线的位置
        int magrinLeft = (int) (mTabWidth * position + mTabWidth * percent);
        mTabIndicator.setTranslationX(magrinLeft);
    }

    /**
     * 企业类型切换栏的事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_defense:// 防护企业
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.tv_supervision:// 监理企业
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.tv_design:// 设计企业
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.ll_update_data:// 全部上传
                break;
        }
    }

    /**
     * 下载数据
     */
    private void dowloadData() {
        String name = PreferenceUtils.getString(PfAssessmentDemoTestActivity.this, "userName");// 拿到账号
        List<IntegrityUserJson> userList = IntegrityUserDao.query(name);// 根据账号，获取当前用户的数据
        InQueryMsg inQueryMsg = new InQueryMsg(1348831860, "query", userList.get(0).getPwd2());
        inQueryMsg.setQueryName("updateCredit");
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
                // 清空所有表的数据
                EnterpriseCreditDao.deleteAll();
                EvaluateAddDao.deleteAll();
                EvaluateDeductDao.deleteAll();
                EvaluateStandardDao.deleteAll();
                EvaluateTestDao.deleteAll();

                // 从后台获取到最新数据
                EvaluatePackage evaluatePackage = com.alibaba.fastjson.JSON.parseObject(response.body().string(), EvaluatePackage.class);
                // Log.d("koko",response.body().string());
                if(evaluatePackage.getEnterpriseCreditList().size() > 0){
                    List<EnterpriseCreditJson> credit = evaluatePackage.getEnterpriseCreditList();
                    List<EvaluateAddJson> add = evaluatePackage.getEvaluateAddList();
                    List<EvaluateDeductJson> deduct = evaluatePackage.getEvaluateDeductList();
                    List<EvaluateStandardJson> standard = evaluatePackage.getEvaluateStandardList();
                    List<EvaluateTestJson> test = evaluatePackage.getEvaluateTestList();

                    /*LogUtil.d("size1", credit.size()+"");
                    LogUtil.d("size2", add.size()+"");
                    LogUtil.d("size3", deduct.size()+"");
                    LogUtil.d("size4", standard.size()+"");
                    LogUtil.d("size5", test.size()+"");*/

                    // 将数据保存到本地数据库
                    EnterpriseCreditDao.insertOrReplaceInTx(credit);
                    EvaluateAddDao.insertOrReplaceInTx(add);
                    EvaluateDeductDao.insertOrReplaceInTx(deduct);
                    EvaluateStandardDao.insertOrReplaceInTx(standard);
                    EvaluateTestDao.insertOrReplaceInTx(test);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.show("下载完毕");
                        }
                    });
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.show("暂无申报企业");
                        }
                    });
                }

            }
        });
    }

    /**
     * 整体上传
     */
    private void uploadAll(){
        String name = PreferenceUtils.getString(PfAssessmentDemoTestActivity.this, "userName");// 拿到账号
        List<IntegrityUserJson> userList = IntegrityUserDao.query(name);// 根据账号，获取当前用户的数据
        InSaveMsg inSaveMsg = new InSaveMsg(1348831860, "save");
        inSaveMsg.setModelName("EnterpriseCreditSave");
        inSaveMsg.setKey(userList.get(0).getPwd2());// key
        // 判断所有企业都已考评后，才能将所有数据全部上传
        List<EvaluateTestJson> testList = EvaluateTestDao.loadAll();
        List<EnterpriseCreditJson> creditList = EnterpriseCreditDao.loadAll();
        inSaveMsg.setTestListProperty(testList);// 打分表
        inSaveMsg.setCreditListProperty(creditList);// 企业名单

        String postData = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            postData = mapper.writeValueAsString(inSaveMsg);
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.show("上传成功");
                        // 更改上传状态（0 未上传 1 已上传）
                        List<EnterpriseCreditJson> testList = EnterpriseCreditDao.loadAll();

                        for(int i = 0;i < testList.size();i++){
                            EnterpriseCreditJson creditJson = new EnterpriseCreditJson();
                            creditJson.setId(testList.get(i).getId());
                            creditJson.setOid(testList.get(i).getOid());
                            creditJson.setEid(testList.get(i).getEid());
                            creditJson.setUnit_name(testList.get(i).getUnit_name());
                            creditJson.setSubmit_date(testList.get(i).getSubmit_date());
                            creditJson.setStatus(1);
                            creditJson.setType(testList.get(i).getType());
                            creditJson.setCid(testList.get(i).getCid());
                            creditJson.setCtstatus(testList.get(i).getCtstatus());
                            creditJson.setQrank(testList.get(i).getQrank());
                            creditJson.setTotal(testList.get(i).getTotal());
                            creditJson.setAnnual(testList.get(i).getAnnual());
                            EnterpriseCreditDao.update(creditJson);
                        }
                    }
                });
            }

        });
    }

}
