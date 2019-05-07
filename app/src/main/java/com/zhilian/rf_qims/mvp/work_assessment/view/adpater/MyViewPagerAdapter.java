package com.zhilian.rf_qims.mvp.work_assessment.view.adpater;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 诚信评估三级条款和打分的ViewPager适配器
 * Created by YiFan on 2017/5/16.
 */
public class MyViewPagerAdapter extends PagerAdapter {
    /*private List<View> mListViews;

    public MyViewPagerAdapter(List<View> mListViews) {
        this.mListViews = mListViews;
    }*/

    public ArrayList<View> mViewList = new ArrayList<View>();

    public ArrayList<View> getViewList() {
        return mViewList;
    }

    public void setViewList(ArrayList<View> viewList) {
        this.mViewList = viewList;
    }

    // 给ViewPager添加View的方法
    public void addViewToAdapter(View v){
        mViewList.add(v);
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }
}
