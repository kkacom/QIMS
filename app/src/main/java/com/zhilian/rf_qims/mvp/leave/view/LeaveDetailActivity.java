package com.zhilian.rf_qims.mvp.leave.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhilian.rf_qims.api.InQueryMsg;
import com.zhilian.rf_qims.api.InSaveMsg;
import com.zhilian.rf_qims.api.JsonStringRequest;
import com.zhilian.rf_qims.util.JsonUtil;
import com.zhilian.rf_qims.api.ParaMap;
import com.zhilian.rf_qims.util.RequestUtil;
import com.zhilian.rf_qims.api.Sign;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.adapter.MySpinnerAdapter;
import com.zhilian.rf_qims.adapter.SelectmanAdapter;
import com.zhilian.rf_qims.base.BaseActivityOA;
import com.zhilian.rf_qims.common.BusinessContant;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.json.T_Selectman;
import com.zhilian.rf_qims.listener.OnItemSelectedListenerImpl;
import com.zhilian.rf_qims.mvp.leave.presenter.LeaveDetailPresenter;
import com.zhilian.rf_qims.widget.CustomPopDialog;
import com.zhilian.rf_qims.widget.DatePickerFragment;
import com.zhilian.rf_qims.widget.EditFragment;
import com.zhilian.rf_qims.util.CacheUtil;
import com.zhilian.rf_qims.util.DateUtil;
import com.zhilian.rf_qims.util.LogUtil;
import com.zhilian.rf_qims.util.OpinionUtil;
import com.zhilian.rf_qims.util.StrKit;
import com.zhilian.rf_qims.bean.LeaveDetailBean;
import com.zhilian.rf_qims.util.RxHttpUtil;
import com.zhilian.rf_qims.constant.Constants;
import com.zhilian.rf_qims.constant.LocalConstants;
import com.zhilian.rf_qims.util.WorkflowUtil;
//import com.zhilian.rf_qims.base.HttpBase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017-12-29.
 */

public class LeaveDetailActivity extends BaseActivityOA implements ILeaveDetailView {
	@BindView(R.id.iv_back)
	ImageView mIvBack;
	@BindView(R.id.tv_title)
	TextView mTvTitle;
	@BindView(R.id.uname)
	TextView mUname;
	@BindView(R.id.dname)
	TextView mDname;
	@BindView(R.id.type)
	Spinner mType;
	@BindView(R.id.married)
	TextView mMarried;
	@BindView(R.id.approvedate)
	TextView mApprovedate;
	@BindView(R.id.sp_bm)
	Spinner mBm;
	@BindView(R.id.sp_em)
	Spinner mEm;
	@BindView(R.id.dayt)
	TextView mDayt;

	@BindView(R.id.begindate)
	TextView mBegindate;
	@BindView(R.id.enddate)
	TextView mEnddate;
	@BindView(R.id.userdate)
	TextView mUserdate;

	/*  @BindView(R.id.fj_list)
	  NoScrollListView mFjList;*/
	@BindView(R.id.backdate)
	TextView mBackdate;
	@BindView(R.id.days)
	TextView mDays;
	@BindView((R.id.qtxj))
	TextView qtxj;
	@BindView(R.id.bt_submit)
	Button mBtSubmit;
	@BindView(R.id.bt_save)
	Button mBtSave;

	@BindView(R.id.bt_return) //返回
	Button mBtReturn;
	@BindView(R.id.bt_undo) //撤销
	Button mBtUndo;
   /* @BindView(R.id.bt_update)
	Button mBtUpdate;*/

	@BindViews({R.id.bt_reason, R.id.bt_opinion1, R.id.bt_opinion2})
	List<Button> mWriteBtns;

	@BindViews({R.id.bt_save, R.id.bt_submit, R.id.bt_return})
	List<Button> mSubmitBtns;

	@BindViews({R.id.reason, R.id.opinion1, R.id.opinion2})
	List<TextView> mWriteEts;
	@BindView(R.id.history)
	TextView mHistory;
	@BindView(R.id.njqk)
	TextView mNjqk;
	/**
	 * 以下内容来自 copy
	 */
	BusinessContant bc = new BusinessContant();
	Common common = new Common();
	SelectmanAdapter selectmanAdapter;
	String tempopinion = "";
	private int task = 0;
	private LeaveDetailBean leave;
	private LeaveDetailPresenter mPresenter;
	private String fileName = "leave";
	private int index;
	private String[] dayTypes = {"全天", "上午", "下午"};
	private String docid;
	private String isdone;
	private TextView tv_title;
	private RadioGroup radioGroup;
	private ListView mansListview;
	private RadioButton tempButton;
	private List<T_Selectman> selectmenlist = new ArrayList<T_Selectman>();
	private String atype;
	private AlertDialog alertDialog;
	private String checked_id;
	public CustomPopDialog mCustomPopDialog;

	@Override
	public void initData() {
		BusinessContant.text = "撤销中...";
        mCustomPopDialog = new CustomPopDialog(this, R.style.CustomDialog);

		task = getIntent().getIntExtra("task", 0);
		mPresenter = new LeaveDetailPresenter(this);
		docid = getIntent().getStringExtra("docid");
		index = getIntent().getIntExtra("index", 0);
		isdone = getIntent().getStringExtra("isdone");
		switch (task) {
			case Constants.TASK_NEW:
				mPresenter.getLeaveDetail(docid, isdone);
			case Constants.TASK_TODO:
				if (!"0".equals(docid)) {
					try {
						leave = new CacheUtil(getApplicationContext(), fileName).
							getObject(LocalConstants.USER_NAME + "-" + docid, LeaveDetailBean.class);
						updateView();
						switchDisplayBtns();
					} catch (Exception e) {
						//	LogUtil.e(ErrorConstants.ERROR_LOAD_CACHE);
						mPresenter.getLeaveDetail(docid, isdone);
					}
				}
				break;
			case Constants.TASK_DONE:
				mPresenter.getLeaveDetail(docid, isdone);
				break;
		}

	}

	@Override
	public void setApplyDate(String date) {
		mApprovedate.setText(date);
		leave.setApprovedate(date);
	}

	@Override
	public void setBeginDate(String date) {
		if (StrKit.notBlank(date)){
			if (StrKit.notBlank(leave.getEnddate())){
				try {
					if (DateUtil.checkDate(date,leave.getEnddate())){
						leave.setBegindate(date);
						mBegindate.setText(date);
						mPresenter.getLeaveDayt(leave.getBegindate(), leave.getBm(), leave.getEnddate(), leave.getEm());
					} else {
						Toast.makeText(this, "结束日期小于开始日期", Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					mBegindate.setText("");
					leave.setBegindate("");
					Toast.makeText(this, e.getCause().getMessage(), Toast.LENGTH_SHORT).show();
				}
			} else {
				leave.setBegindate(date);
				mBegindate.setText(date);
			}
		}
	}

	@Override
	public void setEndDate(String date) {
		if (StrKit.notBlank(leave.getBegindate())) {
			try {
				if (DateUtil.checkDate(leave.getBegindate(), date)) {
					leave.setEnddate(date);
					mEnddate.setText(date);
					mPresenter.getLeaveDayt(leave.getBegindate(), leave.getBm(), leave.getEnddate(), leave.getEm());
				} else {
					Toast.makeText(this, "结束日期小于开始日期", Toast.LENGTH_SHORT).show();
				}
			} catch (Exception e) {
				mEnddate.setText("");
				leave.setEnddate("");
				Toast.makeText(this, e.getCause().getMessage(), Toast.LENGTH_SHORT).show();
			}
		}else {
			leave.setEnddate(date);
			mEnddate.setText(date);
			Toast.makeText(this, "请选择开始日期", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void setBackDate(String date) {
		mBackdate.setText(date);
		leave.setBackdate(date);
	}


	@Override
	public void setUserDate(String date) {
		if (StrKit.notBlank(leave.getUserDate())) {
			try {
				if (DateUtil.checkDate(leave.getUserDate(), date)) {
					leave.setUserDate(date);
					mUserdate.setText(date);
				} else {
					Toast.makeText(this, "请选择用车时间", Toast.LENGTH_SHORT).show();

				}
			} catch (Exception e) {
				mUserdate.setText("");
				leave.setUserDate("");
				Toast.makeText(this, e.getCause().getMessage(), Toast.LENGTH_SHORT).show();
			}
		}else {
			leave.setUserDate(date);
			mUserdate.setText(date);
			Toast.makeText(this, "请选择用车时间", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected int layoutRes() {
		return R.layout.activity_leave_detail;
	}

	@Override
	protected void initView() {

	}

	@Override
	protected void clearRes() {

	}

	@OnClick(R.id.iv_back)
	public void onMIvBackClicked() {
		setResult(task);
		finish();
	}

	@OnClick(R.id.bt_submit)
	public void onMBtSubmitClicked() {
		submit();
		setResult(Constants.SUBMIT);
		//finish();
	}

	@OnClick(R.id.bt_save)
	public void onMBtSaveClicked() {
		//LogUtil.e("保存的类型："+leave.getType());
		leave.setDayt(mDayt.getText().toString().trim());
		new CacheUtil(this, fileName).saveObject(LocalConstants.USER_NAME + "-" + leave.getId(), leave);
		finish();
	}

	@OnClick(R.id.bt_undo)
	public void onUndoClicked() {
		mCustomPopDialog.show();
		mPresenter.deleteApply(leave, this);
	}

	@OnClick({R.id.approvedate, R.id.begindate, R.id.enddate})
	public void onSelectDateClicked(View view) {
		String tempStr = "";
		if (null != leave) {
			switch (view.getId()) {
				case R.id.approvedate:
					tempStr = leave.getApprovedate();
					break;
				case R.id.begindate:
					tempStr = leave.getBegindate();
					break;
				case R.id.enddate:
					tempStr = leave.getEnddate();
					break;
			}
			DatePickerFragment dialog = new DatePickerFragment(this, view.getId(), tempStr);
			dialog.show(getSupportFragmentManager(), "date picker");
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (task == Constants.TASK_NEW) {
				if (null != leave) {
					new CacheUtil(this, fileName).saveObject(LocalConstants.USER_NAME + "-" + leave.getId(), leave);

				}
			}
			setResult(task);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 显示填写的请假原因
	 *
	 * @param reason
	 */
	@Override
	public void onReasonChanged(String reason) {
		mWriteEts.get(0).setText(reason);
		leave.setReason(reason);
		if (StrKit.notBlank(reason)) {
			mSubmitBtns.get(0).setVisibility(View.VISIBLE);
			mSubmitBtns.get(1).setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 显示填写的审核意见
	 *
	 * @param opinion
	 */
	@Override
	public void onOpinion1Changed(String opinion) {
		mWriteEts.get(1).setText(OpinionUtil.getOpinion("", opinion, LocalConstants.USER_NAME));
		leave.setOpinion1(opinion);
		if (StrKit.notBlank(opinion)) {
			mSubmitBtns.get(1).setVisibility(View.VISIBLE);
			if (leave.getBacklaststep().equals("1")) {
				mSubmitBtns.get(2).setVisibility(View.VISIBLE);
			}
		}
		mPresenter.saveOpinion(Constants.SAVE_OPINION, leave);
	}

	/**
	 * 显示填写的审批意见	 *
	 *
	 * @param opinion
	 */
	@Override
	public void onOpinion2Changed(String opinion) {
		leave.setOpinion2(opinion);
		mWriteEts.get(2).setText(opinion);
		if (StrKit.notBlank(opinion)) {
			mSubmitBtns.get(1).setVisibility(View.VISIBLE);
			if (leave.getBacklaststep().equals("1")) {
				mSubmitBtns.get(2).setVisibility(View.VISIBLE);
			}
		}
		mPresenter.saveOpinion("editopinion", leave);
	}

	/**
	 * 显示加载成功的请假详情
	 *
	 * @param bean
	 */
	@Override
	public void onGetLeaveDetailSuccess(LeaveDetailBean bean) {
		leave = bean;
		updateView();
		switchDisplayBtns();
	}

	/**
	 * 显示保存意见成功时的返回结果
	 *
	 * @param result
	 */
	@Override
	public void onSaveOpinionSuccess(String result) {
		Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 显示保存意见失败时的返回结果
	 *
	 * @param result
	 */
	@Override
	public void onSaveOpinionFailure(String result) {
		Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 更新请假天数
	 *
	 * @param dayt
	 */
	@Override
	public void updateDayt(String dayt) {
		mDayt.setText(dayt);
		leave.setDayt(dayt);
	}

	@Override
	public void onDisconnected(String message) {
		if (message.equals(Constants.RESPONSE_ERROR)) {
			//TODO 重新获取key（重新登陆),并请求数据
		} else {
			//Toast.makeText(this, Constants.LEAVE_DETAIL_ERROR, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onDeleted() {
		Intent intent = new Intent();
		intent.putExtra("index", index);
		setResult(Constants.TASK_TODO, intent);
		mCustomPopDialog.dismiss();
		finish();
	}

	/**
	 * 更新页面数据
	 */
	private void updateView() {
		String uname = leave.getUname();
		String dname = leave.getDname();
		String married = leave.getMarried();
		String type = leave.getType();
		String approvedate = leave.getApprovedate();
		String dayt = leave.getDayt();
		String begindate = leave.getBegindate();
		String enddate = leave.getEnddate();
		String reason = leave.getReason();
		String opinion1 = leave.getOpinion1();
		String opinion2 = leave.getOpinion2();
		String backdate = leave.getBackdate();
		String days = leave.getDays();
		String daye = leave.getDaye();
		String workyear = leave.getWorkyear();
		String dayc = leave.getDayc();
		String bm = leave.getBm();
		String em = leave.getEm();
		String dayn = leave.getDayn();
		String dayz = leave.getDayz();
		qtxj.setText(dayz);
		mType.setAdapter(new MySpinnerAdapter(this, leave.getTypes().toArray(new String[]{})));
		mType.setSelection(leave.getTypes().indexOf(leave.getType()));
		mNjqk.setText("工龄 " + workyear + " 年，可休年假 " + daye + " 天，已休 " + dayn + " 天，剩余 " + dayc + " 天");
		mType.setOnItemSelectedListener(new OnItemSelectedListenerImpl() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//				LogUtil.e("选择的类型："+leave.getTypes().get(i));
				leave.setType(leave.getTypes().get(i));
			}
		});

		if (StrKit.notBlank(dname)) {
			mDname.setText(dname);
		}
		if (StrKit.notBlank(married)) {
			mMarried.setText(married);
		}
		if (StrKit.notBlank(uname)) {
			mUname.setText(uname);
		}
		if (StrKit.notBlank(approvedate)) {
			mApprovedate.setText(approvedate);
		}
		if (StrKit.notBlank(dayt)) {
			mDayt.setText(dayt);
		}
		if (StrKit.notBlank(begindate)) {
			mBegindate.setText(begindate);
		}
		if (StrKit.notBlank(enddate)) {
			mEnddate.setText(enddate);
		} else {

		}
		if (StrKit.notBlank(backdate)) {
			mBackdate.setText(backdate);
		}
		if (StrKit.notBlank(days)) {
			mDays.setText(days);
		}
		if (StrKit.notBlank(reason)) {
			mWriteEts.get(0).setText(reason);
		}
		if (StrKit.notBlank(opinion1)) {
			mWriteEts.get(1).setText(OpinionUtil.getOpinion(opinion1, "", LocalConstants.USER_NAME));
		}
		if (StrKit.notBlank(opinion2)) {
			mWriteEts.get(2).setText(OpinionUtil.getOpinion(opinion2, "", LocalConstants.USER_NAME));
		}

		mBm.setAdapter(new MySpinnerAdapter(this, Constants.DAY_TYPE));
		mEm.setAdapter(new MySpinnerAdapter(this, Constants.DAY_TYPE));
		mBm.setSelection(Arrays.asList(Constants.DAY_TYPE).indexOf(bm));
		mEm.setSelection(Arrays.asList(Constants.DAY_TYPE).indexOf(em));
		mEm.setOnItemSelectedListener(new OnItemSelectedListenerImpl() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				switch (i) {
					case 0://全天
						leave.setEm("全天");
						break;
					case 1://上午
						leave.setEm("上午");
						break;
					case 2://下午
						leave.setEm("下午");
						break;
				}
				if (StrKit.notBlank(leave.getBegindate())) {
					mPresenter.getLeaveDayt(leave.getBegindate(), leave.getBm(), leave.getEnddate(), leave.getEm());
				}
			}
		});
		mBm.setOnItemSelectedListener(new OnItemSelectedListenerImpl() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				switch (i) {
					case 0://全天
						leave.setBm("全天");
						break;
					case 1://上午
						leave.setBm("上午");
						break;
					case 2://下午
						leave.setBm("下午");
						break;
				}
				if (StrKit.notBlank(leave.getEnddate())) {
					mPresenter.getLeaveDayt(leave.getBegindate(), leave.getBm(), leave.getEnddate(), leave.getEm());
				} else {
					if (StrKit.notBlank(leave.getBm())) {
						leave.setDayt("0.5");
					} else {
						leave.setDayt("1.0");
					}
					mDayt.setText(leave.getDayt());
				}
			}
		});

	}

	/**
	 * 响应 填写请假原因、审核意见、审批意见 等Button点击事件
	 *
	 * @param view
	 */
	@OnClick({R.id.bt_reason, R.id.bt_opinion1, R.id.bt_opinion2})
	public void onWriteClicked(View view) {
		new EditFragment(view.getId(),
			String.valueOf(leave.getWf().getId()),
			String.valueOf(leave.getItemid()),
			leave.getOpinions().toArray(new String[]{})).show(getFragmentManager(), "write");
	}

	@OnClick(R.id.bt_return)
	public void onBtReturnClicked() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("确认窗口");
		builder.setMessage(bc.BACK_LAST_STEP);
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				bc.setNexttype("toast");
				selectman("TuiHuiShangBu");
			}
		});
		alertDialog = builder.create();
		alertDialog.show();
	}

	/**
	 * 切换视图中的按钮显示状态
	 */
	private void switchDisplayBtns() {
		String opinionField = leave.getOpinionfield();
		String uname = leave.getUname();
		if (task == LocalConstants.TASK_NEW || task == LocalConstants.TASK_TODO) {
			if (uname.equals(LocalConstants.USER_NAME)) {
				mWriteBtns.get(0).setVisibility(View.VISIBLE);
				mSubmitBtns.get(0).setVisibility(View.VISIBLE);
				mSubmitBtns.get(1).setVisibility(View.VISIBLE);
				mBtUndo.setVisibility(View.VISIBLE);
			} else {
				if (opinionField.equals("opinion1")) {
					mWriteBtns.get(1).setVisibility(View.VISIBLE);
				} else if (opinionField.equals("opinion2")) {
					mWriteBtns.get(2).setVisibility(View.VISIBLE);
				}
				if (StrKit.notBlank(leave.getOpinion1()) || StrKit.notBlank(leave.getOpinion2())) {
					mSubmitBtns.get(1).setVisibility(View.VISIBLE);
					//mBtUndo.setVisibility(View.VISIBLE);
					mSubmitBtns.get(2).setVisibility(View.VISIBLE);
				}
			}
		} else {
			mType.setEnabled(false);
			mEm.setEnabled(false);
			mBm.setEnabled(false);
			mDayt.setKeyListener(null);
			mBegindate.setKeyListener(null);
			mEnddate.setKeyListener(null);
			mApprovedate.setKeyListener(null);
		}
	}

	//TODO 提交
	public void submit() {
		if (StrKit.isBlank(leave.getBegindate())) {
			Toast.makeText(this, "请选择开始时间", Toast.LENGTH_SHORT).show();
			return;
		}
		if (StrKit.isBlank(leave.getReason())) {
			Toast.makeText(this, "请填写请假原因", Toast.LENGTH_SHORT).show();
			return;
		}
		bc.setItem_id("" + leave.getItemid());
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		atype = leave.getAtype();

		if (atype.equals("3")) {//环节类型 3为结束 实现完成操作
			builder.setTitle("确认窗口");
			builder.setMessage("此操作将结束流程，是否继续？");
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				}
			});
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					selectman("WanCheng");
				}
			});
			alertDialog = builder.create();
			alertDialog.show();
		} else {//查找下一处理人或下一环节
			builder.setTitle("下一环节");
			View view = getLayoutInflater().inflate(R.layout.button3_layout, null);
			//stepListView=(ListView)view.findViewById(R.id.steps);
			tv_title = (TextView) view.findViewById(R.id.title);
			radioGroup = (RadioGroup) view.findViewById(R.id.selectman);
			mansListview = (ListView) view.findViewById(R.id.selectmans);

			if (bc.getItem_id() != null) {
				if (Common.judgeNet(this)) {
					Fasong();
					if (StrKit.isBlank(bc.getNexttype()) ? false : bc.getNexttype().equals("toast")) {
						builder.setTitle("下一环节处理人");
					}
				}
				radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub
						tempButton = (RadioButton) findViewById(checkedId); // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
						// 以下就可以对这个RadioButton进行处理了
						checked_id = String.valueOf(checkedId);
						if (bc.getNexttype().equals("toast")) {
							bc.setUserid(checked_id);//当前选中人
						} else {
							bc.setCheckid(checked_id);//选中环节
						}

					}
				});

				builder.setView(view);// 使用自定义布局作为对话框内容
				builder.setPositiveButton("确定", null);

				// 负面语义按钮
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						bc.setCheckid("");
						bc.setUserid("");
					}
				});

				alertDialog = builder.create();
				alertDialog.show();
				alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (com.zhilian.api.StrKit.notBlank(bc.getCheckid())) {
							checked_id = bc.getCheckid();
						}
						if (checked_id != null) {
							selectman("FaSongdoc");
						} else {
							Toast.makeText(LeaveDetailActivity.this, "请选择！", Toast.LENGTH_SHORT).show();
						}

						if (bc.getNexttype().equals("toast")) {
							alertDialog.dismiss();

						} else {
							alertDialog.setTitle("下一环节处理人");
							alertDialog.show();
						}
					}

				});
			}
		}
	}

	public void selectman(String operation1) {
		final String operation = operation1;
		String opinionField = leave.getOpinionfield();
		if (null != opinionField) {
			if (opinionField.equals("opinion1")) {
				tempopinion = mWriteEts.get(1).getText().toString();
			} else if (opinionField.equals("opinion2")) {
				tempopinion = mWriteEts.get(2).getText().toString();
			} else if (opinionField.equals("")) {
				tempopinion = mWriteEts.get(0).getText().toString();
			}
		}
		if (StrKit.notBlank(leave.getOpinionfield()) && StrKit.isBlank(tempopinion)) {
			Toast.makeText(LeaveDetailActivity.this, "请填写意见!", Toast.LENGTH_SHORT).show();
		} else {
			new Thread() {
				public void run() {
					try {
                        String url = RxHttpUtil.initUrl();
						String key = bc.getCONFIRM_ID();
						//String url = bc.URL;
//						String token = "1lj4hbato30kl1ppytwa1ueqdn";
//						final String encodingAesKey = "InVjlo7czsOWrCSmTPgEUXBzlFnmqpNMQU3ZfilULHyHZiRjVUhxxWpexhYH6f4i";
//						Map<String, String> ret = Sign.sign(url, token, encodingAesKey);
//						String signature = ret.get("signature");
//						String nonceStr = ret.get("nonceStr");
//						String timestamp = ret.get("timestamp");
//						Map<String, String> queryParas = ParaMap.create("accessToken", token)
//								.put("nonce", nonceStr)
//								.put("timestamp", timestamp)
//								.put("signature", signature)
//								.getData();
//						url = RequestUtil.buildUrlWithQueryString(url, queryParas);
                        HashMap<String, String> map = new HashMap<>();
                        bc.setPid("" + leave.getWf().getId());
                        map.put("pid", bc.getPid());
                        map.put("doc", "leave");
                        map.put("condition", leave.getDays());
                        String postDate = RxHttpUtil.initQueryParams("query", "fasong", map);

						//new HttpBase(url);
						InSaveMsg insaveMsg = new InSaveMsg(1348831860, "save");
						insaveMsg.setModelName("leavesave");
                        WorkflowUtil workflowUtil=new WorkflowUtil();
                        workflowUtil.HTTPFasong(getApplicationContext(), postDate, url);


						if (/*leave.getAtype().equals("3") || */operation.equals("TuiHuiShangBu")) {//如果为完成操作或是退回操作，传回当前环节
							map.put("nextitemid", leave.getItemid());
							map.put("nexttype", "toast");//要改的
						} else {
							map.put("nextitemid", bc.getCheckid());
						}
						map.put("nexttodoman", bc.getUserid());//选中人的id
						map.put("pid", "" + leave.getWf().getId());
						map.put("approvedate", leave.getApprovedate());
						map.put("begindate", leave.getBegindate());
						map.put("enddate", leave.getEnddate());
						map.put("type", leave.getType());
						map.put("dayt", leave.getDayt());
						map.put("reason", leave.getReason());
						map.put("doc", "leave");
						map.put("bm", leave.getBm());
						map.put("em", leave.getEm());
						map.put("operation", operation);
						map.put("opinionfield", leave.getOpinionfield());
						if (tempopinion.contains(LocalConstants.USER_NAME)) {
							if (tempopinion.contains("\\\\n")) {
								tempopinion = tempopinion.split("\\\\n")[0];
							} else if (tempopinion.contains("<br>")) {
								tempopinion = tempopinion.split("<br>")[0];
							}
						}
						map.put("opinion", tempopinion);
						map.put("flow", "leave");
						if (operation.equals("WanCheng")) {
							map.put("backdate", leave.getBackdate());
							map.put("days", leave.getDays());
						}
						insaveMsg.setModelProperty(map);
						String postData = null;
						ObjectMapper mapper = new ObjectMapper();
						try {
							postData = mapper.writeValueAsString(insaveMsg);
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}

						RequestQueue requestQueue = RequestUtil.getRequestQueue();

						JsonRequest jsonRequest = new JsonStringRequest(Request.Method.POST, url, postData,
							new Response.Listener<String>() {
								@Override
								public void onResponse(String response) {

									try {
										JSONObject dataJson = new JSONObject(response);
										String nexttype = dataJson.getString("nextType");
										String amount = dataJson.getString("type");
										if (nexttype.equals("toast")) {
											Toast.makeText(getApplicationContext(), amount, Toast.LENGTH_LONG).show();
											finish();
										} else {
											JSONArray users = dataJson.getJSONArray("user");
											bc.setNexttype("toast");
											String name, sname, pname;
											int id;
											JSONArray user = dataJson.getJSONArray("user");
											List<T_Selectman> list = JsonUtil.getselectmanList(users.toString());
											radioGroup.removeAllViews();
											for (int i = 0; i < user.length(); i++) {
												id = list.get(i).getId();
												name = list.get(i).getName();
												sname = list.get(i).getD_id();
												pname = list.get(i).getPid();

												if (amount.equals("1")) {
													tempButton = new RadioButton(LeaveDetailActivity.this);

													tempButton.setTextSize(16f);
													tempButton.setText(name + " ( " + pname + " : " + sname + " )");
													tempButton.setPadding(80, 0, 0, 0);                 // 设置文字距离按钮四周的距离
													tempButton.setId(id);
													//radioGroup.addView(tempButton, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
													radioGroup.addView(tempButton);
													if (i == 0) {
														radioGroup.check(tempButton.getId());
														System.out.println("=====");
													}
												} else {
													selectmenlist.add(new T_Selectman(id, name, pname, sname));
												}
											}
											if (!amount.equals("1")) {
												selectmanAdapter = new SelectmanAdapter(selectmenlist, getApplicationContext());
												mansListview.setAdapter(selectmanAdapter);
											}
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
							, new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
							}
						});
						requestQueue.add(jsonRequest);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
		}
	}

	private void Fasong() {
		new Thread() {
			public void run() {
				try {
					String key = bc.getCONFIRM_ID();
					String url = bc.URL;
					String token = "1lj4hbato30kl1ppytwa1ueqdn";
					final String encodingAesKey = "InVjlo7czsOWrCSmTPgEUXBzlFnmqpNMQU3ZfilULHyHZiRjVUhxxWpexhYH6f4i";
					Map<String, String> ret = Sign.sign(url, token, encodingAesKey);
					String signature = ret.get("signature");
					String nonceStr = ret.get("nonceStr");
					String timestamp = ret.get("timestamp");
					Map<String, String> queryParas = ParaMap.create("accessToken", token)
						.put("nonce", nonceStr)
						.put("timestamp", timestamp)
						.put("signature", signature)
						.getData();
					url = RequestUtil.buildUrlWithQueryString(url, queryParas);
					InQueryMsg inQueryMsg = new InQueryMsg(1348831860, "query", key);
					inQueryMsg.setQueryName("fasong");
					HashMap<String, String> map = new HashMap<>();
					map.put("curitemid", bc.getItem_id());//环节ID
					bc.setPid("" + leave.getWf().getId());
					map.put("pid", bc.getPid());
					map.put("doc", "leave");
					map.put("condition", leave.getDayt());
					inQueryMsg.setQueryPara(map);
					String postData = null;
					ObjectMapper mapper = new ObjectMapper();
					try {
						postData = mapper.writeValueAsString(inQueryMsg);
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}

					RequestQueue requestQueue = RequestUtil.getRequestQueue();

					JsonRequest jsonRequest = new JsonStringRequest(Request.Method.POST, url, postData,
						new Response.Listener<String>() {
							@Override
							public void onResponse(String response) {
								try {
									JSONObject dataJson = new JSONObject(response.toString());
									String type = dataJson.getString("type");
									String ato, name;
									int id;
									selectmenlist.clear();
									if (type.equals("9")) {
										Toast.makeText(getApplicationContext(), "没有下一处理人，请联系管理员！", Toast.LENGTH_LONG).show();
									} else if (type.equals("step")) {
										bc.setNexttype("windows");
										JSONArray was = dataJson.getJSONArray("trans");
										List<T_Selectman> list = JsonUtil.getselectmanList(was.toString());
										for (int i = 0; i < list.size(); i++) {

											id = list.get(i).getId();
											ato = list.get(i).getAto();

											tempButton = new RadioButton(LeaveDetailActivity.this);
											tempButton.setText(ato);
											tempButton.setTextColor(0xFF505050);
											tempButton.setTextSize(16f);
											tempButton.setPadding(80, 0, 0, 0);                 // 设置文字距离按钮四周的距离
											tempButton.setId(id);
											tempButton.setText(ato);
											//radioGroup.addView(tempButton, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

											radioGroup.addView(tempButton);
											if (i == 0) {
												radioGroup.check(tempButton.getId());
											}

										}
										//selectmanAdapter=new SelectmanAdapter(selectmenlist,getApplicationContext());
										//stepListView.setAdapter(selectmanAdapter);
										tv_title.setText("下一环节");
									} else {
										bc.setCheckid(dataJson.getString("nextStep"));
										bc.setNexttype("toast");
										JSONArray user = dataJson.getJSONArray("user");
										String amount = dataJson.getString("type");//你跟踪看一下这个值有没有不一样
										String sname, pname;
										List<T_Selectman> list = JsonUtil.getselectmanList(user.toString());
										for (int i = 0; i < user.length(); i++) {
											id = list.get(i).getId();
											name = list.get(i).getName();
											sname = list.get(i).getD_id();
											pname = list.get(i).getPid();

											if (amount.equals("1")) {
												tempButton = new RadioButton(LeaveDetailActivity.this);

												tempButton.setTextSize(16f);
												tempButton.setText(name + " ( " + pname + " : " + sname + " )");
												tempButton.setPadding(80, 0, 0, 0);  // 设置文字距离按钮四周的距离
												tempButton.setTextSize(16f);
												tempButton.setId(id);
												//radioGroup.addView(tempButton, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
												radioGroup.addView(tempButton);
												if (i == 0) {
													radioGroup.check(tempButton.getId());
												}
											} else {
												selectmenlist.add(new T_Selectman(id, name, pname, sname));
											}
										}
										if (!amount.equals("1")) {
											selectmanAdapter = new SelectmanAdapter(selectmenlist, getApplicationContext());
											mansListview.setAdapter(selectmanAdapter);
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}, new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {

							Toast.makeText(getApplicationContext(), "网络异常,请刷新或重新登录!", Toast.LENGTH_LONG).show();
						}
					});
					requestQueue.add(jsonRequest);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}


}
