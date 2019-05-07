package com.zhilian.rf_qims.mvp.sample_data.pro_quality;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.adapter.FragAdapter;
import com.zhilian.rf_qims.widget.LazyLoadFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by colin on 2018/3/23 11:46 .
 */

public class ProQualityFragment extends LazyLoadFragment {
    ProQualityGBHD proQualityGBHD;
    ProQualityGJFB proQualityGJFB;
    ProQualityHLTQD proQualityHLTQD;
    ProQualityJGZL proQualityJGZL;
    ProQualityQMZL proQualityQMZL;
    ProQualityXBM proQualityXBM;
    FragAdapter fragAdapter;
    List<Fragment> fragments;
    ViewPager viewPager;
    @BindView(R.id.tv_page_title)
    TextView tvPageTitle;
    @BindView(R.id.tv_page_before)
    TextView tvPageBefore;
    @BindView(R.id.tv_page_curpage)
    TextView tvPageCurpage;
    @BindView(R.id.tv_page_next)
    TextView tvPageNext;
    Unbinder unbinder;
    int mcurrentpos=0;
    @Override
    protected int setContentView() {
        return R.layout.fragment_prosize;
    }

    @Override
    protected void lazyLoad() {
        unbinder = ButterKnife.bind(this, view);
        fragments = new ArrayList<>();
        proQualityGBHD = new ProQualityGBHD();
        proQualityQMZL = new ProQualityQMZL();
        proQualityJGZL = new ProQualityJGZL();
        proQualityHLTQD = new ProQualityHLTQD();
        proQualityGJFB = new ProQualityGJFB();
        proQualityXBM = new ProQualityXBM();
        fragments.add(proQualityGBHD);
        fragments.add(proQualityQMZL);
        fragments.add(proQualityJGZL);
        fragments.add(proQualityHLTQD);
        fragments.add(proQualityGJFB);
        fragments.add(proQualityXBM);
        viewPager = findViewById(R.id.viewpager);
        fragAdapter = new FragAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(fragAdapter);
        viewPager.setCurrentItem(0, false);
        tvPageCurpage.setText((mcurrentpos+1)+"/"+fragments.size());
        tvPageTitle.setText("生产质量-钢板厚度");
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mcurrentpos=position;
                switch (position){
                    case 0:
                        tvPageTitle.setText("生产质量-钢板厚度");
                        break;
                    case 1:
                        tvPageTitle.setText("生产质量-漆膜质量");
                        break;
                    case 2:
                        tvPageTitle.setText("生产质量-加工质量");
                        break;
                    case 3:
                        tvPageTitle.setText("生产质量-混凝土强度");
                        break;
                    case 4:
                        tvPageTitle.setText("生产质量-钢筋分布");
                        break;
                    case 5:
                        tvPageTitle.setText("生产质量-悬摆门");
                        break;
                    default:
                        break;
                }
                tvPageCurpage.setText((mcurrentpos+1)+"/"+fragments.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @OnClick({R.id.tv_page_before, R.id.tv_page_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_page_before:
                if (mcurrentpos==0){
                    return;
                }else {
                    mcurrentpos--;
                    viewPager.setCurrentItem(mcurrentpos,false);
                }
                break;
            case R.id.tv_page_next:
                if (mcurrentpos==fragments.size()-1){
                    return;
                }else {
                    mcurrentpos++;
                    viewPager.setCurrentItem(mcurrentpos,false);
                }
                break;
        }
    }
}