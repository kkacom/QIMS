package com.zhilian.rf_qims.mvp.sample_data;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.colin.utils.StrKit;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.adapter.FragAdapter;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.dao.SampleCheckDao;
import com.zhilian.rf_qims.dao.SampleDao;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.entity.SampleCheck;
import com.zhilian.rf_qims.mvp.SamplePic.SamplePicActivity;
import com.zhilian.rf_qims.mvp.office.SignActivity;
import com.zhilian.rf_qims.mvp.sample_data.info.InfoFragment;
import com.zhilian.rf_qims.mvp.sample_data.ins_quality.InsQualityFragment;
import com.zhilian.rf_qims.mvp.sample_data.ins_size.InsSizeFragment;
import com.zhilian.rf_qims.mvp.sample_data.pro_quality.ProQualityFragment;
import com.zhilian.rf_qims.mvp.sample_data.pro_size.ProSizeFragment;
import com.zhilian.rf_qims.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by colin on 2018/3/12 15:50 .
 */

public class DataActivity extends AppCompatActivity {
    ProSizeFragment proSizeFragment;
    InsSizeFragment insSizeFragment;
    ProQualityFragment proQualityFragment;
    InsQualityFragment insQualityFragment;
    InfoFragment infoFragment;
    FragAdapter fragAdapter;
    List<Fragment> fragments;
    NoScrollViewPager viewPager;
    TabLayout tabLayout;
    private TextView tvSampleMode;
    private TextView tvSampleNo;
    private ImageView ivTakePic;
    private ImageView ivSign;
    Sample sample,mSample;
    SampleCheck SampleCheck;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_data);
        initView();
    }

    private void initView() {
        sample = (Sample) getIntent().getSerializableExtra("sample");
        /*Common这个新类是20180830下午新增。解决门数据无法一次性录入只能一个页面录，原因是每个Fragment各独自获取变量，
        无法全页面同步数据，导致下一页数据覆盖上一页数据，或因为setUserVisibleHint可视懒加载问题错乱覆盖*/
        Common common = new Common();
        SampleCheck = GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().queryBuilder().where(
                        SampleCheckDao.Properties.Id.eq(sample.getId())).unique();
        mSample = GreenDaoManager.getInstance().getNewSession().getSampleDao().queryBuilder().where(
                SampleDao.Properties.Id.eq(sample.getId())).unique();
        common.setSampleCheck(SampleCheck);
        common.setSample(mSample);
        tvSampleMode = findViewById(R.id.tv_samplemodel);
        tvSampleNo = findViewById(R.id.tv_sampleno);
        tvSampleMode.setText("样品型号 : " + (StrKit.notBlank(sample.getSampleModel()) ? sample.getSampleModel() : ""));
        tvSampleNo.setText("样品编号 : " + sample.getSampleNo());
        fragments = new ArrayList<>();
        infoFragment = new InfoFragment();
        proSizeFragment = new ProSizeFragment();
        insSizeFragment = new InsSizeFragment();
        proQualityFragment = new ProQualityFragment();
        insQualityFragment = new InsQualityFragment();
        fragments.add(infoFragment);
        fragments.add(proSizeFragment);
        fragments.add(insSizeFragment);
        fragments.add(proQualityFragment);
        fragments.add(insQualityFragment);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setNoScroll(true);
        fragAdapter = new FragAdapter(getSupportFragmentManager(), fragments);
        initTabAndPager();
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0x12);
                finish();
            }
        });
        ivTakePic = findViewById(R.id.iv_takepic);
        ivTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataActivity.this, SamplePicActivity.class);
                intent.putExtra("sample", sample);
                startActivity(intent);
            }
        });
        ivSign=findViewById(R.id.iv_sign);
        ivSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataActivity.this, SignActivity.class);
                intent.putExtra("sample", sample);
                startActivity(intent);
            }
        });
    }

    private String[] titles = {"样品信息", "生产尺寸", "装配尺寸", "生产质量", "安装质量"};

    //把5个主页装进fragment管理适配填充数据
    private void initTabAndPager() {
        viewPager.setAdapter(fragAdapter);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < fragAdapter.getCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);//获得每一个tab
            tab.setCustomView(R.layout.tab_item);//给每一个tab设置view
            if (i == 0) {
                // 设置第一个tab的TextView是被选择的样式
                tab.getCustomView().findViewById(R.id.tab_root).setSelected(true);//第一个tab被选中
            }
            TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
            textView.setText(titles[i]);//设置tab上的文字
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tab_root).setSelected(true);
                viewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tab_root).setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
