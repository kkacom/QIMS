package com.zhilian.rf_qims.mvp.work_assessment.view.adpater.workassessmentmodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.entity.PersonnelJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colin on 2018/11/21 17:11 .
 */
public class TechnicistListAdapter extends BaseAdapter {
	private List<PersonnelJson> mPersonnelJsonList = new ArrayList<>();
	//private List<CaTestJson> mCaTestJsonList = new ArrayList<>();
	private Context mContext;
	//private Activity mActivity;
	//private ListView mListView;

	public TechnicistListAdapter(Context context, List<PersonnelJson> personnel){
		this.mPersonnelJsonList = personnel;
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return mPersonnelJsonList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		PersonnelJson personnelJson = mPersonnelJsonList.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_technicist_list,null);
			convertView.setTag(new ViewHolder(convertView));
		}
		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		viewHolder.name.setText(personnelJson.getPERNAME()); //姓名
		viewHolder.point.setText(personnelJson.getSCORE());	//个人得分

		return convertView;
	}

	//缓存优化
	public static class ViewHolder {
		public TextView name;//姓名
		public TextView point;//个人分数

		public ViewHolder(View view) {
			name = view.findViewById(R.id.name);
			point = view.findViewById(R.id.point);
		}
	}
}
