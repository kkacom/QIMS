package com.zhilian.rf_qims.mvp.work_assessment.view.adpater.pfassessment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.entity.EvaluateAddJson;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 诚信评估良好行为记录的适配器
 * Created by YiFan on 2017/5/17.
 */
public class RecordAddAdapter extends BaseAdapter {
    private Context mContext;
    private List<EvaluateAddJson> addList;

    public RecordAddAdapter(Context context, List<EvaluateAddJson> addList) {
        this.mContext = context;
        this.addList = addList;
    }

    @Override
    public int getCount() {
        return addList.size();
    }

    @Override
    public Object getItem(int position) {
        return addList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.record_add_item, null);
        }

        TextView awardName = (TextView) convertView.findViewById(R.id.award_name);
        TextView awardUnit = (TextView) convertView.findViewById(R.id.award_unit);
        TextView awardTime = (TextView) convertView.findViewById(R.id.award_time);
        TextView tvPosition = (TextView) convertView.findViewById(R.id.tv_position);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(addList.get(position).getGetDate());
        tvPosition.setText((position+1) + "、");// 条目
        awardName.setText(addList.get(position).getItem_name().trim());// 所获奖项
        awardUnit.setText(addList.get(position).getPrize_unit().trim());// 颁奖单位
        awardTime.setText(date);// 获奖时间

        return convertView;
    }
}
