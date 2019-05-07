package com.zhilian.rf_qims.mvp.work_assessment.view.adpater.Enterprise;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;

import com.zhilian.rf_qims.bean.EnterpriseBean;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.BaseHolder;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.MyBaseAdapter;
import com.zhilian.rf_qims.mvp.work_assessment.view.holder.EnterPriseHolder;

import java.util.List;

/**
 * Created by luocong on 2017/3/30.
 */
public class EnterpriseAdpater extends MyBaseAdapter<EnterpriseBean> {
    public EnterpriseAdpater(Activity mActivity, Context context, List<EnterpriseBean> listData) {
        super(mActivity, context, listData);
    }

    @Override
    public BaseHolder<EnterpriseBean> createViewHolder(Activity mActivity, Context context, ViewGroup parent, EnterpriseBean bean, int position) {
        return new EnterPriseHolder(mActivity,context,parent,this,position,bean);
    }
}
