package com.zhilian.rf_qims.mvp.work_assessment.view.adpater.message;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.bean.MessageBean;
import com.zhilian.rf_qims.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消息的适配器
 * Created by YiFan on 2017/6/1.
 */
public class MessageDemoAdapter extends BaseAdapter {
    private Context mContext;
    private List<MessageBean> mList = new ArrayList<MessageBean>();

    public MessageDemoAdapter(Context context, List<MessageBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = CommonUtils.getView(R.layout.message_list);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        // 图标
        holder.mImgMsgItem.setImageResource(mList.get(position).getPhotoDrawableId());
        // 标题
        holder.mNameMsgItem.setText(mList.get(position).getMessageName());
        // 内容
        holder.mContentMsgItem.setText(mList.get(position).getMessageContent());
        // 时间
        holder.mTimeMsgItem.setText(mList.get(position).getMessageTime());

        return view;
    }

    static class ViewHolder{
        @BindView(R.id.img_msg_item)
		ImageView mImgMsgItem;// 图标
        @BindView(R.id.name_msg_item)
		TextView mNameMsgItem;// 标题
        @BindView(R.id.content_msg_item)
		TextView mContentMsgItem;// 内容
        @BindView(R.id.time_msg_item)
		TextView mTimeMsgItem;// 时间

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
