package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
import com.zhilian.rf_qims.entity.WorkAbilityDao;
import com.zhilian.rf_qims.entity.WorkAbilityJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.pfassessment.DtSignAndPhoto;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule.EnterpriseQualification;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule.EnvironmentalFacility;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule.InstallationQuality;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule.KeyPersonnel;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule.LargeScaleEquipment;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule.ManagementSystem;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule.OperatingManagement;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule.ProcessingEquipment;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule.ProductQuality;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule.ProductionPersonnel;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule.QualityInspectionTools;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule.QualitySystem;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule.SiteCondition;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule.TechnicistPersonnel;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.workassessment.WorkGridViewAdapter;
import com.zhilian.rf_qims.util.DoubleUtil;
import com.zhilian.rf_qims.util.PhotoOrVideo;
import com.zhilian.rf_qims.util.ToastUtils;
import com.zhilian.rf_qims.widget.MediaLoader;
import com.zhilian.rf_qims.widget.MyGridView;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhilian.rf_qims.util.CommonUtils.getContext;

/**
 * 从业评估（打分细节）
 */
public class WorkAssessmentRatingActivity extends AppCompatActivity {
	@BindView(R.id.total_score)
	TextView mTotalScore;// 合计
	@BindView(R.id.one)
	MyGridView mGridView1;// 必备条件
	@BindView(R.id.two)
	MyGridView mGridView2;// 从业能力
	@BindView(R.id.three)
	MyGridView mGridView3;// 生产管理
	/*@BindView(R.id.four)
	MyGridView mGridView4;// 安装管理*/

	@BindView(R.id.record)
	LinearLayout mRecord;// 现场录音
	@BindView(R.id.video)
	LinearLayout mVideo;// 现场录像
	@BindView(R.id.camera)
	LinearLayout mCamera;// 现场拍照
	@BindView(R.id.signature)
	LinearLayout mSignature;// 现场签名
	@BindView(R.id.initialize)
	LinearLayout mInitialize;// 初始化
	@BindView(R.id.import_personnel)
	LinearLayout mImportPersonnel;// 导入人员
	@BindView(R.id.import_device)
	LinearLayout mImportDevice;// 导入设备
	@BindView(R.id.generate_assessment)
	LinearLayout mGenerateAssessment;// 生成评估书

	@BindView(R.id.point1)//必备条件得分
	TextView mPoint1;
	@BindView(R.id.point2)//从业能力得分
	TextView mPoint2;
	@BindView(R.id.point3)//生产管理得分
	TextView mPoint3;

	Bitmap mBitmap;
	private Long cid;
	private Integer eid;

	// 必备条件
	//private String[] mScore1 = {"（Y）", "（Y）", "（Y）", "（Y）"};
	private String[] mModule1 = {"企业资质", "质量体系", "环境设施", "关键人员"};
	// 从业能力
	//private String[] mScore2 = {"（10 / 5）", "（10 / 5）", "（10 / 5）", "（10 / 5）", "（15 / 10）", "（15 / 10）", "（10 / 5）", "（5 / 0）"};
	private String[] mModule2 = {"关键人员", "技术人员", "生产人员", "管理制度", "场地条件", "大型设备", "加工设备", "质检工具"};
	// 生产管理
	//private String[] mScore3 = {"（10 / 5）", "（5 / 0）"};
	private String[] mModule3 = {"经营管理", "产品质量"};
	// 安装管理
	private String[] mScore4 = {"（5 / 0）", "（10 / 5）"};
	private String[] mModule4 = {"安装操作", "安装质量"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_work_assessment_rating);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		ButterKnife.bind(this);

		//初始化Album配置
		Album.initialize(AlbumConfig.newBuilder(getContext())
			.setAlbumLoader(new MediaLoader())
			.setLocale(Locale.getDefault())
			.build());

		initView();

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

	private void initView() {
		cid = Common.getWid();
		eid = Integer.valueOf(Common.getEid());
		List<CaConfigJson> mModule1 = CaConfigDao.query(1, "1.%");// 必备条件
		List<CaConfigJson> mModule2 = CaConfigDao.query(1, "2.%");// 从业能力
		List<CaConfigJson> mModule3 = CaConfigDao.query(1, "3.%");// 生产管理
		//List<CaConfigJson> mModule4 = CaConfigDao.query(1, "4.%");// 安装管理

		//获取所有打分
		WorkAbilityJson workAbilityJson = WorkAbilityDao.queryPoint(cid, eid);

		mPoint1.setText(workAbilityJson.getITEM());
		mPoint2.setText(workAbilityJson.getITEM2());
		mPoint3.setText(workAbilityJson.getITEM3());

		if (workAbilityJson.getTOTAL() == null || workAbilityJson.getTOTAL().equals("")){
			mTotalScore.setText("0.00");// 合计
		}else {
			mTotalScore.setText(workAbilityJson.getTOTAL());
		}

		String[] mScore1 = new String[3], mScore2 = new String[8], mScore3 = new String[3];
		mScore1[0] = workAbilityJson.getITEM1_1();
		mScore1[1] = workAbilityJson.getITEM1_2();
		mScore1[2] = workAbilityJson.getITEM1_3();
		if(mScore1[0] != null && mScore1[1] != null && mScore1[2] != null){
			if (mScore1[0].equals("Y") && mScore1[1].equals("Y") && mScore1[2].equals("Y")){
				mPoint1.setText("（ "+ "Y" +" ）");
			}
		}

		mScore2[0] = workAbilityJson.getITEM2_1();
		mScore2[1] = workAbilityJson.getITEM2_2();
		mScore2[2] = workAbilityJson.getITEM2_3();
		mScore2[3] = workAbilityJson.getITEM2_4();
		mScore2[4] = workAbilityJson.getITEM2_5();
		mScore2[5] = workAbilityJson.getITEM2_6();
		mScore2[6] = workAbilityJson.getITEM2_7();
		mScore2[7] = workAbilityJson.getITEM2_8();

		mScore3[0] = workAbilityJson.getITEM3_1();
		mScore3[1] = workAbilityJson.getITEM3_2();
		mScore3[2] = workAbilityJson.getITEM3_3();

		// 必备条件
		mGridView1.setAdapter(new WorkGridViewAdapter(this, mScore1, mModule1));
		mGridView1.setOnItemClickListener(listener1);
		// 从业能力
		mGridView2.setAdapter(new WorkGridViewAdapter(this, mScore2, mModule2));
		mGridView2.setOnItemClickListener(listener2);
		// 生产管理
		mGridView3.setAdapter(new WorkGridViewAdapter(this, mScore3, mModule3));
		mGridView3.setOnItemClickListener(listener3);
		// 安装管理
        /*mGridView4.setAdapter(new WorkGridViewAdapter(this, mScore4, mModule4));
        mGridView4.setOnItemClickListener(listener4);*/

		mRecord.setOnClickListener(listener);
		mVideo.setOnClickListener(listener);
		mCamera.setOnClickListener(listener);
		mSignature.setOnClickListener(listener);
		mInitialize.setOnClickListener(listener);
		mImportPersonnel.setOnClickListener(listener);
		mImportDevice.setOnClickListener(listener);
		mGenerateAssessment.setOnClickListener(listener);
	}

	/**
	 * 事件监听
	 */
	View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent;
			switch (v.getId()) {
				case R.id.record:// 现场录音
					intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
					startActivityForResult(intent, 1);
					break;
				case R.id.video:// 现场录像
					if (Build.VERSION.SDK_INT > 22) {
						if (ContextCompat.checkSelfPermission(WorkAssessmentRatingActivity.this,
							Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
							//先判断有没有权限 ，没有就在这里进行权限的申请
							int camera_ok = 0;
							ActivityCompat.requestPermissions(WorkAssessmentRatingActivity.this,
								new String[]{Manifest.permission.CAMERA}, camera_ok);
						} else {
                            /*intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                            startActivityForResult(intent,2);*/
							PhotoOrVideo.video(WorkAssessmentRatingActivity.this,
								null, "khxc", "1", 0, String.valueOf(cid));
						}
					} else {
                        /*intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                        startActivityForResult(intent,2);*/
						PhotoOrVideo.video(WorkAssessmentRatingActivity.this,
							null, "khxc", "1", 0, String.valueOf(cid));
					}
					break;
				case R.id.camera:// 现场拍照
					if (Build.VERSION.SDK_INT > 22) {
						if (ContextCompat.checkSelfPermission(WorkAssessmentRatingActivity.this,
							Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
							//先判断有没有权限 ，没有就在这里进行权限的申请
							int camera_ok = 1;
							ActivityCompat.requestPermissions(WorkAssessmentRatingActivity.this,
								new String[]{Manifest.permission.CAMERA}, camera_ok);
						} else {
                            /*intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 3);*/
							PhotoOrVideo.photo(WorkAssessmentRatingActivity.this,
								null, "khxc", "1", 0, String.valueOf(cid));
						}
					} else {
						PhotoOrVideo.photo(WorkAssessmentRatingActivity.this,
							null, "khxc", "1", 0, String.valueOf(cid));
                        /*intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 3);*/
					}
					break;
				case R.id.signature:// 现场签名
					intent = new Intent(WorkAssessmentRatingActivity.this, DtSignAndPhoto.class);
					startActivity(intent);
					break;
				case R.id.initialize:// 初始化
					break;
				case R.id.import_personnel:// 导入人员
					break;
				case R.id.import_device:// 导入设备
					break;
				case R.id.generate_assessment:// 生成评估书
					break;
			}
		}
	};

	/**
	 * 必备条件的事件监听
	 */
	private AdapterView.OnItemClickListener listener1 = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Intent intent;
			switch (position) {
				case 0:// 企业资质
					intent = new Intent(WorkAssessmentRatingActivity.this, EnterpriseQualification.class);
					startActivityForResult(intent, 2);
					break;
				case 1:// 质量体系
					intent = new Intent(WorkAssessmentRatingActivity.this, QualitySystem.class);
					startActivity(intent);
					break;
				case 2:// 环境设施
					intent = new Intent(WorkAssessmentRatingActivity.this, EnvironmentalFacility.class);
					startActivity(intent);
					break;
				case 3:// 关键人员
					ToastUtils.show("已取消");
                    /*intent = new Intent(WorkAssessmentRatingActivity.this, KeyPersonnel.class);
                    startActivity(intent);*/
					break;
			}
		}
	};

	/**
	 * 从业能力的事件监听
	 */
	private AdapterView.OnItemClickListener listener2 = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Intent intent;
			switch (position) {
				case 0:// 关键人员
					intent = new Intent(WorkAssessmentRatingActivity.this, KeyPersonnel.class);
					startActivity(intent);
					break;
				case 1:// 技术人员
					intent = new Intent(WorkAssessmentRatingActivity.this, TechnicistPersonnel.class);
					startActivity(intent);
					break;
				case 2:// 生产人员
					intent = new Intent(WorkAssessmentRatingActivity.this, ProductionPersonnel.class);
					startActivity(intent);
					break;
				case 3:// 管理制度
					intent = new Intent(WorkAssessmentRatingActivity.this, ManagementSystem.class);
					startActivity(intent);
					break;
				case 4:// 场地条件
					intent = new Intent(WorkAssessmentRatingActivity.this, SiteCondition.class);
					startActivity(intent);
					break;
				case 5:// 大型加工设备
					intent = new Intent(WorkAssessmentRatingActivity.this, LargeScaleEquipment.class);
					startActivity(intent);
					break;
				case 6:// 普通加工设备
					intent = new Intent(WorkAssessmentRatingActivity.this, ProcessingEquipment.class);
					startActivity(intent);
					break;
				case 7:// 质检工具
					intent = new Intent(WorkAssessmentRatingActivity.this, QualityInspectionTools.class);
					startActivity(intent);
					break;
			}
		}
	};

	/**
	 * 生产管理的事件监听
	 */
	private AdapterView.OnItemClickListener listener3 = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Intent intent;
			switch (position) {
				case 0:// 经营管理
					intent = new Intent(WorkAssessmentRatingActivity.this, OperatingManagement.class);
					startActivity(intent);
					break;
				case 1:// 产品质量
					intent = new Intent(WorkAssessmentRatingActivity.this, ProductQuality.class);
					startActivity(intent);
					break;
				case 2:// 安装质量
					intent = new Intent(WorkAssessmentRatingActivity.this, InstallationQuality.class);
					startActivity(intent);
					break;
			}
		}
	};

	/**
	 * 安装管理的事件监听
	 */
	private AdapterView.OnItemClickListener listener4 = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Intent intent;
			switch (position) {
				case 0:// 安装操作
                    /*intent = new Intent(WorkAssessmentRatingActivity.this, InstallationOperation.class);
                    startActivity(intent);*/
					ToastUtils.show("已移除");
					break;
				case 1:// 安装质量
					intent = new Intent(WorkAssessmentRatingActivity.this, InstallationQuality.class);
					startActivity(intent);
					break;
			}
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 2 && resultCode == 101) {
			int status = data.getIntExtra("status", 0);
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		switch (requestCode) {
			case 0:
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    /*Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    startActivityForResult(intent, 2);*/
					PhotoOrVideo.video(WorkAssessmentRatingActivity.this,
						null, "khxc", "1", 0, String.valueOf(cid));
				} else {
					//这里是拒绝给APP摄像头权限
					Toast.makeText(WorkAssessmentRatingActivity.this, "请手动打开所需权限", Toast.LENGTH_SHORT).show();
				}
				break;
			case 1:
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 3);*/
					PhotoOrVideo.photo(this, null, "khxc", "1", 0, String.valueOf(cid));
				} else {
					//这里是拒绝给APP摄像头权限
					Toast.makeText(WorkAssessmentRatingActivity.this, "请手动打开所需权限", Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				break;
		}

	}

	//重新开始Activity就刷新界面
	@Override
	protected void onResume() {
		super.onResume();
		initView();
	}
}
