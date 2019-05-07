package com.zhilian.rf_qims.mvp.work_assessment.view.fragment;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.base.BaseNetSFragment;
import com.zhilian.rf_qims.bean.MessageBean;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.message.MessageAdapter;

import java.util.List;

/**
 * 消息
 * Created by luocong on 2017/3/27.
 */
public class MessageFragment extends BaseNetSFragment {
    private MessageAdapter mMessageAdapter;
    @Override
    protected String getURL() {
        return "http://www.oschina.net/action/api/blog_list?pageIndex=";
    }

    @Override
    protected List parseData(String info) {
       /* List<MessageBean> messageList = JSONUtils.jsonString2Beans(info,MessageBean.class);
        return messageList;*/
        return null;
    }

    @Override
    protected void setAdapter() {
        mInfoList.add(new MessageBean(R.drawable.ic_user_1, "有新版本发布", "新版本功能:新增工作日历应用...", "昨天"));
        mInfoList.add(new MessageBean(R.drawable.ic_user_2, "巡检计划", "2016年7月份巡检计划发布，请...", "昨天"));
        mInfoList.add(new MessageBean(R.drawable.ic_user_3, "新的好友", "新好友推荐", "昨天"));
        mInfoList.add(new MessageBean(R.drawable.ic_user_4, "新邮件", "邮件标题:关于参加人防应急墙...", "昨天"));
        mInfoList.add(new MessageBean(R.drawable.ic_user_5, "免费通话", "最近通话:李XX", "昨天"));
        mInfoList.add(new MessageBean(R.drawable.ic_user_6, "文件小助手", "无需数据线，文件小助手帮你实...", "昨天"));
        mMessageAdapter = new MessageAdapter(getActivity(), mActivity,mInfoList);
        mLvFrgInfo.setAdapter(mMessageAdapter);

        // 消息item的事件监听
    }

    @Override
    protected void setData() {
        mMessageAdapter.setDatas(mInfoList);
    }

    @Override
    protected void loadMoreData() {

    }
}
