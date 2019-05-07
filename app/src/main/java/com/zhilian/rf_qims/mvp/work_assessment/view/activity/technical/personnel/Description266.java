package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
import com.zhilian.rf_qims.entity.CaTestDao;
import com.zhilian.rf_qims.entity.CaTestJson;
import com.zhilian.rf_qims.entity.EntEquipmentDao;
import com.zhilian.rf_qims.entity.EntEquipmentJson;
import com.zhilian.rf_qims.entity.WorkAbilityDao;
import com.zhilian.rf_qims.entity.WorkAbilityJson;
import com.zhilian.rf_qims.util.Compute;
import com.zhilian.rf_qims.util.CreatePrrsonOrEquipment;
import com.zhilian.rf_qims.util.DoubleUtil;
import com.zhilian.rf_qims.util.ScoreUtil;
import com.zhilian.rf_qims.util.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhilian.rf_qims.util.CommonUtils.getContext;

/**
 * 加工设备（2.7.6）
 */
public class Description266 extends BaseModuleActivity {
	@BindView(R.id.textView4)// 标题
		TextView mTextView4;
	@BindView(R.id.tv_value2_7_6)// 分值
		TextView mTvValue276;
	@BindView(R.id.tv_score2_7_6)// 得分
		TextView mTvScore276;
	@BindView(R.id.tv_rule2_7_6)// 规则
		TextView mTvRule276;

	@BindView(R.id.cb1_2_7_6)// 缺设备
		CheckBox mCb1276;
	@BindView(R.id.cb2_2_7_6)// 无付款凭证或租凭凭证
		CheckBox mCb2276;
	@BindView(R.id.cb3_2_7_6)// 设备不正常
		CheckBox mCb3276;
	@BindView(R.id.cb4_2_7_6)// 设备未固定
		CheckBox mCb4276;
	@BindView(R.id.cb5_2_7_6)// 工作规模不满足
		CheckBox mCb5276;

	@BindView(R.id.et_name2_7_6)// 设备名称
		EditText mEtName276;
	@BindView(R.id.et_type2_7_6)// 设备型号
		EditText mEtType276;
	@BindView(R.id.et_number2_7_6)// 设备编号
		EditText mEtNumber276;
	@BindView(R.id.et_remark2_7_6)// 备注
		EditText mEtRemark276;
	@BindView(R.id.create)// 创建人员
		Button mCreate;

	private List<CaTestJson> test;
	private List<EntEquipmentJson> mEntEquipmentJsons;
	private Long emid, id;
	private int uploadStatus;
	private String point,status = "";
	private double oldScore;

	@Override
	protected int getLayoutRes() {
		return R.layout.dialog_description11;
	}

	@Override
	protected void initData() {
		mTextView4.setText(item + " " + title);//设置考核标题
		CreatePrrsonOrEquipment.equipment(mCreate, this, Integer.valueOf(eid), Integer.parseInt(cid), item);//创建设备
		mEntEquipmentJsons = EntEquipmentDao.query(eid, cid, item);// 设备表
		test = CaTestDao.query(cid, item);// 打分表
		List<CaConfigJson> config = CaConfigDao.query(item);// 配置表

		point = config.get(0).getSCORE();//基础分值
		mTvValue276.setText(point);// 分值
		mTvScore276.setText(point);// 分值
		mTvRule276.setText(config.get(0).getMEMO());// 扣分说明

		if (test.size() > 0) {
			id = test.get(0).getID();
			status = test.get(0).getSTATUS();
			mTvScore276.setText(test.get(0).getSCORE());// 得分
			if (test.get(0).getUPLOADSTATUS() >= 1){
				uploadStatus = 1;
			}else {
				uploadStatus = 0;
			}
			if (test.get(0).getCHOOSE() != null && !test.get(0).getCHOOSE().equals("")){
				String[] pChoose = test.get(0).getCHOOSE().split(",",-1);
				if (pChoose[0].equals("1")) {// 缺设备不得分
					mCb1276.setChecked(true);
				}
				if (pChoose[1].equals("1")) {// 无付款凭证或租赁凭证不得分
					mCb2276.setChecked(true);
				}
				if (pChoose[2].equals("1")) {// 运行不正常扣一半分
					mCb3276.setChecked(true);
				}
				if (pChoose[3].equals("1")) {// 设备未固定扣一半分
					mCb4276.setChecked(true);
				}
				if (pChoose[4].equals("1")) {// 工作规模不满足
					mCb5276.setChecked(true);
				}
			}
		}

		if (mEntEquipmentJsons.size() > 0){
			emid = mEntEquipmentJsons.get(0).getID();// 设备id
			mEtName276.setText(mEntEquipmentJsons.get(0).getENAME());// 设备名称
			mEtType276.setText(mEntEquipmentJsons.get(0).getEMODEL());// 设备型号
			mEtNumber276.setText(mEntEquipmentJsons.get(0).getECODE());// 设备编号
			mEtRemark276.setText(mEntEquipmentJsons.get(0).getREMARK());// 备注
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
		mEtName276.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mCb1276.isChecked()){ //缺设备
					mTvScore276.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb2276.isChecked()){// 无付款凭证或租赁凭证不得分
						mTvScore276.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb5276.isChecked()){// 工作规模不满足
							mTvScore276.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb3276.isChecked()){// 运行不正常扣一半分
								if (mCb4276.isChecked()){// 设备未固定扣一半分
									mTvScore276.setText(ScoreUtil.SCORE_NOT);
								}else {
									mTvScore276.setText(ScoreUtil.scoreHalf(point));
								}
							}else {
								if (mCb4276.isChecked()){// 设备未固定扣一半分
									mTvScore276.setText(ScoreUtil.scoreHalf(point));
								}else {
									mTvScore276.setText(ScoreUtil.scoreAll(point));
								}
							}
						}
					}
				}
			}
		});

		// 设备型号
		mEtType276.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mCb1276.isChecked()){ //缺设备
					mTvScore276.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb2276.isChecked()){// 无付款凭证或租赁凭证不得分
						mTvScore276.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb5276.isChecked()){// 工作规模不满足
							mTvScore276.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb3276.isChecked()){// 运行不正常扣一半分
								if (mCb4276.isChecked()){// 设备未固定扣一半分
									mTvScore276.setText(ScoreUtil.SCORE_NOT);
								}else {
									mTvScore276.setText(ScoreUtil.scoreHalf(point));
								}
							}else {
								if (mCb4276.isChecked()){// 设备未固定扣一半分
									mTvScore276.setText(ScoreUtil.scoreHalf(point));
								}else {
									mTvScore276.setText(ScoreUtil.scoreAll(point));
								}
							}
						}
					}
				}
			}
		});

		// 设备编号
		mEtNumber276.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mCb1276.isChecked()){ //缺设备
					mTvScore276.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb2276.isChecked()){// 无付款凭证或租赁凭证不得分
						mTvScore276.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb5276.isChecked()){// 工作规模不满足
							mTvScore276.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb3276.isChecked()){// 运行不正常扣一半分
								if (mCb4276.isChecked()){// 设备未固定扣一半分
									mTvScore276.setText(ScoreUtil.SCORE_NOT);
								}else {
									mTvScore276.setText(ScoreUtil.scoreHalf(point));
								}
							}else {
								if (mCb4276.isChecked()){// 设备未固定扣一半分
									mTvScore276.setText(ScoreUtil.scoreHalf(point));
								}else {
									mTvScore276.setText(ScoreUtil.scoreAll(point));
								}
							}
						}
					}
				}
			}
		});

		// 备注
		mEtRemark276.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mCb1276.isChecked()){ //缺设备
					mTvScore276.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb2276.isChecked()){// 无付款凭证或租赁凭证不得分
						mTvScore276.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb5276.isChecked()){// 工作规模不满足
							mTvScore276.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb3276.isChecked()){// 运行不正常扣一半分
								if (mCb4276.isChecked()){// 设备未固定扣一半分
									mTvScore276.setText(ScoreUtil.SCORE_NOT);
								}else {
									mTvScore276.setText(ScoreUtil.scoreHalf(point));
								}
							}else {
								if (mCb4276.isChecked()){// 设备未固定扣一半分
									mTvScore276.setText(ScoreUtil.scoreHalf(point));
								}else {
									mTvScore276.setText(ScoreUtil.scoreAll(point));
								}
							}
						}
					}
				}
			}
		});

		// 缺设备
		mCb1276.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					mTvScore276.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb2276.isChecked()){// 无付款凭证或租赁凭证不得分
						mTvScore276.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb5276.isChecked()){
							mTvScore276.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb3276.isChecked()){// 运行不正常扣一半分
								if (mCb4276.isChecked()){// 设备未固定扣一半分
									mTvScore276.setText(ScoreUtil.SCORE_NOT);
								}else {
									mTvScore276.setText(ScoreUtil.scoreHalf(point));
								}
							}else {
								if (mCb4276.isChecked()){// 设备未固定扣一半分
									mTvScore276.setText(ScoreUtil.scoreHalf(point));
								}else {
									mTvScore276.setText(ScoreUtil.scoreAll(point));
								}
							}
						}
					}
				}
				saveData(status);
			}
		});

		// 无付款凭证或租赁凭证不得分
		mCb2276.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					mTvScore276.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb1276.isChecked()){// 缺设备
						mTvScore276.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb5276.isChecked()){
							mTvScore276.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb3276.isChecked()){// 运行不正常扣一半分
								if (mCb4276.isChecked()){// 设备未固定扣一半分
									mTvScore276.setText(ScoreUtil.SCORE_NOT);
								}else {
									mTvScore276.setText(ScoreUtil.scoreHalf(point));
								}
							}else {
								if (mCb4276.isChecked()){// 设备未固定扣一半分
									mTvScore276.setText(ScoreUtil.scoreHalf(point));
								}else {
									mTvScore276.setText(ScoreUtil.scoreAll(point));
								}
							}
						}
					}
				}
				saveData(status);
			}
		});

		// 运行不正常扣一半分
		mCb3276.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					if (mCb1276.isChecked()){// 缺设备
						mTvScore276.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb2276.isChecked()){// 无付款凭证或租赁凭证不得分
							mTvScore276.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb5276.isChecked()){
								mTvScore276.setText(ScoreUtil.SCORE_NOT);
							}else {
								if (mCb4276.isChecked()){// 设备未固定扣一半分
									mTvScore276.setText(ScoreUtil.SCORE_NOT);
								}else {
									mTvScore276.setText(ScoreUtil.scoreHalf(point));
								}
							}
						}
					}
				}else {
					if (mCb1276.isChecked()){// 缺设备
						mTvScore276.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb2276.isChecked()){// 无付款凭证或租赁凭证不得分
							mTvScore276.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb5276.isChecked()){
								mTvScore276.setText(ScoreUtil.SCORE_NOT);
							}else {
								if (mCb4276.isChecked()){// 设备未固定扣一半分
									mTvScore276.setText(ScoreUtil.scoreHalf(point));
								}else {
									mTvScore276.setText(ScoreUtil.scoreAll(point));
								}
							}
						}
					}
				}
				saveData(status);
			}
		});

		// 设备未固定扣一半分
		mCb4276.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					if (mCb1276.isChecked()){// 缺设备
						mTvScore276.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb2276.isChecked()){// 无付款凭证或租赁凭证不得分
							mTvScore276.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb5276.isChecked()){
								mTvScore276.setText(ScoreUtil.SCORE_NOT);
							}else {
								if (mCb3276.isChecked()){// 运行不正常扣一半分
									mTvScore276.setText(ScoreUtil.SCORE_NOT);
								}else {
									mTvScore276.setText(ScoreUtil.scoreHalf(point));
								}
							}
						}
					}
				}else {
					if (mCb1276.isChecked()){// 缺设备
						mTvScore276.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb2276.isChecked()){// 无付款凭证或租赁凭证不得分
							mTvScore276.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb5276.isChecked()){
								mTvScore276.setText(ScoreUtil.SCORE_NOT);
							}else {
								if (mCb3276.isChecked()){// 运行不正常扣一半分
									mTvScore276.setText(ScoreUtil.scoreHalf(point));
								}else {
									mTvScore276.setText(ScoreUtil.scoreAll(point));
								}
							}
						}
					}
				}
				saveData(status);
			}
		});

		// 工作规模不满足不得分
		mCb4276.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					mTvScore276.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb1276.isChecked()){// 缺设备
						mTvScore276.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb2276.isChecked()){// 无付款凭证或租赁凭证不得分
							mTvScore276.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb3276.isChecked()){// 运行不正常扣一半分
								if (mCb4276.isChecked()){
									mTvScore276.setText(ScoreUtil.SCORE_NOT);
								}else {
									mTvScore276.setText(ScoreUtil.scoreHalf(point));
								}
							}else {
								if (mCb4276.isChecked()){
									mTvScore276.setText(ScoreUtil.scoreHalf(point));
								}else {
									mTvScore276.setText(ScoreUtil.scoreAll(point));
								}
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
		/*☆先计算一遍旧item得分，因为很多item都有可能创建多于需要检测的人数，需要把所有item的分数排序大小再进行多余筛选会非常复杂。
		故反向思考，计算出当前旧item的分数用总item减去旧item分数，最后到计算总分再加上新分数就可以了(Compute.compute(cid, item, oldScore);)*/
		oldScore = Compute.compute(cid, item, oldScore);
		int condition1;
		if (mCb1276.isChecked()) {// 缺设备不得分
			condition1 = 1;
		} else {
			condition1 = 0;
		}

		int condition2;
		if (mCb2276.isChecked()) {// 无付款凭证或租赁凭证不得分
			condition2 = 1;
		} else {
			condition2 = 0;
		}

		int condition3;
		if (mCb3276.isChecked()) {// 运行不正常扣一半分
			condition3 = 1;
		} else {
			condition3 = 0;
		}

		int condition4;
		if (mCb4276.isChecked()) {// 设备未固定扣一半分
			condition4 = 1;
		} else {
			condition4 = 0;
		}

		int condition5;
		if (mCb5276.isChecked()) {// 工作规模不满足
			condition5 = 1;
		} else {
			condition5 = 0;
		}

		String choose = condition1 + "," + condition2 + "," + condition3 + "," + condition4 + "," + condition5;

		String name = mEtName276.getText().toString();// 设备名称
		String specification = mEtType276.getText().toString();// 设备型号
		String code = mEtNumber276.getText().toString();// 设备编号
		String remark = mEtRemark276.getText().toString();// 备注
		String point = String.valueOf(mTvScore276.getText()); // 得分
		if (name.equals("") || code.equals("")){
			ToastUtils.show("设备名称、编号不能为空");
			return;
		}

		EntEquipmentJson equipmentJson = null;
		CaTestJson caTestJson = null;

		if (emid != null){
			equipmentJson = new EntEquipmentJson(emid, Integer.valueOf(eid), cid,
				item, name, code, specification, point, choose, remark, "", uploadStatus);
			EntEquipmentDao.update(equipmentJson);
		}else {
			List<EntEquipmentJson> list = EntEquipmentDao.queryJudgeEquipment(cid, item, code);
			if (list.size() > 0){
				ToastUtils.show("已存在此设备");
				return;
			}else {
				equipmentJson = new EntEquipmentJson(Integer.valueOf(eid), cid,
					item, name, code, specification, point, choose, remark, uploadStatus);
				EntEquipmentDao.insertOrReplace(equipmentJson);
				mEntEquipmentJsons = EntEquipmentDao.query(eid, cid, item);
				if (mEntEquipmentJsons != null && mEntEquipmentJsons.size() > 0){
					emid = mEntEquipmentJsons.get(0).getID();
				}
			}
		}

		if (id != null) {
			caTestJson = new CaTestJson(id, cid, item, point, choose, remark, status, uploadStatus);
			CaTestDao.insertOrReplace(caTestJson);
		} else {
			caTestJson = new CaTestJson(cid, item, point, choose, remark, status, uploadStatus);
			CaTestDao.insertOrReplace(caTestJson);
			test = CaTestDao.query(cid, item);
			if (test != null && test.size() > 0){
				id = test.get(0).getID();
			}
		}

		WorkAbilityJson workAbilityJson = Common.getWorkAbilityJson();
		ScoreUtil.total(workAbilityJson, status, cid, item ,oldScore, Integer.parseInt(eid));
		if (flag == 1){
			if (status.equals("1")) {
				ToastUtils.show("<完成>保存成功");
				flag = 0;
			} else if (status.equals("2")) {
				ToastUtils.show("<未完成>保存成功");
				flag = 0;
			}
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
