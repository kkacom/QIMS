package com.zhilian.rf_qims.mvp.sample_detail.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.pickerview.TimePickerView;
import com.colin.constant.Constants;
import com.colin.listener.TextWatcherImpl;
import com.colin.utils.IDUtil;
import com.colin.utils.SharedPreferencesUtil;
import com.colin.utils.StrKit;
import com.google.gson.Gson;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.adapter.ModelAdapter;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.dao.ModelDao;
import com.zhilian.rf_qims.entity.CheckUser;
import com.zhilian.rf_qims.entity.Company;
import com.zhilian.rf_qims.entity.Model;
import com.zhilian.rf_qims.entity.Project;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.entity.SampleCheck;
import com.zhilian.rf_qims.listener.OnItemSelectedListenerImpl;
import com.zhilian.rf_qims.util.EdUtil;
import com.zhilian.rf_qims.util.GetDate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by colin on 2018/3/12 17:00 .
 */

public class SampleDetailActivity2 extends AppCompatActivity {

	@BindView(R.id.iv_back)
	ImageView ivBack;
	@BindView(R.id.tv_tisamplemodel)
	TextView tvTisamplemodel;
	@BindView(R.id.tv_tisampleno)
	TextView tvTisampleno;
	@BindView(R.id.topbar)
	RelativeLayout topbar;
	@BindView(R.id.tv_page_title)
	TextView tvPageTitle;
	@BindView(R.id.btn_save)
	Button btnSave;
	@BindView(R.id.tv1)
	TextView tv1;
	@BindView(R.id.tv2)
	TextView tv2;
	@BindView(R.id.tv_1_1)
	TextView tv11;
	@BindView(R.id.tv_company)
	TextView tvCompany;
	@BindView(R.id.tv_2_1)
	TextView tv21;
	@BindView(R.id.tv_addr)
	TextView tvAddr;
	@BindView(R.id.tv_3_1)
	TextView tv31;
	@BindView(R.id.sp_type)
	Spinner spType;
	@BindView(R.id.tv_4_1)
	TextView tv41;
	@BindView(R.id.tv_samplemodel)
	EditText tvSamplemodel;
	@BindView(R.id.tv_5_1)
	TextView tv51;
	@BindView(R.id.tv_madeno)
	EditText tvMadeno;
	@BindView(R.id.tv_sitenumber)
	EditText mTvSitenumber;
	@BindView(R.id.tv_6_1)
	TextView tv61;
	@BindView(R.id.tv_madedate)
	TextView tvMadedate;
	@BindView(R.id.tv_7_1)
	TextView tv71;
	@BindView(R.id.tv_installaddr)
	EditText tvInstalladdr;
	@BindView(R.id.tv_8_1)
	TextView tv81;
	@BindView(R.id.tv_installdate)
	TextView tvInstalldate;
	@BindView(R.id.tv_9_1)
	TextView tv91;
	@BindView(R.id.tv_sampleno)
	EditText tvSampleno;
	@BindView(R.id.tv_10_1)
	TextView tv101;
	@BindView(R.id.tv_checkdate)
	TextView tvCheckdate;
	@BindView(R.id.tv_11_1)
	TextView tv111;
	@BindView(R.id.tv_temperature)
	EditText tvTemperature;
	@BindView(R.id.tv_12_1)
	TextView tv121;
	@BindView(R.id.tv_humidity)
	EditText tvHumidity;
	@BindView(R.id.tv_13_1)
	TextView tv131;
	@BindView(R.id.tv_desc)
	EditText tvDesc;
	@BindView(R.id.tv_mainuser)
	TextView tvMainUser;
	@BindView(R.id.tv_fuuser)
	TextView tvFuUser;
	TimePickerView pvTime;
	int timetype = 0;
	String sampletype = "";
	Company company;
	Project project;
	//    int status;//1更新，0新建
	PopupWindow popupWindow;
	EditText mEtSearch;
	ImageView mIvDel;
	TextView mTvSearch;
	ListView mLvModel;
	List<Company> mCompanies;
	TextView mTvCancel;
	ModelAdapter modelAdapter;
	List<Model> models = new ArrayList<>();
	int touch_flag = 0;
	private int mWidthPixels;
	private int mHeightPixels;
	private String samplemodel = "#";
	Sample sample;
	private int mainuserid = -1;
	private String fuuserid = "";
	List<CheckUser> checkUsers;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample_detail2);
		ButterKnife.bind(this);

		initView();
	}

	private void initView() {
		sample = new Sample();
		models = GreenDaoManager.getInstance().getNewSession().getModelDao().loadAll();
		modelAdapter = new ModelAdapter(models, SampleDetailActivity2.this);
		spType.setOnItemSelectedListener(new OnItemSelectedListenerImpl() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (position == 1) {
					sampletype = "TM";
				} else if (position == 2) {
					sampletype = "GM";
				} else if (position == 3) {
					sampletype = "TX";
				} else if (position == 4) {
					sampletype = "GX";
				} else {
					sampletype = "";
				}
			}
		});

		pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
		pvTime.setTime(new Date());
		pvTime.setCyclic(false);
		pvTime.setCancelable(true);
		//时间选择后回调
		pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

			@Override
			public void onTimeSelect(Date date) {
				if (timetype == 0) {
					tvMadedate.setText(getTime(date));
				} else if (timetype == 1) {
					tvInstalldate.setText(getTime(date));
				} else if (timetype == 2) {
					tvCheckdate.setText(getTime(date));
				}
			}
		});
		tvCheckdate.setText(GetDate.getDateLine());
		project = (Project) getIntent().getSerializableExtra("project");
		SharedPreferencesUtil util = new SharedPreferencesUtil(this, Constants.XML_NAME);
		String strcom = util.getObject("company");
		company = new Gson().fromJson(strcom, Company.class);
		tvCompany.setText(company.getName());
		tvAddr.setText(project.getItemName());
		if (project.getSampleSource().equals("2")) { // 项目样品来源是安装现场，则样品安装位置为()~()×()~()方便填写
			tvInstalladdr.setText("()~()×()~()");
		}
		tvSamplemodel.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				touch_flag++;
				if (touch_flag == 1) {
					showListModels();
					touch_flag = 0;
				}
				return false;
			}
		});
		tvSamplemodel.addTextChangedListener(new TextWatcherImpl() {
			@Override
			public void afterTextChanged(Editable s) {
//                updateSampleDesign(tvSamplemodel.getText().toString().trim());
				if (samplemodel.equals(tvSamplemodel.getText().toString().trim())) {
					return;
				}
				Log.d("samplemodel", s.toString());
				models.clear();
				models = GreenDaoManager.getInstance()
					.getNewSession()
					.getModelDao()
					.queryBuilder()
					.where(ModelDao.Properties.Model_number.like("%" + tvSamplemodel.getText().toString().trim() + "%"))
					.list();
				if (models.size() == 0) {
					popupWindow.dismiss();
				} else {
					if (!popupWindow.isShowing()) {
						popupWindow.setFocusable(false);
						popupWindow.setBackgroundDrawable(new BitmapDrawable());
						popupWindow.setOutsideTouchable(true);
						popupWindow.showAsDropDown(tvSamplemodel);
					}
					mLvModel.setAdapter(new ModelAdapter(models, SampleDetailActivity2.this));
				}

			}
		});
		checkUsers = GreenDaoManager.getInstance().getNewSession().getCheckUserDao().loadAll();
		String[] items = new String[checkUsers.size()];
		for (int i = 0; i < checkUsers.size(); i++) {
			items[i] = checkUsers.get(i).getNAME();
		}
		boolean[] checkedItems = new boolean[checkUsers.size()];
		for (int i = 0; i < checkUsers.size(); i++) {
			checkedItems[i] = false;
		}
		tvMainUser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(SampleDetailActivity2.this);
				builder.setIcon(R.mipmap.icon_logo);
				builder.setTitle("请选择主检人员");
				builder.setCancelable(false);
				builder.setSingleChoiceItems(items, mainuserid, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SampleDetailActivity2.this,"您选择了:"+items[which],Toast.LENGTH_SHORT).show();
						tvMainUser.setText(items[which]);
						mainuserid = checkUsers.get(which).getID().intValue();
					}
				});
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SampleDetailActivity2.this,"您点击了"+which,Toast.LENGTH_SHORT).show();

					}
				});
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						tvMainUser.setText("");
						mainuserid = -1;
					}
				});
				builder.show();
			}
		});
		tvFuUser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				fuuserid = "";

				AlertDialog.Builder builder = new AlertDialog.Builder(SampleDetailActivity2.this);
				builder.setIcon(R.mipmap.icon_logo);
				builder.setTitle("请选择辅检人员");
				builder.setCancelable(false);
				builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which, boolean isChecked) {
						checkedItems[which] = isChecked;
					}
				});
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String text = "";
						//记录多选对话框中用户选中的条目
						for (int i = 0; i < checkUsers.size(); i++) {
							text += checkedItems[i] ? items[i] + "," : "";
							if (checkedItems[i]) {
								fuuserid += checkUsers.get(i).getID() + ",";
							}
						}
						tvFuUser.setText(text.substring(0, text.length() - 1));
						fuuserid = fuuserid.substring(0, fuuserid.length() - 1);
						Log.d("hello", fuuserid);
					}
				});
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						for (int i = 0; i < checkUsers.size(); i++) {
							checkedItems[i] = false;
						}
						tvFuUser.setText("");
						fuuserid = "";
					}
				});
				builder.show();
			}
		});

	}

	private void showListModels() {
		if (popupWindow == null) {
			View contentView = LayoutInflater.from(SampleDetailActivity2.this).inflate(R.layout.dialog_model, null);
			mLvModel = (ListView) contentView.findViewById(R.id.lv_model);
			mLvModel.setAdapter(modelAdapter);
			mLvModel.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
			popupWindow = new PopupWindow(contentView);
			popupWindow.setWidth(tvSamplemodel.getWidth());
			popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
			mLvModel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					if (popupWindow != null)
						popupWindow.dismiss();
					samplemodel = models.get(position).getModel_number();
					tvSamplemodel.setText(models.get(position).getModel_number());
				}
			});
		}
		//设置弹出窗体需要软键盘，
		popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
//再设置模式，和Activity的一样，覆盖，调整大小。
		popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		popupWindow.setFocusable(false);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setOutsideTouchable(true);

//        int[] location = new int[2];
//        tvSampleno.getLocationOnScreen(location);
//        popupWindow.showAtLocation(tvSampleno, Gravity.NO_GRAVITY, location[0], location[1] + tvSampleno.getHeight());
//        popupWindow.getContentView().measure(0,0);
//        int popHeight = popupWindow.getContentView().getMeasuredHeight();
		popupWindow.showAsDropDown(tvSamplemodel);
//        popupWindow.showAtLocation(tvSampleno, Gravity.NO_GRAVITY, location[0], location[1] + tvSampleno.getHeight());

	}


	@OnClick({R.id.tv_madedate, R.id.tv_installdate, R.id.tv_checkdate, R.id.iv_back, R.id.btn_save})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.iv_back:
				setResult(0x12);
				finish();
				break;
			case R.id.btn_save:
				save();
				break;
			case R.id.tv_madedate:
				timetype = 0;
				pvTime.show();
				break;
			case R.id.tv_installdate:
				timetype = 1;
				pvTime.show();
				break;
			case R.id.tv_checkdate:
				timetype = 2;
				pvTime.show();
				break;
		}
	}

	private void save() {
		if (tvSamplemodel.getText().toString().trim().equals("")) {
			Toast.makeText(SampleDetailActivity2.this, "样品型号不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		if (mTvSitenumber.getText().toString().trim().equals("")) {
			Toast.makeText(SampleDetailActivity2.this, "现场编号不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		if (tvCheckdate.getText().toString().trim().equals("")) {
			Toast.makeText(SampleDetailActivity2.this, "检验日期不能为空", Toast.LENGTH_SHORT).show();
			return;
		}

		if (sampletype.equals("")) {//不能为空
			Toast.makeText(SampleDetailActivity2.this, "样品类型不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		sample.setId(IDUtil.getId());
		sample.setStatus(1);
		sample.setPid(project.getId());
		sample.setAddr(tvAddr.getText().toString().trim());
		sample.setSampleType(sampletype);
		sample.setSampleModel(EdUtil.getText(tvSamplemodel));
		sample.setMadeDate(tvMadedate.getText().toString().trim());
		sample.setSiteNumber(mTvSitenumber.getText().toString().trim());
		sample.setMadeNo(EdUtil.getText(tvMadeno));
		sample.setCheckDate(tvCheckdate.getText().toString().trim());
		sample.setSampleNo(tvSampleno.getText().toString().trim());
		sample.setInstallAddr(tvInstalladdr.getText().toString().trim());
		sample.setInstallDate(tvInstalldate.getText().toString().trim());
		sample.setTemperature(EdUtil.getText(tvTemperature));
		sample.setHumidity(EdUtil.getText(tvHumidity));
		sample.setDesc(EdUtil.getText(tvDesc));
		sample.setIsselected(0);
		sample.setTester(mainuserid + "");
		sample.setTester1(fuuserid);
		updateSampleDesign(tvSamplemodel.getText().toString().trim());//更新样品设计值
		GreenDaoManager.getInstance().getNewSession().getSampleDao().insert(sample);
		SampleCheck sampleCheck = new SampleCheck();
		sampleCheck.setId(sample.getId());
		sampleCheck.setStatus(1);
		sampleCheck.setPid(project.getId());
		GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().insert(sampleCheck);
		new MaterialDialog.Builder(SampleDetailActivity2.this).title("温馨提示").titleColorRes(R.color.black).content("添加成功")
			.contentColorRes(R.color.black).positiveText("确定").positiveColorRes(R.color.black).show();
		btnSave.setText("已保存");
		btnSave.setTextColor(this.getResources().getColor(R.color.colorPrimary));
		btnSave.setClickable(false);
	}

	public static String getTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	public void getScreenSize() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		mWidthPixels = dm.widthPixels;
		mHeightPixels = dm.heightPixels;
	}

	private void updateSampleDesign(String samplemodel) {
//        Model model= GreenDaoManager.getInstance().getNewSession()
//                .getModelDao().queryBuilder().where(ModelDao.Properties.Model_number.eq(modelno)).unique();
		List<Model> models = GreenDaoManager.getInstance().getNewSession()
			.getModelDao().queryBuilder().where(ModelDao.Properties.Model_number.eq(samplemodel)).list();
		Model model = null;
		if (models != null && models.size() > 0) {
			model = models.get(0);
		}
		if (model != null) {
			sample.setDoor_frame_height(StrKit.notBlank(sample.getDoor_frame_height()) ? sample.getDoor_frame_height() : model.getDoor_frame_height() + "");
			sample.setDoor_frame_width(StrKit.notBlank(sample.getDoor_frame_width()) ? sample.getDoor_frame_width() : model.getDoor_frame_width() + "");

			sample.setDoor_leaf_width(StrKit.notBlank(sample.getDoor_leaf_width()) ? sample.getDoor_leaf_width() : model.getDoor_leaf_width() + "");
			sample.setDoor_leaf_height(StrKit.notBlank(sample.getDoor_leaf_height()) ? sample.getDoor_leaf_height() : model.getDoor_leaf_height() + "");
			sample.setDoor_leaf_thickness(StrKit.notBlank(sample.getDoor_leaf_thickness()) ? sample.getDoor_leaf_thickness() : model.getDoor_leaf_thickness() + "");
			sample.setBattenHeight(StrKit.notBlank(sample.getBattenHeight()) ? sample.getBattenHeight() : model.getEmbedded_plate_height() + "");

			sample.setHangingPlateWidth(StrKit.notBlank(sample.getHangingPlateWidth()) ? sample.getHangingPlateWidth() : model.getHanging_plate_1() + "");
			sample.setHangingPlateWidthCenter(StrKit.notBlank(sample.getHangingPlateWidthCenter()) ? sample.getHangingPlateWidthCenter() : model.getHanging_plate_2() + "");
			sample.setHangingPlateWidthBottom(StrKit.notBlank(sample.getHangingPlateWidthBottom()) ? sample.getHangingPlateWidthBottom() : model.getHanging_plate_3() + "");
			sample.setHangingPlateHeight(StrKit.notBlank(sample.getHangingPlateHeight()) ? sample.getHangingPlateHeight() : model.getHanging_plate_height() + "");
			sample.setHangingPlateHeightCenter(StrKit.notBlank(sample.getHangingPlateHeightCenter()) ? sample.getHangingPlateHeightCenter() : model.getHanging_plate_height2() + "");
			sample.setHangingPlateHeightBottom(StrKit.notBlank(sample.getHangingPlateHeightBottom()) ? sample.getHangingPlateHeightBottom() : model.getHanging_plate_height3() + "");

			sample.setHingepageShaftDiameter(StrKit.notBlank(sample.getHingepageShaftDiameter()) ? sample.getHingepageShaftDiameter() : model.getHingepage_shaft_diameter() + "");
			sample.setHingepageHoleDiameter(StrKit.notBlank(sample.getHingepageHoleDiameter()) ? sample.getHingepageHoleDiameter() : model.getHingepage_hole_diameter() + "");
			sample.setAtresiaShaftDiameter(StrKit.notBlank(sample.getAtresiaShaftDiameter()) ? sample.getAtresiaShaftDiameter() : model.getAtresia_shaft_diameter() + "");
			sample.setAtresiaHoleDiameter(StrKit.notBlank(sample.getAtresiaHoleDiameter()) ? sample.getAtresiaHoleDiameter() : model.getAtresia_hole_diameter() + "");

//            sample.setDoorframeSteelThick("");
//            sample.setSteelPlatePositive("");
//            sample.setSteelPlateOpposite("");

			sample.setLegHeightAcross(StrKit.notBlank(sample.getLegHeightAcross()) ? sample.getLegHeightAcross() : model.getWeld_thickness() + "");

//            sample.setStrength("");
//
//            sample.setSteelbarProtectThick("");
//            sample.setSteelbarSpacing("");
//            sample.setBarDiameterDesignValue("");

			sample.setDoorLeafBaseVentWidth(StrKit.notBlank(sample.getDoorLeafBaseVentWidth()) ? sample.getDoorLeafBaseVentWidth() : model.getDoorleafbaseventwidth() + "");
			sample.setDoorLeafBaseVentHeight(StrKit.notBlank(sample.getDoorLeafBaseVentHeight()) ? sample.getDoorLeafBaseVentHeight() : model.getDoorleafbaseventheight() + "");
//            sample.setHangingPlateThicknessTop("");
//            sample.setHangingPlateThicknessBottom("");
//            sample.setSteelPlantThickness("");
//            sample.setEquivalent_pipe_diameter("");

		} else {
			sample.setDoor_frame_height("");
			sample.setDoor_frame_width("");

			sample.setDoor_leaf_width("");
			sample.setDoor_leaf_height("");
			sample.setDoor_leaf_thickness("");
			sample.setBattenHeight("");

			sample.setHangingPlateWidth("");
			sample.setHangingPlateWidthCenter("");
			sample.setHangingPlateWidthBottom("");
			sample.setHangingPlateHeight("");
			sample.setHangingPlateHeightCenter("");
			sample.setHangingPlateHeightBottom("");

			sample.setHingepageShaftDiameter("");
			sample.setHingepageHoleDiameter("");
			sample.setAtresiaShaftDiameter("");
			sample.setAtresiaHoleDiameter("");

			sample.setDoorframeSteelThick("");
			sample.setSteelPlatePositive("");
			sample.setSteelPlateOpposite("");

			sample.setLegHeightAcross("");

			sample.setStrength("");

			sample.setSteelbarProtectThick("");
			sample.setSteelbarSpacing("");
			sample.setBarDiameterDesignValue("");

			sample.setDoorLeafBaseVentWidth("");
			sample.setDoorLeafBaseVentHeight("");
			sample.setHangingPlateThicknessTop("");
			sample.setHangingPlateThicknessBottom("");
			sample.setSteelPlantThickness("");

			sample.setEquivalent_pipe_diameter("");
		}
	}


//    @Override
//    public boolean onKeyDown(int keyCode,KeyEvent event){
//        if(keyCode==KeyEvent.KEYCODE_BACK){
//            return true;//不执行父类点击事件
//        }
//        return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
//    }
}
