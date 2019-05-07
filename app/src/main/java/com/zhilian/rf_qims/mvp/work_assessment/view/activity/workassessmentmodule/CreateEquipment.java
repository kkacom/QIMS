package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.entity.EntEquipmentDao;
import com.zhilian.rf_qims.entity.EntEquipmentJson;
import com.zhilian.rf_qims.util.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by colin on 2018/12/4 10:14 .
 */
public class CreateEquipment extends AppCompatActivity {
	@BindView(R.id.name)// 设备名称
	EditText mName;
	@BindView(R.id.card)// 设备编号
	EditText mCard;
	@BindView(R.id.specification)// 设备规格
	EditText mSpecification;
	@BindView(R.id.save)// 保存
	Button mSave;

	int eid, equipment;
	String name, card, specification, item, cid;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_equipment);
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
		cid = String.valueOf(getIntent().getIntExtra("cid",0));
		item = getIntent().getStringExtra("item");
		if (eid == 0 || cid.equals("0")){
			ToastUtils.show("获取企业id异常,请返回");
			return;
		}
		//equipment_type = CaConfigDao.query(item).get(0).getCOMMON_TYPE();//获取加载类别

		//保存
		mSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				name = String.valueOf(mName.getText());
				card = String.valueOf(mCard.getText());
				if (!name.equals("") && !card.equals("")){
					specification = String.valueOf(mSpecification.getText());
					List<EntEquipmentJson> list = EntEquipmentDao.queryJudgeEquipment(cid, item, card);
					if (list.size() > 0){
						ToastUtils.show("已存在此设备");
					}else {
						EntEquipmentJson personnelJson = new EntEquipmentJson(eid, cid, item, name, card, specification, 0);
						EntEquipmentDao.insertOrReplace(personnelJson);
						ToastUtils.show("保存成功");
					}
				}else {
					ToastUtils.show("设备名或设备编号不能为空");
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
