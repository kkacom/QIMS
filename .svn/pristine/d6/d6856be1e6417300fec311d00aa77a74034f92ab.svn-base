package com.zhilian.rf_qims.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.zhilian.App;


/**
 * Created by Administrator on 2017-9-29.
 */

public abstract class BaseProjectExpandableListViewAdapter<DATA1, DATA2, PRESENTER, VH1, VH2> extends BaseExpandableListAdapter {
    protected DATA1[] groupData;
    protected DATA2[][] itemData;
    protected PRESENTER presenter;
    protected VH1 holder1;
    protected VH2 holder2;
    protected Context mContext;


    public BaseProjectExpandableListViewAdapter(DATA1[] groupData, DATA2[][] itemData, PRESENTER presenter) {
        this.groupData = groupData;
        this.itemData = itemData;
        this.presenter = presenter;
        mContext = App.getAppContext();
    }

    public BaseProjectExpandableListViewAdapter(DATA1[] groupData, DATA2[][] itemData, PRESENTER presenter, Context context) {
        this.groupData = groupData;
        this.itemData = itemData;
        this.presenter = presenter;
        mContext = context;
    }

    @Override
    public int getGroupCount() {
        return groupData.length;
    }

    @Override
    public int getChildrenCount(int i) {
//        LogUtil.e("itemData[i].length="+itemData[i].length);
        return itemData[i].length;
    }

    @Override
    public Object getGroup(int i) {
        return groupData[i];
    }

    @Override
    public Object getChild(int i, int i1) {
        return itemData[i][i1];
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int position, boolean b, View view, ViewGroup viewGroup) {

        holder1 = null;
        if (null == view) {
            view = LayoutInflater.from(mContext).inflate(layoutRes1(), viewGroup, false);
            holder1 = createViewHolder1(view);
            view.setTag(holder1);
        } else {
            holder1 = (VH1) view.getTag();
        }
        initGroupView(position,view);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
        holder2 = null;
        if (null == view) {
            view = LayoutInflater.from(mContext).inflate(layoutRes2(), viewGroup, false);
            holder2 = createViewHolder2(view);
            view.setTag(holder2);
        } else {
            holder2 = (VH2) view.getTag();
        }
        initChildrenView(groupPosition, childPosition);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    protected abstract int layoutRes1();

    protected abstract int layoutRes2();

    protected abstract VH1 createViewHolder1(View view);

    protected abstract VH2 createViewHolder2(View view);

    protected abstract void initGroupView(int position,View view);

    protected abstract void initChildrenView(int groupPosition, int childPosition);

}
