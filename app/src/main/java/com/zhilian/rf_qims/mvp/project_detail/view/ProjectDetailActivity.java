package com.zhilian.rf_qims.mvp.project_detail.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.colin.base.BaseDateView;
import com.colin.constant.Constants;
import com.colin.utils.ArrayUtil;
import com.colin.utils.SharedPreferencesUtil;
import com.colin.utils.StrKit;
import com.colin.widget.DatePickerFragment;
import com.google.gson.Gson;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.entity.Area;
import com.zhilian.rf_qims.entity.Company;
import com.zhilian.rf_qims.entity.Project;
import com.zhilian.rf_qims.listener.OnItemSelectedListenerImpl;
import com.zhilian.rf_qims.mvp.project_detail.presenter.ProjectDetailPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by colin on 2018/3/9 15:51 .
 */

public class ProjectDetailActivity extends AppCompatActivity implements BaseDateView {

    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.et_item_code)
    EditText mEtItemCode;
    @BindView(R.id.et_project_code)
    EditText mEtProjectCode;
    @BindView(R.id.tv_commission_unit)
    TextView mTvCommissionUnit;
    @BindView(R.id.et_item_name)
    EditText mEtItemName;
    @BindView(R.id.et_item_addr)
    EditText mEtItemAddr;
    @BindView(R.id.sp_item_source)
    Spinner mSpItemSource;
    //	@BindView(R.id.sp_item_area)
//	Spinner mSpItemArea;
    @BindView(R.id.tv_accept_date)
    TextView mTvAcceptDate;
    @BindView(R.id.sp_commission_shape)
    Spinner mSpCommissionShape;
    @BindView(R.id.et_witness_unit)
    EditText mEtWitnessUnit;
    @BindView(R.id.et_witness_unit_phone)
    EditText mEtWitnessUnitPhone;
    @BindView(R.id.et_witness)
    EditText mEtWitness;
    @BindView(R.id.et_witness_phone)
    EditText mEtWitnessPhone;
    @BindView(R.id.et_sender)
    EditText mEtSender;
    @BindView(R.id.et_sender_phone)
    EditText mEtSenderPhone;
    @BindView(R.id.et_acceptor)
    EditText mEtAcceptor;
    @BindView(R.id.sp_commission_category)
    Spinner mSpCommissionCategory;
    @BindView(R.id.et_supervision_unit)
    EditText mEtSupervisionUnit;
    @BindView(R.id.et_supervisor)
    EditText mEtSupervisor;
    @BindView(R.id.sp_secret)
    Spinner mSpSecret;
    @BindView(R.id.et_desc)
    EditText mEtDesc;

    private ProjectDetailPresenter mPresenter;
    private Company company;
    private Project mProject;
    private String[] mCommissionCategories;
    private String[] mSampleSources;
    private String[] mSecrets;
    //private List<Area> mAreas;
    private String[] mAreas;
    private String[] mCommissionShapes;
    private Company currentCompany;
    //修改传过来的值project,status=1;
    //添加 status=0;
    int status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail2);
        ButterKnife.bind(this);
        mPresenter = new ProjectDetailPresenter(this);
        initViewData();
        initContentView();
    }

    private void initViewData() {
//		mPresenter.findLocalAreas();
        mCommissionCategories = getResources().getStringArray(R.array.commission_category);
        mSampleSources = getResources().getStringArray(R.array.sample_source);
        mSecrets = getResources().getStringArray(R.array.secret);
        mCommissionShapes = getResources().getStringArray(R.array.shapes);
        mAreas = getResources().getStringArray(R.array.sample_area);
        SharedPreferencesUtil util = new SharedPreferencesUtil(this, Constants.XML_NAME);
        currentCompany = new Gson().fromJson(util.getObject("company"), Company.class);
        Intent intent = getIntent();
        status = intent.getIntExtra("status", 0);
        mProject = (Project) intent.getSerializableExtra("project");
        if (null == mProject) {
            mProject = new Project();
            mProject.setEntrustId(currentCompany.getId());
        }
        _initView();
        if (status == 0) {
            mTopBar.setTitle(R.string.create_project_title);
            mProject.setStatus(1);
        } else {
            mTopBar.setTitle(R.string.modify_project_title);
            //设置监听
        }
    }

    private void _initView() {
        mSpCommissionShape.setSelection(ArrayUtil.indexOf(mProject.getCommissionShape(), mCommissionShapes));
        mSpItemSource.setSelection(ArrayUtil.indexOf(mProject.getSampleSource(), mSampleSources));
        mSpCommissionCategory.setSelection(ArrayUtil.indexOf(mProject.getCommissionCategory(), mCommissionCategories));
        mSpSecret.setSelection(ArrayUtil.indexOf(mProject.getSecret(), mSecrets));
        mTvAcceptDate.setText(StrKit.notBlank(mProject.getAcceptDate()) ? mProject.getAcceptDate() : "");
        mTvCommissionUnit.setText("" + currentCompany.getName());
        mEtItemCode.setText(StrKit.notBlank(mProject.getItemCode()) ? mProject.getItemCode() : "");
        mEtSupervisor.setText(StrKit.notBlank(mProject.getSupervisor()) ? mProject.getSupervisor() : "");
        mEtSupervisionUnit.setText(StrKit.notBlank(mProject.getSupervisionUnit()) ? mProject.getSupervisionUnit() : "");
        mEtSenderPhone.setText(StrKit.notBlank(mProject.getSenderPhone()) ? mProject.getSenderPhone() : "");
        mEtSender.setText(StrKit.notBlank(mProject.getSender()) ? mProject.getSender() : "");
        mEtWitnessPhone.setText(StrKit.notBlank(mProject.getWitnessPhone()) ? mProject.getWitnessPhone() : "");
        mEtWitness.setText(StrKit.notBlank(mProject.getWitness()) ? mProject.getWitness() : "");
        mEtWitnessUnit.setText(StrKit.notBlank(mProject.getWitnessUnit()) ? mProject.getWitnessUnit() : "");
        mEtWitnessUnitPhone.setText(StrKit.notBlank(mProject.getWitnessUnitPhone()) ? mProject.getWitnessUnitPhone() : "");
        mEtProjectCode.setText(StrKit.notBlank(mProject.getProjectCode()) ? mProject.getProjectCode() : "");
        mEtItemName.setText(StrKit.notBlank(mProject.getItemName()) ? mProject.getItemName() : "");
        mEtItemAddr.setText(StrKit.notBlank(mProject.getProjectAddr()) ? mProject.getProjectAddr() : "");
        mEtAcceptor.setText(StrKit.notBlank(mProject.getAcceptor()) ? mProject.getAcceptor() : "");
        mEtDesc.setText(StrKit.notBlank(mProject.getDesc()) ? mProject.getDesc() : "");
    }

    private void initContentView() {
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                setResult(0x12);
                finish();
            }
        });
        mTopBar.setGravity(Gravity.LEFT);
//		mTopBar.addRightTextButton("上传", QMUIViewHelper.generateViewId()).setOnClickListener(v->{
//			Toast.makeText(this, "上传成功！", Toast.LENGTH_SHORT).show();
//		});


        mTopBar.addRightTextButton("保存", QMUIViewHelper.generateViewId()).setOnClickListener(v -> {
//            if (status==0){
//                getViewData();
////                mPresenter.saveProject(mProject);
                saveProject(status);
//            }else {
//                getViewData();
////                mPresenter.saveProject(mProject);
//            }

        });

        mSpCommissionCategory.setOnItemSelectedListener(new OnItemSelectedListenerImpl() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    mProject.setCommissionCategory(mCommissionCategories[position]);
                }
            }
        });

        mSpItemSource.setOnItemSelectedListener(new OnItemSelectedListenerImpl() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (0 != position) {
                    mProject.setSampleSource(mSampleSources[position]);
                }
            }
        });
        mSpSecret.setOnItemSelectedListener(new OnItemSelectedListenerImpl() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (0 != position) {
                    mProject.setSecret(mSecrets[position]);
                }
            }
        });
        mSpCommissionShape.setOnItemSelectedListener(new OnItemSelectedListenerImpl() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (0 != position) {
                    mProject.setCommissionShape(mCommissionShapes[position]);
                }
            }
        });
    }

    private void saveProject(int status) {
        getViewData();
        if (status==0){
            GreenDaoManager.getInstance().getNewSession().getProjectDao().insert(mProject);
        }else {
            mProject.setStatus(0);
            GreenDaoManager.getInstance().getNewSession().getProjectDao().insert(mProject);
        }
    }


    @OnClick(R.id.tv_accept_date)
    public void onViewClicked() {
        DatePickerFragment dialog = DatePickerFragment.getInstance()
                .init(this, R.id.tv_accept_date, "");
        dialog.show(getSupportFragmentManager(), "date picker");
    }

    @Override
    public void setDate(int id, String date) {
        if (id == R.id.tv_accept_date) {
            mTvAcceptDate.setText(date);
            mProject.setAcceptDate(date);
        }
    }

    public void setAreas(List<Area> areas) {
        //mAreas = areas;
    }

    public void getViewData() {
        mProject.setItemCode(mEtItemCode.getText().toString().trim());
        mProject.setItemName(mEtItemName.getText().toString().trim());
        mProject.setProjectCode(mEtProjectCode.getText().toString().trim());
        mProject.setProjectAddr(mEtItemAddr.getText().toString().trim());
        mProject.setWitnessUnit(mEtWitnessUnit.getText().toString().trim());
        mProject.setWitnessUnitPhone(mEtWitnessUnitPhone.getText().toString().trim());
        mProject.setWitness(mEtWitness.getText().toString().trim());
        mProject.setWitnessPhone(mEtWitnessPhone.getText().toString().trim());
        mProject.setSender(mEtSender.getText().toString().trim());
        mProject.setSenderPhone(mEtSenderPhone.getText().toString().trim());
        mProject.setAcceptor(mEtAcceptor.getText().toString().trim());
        mProject.setSupervisionUnit(mEtSupervisionUnit.getText().toString().trim());
        mProject.setSupervisor(mEtSupervisor.getText().toString().trim());
    }

    public void onSaveProject(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void finish() {
        super.finish();
        setContentTransition();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void setContentTransition() {
        //overridePendingTransition(R.anim.activity_left_in, R.anim.activity_right_out);
        getWindow().setEnterTransition(new Fade());
        getWindow().setExitTransition(new Fade());
    }
}