package com.zhilian.rf_qims.mvp.sample_data.ins_size;

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

public class InsSizeFragment extends LazyLoadFragment {
    InsSizePmd insSizePmd;
    InsSizeZjgc insSizeZjgc;
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
    int mcurrentpos = 0;

    @Override
    protected int setContentView() {
        return R.layout.fragment_prosize;
    }

    @Override
    protected void lazyLoad() {
        unbinder = ButterKnife.bind(this, view);
        fragments = new ArrayList<>();
        insSizeZjgc = new InsSizeZjgc();
        insSizePmd = new InsSizePmd();
        fragments.add(insSizeZjgc);
        fragments.add(insSizePmd);
        viewPager = findViewById(R.id.viewpager);
        fragAdapter = new FragAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(fragAdapter);
        viewPager.setCurrentItem(0, false);
        tvPageCurpage.setText((mcurrentpos+1)+"/"+fragments.size());
        tvPageTitle.setText("装配尺寸-直径公差");
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mcurrentpos = position;
                switch (position) {
                    case 0:
                        tvPageTitle.setText("装配尺寸-直径公差");
                        break;
                    case 1:
                        tvPageTitle.setText("装配尺寸-平面度");
                        break;
                    default:
                        break;
                }
                tvPageCurpage.setText((mcurrentpos + 1) + "/" + fragments.size());
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
                if (mcurrentpos == 0) {
                    return;
                } else {
                    mcurrentpos--;
                    viewPager.setCurrentItem(mcurrentpos, false);
                }
                break;
            case R.id.tv_page_next:
                if (mcurrentpos == fragments.size() - 1) {
                    return;
                } else {
                    mcurrentpos++;
                    viewPager.setCurrentItem(mcurrentpos, false);
                }
                break;
        }
    }
}