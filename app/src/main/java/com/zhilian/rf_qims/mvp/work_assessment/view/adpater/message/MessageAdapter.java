package com.zhilian.rf_qims.mvp.work_assessment.view.adpater.message;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;

import com.zhilian.rf_qims.bean.MessageBean;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.BaseHolder;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.MyBaseAdapter;
import com.zhilian.rf_qims.mvp.work_assessment.view.holder.MessageHolder;

import java.util.List;

/**
 * 消息的适配器
 * Created by luocong on 2017/3/29.
 */
public class MessageAdapter extends MyBaseAdapter<MessageBean> {
    public MessageAdapter(Activity mActivity, Context context, List<MessageBean> messageList) {
        super(mActivity, context, messageList);
    }

    @Override
    public BaseHolder<MessageBean> createViewHolder(Activity mActivity, Context context, ViewGroup parent, MessageBean bean, int position) {
        return new MessageHolder(mActivity,context,parent,this,position,bean);
    }
}
