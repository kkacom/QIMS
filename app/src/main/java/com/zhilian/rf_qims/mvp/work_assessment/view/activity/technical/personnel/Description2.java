package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.zhilian.rf_qims.entity.PersonnelDao;
import com.zhilian.rf_qims.entity.PersonnelJson;
import com.zhilian.rf_qims.entity.WorkAbilityJson;
import com.zhilian.rf_qims.util.Compute;
import com.zhilian.rf_qims.util.ScoreUtil;
import com.zhilian.rf_qims.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhilian.rf_qims.util.CommonUtils.getContext;

/**
 * 技术人员（2.2.1-2.2.3）
 */
public class Description2 extends BaseModuleActivity {
	Long rid;
	Long pid;
	@BindView(R.id.textView4)//	标题
	TextView mTextView4;
	@BindView(R.id.rb1_yes2_1_5)
	RadioButton mRb1Yes215;// 检查、不检查
	@BindView(R.id.rb1_no2_1_5)
	RadioButton mRb1No215;
	@BindView(R.id.rb2_yes2_1_5)
	RadioButton mRb2Yes215;// 满足、不满足
	@BindView(R.id.rb2_no2_1_5)
	RadioButton mRb2No215;
	@BindView(R.id.rb3_yes2_1_5)
	RadioButton mRb3Yes215;// 在岗、不在岗
	@BindView(R.id.rb3_no2_1_5)
	RadioButton mRb3No215;
	@BindView(R.id.rb4_yes2_1_5)
	RadioButton mRb4Yes215;// 有社保、无社保
	@BindView(R.id.rb4_no2_1_5)
	RadioButton mRb4No215;
	@BindView(R.id.rb5_yes2_1_5)
	RadioButton mRb5Yes215;// 有劳动合同、无劳动合同
	@BindView(R.id.rb5_no2_1_5)
	RadioButton mRb5No215;
	@BindView(R.id.rb6_yes2_1_5)
	RadioButton mRb6Yes215;// 考核满意、考核不满意
	@BindView(R.id.rb6_no2_1_5)
	RadioButton mRb6No215;
	@BindView(R.id.rb7_yes2_1_5)
	RadioButton mRb7Yes215;// 有职称或资格证书
	@BindView(R.id.rb7_no2_1_5)
	RadioButton mRb7No215;

	@BindView(R.id.p_score2_1_5)
	TextView mPScore215;// 个人得分
	@BindView(R.id.et_name2_1_5)
	EditText mEtName215;// 姓名
	@BindView(R.id.et_card2_1_5)
	EditText mEtCard215;// 身份证
	@BindView(R.id.et_phone2_1_5)
	EditText mEtPhone215;// 手机号码
	@BindView(R.id.et_time2_1_5)
	EditText mEtTime215;// 入职时间
	@BindView(R.id.et_remark2_1_5)
	EditText mEtRemark215;// 备注

	@BindView(R.id.tv_rule2_1_5)
	TextView mTvRule215;// 扣分说明

	private int uploadStatus, work_type;
	private String point, status = "";
	private double oldScore;

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_description2;
	}

	/**
	 * 初始化数据
	 */
	@Override
	protected void initData() {
		mTextView4.setText(item + title);//设置考核标题
		PersonnelJson personnelJson = (PersonnelJson) getIntent().getSerializableExtra("personnel");// 人员表
		CaTestJson caTestJson = (CaTestJson) getIntent().getSerializableExtra("test");// 打分表
		mTvRule215.setText(getIntent().getStringExtra("configExplain"));// 扣分说明
		point = getIntent().getStringExtra("configPoint");//基础分值

		if (caTestJson != null) {
			rid = caTestJson.getID();
			status = caTestJson.getSTATUS();
			if (caTestJson.getUPLOADSTATUS() >= 1){
				uploadStatus = 1;
			}else {
				uploadStatus = 0;
			}
		}else {
			uploadStatus = 0;
		}
		pid = personnelJson.getID();
		work_type = personnelJson.getWORK_TYPE();
		mPScore215.setText(personnelJson.getSCORE());// 个人得分
		mEtName215.setText(personnelJson.getPERNAME());// 姓名
		mEtCard215.setText(personnelJson.getIDNUMBER());// 身份证
		mEtPhone215.setText(personnelJson.getPHONE());// 手机号码
		mEtTime215.setText(personnelJson.getHIREDATE());// 入职时间
		mEtRemark215.setText(personnelJson.getREMARK());// 备注

		if (personnelJson.getCHOOSE() != null){
			String[] pChoose = personnelJson.getCHOOSE().split(",",-1);
			if (personnelJson.getSTATUS().equals("检查")) {// 检查、不检查
				mRb1Yes215.setChecked(true);
			} else {
				mRb1No215.setChecked(true);
			}
			if (pChoose[0].equals("1")) {// 0 满足、1 不满足
				mRb2Yes215.setChecked(true);
			} else {
				mRb2No215.setChecked(true);
			}
			if (pChoose[1].equals("1")) {// 0 在岗、1 不在岗
				mRb3Yes215.setChecked(true);
			} else {
				mRb3No215.setChecked(true);
			}
			if (pChoose[2].equals("1")) {// 0 有社保、1 无社保
				mRb4Yes215.setChecked(true);
			} else {
				mRb4No215.setChecked(true);
			}
			if (pChoose[3].equals("1")) {// 0 有劳动合同、1 无劳动合同
				mRb5Yes215.setChecked(true);
			} else {
				mRb5No215.setChecked(true);
			}
			if (pChoose[4].equals("1")) {// 0 考核满意、1 考核不满意
				mRb6Yes215.setChecked(true);
			} else {
				mRb6No215.setChecked(true);
			}

			if (pChoose[5].equals("1")) {// 0 有职称或资格证书、1 无职称或资格证书
				mRb7Yes215.setChecked(true);
			} else {
				mRb7No215.setChecked(true);
			}
		}
	}

	/**
	 * 选择不同人员时加载数据
	 */
	private void loadingData(int position) {

	}

	/**
	 * 事件监听
	 */
	@Override
	protected void listener() {

		eTListener(mEtName215);// 姓名
		eTListener(mEtCard215);// 身份证
		eTListener(mEtPhone215);// 手机号码
		eTListener(mEtTime215);// 入职时间
		eTListener(mEtRemark215);// 备注

		// 检查 不检查
		mRb1Yes215.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 1 检查
					if (mRb2Yes215.isChecked()) {// 满意
						if (mRb4Yes215.isChecked()) {// 有社保
							if (mRb5Yes215.isChecked()) {// 有劳动合同
								if (mRb6Yes215.isChecked()) {// 考核满意
									if (mRb7Yes215.isChecked()) {// 有职称或资格证书
										if (mRb3Yes215.isChecked()) {// 在岗
											// 个人得分
											mPScore215.setText(ScoreUtil.scoreAll(point));
										} else {// 不在岗
											mPScore215.setText(ScoreUtil.scoreHalf(point));
										}
									} else {// 无职称或资格证书
										mPScore215.setText("0.3");
									}
								} else {// 考核不满意
									mPScore215.setText(ScoreUtil.SCORE_NOT);
								}
							} else {// 无劳动合同
								mPScore215.setText(ScoreUtil.SCORE_NOT);
							}
						} else {// 无社保
							mPScore215.setText(ScoreUtil.SCORE_NOT);
						}
					} else {// 不满意
						if (mRb4Yes215.isChecked()) {// 有社保
							if (mRb5Yes215.isChecked()) {// 有劳动合同
								if (mRb6Yes215.isChecked()) {// 考核满意
									if (mRb7Yes215.isChecked()) {// 有职称或资格证书
										if (mRb3Yes215.isChecked()) {// 在岗
											mPScore215.setText(ScoreUtil.scoreAll(point));
										} else {
											mPScore215.setText(ScoreUtil.scoreHalf(point));
										}
									} else {// 无职称或资格证书
										mPScore215.setText(ScoreUtil.SCORE_NOT);
									}
								} else {// 考核不满意
									mPScore215.setText(ScoreUtil.SCORE_NOT);
								}
							} else {// 无劳动合同
								mPScore215.setText(ScoreUtil.SCORE_NOT);
							}
						} else {// 无社保
							mPScore215.setText(ScoreUtil.SCORE_NOT);
						}
					}
				} else {// 2 不检查
					mPScore215.setText(ScoreUtil.SCORE_NOT);
				}
				saveData(status);
			}
		});

		// 满意 不满意
		mRb2Yes215.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 1 满意
					mRb3Yes215.setChecked(true);
					mRb4Yes215.setChecked(true);
					mRb5Yes215.setChecked(true);
					mRb6Yes215.setChecked(true);
					mRb7Yes215.setChecked(true);
				} else {// 2 不满足

				}
			}
		});

		// 在岗 不在岗
		mRb3Yes215.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (mRb1Yes215.isChecked()) {// 检查
					if (isChecked) {// 1 在岗
						if (mRb4Yes215.isChecked()) {// 有社保
							if (mRb5Yes215.isChecked()) {// 有劳动合同
								if (mRb6Yes215.isChecked()) {// 考核满意
									if (mRb7Yes215.isChecked()) {// 有职称或资格证书
										mPScore215.setText(ScoreUtil.scoreAll(point));
										mRb2Yes215.setChecked(true);
									} else {// 有职称或资格证书
										mPScore215.setText(ScoreUtil.SCORE_NOT);
										mRb2No215.setChecked(true);
									}
								} else {// 考核不满意
									mPScore215.setText(ScoreUtil.SCORE_NOT);
									mRb2No215.setChecked(true);
								}
							} else {// 不劳动合同
								mPScore215.setText("0.30");
								mPScore215.setText(ScoreUtil.SCORE_NOT);
								mRb2No215.setChecked(true);
							}
						} else {// 无社保
							mPScore215.setText(ScoreUtil.SCORE_NOT);
							mRb2No215.setChecked(true);
						}
					} else {// 2 不在岗
						if (mRb4Yes215.isChecked()) {// 有社保
							if (mRb5Yes215.isChecked()) {// 有劳动合同
								if (mRb6Yes215.isChecked()) {// 考核满意
									if (mRb7Yes215.isChecked()) {// 有职称或资格证书
										mPScore215.setText(ScoreUtil.scoreHalf(point));
										mRb2Yes215.setChecked(true);
									} else {// 有职称或资格证书
										mPScore215.setText(ScoreUtil.SCORE_NOT);
										mRb2No215.setChecked(true);
									}
								} else {// 考核不满意
									mPScore215.setText(ScoreUtil.SCORE_NOT);
									mRb2No215.setChecked(true);
								}
							} else {// 不劳动合同
								mPScore215.setText("0.30");
								mPScore215.setText(ScoreUtil.SCORE_NOT);
								mRb2No215.setChecked(true);
							}
						} else {// 无社保
							mPScore215.setText(ScoreUtil.SCORE_NOT);
							mRb2No215.setChecked(true);
						}
					}
					saveData(status);
				}
			}
		});

		// 有社保 无社保
		mRb4Yes215.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (mRb1Yes215.isChecked()) {// 检查
					if (isChecked) {// 1 有社保
						if (mRb5Yes215.isChecked()) {// 有劳动合同
							if (mRb6Yes215.isChecked()) {// 考核满意
								if (mRb7Yes215.isChecked()) {// 有职称或资格证书
									if (mRb3Yes215.isChecked()) {// 在岗
										mPScore215.setText(ScoreUtil.scoreAll(point));
										mRb2Yes215.setChecked(true);
									} else {// 不在岗
										mPScore215.setText(ScoreUtil.scoreHalf(point));
										mRb2Yes215.setChecked(true);
									}
								} else {// 无职称或资格证书
									mPScore215.setText(ScoreUtil.SCORE_NOT);
									mRb2No215.setChecked(true);
								}
							} else {// 考核不满意
								mPScore215.setText(ScoreUtil.SCORE_NOT);
								mRb2No215.setChecked(true);
							}
						} else {// 无劳动合同
							mPScore215.setText(ScoreUtil.SCORE_NOT);
							mRb2No215.setChecked(true);
						}
					} else {// 2 无社保
						mPScore215.setText(ScoreUtil.SCORE_NOT);
						mRb2No215.setChecked(true);
					}
					saveData(status);
				}
			}
		});

		// 有劳动合同 无劳动合同
		mRb5Yes215.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (mRb1Yes215.isChecked()) {// 检查
					if (isChecked) {// 1 有劳动合同
						if (mRb4Yes215.isChecked()) {// 有社保
							if (mRb6Yes215.isChecked()) {// 考核满意
								if (mRb7Yes215.isChecked()) {// 有职称或资格证书
									if (mRb3Yes215.isChecked()) {// 在岗
										mPScore215.setText(ScoreUtil.scoreAll(point));
										mRb2Yes215.setChecked(true);
									} else {// 不在岗
										mPScore215.setText(ScoreUtil.scoreHalf(point));
										mRb2Yes215.setChecked(true);
									}
								} else {// 无职称或资格证书
									mPScore215.setText(ScoreUtil.SCORE_NOT);
									mRb2No215.setChecked(true);
								}
							} else {// 考核不满意
								mPScore215.setText(ScoreUtil.SCORE_NOT);
								mRb2No215.setChecked(true);
							}
						} else {// 无社保
							mPScore215.setText(ScoreUtil.SCORE_NOT);
							mRb2No215.setChecked(true);
						}
					} else {// 2 无劳动合同
						mPScore215.setText(ScoreUtil.SCORE_NOT);
						mRb2No215.setChecked(true);
					}
					saveData(status);
				}
			}
		});

		// 考核满意 考核不满意
		mRb6Yes215.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (mRb1Yes215.isChecked()) {// 检查
					if (isChecked) {// 1 考核满意
						if (mRb4Yes215.isChecked()) {// 有社保
							if (mRb5Yes215.isChecked()) {// 有劳动合同
								if (mRb7Yes215.isChecked()) {// 有职称或资格证书
									if (mRb3Yes215.isChecked()) {// 在岗
										mPScore215.setText(ScoreUtil.scoreAll(point));
										mRb2Yes215.setChecked(true);
									} else {// 不在岗
										mPScore215.setText(ScoreUtil.scoreHalf(point));
										mRb2Yes215.setChecked(true);
									}
								} else {// 无职称或资格证书
									mPScore215.setText(ScoreUtil.SCORE_NOT);
									mRb2No215.setChecked(true);
								}
							} else {// 有劳动合同
								mPScore215.setText(ScoreUtil.SCORE_NOT);
								mRb2No215.setChecked(true);
							}
						} else {// 无社保
							mPScore215.setText(ScoreUtil.SCORE_NOT);
							mRb2No215.setChecked(true);
						}
					} else {// 2 考核不满意
						mPScore215.setText(ScoreUtil.SCORE_NOT);
						mRb2No215.setChecked(true);
					}
					saveData(status);
				}
			}
		});

		// 有职称或资格证书 无职称或资格证书
		mRb7Yes215.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (mRb1Yes215.isChecked()) {// 检查
					if (isChecked) {// 1 有职称或资格证书
						if (mRb4Yes215.isChecked()) {// 有社保
							if (mRb5Yes215.isChecked()) {// 有劳动合同
								if (mRb6Yes215.isChecked()) {// 考核满意
									if (mRb3Yes215.isChecked()) {// 在岗
										mPScore215.setText(ScoreUtil.scoreAll(point));
										mRb2Yes215.setChecked(true);
									} else {// 不在岗
										mPScore215.setText(ScoreUtil.scoreHalf(point));
										mRb2Yes215.setChecked(true);
									}
								} else {// 考核不满意
									mPScore215.setText(ScoreUtil.SCORE_NOT);
									mRb2No215.setChecked(true);
								}
							} else {// 有劳动合同
								mPScore215.setText(ScoreUtil.SCORE_NOT);
								mRb2No215.setChecked(true);
							}
						} else {// 无社保
							mPScore215.setText(ScoreUtil.SCORE_NOT);
							mRb2No215.setChecked(true);
						}
					} else {// 2 无职称或资格证书
						mPScore215.setText(ScoreUtil.SCORE_NOT);
						mRb2No215.setChecked(true);
					}
					saveData(status);
				}
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
	 * 保存和更改状态
	 *
	 * @param status 状态（1 完成、2 未完成）
	 */
	@Override
	protected void saveStatus(String status) {
		saveData(status);
	}

	/**
	 * EditText的事件监听
	 *
	 * @param et
	 */
	private void eTListener(EditText et) {
		et.addTextChangedListener(new TextWatcher() {
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
	}

	/**
	 * 保存数据
	 *
	 * @param status 状态
	 */
	private void saveData(String status) {
		/*☆先计算一遍旧item得分，因为很多item都有可能创建多于需要检测的人数，需要把所有item的分数排序大小再进行多余筛选会非常复杂。
		故反向思考，计算出当前旧item的分数用总item减去旧item分数，最后到计算总分再加上新分数就可以了(Compute.compute(cid, item, oldScore);)*/
		oldScore = Compute.compute(cid, item, oldScore);
		String isCheck;
		if (mRb1Yes215.isChecked()) {// 检查、不检查
			isCheck = "检查";
		} else {
			isCheck = "不检查";
		}

		int condition1;
		if (mRb2Yes215.isChecked()) {// 0 满足、1 不满足
			condition1 = 1;
		} else {
			condition1 = 0;
		}

		int condition2;
		if (mRb3Yes215.isChecked()) {// 0 在岗、1 不在岗
			condition2 = 1;
		} else {
			condition2 = 0;
		}

		int condition3;
		if (mRb4Yes215.isChecked()) {// 0 有社保、1 无社保
			condition3 = 1;
		} else {
			condition3 = 0;
		}

		int condition4;
		if (mRb5Yes215.isChecked()) {// 0 有劳动合同、1 无劳动合同
			condition4 = 1;
		} else {
			condition4 = 0;
		}

		int condition5;
		if (mRb6Yes215.isChecked()) {// 0 考核满意、1 考核不满意
			condition5 = 1;
		} else {
			condition5 = 0;
		}

		int condition6;
		if (mRb7Yes215.isChecked()) {// 0 有职称或资格证书、1 无职称或资格证书
			condition6 = 1;
		} else {
			condition6 = 0;
		}

		String choose = condition1 + "," + condition2 + "," + condition3 + ","
			+ condition4 + "," + condition5 + "," + condition6;

		String pScore = mPScore215.getText().toString();// 个人得分
		String name = mEtName215.getText().toString();// 姓名
		String card = mEtCard215.getText().toString();// 身份证
		String phone = mEtPhone215.getText().toString();// 手机号码
		String time = mEtTime215.getText().toString();// 入职时间
		String remark = mEtRemark215.getText().toString();// 备注
		if (name.equals("") || card.equals("")){
			ToastUtils.show("姓名和身份证不能为空");
			return;
		}
		if (status.equals("1") || status.equals("2")){
			if (pScore.equals("")){
				ToastUtils.show("请进行打分");
				return;
			}
		}

		PersonnelJson personnelJson = null;
		CaTestJson caTestJson = null;

		personnelJson = new PersonnelJson(pid, Integer.valueOf(eid), Integer.valueOf(cid),
			item, name, card, choose, pScore, isCheck, remark, phone, time, uploadStatus, work_type);
		if (rid != null) {
			caTestJson = new CaTestJson(rid, cid, item, pScore, choose, remark, status, uploadStatus);
			CaTestDao.insertOrReplace(caTestJson);
		} else {
			caTestJson = new CaTestJson(cid, item, pScore, choose, remark, status, uploadStatus);
			CaTestDao.insertOrReplace(caTestJson);
			List<CaTestJson> list = CaTestDao.query(cid, item);
			if (list != null && list.size() > 0){
				rid = list.get(list.size() - 1).getID();
			}
		}

		PersonnelDao.update(personnelJson);

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
}
