package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.os.Bundle;
import android.util.Log;
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
 * 场地条件（2.5.8）
 */
public class Description258 extends BaseModuleActivity {
	@BindView(R.id.textView4)// 标题
		TextView mTextView4;
	@BindView(R.id.score)// 分值
		TextView mScore;
	@BindView(R.id.point)// 得分
		TextView mPoint;
	@BindView(R.id.cb_one2_5_8)// 喷淋不能自动控制
		CheckBox mCbOne258;
	@BindView(R.id.cb_two2_5_8)// 喷淋不能覆盖设备
		CheckBox mCbTwo258;
	@BindView(R.id.cb_three2_5_8)// 控制单元可正常运行，连续作业少于2分钟
		CheckBox mCbThree258;
	@BindView(R.id.tv_rule2_5_8)// 扣分规则
		TextView mTvRule258;

	private List<CaTestJson> list;
	private Long id;
	private int uploadStatus;
	private String point, status = "";
	private double oldScore;

	@Override
	protected int getLayoutRes() {
		return R.layout.dialog_description258;
	}

	@Override
	protected void initData() {
		mTextView4.setText(item + " " + title);
		List<CaConfigJson> config = CaConfigDao.query(item);// 配置表
		list = CaTestDao.query(cid, item); //打分表

		point = config.get(0).getSCORE();//分值
		mScore.setText(point);// 分值、
		mPoint.setText(point);
		mTvRule258.setText(config.get(0).getMEMO());// 扣分规则
		if (list.size() > 0) {
			id = list.get(0).getID();
			status = list.get(0).getSTATUS();
			mPoint.setText(list.get(0).getSCORE()); //得分
			if (list.get(0).getUPLOADSTATUS() >= 1) {
				uploadStatus = 1;
			} else {
				uploadStatus = 0;
			}
			String[] choose = list.get(0).getCHOOSE().split(",",-1);
			if (choose[0].equals("1")) {
				mCbOne258.setChecked(true);
			}
			if (choose[1].equals("1")) {
				mCbTwo258.setChecked(true);
			}
			if (choose[2].equals("1")) {
				mCbThree258.setChecked(true);
			}
		} else {
			uploadStatus = 0;
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

		// 喷淋不能自动控制
		mCbOne258.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					if (mCbTwo258.isChecked()){
						mPoint.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCbThree258.isChecked()){
							mPoint.setText(ScoreUtil.SCORE_NOT);
						}else {
							mPoint.setText(ScoreUtil.scoreHalf(point));
						}
					}
				}else {
					if (mCbTwo258.isChecked()){
						if (mCbThree258.isChecked()){
							mPoint.setText(ScoreUtil.SCORE_NOT);
						}else {
							mPoint.setText(ScoreUtil.scoreHalf(point));
						}
					}else {
						if (mCbThree258.isChecked()){
							mPoint.setText(ScoreUtil.scoreHalf(point));
						}else {
							mPoint.setText(ScoreUtil.scoreAll(point));
						}
					}
				}
				saveData(status);
			}
		});

		// 喷淋不能覆盖设备
		mCbTwo258.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					if (mCbOne258.isChecked()){
						mPoint.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCbThree258.isChecked()){
							mPoint.setText(ScoreUtil.SCORE_NOT);
						}else {
							mPoint.setText(ScoreUtil.scoreHalf(point));
						}
					}
				}else {
					if (mCbOne258.isChecked()){
						if (mCbThree258.isChecked()){
							mPoint.setText(ScoreUtil.SCORE_NOT);
						}else {
							mPoint.setText(ScoreUtil.scoreHalf(point));
						}
					}else {
						if (mCbThree258.isChecked()){
							mPoint.setText(ScoreUtil.scoreHalf(point));
						}else {
							mPoint.setText(ScoreUtil.scoreAll(point));
						}
					}
				}
				saveData(status);
			}
		});

		// 控制单元可正常运行，连续作业少于2分钟
		mCbThree258.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					if (mCbOne258.isChecked()){
						mPoint.setText(ScoreUtil.SCORE_NOT);
					}else {
						if (mCbTwo258.isChecked()){
							mPoint.setText(ScoreUtil.SCORE_NOT);
						}else {
							mPoint.setText(ScoreUtil.scoreHalf(point));
						}
					}
				}else {
					if (mCbOne258.isChecked()){
						if (mCbTwo258.isChecked()){
							mPoint.setText(ScoreUtil.SCORE_NOT);
						}else {
							mPoint.setText(ScoreUtil.scoreHalf(point));
						}
					}else {
						if (mCbTwo258.isChecked()){
							mPoint.setText(ScoreUtil.scoreHalf(point));
						}else {
							mPoint.setText(ScoreUtil.scoreAll(point));
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
				public void onAction(@android.support.annotation.NonNull ArrayList<AlbumFile> result) {
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
				public void onAction(@android.support.annotation.NonNull ArrayList<AlbumFile> result) {
					ToastUtils.show("摄像完毕");
				}
			}).start();
	}

	/**
	 * 保存数据
	 *
	 * @param status 状态
	 */
	public void saveData(String status) {
/*☆先计算一遍旧item得分，因为很多item都有可能创建多于需要检测的人数，需要把所有item的分数排序大小再进行多余筛选会非常复杂。
		故反向思考，计算出当前旧item的分数用总item减去旧item分数，最后到计算总分再加上新分数就可以了(Compute.compute(cid, item, oldScore);)*/
		oldScore = Compute.compute(cid, item, oldScore);
		String point = "", one = "", two = "", three = "";
		if (mCbOne258.isChecked()) { // 原材料、半成品、成品室内分类存放
			one = "1";
		} else {
			one = "0";
		}
		if (mCbTwo258.isChecked()) {// 物品存放混杂
			two = "1";
		} else {
			two = "0";
		}
		if (mCbThree258.isChecked()) {// 存放地具有防水、防潮、防变形等功能、设备和手段
			three = "1";
		} else {
			three = "0";
		}
		String choose = one + "," + two + "," + three;

		CaTestJson caTestJson1 = null;

		point = String.valueOf(mPoint.getText());
		if (status.equals("1") || status.equals("2")){
			if (point.equals("")){
				ToastUtils.show("请进行打分");
				return;
			}
		}

		if (id != null) {
			caTestJson1 = new CaTestJson(id, cid, item, point, choose, "", status, uploadStatus);
			CaTestDao.insertOrReplace(caTestJson1);
		} else {
			caTestJson1 = new CaTestJson(cid, item, point, choose, "", status, uploadStatus);
			CaTestDao.insertOrReplace(caTestJson1);
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
