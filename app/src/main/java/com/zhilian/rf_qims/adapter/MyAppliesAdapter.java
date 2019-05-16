package com.zhilian.rf_qims.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.util.StrKit;
import com.zhilian.rf_qims.bean.LeaveMineBean;
import com.zhilian.rf_qims.constant.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018-1-2.
 */

public class MyAppliesAdapter extends BaseAdapter {
	@BindView(R.id.iv_icon)
	ImageView mIvIcon;
	@BindView(R.id.tv_describe)
	TextView mTvDescribe;
	@BindView(R.id.tv_state)
	TextView mTvState;
	@BindView(R.id.tv_date)
	TextView mTvDate;
	private List<LeaveMineBean.ItemBean> data;
	private Context mContext;

	public MyAppliesAdapter(List<LeaveMineBean.ItemBean> data, Context context) {
		this.data = data;
		mContext = context;
	}


    @Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int i) {
		return data.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		LeaveMineBean.ItemBean leave = data.get(i);
		if (null == view) {
			view = LayoutInflater.from(mContext).inflate(R.layout.leave_item, viewGroup, false);
			ButterKnife.bind(this, view);
		}
		mTvDescribe.setText(generateItemTitle(leave));
		mTvDate.setText(leave.getApprovedate());
		String str = "";
		switch (leave.getPstatus()){
			case "0":
				str = "填写申请";
				break;

			case "1":
				str = "审批中";
				break;

			case "2":
				str = "已完结";
				break;

			case "3":
				str = "已终止";
				break;

		}
		mTvState.setText(str);

		return view;
	}

	private String generateItemTitle(LeaveMineBean.ItemBean leave) {
		StringBuilder sb = new StringBuilder();
		sb.append(Constants.BRACKET1).append(leave.getType()).append(Constants.BRACKET2)
			.append(" "+leave.getBegindate());
		if (StrKit.notBlank(leave.getEnddate())) {
			sb.append(" 至 ")
				.append(leave.getEnddate());
		}
		if (leave.getDayt() > 0.5) {
			sb.append(" ")
				.append(leave.getDayt() + "天");
		} else {
			sb.append(leave.getDayg())
				.append("半天");
		}


		return sb.toString();
	}
}
