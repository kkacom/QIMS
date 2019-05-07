package com.zhilian.rf_qims.mvp.work_assessment.view.adpater.pfassessment;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;

import com.zhilian.rf_qims.entity.RulesDetailsJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.BaseHolder;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.MyBaseAdapter;
import com.zhilian.rf_qims.mvp.work_assessment.view.holder.DetailedScringHolderTest;

import java.util.List;

/**
 * Created by luocong on 2017/4/1.
 */

public class DetailedScringAdapter extends MyBaseAdapter<RulesDetailsJson> {
    public DetailedScringAdapter(Activity mActivity, Context context, List<RulesDetailsJson> listData) {
        super(mActivity, context, listData);
    }

    @Override
    public BaseHolder<RulesDetailsJson> createViewHolder(Activity mActivity, Context context, ViewGroup parent, RulesDetailsJson bean, int position) {
        return new DetailedScringHolderTest(mActivity, context,parent,this,position,bean);
    }
}
