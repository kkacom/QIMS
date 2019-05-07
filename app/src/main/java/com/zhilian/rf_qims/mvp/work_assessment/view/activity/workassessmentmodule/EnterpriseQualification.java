package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.base.BaseModuleActivity;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.CaTestDao;
import com.zhilian.rf_qims.entity.CaTestJson;
import com.zhilian.rf_qims.entity.WorkAbilityDao;
import com.zhilian.rf_qims.entity.WorkAbilityJson;
import com.zhilian.rf_qims.util.DialogUtil;
import com.zhilian.rf_qims.util.ToastUtils;

import java.util.List;

import butterknife.BindView;

/**
 * 企业资质
 */
public class EnterpriseQualification extends BaseModuleActivity {
    Long id1;
    Long id2;
    Long id3;
    Long id4;
    @BindView(R.id.rb_yes1_1_1)
	RadioButton yes1_1_1;
    @BindView(R.id.rb_no1_1_1)
	RadioButton no1_1_1;
    @BindView(R.id.rb_yes1_1_2)
	RadioButton yes1_1_2;
    @BindView(R.id.rb_no1_1_2)
	RadioButton no1_1_2;
    @BindView(R.id.rb_yes1_1_3)
	RadioButton yes1_1_3;
    @BindView(R.id.rb_no1_1_3)
	RadioButton no1_1_3;
    @BindView(R.id.rb_yes1_1_4)
    RadioButton yes1_1_4;
    @BindView(R.id.rb_no1_1_4)
    RadioButton no1_1_4;

    @BindView(R.id.et_address1_1_1)
	EditText address1_1_1;// 居住地
    @BindView(R.id.et_money1_1_2)
	EditText money1_1_2;// 注册资金
    @BindView(R.id.et_code_number1_1_3)
	EditText code_number1_1_3;// 注册证号
    @BindView(R.id.cb_first_1_1_4)
    CheckBox cb_first_1_1_4;//首次
    @BindView(R.id.cb_review_1_1_4)
    CheckBox cb_review_1_1_4;//复查
    @BindView(R.id.et_code_number1_1_4)
    EditText et_code_number1_1_4;// 证号
    @BindView(R.id.ll_date_1_2_1)
    LinearLayout ll_date_1_2_1;// 时间点击布局
    @BindView(R.id.tv_date_1_2_1)
    TextView tv_date_1_2_1;//时间

    String cid;
    int uploadStatus1, uploadStatus2, uploadStatus3, uploadStatus4;
    List<CaTestJson> list1;
    List<CaTestJson> list2;
    List<CaTestJson> list3;
    List<CaTestJson> list4;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_enterprise_qualification;
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        cid = String.valueOf(Common.getWid());
        list1 = CaTestDao.query(cid, "1.1.1");
        list2 = CaTestDao.query(cid, "1.1.2");
        list3 = CaTestDao.query(cid, "1.1.3");
        list4 = CaTestDao.query(cid, "1.1.4");

        if(list1.size() > 0){
            if (list1.get(0).getUPLOADSTATUS() >= 1){
                uploadStatus1 = 1;
            }
            String[] choose = list1.get(0).getCHOOSE().split(",", -1);
            if(choose[0].equals("1")){
                yes1_1_1.setChecked(true);
            }else{
                no1_1_1.setChecked(true);
            }
            address1_1_1.setText(list1.get(0).getFIELD1());
            id1 = list1.get(0).getID();
        }else {
            uploadStatus1 = 0;
        }

        if(list2.size() > 0){
            if (list2.get(0).getUPLOADSTATUS() >= 1){
                uploadStatus2 = 1;
            }
            String[] choose = list2.get(0).getCHOOSE().split(",", -1);
            if(choose[0].equals("1")){
                yes1_1_2.setChecked(true);
            }else{
                no1_1_2.setChecked(true);
            }
            money1_1_2.setText(list2.get(0).getFIELD1());
            id2 = list2.get(0).getID();
        }else {
            uploadStatus2 = 0;
        }

        if(list3.size() > 0){
            if (list3.get(0).getUPLOADSTATUS() >= 1){
                uploadStatus3 = 1;
            }
            String[] choose = list3.get(0).getCHOOSE().split(",", -1);
            if(choose[0].equals("1")){
                yes1_1_3.setChecked(true);
            }else{
                no1_1_3.setChecked(true);
            }
            code_number1_1_3.setText(list3.get(0).getFIELD1());
            id3 = list3.get(0).getID();
        }else {
            uploadStatus3 = 0;
        }

        if(list4.size() > 0){
            if (list4.get(0).getUPLOADSTATUS() >= 1){
                uploadStatus4 = 1;
            }
            String[] choose = list4.get(0).getCHOOSE().split(",",-1);
            if(choose[0].equals("1")){
                yes1_1_4.setChecked(true);
            }else{
                no1_1_4.setChecked(true);
            }
            if (choose[2].equals("1")){
                cb_first_1_1_4.setChecked(true);
            }
            if (choose[3].equals("1")){
                cb_review_1_1_4.setChecked(true);
            }
            String[] field1 = list4.get(0).getFIELD1().split(",",-1);
            et_code_number1_1_4.setText(field1[0]);
            tv_date_1_2_1.setText(field1[1]);
            id4 = list4.get(0).getID();
        }else {
            uploadStatus4 = 0;
        }
    }

    /**
     * 事件监听
     */
    @Override
    protected void listener() {
        photo.setVisibility(View.GONE);
        video.setVisibility(View.GONE);

        eTListener(address1_1_1);// 居住地RadioButton
        eTListener(money1_1_2);// 注册资金
        eTListener(code_number1_1_3);// 注册证号
        eTListener(et_code_number1_1_4);// 注册证号
        cBListener(cb_first_1_1_4);// 首次checkBox
        cBListener(cb_review_1_1_4);// 复查
        rBListener(yes1_1_1);// 1.1.1
        rBListener(yes1_1_2);// 1.1.2
        rBListener(yes1_1_3);// 1.1.3
        rBListener(yes1_1_4);// 1.1.4

        // 1.1.4颁证时间
        ll_date_1_2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.DateDialog date = new DialogUtil.DateDialog(tv_date_1_2_1);
                date.show(getFragmentManager(), "Picker_date");
                saveData("");
            }
        });

        // 时间的变化监听
        tv_date_1_2_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                saveData("");
            }
        });
        //首次
        cb_first_1_1_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    cb_review_1_1_4.setChecked(false);
                }
                saveData("");
            }
        });
        //复查
        cb_review_1_1_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    cb_first_1_1_4.setChecked(false);
                }
                saveData("");
            }
        });
    }

    @Override
    protected void photo() {

    }

    @Override
    protected void video() {

    }

    /**
     * 保存和更改状态
     * @param status 状态（1 完成、2 未完成）
     */
    @Override
    protected void saveStatus(String status) {
        saveData(status);
    }

    /**
     * EditText的事件监听
     */
    private void eTListener(EditText et){
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                saveData("");
            }
        });
    }

    /**
     * RadioButton的事件监听
     */
    private void rBListener(RadioButton rb){
        rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveData("");
            }
        });
    }

    /**
     * CheckBox的事件监听
     */
    private void cBListener(CheckBox cb) {
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveData("");
            }
        });
    }

    /**
     * 保存数据
     * @param status 状态
     */
    public void saveData(String status){
        String value1 = "";
        if(yes1_1_1.isChecked()){
            value1 = "1,0";
        }else{
            value1 = "0,1";
        }

        String value2 = "";
        if(yes1_1_2.isChecked()){
            value2 = "1,0";
        }else{
            value2 = "0,1";
        }

        String value3 = "";
        if(yes1_1_3.isChecked()){
            value3 = "1,0";
        }else{
            value3 = "0,1";
        }

        String value4 = "";
        if(yes1_1_4.isChecked()){
            value4 = "1,0";
        }else{
            value4 = "0,1";
        }

        String address = address1_1_1.getText().toString();// 居住地
        String money = money1_1_2.getText().toString();// 注册资金
        String number = code_number1_1_3.getText().toString();// 注册证号

        String choose4 = "", field1 = "", number1 = " ", date = " ";
        choose4 = value4 +","+ "0,0";// 如果用户首次和复查都没选无法进如以下判断先赋值
        field1 = number1 +","+ date;
        if (cb_first_1_1_4.isChecked()) { // 首次
            choose4 = value4 +","+ "1,0";
            number1 = et_code_number1_1_4.getText().toString();
            date = tv_date_1_2_1.getText().toString();//1.1.4时间;
            field1 = number1 +","+ date;
        }
        if (cb_review_1_1_4.isChecked()){// 复查
            choose4 = value4 +","+ "0,1";
            number1 = et_code_number1_1_4.getText().toString();//1.1.4证号
            date = tv_date_1_2_1.getText().toString();//1.1.4时间
            field1 = number1 +","+ date;
        }

        CaTestJson caTestJson1 = null;
        CaTestJson caTestJson2 = null;
        CaTestJson caTestJson3 = null;
        CaTestJson caTestJson4 = null;

        if(id1 != null){
            caTestJson1 = new CaTestJson(id1, cid, "1.1.1", value1, status, address, uploadStatus1);
        }else{
            caTestJson1 = new CaTestJson(cid, "1.1.1", value1, status, address, uploadStatus1);
			CaTestDao.insertOrReplace(caTestJson1);
            list1 = CaTestDao.query(cid, "1.1.1");
            if (list1 != null && list1.size() > 0){
                id1 = list1.get(0).getID();
            }
        }

        if(id2 != null){
            caTestJson2 = new CaTestJson(id2, cid, "1.1.2", value2, status, money, uploadStatus2);
        }else{
            caTestJson2 = new CaTestJson(cid, "1.1.2", value2, status, money, uploadStatus2);
			CaTestDao.insertOrReplace(caTestJson2);
			list2 = CaTestDao.query(cid, "1.1.2");
			if (list2 != null && list2.size() > 0){
			    id2 = list2.get(0).getID();//第一次之后要赋值，不然会不停创建新数据
            }
        }

        if(id3 != null){
            caTestJson3 = new CaTestJson(id3, cid, "1.1.3", value3, status, number, uploadStatus3);
        }else{
            caTestJson3 = new CaTestJson(cid, "1.1.3", value3, status, number, uploadStatus3);
			CaTestDao.insertOrReplace(caTestJson3);
            list3 = CaTestDao.query(cid, "1.1.3");
            if (list3 != null && list3.size() > 0){
                id3 = list3.get(0).getID();
            }
        }

        if(id4 != null){
            caTestJson4 = new CaTestJson(id4, cid, "1.1.4", choose4, status, field1, uploadStatus4);
        }else{
            caTestJson4 = new CaTestJson(cid, "1.1.4", choose4, status, field1, uploadStatus4);
			CaTestDao.insertOrReplace(caTestJson4);
            list4 = CaTestDao.query(cid, "1.1.4");
            if (list4 != null && list4.size() > 0){
                id4 = list4.get(0).getID();
            }
        }

        CaTestDao.insertOrReplace(caTestJson1);
        CaTestDao.insertOrReplace(caTestJson2);
        CaTestDao.insertOrReplace(caTestJson3);
        CaTestDao.insertOrReplace(caTestJson4);

        if(status.equals("1")){
            String judge;
            WorkAbilityJson workAbilityJson = Common.getWorkAbilityJson();
            if (workAbilityJson.getSTATUS() >= 1){
                workAbilityJson.setSTATUS(1);
            }
            if (value1.equals("1,0") && value2.equals("1,0") && value3.equals("1,0") && value4.equals("1,0")){
                workAbilityJson.setITEM1_1("Y");
                judge = "1,0";
                String[] mScore1 = new String[3];
                mScore1[0] = workAbilityJson.getITEM1_1();
                mScore1[1] = workAbilityJson.getITEM1_2();
                mScore1[2] = workAbilityJson.getITEM1_3();
                if(mScore1[0] != null && mScore1[1] != null && mScore1[2] != null){
                    if (mScore1[0].equals("Y") && mScore1[1].equals("Y") && mScore1[2].equals("Y")){
                        workAbilityJson.setITEM("Y");
                        CaTestJson caTestJson = CaTestDao.queryOne(cid, "1");
                        if (caTestJson == null){
                            caTestJson = new CaTestJson(cid, "1", judge, "1", 0);
                        }else {
                            caTestJson.setCHOOSE(judge);
                            caTestJson.setSTATUS("1");
                        }
                        CaTestDao.insertOrReplace(caTestJson);
                    }
                }
            }else {
                workAbilityJson.setITEM1_1("N");
                workAbilityJson.setITEM("N");
                judge = "0,1";
            }
            String itemTwo = "1.1";
            CaTestJson caTestJson = CaTestDao.queryOne(cid, itemTwo);
            if (caTestJson == null){
                caTestJson = new CaTestJson(cid, itemTwo, judge, "1", 0);
            }else {
                caTestJson.setCHOOSE(judge);
                caTestJson.setSTATUS("1");
            }
            CaTestDao.insertOrReplace(caTestJson);
            WorkAbilityDao.insertOrReplace(workAbilityJson);
            ToastUtils.show("<完成>保存成功");
        }else if (status.equals("2")){
            String judge;
            WorkAbilityJson workAbilityJson = Common.getWorkAbilityJson();
            if (workAbilityJson.getSTATUS() >= 1){
                workAbilityJson.setSTATUS(1);
            }
            if (value1.equals("1,0") && value2.equals("1,0") && value3.equals("1,0") && value4.equals("1,0")){
                workAbilityJson.setITEM1_1("Y");
                judge = "1,0";
                String[] mScore1 = new String[3];
                mScore1[0] = workAbilityJson.getITEM1_1();
                mScore1[1] = workAbilityJson.getITEM1_2();
                mScore1[2] = workAbilityJson.getITEM1_3();
                if(mScore1[0] != null && mScore1[1] != null && mScore1[2] != null){
                    if (mScore1[0].equals("Y") && mScore1[1].equals("Y") && mScore1[2].equals("Y")){
                        workAbilityJson.setITEM("Y");
                        CaTestJson caTestJson = CaTestDao.queryOne(cid, "1");
                        if (caTestJson == null){
                            caTestJson = new CaTestJson(cid, "1", judge, "2", 0);
                        }else {
                            caTestJson.setCHOOSE(judge);
                            caTestJson.setSTATUS("2");
                        }
                        CaTestDao.insertOrReplace(caTestJson);
                    }
                }
            }else {
                workAbilityJson.setITEM1_1("N");
                workAbilityJson.setITEM("N");
                judge = "N";
            }
            String itemTwo = "1.1";
            CaTestJson caTestJson = CaTestDao.queryOne(cid, itemTwo);
            if (caTestJson == null){
                caTestJson = new CaTestJson(cid, itemTwo, judge, "2", 0);
            }else {
                caTestJson.setCHOOSE(judge);
                caTestJson.setSTATUS("2");
            }
            CaTestDao.insertOrReplace(caTestJson);
            WorkAbilityDao.insertOrReplace(workAbilityJson);
            ToastUtils.show("<未完成>保存成功");
        }
    }

}
