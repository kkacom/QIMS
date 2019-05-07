package com.zhilian.rf_qims.mvp.work_assessment.view.adpater.workbench;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能模块
 */
public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    public List<String> moduleName = new ArrayList<>();
    public List<Integer> moduleIcon = new ArrayList<>();

    public GridViewAdapter(Context mContext, List<String> moduleName, List<Integer> moduleIcon) {
        this.mContext = mContext;
        this.moduleName = moduleName;
        this.moduleIcon = moduleIcon;
    }

    @Override
    public int getCount() {
        return moduleName.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = CommonUtils.getView(R.layout.grid_item_layout);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        // 功能模块名称
        holder.moduleName.setText(moduleName.get(position));
        // 功能模块图标
        holder.moduleIcon.setBackgroundResource(moduleIcon.get(position));

        return view;
    }

    static class ViewHolder{
        @BindView(R.id.work_module_icon)
		ImageView moduleIcon;// 功能模块图标
        @BindView(R.id.work_module_name)
		TextView moduleName;// 功能模块名称

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
