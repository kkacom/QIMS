package com.zhilian.rf_qims.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.colin.utils.StrKit;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.entity.Company;

import java.util.List;

/**
 * Created by colin on 2018/3/7 11:04 .
 */

public class CompanyAdapter extends BaseAdapter {
	private View mView;
	private Context mContext;
	private List<Company> mData;

	public CompanyAdapter(Context context, List<Company> data) {
		mContext = context;
		mData = data;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (null == convertView){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_list_item,parent,false);
		}
		TextView tvName = convertView.findViewById(R.id.item);
		String eName = mData.get(position).getName();
		//LogUtil.e("eName = "+eName);
		tvName.setText(StrKit.notBlank(eName) ? eName : "");
		return convertView;
	}
}
