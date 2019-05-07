package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.base.BaseModuleActivity;

import butterknife.BindView;

/**
 * 管理制度（2.3.1-2.3.2）
 */
public class Description231 extends BaseModuleActivity {

    @BindView(R.id.tv_value2_3_1)
	TextView mTvValue231;// 分值
    @BindView(R.id.tv_score2_3_1)
	TextView mTvScore231;// 得分
	EditText mEtRemark231;// 备注
    @BindView(R.id.tv_rule2_3_1)
	TextView mTvRule231;// 扣分规则

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_description1;
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
        mTvValue231.setText("1.0");// 分值


    }

    @Override
    protected void photo() {

    }

    @Override
    protected void video() {

    }

}
