package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.base.BaseModuleActivity;

import butterknife.BindView;

/**
 * 产品质量（3.2.6）
 */
public class Description326 extends BaseModuleActivity {
    @BindView(R.id.rb1_yes3_2_6)
	RadioButton mRb1Yes326;// 符合1
    @BindView(R.id.rb1_no3_2_6)
	RadioButton mRb1No326;// 不符合1
    @BindView(R.id.cb1_condition1_3_2)
	CheckBox mCb1Condition132;// 保护层1
    @BindView(R.id.cb2_condition3_2_6)
	CheckBox mCb2Condition326;// 布筋位置1
    @BindView(R.id.rb2_yes3_2_6)
	RadioButton mRb2Yes326;// 符合2
    @BindView(R.id.rb2_no3_2_6)
	RadioButton mRb2No326;// 不符合2
    @BindView(R.id.cb3_condition3_2_6)
	CheckBox mCb3Condition326;// 保护层2
    @BindView(R.id.cb4_condition3_2_6)
	CheckBox mCb4Condition326;// 布筋位置2

    @BindView(R.id.tv_value3_2_6)
	TextView mTvValue326;// 分值
    @BindView(R.id.tv_score3_2_6)
	TextView mTvScore326;// 得分
    @BindView(R.id.tv_rule3_2_6)
	TextView mTvRule326;// 扣分规则
    @BindView(R.id.et_remark3_2_6)
	TextView mEtRemark326;// 备注

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_description21;
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
        mTvValue326.setText("0.5");// 分值

        // 1 符合 不符合
        mRb1Yes326.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){// 1 符合
                    if(mRb2Yes326.isChecked()){// 2 符合
                        mCb1Condition132.setEnabled(false);// 1
                        mCb2Condition326.setEnabled(false);
                        mCb1Condition132.setChecked(false);
                        mCb2Condition326.setChecked(false);

                        mCb3Condition326.setEnabled(false);// 2
                        mCb4Condition326.setEnabled(false);
                        mCb3Condition326.setChecked(false);
                        mCb4Condition326.setChecked(false);

                        mTvScore326.setText("0.5");
                    }else{// 2 不符合
                        mCb1Condition132.setEnabled(false);// 1
                        mCb2Condition326.setEnabled(false);
                        mCb1Condition132.setChecked(false);
                        mCb2Condition326.setChecked(false);

                        mCb3Condition326.setEnabled(true);
                        mCb4Condition326.setEnabled(true);
                        mCb3Condition326.setChecked(true);
                        mCb4Condition326.setChecked(true);// 2

                        mTvScore326.setText("0.25");
                    }
                }else{// 1 不符合
                    if(mRb2Yes326.isChecked()){// 2 符合
                        mCb1Condition132.setEnabled(true);// 1
                        mCb2Condition326.setEnabled(true);
                        mCb1Condition132.setChecked(true);
                        mCb2Condition326.setChecked(true);

                        mCb3Condition326.setEnabled(false);// 2
                        mCb4Condition326.setEnabled(false);
                        mCb3Condition326.setChecked(false);
                        mCb4Condition326.setChecked(false);

                        mTvScore326.setText("0.25");
                    }else{// 2 不符合
                        mCb1Condition132.setEnabled(true);// 1
                        mCb2Condition326.setEnabled(true);
                        mCb1Condition132.setChecked(true);
                        mCb2Condition326.setChecked(true);

                        mCb3Condition326.setEnabled(true);// 2
                        mCb4Condition326.setEnabled(true);
                        mCb3Condition326.setChecked(true);
                        mCb4Condition326.setChecked(true);

                        mTvScore326.setText("0");
                    }
                }
            }
        });

        // 1 保护层
        mCb1Condition132.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        // 1 布筋位置
        mCb2Condition326.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        // 2 符合 不符合
        mRb2Yes326.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){// 2 符合
                    if(mRb1Yes326.isChecked()){// 1 符合
                        mCb1Condition132.setEnabled(false);// 1
                        mCb2Condition326.setEnabled(false);
                        mCb1Condition132.setChecked(false);
                        mCb2Condition326.setChecked(false);

                        mCb3Condition326.setEnabled(false);// 2
                        mCb4Condition326.setEnabled(false);
                        mCb3Condition326.setChecked(false);
                        mCb4Condition326.setChecked(false);

                        mTvScore326.setText("0.5");
                    }else{// 1 不符合
                        mCb1Condition132.setEnabled(true);// 1
                        mCb2Condition326.setEnabled(true);
                        mCb1Condition132.setChecked(true);
                        mCb2Condition326.setChecked(true);

                        mCb3Condition326.setEnabled(false);
                        mCb4Condition326.setEnabled(false);
                        mCb3Condition326.setChecked(false);
                        mCb4Condition326.setChecked(false);// 2

                        mTvScore326.setText("0.25");
                    }
                }else{// 2 不符合
                    if(mRb1Yes326.isChecked()){// 1 符合
                        mCb1Condition132.setEnabled(false);// 1
                        mCb2Condition326.setEnabled(false);
                        mCb1Condition132.setChecked(false);
                        mCb2Condition326.setChecked(false);

                        mCb3Condition326.setEnabled(true);// 2
                        mCb4Condition326.setEnabled(true);
                        mCb3Condition326.setChecked(true);
                        mCb4Condition326.setChecked(true);

                        mTvScore326.setText("0.25");
                    }else{// 1 不符合
                        mCb1Condition132.setEnabled(true);// 1
                        mCb2Condition326.setEnabled(true);
                        mCb1Condition132.setChecked(true);
                        mCb2Condition326.setChecked(true);

                        mCb3Condition326.setEnabled(true);
                        mCb4Condition326.setEnabled(true);
                        mCb3Condition326.setChecked(true);
                        mCb4Condition326.setChecked(true);// 2

                        mTvScore326.setText("0");
                    }
                }
            }
        });

        // 2 保护层
        mCb3Condition326.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        // 2 布筋位置
        mCb4Condition326.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

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
