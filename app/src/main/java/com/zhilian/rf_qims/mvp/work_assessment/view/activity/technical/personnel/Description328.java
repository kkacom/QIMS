package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.base.BaseModuleActivity;

import butterknife.BindView;

/**
 * 产品质量（3.2.8）
 */
public class Description328 extends BaseModuleActivity {
    @BindView(R.id.rb1_yes3_2_8)
	RadioButton mRb1Yes328;
    @BindView(R.id.rb1_no3_2_8)
	RadioButton mRb1No328;
    @BindView(R.id.rb2_yes3_2_8)
	RadioButton mRb2Yes328;
    @BindView(R.id.rb2_no3_2_8)
	RadioButton mRb2No328;

    @BindView(R.id.tv_value3_2_8)
	TextView mTvValue328;// 分值
    @BindView(R.id.tv_score3_2_8)
	TextView mTvScore328;// 得分
    @BindView(R.id.tv_rule3_2_8)
	TextView mTvRule328;// 扣分规则
    @BindView(R.id.et_remark3_2_8)
	TextView mEtRemark328;// 备注

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_description23;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void saveStatus(String status) {

    }

    /**
     * 事件监听
     */
    @Override
    protected void listener() {
        photo.setVisibility(View.GONE);
        mTvValue328.setText("0.5");// 分值

        // 1 符合 不符合
        mRb1Yes328.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){// 1 符合
                    if(mRb2Yes328.isChecked()){// 2 符合
                        mTvScore328.setText("0.5");
                    }else{// 2 不符合
                        mTvScore328.setText("0.25");
                    }
                }else{// 1 不符合
                    if(mRb2Yes328.isChecked()){// 2 符合
                        mTvScore328.setText("0.25");
                    }else{// 2 不符合
                        mTvScore328.setText("0");
                    }
                }
            }
        });

        // 2 符合 不符合
        mRb2Yes328.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){// 2 符合
                    if(mRb1Yes328.isChecked()){// 1 符合
                        mTvScore328.setText("0.5");
                    }else{// 1 不符合
                        mTvScore328.setText("0.25");
                    }
                }else{// 2 不符合
                    if(mRb1Yes328.isChecked()){// 1 符合
                        mTvScore328.setText("0.25");
                    }else{// 1 不符合
                        mTvScore328.setText("0");
                    }
                }
            }
        });
    }

    @Override
    protected void photo() {

    }

    @Override
    protected void video() {

    }

}
