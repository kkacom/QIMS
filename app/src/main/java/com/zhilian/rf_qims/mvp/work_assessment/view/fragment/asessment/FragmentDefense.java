package com.zhilian.rf_qims.mvp.work_assessment.view.fragment.asessment;

import com.zhilian.rf_qims.base.BaseNetSFragment;
import com.zhilian.rf_qims.entity.EnterpriseCreditDao;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.pfassessment.PfAssessmentAdpater;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * 防护企业
 * Created by luocong on 2017/3/31.
 */
public class FragmentDefense extends BaseNetSFragment {
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
    public void setAdapter() {
        EventBus.getDefault().register(this);

        mLvFrgInfo.setDividerHeight(10);
        mInfoList = EnterpriseCreditDao.query(1);
        mPfAssessmentAdpater = new PfAssessmentAdpater(getActivity(),mActivity, mInfoList);
        mLvFrgInfo.setAdapter(mPfAssessmentAdpater);
        mPfAssessmentAdpater.notifyDataSetChanged();


    }

    @Subscribe
    public void onEventListener(int type) {
        System.out.println("---->>"+type);
        if(type == 1){
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
        mInfoList = EnterpriseCreditDao.query(1);
        mPfAssessmentAdpater = new PfAssessmentAdpater(getActivity(), mActivity, mInfoList);
        mLvFrgInfo.setAdapter(mPfAssessmentAdpater);
        mPfAssessmentAdpater.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
