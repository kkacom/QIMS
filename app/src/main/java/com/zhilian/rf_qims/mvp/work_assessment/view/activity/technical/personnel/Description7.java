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
 * 大型设备（打分明细2.6.10）
 */
public class Description7 extends BaseModuleActivity {
	@BindView(R.id.textView4)// 标题
		TextView mTextView4;

	@BindView(R.id.et_name2_6_10)
	EditText mEtName2610;// 设备名称
	@BindView(R.id.et_type2_6_10)
	EditText mEtType2610;// 设备型号
	@BindView(R.id.et_number2_6_10)
	EditText mEtNumber2610;// 设备编号
	@BindView(R.id.et_remark2_6_10)
	EditText mEtRemark2610;// 备注

	@BindView(R.id.tv_rule2_6_10)
	TextView mTvRule2610;// 扣分说明
	@BindView(R.id.tv_value2_6_10)
	TextView mTvValue2610;// 分值
	@BindView(R.id.tv_score2_6_10)
	TextView mTvScore2610;// 得分

	@BindView(R.id.cb1_2_6_10)// 缺设备不得分
		CheckBox mCb12610;
	@BindView(R.id.cb2_2_6_10)// 无付款凭证或租赁凭证不得分
		CheckBox mCb22610;
	@BindView(R.id.cb3_2_6_10)// 无法整体作业扣一半分
		CheckBox mCb32610;
	@BindView(R.id.cb4_2_6_10)// 无防护用具扣一半分
		CheckBox mCb42610;
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
		return R.layout.activity_description7;
	}

	@Override
	protected void initData() {
		mTextView4.setText(item + " " + title);//设置考核标题
		CreatePrrsonOrEquipment.equipment(mCreate, this, Integer.valueOf(eid), Integer.parseInt(cid), item);//创建设备
		mEntEquipmentJsons = EntEquipmentDao.query(eid, cid, item);// 设备表
		test = CaTestDao.query(cid, item);// 打分表
		List<CaConfigJson> config = CaConfigDao.query(item);// 配置表

		point = config.get(0).getSCORE();//基础分值
		mTvValue2610.setText(point);// 分值
		mTvScore2610.setText(point);// 得值
		mTvRule2610.setText(config.get(0).getMEMO());// 扣分说明

		if (test.size() > 0) {
			id = test.get(0).getID();
			status = test.get(0).getSTATUS();
			mTvScore2610.setText(test.get(0).getSCORE());// 得分
			if (test.get(0).getUPLOADSTATUS() >= 1){
				uploadStatus = 1;
			}else {
				uploadStatus = 0;
			}
			if (test.get(0).getCHOOSE() != null && !test.get(0).getCHOOSE().equals("")){
				String[] pChoose = test.get(0).getCHOOSE().split(",",-1);
				if (pChoose[0].equals("1")) {// 缺设备不得分
					mCb12610.setChecked(true);
				}
				if (pChoose[1].equals("1")) {// 无付款凭证或租赁凭证不得分
					mCb22610.setChecked(true);
				}
				if (pChoose[2].equals("1")) {// 无法整体作业扣一半分
					mCb32610.setChecked(true);
				}
				if (pChoose[3].equals("1")) {// 无防护用具扣一半分
					mCb42610.setChecked(true);
				}
			}
		}else {
			uploadStatus = 0;
		}

		if (mEntEquipmentJsons.size() > 0){
			emid = mEntEquipmentJsons.get(0).getID();// 设备id
			mEtName2610.setText(mEntEquipmentJsons.get(0).getENAME());// 设备名称
			mEtType2610.setText(mEntEquipmentJsons.get(0).getEMODEL());// 设备型号
			mEtNumber2610.setText(mEntEquipmentJsons.get(0).getECODE());// 设备编号
			mEtRemark2610.setText(mEntEquipmentJsons.get(0).getREMARK());// 备注
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
		mEtName2610.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mCb12610.isChecked()) { //缺设备
					mTvScore2610.setText(ScoreUtil.SCORE_NOT);
				} else {
					if (mCb22610.isChecked()) {// 无付款凭证或租赁凭证不得分
						mTvScore2610.setText(ScoreUtil.SCORE_NOT);
					} else {
						if (mCb32610.isChecked()) {// 无法整体作业扣一半分
							if (mCb42610.isChecked()) {// 无防护用具扣一半分
								mTvScore2610.setText(ScoreUtil.SCORE_NOT);
							} else {
								mTvScore2610.setText(ScoreUtil.scoreHalf(point));
							}
						} else {
							if (mCb42610.isChecked()) {// 无防护用具扣一半分
								mTvScore2610.setText(ScoreUtil.scoreHalf(point));
							} else {
								mTvScore2610.setText(ScoreUtil.scoreAll(point));
							}
						}
					}
				}
			}
		});

		// 设备型号
		mEtType2610.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mCb12610.isChecked()) { //缺设备
					mTvScore2610.setText(ScoreUtil.SCORE_NOT);
				} else {
					if (mCb22610.isChecked()) {// 无付款凭证或租赁凭证不得分
						mTvScore2610.setText(ScoreUtil.SCORE_NOT);
					} else {
						if (mCb32610.isChecked()) {// 无法整体作业扣一半分
							if (mCb42610.isChecked()) {// 无防护用具扣一半分
								mTvScore2610.setText(ScoreUtil.SCORE_NOT);
							} else {
								mTvScore2610.setText(ScoreUtil.scoreHalf(point));
							}
						} else {
							if (mCb42610.isChecked()) {// 无防护用具扣一半分
								mTvScore2610.setText(ScoreUtil.scoreHalf(point));
							} else {
								mTvScore2610.setText(ScoreUtil.scoreAll(point));
							}
						}
					}
				}
			}
		});

		// 设备编号
		mEtNumber2610.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mCb12610.isChecked()) { //缺设备
					mTvScore2610.setText(ScoreUtil.SCORE_NOT);
				} else {
					if (mCb22610.isChecked()) {// 无付款凭证或租赁凭证不得分
						mTvScore2610.setText(ScoreUtil.SCORE_NOT);
					} else {
						if (mCb32610.isChecked()) {// 无法整体作业扣一半分
							if (mCb42610.isChecked()) {// 无防护用具扣一半分
								mTvScore2610.setText(ScoreUtil.SCORE_NOT);
							} else {
								mTvScore2610.setText(ScoreUtil.scoreHalf(point));
							}
						} else {
							if (mCb42610.isChecked()) {// 无防护用具扣一半分
								mTvScore2610.setText(ScoreUtil.scoreHalf(point));
							} else {
								mTvScore2610.setText(ScoreUtil.scoreAll(point));
							}
						}
					}
				}
			}
		});

		// 备注
		mEtRemark2610.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mCb12610.isChecked()) { //缺设备
					mTvScore2610.setText(ScoreUtil.SCORE_NOT);
				} else {
					if (mCb22610.isChecked()) {// 无付款凭证或租赁凭证不得分
						mTvScore2610.setText(ScoreUtil.SCORE_NOT);
					} else {
						if (mCb32610.isChecked()) {// 无法整体作业扣一半分
							if (mCb42610.isChecked()) {// 无防护用具扣一半分
								mTvScore2610.setText(ScoreUtil.SCORE_NOT);
							} else {
								mTvScore2610.setText(ScoreUtil.scoreHalf(point));
							}
						} else {
							if (mCb42610.isChecked()) {// 无防护用具扣一半分
								mTvScore2610.setText(ScoreUtil.scoreHalf(point));
							} else {
								mTvScore2610.setText(ScoreUtil.scoreAll(point));
							}
						}
					}
				}
			}
		});

		// 缺设备
		mCb12610.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mTvScore2610.setText(ScoreUtil.SCORE_NOT);
				} else {
					if (mCb22610.isChecked()) {// 无付款凭证或租赁凭证不得分
						mTvScore2610.setText(ScoreUtil.SCORE_NOT);
					} else {
						if (mCb32610.isChecked()) {// 无法整体作业扣一半分
							if (mCb42610.isChecked()) {// 无防护用具扣一半分
								mTvScore2610.setText(ScoreUtil.SCORE_NOT);
							} else {
								mTvScore2610.setText(ScoreUtil.scoreHalf(point));
							}
						} else {
							if (mCb42610.isChecked()) {// 无防护用具扣一半分
								mTvScore2610.setText(ScoreUtil.scoreHalf(point));
							} else {
								mTvScore2610.setText(ScoreUtil.scoreAll(point));
							}
						}
					}
				}
				saveData(status);
			}
		});

		// 无付款凭证或租赁凭证不得分
		mCb22610.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mTvScore2610.setText(ScoreUtil.SCORE_NOT);
				} else {
					if (mCb12610.isChecked()) {// 缺设备
						mTvScore2610.setText(ScoreUtil.SCORE_NOT);
					} else {
						if (mCb32610.isChecked()) {// 无法整体作业扣一半分
							if (mCb42610.isChecked()) {// 无防护用具扣一半分
								mTvScore2610.setText(ScoreUtil.SCORE_NOT);
							} else {
								mTvScore2610.setText(ScoreUtil.scoreHalf(point));
							}
						} else {
							if (mCb42610.isChecked()) {// 无防护用具扣一半分
								mTvScore2610.setText(ScoreUtil.scoreHalf(point));
							} else {
								mTvScore2610.setText(ScoreUtil.scoreAll(point));
							}
						}
					}
				}
				saveData(status);
			}
		});

		// 无法整体作业扣一半分
		mCb32610.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					if (mCb12610.isChecked()) {// 缺设备
						mTvScore2610.setText(ScoreUtil.SCORE_NOT);
					} else {
						if (mCb22610.isChecked()) {// 无付款凭证或租赁凭证不得分
							mTvScore2610.setText(ScoreUtil.SCORE_NOT);
						} else {
							if (mCb42610.isChecked()) {// 无防护用具扣一半分
								mTvScore2610.setText(ScoreUtil.SCORE_NOT);
							} else {
								mTvScore2610.setText(ScoreUtil.scoreHalf(point));
							}
						}
					}
				} else {
					if (mCb12610.isChecked()) {// 缺设备
						mTvScore2610.setText(ScoreUtil.SCORE_NOT);
					} else {
						if (mCb22610.isChecked()) {// 无付款凭证或租赁凭证不得分
							mTvScore2610.setText(ScoreUtil.SCORE_NOT);
						} else {
							if (mCb42610.isChecked()) {// 无防护用具扣一半分
								mTvScore2610.setText(ScoreUtil.scoreHalf(point));
							} else {
								mTvScore2610.setText(ScoreUtil.scoreAll(point));
							}
						}
					}
				}
				saveData(status);
			}
		});

		// 无防护用具扣一半分
		mCb42610.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					if (mCb12610.isChecked()) {// 缺设备
						mTvScore2610.setText(ScoreUtil.SCORE_NOT);
					} else {
						if (mCb22610.isChecked()) {// 无付款凭证或租赁凭证不得分
							mTvScore2610.setText(ScoreUtil.SCORE_NOT);
						} else {
							if (mCb32610.isChecked()) {// 无法整体作业扣一半分
								mTvScore2610.setText(ScoreUtil.SCORE_NOT);
							} else {
								mTvScore2610.setText(ScoreUtil.scoreHalf(point));
							}
						}
					}
				} else {
					if (mCb12610.isChecked()) {// 缺设备
						mTvScore2610.setText(ScoreUtil.SCORE_NOT);
					} else {
						if (mCb22610.isChecked()) {// 无付款凭证或租赁凭证不得分
							mTvScore2610.setText(ScoreUtil.SCORE_NOT);
						} else {
							if (mCb32610.isChecked()) {// 无法整体作业扣一半分
								mTvScore2610.setText(ScoreUtil.scoreHalf(point));
							} else {
								mTvScore2610.setText(ScoreUtil.scoreAll(point));
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
		if (mCb12610.isChecked()) {// 缺设备不得分
			condition1 = 1;
		} else {
			condition1 = 0;
		}

		int condition2;
		if (mCb22610.isChecked()) {// 总面积少于50㎡不得分
			condition2 = 1;
		} else {
			condition2 = 0;
		}

		int condition3;
		if (mCb32610.isChecked()) {// 公差大于0.5mm不得分
			condition3 = 1;
		} else {
			condition3 = 0;
		}

		int condition4;
		if (mCb42610.isChecked()) {// 主平台尺寸不满足不得分
			condition4 = 1;
		} else {
			condition4 = 0;
		}

		String choose = condition1 + "," + condition2 + "," + condition3 + "," + condition4;

		String name = mEtName2610.getText().toString();// 设备名称
		String specification = mEtType2610.getText().toString();// 设备型号
		String code = mEtNumber2610.getText().toString();// 设备编号
		String remark = mEtRemark2610.getText().toString();// 备注
		String point = String.valueOf(mTvScore2610.getText());// 得分
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
