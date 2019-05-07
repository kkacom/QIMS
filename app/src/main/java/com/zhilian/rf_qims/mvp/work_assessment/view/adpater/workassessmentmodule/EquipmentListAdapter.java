package com.zhilian.rf_qims.mvp.work_assessment.view.adpater.workassessmentmodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.entity.EntEquipmentJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colin on 2018/11/21 17:11 .
 */
public class EquipmentListAdapter extends BaseAdapter {
	private List<EntEquipmentJson> mEntEquipmentJsons = new ArrayList<>();
	//private List<CaTestJson> mCaTestJsonList = new ArrayList<>();
	private Context mContext;
	//private Activity mActivity;
	//private ListView mListView;

	public EquipmentListAdapter(Context context, List<EntEquipmentJson> equipment){
		this.mEntEquipmentJsons  = equipment;
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return mEntEquipmentJsons.size();
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
		EntEquipmentJson equipmentJson = mEntEquipmentJsons.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_technicist_list,null);
			convertView.setTag(new ViewHolder(convertView));
		}
		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		viewHolder.name.setText(equipmentJson.getENAME()); //设备名称
		viewHolder.point.setText(equipmentJson.getSCORE());	//每台得分

		return convertView;
	}

	//缓存优化
	public static class ViewHolder {
		public TextView name;//设备名称
		public TextView point;//每台分数

		public ViewHolder(View view) {
			name = view.findViewById(R.id.name);
			point = view.findViewById(R.id.point);
		}
	}
}
