package com.zhilian.rf_qims.base;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.zhilian.rf_qims.constant.BusinessContant;
import com.zhilian.rf_qims.inter.IUIOperation;
import com.zhilian.rf_qims.util.CommonUtils;
import com.zhilian.rf_qims.util.ToastUtils;

/**
 * 诚信评估一、二级条款模块（基类）
 * Created by luocong on 2017/3/28.
 */
public abstract class WorkBaseActivity extends AppCompatActivity implements IUIOperation {
    /** 标题 */
    private TextView tvTitle;
    public String mType;
    public int mEid;
    public int mCid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = CommonUtils.getView(getLayoutRes());
        setContentView(view);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor(BusinessContant.InfoTitle), true);

        mType = getIntent().getStringExtra("type");// 企业类型
        mEid = getIntent().getIntExtra("eid", 0);// 企业ID
        mCid = getIntent().getIntExtra("cid", 0);// 考核次数

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setTitle(getCompanyName());
        initView();
        initListener();
        initData();
    }
    /**
     * 设置界面标题
     * @param title
     */
    public void setPageTitle(String title) {
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }

    /** 查找子控件，可以省略强转 */
    public <T> T findView(int id) {
        @SuppressWarnings("unchecked")
        T view = (T) findViewById(id);
        return view;
    }


    public void showToast(String text) {
        ToastUtils.show(text);
    }


    private ProgressDialog mPDialog;

    /**
     * 显示加载提示框(不能在子线程调用)
     */
    public void showProgressDialog(String message) {
        mPDialog = new ProgressDialog(this);
        mPDialog.setMessage(message);
        // 点击外部时不销毁
        mPDialog.setCanceledOnTouchOutside(false);

        // activity如果正在销毁或已销毁，不能show Dialog，否则出错。
        if (!isFinishing())
            mPDialog.show();
    }

    /**
     * 销毁加载提示框
     */
    public void dismissProgressDialog() {
        if (mPDialog != null) {
            mPDialog.dismiss();
            mPDialog = null;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract String getCompanyName();
}
