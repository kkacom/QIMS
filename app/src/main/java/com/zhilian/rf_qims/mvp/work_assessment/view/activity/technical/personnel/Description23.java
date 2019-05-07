package com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel;

import android.os.Bundle;
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
import io.reactivex.annotations.NonNull;

import static com.zhilian.rf_qims.util.CommonUtils.getContext;

/**
 * 管理制度（2.4.1~2.4.5）
 */
public class Description23 extends BaseModuleActivity {
	@BindView(R.id.textView4)// 标题
		TextView mTextView4;
	@BindView(R.id.have)// 有且适应
		CheckBox mHave;
	@BindView(R.id.inadaptation)// 不适应
		CheckBox mInadaptation;
	@BindView(R.id.operation)// 难操作
		CheckBox mOperation;
	@BindView(R.id.tv_value2_3_1)// 分值
		TextView mTvValue231;
	@BindView(R.id.tv_score2_3_1)// 得分
		TextView mTvScore231;
	@BindView(R.id.tv_rule2_3_1)// 扣分规则
		TextView mTvRule231;

	private Long id;
	private int uploadStatus;
	private List<CaTestJson> list;
	private String point, status = "";
	private double oldScore;

	@Override
	protected int getLayoutRes() {
		return R.layout.dialog_description1;
	}

	@Override
	protected void initData() {
		mTextView4.setText(item + title);
		List<CaConfigJson> config = CaConfigDao.query(item);// 配置表
		list = CaTestDao.query(cid, item); //打分表

		point = config.get(0).getSCORE();//分值
		mTvValue231.setText(point);
		if (list.size() > 0){
			id = list.get(0).getID();
			status = list.get(0).getSTATUS();
			mTvScore231.setText(list.get(0).getSCORE());
			if (list.get(0).getUPLOADSTATUS() >= 1){
				uploadStatus = 1;
			}else {
				uploadStatus = 0;
			}
			String[] choose = list.get(0).getCHOOSE().split(",",-1);
			if (choose[0].equals("1")){
				mHave.setChecked(true);
			}
			if (choose[1].equals("1")){
				mInadaptation.setChecked(true);
			}
			if (choose[2].equals("1")){
				mOperation.setChecked(true);
			}
		}else {
			uploadStatus = 0;
		}
	}

	/**
	 * 保存和更改状态
	 * @param status 状态（1 完成、2 未完成）
	 */
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

		cBListener(mHave);// 有且适应
		cBListener(mInadaptation);// 不适应
		cBListener(mOperation);// 难操作

		// 有且适应
		mHave.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					mInadaptation.setChecked(false);
					mOperation.setChecked(false);
					mTvScore231.setText(ScoreUtil.scoreAll(point));
				}else {
					if (!mInadaptation.isChecked()){
						if (!mOperation.isChecked()){
							mTvScore231.setText("");
						}
					}
				}
				saveData(status);
			}
		});

		// 不适应
		mInadaptation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					mHave.setChecked(false);
					mOperation.setChecked(false);
					mTvScore231.setText(String.valueOf(Double.valueOf(point) - 0.3));
				}else {
					if (!mHave.isChecked()){
						if (!mOperation.isChecked()){
							mTvScore231.setText("");
						}
					}
				}
				saveData(status);
			}
		});

		// 难操作
		mOperation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					mHave.setChecked(false);
					mInadaptation.setChecked(false);
					mTvScore231.setText(ScoreUtil.SCORE_NOT);
				}else {
					if (!mHave.isChecked()){
						if (!mInadaptation.isChecked()){
							mTvScore231.setText("");
						}
					}
				}
				saveData(status);
			}
		});

	}

	/**
	 * CheckBox的事件监听
	 */
	private void cBListener(CheckBox cb) {
		cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
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
	 * @param status 状态
	 */
	public void saveData(String status){
		/*☆先计算一遍旧item得分，因为很多item都有可能创建多于需要检测的人数，需要把所有item的分数排序大小再进行多余筛选会非常复杂。
		故反向思考，计算出当前旧item的分数用总item减去旧item分数，最后到计算总分再加上新分数就可以了(Compute.compute(cid, item, oldScore);)*/
		oldScore = Compute.compute(cid, item, oldScore);
		String point = "", have = "", inadaptation = "", operation = "";
		if (mHave.isChecked()) { // 有
			have = "1";
		}else {
			have = "0";
		}
		if (mInadaptation.isChecked()){// 不适应
			inadaptation = "1";
		}else {
			inadaptation = "0";
		}
		if (mOperation.isChecked()){// 难操作
			operation = "1";
		}else {
			operation = "0";
		}
		String choose = have + "," + inadaptation  + "," + operation;

		CaTestJson caTestJson1 = null;

		point = String.valueOf(mTvScore231.getText());
		if (status.equals("1") || status.equals("2")){
			if (point.equals("")){
				ToastUtils.show("请进行打分");
				return;
			}
		}

		if(id != null){
			caTestJson1 = new CaTestJson(id, cid, item, point, choose, "", status, uploadStatus);
			CaTestDao.insertOrReplace(caTestJson1);
		}else{
			caTestJson1 = new CaTestJson(cid, item, point, choose, "", status, uploadStatus);
			CaTestDao.insertOrReplace(caTestJson1);
			list = CaTestDao.query(cid, item);
			if (list != null && list.size() > 0){
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

	@Override
	protected void onResume() {
		super.onResume();
		initData();
	}
}
