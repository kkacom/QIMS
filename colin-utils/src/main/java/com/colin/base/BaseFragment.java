package com.colin.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.colin.utils.LogUtil;


/**
 * Created by Administrator on 2017-9-21.
 */
public abstract class BaseFragment<PRESENTER extends BasePresenter> extends Fragment {
    public static final Integer REQUEST_CODE = 0x1;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (null == rootView){
            rootView = inflater.inflate(loadLayoutResource(),null);
            onRootViewCreated(rootView);
        }else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && requestCode == REQUEST_CODE){
            freshData();
        }
    }

    protected abstract void freshData();
    protected abstract int loadLayoutResource();
    protected void onRootViewCreated(View view){}
    public <VIEW extends View> VIEW findView(int id){
        if (null != rootView){
            View child = rootView.findViewById(id);
            try {
                return (VIEW) child;
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.e("findView: " + String.valueOf(e.getMessage()));
                return null;
            }
        }
        return null;
    }
}
