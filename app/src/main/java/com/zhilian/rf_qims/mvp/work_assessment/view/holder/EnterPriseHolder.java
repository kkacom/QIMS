package com.zhilian.rf_qims.mvp.work_assessment.view.holder;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.bean.EnterpriseBean;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.BaseHolder;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.MyBaseAdapter;
import com.zhilian.rf_qims.util.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luocong on 2017/3/30.
 */
public class EnterPriseHolder extends BaseHolder<EnterpriseBean> {
    @BindView(R.id.tv_number)
	TextView mTvNumber;
    @BindView(R.id.tv_name)
	TextView mTvName;
    @BindView(R.id.tv_contact)
	TextView mTvContact;
    @BindView(R.id.tv_phone)
	TextView mTvPhone;
    @BindView(R.id.tv_address)
	TextView mTvAddress;
    @BindView(R.id.tv_website)
	TextView mTvWebsite;
    @BindView(R.id.ll_title)
	LinearLayout mLlTitle;

    public EnterPriseHolder(Activity mActivity, Context context, ViewGroup parent, MyBaseAdapter<EnterpriseBean> adapter, int position, EnterpriseBean bean) {
        super(mActivity,context, parent, adapter, position, bean);
    }

    @Override
    public View onCreateView(Context context, ViewGroup parent, int position, EnterpriseBean bean) {
        View epView = CommonUtils.getView(R.layout.enterprise_list);
        ButterKnife.bind(this, epView);
        return epView;

    }

    @Override
    protected void onRefreshView(EnterpriseBean bean, int position) {
        mTvNumber.setText(bean.getNumber());
        mTvName.setText(bean.getEnterPriseName());
        mTvAddress.setText(bean.getEnterPriseAddress());
        mTvContact.setText(bean.getContact());
        mTvPhone.setText(bean.getContactPhone());
        mTvWebsite.setText(bean.getRelatedWebsite());
    }
}
