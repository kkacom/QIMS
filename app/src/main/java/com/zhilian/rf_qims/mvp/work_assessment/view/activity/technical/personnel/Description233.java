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
 * 管理制度（2.3.3）
 */
public class Description233 extends BaseModuleActivity {
    @BindView(R.id.rb_yes2_3_3)
	RadioButton mRbYes233;// 齐全
    @BindView(R.id.rb_no2_3_3)
	RadioButton mRbNo233;// 不齐全
    @BindView(R.id.rb_not2_3_3)
	RadioButton mRbNot233;// 无

    @BindView(R.id.tv_value2_3_3)
	TextView mTvValue233;// 分值
    @BindView(R.id.tv_score2_3_3)
	TextView mTvScore233;// 得分
    @BindView(R.id.et_remark2_3_3)
	EditText mEtRemark233;// 备注
    @BindView(R.id.tv_rule2_3_3)
	TextView mTvRule233;// 扣分规则

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_description2;
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
        mTvValue233.setText("0.5");// 分值

        // 齐全
        mRbYes233.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mTvScore233.setText("0.5");
                }
            }
        });

        // 不齐全
        mRbNo233.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mTvScore233.setText("0.2");
                }
            }
        });

        // 无
        mRbNot233.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mTvScore233.setText("0");
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
