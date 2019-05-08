package com.zhilian.rf_qims.mvp.work_assessment.view.adpater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 封装适配器中的getView方法：填充布局，查找子控件，settag, 刷新子控件显示
 */
public abstract class BaseHolder<T> {

    public Context context;

    /** 指ListView */
    public ViewGroup parent;

	/** ListView的适配器 */
    public MyBaseAdapter<T> adapter;

    /** 列表项位置 */
    public  int position;

    /** 列表项实体 */
    public  T bean;

    /** 列表项布局 */
    public View itemView;

    public LayoutInflater inflater;
    public Activity mActivity;

    public BaseHolder(Activity mActivity, Context context, ViewGroup parent,
					  MyBaseAdapter<T> adapter, int position, T bean) {
        this.mActivity = mActivity;
        this.context = context;
        this.parent = parent;
        this.adapter = adapter;
        this.position = position;
        this.bean = bean;
    }

    /**
     * 初始化holder, 填充布局，查找子控件等
     */
    public void init() {
        inflater = LayoutInflater.from(context);
        // 创建列表项布局， 并查找子控件
        itemView = onCreateView(context, parent, position, bean);
        // setTag操作
        itemView.setTag(this);
    }

    /**
     * 创建列表项布局视图, 并查找item中的子控件
     * @param context
     * @param parent
     * @param bean 列表项对应的javabean
     * @param position 列表项位置
     * @return
     */
    public abstract View onCreateView(Context context, ViewGroup parent, int position, T bean);

    /**
     * 更新javabean及位置，刷新子控件的显示
     *
     * @param bean
     * @param position
     */
    protected abstract void onRefreshView(T bean, int position);

    /**
     * 刷新item子控件显示
     * @param bean
     * @param position
     */
    public void refreshView(T bean, int position) {
        this.bean = bean;
        this.position = position;

        onRefreshView(bean, position);
    }

    /**
     * 返回item布局
     * @return
     */
    public View getItemView() {
        return itemView;
    }
}
