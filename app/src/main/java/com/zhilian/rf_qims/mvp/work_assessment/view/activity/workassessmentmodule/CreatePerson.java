package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.PersonnelDao;
import com.zhilian.rf_qims.entity.PersonnelJson;
import com.zhilian.rf_qims.util.DialogUtil;
import com.zhilian.rf_qims.util.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by colin on 2018/12/4 10:14 .
 */
public class CreatePerson extends AppCompatActivity {
	@BindView(R.id.name)// 姓名
	EditText mName;
	@BindView(R.id.card)// 身份证
	EditText mCard;
	@BindView(R.id.phone)// 手机号
	EditText mPhone;
	@BindView(R.id.date)// 入职时间text
	TextView mDate;
	@BindView(R.id.layout_date)// 入职时间布局
	LinearLayout mLayoutDate;
	@BindView(R.id.save)// 保存
	Button mSave;

	int eid, cid, work_type;
	String name, card, phone, date, item;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_person);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		ButterKnife.bind(this);

		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		eid = getIntent().getIntExtra("eid", 0);
		cid = getIntent().getIntExtra("cid", 0);
		item = getIntent().getStringExtra("item");
		if (eid == 0 || cid == 0 ){
			ToastUtils.show("获取企业id或项目id异常,请返回");
			return;
		}
		work_type = CaConfigDao.query(item).get(0).getCOMMON_TYPE();//获取加载类别

		// 入职时间
		mLayoutDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogUtil.DateDialog date = new DialogUtil.DateDialog(mDate);
				date.show(getFragmentManager(), "Picker_date");
			}
		});

		//保存
		mSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				name = String.valueOf(mName.getText());
				card = String.valueOf(mCard.getText());
				if (!name.equals("") && !card.equals("")){
					phone = String.valueOf(mPhone.getText());
					date = String.valueOf(mDate.getText());
					List<PersonnelJson> list = PersonnelDao.queryJudgePerson(cid, item, card);
					if (list.size() > 0){
						ToastUtils.show("已存在此人员");
					}else {
						PersonnelJson personnelJson = new PersonnelJson(eid, cid, item, name, card, phone, date, work_type, 0);
						PersonnelDao.insertOrReplace(personnelJson);
						ToastUtils.show("保存成功");
					}
				}else {
					ToastUtils.show("姓名和身份证不能为空");
				}
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
}
