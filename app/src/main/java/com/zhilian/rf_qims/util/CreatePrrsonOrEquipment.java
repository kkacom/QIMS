package com.zhilian.rf_qims.util;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule.CreateEquipment;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule.CreatePerson;

/**
 * Created by colin on 2018/12/4 10:00 .
 * 从业评估创建人员or设备
 */
public class CreatePrrsonOrEquipment {
	//创建人员
	public static void person(Button button, Activity activity, int eid, int cid, String item){
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, CreatePerson.class);
				intent.putExtra("eid", eid);
				intent.putExtra("cid", cid);
				intent.putExtra("item", item);
				activity.startActivity(intent);
			}
		});
	}

	//创建设备
	public static void equipment(Button button, Activity activity, int eid, int cid, String item){
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, CreateEquipment.class);
				intent.putExtra("eid", eid);
				intent.putExtra("cid", cid);
				intent.putExtra("item", item);
				activity.startActivity(intent);
			}
		});
	}
}
