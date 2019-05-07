package com.zhilian.rf_qims.mvp.work_assessment.view.adpater.pfassessment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.entity.EvaluateDeductJson;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 诚信评估不良行为记录的适配器
 * Created by YiFan on 2017/5/17.
 */
public class RecordDeductAdapter extends BaseAdapter {
    private Context mContext;
    private List<EvaluateDeductJson> deductList;

    public RecordDeductAdapter(Context context, List<EvaluateDeductJson> deductList) {
        this.mContext = context;
        this.deductList = deductList;
    }

    @Override
    public int getCount() {
        return deductList.size();
    }

    @Override
    public Object getItem(int position) {
        return deductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.record_deduct_item, null);
        }

        TextView awardName = (TextView) convertView.findViewById(R.id.award_name);
        TextView awardUnit = (TextView) convertView.findViewById(R.id.award_unit);
        TextView awardTime = (TextView) convertView.findViewById(R.id.award_time);
        TextView tvPosition = (TextView) convertView.findViewById(R.id.tv_position);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(deductList.get(position).getDodate());
        tvPosition.setText((position+1) + "、");// 条目
        awardName.setText(deductList.get(position).getProject_name().trim());// 项目名称
        awardUnit.setText(deductList.get(position).getExecutable_unit().trim());// 处罚单位
        awardTime.setText(date);// 违反时间

        return convertView;
    }
}
