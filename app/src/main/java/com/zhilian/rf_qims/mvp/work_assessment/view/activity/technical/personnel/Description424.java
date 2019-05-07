package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.base.BaseModuleActivity;

import butterknife.BindView;

/**
 * 安装质量（4.2.4）
 */
public class Description424 extends BaseModuleActivity {
    @BindView(R.id.rb_yes4_2_4)
	RadioButton mRbYes424;// 符合
    @BindView(R.id.rb_no4_2_4)
	RadioButton mRbNo424;// 不符合

    @BindView(R.id.tv_value4_2_4)
	TextView mTvValue424;// 分值
    @BindView(R.id.tv_score4_2_4)
	TextView mTvScore424;// 得分
    @BindView(R.id.et_lack4_2_4)
	EditText mEtLack424;// 缺几门
    @BindView(R.id.et_remark4_2_4)
	EditText mEtRemark424;// 备注
    @BindView(R.id.tv_rule4_2_4)
	TextView mTvRule424;// 扣分规则

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_description25;
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
        mTvValue424.setText("2.0");// 分值

        // 符合 不符合
        mRbYes424.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){// 1 符合
                    mEtLack424.setEnabled(false);
                    mEtLack424.setText("0");
                    mTvScore424.setText("2.0");
                }else{// 2 不符合
                    mEtLack424.setEnabled(true);
                }
            }
        });

        // 缺几门
        mEtLack424.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()){
                    Double score = Double.parseDouble(s.toString());// 输入值
                    if((2d-score*0.3) < 0d){
                        mTvScore424.setText("0");
                    }else{
                        mTvScore424.setText(String.format("%.2f", 2d- (0.3d * score)));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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
