package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.base.BaseModuleActivity;

import butterknife.BindView;

/**
 * 经营管理（3.1.6）
 */
public class Description316 extends BaseModuleActivity {
    @BindView(R.id.rb_yes3_1_6)
	RadioButton mRbYes316;// 符合
    @BindView(R.id.rb_no3_1_6)
	RadioButton mRbNo316;// 不符合

    @BindView(R.id.tv_value3_1_6)
	TextView mTvValue316;// 分值
    @BindView(R.id.tv_score3_1_6)
	TextView mTvScore316;// 得分
    @BindView(R.id.et_remark3_1_6)
	EditText mEtRemark316;// 备注
    @BindView(R.id.tv_rule3_1_6)
	TextView mTvRule316;// 扣分规则

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_description16;
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
        mTvValue316.setText("1.5");// 分值

        // 符合 不符合
        mRbYes316.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){// 符合
                    mTvScore316.setText("1.5");
                }else{// 不符合
                    mTvScore316.setText("0");
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
