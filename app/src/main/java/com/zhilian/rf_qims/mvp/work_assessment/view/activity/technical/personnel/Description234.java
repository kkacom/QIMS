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
 * 管理制度（2.3.4）
 */
public class Description234 extends BaseModuleActivity {
    @BindView(R.id.rb_yes2_3_4)
	RadioButton mRbYes234;// 齐全
    @BindView(R.id.rb_no2_3_4)
	RadioButton mRbNo234;// 制度切实可行，无措施
    @BindView(R.id.rb_not2_3_4)
	RadioButton mRbNot234;// 无

    @BindView(R.id.tv_value2_3_4)
	TextView mTvValue234;// 分值
    @BindView(R.id.tv_score2_3_4)
	TextView mTvScore234;// 得分
    @BindView(R.id.et_remark2_3_4)
	EditText mEtRemark234;// 备注
    @BindView(R.id.tv_rule2_3_4)
	TextView mTvRule234;// 扣分规则

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_description3;
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
        mTvValue234.setText("0.5");

        // 齐全
        mRbYes234.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mTvScore234.setText("0.5");
                }
            }
        });

        // 制度切实可行，无措施
        mRbNo234.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mTvScore234.setText("0.3");
                }
            }
        });

        // 无
        mRbNot234.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mTvScore234.setText("0");
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
