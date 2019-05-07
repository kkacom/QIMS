package com.zhilian.rf_qims.mvp.work_assessment.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.base.MyBaseFragment;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.MainActivity;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.pfassessment.PfAssessmentDemoTestActivity;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessment.WorkAssessmentFieldActivity;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.workbench.GridViewAdapter;
import com.zhilian.rf_qims.util.ToastUtils;
import com.zhilian.rf_qims.widget.MyGridView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 工作台
 * Created by luocong on 2017/3/27.
 */
public class WorkbenchFragment extends MyBaseFragment {
    /*@BindView(R.id.product_test)
    LinearLayout mProductTest;
    @BindView(R.id.install_test)
    LinearLayout mInstallTest;
    @BindView(R.id.professional_assessment)
    LinearLayout mProfessionalAssessment;
    @BindView(R.id.integrity_assessment)
    LinearLayout mIntegrityAssessment;
    @BindView(R.id.feedback)
    LinearLayout mFeedback;
    @BindView(R.id.view2)
    View mView2;
    @BindView(R.id.imageView2)
    ImageView mImageView2;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.imageView)
    ImageView mImageView;*/

    @BindView(R.id.sliderlayout)
    SliderLayout mSliderlayout;
    @BindView(R.id.gridview_one)
    MyGridView mGridviewOne;
    @BindView(R.id.integrity)
	RelativeLayout mIntegrity;
   /* @BindView(R.id.gridview_two)
    MyGridView mGridviewTwo;*/
    Unbinder unbinder1;

    static final String[] GV_First_Name = {"现场监督", "工程巡检","RFID扫描","数据同步","诚信评估","从业评估","" +
            "网签合同","施工管理","个人网盘","加密网盾","GIS地图","现场录像"};

    static final Integer[] GV_First_PT = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d
            ,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j
            ,R.drawable.k,R.drawable.l};
   /* static final String[] GV_SECOND_NAME = {"从业评估", "诚信记录"};
    static final Integer[] GV_SECOND_PT = {R.drawable.pd_test,R.drawable.credit_record};*/

    GridViewAdapter mGridViewAdapter;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_workbench;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this, mRoot);
        imageSlider();
        initAdapter();
    }

    private void initAdapter() {
        List<String> gvFirstName = Arrays.asList(GV_First_Name);
        List<Integer> gvFirstPt = Arrays.asList(GV_First_PT);
        mGridViewAdapter = new GridViewAdapter(mActivity,gvFirstName,gvFirstPt);
        mGridviewOne.setAdapter(mGridViewAdapter);
    }

    /**
     * 设置轮播图的图片和文字
     */
    private void imageSlider() {
        TextSliderView textSliderView1 = new TextSliderView(mActivity);
        textSliderView1.description("湖南省人防开启座谈会").image(R.drawable.lunbo1);

        TextSliderView textSliderView2 = new TextSliderView(mActivity);
        textSliderView2.description("诚信规章制度").image(R.drawable.lunbo2);

        TextSliderView textSliderView3 = new TextSliderView(mActivity);
        textSliderView3.description("人防宣传").image(R.drawable.lunbo3);

        TextSliderView textSliderView4 = new TextSliderView(mActivity);
        textSliderView4.description("诚信通").image(R.drawable.lunbo4);

        TextSliderView textSliderView5 = new TextSliderView(mActivity);
        textSliderView5.description("人防历史").image(R.drawable.lunbo5);

        mSliderlayout.addSlider(textSliderView1);
        mSliderlayout.addSlider(textSliderView2);
        mSliderlayout.addSlider(textSliderView3);
        mSliderlayout.addSlider(textSliderView4);
        mSliderlayout.addSlider(textSliderView5);
        textSliderView1.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                //ToastUtils.show("待开发");
            }
        });

        // 使用默认的轮播指示器
        mSliderlayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSliderlayout.setDuration(2000);// 停留时间

        // 设置AndroidImageslider监听
        mSliderlayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
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

    @Override
    public void initListener() {
        // 模块的点击事件
        mGridviewOne.setOnItemClickListener(mOnItemClickListener);
        // 添加应用
        mIntegrity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("暂无应用添加");
            }
        });
    }

    /**
     * 模块的的点击事件监听
     */
    AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent;
            switch (position) {
                case 4:// 诚信评估
                    intent = new Intent(mActivity,PfAssessmentDemoTestActivity.class);
                    startActivity(intent);
                    break;
                case 5:// 从业评估
                    intent = new Intent(mActivity, WorkAssessmentFieldActivity.class);
                    startActivity(intent);
                    break;
                default:
                    ToastUtils.show("未开通权限");
            }
        }
    };

    @Override
    public void initData() {
        mActivity = (MainActivity) this.getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
