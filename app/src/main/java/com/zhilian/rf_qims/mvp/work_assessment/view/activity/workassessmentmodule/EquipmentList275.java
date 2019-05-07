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
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description10;
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
 * 普通加工设备功能模块的2.7.5的设备列表界面，和设备统计
 */
public class EquipmentList275 extends AppCompatActivity {
	@BindView(R.id.title)//标题
		TextView mTitle;
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

	private Long id;
	private int uploadStatus, flag, equipmentNumFlag, size;
	private double point;
	private String eid, cid, item, title, itemTailor, sPoint, status = "";
	private EquipmentListAdapter adapter;
	private List<CaTestJson> test;
	private List<EntEquipmentJson> equipment;
	private double oldScore;

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_equipment_list275);
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
		//itemTailor = item.substring(0, 2);
		title = getIntent().getStringExtra("title");
		CreatePrrsonOrEquipment.equipment(mCreate, this, Integer.valueOf(eid), Integer.parseInt(cid), item);//创建设备
		equipment = EntEquipmentDao.query(eid, cid, item);// 设备表
		test = CaTestDao.query(cid, item);// 打分表
		//List<CaTestJson> test1 = CaTestDao.query(cid, itemTailor);// 所有子类总打分（如2.7   2.6）
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
		}
		// 获取设备名单的数据
		int newEquipment = 0;// 自有设备
		int oldEquipment = 0;// 租凭设备
		int yesOperate = 0;// 能运转
		int noOperate = 0;// 未能运转
		double totalPoint = 0; //总得分
		double[] array;

		if (equipment.size() > 0) {
			array = new double[equipment.size()];
			for (int i = 0; i < equipment.size(); i++) {
				if (equipment.get(i).getSCORE() != null && !equipment.get(i).getSCORE().equals("")) {
					//totalPoint = totalPoint + Double.valueOf(equipment.get(i).getScore());
					array[i] = Double.parseDouble(equipment.get(i).getSCORE());
					equipmentNumFlag += 1;
				}
				if (equipment.get(i).getCHOOSE() != null && !equipment.get(i).getSCORE().equals("")) { //所有检查设备通过或不通过项统计
					String[] choose = equipment.get(i).getCHOOSE().split(",", -1);
					if (choose[3].equals("0")) { // 已固定 未固定(选中扣一半分，所以0才是正确)
						newEquipment = newEquipment + 1;
					} else {
						oldEquipment = oldEquipment + 1;
					}
					if (choose[2].equals("0")) {// 运转 未正常运转(选中扣一半分，所以0才是正确)
						yesOperate = yesOperate + 1;
					} else {
						noOperate = noOperate + 1;
					}
				}
			}


			if (array != null) {
				if (array.length > 0) {
					Arrays.sort(array); //升序排序
					int number = CaConfigDao.query(item).get(0).getNUMBER(); //规定人数
					if (array.length >= number && number != 0) { //当创建的人数大于规定人数
						for (int i = 0; i < number; i++) {
							totalPoint = DoubleUtil.add(totalPoint, array[array.length - (i + 1)]);
						}
					} else {                        //当创建的人数不足规定人数
						for (int i = 0; i < array.length; i++) {
							totalPoint = DoubleUtil.add(totalPoint, array[i]);
						}
					}
				}
			}
		}

		point = totalPoint;
		sPoint = String.valueOf(totalPoint);
		mTotalPoint.setText(sPoint); // 总得分
		mNewEquipment.setText(String.valueOf(newEquipment));// 新设备
		mOldEquipment.setText(String.valueOf(oldEquipment));// 旧设备
		mYesOperate.setText(String.valueOf(yesOperate));// 正常运转
		mNoOperate.setText(String.valueOf(noOperate));// 未正常运转

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
				//Common.setPersonnelJson(personnel.get(position));
				//Common.setCaTestJson(test.get(position));
				//Common.setConfigPoint(configPoint);
				//.setConfigExplain(configExplain);
				Intent intent;
				//if (item.equals("2.6.1")) {
				intent = new Intent(EquipmentList275.this, Description10.class);
				/*} else {
					intent = new Intent(EquipmentList.this, Description266New.class);
				}*/
				intent.putExtra("equipment", equipment.get(position));//设备
				/*if (test.size() > 0){
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

		String point = String.valueOf(mTotalPoint.getText());// 总得分
		String me = String.valueOf(mNewEquipment.getText());// 自有
		String rent = String.valueOf(mOldEquipment.getText());// 租凭
		String yesOperate = String.valueOf(mYesOperate.getText());// 正常运转
		String noOperate = String.valueOf(mNoOperate.getText());// 未正常运转
		String choose = me + "," + rent + "," + yesOperate + "," + noOperate;
		if (equipmentNumFlag < size) {
			ToastUtils.show("缺"+ (size - equipmentNumFlag) +"台设备,请创建并打分");
			initData();
			return;
		}

		CaTestJson caTestJson = null;

		if (id != null) {
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
		ScoreUtil.total(workAbilityJson, status, cid, item ,oldScore, Integer.parseInt(eid));
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
