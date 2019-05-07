package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
import com.zhilian.rf_qims.entity.CaTestDao;
import com.zhilian.rf_qims.entity.CaTestJson;
import com.zhilian.rf_qims.entity.EntEquipmentDao;
import com.zhilian.rf_qims.entity.EntEquipmentJson;
import com.zhilian.rf_qims.entity.WorkAbilityDao;
import com.zhilian.rf_qims.entity.WorkAbilityJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description4;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.workassessmentmodule.EquipmentListAdapter;
import com.zhilian.rf_qims.util.Compute;
import com.zhilian.rf_qims.util.CreatePrrsonOrEquipment;
import com.zhilian.rf_qims.util.DoubleUtil;
import com.zhilian.rf_qims.util.ScoreUtil;
import com.zhilian.rf_qims.util.ToastUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by colin on 2018/11/21 15:05 .
 * 大型加工设备功能模块的2.6.1的设备列表界面，和设备统计
 */
public class EquipmentList extends AppCompatActivity {
	@BindView(R.id.title)
	TextView mTitle;//标题
	@BindView(R.id.totalPoint)// 总分
		TextView mTotalPoint;
	@BindView(R.id.newEquipment)// 自有设备
		TextView mNewEquipment;
	@BindView(R.id.oldEquipment)// 租凭设备
		TextView mOldEquipment;
	@BindView(R.id.yesOperate)// 正常运转
		TextView mYesOperate;
	@BindView(R.id.noOperate)// 未能正常运转
		TextView mNoOperate;
	@BindView(R.id.yesFixation)// 已固定
		TextView mYesFixation;
	@BindView(R.id.noFixation)// 未固定
		TextView mNoFixation;
	@BindView(R.id.cb1_2_6_1)// 吨位不够
		CheckBox mCb1261;
	@BindView(R.id.cb2_2_6_1)// 起吊高度未满足
		CheckBox mCb2261;
	@BindView(R.id.cb3_2_6_1)// 起吊高度低于标准90%
		CheckBox mCb3261;
	@BindView(R.id.listView)// 设备名称和每台得分列表
		ListView mListView;

	@BindView(R.id.photo)// 拍照
		Button mPhoto;
	@BindView(R.id.unfinished)// 未完成
		Button mUnfinished;
	@BindView(R.id.finish)// 完成
		Button mFinish;
	@BindView(R.id.close)// 关闭
		Button mClose;
	@BindView(R.id.create)//创建设备
		Button mCreate;
	@BindView(R.id.scrollView)
	ScrollView mScrollView;

	private long id;
	private int uploadStatus, flag, equipmentNumFlag, size;
	private double point;
	private String eid, cid, item, title, itemTailor, sPoint, status = "";
	private EquipmentListAdapter adapter;
	private List<CaTestJson> test;
	private List<EntEquipmentJson> equipment;
	private double oldScore;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_equipment_list);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		ButterKnife.bind(this);

		initData();
	}

	/**
	 * 初始化
	 */
	private void initData() {
		equipmentNumFlag = 0;
		eid = Common.getEid();
		cid = String.valueOf(Common.getWid());
		item = getIntent().getStringExtra("item");
		itemTailor = item.substring(0, 2);
		title = getIntent().getStringExtra("title");
		CreatePrrsonOrEquipment.equipment(mCreate, this, Integer.valueOf(eid), Integer.parseInt(cid), item);//创建设备
		equipment = EntEquipmentDao.query(eid, cid, item);// 设备表
		test = CaTestDao.query(cid, item);// 打分表
		//List<CaTestJson> test1 = CaTestDao.query(cid, itemTailor);// 所有子类总打分数据
		List<CaConfigJson> config = CaConfigDao.query(item);// 配置表
		size = config.get(0).getNUMBER();//此检测有几台设备
		String configExplain = config.get(0).getMEMO();// 扣分说明
		String configPoint = String.valueOf(Double.valueOf(config.get(0).getSCORE()) / size);// 分值

		mTitle.setText(item + " " + title);//标题
		if (test.size() > 0) {
			id = test.get(0).getID();
			status = test.get(0).getSTATUS();
			if (test.get(0).getUPLOADSTATUS() >= 1) {
				uploadStatus = 1;
			} else {
				uploadStatus = 0;
			}
			if (test.get(0).getCHOOSE() != null && !test.get(0).getCHOOSE().equals("")) {
				String[] choose = test.get(0).getCHOOSE().split(",", -1);
				if (choose[0].equals("1")) {// 吨位不足
					mCb1261.setChecked(true);
				}
				if (choose[1].equals("1")) {// 起吊高度不足
					mCb2261.setChecked(true);
				}
				if (choose[2].equals("1")) {// 起吊高度不足90%
					mCb3261.setChecked(true);
				}
			}
		} else {
			uploadStatus = 0;
		}

		// 获取设备名单的数据
		int newEquipment = 0;// 自有设备
		int oldEquipment = 0;// 租凭设备
		int yesOperate = 0;// 能运转
		int noOperate = 0;// 未能运转
		int yesFixation = 0;// 已固定
		int noFixation = 0;// 未固定
		double totalPoint = 0; //总得分
		double[] array;

		if (equipment.size() > 0) {
			array = new double[equipment.size()];
			for (int i = 0; i < equipment.size(); i++) {
				if (equipment.get(i).getSCORE() != null && !equipment.get(i).getSCORE().equals("")) {
					//totalPoint = DoubleUtil.add(totalPoint, Double.valueOf(equipment.get(i).getScore()));
					array[i] = Double.parseDouble(equipment.get(i).getSCORE());
					equipmentNumFlag += 1;
				}
				if (equipment.get(i).getCHOOSE() != null && !equipment.get(i).getCHOOSE().equals("")) { //所有检查人通过或不通过项统计
					String[] choose = equipment.get(i).getCHOOSE().split(",", -1);// 0满足1运转2付款凭证3固定4自有or租凭
					if (choose[4].equals("1")) { // 自有 租凭
						newEquipment = newEquipment + 1;
					} else if (choose[4].equals("0")) {
						oldEquipment = oldEquipment + 1;
					}
					if (choose[1].equals("1")) {// 运转 未能运转
						yesOperate = yesOperate + 1;
					} else {
						noOperate = noOperate + 1;
					}
					if (choose[3].equals("1")) {// 固定 未固定
						yesFixation = yesFixation + 1;
					} else {
						noFixation = noFixation + 1;
					}
				}
			}
			if (array != null){
				if (array.length > 0){
					Arrays.sort(array); //升序排序
					int number = CaConfigDao.query(item).get(0).getNUMBER(); //规定人数
					if (array.length >= number && number != 0){ //当创建的人数大于规定人数
						for(int i = 0; i < number; i++){
							totalPoint = DoubleUtil.add(totalPoint, array[array.length - (i+1)]);
						}
					}else { 						//当创建的人数不足规定人数
						for(int i = 0; i < array.length; i++){
							totalPoint = DoubleUtil.add(totalPoint, array[i]);
						}
					}
				}
			}
		}

		point = totalPoint;
		sPoint = String.valueOf(totalPoint);
		mTotalPoint.setText(String.valueOf(totalPoint)); // 总得分
		mNewEquipment.setText(String.valueOf(newEquipment));// 新设备
		mOldEquipment.setText(String.valueOf(oldEquipment));// 旧设备
		mYesOperate.setText(String.valueOf(yesOperate));// 正常运转
		mNoOperate.setText(String.valueOf(noOperate));// 未正常运转
		mYesFixation.setText(String.valueOf(yesFixation));// 已固定
		mNoFixation.setText(String.valueOf(noFixation));// 未固定

		//用于解决scrollView嵌套list导致list无法点击的问题
		mListView.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_MOVE) {
					mScrollView.requestDisallowInterceptTouchEvent(true);
				}
				return false;
			}
		});
		adapter = new EquipmentListAdapter(this, equipment);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent;
				//if (item.equals("2.6.1")) {
				intent = new Intent(EquipmentList.this, Description4.class);
				/*} else {
					intent = new Intent(EquipmentList.this, Description266New.class);
				}*/
				intent.putExtra("equipment", equipment.get(position));//设备
				/*if (test.size() > 0 && position <= test.size() - 1){
					intent.putExtra("test", test.get(position));//打分表  要传实体类，需要在实体类实现Serializable接口生成serialVersionUID
				}*/
				intent.putExtra("configExplain", configExplain);//扣分说明
				intent.putExtra("configPoint", configPoint);//分值
				intent.putExtra("item", item);//item
				intent.putExtra("title", title);//item详细描述
				startActivity(intent);
			}
		});

		checkBoxListener();// 复选按钮监听
		unfinishedButton();// 未完成
		finishButton();// 完成
		close();// 关闭
	}

	/**
	 * 复选按钮监听
	 */
	private void checkBoxListener() {
		// 吨位不足
		mCb1261.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					if (equipmentNumFlag < 3) { // 是否缺设备
						mTotalPoint.setText(ScoreUtil.SCORE_NOT);
					} else {
						if (mCb3261.isChecked()) { // 起吊高度低于90%
							mTotalPoint.setText(ScoreUtil.SCORE_NOT);
						} else {
							if (mCb2261.isChecked()) { //起吊高度不足
								mTotalPoint.setText(ScoreUtil.SCORE_NOT);
							} else {
								mTotalPoint.setText(ScoreUtil.scoreAll(String.valueOf(point - 2)));
							}
						}
					}
				} else {
					if (equipmentNumFlag < 3) { // 是否缺设备
						mTotalPoint.setText(ScoreUtil.SCORE_NOT);
					} else {
						if (mCb3261.isChecked()) { // 起吊高度低于90%
							mTotalPoint.setText(ScoreUtil.SCORE_NOT);
						} else {
							if (mCb2261.isChecked()) { //起吊高度不足
								mTotalPoint.setText(ScoreUtil.scoreAll(String.valueOf(point - 1.5)));
							} else {
								mTotalPoint.setText(ScoreUtil.scoreAll(sPoint));
							}
						}
					}
				}
				saveData(status);
			}
		});

		// 起吊高度未满足
		mCb2261.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					if (equipmentNumFlag < 3) { // 是否缺设备
						mTotalPoint.setText(ScoreUtil.SCORE_NOT);
					} else {
						if (mCb3261.isChecked()) { // 起吊高度低于90%
							mTotalPoint.setText(ScoreUtil.SCORE_NOT);
						} else {
							if (mCb1261.isChecked()) { //吨位不足
								mTotalPoint.setText(ScoreUtil.SCORE_NOT);
							} else {
								mTotalPoint.setText(ScoreUtil.scoreAll(String.valueOf(point - 1.5)));
							}
						}
					}
				} else {
					if (equipmentNumFlag < 3) { // 是否缺设备
						mTotalPoint.setText(ScoreUtil.SCORE_NOT);
					} else {
						if (mCb3261.isChecked()) { // 起吊高度低于90%
							mTotalPoint.setText(ScoreUtil.SCORE_NOT);
						} else {
							if (mCb1261.isChecked()) { //吨位不足
								mTotalPoint.setText(ScoreUtil.scoreAll(String.valueOf(point - 2)));
							} else {
								mTotalPoint.setText(ScoreUtil.scoreAll(sPoint));
							}
						}
					}
				}
				saveData(status);
			}
		});

		// 起吊高度低于标准90%
		mCb3261.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mTotalPoint.setText(ScoreUtil.SCORE_NOT);
				} else {
					if (equipmentNumFlag < 3) { // 是否缺设备
						mTotalPoint.setText(ScoreUtil.SCORE_NOT);
					} else {
						if (mCb1261.isChecked()) { // 吨位不足
							if (mCb2261.isChecked()) { // 起吊高度不足
								mTotalPoint.setText(ScoreUtil.SCORE_NOT);
							} else {
								mTotalPoint.setText(ScoreUtil.scoreAll(String.valueOf(point - 2)));
							}
						} else {
							if (mCb2261.isChecked()) { // 起吊高度不足
								mTotalPoint.setText(ScoreUtil.scoreAll(String.valueOf(point - 1.5)));
							} else {
								mTotalPoint.setText(ScoreUtil.scoreAll(sPoint));
							}
						}
					}
				}
				saveData(status);
			}
		});
	}

	/**
	 * 未完成
	 */
	private void unfinishedButton() {
		mUnfinished.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				flag = 1;
				saveData("2");
			}
		});
	}

	/**
	 * 完成
	 */
	private void finishButton() {
		mFinish.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				flag = 1;
				saveData("1");
			}
		});
	}

	/**
	 * 保存数据
	 */
	private void saveData(String status) {
		/*☆先计算一遍旧item得分，因为很多item都有可能创建多于需要检测的人数，需要把所有item的分数排序大小再进行多余筛选会非常复杂。
		故反向思考，计算出当前旧item的分数用总item减去旧item分数，最后到计算总分再加上新分数就可以了(Compute.compute(cid, item, oldScore);)*/
		oldScore = Compute.compute(cid, item, oldScore);
		String one = "", two = "", three = "";
		if (mCb1261.isChecked()) { //吨位不足
			one = "1";
		} else {
			one = "2";
		}

		if (mCb2261.isChecked()) {// 起吊高度不足
			two = "1";
		} else {
			two = "0";
		}

		if (mCb3261.isChecked()) {// 起吊高度不足90%
			three = "1";
		} else {
			three = "0";
		}

		String point = String.valueOf(mTotalPoint.getText());// 总得分
		String me = String.valueOf(mNewEquipment.getText());// 自有
		String rent = String.valueOf(mOldEquipment.getText());// 租凭
		String yesOperate = String.valueOf(mYesOperate.getText());// 正常运转
		String noOperate = String.valueOf(mNoOperate.getText());// 未正常运转
		String yesFixation = String.valueOf(mYesFixation.getText());// 已固定
		String noFixation = String.valueOf(mNoFixation.getText());// 未固定
		String choose = one + "," + two + "," + three
			+ "," + me + "," + rent + "," + yesOperate + "," + noOperate + "," + yesFixation + "," + noFixation;
		if (equipmentNumFlag < size) {
			ToastUtils.show("缺"+ (size - equipmentNumFlag) +"台设备,请创建并打分");
			return;
		}

		CaTestJson caTestJson = null;

		if (id != 0) {
			caTestJson = new CaTestJson(id, cid, item, point, choose, "", status, uploadStatus);
			CaTestDao.insertOrReplace(caTestJson);
		} else {
			caTestJson = new CaTestJson(cid, item, point, choose, "", status, uploadStatus);
			CaTestDao.insertOrReplace(caTestJson);
			test = CaTestDao.query(cid, item);
			if (test != null && test.size() > 0) {
				id = test.get(0).getID();
			}
		}

		WorkAbilityJson workAbilityJson = Common.getWorkAbilityJson();
		total(workAbilityJson, status);
		if (flag == 1) {
			if (status.equals("1")) {
				ToastUtils.show("<完成>保存成功");
				flag = 0;
			} else if (status.equals("2")) {
				ToastUtils.show("<未完成>保存成功");
				flag = 0;
			}
		}
	}

	/**
	 * 算总分
	 */
	private void total(WorkAbilityJson workAbilityJson, String status) {
		String itemLike = item;
		String itemTailor = item.substring(0, 3);
		List<CaTestJson> caTestJsonList = CaTestDao.query(cid, itemLike);
		double total = 0.00;
		double[] allScore = new double[caTestJsonList.size()];
		for (int i = 0; i < caTestJsonList.size(); i++) {
			if (caTestJsonList.get(i).getSCORE() != null) {
				allScore[i] = caTestJsonList.get(i).getSCORE().equals("") ? 0.00 : Double.valueOf(caTestJsonList.get(i).getSCORE());
			} else {
				allScore[i] = 0.00;
			}
		}
		if (allScore != null) {
			if (allScore.length > 0) {
				Arrays.sort(allScore); //升序排序
				int number = CaConfigDao.query(item).get(0).getNUMBER(); //规定人数
				if (allScore.length >= number) { //当创建的人数大于规定人数
					for (int i = 0; i < number; i++) {
						total = DoubleUtil.add(total, allScore[allScore.length - (i + 1)]);
					}
				} else {                        //当创建的人数不足规定人数
					for (int i = 0; i < allScore.length; i++) {
						total = DoubleUtil.add(total, allScore[i]);
					}
				}
			}
		}
		CaTestJson caTestJson = CaTestDao.queryOne(cid, itemTailor);
		if (caTestJson == null) {
			caTestJson = new CaTestJson(cid, itemTailor, String.valueOf(total), "", "", status, 0);
		} else {
			caTestJson.setSCORE(String.valueOf(DoubleUtil.add(DoubleUtil.sub(Double.valueOf(caTestJson.getSCORE()), oldScore), total)));
			caTestJson.setSTATUS(status);
		}
		CaTestDao.insertOrReplace(caTestJson);
		//-------------------------------------------------------------------------------------------下面存item2
		itemLike = item.substring(0, 2);//取item的前2获取所有2.的人，如2.1.11 = 2.
		itemTailor = item.substring(0, 1);
		caTestJsonList = CaTestDao.queryLike(cid, itemLike + "%");
		double total2 = 0.00;
		for (CaTestJson caTestJson3 : caTestJsonList) {
			if (caTestJson3.getSCORE() != null && !caTestJson3.getSCORE().equals("")) {
				if (caTestJson3.getITEM().length() == 3) { //只计算item为x.x这样的
					total2 = DoubleUtil.add(total2, Double.valueOf(caTestJson3.getSCORE()));
				}
			}
		}
		CaTestJson caTestJson2 = CaTestDao.queryOne(cid, itemTailor);
		if (caTestJson2 == null) {
			caTestJson2 = new CaTestJson(cid, itemTailor, String.valueOf(total2), "", "", status, 0);
		} else {
			caTestJson2.setSCORE(String.valueOf(total2));
			caTestJson2.setSTATUS(status);
		}

		CaTestDao.insertOrReplace(caTestJson2);

		if (status.equals("1")) {
			workAbilityJson.setSTATUS(1);
		}
		workAbilityJson.setITEM2_6(String.valueOf(caTestJson.getSCORE()));
		workAbilityJson.setITEM2(String.valueOf(total2));
		WorkAbilityDao.insertOrReplace(workAbilityJson);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 关闭界面
	 */
	private void close() {
		mClose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	/**
	 * 返回此页面时刷新数据
	 */
	@Override
	protected void onResume() {
		super.onResume();
		initData();
	}
}
