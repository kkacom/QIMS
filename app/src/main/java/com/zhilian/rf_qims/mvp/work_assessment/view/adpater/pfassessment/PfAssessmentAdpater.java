package com.zhilian.rf_qims.mvp.work_assessment.view.adpater.pfassessment;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;

import com.zhilian.rf_qims.entity.EnterpriseCreditJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.BaseHolder;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.MyBaseAdapter;
import com.zhilian.rf_qims.mvp.work_assessment.view.holder.PfAssessmentHolder;

import java.util.List;

/**
 * 企业名单的适配器
 * Created by luocong on 2017/3/30.
 */
public class PfAssessmentAdpater extends MyBaseAdapter<EnterpriseCreditJson> {

    public PfAssessmentAdpater(Activity mActivity, Context context, List<EnterpriseCreditJson> listData) {
        super(mActivity, context, listData);
    }

    @Override
    public BaseHolder<EnterpriseCreditJson> createViewHolder(Activity mActivity, Context context, ViewGroup parent, EnterpriseCreditJson bean, int position) {
        return new PfAssessmentHolder(mActivity, context, parent, this, position, bean);
    }

}
