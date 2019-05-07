package com.zhilian.rf_qims.mvp.work_assessment.view.adpater.pfassessment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.entity.EvaluateStandardJson;
import com.zhilian.rf_qims.util.CommonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 二级界面的适配器
 * Created by Administrator on 2017/5/11.
 */
public class TwoAdapter extends BaseAdapter {
    private Context mContext;
    private List<EvaluateStandardJson> evaluateStandardList;

    public TwoAdapter(Context context, List<EvaluateStandardJson> list) {
        mContext = context;
        this.evaluateStandardList = list;
    }

    @Override
    public int getCount() {
        return evaluateStandardList.size();
    }

    @Override
    public Object getItem(int position) {
        return evaluateStandardList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = CommonUtils.getView(R.layout.two_item);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        EvaluateStandardJson list = evaluateStandardList.get(position);

        holder.mTvTitle.setText(list.getSn()+" "+list.getTitle());// 模块
        holder.mTvScore.setText(0.0+"");// 考评得分
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_title)
		TextView mTvTitle;// 模块
        @BindView(R.id.tv_score)
		TextView mTvScore;// 考核评分

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
