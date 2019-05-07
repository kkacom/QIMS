package com.zhilian.rf_qims.mvp.work_assessment.view.fragment.asessment;

import com.luck.picture.lib.rxbus2.Subscribe;
import com.zhilian.rf_qims.base.BaseNetSFragment;
import com.zhilian.rf_qims.entity.EnterpriseCreditDao;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.pfassessment.PfAssessmentAdpater;
import com.zhilian.rf_qims.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * 监理企业
 * Created by luocong on 2017/3/31.
 */
public class FragmentSuv extends BaseNetSFragment {
    private PfAssessmentAdpater mPfAssessmentAdpater;

    @Override
    protected String getURL() {
        return "http://j.baidumap.com";
    }

    @Override
    protected List parseData(String info) {
        return null;
    }

    @Override
    protected void setAdapter() {
        EventBus.getDefault().register(this);

        mLvFrgInfo.setDividerHeight(10);
        mInfoList = EnterpriseCreditDao.query(6);
        mPfAssessmentAdpater = new PfAssessmentAdpater(getActivity(),mActivity, mInfoList);
        mLvFrgInfo.setAdapter(mPfAssessmentAdpater);
        mPfAssessmentAdpater.notifyDataSetChanged();
    }

    @Subscribe
    public void onEvent(int type) {
        ToastUtils.show("---->>"+type);
        if(type == 6){
            mPfAssessmentAdpater.notifyDataSetChanged();
        }
    }

    @Override
    protected void setData() {
        mPfAssessmentAdpater.setDatas(mInfoList);
    }

    @Override
    protected void loadMoreData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        // 打完分后回来刷新界面，这种方式好像不是很好，后续可优化
        mInfoList = EnterpriseCreditDao.query(6);
        mPfAssessmentAdpater = new PfAssessmentAdpater(getActivity(),mActivity, mInfoList);
        mLvFrgInfo.setAdapter(mPfAssessmentAdpater);
        mPfAssessmentAdpater.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
