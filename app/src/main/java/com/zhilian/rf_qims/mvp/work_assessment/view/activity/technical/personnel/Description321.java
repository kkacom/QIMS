package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.zhilian.rf_qims.util.ScoreUtil;
import com.zhilian.rf_qims.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhilian.rf_qims.util.CommonUtils.getContext;

/**
 * 产品质量（3.2.1）
 */
public class Description321 extends BaseModuleActivity {
	@BindView(R.id.textView4)// 标题
		TextView mTextView4;
	@BindView(R.id.tv_value3_2_1)// 分值
		TextView mTvValue321;
	@BindView(R.id.tv_score3_2_1)// 得分
		TextView mTvScore321;
	@BindView(R.id.tv_rule3_2_1)// 扣分规则
		TextView mTvRule321;
	@BindView(R.id.cb1_3_2_1)// 现场抽检单项合格率在80%以上
		CheckBox mCb1321;
	@BindView(R.id.cb2_3_2_1)// 抽检单项合格率在60%以上的扣一半分
		CheckBox mCb2321;
	@BindView(R.id.cb3_3_2_1)// 抽检单项合格率在60%以下的不得分
		CheckBox mCb3321;
	@BindView(R.id.cb4_3_2_1)// 现场抽检材质及强度不合格的不得分
		CheckBox mCb4321;

	private List<CaTestJson> list;
	private Long id;
	private int uploadStatus;
	private String point, status = "";
	private double oldScore;

	@Override
	protected int getLayoutRes() {
		return R.layout.dialog_description18;
	}

	@Override
	protected void initData() {
		mTextView4.setText(item + " " + title);//设置考核标题
		list = CaTestDao.query(cid, item);// 打分表
		List<CaConfigJson> config = CaConfigDao.query(item);// 配置表

		point = config.get(0).getSCORE();//基础分值
		mTvValue321.setText(point);// 分值
		mTvRule321.setText(config.get(0).getMEMO());// 扣分说明

		if (list.size() > 0) {
			id = list.get(0).getID();
			status = list.get(0).getSTATUS();
			mTvScore321.setText(list.get(0).getSCORE()); //得分
			if (list.get(0).getUPLOADSTATUS() >= 1) {
				uploadStatus = 1;
			} else {
				uploadStatus = 0;
			}

			String[] pChoose = list.get(0).getCHOOSE().split(",",-1);
			if (pChoose[0].equals("1")) {// 现场抽检单项合格率在80%以上
				mCb1321.setChecked(true);
			}
			if (pChoose[1].equals("1")) {// 抽检单项合格率在60%以上的扣一半分
				mCb2321.setChecked(true);
			}
			if (pChoose[2].equals("1")) {// 抽检单项合格率在60%以下的不得分
				mCb3321.setChecked(true);
			}
			if (pChoose[3].equals("1")) {// 现场抽检材质及强度不合格的不得分
				mCb4321.setChecked(true);
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
		photo.setVisibility(View.GONE);
		video.setVisibility(View.GONE);

		// 现场抽检单项合格率在80%以上
		mCb1321.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mCb2321.setChecked(false);
					mCb3321.setChecked(false);
					if (mCb4321.isChecked()){// 现场抽检材质及强度不合格的不得分
						mTvScore321.setText(ScoreUtil.SCORE_NOT);
					}else {
						mTvScore321.setText(ScoreUtil.scoreAll(point));
					}
				}else {
					if (mCb2321.isChecked()){
						if (mCb4321.isChecked()){
							mTvScore321.setText(ScoreUtil.SCORE_NOT);
						}else {
							mTvScore321.setText(ScoreUtil.scoreHalf(point));
						}
					}else {
						if (mCb3321.isChecked()){
							mTvScore321.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb4321.isChecked()){
								mTvScore321.setText(ScoreUtil.SCORE_NOT);
							}else {
								mTvScore321.setText("");
							}
						}
					}
				}
				saveData(status);
			}
		});

		// 抽检单项合格率在60%以上的扣一半分
		mCb2321.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mCb1321.setChecked(false);
					mCb3321.setChecked(false);
					if (mCb4321.isChecked()){// 现场抽检材质及强度不合格的不得分
						mTvScore321.setText(ScoreUtil.SCORE_NOT);
					}else {
						mTvScore321.setText(ScoreUtil.scoreHalf(point));
					}
				}else {
					if (mCb1321.isChecked()){
						if (mCb4321.isChecked()){
							mTvScore321.setText(ScoreUtil.SCORE_NOT);
						}else {
							mTvScore321.setText(ScoreUtil.scoreAll(point));
						}
					}else {
						if (mCb3321.isChecked()){
							mTvScore321.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb4321.isChecked()){
								mTvScore321.setText(ScoreUtil.SCORE_NOT);
							}else {
								mTvScore321.setText("");
							}
						}
					}
				}
				saveData(status);
			}
		});

		// 抽检单项合格率在60%以下的不得分
		mCb3321.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mCb1321.setChecked(false);
					mCb2321.setChecked(false);
					mTvScore321.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb1321.isChecked()){
						if (mCb4321.isChecked()){
							mTvScore321.setText(ScoreUtil.SCORE_NOT);
						}else {
							mTvScore321.setText(ScoreUtil.scoreAll(point));
						}
					}else {
						if (mCb2321.isChecked()){
							mTvScore321.setText(ScoreUtil.SCORE_NOT);
						}else {
							if (mCb4321.isChecked()){
								mTvScore321.setText(ScoreUtil.SCORE_NOT);
							}else {
								mTvScore321.setText("");
							}
						}
					}
				}
				saveData(status);
			}
		});

		// 现场抽检材质及强度不合格的不得分
		mCb4321.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mTvScore321.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb1321.isChecked()){
						mTvScore321.setText(ScoreUtil.scoreAll(point));
					}else {
						if (mCb2321.isChecked()){
							mTvScore321.setText(ScoreUtil.scoreHalf(point));
						}else {
							if (mCb3321.isChecked()){
								mTvScore321.setText(ScoreUtil.SCORE_NOT);
							}else {
								mTvScore321.setText("");
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
		if (mCb1321.isChecked()) {// 各项抽查近一年质量
			condition1 = 1;
		} else {
			condition1 = 0;
		}

		int condition2;
		if (mCb2321.isChecked()) {// 外购件附有产品合格证书或第三方抽验检测报告
			condition2 = 1;
		} else {
			condition2 = 0;
		}

		int condition3;
		if (mCb3321.isChecked()) {// 检验报告不合格未做处置不得分
			condition3 = 1;
		} else {
			condition3 = 0;
		}

		int condition4;
		if (mCb4321.isChecked()) {// 外购件无质量凭证不得分
			condition4 = 1;
		} else {
			condition4 = 0;
		}

		String choose = condition1 + "," + condition2 + "," + condition3 + "," + condition4;

		String point = String.valueOf(mTvScore321.getText());// 得分
		if (status.equals("1") || status.equals("2")){
			if (point.equals("")){
				ToastUtils.show("请进行打分");
				return;
			}
		}

		CaTestJson caTestJson = null;

		if (id != null) {
			caTestJson = new CaTestJson(id, cid, item, point, choose, "", status, uploadStatus);
			CaTestDao.insertOrReplace(caTestJson);
		} else {
			caTestJson = new CaTestJson(cid, item, point, choose, "", status, uploadStatus);
			CaTestDao.insertOrReplace(caTestJson);
			list = CaTestDao.query(cid, item);
			if (list != null && list.size() > 0) {
				id = list.get(0).getID();
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
