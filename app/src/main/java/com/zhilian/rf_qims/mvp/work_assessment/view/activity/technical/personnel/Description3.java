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
import io.reactivex.annotations.NonNull;

import static com.zhilian.rf_qims.util.CommonUtils.getContext;

/**
 * 生产人员（2.3.4~2.3.5）
 */
public class Description3 extends BaseModuleActivity {
	Long rid;
	Long id;
	@BindView(R.id.textView4)//标题
		TextView mTextView4;
	@BindView(R.id.rb1_yes2_1_11)//检查、不检查
		RadioButton mRb1Yes2111;
	@BindView(R.id.rb1_no2_1_11)
	RadioButton mRb1No2111;
	@BindView(R.id.rb2_yes2_1_11)//满足、不满足
		RadioButton mRb2Yes2111;
	@BindView(R.id.rb2_no2_1_11)
	RadioButton mRb2No2111;
	@BindView(R.id.rb3_yes2_1_11)//在岗、不在岗
		RadioButton mRb3Yes2111;
	@BindView(R.id.rb3_no2_1_11)
	RadioButton mRb3No2111;
	@BindView(R.id.rb4_yes2_1_11)//在册、不在册
		RadioButton mRb4Yes2111;
	@BindView(R.id.rb4_no2_1_11)
	RadioButton mRb4No2111;
	@BindView(R.id.rb5_yes2_1_11)//有社保、无社保
		RadioButton mRb5Yes2111;
	@BindView(R.id.rb5_no2_1_11)
	RadioButton mRb5No2111;
	@BindView(R.id.rb6_yes2_1_11)//有劳动合同、无劳动合同
		RadioButton mRb6Yes2111;
	@BindView(R.id.rb6_no2_1_11)
	RadioButton mRb6No2111;
	@BindView(R.id.cb1_2_1_11)// 技术员兼任
	CheckBox mCb12111;

	@BindView(R.id.p_score2_1_11)// 个人得分
		TextView mPScore2111;
	@BindView(R.id.et_name2_1_11)
	EditText mEtName2111;// 姓名
	@BindView(R.id.et_card2_1_11)
	EditText mEtCard2111;// 身份证
	@BindView(R.id.et_phone2_1_11)
	EditText mEtPhone2111;// 手机号码
	@BindView(R.id.et_time2_1_11)
	EditText mEtTime2111;// 入职时间
	@BindView(R.id.et_remark2_1_11)
	EditText mEtRemark2111;// 备注

	@BindView(R.id.tv_rule2_1_11)
	TextView mTvRule2111;// 扣分说明

	private int uploadStatus, work_type;
	private String point, status;
	private double oldScore;


	@Override
	protected int getLayoutRes() {
		return R.layout.activity_description3;
	}

	@Override
	protected void initData() {
		mTextView4.setText(item + title);//设置考核标题
		PersonnelJson personnelJson = (PersonnelJson) getIntent().getSerializableExtra("personnel");// 人员表
		CaTestJson caTestJson = (CaTestJson) getIntent().getSerializableExtra("test");// 打分表
		mTvRule2111.setText(getIntent().getStringExtra("configExplain"));// 扣分说明
		point = getIntent().getStringExtra("configPoint");//基础分值(已用总分除以分数)

		if (caTestJson != null) {
			rid = caTestJson.getID();
			status = caTestJson.getSTATUS();
			if (caTestJson.getUPLOADSTATUS() >= 1){
				uploadStatus = 1;
			}else {
				uploadStatus = 0;
			}
			if (item.equals("2.3.5")) {
				mCb12111.setVisibility(View.VISIBLE);
				if (caTestJson.getFIELD1().equals("技术员兼任")) {// 技术员兼任
					mCb12111.setChecked(true);
				}
			}
		}else {
			uploadStatus = 0;
		}
		id = personnelJson.getID();
		work_type = personnelJson.getWORK_TYPE();
		mPScore2111.setText(personnelJson.getSCORE());// 个人得分
		mEtName2111.setText(personnelJson.getPERNAME());// 姓名
		mEtCard2111.setText(personnelJson.getIDNUMBER());// 身份证
		mEtPhone2111.setText(personnelJson.getPHONE());// 手机号码
		mEtTime2111.setText(personnelJson.getHIREDATE());// 入职时间
		mEtRemark2111.setText(personnelJson.getREMARK());// 备注

		if (personnelJson.getCHOOSE() != null){
			String[] pChoose = personnelJson.getCHOOSE().split(",",-1);
			if (personnelJson.getSTATUS().equals("检查")) {// 检查、不检查
				mRb1Yes2111.setChecked(true);
			} else {
				mRb1No2111.setChecked(true);
			}
			if (pChoose[0].equals("1")) {// 0 满足、1 不满足
				mRb2Yes2111.setChecked(true);
			} else {
				mRb2No2111.setChecked(true);
			}
			if (pChoose[1].equals("1")) {// 0 在岗、1 不在岗
				mRb3Yes2111.setChecked(true);
			} else {
				mRb3No2111.setChecked(true);
			}
			if (pChoose[2].equals("1")) {// 0 在册、1 不在册
				mRb4Yes2111.setChecked(true);
			} else {
				mRb4No2111.setChecked(true);
			}
			if (pChoose[3].equals("1")) {// 0 有社保、1 无社保
				mRb5Yes2111.setChecked(true);
			} else {
				mRb5No2111.setChecked(true);
			}
			if (pChoose[4].equals("1")) {// 0 有劳动合同、1 无劳动合同
				mRb6Yes2111.setChecked(true);
			} else {
				mRb6No2111.setChecked(true);
			}
		}
	}

	/**
	 * 事件监听
	 */
	@Override
	protected void listener() {

		eTListener(mEtName2111);// 姓名
		eTListener(mEtCard2111);// 身份证
		eTListener(mEtPhone2111);// 手机号码
		eTListener(mEtTime2111);// 入职时间
		eTListener(mEtRemark2111);// 备注

		// 检查 不检查
		mRb1Yes2111.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 1 检查
					if (mRb3Yes2111.isChecked()) {// 在岗
						if (mRb4Yes2111.isChecked()) {// 在册
							if (mRb5Yes2111.isChecked()) {// 有社保
								if (mRb6Yes2111.isChecked()) {// 有书劳动合同
									mPScore2111.setText(ScoreUtil.scoreAll(point));
									mRb2Yes2111.setChecked(true);
								} else {// 无劳动合同
									mPScore2111.setText(ScoreUtil.SCORE_NOT);
									mRb2No2111.setChecked(true);
								}
							} else {// 无社保
								mPScore2111.setText(ScoreUtil.SCORE_NOT);
								mRb2No2111.setChecked(true);
							}
						} else {// 不在册
							mPScore2111.setText(ScoreUtil.SCORE_NOT);
							mRb2No2111.setChecked(true);
						}
					} else {// 不在岗
						if (mRb4Yes2111.isChecked()) {// 在册
							if (mRb5Yes2111.isChecked()) {// 有社保
								if (mRb6Yes2111.isChecked()) {// 有书劳动合同
									mPScore2111.setText(ScoreUtil.scoreHalf(point));
									mRb2Yes2111.setChecked(true);
								} else {// 无劳动合同
									mPScore2111.setText(ScoreUtil.SCORE_NOT);
									mRb2No2111.setChecked(true);
								}
							} else {// 无社保
								mPScore2111.setText(ScoreUtil.SCORE_NOT);
								mRb2No2111.setChecked(true);
							}
						} else {// 不在册
							mPScore2111.setText(ScoreUtil.SCORE_NOT);
							mRb2No2111.setChecked(true);
						}
					}
				} else {// 2 不检查

				}
				saveData(status);
			}
		});

		// 满足 不满足
		mRb2Yes2111.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 满足
					mRb3Yes2111.setChecked(true);
					mRb4Yes2111.setChecked(true);
					mRb5Yes2111.setChecked(true);
					mRb6Yes2111.setChecked(true);
				} else {// 不满足

				}
			}
		});

		// 在岗 不在岗
		mRb3Yes2111.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (mRb1Yes2111.isChecked()) {// 检查
					if (isChecked) {// 在岗
						if (mRb4Yes2111.isChecked()) {// 在册
							if (mRb5Yes2111.isChecked()) {// 有社保
								if (mRb6Yes2111.isChecked()) {// 有劳动合同
									mPScore2111.setText(ScoreUtil.scoreAll(point));
									mRb2Yes2111.setChecked(true);
								} else {// 无劳动合同
									mPScore2111.setText(ScoreUtil.SCORE_NOT);
									mRb2No2111.setChecked(true);
								}
							} else {// 无社保
								mPScore2111.setText(ScoreUtil.SCORE_NOT);
								mRb2No2111.setChecked(true);
							}
						} else {// 不在册
							mPScore2111.setText(ScoreUtil.SCORE_NOT);
							mRb2No2111.setChecked(true);
						}
					} else {// 不在岗
						if (mRb4Yes2111.isChecked()) {// 在册
							if (mRb5Yes2111.isChecked()) {// 有社保
								if (mRb6Yes2111.isChecked()) {// 有劳动合同
									mPScore2111.setText(ScoreUtil.scoreHalf(point));
									mRb2Yes2111.setChecked(true);
								} else {// 无劳动合同
									mPScore2111.setText(ScoreUtil.SCORE_NOT);
									mRb2No2111.setChecked(true);
								}
							} else {// 无社保
								mPScore2111.setText(ScoreUtil.SCORE_NOT);
								mRb2No2111.setChecked(true);
							}
						} else {// 不在册
							mPScore2111.setText(ScoreUtil.SCORE_NOT);
							mRb2No2111.setChecked(true);
						}
					}
					saveData(status);
				}
			}
		});

		// 在册 不在册
		mRb4Yes2111.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (mRb1Yes2111.isChecked()) {// 检查
					if (isChecked) {// 1 在册
						if (mRb5Yes2111.isChecked()) {// 有社保
							if (mRb6Yes2111.isChecked()) {// 有劳动合同
								if (mRb3Yes2111.isChecked()) {// 在岗
									mPScore2111.setText(ScoreUtil.scoreAll(point));
									mRb2Yes2111.setChecked(true);
								} else {// 不在岗
									mPScore2111.setText(ScoreUtil.scoreHalf(point));
									mRb2Yes2111.setChecked(true);
								}
							} else {// 不在岗
								mPScore2111.setText(ScoreUtil.SCORE_NOT);
								mRb2No2111.setChecked(true);
							}
						} else {// 无社保
							mPScore2111.setText(ScoreUtil.SCORE_NOT);
							mRb2No2111.setChecked(true);
						}
					} else {// 2 不在册
						mPScore2111.setText(ScoreUtil.SCORE_NOT);
						mRb2No2111.setChecked(true);
					}
					saveData(status);
				}
			}
		});

		// 有社保 无社保
		mRb5Yes2111.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (mRb1Yes2111.isChecked()) {// 检查
					if (isChecked) {// 1 有社保
						if (mRb4Yes2111.isChecked()) {// 在册
							if (mRb6Yes2111.isChecked()) {// 有劳动合同
								if (mRb3Yes2111.isChecked()) {// 在岗
									mPScore2111.setText(ScoreUtil.scoreAll(point));
									mRb2Yes2111.setChecked(true);
								} else {// 不在岗
									mPScore2111.setText(ScoreUtil.scoreHalf(point));
									mRb2Yes2111.setChecked(true);
								}
							} else {// 无劳动合同
								mPScore2111.setText(ScoreUtil.SCORE_NOT);
								mRb2No2111.setChecked(true);
							}
						} else {// 不在册
							mPScore2111.setText(ScoreUtil.SCORE_NOT);
							mRb2No2111.setChecked(true);
						}
					} else {// 2 无社保
						mPScore2111.setText(ScoreUtil.SCORE_NOT);
						mRb2No2111.setChecked(true);
					}
					saveData(status);
				}
			}
		});

		// 有劳动合同 无劳动合同
		mRb6Yes2111.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (mRb1Yes2111.isChecked()) {// 检查
					if (isChecked) {// 1 有劳动合同
						if (mRb4Yes2111.isChecked()) {// 在册
							if (mRb5Yes2111.isChecked()) {// 有社保
								if (mRb3Yes2111.isChecked()) {// 在岗
									mPScore2111.setText(ScoreUtil.scoreAll(point));
									mRb2Yes2111.setChecked(true);
								} else {// 不在岗
									mPScore2111.setText(ScoreUtil.scoreHalf(point));
									mRb2Yes2111.setChecked(true);
								}
							} else {// 专业不符合
								mPScore2111.setText(ScoreUtil.SCORE_NOT);
								mRb2No2111.setChecked(true);
							}
						} else {// 无社保
							mPScore2111.setText(ScoreUtil.SCORE_NOT);
							mRb2No2111.setChecked(true);
						}
					} else {// 2 无劳动合同
						mPScore2111.setText(ScoreUtil.SCORE_NOT);
						mRb2No2111.setChecked(true);
					}
					saveData(status);
				}
			}
		});
	}

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
				saveData("");
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
	 * 保存数据
	 *
	 * @param status 状态
	 */
	private void saveData(String status) {
		/*☆先计算一遍旧item得分，因为很多item都有可能创建多于需要检测的人数，需要把所有item的分数排序大小再进行多余筛选会非常复杂。
		故反向思考，计算出当前旧item的分数用总item减去旧item分数，最后到计算总分再加上新分数就可以了(Compute.compute(cid, item, oldScore);)*/
		oldScore = Compute.compute(cid, item, oldScore);
		String isCheck;
		if (mRb1Yes2111.isChecked()) {// 检查、不检查
			isCheck = "检查";
		} else {
			isCheck = "不检查";
		}

		int condition1;
		if (mRb2Yes2111.isChecked()) {// 0 满足、1 不满足
			condition1 = 1;
		} else {
			condition1 = 0;
		}

		int condition2;
		if (mRb3Yes2111.isChecked()) {// 0 在岗、1 不在岗
			condition2 = 1;
		} else {
			condition2 = 0;
		}

		int condition3;
		if (mRb4Yes2111.isChecked()) {// 0 在册、1 在册
			condition3 = 1;
		} else {
			condition3 = 0;
		}

		int condition4;
		if (mRb5Yes2111.isChecked()) {// 0 有社保、1 无社保
			condition4 = 1;
		} else {
			condition4 = 0;
		}

		int condition5;
		if (mRb6Yes2111.isChecked()) {// 0 有劳动合同、1 无劳动合同
			condition5 = 1;
		} else {
			condition5 = 0;
		}

		String field1 = "";
		if (item.equals("2.3.5")){// 技术员兼任
			field1 = mCb12111.isChecked() ? "技术员兼任":"";
		}

		String choose = condition1 + "," + condition2 + "," + condition3 + ","
			+ condition4 + "," + condition5;

		String pScore = mPScore2111.getText().toString();// 个人得分
		String name = mEtName2111.getText().toString();// 姓名
		String card = mEtCard2111.getText().toString();// 身份证
		String phone = mEtPhone2111.getText().toString();// 手机号码
		String time = mEtTime2111.getText().toString();// 入职时间
		String remark = mEtRemark2111.getText().toString();// 备注
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

		personnelJson = new PersonnelJson(id, Integer.valueOf(eid), Integer.valueOf(cid),
			item, name, card, choose, pScore, isCheck, remark, phone, time, uploadStatus, work_type);
		if (rid != null) {
			caTestJson = new CaTestJson(rid, cid, item, pScore, choose, remark, status, field1, "", uploadStatus);
			CaTestDao.insertOrReplace(caTestJson);
		} else {
			caTestJson = new CaTestJson(cid, item, pScore, choose, remark, status, field1, uploadStatus);
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
