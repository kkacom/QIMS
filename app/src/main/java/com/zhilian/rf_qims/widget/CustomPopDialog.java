package com.zhilian.rf_qims.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.common.BusinessContant;

/**
 * Created by colin on 2019/1/19 9:33 .
 * 自定义布局进度条弹窗
 */
public class CustomPopDialog extends ProgressDialog {
	public CustomPopDialog(Context context){
		super(context);
	}

	public CustomPopDialog(Context context, int theme){
		super(context, theme);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		init(getContext());
	}

	private void init(Context context){
		//设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
		/*setCancelable(false);
		setCanceledOnTouchOutside(false);*/

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.load_dialog, null);//布局
		TextView info = v.findViewById(R.id.tv_load_dialog);
		info.setText(BusinessContant.text);
		setContentView(v);
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.width = WindowManager.LayoutParams.WRAP_CONTENT;
		params.height = WindowManager.LayoutParams.WRAP_CONTENT;
		getWindow().setAttributes(params);
	}

	@Override
	public void show(){
		super.show();
	}
}
