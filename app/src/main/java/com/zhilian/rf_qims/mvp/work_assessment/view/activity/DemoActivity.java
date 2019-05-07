package com.zhilian.rf_qims.mvp.work_assessment.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.util.ToastUtils;


public class DemoActivity extends AppCompatActivity {

    private SliderLayout mSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        mSlider = (SliderLayout) findViewById(R.id.sliderlayout);
        initView();

    }

    private void initView() {
        TextSliderView textSliderView1 = new TextSliderView(this);
        textSliderView1.description("闪电").image(R.drawable.lunbo1);

        TextSliderView textSliderView2 = new TextSliderView(this);
        textSliderView2.description("夜晚").image(R.drawable.lunbo2);

        TextSliderView textSliderView3 = new TextSliderView(this);
        textSliderView3.description("月亮").image(R.drawable.lunbo3);

        mSlider.addSlider(textSliderView1);
        mSlider.addSlider(textSliderView2);
        mSlider.addSlider(textSliderView3);

        textSliderView1.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                ToastUtils.show("闪电");
            }
        });
        //使用默认的轮播指示器
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSlider.setDuration(2000);//停留时间

        //设置AndroidImageslider监听
        mSlider.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
