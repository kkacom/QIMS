package com.zhilian.rf_qims.mvp.work_assessment.view.fragment;

import android.view.View;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.base.BaseNetSFragment;
import com.zhilian.rf_qims.bean.EnterpriseBean;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.Enterprise.EnterpriseAdpater;
import com.zhilian.rf_qims.util.CommonUtils;

import java.util.List;

/**
 * 企业记录
 * Created by luocong on 2017/3/27.
 */
public class EnterpriseFragment extends BaseNetSFragment {

    private EnterpriseAdpater mEnterpriseAdpater;

    @Override
    protected String getURL() {
        return "http://asd1asdasd";
    }

    @Override
    protected List parseData(String info) {
        return null;
    }


    @Override
    protected void setAdapter() {
        /*mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);*/
       /* mLlTitle.setVisibility(View.VISIBLE);*/
        /*getActivity().setRequestedOrientation(//通过程序改变屏幕显示的方向
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);*/
        mInfoList.add(new EnterpriseBean("A01", "广东海珠人防工程防护设备安装有限公司", "何云多", "1354123155", "广州市海珠区大干围路振兴大街3号", "http://j.map.baidu.com/Mdzq5"));
        mInfoList.add(new EnterpriseBean("A02", "广东羊城人防", "吕边", "1354123155", "广州市海珠区", "http://j.map.baidu.com/Mdzq5"));
        mInfoList.add(new EnterpriseBean("A03", "广东荔湾人防", "张共", "1354123155", "广州市海珠区", "http://j.map.baidu.com/Mdzq5"));
        mInfoList.add(new EnterpriseBean("B01", "河源人防", "刘星", "1354123155", "广州市海珠区", "http://j.map.baidu.com/Mdzq5"));
        mEnterpriseAdpater = new EnterpriseAdpater(getActivity(),mActivity, mInfoList);
        mLvFrgInfo.setAdapter(mEnterpriseAdpater);
        View headView = CommonUtils.getView(R.layout.enterprise_list);
       /* mLvFrgInfo.addHeaderView(headView);*/
    }

    @Override
    protected void setData() {
        mEnterpriseAdpater.setDatas(mInfoList);
    }

    @Override
    protected void loadMoreData() {

    }

   /* @BindView(R.id.lv_load)
    LoadMoreListView mLvLoad;
    Unbinder unbinder;



    @Override
    public int getLayoutRes() {
        return R.layout.fragment_enterprise;
    }

    @Override
    protected String getURL() {
        return "http://asdas";
    }

    @Override
    protected List parseData(String info) {
        return null;
    }

    @Override
    protected void setAdapter() {

    }

    @Override
    protected void setData() {

    }

    @Override
    protected void loadMoreData() {

    }*/

}
