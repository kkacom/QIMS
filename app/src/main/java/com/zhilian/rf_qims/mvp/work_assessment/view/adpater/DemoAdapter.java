package com.zhilian.rf_qims.mvp.work_assessment.view.adpater;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.entity.EnterpriseCreditJson;
import com.zhilian.rf_qims.util.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 企业列表的适配器
 * Created by YiFan on 2017/5/27.
 */
public class DemoAdapter extends BaseAdapter {
    private Context mContext;
    List<EnterpriseCreditJson> creditList = new ArrayList<EnterpriseCreditJson>();

    public DemoAdapter(Context context, List<EnterpriseCreditJson> creditList) {
        mContext = context;
        this.creditList = creditList;
    }

    @Override
    public int getCount() {
        return creditList.size();
    }

    @Override
    public Object getItem(int position) {
        return creditList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = CommonUtils.getView(R.layout.pf_assess_list);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(creditList.get(position).getSubmit_date());
        holder.mTvPosition.setText((position+1) + "、");// 条目
        holder.mTvName.setText(creditList.get(position).getUnit_name());// 企业名称
        holder.mTvTotalScore.setText(creditList.get(position).getTotal() + "");// 总分
        holder.mTvYear.setText(creditList.get(position).getAnnual());// 年份
        holder.mTvDate.setText(date);// 企业申报时间
        if(creditList.get(position).getStatus() == 0){
            holder.mTvStatus.setText(status(holder, creditList.get(position).getCtstatus()));// 状态
        }else{
            holder.mTvStatus.setText("已上传");// 状态
            holder.mTvStatus.setTextColor(Color.parseColor("#6495ED"));
        }

        return view;
    }

    public String status(ViewHolder holder, int status) {
        if (status == 1) {
            holder.mTvStatus.setTextColor(Color.parseColor("#DC143C"));
            return "已申报";

        } else if (status == 2) {
            holder.mTvStatus.setTextColor(Color.parseColor("#5CB292"));
            return "已考评";

        } else if (status == 3) {
            return "已审核";

        } else if (status == 4) {
            holder.mTvStatus.setTextColor(Color.parseColor("#D2691E"));
            return "考评中";

        } else if (status == 5) {
            return "审核不通过";

        } else {
            return "审核结果出错";
        }
    }

    static class ViewHolder{
        @BindView(R.id.tv_position)
		TextView mTvPosition;// 条目
        @BindView(R.id.tv_name)
		TextView mTvName;// 企业名称
        @BindView(R.id.tv_total_score)
		TextView mTvTotalScore;// 总分
        @BindView(R.id.tv_year)
		TextView mTvYear;// 年份
        @BindView(R.id.tv_date)
		TextView mTvDate;// 企业申报时间
        @BindView(R.id.tv_status)
		TextView mTvStatus;// 状态

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
