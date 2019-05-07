package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.base.BaseModuleActivity;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.CaTestDao;
import com.zhilian.rf_qims.entity.CaTestJson;
import com.zhilian.rf_qims.entity.EntEquipmentDao;
import com.zhilian.rf_qims.entity.EntEquipmentJson;
import com.zhilian.rf_qims.entity.WorkAbilityDao;
import com.zhilian.rf_qims.entity.WorkAbilityJson;
import com.zhilian.rf_qims.util.ScoreUtil;
import com.zhilian.rf_qims.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

import static com.zhilian.rf_qims.util.CommonUtils.getContext;

/**
 * 加工设备（打分明细2.7.1）
 */
public class Description9 extends BaseModuleActivity {
	@BindView(R.id.rb5_yes2_7_1)// 满足
		RadioButton mRb5Yes271;
	@BindView(R.id.rb5_no2_7_1)// 不满足
		RadioButton mRb5No271;
	@BindView(R.id.rb1_yes2_7_1)// 能运转
		RadioButton mRb1Yes271;
	@BindView(R.id.rb1_no2_7_1)// 未正常运转
		RadioButton mRb1No271;
	@BindView(R.id.rb2_yes2_7_1)// 有付款凭证
		RadioButton mRb2Yes271;
	@BindView(R.id.rb2_no2_7_1)// 无付款凭证
		RadioButton mRb2No271;

	@BindView(R.id.et_name2_7_1)// 设备名称
		EditText mEtName271;
	@BindView(R.id.et_type2_7_1)// 设备型号
		EditText mEtType271;
	@BindView(R.id.et_number2_7_1)// 设备编号
		EditText mEtNumber271;
	@BindView(R.id.et_remark2_7_1)// 备注
		EditText mEtRemark271;

	@BindView(R.id.tv_rule2_7_1)// 扣分说明
		TextView mTvRule271;
	@BindView(R.id.tv_score2_7_1)// 得分
		TextView mTvScore271;
	@BindView(R.id.textView4)// 标题
		TextView mTextView4;

	@BindView(R.id.cb1_2_7_1)// 自有
		CheckBox mCb1271;
	@BindView(R.id.cb2_2_7_1)// 租凭
		CheckBox mCb2271;

	private CaTestJson mCaTestJson;
	private long emid, id;
	private int uploadStatus;
	private String point, status = "";

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_description9;
	}

	@Override
	protected void initData() {
		unfinished.setVisibility(View.GONE);
		mTextView4.setText(item + " " + title);//设置考核标题
		EntEquipmentJson equipmentJson = (EntEquipmentJson) getIntent().getSerializableExtra("equipment");// 设备表
		//CaTestJson caTestJson = (CaTestJson) getIntent().getSerializableExtra("test");// 打分表
		mTvRule271.setText(getIntent().getStringExtra("configExplain"));// 扣分说明
		point = getIntent().getStringExtra("configPoint");//基础分值

		if (equipmentJson != null) {
			mTvScore271.setText(equipmentJson.getSCORE()); //得分
			if (equipmentJson.getUPLOADSTATUS() >= 1) {
				uploadStatus = 1;
			} else {
				uploadStatus = 0;
			}
			emid = equipmentJson.getID();//设备ID
			mEtName271.setText(equipmentJson.getENAME());// 设备名称
			mEtType271.setText(equipmentJson.getEMODEL());// 设备型号
			mEtNumber271.setText(equipmentJson.getSCORE());// 设备编号
			mEtRemark271.setText(equipmentJson.getREMARK());// 备注
			if (equipmentJson.getCHOOSE() != null && !equipmentJson.getCHOOSE().equals("")) {
				String[] pChoose = equipmentJson.getCHOOSE().split(",",-1);
				if (pChoose[0].equals("1")) {// 1 满足、0 不满足
					mRb5Yes271.setChecked(true);
				} else {
					mRb5No271.setChecked(true);
				}
				if (pChoose[1].equals("1")) {// 1 正常运转、0 为正常运转
					mRb1Yes271.setChecked(true);
				} else {
					mRb1No271.setChecked(true);
				}
				if (pChoose[2].equals("1")) {// 1 有付款凭证、0 无付款凭证
					mRb2Yes271.setChecked(true);
				} else {
					mRb2No271.setChecked(true);
				}
				if (pChoose[3].equals("1")) {// 1 自有
					mCb1271.setChecked(true);
				} else if (pChoose[3].equals("0")){//、0 租凭
					mCb2271.setChecked(true);
				}
			}
		} else {
			uploadStatus = 0;
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

// 设备名称
		mEtName271.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				saveData(status);
			}
		});

		// 设备型号
		mEtType271.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				saveData(status);
			}
		});

		// 设备编号
		mEtNumber271.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				saveData(status);
			}
		});

		// 备注
		mEtRemark271.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				saveData(status);
			}
		});

		// 满足 不满足
		mRb5Yes271.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mRb1Yes271.setChecked(true);
					mRb2Yes271.setChecked(true);
					mTvScore271.setText(ScoreUtil.scoreAll(point));
				}
				saveData(status);
			}
		});

		// 能运转 未能运转
		mRb1Yes271.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					if (mRb2Yes271.isChecked()){ //有付款凭证
						mRb5Yes271.setChecked(true);
						mTvScore271.setText(ScoreUtil.scoreAll(point));
					}else {
						mRb5No271.setChecked(true);
						mTvScore271.setText(ScoreUtil.SCORE_NOT);
					}
				}else {
					mRb5No271.setChecked(true);
					mTvScore271.setText(ScoreUtil.SCORE_NOT);
				}
				saveData(status);
			}
		});

		// 有付款凭证 无付款凭证
		mRb2Yes271.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					if (mRb1Yes271.isChecked()){ //能运转
						mRb5Yes271.setChecked(true);
						mTvScore271.setText(ScoreUtil.scoreAll(point));
					}else {
						mRb5No271.setChecked(true);
						mTvScore271.setText(ScoreUtil.SCORE_NOT);
					}
				}else {
					mRb5No271.setChecked(true);
					mTvScore271.setText(ScoreUtil.SCORE_NOT);
				}
				saveData(status);
			}
		});

		//自有
		mCb1271.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					mCb2271.setChecked(false);
				}
				saveData(status);
			}
		});

		//租凭
		mCb2271.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					mCb1271.setChecked(false);
				}
				saveData(status);
			}
		});
	}

	@Override
	protected void photo() {
		Album.image(getContext()) // Image selection.
			.singleChoice()
			.camera(true)
			.columnCount(2)
			.onResult(new Action<ArrayList<AlbumFile>>() {
				@Override
				public void onAction(@android.support.annotation.NonNull ArrayList<AlbumFile> result) {
					ToastUtils.show("拍照完毕");
					Log.e("进入拍照", "返回结果");
				}
			}).start();
	}

	@Override
	protected void video() {
		Album.video(getContext()) // Video selection.
			.singleChoice()
			.camera(true)
			.columnCount(2)
			.onResult(new Action<ArrayList<AlbumFile>>() {
				@Override
				public void onAction(@android.support.annotation.NonNull ArrayList<AlbumFile> result) {
					ToastUtils.show("摄像完毕");
				}
			}).start();
	}

	/**
	 * 保存
	 */
	private void saveData(String status) {
		int condition1;
		if (mRb5Yes271.isChecked()) {// 1 满足、0 不满足
			condition1 = 1;
		} else {
			condition1 = 0;
		}

		int condition2;
		if (mRb1Yes271.isChecked()) {// 1 能运转、0 未能运转
			condition2 = 1;
		} else {
			condition2 = 0;
		}

		int condition3;
		if (mRb2Yes271.isChecked()) {// 1 有付款凭证、0 无付款凭证
			condition3 = 1;
		} else {
			condition3 = 0;
		}

		int condition4;
		if (mCb1271.isChecked()) {// 1 自有、0 租凭（不选默认租凭）
			condition4= 1;
		} else {
			condition4 = 0;
		}

		String choose = condition1 + "," + condition2 + "," + condition3 + ","
			+ condition4;

		String name = mEtName271.getText().toString();// 设备名称
		String specification = mEtType271.getText().toString();// 设备型号
		String code = mEtNumber271.getText().toString();// 设备编号
		String remark = mEtRemark271.getText().toString();// 备注
		String point = String.valueOf(mTvScore271.getText());// 得分
		if (name.equals("") && specification.equals("")) {
			ToastUtils.show("设备名称或编号不能为空");
		}
		if (point.equals("")) {
			ToastUtils.show("请进行打分");
			return;
		}

		EntEquipmentJson equipmentJson = null;
		//CaTestJson caTestJson = null;

		equipmentJson = new EntEquipmentJson(emid, Integer.valueOf(eid), cid,
			item, name, code, specification, point, choose, remark, "", uploadStatus);
		/*if (id != 0) {
			caTestJson = new CaTestJson(id, cid, item, point, choose, remark, status, uploadStatus);
			CaTestDao.insertOrReplace(caTestJson);
		} else {
			caTestJson = new CaTestJson(cid, item, point, choose, remark, status, uploadStatus);
		}*/

		EntEquipmentDao.update(equipmentJson);
		if (flag == 1) {
			ToastUtils.show("保存完成");
			flag = 0;
		}

		/*if (status.equals("1")) {
			WorkAbilityJson workAbilityJson = Common.getWorkAbilityJson();
			total(workAbilityJson, status);
			ToastUtils.show("<完成>保存成功");
		} else if (status.equals("2")) {
			WorkAbilityJson workAbilityJson = Common.getWorkAbilityJson();
			total(workAbilityJson, status);
			ToastUtils.show("<未完成>保存成功");
		}*/

	}

	/**
	 * 算总分
	 */
	private void total(WorkAbilityJson workAbilityJson, String status) {
		String itemLike = item.substring(0, 4);//取item的前4获取所有2.1.的人，如2.1.11 = 2.1.
		String itemTailor = item.substring(0, 3);
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
			workAbilityJson.setITEM2_7(String.valueOf(total));
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
