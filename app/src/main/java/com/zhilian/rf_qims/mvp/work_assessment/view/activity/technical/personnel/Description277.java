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
 * 大型设备（打分明细2.7.7）
 */
public class Description277 extends BaseModuleActivity {
	@BindView(R.id.textView4)// 标题
		TextView mTextView4;

	@BindView(R.id.tv_value2_7_7)// 分值
		TextView mTvValue277;
	@BindView(R.id.tv_score2_7_7)// 得分
		TextView mTvScore277;
	@BindView(R.id.tv_rule2_7_7)// 扣分说明
		TextView mTvRule277;
	@BindView(R.id.et_name2_7_7)// 设备名称
		EditText mEtName277;
	@BindView(R.id.et_type2_7_7)// 设备型号
		EditText mEtType277;
	@BindView(R.id.et_number2_7_7)// 设备编号
		EditText mEtNumber277;
	@BindView(R.id.et_remark2_7_7)// 备注
		EditText mEtRemark277;

	@BindView(R.id.cb1_2_7_7)// 采用商品混凝土生产设备
		CheckBox mCb1277;
	@BindView(R.id.cb2_2_7_7)// 满足要求，配置齐全
		CheckBox mCb2277;
	@BindView(R.id.cb3_2_7_7)// 缺振捣设备扣0.5分
		CheckBox mCb3277;
	@BindView(R.id.cb4_2_7_7)// 缺试件成型模具扣0.5分
		CheckBox mCb4277;
	@BindView(R.id.create)//创建人员
		Button mCreate;

	private List<CaTestJson> test;
	private List<EntEquipmentJson> mEntEquipmentJsons;
	private Long emid, id;
	private int uploadStatus;
	private String point,status = "";
	private double oldScore;


	@Override
	protected int getLayoutRes() {
		return R.layout.activity_description277;
	}

	@Override
	protected void initData() {
		mTextView4.setText(item + " " + title);//设置考核标题
		CreatePrrsonOrEquipment.equipment(mCreate, this, Integer.valueOf(eid), Integer.parseInt(cid), item);//创建设备
		mEntEquipmentJsons = EntEquipmentDao.query(eid, cid, item);// 设备表
		test = CaTestDao.query(cid, item);// 打分表
		List<CaConfigJson> config = CaConfigDao.query(item);// 配置表

		point = config.get(0).getSCORE();//基础分值
		mTvValue277.setText(point);// 分值
		mTvRule277.setText(config.get(0).getMEMO());// 扣分说明

		if (test.size() > 0) {
			id = test.get(0).getID();
			status = test.get(0).getSTATUS();
			mTvScore277.setText(test.get(0).getSCORE());// 得分
			if (test.get(0).getUPLOADSTATUS() >= 1){
				uploadStatus = 1;
			}else {
				uploadStatus = 0;
			}
			if (test.get(0).getCHOOSE() != null && !test.get(0).getCHOOSE().equals("")){
				String[] pChoose = test.get(0).getCHOOSE().split(",",-1);
				if (pChoose[0].equals("1")) {// 采用商品混凝土生产设备
					mCb1277.setChecked(true);
				}
				if (pChoose[1].equals("1")) {// 满足要求，配置齐全
					mCb2277.setChecked(true);
				}
				if (pChoose[2].equals("1")) {// 缺振捣设备扣0.5分
					mCb3277.setChecked(true);
				}
				if (pChoose[3].equals("1")) {// 缺试件成型模具扣0.5分
					mCb4277.setChecked(true);
				}
			}

		}

		if (mEntEquipmentJsons.size() > 0){
			emid = mEntEquipmentJsons.get(0).getID();// 设备id
			mEtName277.setText(mEntEquipmentJsons.get(0).getENAME());// 设备名称
			mEtType277.setText(mEntEquipmentJsons.get(0).getEMODEL());// 设备型号
			mEtNumber277.setText(mEntEquipmentJsons.get(0).getECODE());// 设备编号
			mEtRemark277.setText(mEntEquipmentJsons.get(0).getREMARK());// 备注
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
		mEtName277.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mCb3277.isChecked()){ //缺振捣设备扣0.5分
					mTvScore277.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb4277.isChecked()){// 缺试件成型模具扣0.5分
						mTvScore277.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb1277.isChecked()){// 采用商品混凝土生产设备
							if (mCb2277.isChecked()){// 满足要求，配置齐全
								mTvScore277.setText(ScoreUtil.scoreAll(point));
							}else {
								mTvScore277.setText(ScoreUtil.scoreHalf(point));
							}
						}else {
							if (mCb2277.isChecked()){// 满足要求，配置齐全
								mTvScore277.setText(ScoreUtil.scoreHalf(point));
							}else {
								mTvScore277.setText(ScoreUtil.SCORE_NOT);
							}
						}
					}
				}
			}
		});

		// 设备型号
		mEtType277.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mCb3277.isChecked()){ // 缺振捣设备扣0.5分
					mTvScore277.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb4277.isChecked()){// 缺试件成型模具扣0.5分
						mTvScore277.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb1277.isChecked()){// 采用商品混凝土生产设备
							if (mCb2277.isChecked()){// 满足要求，配置齐全
								mTvScore277.setText(ScoreUtil.scoreAll(point));
							}else {
								mTvScore277.setText(ScoreUtil.scoreHalf(point));
							}
						}else {
							if (mCb2277.isChecked()){// 满足要求，配置齐全
								mTvScore277.setText(ScoreUtil.scoreHalf(point));
							}else {
								mTvScore277.setText(ScoreUtil.SCORE_NOT);
							}
						}
					}
				}
			}
		});

		// 设备编号
		mEtNumber277.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mCb3277.isChecked()){ // 缺振捣设备扣0.5分
					mTvScore277.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb4277.isChecked()){// 缺试件成型模具扣0.5分
						mTvScore277.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb1277.isChecked()){// 采用商品混凝土生产设备
							if (mCb2277.isChecked()){// 满足要求，配置齐全
								mTvScore277.setText(ScoreUtil.scoreAll(point));
							}else {
								mTvScore277.setText(ScoreUtil.scoreHalf(point));
							}
						}else {
							if (mCb2277.isChecked()){// 满足要求，配置齐全
								mTvScore277.setText(ScoreUtil.scoreHalf(point));
							}else {
								mTvScore277.setText(ScoreUtil.SCORE_NOT);
							}
						}
					}
				}
			}
		});

		// 备注
		mEtRemark277.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mCb3277.isChecked()){ // 缺振捣设备扣0.5分
					mTvScore277.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb4277.isChecked()){// 缺试件成型模具扣0.5分
						mTvScore277.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb1277.isChecked()){// 采用商品混凝土生产设备
							if (mCb2277.isChecked()){// 满足要求，配置齐全
								mTvScore277.setText(ScoreUtil.scoreAll(point));
							}else {
								mTvScore277.setText(ScoreUtil.scoreHalf(point));
							}
						}else {
							if (mCb2277.isChecked()){// 满足要求，配置齐全
								mTvScore277.setText(ScoreUtil.scoreHalf(point));
							}else {
								mTvScore277.setText(ScoreUtil.SCORE_NOT);
							}
						}
					}
				}
			}
		});

		// 采用商品混凝土生产设备
		mCb1277.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					if (mCb3277.isChecked()){ // 缺振捣设备扣0.5分
						mTvScore277.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb4277.isChecked()){// 缺试件成型模具扣0.5分
							mTvScore277.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb2277.isChecked()){// 满足要求，配置齐全
								mTvScore277.setText(ScoreUtil.scoreAll(point));
							}else {
								mTvScore277.setText(ScoreUtil.scoreHalf(point));
							}
						}
					}
				}else {
					if (mCb3277.isChecked()){ // 缺振捣设备扣0.5分
						mTvScore277.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb4277.isChecked()){// 缺试件成型模具扣0.5分
							mTvScore277.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb2277.isChecked()){// 满足要求，配置齐全
								mTvScore277.setText(ScoreUtil.scoreHalf(point));
							}else {
								mTvScore277.setText(ScoreUtil.SCORE_NOT);
							}
						}
					}
				}
				saveData(status);
			}
		});

		// 满足要求，配置齐全
		mCb2277.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					if (mCb3277.isChecked()){ // 缺振捣设备扣0.5分
						mTvScore277.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb4277.isChecked()){// 缺试件成型模具扣0.5分
							mTvScore277.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb1277.isChecked()){// 采用商品混凝土生产设备
								mTvScore277.setText(ScoreUtil.scoreAll(point));
							}else {
								mTvScore277.setText(ScoreUtil.scoreHalf(point));
							}
						}
					}
				}else {
					if (mCb3277.isChecked()){ // 缺振捣设备扣0.5分
						mTvScore277.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb4277.isChecked()){// 缺试件成型模具扣0.5分
							mTvScore277.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb1277.isChecked()){// 采用商品混凝土生产设备
								mTvScore277.setText(ScoreUtil.scoreHalf(point));
							}else {
								mTvScore277.setText(ScoreUtil.SCORE_NOT);
							}
						}
					}
				}
				saveData(status);
			}
		});

		// 缺振捣设备扣0.5分
		mCb3277.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					mTvScore277.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb4277.isChecked()){// 缺试件成型模具扣0.5分
						mTvScore277.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb1277.isChecked()){// 采用商品混凝土生产设备
							if (mCb2277.isChecked()){// 满足要求，配置齐全
								mTvScore277.setText(ScoreUtil.scoreAll(point));
							}else {
								mTvScore277.setText(ScoreUtil.scoreHalf(point));
							}
						}else {
							if (mCb2277.isChecked()){// 满足要求，配置齐全
								mTvScore277.setText(ScoreUtil.scoreHalf(point));
							}else {
								mTvScore277.setText(ScoreUtil.SCORE_NOT);
							}
						}
					}
				}
				saveData(status);
			}
		});

		// 缺试件成型模具扣0.5分
		mCb4277.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					mTvScore277.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb4277.isChecked()){// 缺振捣设备扣0.5分
						mTvScore277.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCb1277.isChecked()){// 采用商品混凝土生产设备
							if (mCb2277.isChecked()){// 满足要求，配置齐全
								mTvScore277.setText(ScoreUtil.scoreAll(point));
							}else {
								mTvScore277.setText(ScoreUtil.scoreHalf(point));
							}
						}else {
							if (mCb2277.isChecked()){// 满足要求，配置齐全
								mTvScore277.setText(ScoreUtil.scoreHalf(point));
							}else {
								mTvScore277.setText(ScoreUtil.SCORE_NOT);
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
		if (mCb1277.isChecked()) {// 采用商品混凝土生产设备
			condition1 = 1;
		} else {
			condition1 = 0;
		}

		int condition2;
		if (mCb2277.isChecked()) {// 满足要求，配置齐全
			condition2 = 1;
		} else {
			condition2 = 0;
		}

		int condition3;
		if (mCb3277.isChecked()) {// 缺振捣设备扣0.5分
			condition3 = 1;
		} else {
			condition3 = 0;
		}

		int condition4;
		if (mCb4277.isChecked()) {// 缺试件成型模具扣0.5分
			condition4 = 1;
		} else {
			condition4 = 0;
		}

		String choose = condition1 + "," + condition2 + "," + condition3 + "," + condition4;

		String name = mEtName277.getText().toString();// 设备名称
		String specification = mEtType277.getText().toString();// 设备型号
		String code = mEtNumber277.getText().toString();// 设备编号
		String remark = mEtRemark277.getText().toString();// 备注
		String point = String.valueOf(mTvScore277.getText());// 得分
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
