package com.zhilian.rf_qims.util;

import android.content.Context;
import android.os.Build;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.colin.constant.Constants;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.entity.SampleCheckAlterLog;

import java.util.Map;

import static com.zhilian.rf_qims.util.CommonUtils.getContext;

/**
 * Created by colin on 2019/3/27 8:43 .
 * 改变edit聚焦、增加长按监听
 */
public class UpdateSampleCheckStatu extends DoubleClickListener {

	public UpdateSampleCheckStatu(DoubleClickCallback callback) {
		super(callback);
	}

	//改变是否可聚焦
	public static void UpdateSampleCheckStatu(Boolean value, View view, Context context, Long SampleCheckId, Map<Integer, String> map,
											  Map<Integer, String> serialNumber){
		if (!value){
			view.setFocusable(false);
			view.setFocusableInTouchMode(false);
			editDoubleClickListener(context, SampleCheckId, map, serialNumber, (EditText) view);
		}
	}

	/**设置多个Edit长按监听
	 * 有一个问题，长按触发的粘贴只有在6.0及以上才可屏蔽，
	 * 那么往下的系统就可以通过粘贴改动数据，而不需要通过弹出窗口做日志*/
	public static void editOnLongClickListener(Context context, Long SampleCheckId, Map<Integer, String> map,
											   Map<Integer, String> serialNumber,EditText... editTexts){
		for (EditText editText : editTexts){
			editText.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					if (!editText.isFocusable()){
						DialogUtil.dialog dialogUtil = new DialogUtil.dialog(context);
						dialogUtil.old_value.setText(editText.getText());
						//确定
						dialogUtil.btn_sure.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								if (!String.valueOf(dialogUtil.new_value.getText()).trim().equals("") &&
										!String.valueOf(dialogUtil.desc.getText()).trim().equals("")){

									SampleCheckAlterLog sampleCheckAlterLog = new SampleCheckAlterLog();
									sampleCheckAlterLog.setCheck_time(GetDate.getDateSplit());						//变更时间
									sampleCheckAlterLog.setStatu(0);												//是否需上传标记0未上传
									sampleCheckAlterLog.setApplicant(Constants.userId);								//用户ID
									sampleCheckAlterLog.setSampleCheckId(String.valueOf(SampleCheckId));			//关联本地样品检测
									sampleCheckAlterLog.setCheck_num(editText.getText().toString());				//旧值
									sampleCheckAlterLog.setChange_num(dialogUtil.new_value.getText().toString());	//新值
									sampleCheckAlterLog.setField_name(map.get(editText.getId()));					//变更列名
									sampleCheckAlterLog.setChange_reason(dialogUtil.desc.getText().toString());		//变更原因
									sampleCheckAlterLog.setSerial_number(serialNumber.get(editText.getId()));		//序值
									GreenDaoManager.getInstance().getNewSession().getSampleCheckAlterLogDao().insert(sampleCheckAlterLog);

									editText.setText(dialogUtil.new_value.getText().toString());
									dialogUtil.dialog.dismiss();
								}else {
									ToastUtils.show("请输入新值和描述！");
								}
							}
						});
						//取消
						dialogUtil.btn_cancel.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								dialogUtil.dialog.dismiss();
							}
						});
					}
					return false;
				}
			});

			//设置屏蔽长按弹出粘贴，系统得大于等于23（6.0），所以当小于这个版本的时候会有一个粘贴Bug
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				editText.setCustomInsertionActionModeCallback(new ActionMode.Callback() {
					@Override
					public boolean onCreateActionMode(ActionMode mode, Menu menu) {
						return false;
					}

					@Override
					public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
						return false;
					}

					@Override
					public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
						return false;
					}

					@Override
					public void onDestroyActionMode(ActionMode mode) {

					}
				});
			}
		}
	}

	public static void editDoubleClickListener(Context context, Long SampleCheckId, Map<Integer, String> map,
											   Map<Integer, String> serialNumber, EditText editText){
		editText.setOnTouchListener(new DoubleClickListener(new DoubleClickCallback() {
			@Override
			public void onDoubleClick(View view) {
				DialogUtil.dialog dialogUtil = new DialogUtil.dialog(context);
				dialogUtil.old_value.setText(editText.getText());
				//确定
				dialogUtil.btn_sure.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!String.valueOf(dialogUtil.new_value.getText()).trim().equals("") &&
							!String.valueOf(dialogUtil.desc.getText()).trim().equals("")){

							SampleCheckAlterLog sampleCheckAlterLog = new SampleCheckAlterLog();
							sampleCheckAlterLog.setCheck_time(GetDate.getDateSplit());						//变更时间
							sampleCheckAlterLog.setStatu(0);												//是否需上传标记0未上传
							sampleCheckAlterLog.setApplicant(Constants.userId);								//用户ID
							sampleCheckAlterLog.setSampleCheckId(String.valueOf(SampleCheckId));			//关联本地样品检测
							sampleCheckAlterLog.setCheck_num(editText.getText().toString());				//旧值
							sampleCheckAlterLog.setChange_num(dialogUtil.new_value.getText().toString());	//新值
							sampleCheckAlterLog.setField_name(map.get(editText.getId()));					//变更列名
							sampleCheckAlterLog.setChange_reason(dialogUtil.desc.getText().toString());		//变更原因
							sampleCheckAlterLog.setSerial_number(serialNumber.get(editText.getId()));		//序值
							GreenDaoManager.getInstance().getNewSession().getSampleCheckAlterLogDao().insert(sampleCheckAlterLog);

							editText.setText(dialogUtil.new_value.getText().toString());
							dialogUtil.dialog.dismiss();
						}else {
							ToastUtils.show("请输入新值和描述！");
						}
					}
				});
				//取消
				dialogUtil.btn_cancel.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialogUtil.dialog.dismiss();
					}
				});
			}
		}));
	}

	/**设置多个Edit焦点改变监听*/
	public static void editOnFocusChangeListener(Context context, Long SampleCheckId, Map<Integer, String> map,
												 Map<Integer, String> serialNumber,EditText... editTexts){
		for (EditText editText : editTexts){
			editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (!hasFocus){
						if (!String.valueOf(editText.getText()).equals("")){
							editText.setFocusable(false);
							editText.setFocusableInTouchMode(false);
							editDoubleClickListener(context, SampleCheckId, map, serialNumber, editText);
						}
					}
				}
			});
		}
	}
}
