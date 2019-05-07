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
 * 安装质量（3.3.2）
 */
public class Description332 extends BaseModuleActivity {
	@BindView(R.id.textView4)// 标准
		TextView mTextView4;
	@BindView(R.id.tv_value3_3_2)// 分值
		TextView mTvValue332;
	@BindView(R.id.tv_score3_3_2)// 得分
		TextView mTvScore332;
	@BindView(R.id.tv_rule3_3_2)// 扣分规则
		TextView mTvRule332;
	@BindView(R.id.cb1_3_3_2)// 门框安装记录未全覆盖且信息不全扣一半分
		CheckBox mCb1332;
	@BindView(R.id.cb2_3_3_2)// 考评未见安装检查结果不得分
		CheckBox mCb2332;

	private List<CaTestJson> list;
	private Long id;
	private int uploadStatus;
	private String point, status = "";
	private double oldScore;

	@Override
	protected int getLayoutRes() {
		return R.layout.dialog_description332;
	}

	@Override
	protected void initData() {
		mTextView4.setText(item + " " + title);//设置考核标题
		list = CaTestDao.query(cid, item);// 打分表
		List<CaConfigJson> config = CaConfigDao.query(item);// 配置表

		point = config.get(0).getSCORE();//基础分值
		mTvValue332.setText(point);// 分值
		mTvScore332.setText(point);// 分值
		mTvRule332.setText(config.get(0).getMEMO());// 扣分说明

		if (list.size() > 0) {
			id = list.get(0).getID();
			status = list.get(0).getSTATUS();
			mTvScore332.setText(list.get(0).getSCORE()); //得分
			if (list.get(0).getUPLOADSTATUS() >= 1) {
				uploadStatus = 1;
			} else {
				uploadStatus = 0;
			}

			String[] pChoose = list.get(0).getCHOOSE().split(",",-1);
			if (pChoose[0].equals("1")) {// 门框安装记录未全覆盖且信息不全扣一半分
				mCb1332.setChecked(true);
			}
			if (pChoose[1].equals("1")) {// 考评未见安装检查结果不得分
				mCb2332.setChecked(true);
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

		// 门框安装记录未全覆盖且信息不全扣一半分
		mCb1332.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					if (mCb2332.isChecked()){
						mTvScore332.setText(ScoreUtil.SCORE_NOT);
					}else {
						mTvScore332.setText(ScoreUtil.scoreHalf(point));
					}
				}else {
					if (mCb2332.isChecked()){
						mTvScore332.setText(ScoreUtil.SCORE_NOT);
					}else {
						mTvScore332.setText(ScoreUtil.scoreAll(point));
					}
				}
				saveData(status);
			}
		});

		// 考评未见安装检查结果不得分
		mCb2332.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mTvScore332.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (mCb1332.isChecked()){
						mTvScore332.setText(ScoreUtil.scoreHalf(point));
					}else {
						mTvScore332.setText(ScoreUtil.scoreAll(point));
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
		if (mCb1332.isChecked()) {// 自动焊工艺每批均有抽检记录
			condition1 = 1;
		} else {
			condition1 = 0;
		}

		int condition2;
		if (mCb2332.isChecked()) {// 门扇、门框成品检查记录不齐全不得分
			condition2 = 1;
		} else {
			condition2 = 0;
		}

		String choose = condition1 + "," + condition2;

		String point = String.valueOf(mTvScore332.getText());
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
