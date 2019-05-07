package com.zhilian.rf_qims.mvp.work_assessment.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.bean.MessageBean;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.message.MessageDemoAdapter;
import com.zhilian.rf_qims.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消息
 * Created by YiFan on 2017/6/1.
 */
public class MessageFragmentDome extends Fragment {
    @BindView(R.id.message_list)
	ListView mListView;
    private MessageDemoAdapter mAdapter;
    public List<MessageBean> mList = new ArrayList<MessageBean>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_layout, null);
        ButterKnife.bind(this, view);

        initData();

        return view;
    }

    /**
     * 加载数据（假数据）
     */
    private void initData(){
        mList.clear();
        mList.add(new MessageBean(R.drawable.ic_user_1, "有新版本发布", "新版本功能:新增工作日历应用...", "昨天"));
        mList.add(new MessageBean(R.drawable.ic_user_2, "巡检计划", "2016年7月份巡检计划发布，请...", "昨天"));
        mList.add(new MessageBean(R.drawable.ic_user_3, "新的好友", "新好友推荐", "昨天"));
        mList.add(new MessageBean(R.drawable.ic_user_4, "新邮件", "邮件标题:关于参加人防应急墙...", "昨天"));
        mList.add(new MessageBean(R.drawable.ic_user_5, "免费通话", "最近通话:李XX", "昨天"));
        mList.add(new MessageBean(R.drawable.ic_user_6, "文件小助手", "无需数据线，文件小助手帮你实...", "昨天"));
        mAdapter = new MessageDemoAdapter(getActivity(), mList);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.show("未开通权限");
            }
        });
    }
}
