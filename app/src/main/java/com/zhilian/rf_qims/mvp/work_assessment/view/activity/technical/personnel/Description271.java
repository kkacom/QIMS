package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.zhilian.rf_qims.entity.WorkAbilityDao;
import com.zhilian.rf_qims.entity.WorkAbilityJson;
import com.zhilian.rf_qims.util.Compute;
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
 * 质检工具（2.8.1~2.8.18）
 */
public class Description271 extends BaseModuleActivity {
	@BindView(R.id.textView4)// 标题
		TextView mTextView4;
	@BindView(R.id.cb_condition2_7_1)// 符合
		CheckBox mCbCondition271;
	@BindView(R.id.cb1_condition2_7_1)// 缺规程
		CheckBox mCb1Condition271;
	@BindView(R.id.cb2_condition2_7_1)// 缺设备
		CheckBox mCb2Condition271;
	@BindView(R.id.cb3_condition2_7_1)// 缺证书
		CheckBox mCb3Condition271;
	@BindView(R.id.cb4_condition2_7_1)// 缺凭证
		CheckBox mCb4Condition271;

	@BindView(R.id.tv_value2_7_1)// 分值
		TextView mTvValue271;
	@BindView(R.id.tv_score2_7_1)// 得分
		TextView mTvScore271;
	@BindView(R.id.et_remark2_7_1)// 备注
		EditText mEtRemark271;
	@BindView(R.id.tv_rule2_7_1)// 扣分规则
		TextView mTvRule271;

	@BindView(R.id.cb5_condition2_7_1)// 万能超压室
		CheckBox mCb5Condition271;
	@BindView(R.id.cb6_condition2_7_1)// 典型超压室扣0.4分
		CheckBox mCb6Condition271;
	@BindView(R.id.cb7_condition2_7_1)// 无超压室不得分
		CheckBox mCb7Condition271;
	@BindView(R.id.layout)// 2.8.18隐藏布局，只用于2.8.18才显示
		LinearLayout mLayout;


	private int uploadStatus;
	private double dPoint, oldScore;
	private Long rid;
	private List<CaTestJson> list;
	private String point, status = "";

	@Override
	protected int getLayoutRes() {
		return R.layout.dialog_description12;
	}

	@Override
	protected void initData() {
		List<CaConfigJson> config = CaConfigDao.query(item);// 配置表
		list = CaTestDao.query(cid, item);// 打分表

		mTextView4.setText(item + title);//设置考核标题
		point = config.get(0).getSCORE();// 拿到分值String
		dPoint = Double.valueOf(point);// 分值int便于计算
		mTvValue271.setText(config.get(0).getSCORE());// 分值
		mTvRule271.setText(config.get(0).getMEMO());// 扣分说明

		if(item.equals("2.8.18")){ //布局不同，item2.8.18才放出来
			mLayout.setVisibility(View.VISIBLE);
		}
		if (list.size() > 0) {
			rid = list.get(0).getID();
			status = list.get(0).getSTATUS();
			if (list.get(0).getUPLOADSTATUS() >= 1) {
				uploadStatus = 1; //上传状态 0未上传，1已上传，修改需再次上传。2已上传
			} else {
				uploadStatus = 0;
			}

			mTvScore271.setText(list.get(0).getSCORE());//得分
			mEtRemark271.setText(list.get(0).getREMARK());// 备注
			String[] choose = list.get(0).getCHOOSE().split(",",-1);
			if (choose[0].equals("1")) {// 符合
				mCbCondition271.setChecked(true);
			}

			if (choose[1].equals("1")) {// 缺规程
				mCb1Condition271.setChecked(true);
			}

			if (choose[2].equals("1")) {// 缺设备
				mCb2Condition271.setChecked(true);
			}

			if (choose[3].equals("1")) {// 缺证书
				mCb3Condition271.setChecked(true);
			}

			if (choose[4].equals("1")) {// 缺凭证
				mCb4Condition271.setChecked(true);
			}

			if (item.equals("2.8.18")){
				if (choose[5].equals("1")) {// 万能超压室
					mCb5Condition271.setChecked(true);
				}
				if (choose[6].equals("1")) {// 典型超压室扣0.4分
					mCb6Condition271.setChecked(true);
				}
				if (choose[7].equals("1")) {// 无超压室不得分
					mCb7Condition271.setChecked(true);
				}
			}
		}else {
			uploadStatus = 0;
		}
	}

	@Override
	protected void saveStatus(String status) {
		if (!item.equals("2.8.18")){
			saveData(status);
		}else {
			if (status.equals("1")){
				if (mCb5Condition271.isChecked()){
					saveData(status);
				}else if (mCb6Condition271.isChecked()){
					saveData(status);
				}else if (mCb7Condition271.isChecked()){
					saveData(status);
				}else {
					ToastUtils.show("请检查超压室");
				}
			}
		}
	}

	/**
	 * 事件监听
	 */
	@Override
	protected void listener() {
		photo.setVisibility(View.GONE);
		video.setVisibility(View.GONE);

		// 符合 不符合
		mCbCondition271.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 符合
					//mCb1Condition271.setChecked(false);
					mCb2Condition271.setChecked(false);
					mCb3Condition271.setChecked(false);
					mCb4Condition271.setChecked(false);
					if (!item.equals("2.8.18")){
						if (mCb1Condition271.isChecked()){
							mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.1)));
						}else {
							mTvScore271.setText(ScoreUtil.scoreAll(point));
						}
					}else {
						if (mCb5Condition271.isChecked()){// 万能超压室
							if (mCb1Condition271.isChecked()){
								mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.1)));
							}else {
								mTvScore271.setText(ScoreUtil.scoreAll(point));
							}
						}
						if (mCb6Condition271.isChecked()){// 典型超压室扣0.4分
							if (mCb1Condition271.isChecked()){
								mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.5)));
							}else {
								mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.4)));
							}
						}
						if (mCb7Condition271.isChecked()){// 无超压室不得分
							mTvScore271.setText(ScoreUtil.SCORE_NOT);
						}
					}
				} else {// 不符合
					if (!mCb2Condition271.isChecked() && !mCb3Condition271.isChecked() && !mCb4Condition271.isChecked()){
						mTvScore271.setText("");
					}else {
						mTvScore271.setText(ScoreUtil.SCORE_NOT);
					}
				}
			}
		});

		// 缺规程
		mCb1Condition271.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 1 缺规程
					if (mCb2Condition271.isChecked()) {// 缺设备
						mTvScore271.setText(ScoreUtil.SCORE_NOT);
					} else {
						if (mCb3Condition271.isChecked()) {// 缺证书
							mTvScore271.setText(ScoreUtil.SCORE_NOT);
						} else {
							if (mCb4Condition271.isChecked()) {// 缺凭证
								mTvScore271.setText(ScoreUtil.SCORE_NOT);
							} else {
								if (item.equals("2.8.18")){
									if (mCbCondition271.isChecked()){
										if (mCb5Condition271.isChecked()){// 万能超压室
											mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.1)));
										}
										if (mCb6Condition271.isChecked()){// 典型超压室扣0.4
											mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.5)));
										}
										if (mCb7Condition271.isChecked()){// 无超压室
											mTvScore271.setText(ScoreUtil.SCORE_NOT);
										}
									}
								}else {
									if (mCbCondition271.isChecked()){
										mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.1)));
									}else {
										mTvScore271.setText(ScoreUtil.SCORE_NOT);
									}
								}
							}
						}
					}
				} else {// 2 不选中缺规格
					if (mCb2Condition271.isChecked()) {// 缺设备
						mTvScore271.setText(ScoreUtil.SCORE_NOT);
					} else {
						if (mCb3Condition271.isChecked()) {// 缺证书
							mTvScore271.setText(ScoreUtil.SCORE_NOT);
						} else {
							if (mCb4Condition271.isChecked()) {// 缺凭证
								mTvScore271.setText(ScoreUtil.SCORE_NOT);
							} else {
								mCbCondition271.setChecked(true);
								if (item.equals("2.8.18")){
									if (mCb5Condition271.isChecked()){// 万能超压室
										mTvScore271.setText(ScoreUtil.scoreAll(point));
									}
									if (mCb6Condition271.isChecked()){// 典型超压室扣0.4
										mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.4)));
									}
									if (mCb7Condition271.isChecked()){// 无超压室
										mTvScore271.setText(ScoreUtil.SCORE_NOT);
									}
								}else {
									mTvScore271.setText(ScoreUtil.scoreAll(point));
								}
							}
						}
					}
				}
			}
		});

		// 缺设备
		mCb2Condition271.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 1 缺设备
					mTvScore271.setText(ScoreUtil.SCORE_NOT);
					mCbCondition271.setChecked(false);
				} else {// 2
					if (mCb1Condition271.isChecked()) {// 缺规程
						mTvScore271.setText("");
					} else {
						if (mCb3Condition271.isChecked()) {// 缺证书
							mTvScore271.setText(ScoreUtil.SCORE_NOT);
						} else {
							if (mCb4Condition271.isChecked()) {// 缺凭证
								mTvScore271.setText(ScoreUtil.SCORE_NOT);
							} else {
								mCbCondition271.setChecked(true);
								if (item.equals("2.8.18")){
									if (mCb5Condition271.isChecked()){// 万能超压室
										mTvScore271.setText(ScoreUtil.scoreAll(point));
									}
									if (mCb6Condition271.isChecked()){// 典型超压室扣0.4
										mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.4)));
									}
									if (mCb7Condition271.isChecked()){// 无超压室
										mTvScore271.setText(ScoreUtil.SCORE_NOT);
									}
								}else {
									mTvScore271.setText(ScoreUtil.scoreAll(point));
								}
							}
						}
					}
				}
			}
		});

		// 缺证书
		mCb3Condition271.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 1缺证书
					mTvScore271.setText(ScoreUtil.SCORE_NOT);
					mCbCondition271.setChecked(false);
				} else {// 2
					if (mCb1Condition271.isChecked()) {// 缺规程
						mTvScore271.setText("");
					} else {
						if (mCb2Condition271.isChecked()) {// 缺设备
							mTvScore271.setText(ScoreUtil.SCORE_NOT);
						} else {
							if (mCb4Condition271.isChecked()) {// 缺凭证
								mTvScore271.setText(ScoreUtil.SCORE_NOT);
							} else {
								mCbCondition271.setChecked(true);
								if (item.equals("2.8.18")){
									if (mCb5Condition271.isChecked()){// 万能超压室
										mTvScore271.setText(ScoreUtil.scoreAll(point));
									}
									if (mCb6Condition271.isChecked()){// 典型超压室扣0.4
										mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.4)));
									}
									if (mCb7Condition271.isChecked()){// 无超压室
										mTvScore271.setText(ScoreUtil.SCORE_NOT);
									}
								}else {
									mTvScore271.setText(ScoreUtil.scoreAll(point));
								}
							}
						}
					}
				}
			}
		});

		// 缺凭证
		mCb4Condition271.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {// 1 缺凭证
					mTvScore271.setText(ScoreUtil.SCORE_NOT);
					mCbCondition271.setChecked(false);
				} else {// 2
					if (mCb1Condition271.isChecked()) {// 缺规程
						mTvScore271.setText("");
					} else {
						if (mCb2Condition271.isChecked()) {// 缺设备
							mTvScore271.setText(ScoreUtil.SCORE_NOT);
						} else {
							if (mCb3Condition271.isChecked()) {// 缺证书
								mTvScore271.setText(ScoreUtil.SCORE_NOT);
							} else {
								mCbCondition271.setChecked(true);
								if (item.equals("2.8.18")){
									if (mCb5Condition271.isChecked()){// 万能超压室
										mTvScore271.setText(ScoreUtil.scoreAll(point));
									}
									if (mCb6Condition271.isChecked()){// 典型超压室扣0.4
										mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.4)));
									}
									if (mCb7Condition271.isChecked()){// 无超压室
										mTvScore271.setText(ScoreUtil.SCORE_NOT);
									}
								}else {
									mTvScore271.setText(ScoreUtil.scoreAll(point));
								}
							}
						}
					}
				}
			}
		});

		if (item.equals("2.8.18")){
			// 万能超压室
			mCb5Condition271.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked){
						mCb6Condition271.setChecked(false);
						mCb7Condition271.setChecked(false);
						if (mCbCondition271.isChecked()){// 符合
							if (mCb1Condition271.isChecked()){
								mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.1)));
							}else {
								mTvScore271.setText(ScoreUtil.scoreAll(point));
							}
						}else {
							if (mCb2Condition271.isChecked()){// 缺设备
								mTvScore271.setText(ScoreUtil.SCORE_NOT);
							}else {
								if (mCb3Condition271.isChecked()){// 缺证书
									mTvScore271.setText(ScoreUtil.SCORE_NOT);
								}else {
									if (mCb4Condition271.isChecked()){// 缺凭证
										mTvScore271.setText(ScoreUtil.SCORE_NOT);
									}else {
										if (mCb1Condition271.isChecked()){
											if (mCbCondition271.isChecked()){
												mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.1)));
											}else {
												mTvScore271.setText("");
											}
										}
									}
								}
							}
						}
					}else {
						if (!mCb6Condition271.isChecked() && !mCb7Condition271.isChecked()){
							mTvScore271.setText("");
						}
					}
				}
			});

			// 典型超压室扣0.4分
			mCb6Condition271.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked){
						mCb5Condition271.setChecked(false);
						mCb7Condition271.setChecked(false);
						if (mCbCondition271.isChecked()){// 符合
							if (mCb1Condition271.isChecked()){// 缺规程
								mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.5)));
							}else{
								mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.4)));
							}
						}else {
							if (mCb2Condition271.isChecked()){// 缺设备
								mTvScore271.setText(ScoreUtil.SCORE_NOT);
							}else {
								if (mCb3Condition271.isChecked()){// 缺证书
									mTvScore271.setText(ScoreUtil.SCORE_NOT);
								}else {
									if (mCb4Condition271.isChecked()){// 缺凭证
										mTvScore271.setText(ScoreUtil.SCORE_NOT);
									}else {
										if (mCbCondition271.isChecked()){
											if (mCb1Condition271.isChecked()){
												mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.5)));
											}
										}
									}
								}
							}
						}
					}else {
						if (!mCb5Condition271.isChecked() && !mCb7Condition271.isChecked()){
							mTvScore271.setText("");
						}
					}
				}
			});

			// 无超压室不得分
			mCb7Condition271.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked){
						mCb5Condition271.setChecked(false);
						mCb6Condition271.setChecked(false);
						if (mCbCondition271.isChecked()){// 符合
							//mTvScore271.setText(ScoreUtil.scoreAll(point));
							mTvScore271.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb2Condition271.isChecked()){// 缺设备
								mTvScore271.setText(ScoreUtil.SCORE_NOT);
							}else {
								if (mCb3Condition271.isChecked()){// 缺证书
									mTvScore271.setText(ScoreUtil.SCORE_NOT);
								}else {
									if (mCb4Condition271.isChecked()){// 缺凭证
										mTvScore271.setText(ScoreUtil.SCORE_NOT);
									}else {
										if (mCbCondition271.isChecked()){
											if (mCb1Condition271.isChecked()){
												mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.1)));
											}
										}
									}
								}
							}
						}
					}else {
						if (!mCb6Condition271.isChecked() && !mCb7Condition271.isChecked()){
							mTvScore271.setText("");
						}
						/*if (mCbCondition271.isChecked()) {// 符合
							if (mCb5Condition271.isChecked()){
								if (mCb1Condition271.isChecked()){
									mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.1)));
								}else {
									mTvScore271.setText(ScoreUtil.scoreAll(point));
								}
							}else if (mCb6Condition271.isChecked()){
								if (mCb1Condition271.isChecked()){
									mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.5)));
								}else {
									mTvScore271.setText(ScoreUtil.scoreAll(String.valueOf(dPoint - 0.4)));
								}
							}else {
								mTvScore271.setText("");
							}
						}*/
					}
				}
			});
		}
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
	 * @param status 状态
	 */
	public void saveData(String status){
		/*☆先计算一遍旧item得分，因为很多item都有可能创建多于需要检测的人数，需要把所有item的分数排序大小再进行多余筛选会非常复杂。
		故反向思考，计算出当前旧item的分数用总item减去旧item分数，最后到计算总分再加上新分数就可以了(Compute.compute(cid, item, oldScore);)*/
		oldScore = Compute.compute(cid, item, oldScore);
		String check1 = "";
		if (mCbCondition271.isChecked()) { // 符合
			check1 = "1";
		}else {
			check1 = "0";
		}

		String check2 = "";
		if (mCb1Condition271.isChecked()){// 缺规程
			check2 = "1";
		}else {
			check2 = "0";
		}

		String check3 = "";
		if (mCb2Condition271.isChecked()){// 缺设备
			check3 = "1";
		}else {
			check3 = "0";
		}

		String check4 = "";
		if (mCb3Condition271.isChecked()){// 缺证书
			check4 = "1";
		}else {
			check4 = "0";
		}

		String check5 = "";
		if (mCb4Condition271.isChecked()){// 缺凭证
			check5 = "1";
		}else {
			check5 = "0";
		}

		String choose = "";
		if (!item.equals("2.8.18")){
			choose = check1 + "," + check2  + "," + check3 + "," + check4  + "," + check5;
		}else {
			String check6 = "";
			if (mCb5Condition271.isChecked()){// 万能超压室
				check6 = "1";
			}else {
				check6 = "0";
			}

			String check7 = "";
			if (mCb6Condition271.isChecked()){// 典型超压室扣0.4分
				check7 = "1";
			}else {
				check7 = "0";
			}

			String check8 = "";
			if (mCb7Condition271.isChecked()){// 无超压室不得分
				check8 = "1";
			}else {
				check8 = "0";
			}
			choose = check1 + "," + check2  + "," + check3 + "," + check4  + "," + check5 + "," + check6  + "," + check7
				+ "," + check8;
		}

		String point = String.valueOf(mTvScore271.getText());// 得分
		String remark = String.valueOf(mEtRemark271.getText());//备注
		if (point.equals("")){
			ToastUtils.show("请进行打分");
			return;
		}else if (point.equals("0.00")){
			if (!mCb1Condition271.isChecked() && !mCb2Condition271.isChecked() &&
				!mCb3Condition271.isChecked() && !mCb4Condition271.isChecked()){
				if (!item.equals("2.8.18")){
					ToastUtils.show("请进行打分");
					return;
				}
			}
		}

		CaTestJson caTestJson1 = null;

		if(rid != null){
			caTestJson1 = new CaTestJson(rid, cid, item, point, choose, remark, status, uploadStatus);
			CaTestDao.insertOrReplace(caTestJson1);
		}else{
			caTestJson1 = new CaTestJson(cid, item, point, choose, remark, status, uploadStatus);
			CaTestDao.insertOrReplace(caTestJson1);
			list = CaTestDao.query(cid, item);
			if (list != null && list.size() > 0){
				rid = list.get(0).getID();
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
}
