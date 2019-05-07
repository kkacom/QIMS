package com.zhilian.rf_qims.mvp.work_assessment.view.adpater.workassessment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.WorkAbilityJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessment.WorkAssessmentRatingActivity;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessment.WorkAssessmentUpdate;
import com.zhilian.rf_qims.util.ToastUtils;

import java.util.List;

/**
 * 从业评估（从业能力现场评估表）适配器
 */
public class WorkAssessmentFieldAdapter extends BaseAdapter implements View.OnClickListener{
    public List<WorkAbilityJson> mList;
    public Context mContext;
    private LayoutInflater mInflater;
    private Activity mActivity;

    public WorkAssessmentFieldAdapter(Context mContext, List<WorkAbilityJson> mList, Activity activity) {
        this.mContext = mContext;
        this.mList = mList;
        this.mInflater = LayoutInflater.from(mContext);
        this.mActivity = activity;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.activity_work_assessment_field_item, null);
        }

        TextView count = (TextView) convertView.findViewById(R.id.tv_position);// 条目
        TextView companyName = (TextView) convertView.findViewById(R.id.tv_company_name);// 公司名称
        TextView totalScore = (TextView) convertView.findViewById(R.id.tv_total_score);// 总分
        TextView assessmentNumber = (TextView) convertView.findViewById(R.id.tv_assessment_number);// 考核编号
        TextView assessmentDate = (TextView) convertView.findViewById(R.id.tv_assessment_date);// 考核日期
        TextView witnessesPerson = (TextView) convertView.findViewById(R.id.tv_witnesses_person);// 企业见证人
        TextView assistPerson = (TextView) convertView.findViewById(R.id.tv_assist_person);// 企业协助人员
        TextView responsiblePerson = (TextView) convertView.findViewById(R.id.tv_responsible_person);// 企业负责人
        TextView association = (TextView) convertView.findViewById(R.id.tv_association);// 协会
        TextView defenseOffice = (TextView) convertView.findViewById(R.id.tv_defense_office);// 人防办
        TextView operationPerson = (TextView) convertView.findViewById(R.id.tv_operation_person);// 考评实操人员
        TextView update = (TextView) convertView.findViewById(R.id.update);
        ImageView delete = (ImageView) convertView.findViewById(R.id.delete);
        TextView assessment = (TextView) convertView.findViewById(R.id.assessment);
        update.setFocusable(false);
        delete.setFocusable(false);
        assessment.setFocusable(false);
        // 修改按钮的事件监听
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WorkAssessmentUpdate.class);
                //intent.putExtra("wcode", mList.get(position).getWcode());// 考核编号,用ID不是更准确
                intent.putExtra("flag", "update");// 标记是更新，因为跟添加公用一个界面
                intent.putExtra("id", mList.get(position).getID());// id
                mActivity.startActivityForResult(intent, 2);
            }
        });
        // 考评按钮的事件监听
        assessment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.setEid(String.valueOf(mList.get(position).getEID()));
                Common.setWid(mList.get(position).getWID());
                Common.setWorkAbilityJson(mList.get(position));
                Intent intent = new Intent(mContext, WorkAssessmentRatingActivity.class);
                mContext.startActivity(intent);
            }
        });
        delete.setOnClickListener(this);

        count.setText(position+1+"、");
        companyName.setText(mList.get(position).getENTERPRISE_NAME());
        totalScore.setText(mList.get(position).getTOTAL());
        assessmentNumber.setText(mList.get(position).getWCODE());
        assessmentDate.setText(mList.get(position).getTESTDATE());
        witnessesPerson.setText(mList.get(position).getCONFIRMER());
        assistPerson.setText(mList.get(position).getASSISTANT());
        responsiblePerson.setText(mList.get(position).getADMINISTRATOR());
        association.setText(mList.get(position).getVC());
        defenseOffice.setText(mList.get(position).getCCAD());
        operationPerson.setText(mList.get(position).getTESTER());

        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*case R.id.assessment:// 考评
                Intent intent = new Intent(mContext, WorkAssessmentRatingActivity.class);
                mContext.startActivity(intent);
                break;*/
            case R.id.delete:// 删除
                ToastUtils.show("删除");
                break;
        }
    }
}
