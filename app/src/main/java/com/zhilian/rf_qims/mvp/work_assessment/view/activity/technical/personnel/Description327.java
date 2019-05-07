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
 * 产品质量（3.2.7）
 */
public class Description327 extends BaseModuleActivity {
    @BindView(R.id.rb_yes3_2_7)
	RadioButton mRbYes327;// 符合
    @BindView(R.id.rb_no3_2_7)
	RadioButton mRbNo327;// 不符合
    @BindView(R.id.cb1_condition3_2_7)
	CheckBox mCb1Condition327;// 缺活门合格证
    @BindView(R.id.cb2_condition3_2_7)
	CheckBox mCb2Condition327;// 缺阀门合格证
    @BindView(R.id.cb3_condition3_2_7)
	CheckBox mCb3Condition327;// 缺风管材质合格证

    @BindView(R.id.tv_value3_2_7)
	TextView mTvValue327;// 分值
    @BindView(R.id.tv_score3_2_7)
	TextView mTvScore327;// 得分
    @BindView(R.id.et_remark3_2_7)
	EditText mEtRemark327;// 备注
    @BindView(R.id.tv_rule3_2_7)
	TextView mTvRule327;// 扣分规则

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_description22;
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
        mTvValue327.setText("0.5");// 分值

        // 符合 不符合
        mRbYes327.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){// 1 符合
                    mCb1Condition327.setEnabled(false);
                    mCb2Condition327.setEnabled(false);
                    mCb3Condition327.setEnabled(false);

                    mCb1Condition327.setChecked(false);
                    mCb2Condition327.setChecked(false);
                    mCb3Condition327.setChecked(false);

                    mTvScore327.setText("0.5");
                }else{// 不符合
                    mCb1Condition327.setEnabled(true);
                    mCb2Condition327.setEnabled(true);
                    mCb3Condition327.setEnabled(true);

                    mCb1Condition327.setChecked(true);
                    mCb2Condition327.setChecked(true);
                    mCb3Condition327.setChecked(true);

                    mTvScore327.setText("0");
                }
            }
        });

        // 缺活门合格证
        mCb1Condition327.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){// 1 缺活门合格证
                    if(mCb2Condition327.isChecked()){// 缺阀门合格证
                        if(mCb3Condition327.isChecked()){// 缺风管材质合格证
                            mTvScore327.setText("0");
                        }else{
                            mTvScore327.setText("0.1");
                        }
                    }else{
                        if(mCb3Condition327.isChecked()){// 缺风管材质合格证
                            mTvScore327.setText("0.1");
                        }else{
                            mTvScore327.setText("0.3");
                        }
                    }
                }else{// 2
                    if(mCb2Condition327.isChecked()){// 缺阀门合格证
                        if(mCb3Condition327.isChecked()){// 缺风管材质合格证
                            mTvScore327.setText("0.1");
                        }else{
                            mTvScore327.setText("0.3");
                        }
                    }else{
                        if(mCb3Condition327.isChecked()){// 缺风管材质合格证
                            mTvScore327.setText("0.3");
                        }else{
                            mTvScore327.setText("0.5");
                        }
                    }
                }
            }
        });

        // 缺阀门合格证
        mCb2Condition327.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){// 1 缺阀门合格证
                    if(mCb1Condition327.isChecked()){// 缺活门合格证
                        if(mCb3Condition327.isChecked()){// 缺风管材质合格证
                            mTvScore327.setText("0");
                        }else{
                            mTvScore327.setText("0.1");
                        }
                    }else{
                        if(mCb3Condition327.isChecked()){// 缺风管材质合格证
                            mTvScore327.setText("0.1");
                        }else{
                            mTvScore327.setText("0.3");
                        }
                    }
                }else{// 2
                    if(mCb1Condition327.isChecked()){// 缺活门合格证
                        if(mCb3Condition327.isChecked()){// 缺风管材质合格证
                            mTvScore327.setText("0.1");
                        }else{
                            mTvScore327.setText("0.3");
                        }
                    }else{
                        if(mCb3Condition327.isChecked()){// 缺风管材质合格证
                            mTvScore327.setText("0.3");
                        }else{
                            mTvScore327.setText("0.5");
                        }
                    }
                }
            }
        });

        // 缺风管材质合格证
        mCb3Condition327.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){// 1 缺风管材质合格证
                    if(mCb1Condition327.isChecked()){// 缺活门合格证
                        if(mCb2Condition327.isChecked()){// 缺阀门合格证
                            mTvScore327.setText("0");
                        }else{
                            mTvScore327.setText("0.1");
                        }
                    }else{
                        if(mCb2Condition327.isChecked()){// 缺阀门合格证
                            mTvScore327.setText("0.1");
                        }else{
                            mTvScore327.setText("0.3");
                        }
                    }
                }else{// 2
                    if(mCb1Condition327.isChecked()){// 缺活门合格证
                        if(mCb2Condition327.isChecked()){// 缺阀门合格证
                            mTvScore327.setText("0.1");
                        }else{
                            mTvScore327.setText("0.3");
                        }
                    }else{
                        if(mCb2Condition327.isChecked()){// 缺阀门合格证
                            mTvScore327.setText("0.3");
                        }else{
                            mTvScore327.setText("0.5");
                        }
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
