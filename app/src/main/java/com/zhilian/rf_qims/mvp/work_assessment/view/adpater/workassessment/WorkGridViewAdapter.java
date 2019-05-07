package com.zhilian.rf_qims.mvp.work_assessment.view.adpater.workassessment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.entity.CaConfigJson;

import java.util.ArrayList;
import java.util.List;

/**
 * GridView的适配器
 */
public class WorkGridViewAdapter extends BaseAdapter {
    private Context mContext;
    public String[] mScore;
    List<CaConfigJson> mModule = new ArrayList<CaConfigJson>();

    public WorkGridViewAdapter(Context mContext, String[] mScore, List<CaConfigJson> mModule) {
        this.mContext = mContext;
        this.mScore = mScore;
        this.mModule = mModule;
    }

    @Override
    public int getCount() {
            return mModule.size();
    }

    @Override
    public Object getItem(int position) {
        return mModule.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.work_grid, parent, false);
        }
        // 企业资质分数
        TextView score = (TextView) convertView.findViewById(R.id.enterprise_qualification_score);
        // 企业资质
        TextView module = (TextView) convertView.findViewById(R.id.enterprise_qualification);
        score.setText(mScore[position]);
        module.setText(mModule.get(position).getTITLE());
        return convertView;
    }
}
