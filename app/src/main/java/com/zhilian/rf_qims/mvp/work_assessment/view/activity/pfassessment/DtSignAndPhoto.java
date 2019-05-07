package com.zhilian.rf_qims.mvp.work_assessment.view.activity.pfassessment;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.MyFragmentAdapter;
import com.zhilian.rf_qims.mvp.work_assessment.view.fragment.sign.SgAndPtFragmentFive;
import com.zhilian.rf_qims.mvp.work_assessment.view.fragment.sign.SgAndPtFragmentFour;
import com.zhilian.rf_qims.mvp.work_assessment.view.fragment.sign.SgAndPtFragmentOne;
import com.zhilian.rf_qims.mvp.work_assessment.view.fragment.sign.SgAndPtFragmentSix;
import com.zhilian.rf_qims.mvp.work_assessment.view.fragment.sign.SgAndPtFragmentThree;
import com.zhilian.rf_qims.mvp.work_assessment.view.fragment.sign.SgAndPtFragmentTwo;
import com.zhilian.rf_qims.widget.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 现场签名
 */
public class DtSignAndPhoto extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.tv_work_one)
	TextView mTvWorkOne;
    @BindView(R.id.tv_work_two)
	TextView mTvWorkTwo;
    @BindView(R.id.tv_pro_one)
	TextView mTvProOne;
    @BindView(R.id.tv_pro_two)
	TextView mTvProTwo;
    @BindView(R.id.tv_install_one)
	TextView mTvInstallOne;
    @BindView(R.id.tv_install_two)
	TextView mTvInstallTwo;
    @BindView(R.id.sign_viewpager)
    NoScrollViewPager mSignViewpager;
    /**
     * 选项卡
     */
    private TextView[] mTabs = new TextView[6];

    /**
     * 当前选中的选项卡
     */
    private TextView mCurrentTab;

    ArrayList<Fragment> mFragmentList = new ArrayList<>();
    MyFragmentAdapter mMyFragmentAdapter;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//强制为横屏
        setContentView(R.layout.activity_dt_sign_and_photo_demo);
        ButterKnife.bind(this);
        initView();
        //初始化Fragment
        InitFragment();

        //初始化ViewPager
        InitViewPager();
    }

    private void initView() {

        //用TextView来取代TabLayout的作用
        mTabs[0] = mTvWorkOne;
        mTabs[1] = mTvWorkTwo;
        mTabs[2] = mTvProOne;
        mTabs[3] = mTvProTwo;
        mTabs[4] = mTvInstallOne;
        mTabs[5] = mTvInstallTwo;

        //默认选中第一个选项
        mCurrentTab = mTabs[0];

        mCurrentTab.setSelected(true);
        //选中的时候,textView变大,颜色变绿色
        mCurrentTab.setScaleX(1.2f);
        mCurrentTab.setScaleY(1.2f);
        //添加点击事件

        mSignViewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    private void InitViewPager() {
        mMyFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), mFragmentList);
        mSignViewpager.setAdapter(mMyFragmentAdapter);
        initListener();
    }

    private void initListener() {
        mTvWorkOne.setOnClickListener(this);
        mTvWorkTwo.setOnClickListener(this);
        mTvProOne.setOnClickListener(this);
        mTvProTwo.setOnClickListener(this);
        mTvInstallOne.setOnClickListener(this);
        mTvInstallTwo.setOnClickListener(this);

        mSignViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //点击顶部TextView切换ViewPager
                changeViewPager(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 用户点击，选项卡切换了
     *
     * @param position 选中的选项卡
     */
    private void changeViewPager(int position) {
        //没被选中的时候,显示
        mCurrentTab.setSelected(false);
        // 取消选中状态，TextView设置为原来的大小
        mCurrentTab.animate().scaleX(1f).scaleY(1f);

        //当选中之后,默认被选中的textView修改为postion位置
        mCurrentTab = mTabs[position];
        //被选中的选项,要修改样式
        mCurrentTab.setSelected(true);
        mCurrentTab.animate().scaleX(1.2f).scaleY(1.2f);


    }

    private void InitFragment() {
        mFragmentList.add(new SgAndPtFragmentOne());
        mFragmentList.add(new SgAndPtFragmentTwo());
        mFragmentList.add(new SgAndPtFragmentThree());
        mFragmentList.add(new SgAndPtFragmentFour());
        mFragmentList.add(new SgAndPtFragmentFive());
        mFragmentList.add(new SgAndPtFragmentSix());
        //让ViewPager缓存2个页面
        mSignViewpager.setOffscreenPageLimit(2);
        //设置默认打开第一页
        mSignViewpager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_work_one:
                mSignViewpager.setCurrentItem(0, false);
                break;
            case R.id.tv_work_two:
                mSignViewpager.setCurrentItem(1, false);
                break;
            case R.id.tv_pro_one:
                mSignViewpager.setCurrentItem(2, false);
                break;
            case R.id.tv_pro_two:
                mSignViewpager.setCurrentItem(3, false);
                break;
            case R.id.tv_install_one:
                mSignViewpager.setCurrentItem(4, false);
                break;
            case R.id.tv_install_two:
                mSignViewpager.setCurrentItem(5, false);
                break;
        }
    }
   /* *//**
     * 头标点击监听
     * @author weizhi
     * @version 1.0
     *//*
    public class MyOnClickListener implements View.OnClickListener{
        private int index = 0 ;
        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mSignViewpager.setCurrentItem(index);
        }
    }*/


}
