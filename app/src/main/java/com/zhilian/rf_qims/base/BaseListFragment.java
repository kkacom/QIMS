package com.zhilian.rf_qims.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.zhilian.rf_qims.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 企业列表的基类
 * Created by YiFan on 2017/5/27.
 */
public abstract class BaseListFragment extends Fragment {
    @BindView(R.id.listView)
    public ListView mListView;
    @BindView(R.id.list_bg)
    public LinearLayout mListBg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo_layout, null);
        ButterKnife.bind(this, view);

        initView();
        // ListView的点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener(position);
            }
        });
        // ListView的长按事件
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                longListener(position);
                return true;
            }
        });

        return view;
    }

    protected abstract void initView();// 初始化控件
    protected abstract void listener(int position);// ListView的点击事件
    protected abstract void longListener(int position);// ListView的长按事件

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
