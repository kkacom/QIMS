package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zhilian.api.StrKit;
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
import butterknife.ButterKnife;

/**
 * 环境设施
 */
public class EnvironmentalFacility extends BaseModuleActivity {
	Long id1;
	Long id2;
	Long id3;
	Long id4;
	Long id5;
	@BindView(R.id.rb_yes1_3_1)
	RadioButton yes1_3_1;
	@BindView(R.id.rb_no1_3_1)
	RadioButton no1_3_1;
	@BindView(R.id.rb_yes1_3_2)
	RadioButton yes1_3_2;
	@BindView(R.id.rb_no1_3_2)
	RadioButton no1_3_2;
	@BindView(R.id.rb_yes1_3_3)
	RadioButton yes1_3_3;
	@BindView(R.id.rb_no1_3_3)
	RadioButton no1_3_3;
	@BindView(R.id.rb_yes1_3_4)
	RadioButton yes1_3_4;
	@BindView(R.id.rb_no1_3_4)
	RadioButton no1_3_4;
	@BindView(R.id.rb_yes1_3_5)
	RadioButton yes1_3_5;
	@BindView(R.id.rb_no1_3_5)
	RadioButton no1_3_5;

	@BindView(R.id.cb1_condition1_3_1)// 自有
	CheckBox cb1_condition1_3_1;
	@BindView(R.id.cb2_condition1_3_1)// 不足三年
	CheckBox cb2_condition1_3_1;
	@BindView(R.id.et_rent1_3_1)
	EditText rent1_3_1;// 每平米月租
	@BindView(R.id.ll_date_1_3_1)
	LinearLayout selectDate_1_3_1;// 选择签约时间
	@BindView(R.id.tv_date_1_3_1)
	TextView date_1_3_1;// 显示签约时间

	@BindView(R.id.ll_date_1_3_2)
	LinearLayout selectDate_1_3_2;// 选择验收时间
	@BindView(R.id.tv_date_1_3_2)
	TextView date_1_3_2;// 显示验收时间
	@BindView(R.id.cb_condition1_3_2)
	CheckBox cb_condition1_3_2;// 自述手续已办、未见原件
	@BindView(R.id.cb_firefighting1_3_2)
	CheckBox cb_firefighting1_3_2;//消防设施有效、明确
	@BindView(R.id.cb_manage1_3_2)
	CheckBox cb_manage1_3_2;//有管理制度或描述

	@BindView(R.id.et_advise1_3_3)
	EditText et_advise1_3_3;//涉及安全建议项
	@BindView(R.id.et_unit1_3_3)
	EditText et_unit1_3_3;//验收单位简称

	@BindView(R.id.tv_date_1_3_4)
	TextView tv_date_1_3_4;//有效期至
	@BindView(R.id.ll_date_1_3_4)
	LinearLayout ll_date_1_3_4;//选择有效期时间
	@BindView(R.id.cb_certificate1_3_4)
	CheckBox cb_certificate1_3_4;//查有环评证书
	@BindView(R.id.cb_environment1_3_4)
	CheckBox cb_environment1_3_4;//环保措施有效、明确
	@BindView(R.id.cb_manage1_3_4)
	CheckBox cb_manage1_3_4;//有管理制度或描述

	@BindView(R.id.cb_danger1_3_5)
	CheckBox cb_danger1_3_5;//危险、告诫警示清晰到位
	@BindView(R.id.cb_operation1_3_5)
	CheckBox cb_operation1_3_5;//操作规程挂墙明示
	@BindView(R.id.cb_manage1_3_5)
	CheckBox cb_manage1_3_5;//有管理制度或描述
	@BindView(R.id.environmental_facility_bottom)
	LinearLayout mEnvironmentalFacilityBottom;

	String cid;
	int uploadStatus1, uploadStatus2, uploadStatus3, uploadStatus4, uploadStatus5;
	List<CaTestJson> list1;
	List<CaTestJson> list2;
	List<CaTestJson> list3;
	List<CaTestJson> list4;
	List<CaTestJson> list5;

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_environmental_facility;
	}

	/**
	 * 初始化数据
	 */
	@Override
	protected void initData() {
		cid = String.valueOf(Common.getWid());
		list1 = CaTestDao.query(cid, "1.3.1");
		list2 = CaTestDao.query(cid, "1.3.2");
		list3 = CaTestDao.query(cid, "1.3.3");
		list4 = CaTestDao.query(cid, "1.3.4");
		list5 = CaTestDao.query(cid, "1.3.5");

		if (list1.size() > 0) {
			if (list1.get(0).getUPLOADSTATUS() >= 1){
				uploadStatus1 = 1;
			}
			String choose[] = list1.get(0).getCHOOSE().split(",",-1);
			if (choose[0].equals("1")) {
				yes1_3_1.setChecked(true);
			} else {
				no1_3_1.setChecked(true);
			}

			if (choose[2].equals("1")) {// 自有？
				cb1_condition1_3_1.setChecked(true);
				String[] field1 = list1.get(0).getFIELD1().split(",",-1);
				if (StrKit.notBlank(field1[0])) {
					rent1_3_1.setText(field1[0]);// 每平米月租
				}
				if (StrKit.notBlank(field1[1])) {
					date_1_3_1.setText(field1[1]);// 签约时间
				}
			}
			if (choose[3].equals("1")) {// 不足三年？
				cb2_condition1_3_1.setChecked(true);
			}
			id1 = list1.get(0).getID();
		}else {
			uploadStatus1 = 0;
		}

		if (list2.size() > 0) {
			if (list2.get(0).getUPLOADSTATUS() >= 1){
				uploadStatus2 = 1;
			}
			String[] choose = list2.get(0).getCHOOSE().split(",",-1);
			if (choose[0].equals("1")) {
				yes1_3_2.setChecked(true);
			} else {
				no1_3_2.setChecked(true);
			}

			if (StrKit.notBlank(list2.get(0).getFIELD1())) {
				date_1_3_2.setText(list2.get(0).getFIELD1());// 验收时间
			}

			if (choose[2].equals("1")) {// 自述手续已办、未见原件？
				cb_condition1_3_2.setChecked(true);
			}
			if (choose[3].equals("1")) {
				cb_firefighting1_3_2.setChecked(true);
			}
			if (choose[4].equals("1")) {
				cb_manage1_3_2.setChecked(true);
			}
			id2 = list2.get(0).getID();
		}else {
			uploadStatus2 = 0;
		}

		if (list3.size() > 0) {
			if (list3.get(0).getUPLOADSTATUS() >= 1){
				uploadStatus3 = 1;
			}
			String[] choose = list3.get(0).getCHOOSE().split(",", -1);
			if (choose[0].equals("1")) {
				yes1_3_3.setChecked(true);
			} else {
				no1_3_3.setChecked(true);
			}

			String[] field1 = list3.get(0).getFIELD1().split(",",-1);
			if (StrKit.notBlank(field1[0])) {
				et_advise1_3_3.setText(field1[0]);// 涉及安全建议项
			}
			if (StrKit.notBlank(field1[1])) {
				et_unit1_3_3.setText(field1[1]);// 验收单位简称
			}
			id3 = list3.get(0).getID();
		}else {
			uploadStatus3 = 0;
		}

		if (list4.size() > 0) {
			if (list4.get(0).getUPLOADSTATUS() >= 1){
				uploadStatus4 = 1;
			}
			String[] choose = list4.get(0).getCHOOSE().split(",",-1);
			if (choose[0].equals("1")) {
				yes1_3_4.setChecked(true);
			} else {
				no1_3_4.setChecked(true);
			}

			if (StrKit.notBlank(list4.get(0).getFIELD1())) {
				tv_date_1_3_4.setText(list4.get(0).getFIELD1());// 验收时间
			}


			if (choose[2].equals("1")) {
				cb_certificate1_3_4.setChecked(true);
			}
			if (choose[3].equals("1")) {
				cb_environment1_3_4.setChecked(true);
			}
			if (choose[4].equals("1")) {
				cb_manage1_3_4.setChecked(true);
			}
			id4 = list4.get(0).getID();
		}else {
			uploadStatus4 = 0;
		}

		if (list5.size() > 0) {
			if (list5.get(0).getUPLOADSTATUS() >= 1){
				uploadStatus5 = 1;
			}
			String[] choose = list5.get(0).getCHOOSE().split(",",-1);
			if (choose[0].equals("1")) {
				yes1_3_5.setChecked(true);
			} else {
				no1_3_5.setChecked(true);
			}

			if (choose[2].equals("1")) {
				cb_danger1_3_5.setChecked(true);
			}
			if (choose[3].equals("1")) {
				cb_operation1_3_5.setChecked(true);
			}
			if (choose[4].equals("1")) {
				cb_manage1_3_5.setChecked(true);
			}
			id5 = list5.get(0).getID();
		}else {
			uploadStatus5 = 0;
		}
	}

	/**
	 * 事件监听
	 */
	@Override
	protected void listener() {
		photo.setVisibility(View.GONE);
		video.setVisibility(View.GONE);

		eTListener(rent1_3_1);// 每平米月租
		eTListener(et_advise1_3_3);// 涉及安全建议项
		eTListener(et_unit1_3_3);// 验收单位简称
		tVListener(date_1_3_1);// 签约时间
		tVListener(date_1_3_2);// 验收时间
		tVListener(tv_date_1_3_4);// 有效期至
		rBListener(yes1_3_1);// 1.3.1
		rBListener(yes1_3_2);// 1.3.2
		rBListener(yes1_3_3);// 1.3.3
		rBListener(yes1_3_4);// 1.3.4
		rBListener(yes1_3_5);// 1.3.5
		cBListener(cb1_condition1_3_1);// 自有
		cBListener(cb2_condition1_3_1);// 不足三年
		cBListener(cb_condition1_3_2);// 自述手续已办、未见原件
		cBListener(cb_firefighting1_3_2);// 消防设施有效、明确
		cBListener(cb_manage1_3_2);// 有管理制度或描述
		cBListener(cb_certificate1_3_4);// 查有环评证书
		cBListener(cb_environment1_3_4);// 环保措施有效、明确
		cBListener(cb_manage1_3_4);// 有管理制度或描述
		cBListener(cb_danger1_3_5);// 危险、告诫警示清晰到位
		cBListener(cb_operation1_3_5);// 操作规程挂墙明示
		cBListener(cb_manage1_3_5);// 有管理制度或描述

		lLListener(selectDate_1_3_1, date_1_3_1);// 签约时间
		lLListener(selectDate_1_3_2, date_1_3_2);// 验收时间
		lLListener(ll_date_1_3_4, tv_date_1_3_4);// 有效期至

		// 自有
		cb1_condition1_3_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					rent1_3_1.setEnabled(false);// 每平米月租
					rent1_3_1.setText("");
					cb2_condition1_3_1.setChecked(false);// 不足三年
					selectDate_1_3_1.setEnabled(false);// 选择签约时间
					date_1_3_1.setText(""); //时间text
				} else {
					rent1_3_1.setEnabled(true);// 每平米月租
					selectDate_1_3_1.setEnabled(true);// 选择签约时间
				}
				saveData("");
			}
		});

		// 不足三年
		cb2_condition1_3_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					cb1_condition1_3_1.setChecked(false);
					rent1_3_1.setEnabled(true);// 每平米月租
					selectDate_1_3_1.setEnabled(true);// 选择签约时间
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
	 * 保存打分数据
	 *
	 * @param status 状态（1 完成、2 未完成）
	 */
	@Override
	protected void saveStatus(String status) {
		saveData(status);
	}

	/**
	 * 判断EditText文字信息不能为~的事件监听
	 */
	private void judge(final EditText editText) {
		editText.addTextChangedListener(new TextWatcher() {
			String tmp = "";
			String digits = "~";

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				tmp = s.toString();
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				editText.setSelection(s.length());
			}

			@Override
			public void afterTextChanged(Editable s) {
				String str = s.toString();
				if (str.equals(tmp)) {
					return;
				}
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < str.length(); i++) {
					if (digits.indexOf(str.charAt(i)) < 0) {
						sb.append(str.charAt(i));
					} else {
						ToastUtils.show("提示：不能输入~");
					}
				}
				tmp = sb.toString();
				editText.setText(tmp);

				saveData("");
			}
		});
	}

	/**
	 * LinearLayout的事件监听
	 *
	 * @param tv 显示时间的的控件
	 */
	private void lLListener(LinearLayout ll, final TextView tv) {
		ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogUtil.DateDialog date = new DialogUtil.DateDialog(tv);
				date.show(getFragmentManager(), "Picker_date");
			}
		});
	}

	/**
	 * RadioButton的事件监听
	 */
	private void rBListener(RadioButton rb) {
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
	 * EditText的事件监听
	 */
	private void eTListener(EditText et) {
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
	 * EditText的事件监听
	 */
	private void tVListener(TextView tv) {
		tv.addTextChangedListener(new TextWatcher() {
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
	 * 保存数据
	 *
	 * @param status 状态
	 */
	private void saveData(String status) {
		String value1 = "";
		if (yes1_3_1.isChecked()) {
			value1 = "1,0";
		} else {
			value1 = "0,1";
		}

		String value2 = "";
		if (yes1_3_2.isChecked()) {
			value2 = "1,0";
		} else {
			value2 = "0,1";
		}

		String value3 = "";
		if (yes1_3_3.isChecked()) {
			value3 = "1,0";
		} else {
			value3 = "0,1";
		}

		String value4 = "";
		if (yes1_3_4.isChecked()) {
			value4 = "1,0";
		} else {
			value4 = "0,1";
		}

		String value5 = "";
		if (yes1_3_5.isChecked()) {
			value5 = "1,0";
		} else {
			value5 = "0,1";
		}

		int condition1;
		int condition2;
		String rent;
		String date1;
		if (cb1_condition1_3_1.isChecked()) {// 自有？
			condition1 = 1;
			rent = "";
			date1 = "";
			condition2 = 0;
		} else {
			condition1 = 0;
			rent = rent1_3_1.getText().toString();// 每平米月租
			date1 = date_1_3_1.getText().toString();// 签约时间
			if (cb2_condition1_3_1.isChecked()) {// 不足三年？
				condition2 = 1;
			} else {
				condition2 = 0;
			}
		}
		String choose131 = value1 + "," + condition1 + "," + condition2;
		String condition = rent + "," + date1;

		String select ,firefighting ,manage;
		if (cb_condition1_3_2.isChecked()) {// 自述手续已办、未见原件
			select = "1";
		} else {
			select = "0";
		}
		if (cb_firefighting1_3_2.isChecked()) {// 消防设施有效、明确
			firefighting = "1";
		} else {
			firefighting = "0";
		}
		if (cb_manage1_3_2.isChecked()) {// 有管理制度或描述
			manage = "1";
		} else {
			manage = "0";
		}
		String choose132 = value2 + "," + select +","+ firefighting +","+ manage;
		String date2 = date_1_3_2.getText().toString();// 验收时间(有效期至)

		String advise, uni;// 1_3_3
		advise = et_advise1_3_3.getText().toString();
		uni = et_unit1_3_3.getText().toString();
		String field3 = advise +","+ uni;

		String certificate ,environment ,manage4;// 134
		if (cb_certificate1_3_4.isChecked()) {// 查有环评证书
			certificate = "1";
		} else {
			certificate = "0";
		}
		if (cb_environment1_3_4.isChecked()) {// 环保措施有效、明确
			environment = "1";
		} else {
			environment = "0";
		}
		if (cb_manage1_3_4.isChecked()) {// 有管理制度或描述
			manage4 = "1";
		} else {
			manage4 = "0";
		}
		String choose134 = value4 +","+ certificate +","+ environment +","+ manage4;
		String date4 = tv_date_1_3_4.getText().toString();// 验收时间(有效期至)

		String danger ,operation ,manage5;
		if (cb_danger1_3_5.isChecked()) {// 查有环评证书
			danger = "1";
		} else {
			danger = "0";
		}
		if (cb_operation1_3_5.isChecked()) {// 环保措施有效、明确
			operation = "1";
		} else {
			operation = "0";
		}
		if (cb_manage1_3_5.isChecked()) {// 有管理制度或描述
			manage5 = "1";
		} else {
			manage5 = "0";
		}
		String choose135 = value5 +","+ danger +","+ operation +","+ manage5;

		CaTestJson caTestJson1 = null;
		CaTestJson caTestJson2 = null;
		CaTestJson caTestJson3 = null;
		CaTestJson caTestJson4 = null;
		CaTestJson caTestJson5 = null;

		if (id1 != null) {
			caTestJson1 = new CaTestJson(id1, cid, "1.3.1", choose131, status, condition, uploadStatus1);
		} else {
			caTestJson1 = new CaTestJson(cid, "1.3.1", choose131, status, condition, uploadStatus1);
			CaTestDao.insertOrReplace(caTestJson1);
			list1 = CaTestDao.query(cid, "1.3.1");
			if (list1 != null && list1.size() > 0){
				id1 = list1.get(0).getID();
			}
		}

		if (id2 != null) {
			caTestJson2 = new CaTestJson(id2, cid, "1.3.2", choose132, status, date2, uploadStatus2);
		} else {
			caTestJson2 = new CaTestJson(cid, "1.3.2", choose132, status, date2, uploadStatus2);
			CaTestDao.insertOrReplace(caTestJson2);
			list2 = CaTestDao.query(cid, "1.3.2");
			if (list2 != null && list2.size() > 0){
				id2 = list2.get(0).getID();
			}
		}

		if (id3 != null) {
			caTestJson3 = new CaTestJson(id3, cid, "1.3.3", value3, status, field3, uploadStatus3);
		} else {
			caTestJson3 = new CaTestJson(cid, "1.3.3", value3, status, field3, uploadStatus3);
			CaTestDao.insertOrReplace(caTestJson3);
			list3 = CaTestDao.query(cid, "1.3.3");
			if (list3 != null && list3.size() > 0){
				id3 = list3.get(0).getID();
			}
		}

		if (id4 != null) {
			caTestJson4 = new CaTestJson(id4, cid, "1.3.4", choose134, status, date4, uploadStatus4);
		} else {
			caTestJson4 = new CaTestJson(cid, "1.3.4", choose134, status, date4, uploadStatus4);
			CaTestDao.insertOrReplace(caTestJson4);
			list4 = CaTestDao.query(cid, "1.3.4");
			if (list4 != null && list4.size() > 0){
				id4 = list4.get(0).getID();
			}
		}

		if (id5 != null) {
			caTestJson5 = new CaTestJson(id5, cid, "1.3.5", choose135, status, "", uploadStatus5);
		} else {
			caTestJson5 = new CaTestJson(cid, "1.3.5", choose135, status, "", uploadStatus5);
			CaTestDao.insertOrReplace(caTestJson5);
			list5 = CaTestDao.query(cid, "1.3.5");
			if (list5 != null && list5.size() > 0){
				id5 = list5.get(0).getID();
			}
		}

		CaTestDao.insertOrReplace(caTestJson1);
		CaTestDao.insertOrReplace(caTestJson2);
		CaTestDao.insertOrReplace(caTestJson3);
		CaTestDao.insertOrReplace(caTestJson4);
		CaTestDao.insertOrReplace(caTestJson5);

		if(status.equals("1")){
			String judge;
			WorkAbilityJson workAbilityJson = Common.getWorkAbilityJson();
			if (workAbilityJson.getSTATUS() >= 1){
				workAbilityJson.setSTATUS(1);
			}
			if (value1.equals("1,0") && value2.equals("1,0") && value3.equals("1,0") && value4.equals("1,0")){
				workAbilityJson.setITEM1_3("Y");
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
				workAbilityJson.setITEM1_3("N");
				workAbilityJson.setITEM("N");
				judge = "0,1";
			}
			String itemTwo = "1.3";
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
				workAbilityJson.setITEM1_3("Y");
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
				workAbilityJson.setITEM1_3("N");
				workAbilityJson.setITEM("N");
				judge = "N";
			}
			String itemTwo = "1.3";
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}
}
