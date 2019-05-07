package com.zhilian.rf_qims.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.widget.LoadMoreListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by luocong on 2017/3/29.
 */
public abstract class BaseNetSFragment<T> extends MyBaseFragment {
    //    public String infoUrl = "http://www.oschina.net/action/api/news_list?pageIndex=0&catalog=1&pageSize=20";
    public List<T> mInfoList = new ArrayList<>();
    public String url;
    public int pageIndex = 1;
    @BindView(R.id.lv_frg_info)
    public LoadMoreListView mLvFrgInfo;
    @BindView(R.id.srl_news)
    public SwipeRefreshLayout mSrlNews;
    Unbinder unbinder;
    @BindView(R.id.enterprise_title)
    public LinearLayout mLlTitle;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_info;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this, mRoot);
        setAdapter();
        mSrlNews.setColorSchemeColors(Color.parseColor("#40AA53"));
       /* mSrlNews.setRefreshing(true);*/   //开启下拉刷新功能
        mSrlNews.setRefreshing(false);
        mSrlNews.setEnabled(false);    //先禁止下拉刷新
    }

    @Override
    public void initListener() {
       /* mSrlNews.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                url = getURL();
                loadNetData();
                showToast("loading");
            }
        });

        //加载更多监听
        mLvFrgInfo.setOnLoaderListener(new LoadMoreListView.OnLoaderListener() {
            @Override
            public void loadMore() {
                loadMoreData();
                if (url == null) {
                    mLvFrgInfo.loadComplete();
                    //showToast("没有更多了");
                } else {
                    loadNetData();
                }
            }
        });*/
    }

    @Override
    public void initData() {
       /* url = getURL();
        loadNetData();*/
    }

    private void loadNetData() {

      /*  Log.d("lkj", Global.getSpUtil().getString("Cookie", ""));*/

        //加载网络数据
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showToast("网络出错");
                mSrlNews.setRefreshing(false);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取网络数据
                String info = response.body().string();
                //                NewsList newsInfo = XmlUtils.toBean(NewsList.class, info);
                //                mInfoList = newsInfo.getList();
                if (info != null) {

                    List<T> newList = parseData(info);
                    //没有更多的数据
                    if (newList == null || newList.size() == 0) {

                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast("没有更多了");
                                //隐藏加载更多
                                mLvFrgInfo.loadComplete();

                                mSrlNews.setRefreshing(false);
                            }
                        });
                    } else {

                        if (mSrlNews.isRefreshing()) {
                            mInfoList.clear();
                            mInfoList.addAll(0, newList);
                        } else {
                            mInfoList.addAll(newList);
                        }
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setData();
                                mSrlNews.setRefreshing(false);

                                if (mLvFrgInfo.isLoadingMore) {
                                    pageIndex++;
                                }
                                showToast("加载完成");

                                //隐藏加载更多
                                mLvFrgInfo.loadComplete();
                            }
                        });
                    }
                }
            }
        });
    }

    //设置url
    protected abstract String getURL();

    //解析数据
    protected abstract List<T> parseData(String info);

    //设置适配器
    protected abstract void setAdapter();

    //设置数据，适配器刷新数据
    protected abstract void setData();

    //实现加载更多
    protected abstract void loadMoreData();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
