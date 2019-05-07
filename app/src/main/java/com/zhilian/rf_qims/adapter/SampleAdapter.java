package com.zhilian.rf_qims.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.colin.utils.StrKit;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.entity.Sample;

import java.util.List;

/**
 * Created by colin on 2018/2/6 10:39 .
 */

public class SampleAdapter extends BaseAdapter {
    private List<Sample> mSamples;
    private Context mContext;
    private View mView;

    public SampleAdapter(Context context, List<Sample> samples) {
        mSamples = samples;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mSamples.size();
    }

    @Override
    public Object getItem(int i) {
        return mSamples.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//		Sample sample = mSamples.get(i);
//		if (null == view) {
//			view = LayoutInflater.from(mContext).inflate(R.layout.layout_sample_item, viewGroup, false);
//		}
//		TextView mTvName = view.findViewById(R.id.tv_name);
//		TextView mTvSite = view.findViewById(R.id.tv_site);
//		TextView mTvDate = view.findViewById(R.id.tv_date);
//

//		mTvName.setText(type+" "+(StrKit.notBlank(sample.getSampleModel())?sample.getSampleModel():""));
//		mTvSite.setText(StrKit.notBlank(sample.getSampleNo())?sample.getSampleNo():"");
//		mTvDate.setText(StrKit.notBlank(sample.getMadeDate())?sample.getMadeDate():"");

        Sample sample = mSamples.get(i);
        if (null == view) {
            view = LayoutInflater.from(mContext).inflate(R.layout.layout_sample_item, viewGroup, false);
            view.setTag(new ViewHolder(view));
        }
        String type = "";
        if (sample.getSampleType() != null && !sample.getSampleType().equals("")) {
            if (sample.getSampleType().equals("TM")) {
                type = "钢筋混凝土门";
            } else if (sample.getSampleType().equals("GM")) {
                type = "钢结构门";
            } else if (sample.getSampleType().equals("TX")) {
                type = "钢筋混凝土悬摆门";
            } else if (sample.getSampleType().equals("GX")) {
                type = "钢结构悬摆门";
            } else {
                type = "";
            }
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.mTvSite.setText(StrKit.notBlank(sample.getSampleNo()) ? sample.getSampleNo() : "");
        holder.mTvName.setText(type + "  " + (StrKit.notBlank(sample.getSampleModel()) ? sample.getSampleModel() : ""));
        holder.mTvDate.setText(StrKit.notBlank(sample.getMadeDate()) ? sample.getMadeDate() : "");
        if (sample.getStatus() != 2) {//未上传
            holder.mTvStatus.setText("未上传");
            holder.mTvStatus.setTextColor(Color.BLACK);
            holder.mivStatus.setBackgroundResource(R.drawable.no_upload);
        } else {
            holder.mTvStatus.setText("已上传");
            holder.mTvStatus.setTextColor(mContext.getResources().getColor(R.color.app_color_blue));
            holder.mivStatus.setBackgroundResource(R.drawable.has_upload);
        }
        if (sample.getIsprinted() == null || sample.getIsprinted() == 0) {
            holder.mivPrint.setBackgroundResource(R.drawable.no_toggle);//不选中
        } else {
            holder.mivPrint.setBackgroundResource(R.drawable.has_toggle);//选中
        }
//		if (sample.getStatus() == 2) {
//			holder.mivSelect.setBackgroundResource(R.drawable.no_toggle);
//		}
        holder.mivPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (sample.getStatus() == 2) {
                    if (sample.getIsprinted() == null || sample.getIsprinted() == 0) {
                        sample.setIsselected(1);
                        sample.setIsprinted(1);
                        GreenDaoManager.getInstance().getNewSession().getSampleDao().update(sample);
                        notifyDataSetChanged();
                    } else {
                        sample.setIsselected(0);
                        sample.setIsprinted(0);
                        GreenDaoManager.getInstance().getNewSession().getSampleDao().update(sample);
                        notifyDataSetChanged();
                    }
                //}

            }
        });
        return view;
    }

    class ViewHolder {
        TextView mTvName;
        TextView mTvSite;
        TextView mTvDate;


        TextView mTvStatus;
        ImageView mivStatus;
        ImageView mivPrint;

        public ViewHolder(View view) {
            mTvName = view.findViewById(R.id.tv_name);
            mTvSite = view.findViewById(R.id.tv_site);
            mTvDate = view.findViewById(R.id.tv_date);

            mTvStatus = view.findViewById(R.id.tv_status);
            mivStatus = view.findViewById(R.id.iv_status);
            mivPrint = view.findViewById(R.id.iv_print);
        }
    }
}