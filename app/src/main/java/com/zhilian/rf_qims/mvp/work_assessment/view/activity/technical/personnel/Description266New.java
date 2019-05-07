package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.os.Bundle;
import android.view.View;
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
import com.zhilian.rf_qims.entity.EntEquipmentDao;
import com.zhilian.rf_qims.entity.EntEquipmentJson;
import com.zhilian.rf_qims.entity.WorkAbilityDao;
import com.zhilian.rf_qims.entity.WorkAbilityJson;
import com.zhilian.rf_qims.util.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 大型设备（打分明细2.6.6）
 */
public class Description266New extends BaseModuleActivity {
	@BindView(R.id.textView4)// 标题
		TextView mTextView4;
	@BindView(R.id.tv_value2_6_3)// 分值
	TextView mTvValue253;
	@BindView(R.id.tv_score2_6_3)// 得分
	TextView mTvScore253;
	@BindView(R.id.tv_rule2_6_3)// 扣分说明
	TextView mTvRule253;
	@BindView(R.id.et_name2_6_3)// 设备名称
	EditText mEtName253;
	@BindView(R.id.et_type2_6_3)// 设备型号
	EditText mEtType253;
	@BindView(R.id.et_number2_6_3)// 设备编号
	EditText mEtNumber253;
	@BindView(R.id.et_remark2_6_3)// 备注
	EditText mEtRemark253;

	long emid, id;
	int uploadStatus;
	String point;

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_description6;
	}

	@Override
	protected void initData() {
		mTextView4.setText(item + " " + title);//设置考核标题
		EntEquipmentJson equipmentJson = (EntEquipmentJson) getIntent().getSerializableExtra("equipment");// 人员表
		CaTestJson caTestJson = (CaTestJson) getIntent().getSerializableExtra("test");// 打分表
		mTvRule253.setText(getIntent().getStringExtra("configExplain"));// 扣分说明
		point = getIntent().getStringExtra("configPoint");//基础分值

		if (caTestJson != null) {
			id = caTestJson.getID();
		}
		emid = equipmentJson.getID();
		String[] field = equipmentJson.getFIELD().split(",",-1);
		mEtName253.setText(equipmentJson.getENAME());// 设备名称
		mEtType253.setText(equipmentJson.getEMODEL());// 设备型号
		mEtNumber253.setText(equipmentJson.getECODE());// 设备编号
		mEtRemark253.setText(equipmentJson.getREMARK());// 备注

		String[] pChoose = equipmentJson.getCHOOSE().split(",",-1);
		/*if (pChoose[0].equals("1")) {// 1 新设备、0 旧设备
			mRb1Yes253.setChecked(true);
		} else {
			mRb1No253.setChecked(true);
		}
		if (pChoose[1].equals("1")) {// 1 有付款凭证、0 无付款凭证
			mRb2Yes253.setChecked(true);
		} else {
			mRb2No253.setChecked(true);
		}
		if (pChoose[2].equals("1")) {// 1 能运转、0 未能运转
			mRb3Yes253.setChecked(true);
		} else {
			mRb3No253.setChecked(true);
		}
		if (pChoose[3].equals("1")) {// 1 已固定、0 未固定
			mRb4Yes253.setChecked(true);
		} else {
			mRb4No253.setChecked(true);
		}*/
	}

	@Override
	protected void saveStatus(String status) {
		saveData(status);
	}

	/**
	 * 事件监听
	 */
	@Override
	protected void listener() {

	}

	@Override
	protected void photo() {

	}

	@Override
	protected void video() {

	}

	/**
	 * 保存
	 */
	private void saveData(String status) {
		int condition1;
		/*if (mRb1Yes253.isChecked()) {// 0 新设备、1 旧设备
			condition1 = 1;
		} else {
			condition1 = 0;
		}

		int condition2;
		if (mRb2Yes253.isChecked()) {// 0 有付款凭证、1 无付款凭证
			condition2 = 1;
		} else {
			condition2 = 0;
		}

		int condition3;
		if (mRb3Yes253.isChecked()) {// 0 能运转、1 未能运转
			condition3 = 1;
		} else {
			condition3 = 0;
		}

		int condition4;
		if (mRb4Yes253.isChecked()) {// 0 已固定、1 未固定
			condition4 = 1;
		} else {
			condition4 = 0;
		}*/

		//String choose = condition1 + "," + condition2 + "," + condition3 + "," + condition4;

		String remark = mEtName253.getText().toString();// 备注
		String name = mEtType253.getText().toString();// 设备名称
		String specification = mEtNumber253.getText().toString();// 设备型号
		String code = mEtRemark253.getText().toString();// 设备编号

		EntEquipmentJson equipmentJson = null;
		CaTestJson caTestJson = null;

		/*equipmentJson = new EntEquipmentJson(emid, Integer.valueOf(eid), cid,
			item, name, code, specification, "得分", choose, remark, uploadStatus);
		if (id != 0) {
			caTestJson = new CaTestJson(id, cid, item, "得分", choose, remark, status, uploadStatus);
		} else {
			caTestJson = new CaTestJson(cid, item, "得分", choose, remark, status, uploadStatus);
		}*/

		EntEquipmentDao.update(equipmentJson);
		CaTestDao.insertOrReplace(caTestJson);

		if (status.equals("1")) {
			WorkAbilityJson workAbilityJson = Common.getWorkAbilityJson();
			total(workAbilityJson, status);
			ToastUtils.show("<完成>保存成功");
		} else if (status.equals("2")) {
			WorkAbilityJson workAbilityJson = Common.getWorkAbilityJson();
			total(workAbilityJson, status);
			ToastUtils.show("<未完成>保存成功");
		}

	}

	/**
	 * 算总分
	 */
	private void total(WorkAbilityJson workAbilityJson, String status) {
		String itemLike = item.substring(0, 3);//取item的前4获取所有2.1.的人，如2.1.11 = 2.1.
		String itemTailor = item.substring(0, 2);
		List<CaTestJson> caTestJsonList = CaTestDao.queryLike(cid, itemLike);
		double total = 0.00;
		for (CaTestJson caTestJson : caTestJsonList) {
			total += Double.valueOf(caTestJson.getSCORE());
		}
		CaTestJson caTestJson = CaTestDao.queryOne(cid, itemTailor);
		if (caTestJson == null) {
			caTestJson = new CaTestJson(cid, itemTailor, String.valueOf(total), status, 0);
		} else {
			caTestJson.setSCORE(String.valueOf(total));
			caTestJson.setSTATUS(status);
		}
		CaTestDao.insertOrReplace(caTestJson);
		if (status.equals("1")){
			workAbilityJson.setSTATUS(1);
			workAbilityJson.setITEM2_6(String.valueOf(total));
			WorkAbilityDao.insertOrReplace(workAbilityJson);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}
}
