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
 * 管理制度（2.3.5）
 */
public class Description235 extends BaseModuleActivity {
    @BindView(R.id.rb_yes2_3_5)
	RadioButton mRbYes235;// 齐全
    @BindView(R.id.rb_no2_3_5)
	RadioButton mRbNo235;// 有专门的安全制度，未挂墙
    @BindView(R.id.rb_not2_3_5)
	RadioButton mRbNot235;// 无

    @BindView(R.id.tv_value2_3_5)
	TextView mTvValue235;// 分值
    @BindView(R.id.tv_score2_3_5)
	TextView mTvScore235;// 得分
    @BindView(R.id.et_remark2_3_5)
	EditText mEtRemark235;// 备注
    @BindView(R.id.tv_rule2_3_5)
	TextView mTvRule235;// 扣分规则

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_description4;
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
        mTvValue235.setText("1.0");// 分值

        // 齐全
        mRbYes235.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mTvScore235.setText("1.0");
                }
            }
        });

        // 有专门的安全制度，未挂墙
        mRbNo235.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mTvScore235.setText("0.5");
                }
            }
        });

        // 无
        mRbNot235.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mTvScore235.setText("0");
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
