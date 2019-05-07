package com.zhilian.rf_qims.mvp.sample_data.info;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.colin.constant.Constants;
import com.colin.http.HttpServiceManager;
import com.colin.http.HttpUtil;
import com.colin.http.Ip;
import com.colin.utils.ArrayUtil;
import com.colin.utils.SharedPreferencesUtil;
import com.colin.utils.StrKit;
import com.google.gson.Gson;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.adapter.ModelAdapter;
import com.zhilian.rf_qims.bean.UploadMesBean;
import com.zhilian.rf_qims.dao.CheckUserDao;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.dao.SampleDao;
import com.zhilian.rf_qims.entity.Company;
import com.zhilian.rf_qims.entity.Model;
import com.zhilian.rf_qims.entity.Project;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.mvp.office.PdfSeeActivity;
import com.zhilian.rf_qims.util.ToastUtils;
import com.zhilian.rf_qims.widget.DialogProgress;
import com.zhilian.rf_qims.widget.HorizontalProgress;
import com.zhilian.rf_qims.widget.LazyLoadFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.ResponseBody;

import static com.zhilian.rf_qims.R.id.tv_company;

/**
 * Created by colin on 2018/3/23 11:43 .
 */

public class InfoFragment extends LazyLoadFragment {

	@BindView(R.id.tv_page_title)
	TextView tvPageTitle;
	@BindView(R.id.tv_1_1)
	TextView tv11;
	@BindView(tv_company)
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
	@BindView(R.id.tv_sitenumber)
	EditText mTvSitenumber;
	@BindView(R.id.tv_9_1)
	TextView tv91;
	@BindView(R.id.tv_sampleno)
	EditText tvSampleno;
	@BindView(R.id.tv_10_1)
	TextView tv101;
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
	@BindView(R.id.tv_checkdate)
	TextView tvCheckdate;
	@BindView(R.id.tv_mainuser)
	TextView tvMainUser;
	@BindView(R.id.tv_fuuser)
	TextView tvFuUser;

	Unbinder unbinder;
	DialogProgress mDatePickerDialog;
	TimePickerView pvTime;
	int timetype = 0;
	String sampletype = "";
	String[] perms = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
		Manifest.permission.WRITE_EXTERNAL_STORAGE};
	Company company;
	Project project;
	String[] sampletypes = {"请选择", "TM", "GM", "TX", "GX"};
	int touch_flag = 0;
	PopupWindow popupWindow;
	ListView mLvModel;
	ModelAdapter modelAdapter;
	List<Model> models = new ArrayList<>();
	Unbinder unbinder1;
	private String samplemodel = "#";
	private Sample sample;
	private int lastsamplestatus;
	HorizontalProgress horizontalProgress;
	public String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/rf_pdf";

	@Override
	protected int setContentView() {
		return R.layout.fragment_sample_mes;
	}


	@Override
	protected void lazyLoad() {
		mDatePickerDialog = new DialogProgress(mActivity, "等待后台生成并下载...");
		File file = new File(dir);
		//判断文件夹是否存在，如果不存在就创建，否则不创建
		if (!file.exists()) {
			//通过file的mkdirs()方法创建目录中包含却不存在的文件夹
			file.mkdirs();
		}
		unbinder = ButterKnife.bind(this, view);
//        if (!EasyPermissions.hasPermissions(getActivity(), perms)) {
//            EasyPermissions.requestPermissions(getActivity(), "需要访问手机存储权限！", 10086, perms);
//        } else {
//            File testFolder = new File(Environment.getExternalStorageDirectory() + "/rfqims");
//            if (testFolder.exists() && testFolder.isDirectory()) {
//                Log.d("", "test folder already exists");
//            } else if (!testFolder.exists()) {
//                testFolder.mkdir();
//            }
//            for (int n = 0; n < FILE_NAME.length; n++) {
//                File modelFile = new File(testFolder, FILE_NAME[n]);
//                if (!modelFile.exists()) {
//                    copyAssetFilesToSDCard(modelFile, FILE_NAME[n]);
//                }
//            }
//        }


		Sample intentsample = (Sample) getActivity().getIntent().getSerializableExtra("sample");
		project = (Project) getActivity().getIntent().getSerializableExtra("project");
		SharedPreferencesUtil util = new SharedPreferencesUtil(getActivity(), Constants.XML_NAME);
		String strcom = util.getObject("company");
		company = new Gson().fromJson(strcom, Company.class);
		sample = GreenDaoManager.getInstance().getNewSession().getSampleDao().queryBuilder().where(SampleDao.Properties.Id.eq(intentsample.getId())).unique();

		tvCompany.setText(company.getName());
		tvAddr.setText(project.getItemName());

		findViewById(R.id.tv_report).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//                if (!EasyPermissions.hasPermissions(getActivity(), perms)) {
//                    EasyPermissions.requestPermissions(getActivity(), "需要访问手机存储权限！", 10086, perms);
//                } else {
//                    Intent intent = new Intent(getActivity(), PdfSeeActivity.class);
//                    startActivity(intent);
//                }
				mDatePickerDialog.show();
				getFileName(1);
			}
		});
		horizontalProgress = new HorizontalProgress(getActivity(), null);
		findViewById(R.id.tv_record).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


//                new MaterialDialog.Builder(getActivity()).title("温馨提示").titleColorRes(R.color.black).content("请选择那种方式打开文件")
//                        .contentColorRes(R.color.black).positiveText("本应用打开").positiveColorRes(R.color.black).onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        Toast.makeText(getActivity(),"111",Toast.LENGTH_SHORT).show();
//                    }
//                }).negativeText("其他应用打开").negativeColorRes(R.color.black).onNegative(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        Toast.makeText(getActivity(),"222",Toast.LENGTH_SHORT).show();
//                    }
//                }). show();
				mDatePickerDialog.show();
				getFileName(2);
			}
		});


		//主检
		if (StrKit.isBlank(sample.getTester()) || sample.getTester().equals("-1")) {
			tvMainUser.setText("");
		} else {
			tvMainUser.setText(GreenDaoManager.getInstance().getNewSession().getCheckUserDao().queryBuilder().where(CheckUserDao
				.Properties.ID.eq(sample.getTester())).unique().getNAME());

		}
		//复检
		if (StrKit.isBlank(sample.getTester1()) || sample.getTester1().equals("")) {
			tvFuUser.setText("");
		} else {
			String funame = "";
			String names[] = sample.getTester1().split(",", -1);
			for (int i = 0; i < names.length; i++) {
				funame += GreenDaoManager.getInstance().getNewSession().getCheckUserDao().queryBuilder().where(CheckUserDao
					.Properties.ID.eq(names[i])).unique().getNAME() + ",";
			}
			tvFuUser.setText(funame.substring(0, funame.length() - 1));
		}
		setViewData();
	}

	private void setViewData() {
		tvSamplemodel.setText(sample.getSampleModel());
		tvMadeno.setText(sample.getMadeNo());
		mTvSitenumber.setText(sample.getSiteNumber());
		tvSampleno.setText(sample.getSampleNo());
		tvMadedate.setText(sample.getMadeDate());
		tvInstalladdr.setText(sample.getInstallAddr());
		tvInstalldate.setText(sample.getInstallDate());
		tvCheckdate.setText(sample.getCheckDate());
		tvTemperature.setText(sample.getTemperature());
		tvHumidity.setText(sample.getHumidity());
		spType.setSelection(ArrayUtil.indexOf(sample.getSampleType(), sampletypes));
		tvDesc.setText(sample.getDesc());
	}

//    private void copyAssetFilesToSDCard(final File testFileOnSdCard, final String FileToCopy) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    InputStream is = getActivity().getAssets().open(FileToCopy);
//                    FileOutputStream fos = new FileOutputStream(testFileOnSdCard);
//                    byte[] buffer = new byte[8192];
//                    int read;
//                    try {
//                        while ((read = is.read(buffer)) != -1) {
//                            fos.write(buffer, 0, read);
//                        }
//                    } finally {
//                        fos.flush();
//                        fos.close();
//                        is.close();
//                    }
//                } catch (IOException e) {
//                    Log.d("", "Can't copy test file onto SD card");
//                }
//            }
//        }).start();
//    }


	public void getFileName(int type) {
//        Sample sample = (Sample) getActivity().getIntent().getSerializableExtra("sample");
		Map<String, String> params = new HashMap<>();
		params.put("type", type + "");//1是报告 //2是原始记录
		params.put("id", sample.getId() + "");//样品id
		HttpServiceManager.getInstance()
			.getHttpService()
			.getServerData(HttpUtil.initUrl(), HttpUtil.initQueryParams("query", "getFileName", params))
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Consumer<ResponseBody>() {
				@Override
				public void accept(ResponseBody responseBody) throws Exception {
					String result = responseBody.string();
					UploadMesBean uploadMesBean = new Gson().fromJson(result, UploadMesBean.class);
					Log.d("upload", "获取名字" + result);
					if (uploadMesBean.getStatus().equals("1")) {
						if (type == 1) {//报告221870-1526632204273   报告名
//                                File file = new File(Environment.getExternalStorageDirectory() + "/rf_pdf/" + uploadMesBean.getMsg() + ".pdf");
							File file = getActivity().getFileStreamPath("rfpdf");
							if (!file.exists()) {
								//通过file的mkdirs()方法创建目录中包含却不存在的文件夹
								file.mkdirs();
							}
							String path = file.getAbsolutePath() + "/" + uploadMesBean.getMsg() + ".pdf";
							if (new File(path).exists()) {
								//打开
								Intent intent = new Intent(getActivity(), PdfSeeActivity.class);
								intent.putExtra("path", path);
								intent.putExtra("type", 1);
								intent.putExtra("sample", sample);
								startActivity(intent);
							} else {
								//下载
								downloadPdf(type, uploadMesBean.getMsg());
							}
						} else {//记录 /File/OriginRecord/originRecordFH1526632103365.pdf
//                                File file = new File(Environment.getExternalStorageDirectory() + "/rf_pdf/" + uploadMesBean.getMsg().substring(uploadMesBean.getMsg().lastIndexOf("/") + 1));
							File file = getActivity().getFileStreamPath("rfpdf");
							if (!file.exists()) {
								//通过file的mkdirs()方法创建目录中包含却不存在的文件夹
								file.mkdirs();
							}
							String path = file.getAbsolutePath() + "/" + uploadMesBean.getMsg().substring(uploadMesBean.getMsg().lastIndexOf("/") + 1);
							if (new File(path).exists()) {
								Intent intent = new Intent(getActivity(), PdfSeeActivity.class);
								intent.putExtra("path", path);
								intent.putExtra("type", 2);
								intent.putExtra("sample", sample);
								startActivity(intent);
							} else {
								//下载
								downloadPdf(type, uploadMesBean.getMsg());
							}
						}
					} else {
//                            File file = new File(Environment.getExternalStorageDirectory() + "/originRecordFH1526869178902.pdf");
//                            if (file.exists()) {
//                                Intent intent = new Intent(getActivity(), PdfSeeActivity.class);
//                                intent.putExtra("path", file.getAbsolutePath());
//                                intent.putExtra("type", 2);
//                                intent.putExtra("sample", sample);
//                                startActivity(intent);
//                            }
						Toast.makeText(getActivity(), uploadMesBean.getMsg(), Toast.LENGTH_SHORT).show();
					}
					mDatePickerDialog.dismiss();
				}
			}, new Consumer<Throwable>() {
				@Override
				public void accept(Throwable throwable) throws Exception {
					Log.d("upload", "获取名字" + throwable.getMessage());
					mDatePickerDialog.dismiss();
					if (type == 1) {
						ToastUtils.show("获取检测报告失败");
					} else {
						ToastUtils.show("获取原始记录失败");
					}
				}
			});
	}

	private void downloadPdf(int type, String msg) {
		String url = "";
		String filename = "";
		if (type == 1) {
			url = Ip.getIp() + "File/Report/pdf/" + msg + ".pdf";
			filename = msg + ".pdf";
		} else {
			url = Ip.getIp().substring(0, Ip.getIp().length() - 1) + msg;
			filename = msg.substring(msg.lastIndexOf("/") + 1);
		}
		horizontalProgress = new HorizontalProgress(getActivity(), null);
		horizontalProgress.show();
		String finalFilename = filename;
		OkHttpUtils//
			.get()//
			.url(url)//
			.build()//
			.execute(new FileCallBack(getActivity().getFileStreamPath("rfpdf").getAbsolutePath() + "/", finalFilename) {
				@Override
				public void inProgress(float progress, long total) {
					Log.d("hello", (int) (100 * progress) + "");
					horizontalProgress.setProgress((int) (100 * progress));
				}

				@Override
				public void onError(Call call, Exception e) {
					horizontalProgress.dismiss();
				}

				@Override
				public void onResponse(File response) {
					horizontalProgress.dismiss();
					Intent intent = new Intent(getActivity(), PdfSeeActivity.class);
					intent.putExtra("path", getActivity().getFileStreamPath("rfpdf").getAbsolutePath() + "/" + finalFilename);
					intent.putExtra("type", type);
					intent.putExtra("sample", sample);
					startActivity(intent);
				}
			});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO: inflate a fragment view
		View rootView = super.onCreateView(inflater, container, savedInstanceState);
		unbinder1 = ButterKnife.bind(this, rootView);
		return rootView;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder1.unbind();
	}
}