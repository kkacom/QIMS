package com.zhilian.rf_qims.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017-9-26.
 */
public abstract class BaseGridViewAdapter<T,VH> extends BaseAdapter{
    protected Context mContext;
    protected List<T> list;
    protected VH holder;

    public BaseGridViewAdapter(List<T> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }



    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (null == mContext) {
            mContext = viewGroup.getContext();
        }
        if (view != null) {
            holder = (VH) view.getTag();
        } else {
            view = LayoutInflater.from(mContext).inflate(layoutRes(), viewGroup, false);
            holder = createViewHolder(view);
            view.setTag(holder);
        }

        initView(i);
        return view;
    }


    @Override
    public Object getItem(int i) {
        return i;
    }

    protected abstract int layoutRes();
    protected abstract VH createViewHolder(View view);
    protected abstract void initView(int position);
}