package com.zhilian.rf_qims.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhilian.rf_qims.inter.IUIOperation;
import com.zhilian.rf_qims.util.CommonUtils;
import com.zhilian.rf_qims.util.ToastUtils;

public abstract class MyBaseFragment extends Fragment implements IUIOperation {
	
	/** 管理Fragment的Activity */
	public Activity mActivity;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = (Activity) this.getActivity();
	}

	/** Fragment显示的布局 */
	public View mRoot;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		
		if (mRoot == null) {
			/*mRoot = Global.inflate(getLayoutRes(), container);*/
			mRoot = CommonUtils.getView(getLayoutRes());
			initView();
			initListener();
			initData();
			
		} else {
			// 解除mRoot与其父控件的关系
			unbindWidthParent(mRoot);
		}

		return mRoot;
	}

	/**
	 * 解除父子控件关系
	 * 
	 * @param view
	 */
	public void unbindWidthParent(View view) {
		ViewGroup parent = (ViewGroup) view.getParent();
		if (parent != null) {
			parent.removeView(view);
		}
	}

	/** 查找子控件，可以省略强转 */
	public <T> T findView(int id) {
		@SuppressWarnings("unchecked")
		T view = (T) mRoot.findViewById(id);
		return view;
	}

	public void showToast(String text) {
		ToastUtils.show(text);
	}
}

