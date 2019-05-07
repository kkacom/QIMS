package com.zhilian.rf_qims.mvp.work_assessment.view.holder;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.bean.MessageBean;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.BaseHolder;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.MyBaseAdapter;
import com.zhilian.rf_qims.util.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luocong on 2017/3/29.
 */
public class MessageHolder extends BaseHolder<MessageBean> {
    @BindView(R.id.img_msg_item)
	ImageView mImgMsgItem;// 图标
    @BindView(R.id.name_msg_item)
	TextView mNameMsgItem;// 标题
    @BindView(R.id.content_msg_item)
	TextView mContentMsgItem;// 内容
    @BindView(R.id.time_msg_item)
	TextView mTimeMsgItem;// 时间

    public MessageHolder(Activity mActivity, Context context, ViewGroup parent, MyBaseAdapter<MessageBean> adapter, int position, MessageBean bean) {
        super(mActivity, context, parent, adapter, position, bean);
    }

    @Override
    public View onCreateView(Context context, ViewGroup parent, int position, MessageBean bean) {
        View messageView = CommonUtils.getView(R.layout.message_list);
        ButterKnife.bind(this,messageView);
        return messageView;
    }

    @Override
    protected void onRefreshView(MessageBean bean, int position) {
        mImgMsgItem.setImageResource(bean.getPhotoDrawableId());
        mNameMsgItem.setText(bean.getMessageName());
        mContentMsgItem.setText(bean.getMessageContent());
        mTimeMsgItem.setText(bean.getMessageTime());
    }
}
