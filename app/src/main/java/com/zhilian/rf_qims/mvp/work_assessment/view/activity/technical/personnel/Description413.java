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
 * 安装操作（4.1.3）
 */
public class Description413 extends BaseModuleActivity {
    @BindView(R.id.rb_yes4_1_3)
	RadioButton mRbYes413;// 符合
    @BindView(R.id.rb_no4_1_3)
	RadioButton mRbNo413;// 缺隐蔽记录
    @BindView(R.id.rb_not4_1_3)
	RadioButton mRbNot413;// 不符合

    @BindView(R.id.tv_value4_1_3)
	TextView mTvValue413;// 分值
    @BindView(R.id.tv_score4_1_3)
	TextView mTvScore413;// 得分
    @BindView(R.id.et_lack4_1_3)
	EditText mEtLack413;// 缺几项
    @BindView(R.id.et_remark4_1_3)
	EditText mEtRemark413;// 备注
    @BindView(R.id.tv_rule4_1_3)
	TextView mTvRule413;// 扣分规则

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_description24;
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
        mTvValue413.setText("1.0");// 分值

        // 符合
        mRbYes413.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mEtLack413.setEnabled(false);
                    mEtLack413.setText("0");
                    mTvScore413.setText("1.0");
                }
            }
        });

        // 缺隐蔽记录
        mRbNo413.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mEtLack413.setEnabled(false);
                    mEtLack413.setText("0");
                    mTvScore413.setText("0");
                }
            }
        });

        // 不符合
        mRbNot413.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mEtLack413.setEnabled(true);
                    mTvScore413.setText("1.0");
                }
            }
        });

        // 缺几项
        mEtLack413.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()){
                    int score = Integer.parseInt(s.toString());
                    if((1-score*0.5) < 0){
                        mTvScore413.setText("0");
                    }else{
                        mTvScore413.setText((1-score*0.5)+"");
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
