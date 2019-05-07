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
import com.zhilian.rf_qims.entity.PersonnelDao;
import com.zhilian.rf_qims.entity.PersonnelJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description2;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description2P;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description3;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.workassessmentmodule.TechnicistListAdapter;
import com.zhilian.rf_qims.util.CreatePrrsonOrEquipment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by colin on 2018/11/21 15:05 .
 * 技术人员功能模块的2.2.1~2.2.7的人员列表界面，和所有人统计
 */
public class TechnicistList extends AppCompatActivity {
	@BindView(R.id.title)//标题
		TextView mTitle;
	@BindView(R.id.totalPoint)//总分
		TextView mTotalPoint;
	@BindView(R.id.yesCredential)//有职称或资格证书、无职称或资格证书
		TextView mYesCredential;
	@BindView(R.id.noCredential)
	TextView mNoCredential;
	@BindView(R.id.yesSecurity)//有社保、无社保
		TextView mYesSecurity;
	@BindView(R.id.noSecurity)
	TextView mNoSecurity;
	@BindView(R.id.pass)//考核通过、考核不通过
		TextView mPass;
	@BindView(R.id.notPass)
	TextView mNotPass;
	@BindView(R.id.yesCertificate)//有证书、无证书
		TextView mYesCertificate;
	@BindView(R.id.noCertificate)
	TextView mNoCertificate;
	@BindView(R.id.yesCultivate)//有培训证书、无培训证书
		TextView mYesCultivate;
	@BindView(R.id.noCultivate)
	TextView mNoCultivate;
	@BindView(R.id.yesPact)//有劳动合同、无劳动合同
		TextView mYesPact;
	@BindView(R.id.noPact)
	TextView mNoPact;
	@BindView(R.id.tome)//在册、不在册
		TextView mTome;
	@BindView(R.id.notTome)
	TextView mNotTome;
	@BindView(R.id.listView)//人员和个人得分列表
		ListView mListView;
	@BindView(R.id.scrollView)
	ScrollView mScrollView;
	@BindView(R.id.create)//创建人员
		Button mCreate;

	String eid, cid, item, title;
	TechnicistListAdapter adapter;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_technicist_list);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		ButterKnife.bind(this);

		initData();
	}

	/**
	 * 初始化
	 */
	private void initData() {
		eid = Common.getEid();
		cid = String.valueOf(Common.getWid());
		item = getIntent().getStringExtra("item");
		title = getIntent().getStringExtra("title");
		CreatePrrsonOrEquipment.person(mCreate, this, Integer.valueOf(eid), Integer.valueOf(cid), item);//创建人员
		List<PersonnelJson> personnel = PersonnelDao.query(eid, cid, item);// 人员表
		List<CaTestJson> test = CaTestDao.query(cid, item);// 打分表
		List<CaConfigJson> config = CaConfigDao.query(item);// 配置表
		String configPoint = String.valueOf(config.get(0).getEACH());// 个人分值
		String configExplain = config.get(0).getMEMO();// 扣分说明

		mTitle.setText(item + title);//标题
		// 获取人员名单的数据
		int yesCredential = 0;        // 有职称或资格证书
		int noCredential = 0;        // 无职称或资格证书
		int yesCredential2 = 0;        // 有证书
		int noCredential2 = 0;        // 无证书
		int yesCultivate = 0;        // 有培训证书
		int noCultivate = 0;        // 无培训证书
		int yesSecurity = 0;        // 有社保
		int noSecurity = 0;            // 无社保
		int yesPact = 0;            // 有劳动合同
		int noPact = 0;                // 无劳动合同
		int pass = 0;                // 考核通过
		int notPass = 0;            // 考核不通过
		int tome = 0;                // 在册
		int notTome = 0;            // 不在册
		double totalPoint = 0;        //总分

		if (personnel.size() > 0) {
			for (int i = 0; i < personnel.size(); i++) {
				if (personnel.get(i).getSCORE() != null) {
					totalPoint = totalPoint + Double.valueOf(personnel.get(i).getSCORE());
				}
				if (personnel.get(i).getWORK_TYPE() == 1) {// 人员工作类型
					if (personnel.get(i).getCHOOSE() != null && !personnel.get(i).getCHOOSE().equals("")) { //所有检查人通过或不通过项统计
						String[] choose = personnel.get(i).getCHOOSE().split(",", -1);
						if (choose[0].equals("1")) { // 社保
							yesSecurity = yesSecurity + 1;
						} else {
							noSecurity = noSecurity + 1;
						}
						if (choose[1].equals("1")) { // 劳动
							yesPact = yesPact + 1;
						} else {
							noPact = noPact + 1;
						}
						if (choose[2].equals("1")) {// 职称或资格证书
							pass = pass + 1;
						} else {
							notPass = notPass + 1;
						}
						if (choose[2].equals("1")) {// 职称或资格证书
							yesCredential = yesCredential + 1;
						} else {
							noCredential = noCredential + 1;
						}
					}
				} else if (personnel.get(i).getWORK_TYPE() == 2) {
					if (personnel.get(i).getCHOOSE() != null && !personnel.get(i).getCHOOSE().equals("")) { //所有检查人通过或不通过项统计
						String[] choose = personnel.get(i).getCHOOSE().split(",", -1);
						if (choose[0].equals("1")) { // 社保
							yesSecurity = yesSecurity + 1;
						} else {
							noSecurity = noSecurity + 1;
						}
						if (choose[1].equals("1")) { // 劳动
							yesPact = yesPact + 1;
						} else {
							noPact = noPact + 1;
						}
						if (choose[2].equals("1")) {// 考核满意
							pass = pass + 1;
						} else {
							notPass = notPass + 1;
						}
						if (choose[3].equals("1")) {// 有证书
							yesCredential2 = yesCredential2 + 1;
						} else {
							noCredential2 = noCredential2 + 1;
						}
						if (choose[4].equals("1")) {// 培训证书
							yesCultivate = yesCultivate + 1;
						} else {
							noCultivate = noCultivate + 1;
						}
					}
				} else {
					if (personnel.get(i).getCHOOSE() != null && !personnel.get(i).getCHOOSE().equals("")) { //所有检查人通过或不通过项统计
						String[] choose = personnel.get(i).getCHOOSE().split(",", -1);
						if (choose[0].equals("1")) { // 在册
							tome = tome + 1;
						} else {
							notTome = notTome + 1;
						}
						if (choose[1].equals("1")) { // 社保
							yesSecurity = yesSecurity + 1;
						} else {
							noSecurity = noSecurity + 1;
						}
						if (choose[2].equals("1")) {// 劳动
							yesPact = yesPact + 1;
						} else {
							noPact = noPact + 1;
						}
					}
				}
			}
		}

		mTotalPoint.setText(String.valueOf(totalPoint)); // 总得分
		mYesCredential.setText(String.valueOf(yesCredential));// 有职称或资格证书
		mNoCredential.setText(String.valueOf(noCredential));// 无职称或资格证书
		mYesSecurity.setText(String.valueOf(yesSecurity));// 有社保
		mNoSecurity.setText(String.valueOf(noSecurity));// 无社保
		mPass.setText(String.valueOf(pass));// 考核通过
		mNotPass.setText(String.valueOf(notPass));// 考核不通过
		mYesCertificate.setText(String.valueOf(yesCredential2));// 有证书
		mNoCertificate.setText(String.valueOf(noCredential2));// 无证书
		mYesCultivate.setText(String.valueOf(yesCultivate));// 有培训证书
		mNoCultivate.setText(String.valueOf(noCultivate));// 无培训证书
		mYesPact.setText(String.valueOf(yesPact));// 有劳动合同
		mNoPact.setText(String.valueOf(noPact));// 无劳动合同
		mTome.setText(String.valueOf(tome));// 在册
		mNotTome.setText(String.valueOf(notTome));// 不在册

		adapter = new TechnicistListAdapter(this, personnel);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Common.setPersonnelJson(personnel.get(position));
				//Common.setCaTestJson(test.get(position));
				//Common.setConfigPoint(configPoint);
				//.setConfigExplain(configExplain);
				Intent intent;
				//分3种打分页面展示，根据人的work_type
				int workType = personnel.get(position).getWORK_TYPE();
				if (workType == 1) {
					intent = new Intent(TechnicistList.this, Description2.class);
				} else if (workType == 2) {
					intent = new Intent(TechnicistList.this, Description2P.class);
				} else {
					intent = new Intent(TechnicistList.this, Description3.class);
				}
				intent.putExtra("personnel", personnel.get(position));//人员
				if (test.size() > position && position <= test.size() - 1) {
					intent.putExtra("test", test.get(position));//打分表  要传实体类，需要在实体类实现Serializable接口生成serialVersionUID
				}
				intent.putExtra("configPoint", configPoint);//个人分值
				intent.putExtra("configExplain", configExplain);//扣分说明
				intent.putExtra("item", item);//item
				intent.putExtra("title", title);//item
				startActivity(intent);
			}
		});

		//用于解决scrollView嵌套list导致list无法点击的问题
		mListView.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_MOVE) {
					mScrollView.requestDisallowInterceptTouchEvent(true);
				}
				return false;
			}
		});
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
	 * 返回此页面时刷新数据
	 */
	@Override
	protected void onResume() {
		super.onResume();
		initData();
	}
}
