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
import com.zhilian.rf_qims.entity.WorkAbilityJson;
import com.zhilian.rf_qims.util.Compute;
import com.zhilian.rf_qims.util.CreatePrrsonOrEquipment;
import com.zhilian.rf_qims.util.ScoreUtil;
import com.zhilian.rf_qims.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhilian.rf_qims.util.CommonUtils.getContext;

/**
 * 大型设备（打分明细2.6.3）
 */
public class Description6 extends BaseModuleActivity {
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

	private List<CaTestJson> test;
	private List<EntEquipmentJson> mEntEquipmentJsons;
	private Long emid, id;
	private int uploadStatus;
	private String point,status = "";
	private double oldScore;

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_description6;
	}

	@Override
	protected void initData() {
		mTextView4.setText(item + " " + title);//设置考核标题
		CreatePrrsonOrEquipment.equipment(mCreate, this, Integer.valueOf(eid), Integer.parseInt(cid), item);//创建设备
		mEntEquipmentJsons = EntEquipmentDao.query(eid, cid, item);// 设备表
		test = CaTestDao.query(cid, item);// 打分表
		List<CaConfigJson> config = CaConfigDao.query(item);// 配置表

		point = config.get(0).getSCORE();//基础分值
		mTvValue263.setText(point);// 分值
		mTvScore263.setText(point);// 分值
		mTvRule263.setText(config.get(0).getMEMO());// 扣分说明

		if (test.size() > 0) {
			id = test.get(0).getID();
			status = test.get(0).getSTATUS();
			mTvScore263.setText(test.get(0).getSCORE());// 得分
			if (test.get(0).getUPLOADSTATUS() >= 1){
				uploadStatus = 1;
			}else {
				uploadStatus = 0;
			}
			if (test.get(0).getCHOOSE() != null && !test.get(0).getCHOOSE().equals("")){
				String[] pChoose = test.get(0).getCHOOSE().split(",",-1);
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
		}else {
			uploadStatus = 0;
		}

		if (mEntEquipmentJsons.size() > 0){
			emid = mEntEquipmentJsons.get(0).getID();// 设备id
			mEtName263.setText(mEntEquipmentJsons.get(0).getENAME());// 设备名称
			mEtType263.setText(mEntEquipmentJsons.get(0).getEMODEL());// 设备型号
			mEtNumber263.setText(mEntEquipmentJsons.get(0).getECODE());// 设备编号
			mEtRemark263.setText(mEntEquipmentJsons.get(0).getREMARK());// 备注
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
		/*☆先计算一遍旧item得分，因为很多item都有可能创建多于需要检测的人数，需要把所有item的分数排序大小再进行多余筛选会非常复杂。
		故反向思考，计算出当前旧item的分数用总item减去旧item分数，最后到计算总分再加上新分数就可以了(Compute.compute(cid, item, oldScore);)*/
		oldScore = Compute.compute(cid, item, oldScore);
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
		if (name.equals("") || code.equals("")){
			ToastUtils.show("设备名称、编号不能为空");
			return;
		}

		EntEquipmentJson equipmentJson = null;
		CaTestJson caTestJson = null;

		if (emid != null){
			equipmentJson = new EntEquipmentJson(emid, Integer.valueOf(eid), cid,
				item, name, code, specification, point, choose, remark, uploadStatus);
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
