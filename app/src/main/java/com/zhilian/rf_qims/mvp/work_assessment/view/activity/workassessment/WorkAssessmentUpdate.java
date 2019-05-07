package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.colin.http.HttpServiceManager;
import com.colin.http.HttpUtil;
import com.google.gson.JsonObject;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.dao.CompanyDao;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.entity.Company;
import com.zhilian.rf_qims.entity.WorkAbilityDao;
import com.zhilian.rf_qims.entity.WorkAbilityJson;
import com.zhilian.rf_qims.entity.WorkUserDao;
import com.zhilian.rf_qims.entity.WorkUserJson;
import com.zhilian.rf_qims.util.DialogUtil;
import com.zhilian.rf_qims.util.ToastUtils;
import com.zhilian.rf_qims.util.UploadParams;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 企业评估信息（新增、修改）
 */
public class WorkAssessmentUpdate extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.company_name)
	EditText mCompanyName;// 企业名称
    @BindView(R.id.assessment_number)
	EditText mAssessmentNumber;// 考核编号
    @BindView(R.id.assessment_date)
	TextView mAssessmentDate;// 评估日期
    @BindView(R.id.association)
	EditText mAssociation;// 协会副会长
    @BindView(R.id.defense_office)
	EditText mDefenseOffice;// 人防办代表
    @BindView(R.id.operation_person)
	TextView mOperationPerson;// 考评实操人员
    @BindView(R.id.assist_person)
	EditText mAssistPerson;// 企业协助人员
    @BindView(R.id.witnesses_person)
	EditText mWitnessesPerson;// 企业见证人
    @BindView(R.id.responsible_person)
	EditText mResponsiblePerson;// 企业负责人
    @BindView(R.id.type_new)
	RadioButton mTypeNew;// 企业类型（新）
    @BindView(R.id.type_old)
	RadioButton mTypeOld;// 企业类型（旧）

    @BindView(R.id.sel_assessment_date)
	LinearLayout mSelectDate;// 选择考核日期
    @BindView(R.id.sel_operation_person)
	LinearLayout mSelectPerson;// 选择考评实操人员
    @BindView(R.id.save)
	TextView mCave;// 保存
    @BindView(R.id.close)
	TextView mClose;// 关闭
    WorkAbilityJson workAbilityList;
    String flag;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_assessment_update);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        flag = getIntent().getStringExtra("flag");
        id = getIntent().getLongExtra("id",-1);// ID
        if(flag.equals("add") && id == -1){
            String name = getIntent().getStringExtra("ep");//企业名称
            Company company = GreenDaoManager.getInstance().getNewSession().getCompanyDao().queryBuilder().
                where(CompanyDao.Properties.Name.eq(name)).unique();
            mCompanyName.setText(name);
            mAssessmentNumber.setText(company.getNumber());
            mCave.setOnClickListener(this);
            mClose.setOnClickListener(this);
            mSelectDate.setOnClickListener(this);
            mSelectPerson.setOnClickListener(this);
        }else if (flag.equals("update") && id != -1){
            updateData(id);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // 修改加载数据
    private void updateData(long id) {
        workAbilityList = WorkAbilityDao.queryId(id);
        mCompanyName.setText(workAbilityList.getENTERPRISE_NAME());// 公司名称
        mAssessmentNumber.setText(workAbilityList.getWCODE());// 考核编号
        mAssessmentDate.setText(workAbilityList.getTESTDATE());// 考核日期
        mAssociation.setText(workAbilityList.getVC());// 协会
        mDefenseOffice.setText(workAbilityList.getCCAD());// 人防办
        mOperationPerson.setText(workAbilityList.getTESTER());// 考评实操人员
        mAssistPerson.setText(workAbilityList.getASSISTANT());// 企业协助人
        mWitnessesPerson.setText(workAbilityList.getCONFIRMER());// 企业见证人
        mResponsiblePerson.setText(workAbilityList.getADMINISTRATOR());// 企业负责人
        if(workAbilityList.getWTYPE().equals("新")){// 企业类型
            mTypeNew.setChecked(true);
        }else{
            mTypeOld.setChecked(true);
        }
        mCave.setOnClickListener(this);
        mClose.setOnClickListener(this);
        mSelectDate.setOnClickListener(this);
        mSelectPerson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:// 保存
                if (flag.equals("update")){
                    String name = mCompanyName.getText().toString();// 企业名称
                    String number = mAssessmentNumber.getText().toString();// 考核编号
                    String date1 = mAssessmentDate.getText().toString();// 考核日期
                    String association = mAssociation.getText().toString();// 协会副会长
                    String defenseOffice = mDefenseOffice.getText().toString();// 人防办
                    String operationPerson = mOperationPerson.getText().toString();// 考评实操人员
                    String assistPerson = mAssistPerson.getText().toString();// 企业协助人员
                    String witnessesPerson = mWitnessesPerson.getText().toString();// 企业见证人
                    String responsiblePerson = mResponsiblePerson.getText().toString();// 企业负责人
                    String type = "";
                    if(mTypeNew.isChecked()){
                        type = "新";
                    }else if(mTypeOld.isChecked()){
                        type = "旧";
                    }
                    if (workAbilityList.getSTATUS() >= 1){
                        workAbilityList.setSTATUS(1);
                    }
                    workAbilityList.setTESTDATE(date1);
                    workAbilityList.setCONFIRMER(witnessesPerson);
                    workAbilityList.setVC(association);
                    workAbilityList.setCCAD(defenseOffice);
                    workAbilityList.setTESTER(operationPerson);
                    workAbilityList.setASSISTANT(assistPerson);
                    workAbilityList.setADMINISTRATOR(responsiblePerson);
                    workAbilityList.setENTERPRISE_NAME(name);
                    workAbilityList.setWCODE(number);
                    workAbilityList.setWTYPE(type);
                    WorkAbilityDao.update(workAbilityList);
                    ToastUtils.show("已修改保存");
                    setResult(100);
                    finish();
                }else {
                    String name = mCompanyName.getText().toString();// 企业名称
                    String number = mAssessmentNumber.getText().toString();// 考核编号
                    String date1 = mAssessmentDate.getText().toString();// 考核日期
                    String association = mAssociation.getText().toString();// 协会副会长
                    String defenseOffice = mDefenseOffice.getText().toString();// 人防办
                    String operationPerson = mOperationPerson.getText().toString();// 考评实操人员
                    String assistPerson = mAssistPerson.getText().toString();// 企业协助人员
                    String witnessesPerson = mWitnessesPerson.getText().toString();// 企业见证人
                    String responsiblePerson = mResponsiblePerson.getText().toString();// 企业负责人
                    String type = "";
                    if(mTypeNew.isChecked()){
                        type = "新";
                    }else if(mTypeOld.isChecked()){
                        type = "旧";
                    }
                    //原协会id存的表的eid，不是防护的主id，所以加了MainId来存主ID。创建项目就上传，所以默认本地上传状态为1
                    String id = String.valueOf(GreenDaoManager.getInstance().getNewSession().getCompanyDao().queryBuilder().where(
                                CompanyDao.Properties.Number.eq(number)).unique().getMainId());
                    WorkAbilityJson workAbilityJson = new WorkAbilityJson(date1, witnessesPerson, association,
                        defenseOffice, operationPerson, assistPerson, responsiblePerson, name, number, type , 1, Integer.valueOf(id));
                    Map<String,String> params = UploadParams.getWorkAbilityParams(workAbilityJson);
                    HttpServiceManager.getInstance().getHttpService()
                        .getServerData(HttpUtil.initUrl(), HttpUtil.initSaveParams("save","workAbilitySave",params))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResponseBody>() {
                            @Override
                            public void accept(ResponseBody responseBody) throws Exception {
                                String json = responseBody.string();
                                if (json.equals("false")){
                                    ToastUtils.show("同步上传服务器失败,无法本地保存,检查数据");
                                    return;
                                }
                                workAbilityJson.setWID(Long.valueOf(json));
                                WorkAbilityDao.insertOrReplace(workAbilityJson);
                                ToastUtils.show("已保存");
                                setResult(100);
                                finish();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                ToastUtils.showLong("同步上传服务器失败,无法本地保存,请检查网络");
                                Log.e("同步上传服务器失败", throwable.getMessage());
                            }
                        });
                }
                break;
            case R.id.close:// 关闭
                finish();
                break;
            case R.id.sel_assessment_date:// 选择考核日期
                DialogUtil.DateDialog date = new DialogUtil.DateDialog(mAssessmentDate);
                date.show(getFragmentManager(), "Picker_date");
                break;
            case R.id.sel_operation_person:// 选择考评实操人员
                List<WorkUserJson> userList = WorkUserDao.loadAll();
                String[] data = new String[userList.size()];
                for(int i = 0;i < userList.size();i++){
                    data[i] = userList.get(i).getNAME();
                }

                DialogUtil.createChecksDialog(this, data, mOperationPerson);
                break;
        }
    }
}
