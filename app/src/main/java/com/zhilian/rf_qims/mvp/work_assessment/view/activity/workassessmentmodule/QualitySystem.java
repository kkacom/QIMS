package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import butterknife.ButterKnife;

/**
 * 质量体系
 */
public class QualitySystem extends BaseModuleActivity {
	Long id1;
	Long id2;
	Long id3;
	Long id4;
	@BindView(R.id.rb_yes1_2_1)
	RadioButton yes1_2_1;
	@BindView(R.id.rb_no1_2_1)
	RadioButton no1_2_1;
	@BindView(R.id.rb_yes1_2_2)
	RadioButton yes1_2_2;
	@BindView(R.id.rb_no1_2_2)
	RadioButton no1_2_2;
	@BindView(R.id.rb_yes1_2_3)
	RadioButton yes1_2_3;
	@BindView(R.id.rb_no1_2_3)
	RadioButton no1_2_3;
	@BindView(R.id.rb_yes1_2_4)
	RadioButton yes1_2_4;
	@BindView(R.id.rb_no1_2_4)
	RadioButton no1_2_4;

	@BindView(R.id.ll_date_1_2_1)
	LinearLayout selectDate_1_2_1;// 选择时间
	@BindView(R.id.tv_date_1_2_1)
	TextView date_1_2_1;// 显示时间
	@BindView(R.id.cb1_1_2_1)// 国家认证认可信息查询系统，认证覆盖防护设备生产过程且在有效期内
		CheckBox mCb1121;
	@BindView(R.id.cb2_1_2_1)// 认证已过期
		CheckBox mCb2121;
	@BindView(R.id.cb1_1_2_2)// 建有完整的质量体系文件且适用
		CheckBox mCb1122;
	@BindView(R.id.cb2_1_2_2)// 建有作业操作规程及工艺流程
		CheckBox mCb2122;
	@BindView(R.id.cb3_1_2_2)// 人员培训、岗位确认记录
		CheckBox mCb3122;
	@BindView(R.id.cb4_1_2_2)// 材料、产品出入库记录
		CheckBox mCb4122;
	@BindView(R.id.cb5_1_2_2)// 质量检验记录
		CheckBox mCb5122;
	@BindView(R.id.cb6_1_2_2)// 不符合处置记录
		CheckBox mCb6122;
	@BindView(R.id.cb7_1_2_2)// 记录不齐全
		CheckBox mCb7122;
	@BindView(R.id.cb_manage_1_2_3)
	CheckBox cb_manage_1_2_3;//管理清晰、职责分明
	@BindView(R.id.cb_institution_1_2_3)
	CheckBox cb_institution_1_2_3;//制度明确、人员落实
	@BindView(R.id.cb_lack_1_2_3)
	CheckBox cb_lack_1_2_3;//缺运行记录、工作场所
	@BindView(R.id.cb_one_1_2_4)
	CheckBox cb_one_1_2_4;//下料、加工、成型、仓储区域明确
	@BindView(R.id.cb_two_1_2_4)
	CheckBox cb_two_1_2_4;//流程图挂墙明示、物品标识清晰
	@BindView(R.id.cb_three_1_2_4)
	CheckBox cb_three_1_2_4;//进货、检测、出品、项目有台账
	@BindView(R.id.cb_four_1_2_4)
	CheckBox cb_four_1_2_4;//凌乱无序、指示不清
	@BindView(R.id.quality_system_bottom)
	LinearLayout mQualitySystemBottom;

	String cid;
	int uploadStatus1, uploadStatus2, uploadStatus3, uploadStatus4;
	List<CaTestJson> list1;
	List<CaTestJson> list2;
	List<CaTestJson> list3;
	List<CaTestJson> list4;

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_quality_system;
	}

	/**
	 * 初始化数据
	 */
	@Override
	protected void initData() {
		cid = String.valueOf(Common.getWid());
		list1 = CaTestDao.query(cid, "1.2.1");
		list2 = CaTestDao.query(cid, "1.2.2");
		list3 = CaTestDao.query(cid, "1.2.3");
		list4 = CaTestDao.query(cid, "1.2.4");

		if (list1.size() > 0) {
			if (list1.get(0).getUPLOADSTATUS() >= 1) {
				uploadStatus1 = 1;
			}
			String[] choose = list1.get(0).getCHOOSE().split(",",-1);
			if (choose[0].equals("1")) { // Y和N
				yes1_2_1.setChecked(true);
			} else {
				no1_2_1.setChecked(true);
			}
			if (choose[2].equals("1")) {// 国家认证认可信息查询系统，认证覆盖防护设备生产过程且在有效期内
				mCb1121.setChecked(true);
			}
			if (choose[3].equals("1")) {// 认证已过期
				mCb2121.setChecked(true);
			}
			date_1_2_1.setText(list1.get(0).getFIELD1());
			id1 = list1.get(0).getID();
		} else {
			uploadStatus1 = 0;
		}

		if (list2.size() > 0) {
			if (list2.get(0).getUPLOADSTATUS() >= 1) {
				uploadStatus2 = 2;
			}
			String[] choose = list2.get(0).getCHOOSE().split(",",-1);
			if (choose[0].equals("1")) { // Y和N
				yes1_2_2.setChecked(true);
			} else {
				no1_2_2.setChecked(true);
			}
			if (choose[2].equals("1")) {// 建有完整的质量体系文件且适用
				mCb1122.setChecked(true);
			}
			if (choose[3].equals("1")) {// 建有作业操作规程及工艺流程
				mCb2122.setChecked(true);
			}
			if (choose[4].equals("1")) {// 人员培训、岗位确认记录
				mCb3122.setChecked(true);
			}
			if (choose[5].equals("1")) {// 材料、产品出入库记录
				mCb4122.setChecked(true);
			}
			if (choose[6].equals("1")) {// 质量检验记录
				mCb5122.setChecked(true);
			}
			if (choose[7].equals("1")) {// 不符合处置记录
				mCb6122.setChecked(true);
			}
			if (choose[8].equals("1")) {// 记录不齐全
				mCb7122.setChecked(true);
			}

			id2 = list2.get(0).getID();
		} else {
			uploadStatus2 = 0;
		}

		if (list3.size() > 0) {
			if (list3.get(0).getUPLOADSTATUS() >= 1) {
				uploadStatus3 = 1;
			}
			String[] choose = list3.get(0).getCHOOSE().split(",",-1);
			if (choose[0].equals("1")) {
				yes1_2_3.setChecked(true);
			}

			if (choose[2].equals("1")) {//管理清晰、职责分明
				cb_manage_1_2_3.setChecked(true);
			}

			if (choose[3].equals("1")) {//制度明确、人员落实
				cb_institution_1_2_3.setChecked(true);
			}

			if (choose[4].equals("1")) {//缺运行记录、工作场所
				cb_lack_1_2_3.setChecked(true);
			}
			id3 = list3.get(0).getID();
		} else {
			uploadStatus3 = 0;
		}

		if (list4.size() > 0) {
			if (list4.get(0).getUPLOADSTATUS() >= 1) {
				uploadStatus4 = 1;
			}
			String[] choose = list4.get(0).getCHOOSE().split(",",-1);
			if (choose[0].equals("1")) {
				yes1_2_4.setChecked(true);
			}

			if (choose[2].equals("1")) {
				cb_one_1_2_4.setChecked(true);
			}

			if (choose[3].equals("1")) {
				cb_two_1_2_4.setChecked(true);
			}

			if (choose[4].equals("1")) {
				cb_three_1_2_4.setChecked(true);
			}

			if (choose[5].equals("1")) {
				cb_four_1_2_4.setChecked(true);
			}
			id4 = list4.get(0).getID();
		} else {
			uploadStatus4 = 0;
		}
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
	 * 事件监听
	 */
	@Override
	protected void listener() {
		photo.setVisibility(View.GONE);
		video.setVisibility(View.GONE);

		// 时间
		selectDate_1_2_1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogUtil.DateDialog date = new DialogUtil.DateDialog(date_1_2_1);
				date.show(getFragmentManager(), "Picker_date");
			}
		});

		rBListener(yes1_2_1);// 1.2.1
		rBListener(yes1_2_2);// 1.2.2
		rBListener(yes1_2_3);// 1.2.3
		rBListener(yes1_2_4);// 1.2.4
		cBListener(mCb1121);// 国家认证认可信息查询系统，认证覆盖防护设备生产过程且在有效期内
		cBListener(mCb2121);// 认证已过期
		cBListener(mCb1122);// 建有完整的质量体系文件且适用
		cBListener(mCb2122);// 建有作业操作规程及工艺流程
		cBListener(mCb3122);// 人员培训、岗位确认记录
		cBListener(mCb4122);// 材料、产品出入库记录
		cBListener(mCb5122);// 质量检验记录
		cBListener(mCb6122);// 不符合处置记录
		cBListener(mCb7122);// 记录不齐全
		cBListener(cb_manage_1_2_3);// 管理清晰、职责分明
		cBListener(cb_institution_1_2_3);// 制度明确、人员落实
		cBListener(cb_lack_1_2_3);// 缺运行记录、工作场所
		cBListener(cb_one_1_2_4);// 下料、加工、成型、仓储区域明确
		cBListener(cb_two_1_2_4);// 流程图挂墙明示、物品标识清晰
		cBListener(cb_three_1_2_4);// 进货、检测、出品、项目有台账
		cBListener(cb_four_1_2_4);// 凌乱无序、指示不清

		// 时间的变化监听
		date_1_2_1.addTextChangedListener(new TextWatcher() {
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

	@Override
	protected void photo() {

	}

	@Override
	protected void video() {

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
	 * 保存数据
	 *
	 * @param status 状态
	 */
	private void saveData(String status) {
		String value1 = "";
		if (yes1_2_1.isChecked()) {
			value1 = "1,0";
		} else {
			value1 = "0,1";
		}

		String value2 = "";
		if (yes1_2_2.isChecked()) {
			value2 = "1,0";
		} else {
			value2 = "0,1";
		}

		String value3 = "";
		if (yes1_2_3.isChecked()) {
			value3 = "1,0";
		} else {
			value3 = "0,1";
		}

		String value4 = "";
		if (yes1_2_4.isChecked()) {
			value4 = "1,0";
		} else {
			value4 = "0,1";
		}
		//1.2.1
		String field8;
		if (mCb1121.isChecked()) {// 建有完整的质量体系文件且适用
			field8 = "1";
		} else {
			field8 = "0";
		}

		String field9;
		if (mCb2121.isChecked()) {// 建有完整的质量体系文件且适用
			field9 = "1";
		} else {
			field9 = "0";
		}
		String choose121 = value1 +","+ field8 +","+ field9;
		String date = date_1_2_1.getText().toString();// 时间

		//1.2.2
		String field1;
		if (mCb1122.isChecked()) {// 建有完整的质量体系文件且适用
			field1 = "1";
		} else {
			field1 = "0";
		}

		String field2;
		if (mCb2122.isChecked()) {// 建有作业操作规程及工艺流程
			field2 = "1";
		} else {
			field2 = "0";
		}

		String field3;
		if (mCb3122.isChecked()) {// 人员培训、岗位确认记录
			field3 = "1";
		} else {
			field3 = "0";
		}

		String field4;
		if (mCb4122.isChecked()) {// 材料、产品出入库记录
			field4 = "1";
		} else {
			field4 = "0";
		}

		String field5;
		if (mCb5122.isChecked()) {// 质量检验记录
			field5 = "1";
		} else {
			field5 = "0";
		}

		String field6;
		if (mCb6122.isChecked()) {// 不符合处置记录
			field6 = "1";
		} else {
			field6 = "0";
		}

		String field7;
		if (mCb7122.isChecked()) {// 记录不齐全
			field7 = "1";
		} else {
			field7 = "0";
		}
		String choose122 = value2 + "," + field1 + "," + field2 + "," + field3 + "," + field4 + "," + field5 + "," + field6 + "," + field7;

		String manage, institution, lack;
		if (cb_manage_1_2_3.isChecked()) {// 管理清晰、职责分明
			manage = "1";
		} else {
			manage = "0";
		}
		if (cb_institution_1_2_3.isChecked()) {// 制度明确、人员落实
			institution = "1";
		} else {
			institution = "0";
		}
		if (cb_lack_1_2_3.isChecked()) {// 缺运行记录、工作场所
			lack = "1";
		} else {
			lack = "0";
		}
		String choose123 = value3 + "," + manage + "," + institution + "," + lack;

		String one, two, three, four;
		if (cb_one_1_2_4.isChecked()) {// 下料、加工、成型、仓储区域明确
			one = "1";
		} else {
			one = "01";
		}
		if (cb_two_1_2_4.isChecked()) {// 流程图挂墙明示、物品标识清晰
			two = "1";
		} else {
			two = "0";
		}
		if (cb_three_1_2_4.isChecked()) {// 进货、检测、出品、项目有台账
			three = "1";
		} else {
			three = "0";
		}
		if (cb_four_1_2_4.isChecked()) {// 凌乱无序、指示不清
			four = "1";
		} else {
			four = "0";
		}
		String choose124 = value4 + "," + one + "," + two + "," + three + "," + four;

		CaTestJson caTestJson1 = null;
		CaTestJson caTestJson2 = null;
		CaTestJson caTestJson3 = null;
		CaTestJson caTestJson4 = null;

		if (id1 != null) {
			caTestJson1 = new CaTestJson(id1, cid, "1.2.1", choose121, status, date, uploadStatus1);
		} else {
			caTestJson1 = new CaTestJson(cid, "1.2.1", choose121, status, date, uploadStatus1);
			CaTestDao.insertOrReplace(caTestJson1);
			CaTestDao.insertOrReplace(caTestJson1);
			list1 = CaTestDao.query(cid, "1.2.1");
			if (list1 != null && list1.size() > 0) {
				id1 = list1.get(0).getID();
			}
		}

		if (id2 != null) {
			caTestJson2 = new CaTestJson(id2, cid, "1.2.2", choose122, status, "", uploadStatus2);
		} else {
			caTestJson2 = new CaTestJson(cid, "1.2.2", choose122, status, "", uploadStatus2);
			CaTestDao.insertOrReplace(caTestJson2);
			list2 = CaTestDao.query(cid, "1.2.2");
			if (list2 != null && list2.size() > 0) {
				id2 = list2.get(0).getID();
			}
		}

		if (id3 != null) {
			caTestJson3 = new CaTestJson(id3, cid, "1.2.3", choose123, status, "", uploadStatus3);
		} else {
			caTestJson3 = new CaTestJson(cid, "1.2.3", choose123, status, "", uploadStatus3);
			CaTestDao.insertOrReplace(caTestJson3);
			list3 = CaTestDao.query(cid, "1.2.3");
			if (list3 != null && list3.size() > 0) {
				id3 = list3.get(0).getID();
			}
		}

		if (id4 != null) {
			caTestJson4 = new CaTestJson(id4, cid, "1.2.4", choose124, status, "", uploadStatus4);
		} else {
			caTestJson4 = new CaTestJson(cid, "1.2.4", choose124, status, "", uploadStatus4);
			CaTestDao.insertOrReplace(caTestJson4);
			list4 = CaTestDao.query(cid, "1.2.4");
			if (list4 != null && list4.size() > 0) {
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
				workAbilityJson.setITEM1_2("Y");
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
				workAbilityJson.setITEM1_2("N");
				workAbilityJson.setITEM("N");
				judge = "0,1";
			}
			String itemTwo = "1.2";
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
				workAbilityJson.setITEM1_2("Y");
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
				workAbilityJson.setITEM1_2("N");
				workAbilityJson.setITEM("N");
				judge = "N";
			}
			String itemTwo = "1.2";
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
