package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.base.BaseModuleActivity;
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

import static com.zhilian.rf_qims.util.CommonUtils.getContext;

/**
 * 大型加工设备（打分明细4）
 */
public class Description4 extends BaseModuleActivity {
	@BindView(R.id.textView4)
	TextView mTextView4;
	@BindView(R.id.rb5_yes2_5_1)// 满足、不满足
		RadioButton mRb5Yes251;
	@BindView(R.id.rb5_no2_5_1)
	RadioButton mRb5No251;
	@BindView(R.id.rb1_yes2_5_1)// 正常运转、为正常运转
		RadioButton mRb1Yes251;
	@BindView(R.id.rb1_no2_5_1)
	RadioButton mRb1No251;
	@BindView(R.id.rb2_yes2_5_1)// 有付款凭证、无付款凭证
		RadioButton mRb2Yes251;
	@BindView(R.id.rb2_no2_5_1)
	RadioButton mRb2No251;
	@BindView(R.id.rb3_yes2_5_1)// 已固定、未固定
		RadioButton mRb3Yes251;
	@BindView(R.id.rb3_no2_5_1)
	RadioButton mRb3No251;

	@BindView(R.id.cb1_2_7_1)// 自有
		CheckBox mCb1271;
	@BindView(R.id.cb2_2_7_1)// 租凭
		CheckBox mCb2271;

	@BindView(R.id.et_name2_5_1)// 设备名称
		EditText mEtName251;
	@BindView(R.id.et_type2_5_1)// 设备型号
		EditText mEtType251;
	@BindView(R.id.et_number2_5_1)// 设备编号
		EditText mEtNumber251;
	@BindView(R.id.et_remark2_5_1)// 备注
		EditText mEtRemark251;

	@BindView(R.id.tv_rule2_5_1)// 扣分说明
		TextView mTvRule251;
	@BindView(R.id.tv_score2_5_1)// 得分
		TextView mTvScore251;
	@BindView(R.id.linearLayout6)
	LinearLayout mLinearLayout6;

	private CaTestJson mCaTestJson;
	private long emid, id;
	private int uploadStatus;
	private String point, status = "";

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_description4;
	}

	@Override
	protected void initData() {
		unfinished.setVisibility(View.GONE);
		mTextView4.setText(item + " " + title);//设置考核标题
		EntEquipmentJson equipmentJson = (EntEquipmentJson) getIntent().getSerializableExtra("equipment");// 设备表
		//mCaTestJson = (CaTestJson) getIntent().getSerializableExtra("test");// 打分表
		mTvRule251.setText(getIntent().getStringExtra("configExplain"));// 扣分说明
		point = getIntent().getStringExtra("configPoint");//基础分值

		if (equipmentJson != null) {
			mTvScore251.setText(equipmentJson.getSCORE()); //得分
			if (equipmentJson.getUPLOADSTATUS() >= 1) {
				uploadStatus = 1;
			} else {
				uploadStatus = 0;
			}
		} else {
			uploadStatus = 0;
		}
		emid = equipmentJson.getID();//设备ID
		mEtName251.setText(equipmentJson.getENAME());// 设备名称
		mEtType251.setText(equipmentJson.getEMODEL());// 设备型号
		mEtNumber251.setText(equipmentJson.getECODE());// 设备编号
		mEtRemark251.setText(equipmentJson.getREMARK());// 备注

		if (equipmentJson.getCHOOSE() != null && !equipmentJson.getCHOOSE().equals("")) {
			String[] pChoose = equipmentJson.getCHOOSE().split(",", -1);
			if (pChoose[0].equals("1")) {// 1 满足、0 不满足
				mRb5Yes251.setChecked(true);
			} else {
				mRb5No251.setChecked(true);
			}
			if (pChoose[1].equals("1")) {// 1 正常运转、0 为正常运转
				mRb1Yes251.setChecked(true);
			} else {
				mRb1No251.setChecked(true);
			}
			if (pChoose[2].equals("1")) {// 1 有付款凭证、0 无付款凭证
				mRb2Yes251.setChecked(true);
			} else {
				mRb2No251.setChecked(true);
			}
			if (pChoose[3].equals("1")) {// 1 已固定、0 未固定
				mRb3Yes251.setChecked(true);
			} else {
				mRb3No251.setChecked(true);
			}
			if (pChoose[4].equals("1")) {// 1 自有
				mCb1271.setChecked(true);
			} else if (pChoose[4].equals("0")){//、0 租凭
				mCb2271.setChecked(true);
			}
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
		mEtName251.addTextChangedListener(new TextWatcher() {
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
		mEtType251.addTextChangedListener(new TextWatcher() {
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
		mEtNumber251.addTextChangedListener(new TextWatcher() {
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
		mEtRemark251.addTextChangedListener(new TextWatcher() {
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
		mRb5Yes251.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mRb1Yes251.setChecked(true);
					mRb2Yes251.setChecked(true);
					mRb3Yes251.setChecked(true);
					mTvScore251.setText(point);
				}
				saveData(status);
			}
		});

		// 能运转 未能运转
		mRb1Yes251.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					if (mRb2Yes251.isChecked()) {// 有付款凭证
						if (mRb3Yes251.isChecked()) {// 已固定
							mTvScore251.setText(ScoreUtil.scoreAll(point));
							mRb5Yes251.setChecked(true);
						} else {// 未固定
							mRb5Yes251.setChecked(true);
							mRb3No251.setChecked(true);
							mTvScore251.setText(ScoreUtil.scoreHalf(point));
						}
					} else {// 无付款凭证
						mTvScore251.setText(ScoreUtil.SCORE_NOT);
						mRb5No251.setChecked(true);
					}
				} else {// 2未能运转
					mTvScore251.setText(ScoreUtil.SCORE_NOT);
					mRb5No251.setChecked(true);
				}
				saveData(status);
			}
		});

		// 有付款凭证 无付款凭证
		mRb2Yes251.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					if (mRb2Yes251.isChecked()) {// 正常运转
						if (mRb3Yes251.isChecked()) {// 已固定
							mTvScore251.setText(ScoreUtil.scoreAll(point));
							mRb5Yes251.setChecked(true);
						} else {// 未固定
							mRb5Yes251.setChecked(true);
							mRb3No251.setChecked(true);
							mTvScore251.setText(ScoreUtil.scoreHalf(point));
						}
					} else {// 未正常运转
						mTvScore251.setText(ScoreUtil.SCORE_NOT);
						mRb5No251.setChecked(true);
					}
				} else {// 2无付款凭证
					mTvScore251.setText(ScoreUtil.SCORE_NOT);
					mRb5No251.setChecked(true);
				}
				saveData(status);
			}
		});

		// 已固定、未固定
		mRb3Yes251.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					if (mRb1Yes251.isChecked()) {// 正常运转
						if (mRb2Yes251.isChecked()) {// 有付款凭证
							mTvScore251.setText(ScoreUtil.scoreAll(point));
							mRb5Yes251.setChecked(true);
						} else {// 无付款凭证
							mRb5No251.setChecked(true);
							mTvScore251.setText(ScoreUtil.SCORE_NOT);
						}
					} else {// 未正常运转
						mRb5No251.setChecked(true);
						mTvScore251.setText(ScoreUtil.SCORE_NOT);
					}
				} else {// 2未固定
					if (mRb1Yes251.isChecked()) {// 正常运转
						if (mRb2Yes251.isChecked()) {// 有付款凭证
							mRb5Yes251.setChecked(true);
							mRb3No251.setChecked(true);
							mTvScore251.setText(ScoreUtil.scoreHalf(point));
						} else {// 无付款凭证
							mTvScore251.setText(ScoreUtil.SCORE_NOT);
							mRb5No251.setChecked(true);
						}
					} else {// 未正常运转
						mTvScore251.setText(ScoreUtil.SCORE_NOT);
						mRb5No251.setChecked(true);
					}
				}
				saveData(status);
			}
		});

		//自有
		mCb1271.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mCb2271.setChecked(false);
				}
				saveData(status);
			}
		});

		//租凭
		mCb2271.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
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
				public void onAction(@NonNull ArrayList<AlbumFile> result) {
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
				public void onAction(@NonNull ArrayList<AlbumFile> result) {
					ToastUtils.show("摄像完毕");
				}
			}).start();
	}


	/**
	 * 保存
	 */
	private void saveData(String status) {
		int condition1;
		if (mRb5Yes251.isChecked()) {// 1 满足、0 不满足
			condition1 = 1;
		} else {
			condition1 = 0;
		}

		int condition2;
		if (mRb1Yes251.isChecked()) {// 1 能运转、0 未能运转
			condition2 = 1;
		} else {
			condition2 = 0;
		}

		int condition3;
		if (mRb2Yes251.isChecked()) {// 1 有付款凭证、0 无付款凭证
			condition3 = 1;
		} else {
			condition3 = 0;
		}

		int condition4;
		if (mRb3Yes251.isChecked()) {// 1 已固定、0 未固定
			condition4 = 1;
		} else {
			condition4 = 0;
		}

		int condition5;
		if (mCb1271.isChecked()) {// 1 自有、0 租凭
			condition5 = 1;
		} else if (mCb2271.isChecked()) {
			condition5 = 0;
		} else {
			condition5 = 2; //两个都未选中
		}

		String choose = condition1 + "," + condition2 + "," + condition3 + ","
			+ condition4 + "," + condition5;

		String remark = mEtRemark251.getText().toString();// 备注
		String name = mEtName251.getText().toString();// 设备名称
		String specification = mEtType251.getText().toString();// 设备型号
		String code = mEtNumber251.getText().toString();// 设备编号
		String point = String.valueOf(mTvScore251.getText());//得分
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
			CaTestDao.insertOrReplace(caTestJson);
			List<CaTestJson> test = CaTestDao.query(cid, item);
			if (test != null && test.size() > 0){
				id = test.get(0).getId();
			}
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}
}
