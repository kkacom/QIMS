package com.zhilian.rf_qims.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zhilian.rf_qims.R;

/**
 * Created by Administrator on 2018-1-2.
 */

public class MySpinnerAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private String[] mStringArray;
    public MySpinnerAdapter(Context context, String[] stringArray) {
        super(context, R.layout.spinner_item, stringArray);
        mContext = context;
        mStringArray = stringArray;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        //修改Spinner展开后的字体颜色
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.spinner_item, parent, false);
        }

        //此处text1是Spinner默认的用来显示文字的TextView
        TextView tv = (TextView) convertView.findViewById(R.id.text1);
        tv.setText(mStringArray[position]);
        //tv.setHeight(40);
        tv.setWidth(160);
        tv.setTextSize(12);
        tv.setTextColor(Color.GRAY);
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 修改Spinner选择后结果的字体颜色
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.spinner_item, parent, false);
        }

        //此处text1是Spinner默认的用来显示文字的TextView
        TextView tv = (TextView) convertView.findViewById(R.id.text1);
        View head = convertView.findViewById(R.id.head);
        View foot = convertView.findViewById(R.id.foot);
        head.setVisibility(View.GONE);
        foot.setVisibility(View.GONE);
        tv.setText(mStringArray[position]);
        tv.setTextSize(12);
        tv.setTextColor(Color.GRAY);
        return convertView;
    }


}
