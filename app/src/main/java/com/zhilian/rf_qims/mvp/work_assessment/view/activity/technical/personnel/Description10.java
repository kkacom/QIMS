package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.base.BaseModuleActivity;
import com.zhilian.rf_qims.entity.CaTestJson;
import com.zhilian.rf_qims.entity.EntEquipmentDao;
import com.zhilian.rf_qims.entity.EntEquipmentJson;
import com.zhilian.rf_qims.util.ScoreUtil;
import com.zhilian.rf_qims.util.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhilian.rf_qims.util.CommonUtils.getContext;

/**
 * 大型设备（打分明细2.6.3）
 */
public class Description10 extends BaseModuleActivity {
	@BindView(R.id.textView4)// 标题
		TextView mTextView4;

	@BindView(R.id.tv_value2_6_3)// 分值
		TextView mTvValue263;
	@BindView(R.id.tv_score2_6_3)// 得分
		TextView mTvScore263;
	@BindView(R.id.tv_rule2_6_3)// 扣分说明
		TextView mTvRule263;
	@BindView(R.id.et_name2_6_3)// 设备名称
		EditText mEtName263;
	@BindView(R.id.et_type2_6_3)// 设备型号
		EditText mEtType263;
	@BindView(R.id.et_number2_6_3)// 设备编号
		EditText mEtNumber263;
	@BindView(R.id.et_remark2_6_3)// 备注
		EditText mEtRemark263;

	@BindView(R.id.cb1_2_6_3)// 缺设备不得分
	CheckBox mCb1263;
	@BindView(R.id.cb2_2_6_3)// 无付款凭证或租赁凭证不得分
	CheckBox mCb2263;
	@BindView(R.id.cb3_2_6_3)// 运行不正常扣一半分
	CheckBox mCb3263;
	@BindView(R.id.cb4_2_6_3)// 设备未固定扣一半分
	CheckBox mCb4263;
	@BindView(R.id.create)//创建设备
		Button mCreate;

	private CaTestJson mCaTestJson;
	private long emid, id;
	private int uploadStatus;
	private String point, status = "";

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_description6;
	}

	@Override
	protected void initData() {
		unfinished.setVisibility(View.GONE);
		mTextView4.setText(item + " " + title);//设置考核标题
		EntEquipmentJson equipmentJson = (EntEquipmentJson) getIntent().getSerializableExtra("equipment");// 设备表
		//CaTestJson caTestJson = (CaTestJson) getIntent().getSerializableExtra("test");// 打分表
		mTvRule263.setText(getIntent().getStringExtra("configExplain"));// 扣分说明
		point = getIntent().getStringExtra("configPoint");//基础分值

		mTvValue263.setText(point);// 分值
		mTvScore263.setText(point);// 分值

		if (equipmentJson != null){
			mTvScore263.setText(equipmentJson.getSCORE());// 得分
			if (equipmentJson.getUPLOADSTATUS() >= 1){
				uploadStatus = 1;
			}else {
				uploadStatus = 0;
			}
			emid = equipmentJson.getID();// 设备id
			mEtName263.setText(equipmentJson.getENAME());// 设备名称
			mEtType263.setText(equipmentJson.getEMODEL());// 设备型号
			mEtNumber263.setText(equipmentJson.getECODE());// 设备编号
			mEtRemark263.setText(equipmentJson.getREMARK());// 备注

			if (equipmentJson.getCHOOSE() != null && !equipmentJson.getCHOOSE().equals("")){
				String[] pChoose = equipmentJson.getCHOOSE().split(",",-1);
				if (pChoose[0].equals("1")) {// 缺设备不得分
					mCb1263.setChecked(true);
				}
				if (pChoose[1].equals("1")) {// 无付款凭证或租赁凭证不得分
					mCb2263.setChecked(true);
				}
				if (pChoose[2].equals("1")) {// 运行不正常扣一半分
					mCb3263.setChecked(true);
				}
				if (pChoose[3].equals("1")) {// 设备未固定扣一半分
					mCb4263.setChecked(true);
				}
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
		mEtName263.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mCb1263.isChecked()){ //缺设备
					mTvScore263.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb2263.isChecked()){// 无付款凭证或租赁凭证不得分
						mTvScore263.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb3263.isChecked()){// 运行不正常扣一半分
							if (mCb4263.isChecked()){// 设备未固定扣一半分
								mTvScore263.setText(ScoreUtil.SCORE_NOT);
							}else {
								mTvScore263.setText(ScoreUtil.scoreHalf(point));
							}
						}else {
							if (mCb4263.isChecked()){// 设备未固定扣一半分
								mTvScore263.setText(ScoreUtil.scoreHalf(point));
							}else {
								mTvScore263.setText(ScoreUtil.scoreAll(point));
							}
						}
					}
				}
			}
		});

		// 设备型号
		mEtType263.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mCb1263.isChecked()){ //缺设备
					mTvScore263.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb2263.isChecked()){// 无付款凭证或租赁凭证不得分
						mTvScore263.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb3263.isChecked()){// 运行不正常扣一半分
							if (mCb4263.isChecked()){// 设备未固定扣一半分
								mTvScore263.setText(ScoreUtil.SCORE_NOT);
							}else {
								mTvScore263.setText(ScoreUtil.scoreHalf(point));
							}
						}else {
							if (mCb4263.isChecked()){// 设备未固定扣一半分
								mTvScore263.setText(ScoreUtil.scoreHalf(point));
							}else {
								mTvScore263.setText(ScoreUtil.scoreAll(point));
							}
						}
					}
				}
			}
		});

		// 设备编号
		mEtNumber263.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mCb1263.isChecked()){ //缺设备
					mTvScore263.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb2263.isChecked()){// 无付款凭证或租赁凭证不得分
						mTvScore263.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb3263.isChecked()){// 运行不正常扣一半分
							if (mCb4263.isChecked()){// 设备未固定扣一半分
								mTvScore263.setText(ScoreUtil.SCORE_NOT);
							}else {
								mTvScore263.setText(ScoreUtil.scoreHalf(point));
							}
						}else {
							if (mCb4263.isChecked()){// 设备未固定扣一半分
								mTvScore263.setText(ScoreUtil.scoreHalf(point));
							}else {
								mTvScore263.setText(ScoreUtil.scoreAll(point));
							}
						}
					}
				}
			}
		});

		// 备注
		mEtRemark263.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mCb1263.isChecked()){ //缺设备
					mTvScore263.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb2263.isChecked()){// 无付款凭证或租赁凭证不得分
						mTvScore263.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb3263.isChecked()){// 运行不正常扣一半分
							if (mCb4263.isChecked()){// 设备未固定扣一半分
								mTvScore263.setText(ScoreUtil.SCORE_NOT);
							}else {
								mTvScore263.setText(ScoreUtil.scoreHalf(point));
							}
						}else {
							if (mCb4263.isChecked()){// 设备未固定扣一半分
								mTvScore263.setText(ScoreUtil.scoreHalf(point));
							}else {
								mTvScore263.setText(ScoreUtil.scoreAll(point));
							}
						}
					}
				}
			}
		});

		// 缺设备
		mCb1263.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					mTvScore263.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb2263.isChecked()){// 无付款凭证或租赁凭证不得分
						mTvScore263.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb3263.isChecked()){// 运行不正常扣一半分
							if (mCb4263.isChecked()){// 设备未固定扣一半分
								mTvScore263.setText(ScoreUtil.SCORE_NOT);
							}else {
								mTvScore263.setText(ScoreUtil.scoreHalf(point));
							}
						}else {
							if (mCb4263.isChecked()){// 设备未固定扣一半分
								mTvScore263.setText(ScoreUtil.scoreHalf(point));
							}else {
								mTvScore263.setText(ScoreUtil.scoreAll(point));
							}
						}
					}
				}
				saveData(status);
			}
		});

		// 无付款凭证或租赁凭证不得分
		mCb2263.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					mTvScore263.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb1263.isChecked()){// 缺设备
						mTvScore263.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb3263.isChecked()){// 运行不正常扣一半分
							if (mCb4263.isChecked()){// 设备未固定扣一半分
								mTvScore263.setText(ScoreUtil.SCORE_NOT);
							}else {
								mTvScore263.setText(ScoreUtil.scoreHalf(point));
							}
						}else {
							if (mCb4263.isChecked()){// 设备未固定扣一半分
								mTvScore263.setText(ScoreUtil.scoreHalf(point));
							}else {
								mTvScore263.setText(ScoreUtil.scoreAll(point));
							}
						}
					}
				}
				saveData(status);
			}
		});

		// 运行不正常扣一半分
		mCb3263.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					if (mCb1263.isChecked()){// 缺设备
						mTvScore263.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb2263.isChecked()){// 无付款凭证或租赁凭证不得分
							mTvScore263.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb4263.isChecked()){// 设备未固定扣一半分
								mTvScore263.setText(ScoreUtil.SCORE_NOT);
							}else {
								mTvScore263.setText(ScoreUtil.scoreHalf(point));
							}
						}
					}
				}else {
					if (mCb1263.isChecked()){// 缺设备
						mTvScore263.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb2263.isChecked()){// 无付款凭证或租赁凭证不得分
							mTvScore263.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb4263.isChecked()){// 设备未固定扣一半分
								mTvScore263.setText(ScoreUtil.scoreHalf(point));
							}else {
								mTvScore263.setText(ScoreUtil.scoreAll(point));
							}
						}
					}
				}
				saveData(status);
			}
		});

		// 设备未固定扣一半分
		mCb4263.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					if (mCb1263.isChecked()){// 缺设备
						mTvScore263.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb2263.isChecked()){// 无付款凭证或租赁凭证不得分
							mTvScore263.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb3263.isChecked()){// 运行不正常扣一半分
								mTvScore263.setText(ScoreUtil.SCORE_NOT);
							}else {
								mTvScore263.setText(ScoreUtil.scoreHalf(point));
							}
						}
					}
				}else {
					if (mCb1263.isChecked()){// 缺设备
						mTvScore263.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb2263.isChecked()){// 无付款凭证或租赁凭证不得分
							mTvScore263.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb3263.isChecked()){// 运行不正常扣一半分
								mTvScore263.setText(ScoreUtil.scoreHalf(point));
							}else {
								mTvScore263.setText(ScoreUtil.scoreAll(point));
							}
						}
					}
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
		if (mCb1263.isChecked()) {// 缺设备不得分
			condition1 = 1;
		} else {
			condition1 = 0;
		}

		int condition2;
		if (mCb2263.isChecked()) {// 无付款凭证或租赁凭证不得分
			condition2 = 1;
		} else {
			condition2 = 0;
		}

		int condition3;
		if (mCb3263.isChecked()) {// 运行不正常扣一半分
			condition3 = 1;
		} else {
			condition3 = 0;
		}

		int condition4;
		if (mCb4263.isChecked()) {// 设备未固定扣一半分
			condition4 = 1;
		} else {
			condition4 = 0;
		}

		String choose = condition1 + "," + condition2 + "," + condition3 + "," + condition4;

		String name = mEtName263.getText().toString();// 设备名称
		String specification = mEtType263.getText().toString();// 设备型号
		String code = mEtNumber263.getText().toString();// 设备编号
		String remark = mEtRemark263.getText().toString();// 备注
		String point = String.valueOf(mTvScore263.getText());//得分
		if (name.equals("") && specification.equals("")) {
			ToastUtils.show("设备名称或编号不能为空");
		}
		if (point.equals("")) {
			ToastUtils.show("请进行打分");
			return;
		}

		EntEquipmentJson equipmentJson = null;
		equipmentJson = new EntEquipmentJson(emid, Integer.valueOf(eid), cid,
			item, name, code, specification, point, choose, remark, uploadStatus);
		EntEquipmentDao.update(equipmentJson);

		if (flag == 1) {
			ToastUtils.show("保存完成");
			flag = 0;
		}
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		initData();
	}
}
