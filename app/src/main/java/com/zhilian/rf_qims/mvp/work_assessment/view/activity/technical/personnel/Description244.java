package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.base.BaseModuleActivity;

import butterknife.BindView;

/**
 * 场地条件（2.4.4）
 */
public class Description244 extends BaseModuleActivity {
    @BindView(R.id.rb_yes2_4_4)
	RadioButton mRbYes244;
    @BindView(R.id.rb_no2_4_4)
	RadioButton mRbNo244;

    @BindView(R.id.tv_value2_4_4)
	TextView mTvValue244;// 分值
    @BindView(R.id.tv_score2_4_4)
	TextView mTvScore244;// 得分
    @BindView(R.id.et_area2_4_4)
	EditText mEtArea244;// 实际面积
    @BindView(R.id.et_remark2_4_4)
	EditText mEtRemark244;// 备注
    @BindView(R.id.tv_rule2_4_4)
	TextView mTvRule244;// 扣分规则

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_description8;
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
        mTvValue244.setText("2.0");// 分值

        mEtArea244.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()){
                    int score = Integer.valueOf(s.toString());
                    if(score < 300){
                        if(score < 270){
                            mTvScore244.setText("0");
                        }else{
                            if(mRbYes244.isChecked()){// 喷淋系统
                                mTvScore244.setText("1.0");
                            }else{
                                mTvScore244.setText("0.5");
                            }
                        }
                    }else{
                        if(mRbYes244.isChecked()){// 喷淋系统
                            mTvScore244.setText("2.0");
                        }else{
                            mTvScore244.setText("1.5");
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // 喷淋系统 有 无
        mRbYes244.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!mEtArea244.getText().toString().isEmpty()){
                    int area = Integer.valueOf(mEtArea244.getText().toString());
                    if(isChecked){// 1 喷淋系统
                        if(area < 300){
                            if(area < 270){
                                mTvScore244.setText("0");
                            }else{
                                mTvScore244.setText("1.0");
                            }
                        }else{
                            mTvScore244.setText("2.0");
                        }
                    }else{// 2
                        if(area < 300){
                            if(area < 270){
                                mTvScore244.setText("0");
                            }else{
                                mTvScore244.setText("0.5");
                            }
                        }else{
                            mTvScore244.setText("1.5");
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
