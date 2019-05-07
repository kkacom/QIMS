package com.zhilian.rf_qims.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.colin.listener.TextWatcherImpl;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.adapter.CompanyAdapter;
import com.zhilian.rf_qims.entity.Company;
import com.zhilian.rf_qims.mvp.project_manager.view.ProjectManagerActivity;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by colin on 2018/2/7 8:00 .
 */

public class EnterpriseListFragment extends DialogFragment {
	@BindView(R.id.et_search)
	EditText mEtSearch;
	@BindView(R.id.iv_del)
	ImageView mIvDel;
	@BindView(R.id.tv_search)
	TextView mTvSearch;
	@BindView(R.id.lv_enterprise)
	ListView mLvEnterprise;

	Unbinder unbinder;
	private View mView;

	private ProjectManagerActivity mActivity;
	private CompanyAdapter mAdapter;
	private List<Company> mData;

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
		mActivity = (ProjectManagerActivity) getActivity();
		mView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_enterprise, null);
		unbinder = ButterKnife.bind(this, mView);
		mEtSearch.addTextChangedListener(new TextWatcherImpl() {
			@Override
			public void afterTextChanged(Editable s) {
				if (s.length() == 0) {
					mIvDel.setVisibility(View.GONE);//当文本框为空时，则叉叉消失
				} else {
					mTvSearch.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							search(mEtSearch.getText().toString());
						}
					});
					mIvDel.setVisibility(View.VISIBLE);//当文本框不为空时，出现叉叉
				}
			}
		});

		dialog.setView(mView);
		return dialog;
	}

	private void search(String condition) {
		mActivity.searchEnterpriseByCondition(condition);
	}

	@Override
	public void onResume() {
		super.onResume();
//		mActivity.loadCacheEnterpriseList();
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if (hidden) {
			if (!mData.isEmpty()) {
				mData.clear();
			}
		} else {
//			mActivity.loadCacheEnterpriseList();
		}
	}

	public void notifyDataChanged(List<Company> list) {
		mData = new ArrayList<>();
		for (Company bean : list) {
			mData.add(bean);
		}
		mAdapter = new CompanyAdapter(mActivity, mData);
		mLvEnterprise.setAdapter(mAdapter);
	}



	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}

	@OnClick({R.id.iv_del, R.id.tv_search})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.iv_del:
				break;
			case R.id.tv_search:
				break;
		}
	}
}
