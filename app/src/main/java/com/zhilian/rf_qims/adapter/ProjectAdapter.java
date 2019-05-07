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
import com.zhilian.rf_qims.entity.Project;

import java.util.List;

/**
 * Created by colin on 2018/2/6 10:39 .
 */

public class ProjectAdapter extends BaseAdapter {
    private List<Project> mProjects;
    private Context mContext;

    public ProjectAdapter(Context context, List<Project> projects) {
        mProjects = projects;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mProjects.size();
    }

    @Override
    public Object getItem(int i) {
        return mProjects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Project project = mProjects.get(i);
        if (null == view) {
            view = LayoutInflater.from(mContext).inflate(R.layout.project_item, viewGroup, false);
            view.setTag(new ViewHolder(view));
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.mTvSite.setText(StrKit.notBlank(project.getItemCode()) ? project.getItemCode() : "");
        holder.mTvName.setText(StrKit.notBlank(project.getItemName()) ? project.getItemName() : "");
        holder.mTvDate.setText(StrKit.notBlank(project.getAcceptDate()) ? project.getAcceptDate() : "");
        if (project.getStatus() != 2) {//未上传
            holder.mTvStatus.setText("未上传");
            holder.mTvStatus.setTextColor(Color.BLACK);
            holder.mivStatus.setBackgroundResource(R.drawable.no_upload);
        } else {
            holder.mTvStatus.setText("已上传");
            holder.mTvStatus.setTextColor(mContext.getResources().getColor(R.color.app_color_blue));
            holder.mivStatus.setBackgroundResource(R.drawable.has_upload);
        }
        if (project.getIsselected() == null || project.getIsselected() == 0) {
            holder.mivSelect.setBackgroundResource(R.drawable.no_toggle);//不选中
        } else {
            holder.mivSelect.setBackgroundResource(R.drawable.has_toggle);//选中
        }
        holder.mivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (project.getIsselected() == null || project.getIsselected() == 0) {
                        project.setIsselected(1);
                        GreenDaoManager.getInstance().getNewSession().getProjectDao().update(project);
                        notifyDataSetChanged();
                    } else {
                        project.setIsselected(0);
                        GreenDaoManager.getInstance().getNewSession().getProjectDao().update(project);
                        notifyDataSetChanged();
                    }
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
        ImageView mivSelect;

        public ViewHolder(View view) {
            mTvName = view.findViewById(R.id.tv_name);
            mTvSite = view.findViewById(R.id.tv_site);
            mTvDate = view.findViewById(R.id.tv_date);

            mTvStatus = view.findViewById(R.id.tv_status);
            mivStatus = view.findViewById(R.id.iv_status);
            mivSelect = view.findViewById(R.id.iv_select);
        }
    }
}
