package com.zhilian.rf_qims.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by luocong on 2017/3/28.
 */

public class LoadMoreListView extends ListView {
    public View mFooter;
    public boolean isLoadingMore = false;
    private OnLoaderListener mOnLoaderListener;

    public LoadMoreListView(Context context) {
        this(context, null);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFoot();
        initEvent();
    }


    //加载尾部布局
    private void initFoot() {
       /* mFooter = View.inflate(getContext(), R.layout.view_loading_foot,null);
        this.addFooterView(mFooter);
        mFooter.setVisibility(View.GONE);*/
    }

    //加载更多监听
    private void initEvent() {
        setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int lastVisiblePosition = getLastVisiblePosition();
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && (lastVisiblePosition == (getCount() - 1)) && !isLoadingMore) {
                    isLoadingMore = true;
                    //mFooter.setVisibility(View.VISIBLE);
                    // 提供接口给
                    if (mOnLoaderListener != null) {
                        mOnLoaderListener.loadMore();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
								 int visibleItemCount, int totalItemCount) {
            }
        });
    }

    //提供加载完成隐藏的方法
    public void loadComplete() {
        mFooter.setVisibility(View.GONE);
        isLoadingMore = false;
    }


    public interface OnLoaderListener {
        void loadMore();
    }

    //设置回调接口
    public void setOnLoaderListener(OnLoaderListener mOnLoaderListener) {
        this.mOnLoaderListener = mOnLoaderListener;
    }
}
