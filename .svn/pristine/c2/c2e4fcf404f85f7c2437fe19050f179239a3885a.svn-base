package com.zhilian.rf_qims.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.entity.Model;

import java.util.List;

/**
 * Created by hanson on 2016/7/12.
 */
public class ModelAdapter extends BaseAdapter {
    private List<Model> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public ModelAdapter(List<Model> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        Log.d("size", mList.size() + "");
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            //适配
            convertView = mInflater.inflate(R.layout.layout_list_item, parent, false);
            holder.item = (TextView) convertView.findViewById(R.id.item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.item.setText(mList.get(position).getModel_number());
        return convertView;
    }

    public class ViewHolder {
        public TextView item;
    }
}
