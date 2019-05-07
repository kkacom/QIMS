package com.colin.widget;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;


import com.colin.base.BaseDateView;
import com.colin.utils.DateUtil;
import com.colin.utils.StrKit;

import java.util.Calendar;

/**
 * Created by Administrator on 2018-1-2.
 */

@SuppressLint("ValidFragment")
public class DatePickerFragment<VIEW extends BaseDateView> extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	private VIEW mView;
	private String mDate;
	private int mId;
	private static DatePickerFragment instance = new DatePickerFragment();

	private DatePickerFragment() {

	}

	public static DatePickerFragment getInstance() {
		return instance;
	}

	public DatePickerFragment init(VIEW view, int id, String date) {
		mView = view;
		mId = id;
		mDate = date;
		return instance;
	}

	@Override
	public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
		mView.setDate(mId, DateUtil.formatDate(i, i1 + 1, i2));
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		DatePickerDialog dialog = null;
		if (StrKit.isBlank(mDate)) {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			dialog = new DatePickerDialog(getActivity(), this, year, month, day);
		} else {
			int[] arr = DateUtil.getInstance().date2IntArray(mDate, "-");
			dialog = new DatePickerDialog(getActivity(), this, arr[0], arr[1] - 1, arr[2]);
		}
		return dialog;
	}
}
