package com.zhilian.rf_qims.mvp.sample_detail.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.colin.constant.Constants;
import com.colin.utils.SharedPreferencesUtil;
import com.colin.widget.DatePickerFragment;
import com.google.gson.Gson;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.entity.Company;
import com.zhilian.rf_qims.entity.Project;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.listener.OnItemSelectedListenerImpl;
import com.zhilian.rf_qims.mvp.sample_detail.presenter.SampleDetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by colin on 2018/3/12 17:00 .
 */

public class SampleDetailActivity extends AppCompatActivity implements ISampleDetailView {
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.tv_enterprise)
    TextView mTvEnterprise;
    @BindView(R.id.et_project_addr)
    EditText mEtProjectAddr;
    @BindView(R.id.et_part)
    EditText mEtPart;
    @BindView(R.id.et_sample_model)
    EditText mEtSampleModel;
    @BindView(R.id.tv_accept_date)
    TextView mTvAcceptDate;
    @BindView(R.id.tv_install_date)
    TextView mTvInstallDate;
    @BindView(R.id.et_install_side)
    EditText mEtInstallSide;
    @BindView(R.id.tv_check_date)
    TextView mTvCheckDate;
    @BindView(R.id.et_sample_no)
    EditText mEtSampleNo;
    @BindView(R.id.sp_sample_state)
    Spinner mSpSampleState;
    @BindView(R.id.sp_sample_type)
    Spinner mSpSampleType;
    @BindView(R.id.et_product_no)
    EditText mEtProductNo;
    @BindView(R.id.et_temperature)
    EditText mEtTemperature;
    @BindView(R.id.et_humidity)
    EditText mEtHumidity;
    @BindView(R.id.et_sample_description)
    EditText mEtSampleDescription;

    private SampleDetailPresenter mPresenter;
    private Sample mSample;
    private String[] mSampleStates;
    private String[] mSampleTypes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_detail_absolute);
        ButterKnife.bind(this);
        initViewData();
        initView();
        initTopBar();
    }

    private void initViewData() {
        mPresenter = new SampleDetailPresenter(this);
        mSampleStates = getResources().getStringArray(R.array.sample_state);
        mSampleTypes = getResources().getStringArray(R.array.sample_type);
        Intent intent = getIntent();
        int status = intent.getIntExtra("status", 0);
        if (status == 0) {
            mSample = new Sample();
//			mSample.setId(IDUtil.generateId());
            mTopBar.setTitle(R.string.create_sample_title);
        } else {
            mSample = (Sample) intent.getSerializableExtra("sample");
            mTopBar.setTitle(R.string.modify_sample_title);
        }
//		mSample.setStatus(status);
    }

    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(v -> {
            setResult(0x12);
            finish();
        });
        mTopBar.addRightTextButton("保存", QMUIViewHelper.generateViewId()).setOnClickListener(v -> {
            getViewData();
            mPresenter.saveSample(mSample);
        });
        mTopBar.addRightTextButton("上传", QMUIViewHelper.generateViewId()).setOnClickListener(v -> {

        });

    }

    @OnClick({R.id.tv_accept_date, R.id.tv_install_date, R.id.tv_check_date})
    public void onViewClicked(View view) {
        String date = "";
        DatePickerFragment fragment = DatePickerFragment.getInstance().init(this, view.getId(), date);
        switch (view.getId()) {
            case R.id.tv_accept_date:
                fragment.show(getSupportFragmentManager(), "date");
                break;
            case R.id.tv_install_date:
                fragment.show(getSupportFragmentManager(), "date");
                break;
            case R.id.tv_check_date:
                fragment.show(getSupportFragmentManager(), "date");
                break;
        }

    }

    @Override
    public void setDate(int id, String date) {
        switch (id) {
            case R.id.tv_accept_date:
                mTvAcceptDate.setText(date);
                mSample.setMadeDate(date);
                break;
            case R.id.tv_install_date:
                mTvInstallDate.setText(date);
                mSample.setInstallDate(date);
                break;
            case R.id.tv_check_date:
                mTvCheckDate.setText(date);
                mSample.setCheckDate(date);
                break;
        }
    }

    private void initView() {
        SharedPreferencesUtil util = new SharedPreferencesUtil(this, Constants.XML_NAME);
        Company currentCompany = new Gson().fromJson(util.getObject("company"), Company.class);
        mTvEnterprise.setText(currentCompany.getName());
        mSpSampleState.setOnItemSelectedListener(new OnItemSelectedListenerImpl() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (0 != position) {
                    mSample.setSampleState(mSampleStates[position]);
                }
            }
        });
        mSpSampleType.setOnItemSelectedListener(new OnItemSelectedListenerImpl() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (0 != position) {
                    mSample.setSampleType(mSampleTypes[position]);
                }
            }
        });
    }

    private void getViewData() {
        Project project = (Project) getIntent().getSerializableExtra("project");
        mSample.setPid(project.getId());
        mSample.setAddr(mEtProjectAddr.getText().toString().trim());
        mSample.setPart(mEtPart.getText().toString().trim());
        mSample.setSampleModel(mEtSampleModel.getText().toString().trim());
        mSample.setMadeNo(mEtProductNo.getText().toString().trim());
        mSample.setSampleNo(mEtSampleNo.getText().toString().trim());
        mSample.setInstallAddr(mEtInstallSide.getText().toString().trim());
        mSample.setTemperature(mEtTemperature.getText().toString().trim());
        mSample.setHumidity(mEtHumidity.getText().toString().trim());
        mSample.setDesc(mEtSampleDescription.getText().toString().trim());
    }

    @Override
    public void onSaveSample(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
