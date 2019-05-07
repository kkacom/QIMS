package com.zhilian.rf_qims.mvp.work_assessment.view.adpater.workassessment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.entity.Company;
import com.zhilian.rf_qims.util.AreaMap;

import java.util.List;
import java.util.Map;

/**
 * 从业评估（企业名单列表）适配器
 */
public class WorkAssessmentListAdapter extends BaseAdapter {
    public List<Company> mList;
    public Context mContext;
    private LayoutInflater mInflater;
    private String sum;
    private Map<Long,String> mMap = AreaMap.getMap();

    public WorkAssessmentListAdapter(Context mContext, List<Company> mList) {
        this.mContext = mContext;
        this.mList = mList;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.activity_work_assessment_list_item, null);
        }

        TextView count = (TextView) convertView.findViewById(R.id.tv_position);// 条目
        TextView companyName = (TextView) convertView.findViewById(R.id.tv_company_name);// 公司名称
        TextView number = (TextView) convertView.findViewById(R.id.tv_number);// 编号
        TextView city = (TextView) convertView.findViewById(R.id.tv_city);// 地市
        TextView contacts = (TextView) convertView.findViewById(R.id.tv_contacts);// 联系人
        TextView phone = (TextView) convertView.findViewById(R.id.tv_phone);// 联系电话
        TextView companyAddress = (TextView) convertView.findViewById(R.id.tv_company_address);// 企业地址

        sum = position + 1 +"、";
        count.setText(sum);
        companyName.setText(mList.get(position).getName());
        number.setText(mList.get(position).getNumber());
        city.setText(mMap.get(mList.get(position).getArea()));
        contacts.setText(mList.get(position).getContacts());
        phone.setText(mList.get(position).getPhone());
        companyAddress.setText(mList.get(position).getAddr());

        return convertView;
    }
}
