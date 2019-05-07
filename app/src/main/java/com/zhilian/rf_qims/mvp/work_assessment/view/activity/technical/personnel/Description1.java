package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.base.BaseModuleActivity;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
import com.zhilian.rf_qims.entity.CaTestDao;
import com.zhilian.rf_qims.entity.CaTestJson;
import com.zhilian.rf_qims.entity.PersonnelDao;
import com.zhilian.rf_qims.entity.PersonnelJson;
import com.zhilian.rf_qims.entity.WorkAbilityJson;
import com.zhilian.rf_qims.util.Compute;
import com.zhilian.rf_qims.util.CreatePrrsonOrEquipment;
import com.zhilian.rf_qims.util.ScoreUtil;
import com.zhilian.rf_qims.util.ToastUtils;
import com.zhilian.rf_qims.widget.MediaLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhilian.rf_qims.util.CommonUtils.getContext;

/**
 * 关键人员（2.1.1-2.1.4）
 */
public class Description1 extends BaseModuleActivity {
	@BindView(R.id.rb1_yes2_1_1)
	RadioButton mRb1Yes211;// 满足、不满足
	@BindView(R.id.rb1_no2_1_1)
	RadioButton mRb1No211;
	@BindView(R.id.rb2_yes2_1_1)
	RadioButton mRb2Yes211;// 在岗、不在岗
	@BindView(R.id.rb2_no2_1_1)
	RadioButton mRb2No211;
	@BindView(R.id.rb3_yes2_1_1)
	RadioButton mRb3Yes211;// 有社保、无社保
	@BindView(R.id.rb3_no2_1_1)
	RadioButton mRb3No211;
	@BindView(R.id.rb4_yes2_1_1)
	RadioButton mRb4Yes211;// 有职称证书、无职称证书
	@BindView(R.id.rb4_no2_1_1)
	RadioButton mRb4No211;
	@BindView(R.id.rb5_yes2_1_1)
	RadioButton mRb5Yes211;// 有学历证书、无学历证书
	@BindView(R.id.rb5_no2_1_1)
	RadioButton mRb5No211;
	@BindView(R.id.rb6_yes2_1_1)
	RadioButton mRb6Yes211;// 考核满意、不满意
	@BindView(R.id.rb6_no2_1_1)
	RadioButton mRb6No211;
	@BindView(R.id.rb7_yes2_1_1)
	RadioButton mRb7Yes211;// 有劳动合同、无劳动合同
	@BindView(R.id.rb7_no2_1_1)
	RadioButton mRb7No211;
	@BindView(R.id.rb8_yes2_1_1)
	RadioButton mRb8Yes211;// 有任命书、无任命书
	@BindView(R.id.rb8_no2_1_1)
	RadioButton mRb8No211;

	@BindView(R.id.tv_value2_1_1)
	TextView mTvValue211;// 分值
	@BindView(R.id.tv_score2_1_1)
	TextView mTvScore211;// 得分
	@BindView(R.id.tv_rule2_1_1)
	TextView mTvRule211;// 扣分说明

	@BindView(R.id.et_name2_1_1)
	EditText mEtName211;// 姓名
	@BindView(R.id.et_card2_1_1)
	EditText mEtCard211;// 身份证
	@BindView(R.id.et_phone2_1_1)
	EditText mEtPhone211;// 手机号码
	@BindView(R.id.et_time2_1_1)
	EditText mEtTime211;// 入职时间
	@BindView(R.id.et_remark2_1_1)
	EditText mEtRemark211;// 备注
	@BindView(R.id.textView4)
	TextView mTextView4;//标题名称
	@BindView(R.id.create)//创建人员
		Button mCreate;

	private List<CaTestJson> test;
	private List<PersonnelJson> personnel;
	private Long rid, id;
	private int uploadStatus1, uploadStatus2, work_type;
	private String value, status = "";
	private double oldScore;

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_description1;
	}

	/**
	 * 初始化数据
	 */
	@Override
	protected void initData() {
		personnel = PersonnelDao.query(eid, cid, item);// 人员表
		List<CaConfigJson> config = CaConfigDao.query(item);// 配置表
		test = CaTestDao.query(cid, item);// 打分表
		work_type = config.get(0).getCOMMON_TYPE();

		if (test.size() > 0) {
			rid = test.get(0).getID();
			status = test.get(0).getSTATUS();
			mTvScore211.setText(test.get(0).getSCORE());// 得分
			if (test.get(0).getUPLOADSTATUS() >= 1) {
				uploadStatus2 = 1; //上传状态 0未上传，1已上传，修改需再次上传。2已上传
			} else {
				uploadStatus2 = 0;
			}

			if (test.get(0).getCHOOSE() != null) {
				if (!test.get(0).getCHOOSE().equals("")) {
					String[] choose = test.get(0).getCHOOSE().split(",", -1);
					if (choose[0].equals("1")) {// 满足、不满足
						mRb1Yes211.setChecked(true);
					} else {
						mRb1No211.setChecked(true);
					}

					if (choose[1].equals("1")) {// 在岗、不在岗
						mRb2Yes211.setChecked(true);
					} else {
						mRb2No211.setChecked(true);
					}

					if (choose[2].equals("1")) {// 有社保、无社保
						mRb3Yes211.setChecked(true);
					} else {
						mRb3No211.setChecked(true);
					}

					if (choose[3].equals("1")) {// 有职称证书、无职称证书
						mRb4Yes211.setChecked(true);
					} else {
						mRb4No211.setChecked(true);
					}

					if (choose[4].equals("1")) {// 有学历证书、无学历证书
						mRb5Yes211.setChecked(true);
					} else {
						mRb5No211.setChecked(true);
					}

					if (choose[5].equals("1")) {// 考核满意、考核不满意
						mRb6Yes211.setChecked(true);
					} else {
						mRb6No211.setChecked(true);
					}

					if (choose[6].equals("1")) {// 有劳动合同、无劳动合同
						mRb7Yes211.setChecked(true);
					} else {
						mRb7No211.setChecked(true);
					}

					if (choose[7].equals("1")) {// 有任命书、无任命书
						mRb8Yes211.setChecked(true);
					} else {
						mRb8No211.setChecked(true);
					}
				}
			}

		} else {
			uploadStatus1 = 0;
		}
		mTextView4.setText(item + config.get(0).getTITLE());//设置考核标题
		value = config.get(0).getSCORE();// 拿到分值
		mTvValue211.setText(config.get(0).getSCORE());// 分值
		mTvRule211.setText(config.get(0).getMEMO());// 扣分说明

		if (personnel.size() > 0) {
			id = personnel.get(0).getID();
			mEtName211.setText(personnel.get(0).getPERNAME());// 姓名
			mEtCard211.setText(personnel.get(0).getIDNUMBER());// 身份证
			mEtPhone211.setText(personnel.get(0).getPHONE());// 手机号码
			mEtTime211.setText(personnel.get(0).getHIREDATE());// 入职时间
			mEtRemark211.setText(personnel.get(0).getREMARK());// 备注
		}
	}

	/**
	 * 事件监听
	 */
	@Override
	protected void listener() {
		eTListener(mEtName211);// 姓名
		eTListener(mEtCard211);// 身份证
		eTListener(mEtPhone211);// 手机号码
		eTListener(mEtTime211);// 入职时间
		eTListener(mEtRemark211);// 备注

		// 满足 不满足
		mRb1Yes211.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 1 满足
					mTvScore211.setText(ScoreUtil.scoreAll(value));
					mRb2Yes211.setChecked(true);
					mRb3Yes211.setChecked(true);
					mRb4Yes211.setChecked(true);
					mRb5Yes211.setChecked(true);
					mRb6Yes211.setChecked(true);
					mRb7Yes211.setChecked(true);
					mRb8Yes211.setChecked(true);
				}
				saveData(status);
			}
		});

		// 在岗 不在岗
		mRb2Yes211.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 1 在岗
					if (mRb3Yes211.isChecked()) {// 有社保
						if (mRb4Yes211.isChecked()) {// 有职称证书
							if (mRb5Yes211.isChecked()) {// 有学历证书
								if (mRb6Yes211.isChecked()) {// 考核满意
									if (mRb7Yes211.isChecked()) {// 有劳动合同
										if (mRb8Yes211.isChecked()) {// 有任命聘用书
											mTvScore211.setText(ScoreUtil.scoreAll(value));
											mRb1Yes211.setChecked(true);
										} else {// 无任命聘用书
											mRb1Yes211.setChecked(true);
											mRb8Yes211.setChecked(false);
											mRb8No211.setChecked(true);
											mTvScore211.setText(ScoreUtil.scoreHalf(value));
										}
									} else {// 无劳动合同
										mTvScore211.setText(ScoreUtil.SCORE_NOT);
										mRb1No211.setChecked(true);
									}
								} else {//考核不满意
									mTvScore211.setText(ScoreUtil.SCORE_NOT);
									mRb1No211.setChecked(true);
								}
							} else {// 无学历证书
								mTvScore211.setText(ScoreUtil.SCORE_NOT);
								mRb1No211.setChecked(true);
							}
						} else {// 无职称证书
							mTvScore211.setText(ScoreUtil.SCORE_NOT);
							mRb1No211.setChecked(true);
						}
					} else {// 无社保
						mTvScore211.setText(ScoreUtil.SCORE_NOT);
						mRb1No211.setChecked(true);
					}
				} else {// 2  不在岗
					if (mRb3Yes211.isChecked()) {// 有社保
						if (mRb4Yes211.isChecked()) {// 有职称证书
							if (mRb5Yes211.isChecked()) {// 有学历证书
								if (mRb6Yes211.isChecked()) {// 考核满意
									if (mRb7Yes211.isChecked()) {// 有劳动合同
										if (mRb8Yes211.isChecked()) {// 有任命聘用书
											mRb1Yes211.setChecked(true);
											mRb2Yes211.setChecked(false);
											mTvScore211.setText(ScoreUtil.scoreHalf(value));
										} else {// 无任命聘用书
											mTvScore211.setText(ScoreUtil.SCORE_NOT);
											mRb1No211.setChecked(true);
										}
									} else { //无劳动合同
										mTvScore211.setText(ScoreUtil.SCORE_NOT);
										mRb1No211.setChecked(true);
									}
								} else {//考核不满意
									mTvScore211.setText(ScoreUtil.SCORE_NOT);
									mRb1No211.setChecked(true);
								}
							} else {// 无学历证书
								mTvScore211.setText(ScoreUtil.SCORE_NOT);
								mRb1No211.setChecked(true);
							}
						} else {// 无职称证书
							mTvScore211.setText(ScoreUtil.SCORE_NOT);
							mRb1No211.setChecked(true);
						}
					} else {// 无社保
						mTvScore211.setText(ScoreUtil.SCORE_NOT);
						mRb1No211.setChecked(true);
					}
				}
				saveData(status);
			}
		});

		// 有社保 无社保
		mRb3Yes211.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 1 有社保
					if (mRb4Yes211.isChecked()) {// 有职称证书
						if (mRb5Yes211.isChecked()) {// 有学历证书
							if (mRb6Yes211.isChecked()) {// 考核满意
								if (mRb7Yes211.isChecked()) {// 有劳动合同
									if (mRb8Yes211.isChecked()) {// 有任命聘用书
										if (mRb2Yes211.isChecked()) {//在岗
											mRb1Yes211.setChecked(true);
											mTvScore211.setText(ScoreUtil.scoreAll(value));
										} else {//不在岗
											mRb1Yes211.setChecked(true);
											mRb2Yes211.setChecked(false);
											mRb2No211.setChecked(true);
											mTvScore211.setText(ScoreUtil.scoreHalf(value));
										}
									} else {// 无任命聘用书
										if (mRb2Yes211.isChecked()) {//在岗
											mRb1Yes211.setChecked(true);
											mRb8Yes211.setChecked(false);
											mRb8No211.setChecked(true);
											mTvScore211.setText(ScoreUtil.scoreHalf(value));
										} else {//不在岗
											mTvScore211.setText(ScoreUtil.SCORE_NOT);
											mRb1No211.setChecked(true);
										}
									}
								} else {// 无劳动合同
									mTvScore211.setText(ScoreUtil.SCORE_NOT);
									mRb1No211.setChecked(true);
								}
							} else {//考核不满意
								mTvScore211.setText(ScoreUtil.SCORE_NOT);
								mRb1No211.setChecked(true);
							}
						} else {// 无学历证书
							mTvScore211.setText(ScoreUtil.SCORE_NOT);
							mRb1No211.setChecked(true);
						}
					} else {// 无职称证书
						mTvScore211.setText(ScoreUtil.SCORE_NOT);
						mRb1No211.setChecked(true);
					}
				} else {// 2 无社保
					mTvScore211.setText(ScoreUtil.SCORE_NOT);
					mRb1No211.setChecked(true);
				}
				saveData(status);
			}
		});

		// 有职称证书 无职称证书
		mRb4Yes211.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 1 有职称证书
					if (mRb3Yes211.isChecked()) {// 有社保
						if (mRb5Yes211.isChecked()) {// 有学历证书
							if (mRb6Yes211.isChecked()) {// 考核满意
								if (mRb7Yes211.isChecked()) {// 有劳动合同
									if (mRb8Yes211.isChecked()) {// 有任命聘用书
										if (mRb2Yes211.isChecked()) {// 在岗
											mTvScore211.setText(ScoreUtil.scoreAll(value));
											mRb1Yes211.setChecked(true);
										} else {// 不在岗
											mRb1Yes211.setChecked(true);
											mRb2Yes211.setChecked(false);
											mRb2No211.setChecked(true);
											mTvScore211.setText(ScoreUtil.scoreHalf(value));
										}
									} else {// 无任命聘用书
										if (mRb2Yes211.isChecked()) {// 在岗
											mRb1Yes211.setChecked(true);
											mRb8Yes211.setChecked(false);
											mRb8No211.setChecked(true);
											mTvScore211.setText(ScoreUtil.scoreHalf(value));
										} else {// 不在岗
											mTvScore211.setText(ScoreUtil.SCORE_NOT);
											mRb1No211.setChecked(true);
										}
									}
								} else {// 无劳动合同
									mTvScore211.setText(ScoreUtil.SCORE_NOT);
									mRb1No211.setChecked(true);
								}
							} else {// 考核不满意
								mTvScore211.setText(ScoreUtil.SCORE_NOT);
								mRb1No211.setChecked(true);
							}
						} else {// 无学历证书
							mTvScore211.setText(ScoreUtil.SCORE_NOT);
							mRb1No211.setChecked(true);
						}
					} else {// 无社保
						mTvScore211.setText(ScoreUtil.SCORE_NOT);
						mRb1No211.setChecked(true);
					}
				} else {// 2 无职称证书
					mTvScore211.setText(ScoreUtil.SCORE_NOT);
					mRb1No211.setChecked(true);
				}
				saveData(status);
			}
		});

		// 有学历证书 无学历证书
		mRb5Yes211.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 1 有学历证书
					if (mRb3Yes211.isChecked()) {// 有社保
						if (mRb4Yes211.isChecked()) {// 有职称证书
							if (mRb6Yes211.isChecked()) {// 考核满意
								if (mRb7Yes211.isChecked()) {// 有劳动合同
									if (mRb8Yes211.isChecked()) {// 有任命聘用书
										if (mRb2Yes211.isChecked()) {//在岗
											mTvScore211.setText(ScoreUtil.scoreAll(value));
											mRb1Yes211.setChecked(true);
										} else {//不在岗
											mRb1Yes211.setChecked(true);
											mRb2Yes211.setChecked(false);
											mRb2No211.setChecked(true);
											mTvScore211.setText(ScoreUtil.scoreHalf(value));
										}
									} else {// 无任命聘用书
										if (mRb2Yes211.isChecked()) {//在岗
											mRb1Yes211.setChecked(true);
											mRb8Yes211.setChecked(false);
											mRb8No211.setChecked(true);
											mTvScore211.setText(ScoreUtil.scoreHalf(value));
										} else {//不在岗
											mTvScore211.setText(ScoreUtil.SCORE_NOT);
											mRb1No211.setChecked(true);
										}
									}
								} else {// 无劳动合同
									mTvScore211.setText(ScoreUtil.SCORE_NOT);
									mRb1No211.setChecked(true);
								}
							} else {// 考核不满意
								mTvScore211.setText(ScoreUtil.SCORE_NOT);
								mRb1No211.setChecked(true);
							}
						} else {// 无职称证书
							mTvScore211.setText(ScoreUtil.SCORE_NOT);
							mRb1No211.setChecked(true);
						}
					} else {// 无社保
						mTvScore211.setText(ScoreUtil.SCORE_NOT);
						mRb1No211.setChecked(true);
					}
				} else {// 2 无学历证书
					mTvScore211.setText(ScoreUtil.SCORE_NOT);
					mRb1No211.setChecked(true);
				}
				saveData(status);
			}
		});

		// 考核满意 考核不满意
		mRb6Yes211.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 1 考核满意
					if (mRb3Yes211.isChecked()) {// 有社保
						if (mRb4Yes211.isChecked()) {// 有职称证书
							if (mRb5Yes211.isChecked()) {// 有学历证书
								if (mRb7Yes211.isChecked()) {// 有劳动合同
									if (mRb8Yes211.isChecked()) {// 有任命聘用书
										if (mRb2Yes211.isChecked()) {//在岗
											mTvScore211.setText(ScoreUtil.scoreAll(value));
											mRb1Yes211.setChecked(true);
										} else {//不在岗
											mRb1No211.setChecked(true);
											mRb2Yes211.setChecked(false);
											mRb2No211.setChecked(true);
											mTvScore211.setText(ScoreUtil.SCORE_NOT);
										}
									} else {// 无任命聘用书
										if (mRb2Yes211.isChecked()) {//在岗
											mRb1Yes211.setChecked(true);
											mRb8Yes211.setChecked(false);
											mRb8No211.setChecked(true);
											mTvScore211.setText(ScoreUtil.scoreHalf(value));
										} else {//不在岗
											mTvScore211.setText(ScoreUtil.SCORE_NOT);
											mRb1No211.setChecked(true);
										}
									}
								} else {// 无劳动合同
									mTvScore211.setText(ScoreUtil.SCORE_NOT);
									mRb1No211.setChecked(true);
								}
							} else {// 有学历证书
								mTvScore211.setText(ScoreUtil.SCORE_NOT);
								mRb1No211.setChecked(true);
							}
						} else {// 无职称证书
							mTvScore211.setText(ScoreUtil.SCORE_NOT);
							mRb1No211.setChecked(true);
						}
					} else {// 无社保
						mTvScore211.setText(ScoreUtil.SCORE_NOT);
						mRb1No211.setChecked(true);
					}
				} else {// 2 考核不满意
					mTvScore211.setText(ScoreUtil.SCORE_NOT);
					mRb1No211.setChecked(true);
				}
				saveData(status);
			}
		});

		// 有劳动合同、 无劳动合同
		mRb7Yes211.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 1 有劳动合同
					if (mRb3Yes211.isChecked()) {// 有社保
						if (mRb4Yes211.isChecked()) {// 有职称证书
							if (mRb5Yes211.isChecked()) {// 有学历证书
								if (mRb6Yes211.isChecked()) {// 考核通过
									if (mRb8Yes211.isChecked()) {// 有任命聘用书
										if (mRb2Yes211.isChecked()) {//在岗
											mTvScore211.setText(ScoreUtil.scoreAll(value));
											mRb1Yes211.setChecked(true);
										} else {//不在岗
											mRb1Yes211.setChecked(true);
											mRb2Yes211.setChecked(false);
											mRb2No211.setChecked(true);
											mTvScore211.setText(ScoreUtil.scoreHalf(value));
										}
									} else {// 无任命聘用书
										if (mRb2Yes211.isChecked()) {//在岗
											mRb1Yes211.setChecked(true);
											mRb8Yes211.setChecked(false);
											mRb8No211.setChecked(true);
											mTvScore211.setText(ScoreUtil.scoreHalf(value));
										} else {//不在岗
											mTvScore211.setText(ScoreUtil.SCORE_NOT);
											mRb1No211.setChecked(true);
										}
									}
								} else {//考核不通过
									mTvScore211.setText(ScoreUtil.SCORE_NOT);
									mRb1No211.setChecked(true);
								}
							} else {// 有学历证书
								mTvScore211.setText(ScoreUtil.SCORE_NOT);
								mRb1No211.setChecked(true);
							}
						} else {// 无职称证书
							mTvScore211.setText(ScoreUtil.SCORE_NOT);
							mRb1No211.setChecked(true);
						}
					} else {// 无社保
						mTvScore211.setText(ScoreUtil.SCORE_NOT);
						mRb1No211.setChecked(true);
					}
				} else {// 2 无劳动合同
					mTvScore211.setText(ScoreUtil.SCORE_NOT);
					mRb1No211.setChecked(true);
				}
				saveData(status);
			}
		});

		// 有任命聘用书、 无任命聘用书
		mRb8Yes211.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 1 有任命聘用书
					if (mRb3Yes211.isChecked()) {// 有社保
						if (mRb4Yes211.isChecked()) {// 有职称证书
							if (mRb5Yes211.isChecked()) {// 有学历证书
								if (mRb6Yes211.isChecked()) {// 考核满意
									if (mRb7Yes211.isChecked()) {// 有劳动合同
										if (mRb2Yes211.isChecked()) {//在岗
											mTvScore211.setText(ScoreUtil.scoreAll(value));
											mRb1Yes211.setChecked(true);
										} else {//不在岗
											mRb1Yes211.setChecked(true);
											mRb2Yes211.setChecked(false);
											mRb2No211.setChecked(true);
											mTvScore211.setText(ScoreUtil.scoreHalf(value));
										}
									} else {// 无劳动合同
										mTvScore211.setText(ScoreUtil.SCORE_NOT);
										mRb1No211.setChecked(true);
									}
								} else {//考核不满意
									mTvScore211.setText(ScoreUtil.SCORE_NOT);
									mRb1No211.setChecked(true);
								}
							} else {// 有学历证书
								mTvScore211.setText(ScoreUtil.SCORE_NOT);
								mRb1No211.setChecked(true);
							}
						} else {// 无职称证书
							mTvScore211.setText(ScoreUtil.SCORE_NOT);
							mRb1No211.setChecked(true);
						}
					} else {// 无社保
						mTvScore211.setText(ScoreUtil.SCORE_NOT);
						mRb1No211.setChecked(true);
					}
				} else {// 2 无任命聘用书
					if (mRb3Yes211.isChecked()) {// 有社保
						if (mRb4Yes211.isChecked()) {// 有职称证书
							if (mRb5Yes211.isChecked()) {// 有学历证书
								if (mRb6Yes211.isChecked()) {// 考核满意
									if (mRb7Yes211.isChecked()) {// 有劳动合同
										if (mRb2Yes211.isChecked()) {// 在岗
											mRb1Yes211.setChecked(true);
											mTvScore211.setText(ScoreUtil.scoreHalf(value));
										} else {// 不在岗
											mTvScore211.setText(ScoreUtil.SCORE_NOT);
											mRb1No211.setChecked(true);
										}
									} else {// 无劳动合同
										mTvScore211.setText(ScoreUtil.SCORE_NOT);
										mRb1No211.setChecked(true);
									}
								} else {//考核不满意
									mTvScore211.setText(ScoreUtil.SCORE_NOT);
									mRb1No211.setChecked(true);
								}
							} else {// 有学历证书
								mTvScore211.setText(ScoreUtil.SCORE_NOT);
								mRb1No211.setChecked(true);
							}
						} else {// 无职称证书
							mTvScore211.setText(ScoreUtil.SCORE_NOT);
							mRb1No211.setChecked(true);
						}
					} else {// 无社保
						mTvScore211.setText(ScoreUtil.SCORE_NOT);
						mRb1No211.setChecked(true);
					}
				}
				saveData(status);
			}
		});
	}

	/**
	 * 拍照
	 */
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
				//String str = s.toString();
				//saveData(status);

				//ToastUtils.show("suecssess："+str);
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
		int condition1;
		if (mRb1Yes211.isChecked()) {// 1 满足、0 不满足
			condition1 = 1;
		} else {
			condition1 = 0;
		}

		int condition2;
		if (mRb2Yes211.isChecked()) {// 1 在岗、0 不在岗
			condition2 = 1;
		} else {
			condition2 = 0;
		}

		int condition3;
		if (mRb3Yes211.isChecked()) {// 1 有社保、0 无社保
			condition3 = 1;
		} else {
			condition3 = 0;
		}

		int condition4;
		if (mRb4Yes211.isChecked()) {// 1 有职称证书、0 无职称证书
			condition4 = 1;
		} else {
			condition4 = 0;
		}

		int condition5;
		if (mRb5Yes211.isChecked()) {// 1 有学历证书、0 无学历证书
			condition5 = 1;
		} else {
			condition5 = 0;
		}

		int condition6;
		if (mRb6Yes211.isChecked()) {// 1 考核满意、0 考核不满意
			condition6 = 1;
		} else {
			condition6 = 0;
		}

		int condition7;
		if (mRb7Yes211.isChecked()) {// 1 有劳动合同、0 无劳动合同
			condition7 = 1;
		} else {
			condition7 = 0;
		}

		int condition8;
		if (mRb8Yes211.isChecked()) {// 1 有任命书、0 有任命书
			condition8 = 1;
		} else {
			condition8 = 0;
		}

		String choose = condition1 + "," + condition2 + "," + condition3 + "," + condition4 + ","
			+ condition5 + "," + condition6 + "," + condition7 + "," + condition8;
		String score = mTvScore211.getText().toString();// 得分
		String name = mEtName211.getText().toString();// 姓名
		String card = mEtCard211.getText().toString();// 身份证
		String phone = mEtPhone211.getText().toString();// 手机号码
		String time = mEtTime211.getText().toString();// 入职时间
		String remark = mEtRemark211.getText().toString();// 备注
		if (name.equals("") || card.equals("")){
			ToastUtils.show("姓名和身份证不能为空");
			return;
		}
		if (status.equals("1") || status.equals("2")){
			if (score.equals("")){
				ToastUtils.show("请进行打分");
				return;
			}
		}

		PersonnelJson personnelJson = null;
		CaTestJson caTestJson = null;

		if (id != null) {
			personnelJson = new PersonnelJson(id, Integer.valueOf(eid), Integer.valueOf(cid),
				item, name, card, choose, score, remark, phone, time, uploadStatus1);
			PersonnelDao.update(personnelJson);
		}else {
			List<PersonnelJson> list = PersonnelDao.queryJudgePerson(Integer.parseInt(cid), item, card);
			if (list.size() > 0){
				ToastUtils.show("已存在此人员");
				return;
			}else {
				personnelJson = new PersonnelJson(Integer.valueOf(eid), Integer.valueOf(cid),
					item, name, card, choose, score, remark, phone, time, uploadStatus1);
				PersonnelDao.insertOrReplace(personnelJson);
				personnel = PersonnelDao.query(eid, cid, item);
				if (personnel != null && personnel.size() > 0){
					id = personnel.get(0).getID();
				}
			}
		}

		if (rid != null) {
			caTestJson = new CaTestJson(rid, cid, item, score, choose, remark, status, uploadStatus2);
			CaTestDao.insertOrReplace(caTestJson);
		} else {
			caTestJson = new CaTestJson(cid, item, score, choose, remark, status, uploadStatus2);
			CaTestDao.insertOrReplace(caTestJson);
			test = CaTestDao.query(cid, item);
			if (test != null && test.size() > 0){
				rid = test.get(0).getID();
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
