package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.base.BaseModuleActivity;

import butterknife.BindView;

/**
 * 经营管理（3.1.7）
 */
public class Description317 extends BaseModuleActivity {
    @BindView(R.id.rb_yes3_1_7)
	RadioButton mRbYes317;// 符合
    @BindView(R.id.rb_no3_1_7)
	RadioButton mRbNo317;// 不符合
    @BindView(R.id.cb1_condition3_1_7)
	CheckBox mCb1Condition317;// 缺使用说明
    @BindView(R.id.cb2_condition3_1_7)
	CheckBox mCb2Condition317;// 缺检验报告

    @BindView(R.id.tv_value3_1_7)
	TextView mTvValue317;// 分值
    @BindView(R.id.tv_score3_1_7)
	TextView mTvScore317;// 得分
    @BindView(R.id.et_remark3_1_7)
	EditText mEtRemark317;// 备注
    @BindView(R.id.tv_rule3_1_7)
	TextView mTvRule317;// 扣分规则

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_description17;
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
        mTvValue317.setText("1.5");// 分值

        // 符合 不符合
        mRbYes317.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){// 1 符合
                    mCb1Condition317.setEnabled(false);
                    mCb2Condition317.setEnabled(false);

                    mCb1Condition317.setChecked(false);
                    mCb2Condition317.setChecked(false);
                    mTvScore317.setText("1.5");
                }else{// 2 不符合
                    mCb1Condition317.setEnabled(true);
                    mCb2Condition317.setEnabled(true);

                    mCb1Condition317.setChecked(true);
                    mCb2Condition317.setChecked(true);
                    mTvScore317.setText("0");
                }
            }
        });

        // 缺使用说明
        mCb1Condition317.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){// 1 缺使用说明
                    if(mCb2Condition317.isChecked()){// 缺检验报告
                        mTvScore317.setText("0");
                    }else{
                        mTvScore317.setText("0.5");
                    }
                }else{// 2
                    if(mCb2Condition317.isChecked()){// 缺检验报告
                        mTvScore317.setText("1.0");
                    }else{
                        mTvScore317.setText("1.5");
                    }
                }
            }
        });

        // 缺检验报告
        mCb2Condition317.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){// 1 缺检验报告
                    if(mCb1Condition317.isChecked()){// 缺使用说明
                        mTvScore317.setText("0");
                    }else{
                        mTvScore317.setText("1.0");
                    }
                }else{// 2
                    if(mCb1Condition317.isChecked()){// 缺使用说明
                        mTvScore317.setText("0.5");
                    }else{
                        mTvScore317.setText("1.5");
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
