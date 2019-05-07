package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.base.BaseModuleActivity;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
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
 * 大型设备（打分明细2.6.5）
 */
public class Description8 extends BaseModuleActivity {
	@BindView(R.id.rb1_yes2_5_5)// 新设备、旧设备
	RadioButton mRb1Yes255;
	@BindView(R.id.rb1_no2_5_5)
	RadioButton mRb1No255;
	@BindView(R.id.rb2_yes2_5_5)// 有付款凭证、无
	RadioButton mRb2Yes255;
	@BindView(R.id.rb2_no2_5_5)
	RadioButton mRb2No255;
	@BindView(R.id.rb3_yes2_5_5)// 能运转、未能运转
	RadioButton mRb3Yes255;
	@BindView(R.id.rb3_no2_5_5)
	RadioButton mRb3No255;
	@BindView(R.id.rb4_yes2_5_5)//已固定、未固定
	RadioButton mRb4Yes255;
	@BindView(R.id.rb4_no2_5_5)
	RadioButton mRb4No255;

	@BindView(R.id.et_name2_5_5)
	EditText mEtName255;// 设备名称
	@BindView(R.id.et_type2_5_5)
	EditText mEtType255;// 设备型号
	@BindView(R.id.et_number2_5_5)
	EditText mEtNumber255;// 设备编号
	@BindView(R.id.et_remark2_5_5)
	EditText mEtRemark255;// 备注

	@BindView(R.id.textView4)// 标题
	TextView mTextView4;
	@BindView(R.id.tv_value2_5_5)//分值
	TextView mTvValue255;
	@BindView(R.id.tv_score2_5_5)// 得分
	TextView mTvScore255;
	@BindView(R.id.tv_rule2_5_5)// 扣分说明
	TextView mTvRule255;

	long emid, id;
	int uploadStatus;
	String point;

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_description8;
	}

	@Override
	protected void initData() {
		mTextView4.setText(item + " " + title);//设置考核标题
		EntEquipmentJson equipmentJson = (EntEquipmentJson) getIntent().getSerializableExtra("equipment");// 设备表
		CaTestJson caTestJson = (CaTestJson) getIntent().getSerializableExtra("test");// 打分表
		List<CaConfigJson> config = CaConfigDao.query(item);// 配置表
		mTvRule255.setText(config.get(0).getMEMO());// 扣分说明
		point = config.get(0).getSCORE();//基础分值

		if (caTestJson != null) {
			id = caTestJson.getID();
		}
		emid = equipmentJson.getID();
		mTvValue255.setText(point);// 分值
		mEtName255.setText(equipmentJson.getENAME());// 设备名称
		mEtType255.setText(equipmentJson.getEMODEL());// 设备型号
		mEtNumber255.setText(equipmentJson.getECODE());// 设备编号
		mEtRemark255.setText(equipmentJson.getREMARK());// 备注

		String[] pChoose = equipmentJson.getCHOOSE().split(",",-1);
		if (pChoose[0].equals("1")) {// 1 新设备、0 旧设备
			mRb1Yes255.setChecked(true);
		} else {
			mRb1No255.setChecked(true);
		}
		if (pChoose[1].equals("1")) {// 1 有付款凭证、0 无付款凭证
			mRb2Yes255.setChecked(true);
		} else {
			mRb2No255.setChecked(true);
		}
		if (pChoose[2].equals("1")) {// 1 能运转、0 未能运转
			mRb3Yes255.setChecked(true);
		} else {
			mRb3No255.setChecked(true);
		}
		if (pChoose[3].equals("1")) {// 1 已固定、0 未固定
			mRb4Yes255.setChecked(true);
		} else {
			mRb4No255.setChecked(true);
		}
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
		mTvValue255.setText("1.0");

		// 新设备 旧设备
		mRb1Yes255.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

			}
		});

		// 有付款凭证 无付款凭证
		mRb2Yes255.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 有付款凭证
					if (mRb3Yes255.isChecked()) {// 能运转
						if (mRb4Yes255.isChecked()) {// 已固定
							mTvScore255.setText("1.0");
						} else {// 未固定
							mTvScore255.setText("0.5");
						}
					} else {// 未能运转
						mTvScore255.setText("0");
					}
				} else {// 无付款凭证
					mTvScore255.setText("0");
				}
			}
		});

		// 能运转 未能运转
		mRb3Yes255.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 能运转
					if (mRb2Yes255.isChecked()) {// 有付款凭证
						if (mRb4Yes255.isChecked()) {// 已固定
							mTvScore255.setText("1.0");
						} else {// 未固定
							mTvScore255.setText("0.5");
						}
					} else {// 无付款凭证
						mTvScore255.setText("0");
					}
				} else {// 未能运转
					mTvScore255.setText("0");
				}
			}
		});

		// 已固定 未固定
		mRb4Yes255.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 已固定
					if (mRb2Yes255.isChecked()) {// 有付款凭证
						if (mRb3Yes255.isChecked()) {// 能运转
							mTvScore255.setText("1.0");
						} else {// 未能运转
							mTvScore255.setText("0");
						}
					} else {// 无付款凭证
						mTvScore255.setText("0");
					}
				} else {// 未固定
					if (mRb2Yes255.isChecked()) {// 有付款凭证
						if (mRb3Yes255.isChecked()) {// 能运转
							mTvScore255.setText("0.5");
						} else {// 未能运转
							mTvScore255.setText("0");
						}
					} else {// 无付款凭证
						mTvScore255.setText("0");
					}
				}
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
	 * 保存
	 */
	private void saveData(String status) {
		int condition1;
		if (mRb1Yes255.isChecked()) {// 0 新设备、1 旧设备
			condition1 = 1;
		} else {
			condition1 = 0;
		}

		int condition2;
		if (mRb2Yes255.isChecked()) {// 0 有付款凭证、1 无付款凭证
			condition2 = 1;
		} else {
			condition2 = 0;
		}

		int condition3;
		if (mRb3Yes255.isChecked()) {// 0 能运转、1 未能运转
			condition3 = 1;
		} else {
			condition3 = 0;
		}

		int condition4;
		if (mRb4Yes255.isChecked()) {// 0 已固定、1 未固定
			condition4 = 1;
		} else {
			condition4 = 0;
		}

		String choose = condition1 + "," + condition2 + "," + condition3 + "," + condition4;

		String name = mEtName255.getText().toString();// 设备名称
		String specification = mEtType255.getText().toString();// 设备型号
		String code = mEtNumber255.getText().toString();// 设备编号
		String remark = mEtRemark255.getText().toString();// 备注

		EntEquipmentJson equipmentJson = null;
		CaTestJson caTestJson = null;

		equipmentJson = new EntEquipmentJson(emid, Integer.valueOf(eid), cid,
			item, name, code, specification, "得分", choose, remark, "", uploadStatus);
		if (id != 0) {
			caTestJson = new CaTestJson(id, cid, item, "得分", choose, remark, status, uploadStatus);
		} else {
			caTestJson = new CaTestJson(cid, item, "得分", choose, remark, status, uploadStatus);
		}

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
