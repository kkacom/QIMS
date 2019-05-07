package com.zhilian.rf_qims.mvp.project_detail.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.pickerview.TimePickerView;
import com.colin.constant.Constants;
import com.colin.utils.IDUtil;
import com.colin.utils.SharedPreferencesUtil;
import com.google.gson.Gson;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.entity.AreaMes;
import com.zhilian.rf_qims.entity.Company;
import com.zhilian.rf_qims.entity.Project;
import com.zhilian.rf_qims.listener.OnItemSelectedListenerImpl;
import com.zhilian.rf_qims.mvp.project_detail.presenter.ProjectDetailPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProjectAddActivity extends AppCompatActivity {
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
    @BindView(R.id.sp_item_area)
    Spinner mSpItemArea;
    private ProjectDetailPresenter mPresenter;
    private Project mProject;
    private String[] mCommissionCategories;
    private String[] mSampleSources;
    private String[] mSecrets;
    //private List<Area> mAreas;
    private String[] mAreas;
    private String[] mCommissionShapes;
    private Company currentCompany;
    TimePickerView pvTime;
    List<AreaMes> areaList = new ArrayList<>();
    List<String> areastrs = new ArrayList<>();
    private ArrayAdapter<String> arr_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_add);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mCommissionCategories = getResources().getStringArray(R.array.commission_category);
        mSampleSources = getResources().getStringArray(R.array.sample_source);
        mSecrets = getResources().getStringArray(R.array.secret);
        mCommissionShapes = getResources().getStringArray(R.array.shapes);
        SharedPreferencesUtil util = new SharedPreferencesUtil(this, Constants.XML_NAME);
        currentCompany = new Gson().fromJson(util.getObject("company"), Company.class);
        mProject = new Project();
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0x12);
                finish();
            }
        });
        mTopBar.setGravity(Gravity.LEFT);
        mTopBar.addRightTextButton("保存", QMUIViewHelper.generateViewId()).setOnClickListener(v -> {
            savaProject();
        });
        //填充view
        setViewData();


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
//                if (0 != position) {
//                    mProject.setSampleSource(mSampleSources[position]);
//                }
                if (position == 0) {
                    mProject.setSampleSource(1 + "");
                } else {
                    mProject.setSampleSource(2 + "");
                }

            }
        });
        mSpSecret.setOnItemSelectedListener(new OnItemSelectedListenerImpl() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (0 != position) {
                    mProject.setSecret((position - 1) + "");
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

        pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                mTvAcceptDate.setText(getTime(date));
            }
        });
        areaList = GreenDaoManager.getInstance().getNewSession().getAreaMesDao().queryBuilder().list();
//        areastrs.add("请选择");
        if (areaList != null && areaList.size() > 0) {
            for (int i = 0; i < areaList.size(); i++) {
                areastrs.add(areaList.get(i).getName());
            }
        }

        //适配器
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, areastrs);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        mSpItemArea.setAdapter(arr_adapter);
        mSpItemArea.setOnItemSelectedListener(new OnItemSelectedListenerImpl() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0) {
//                    mProject.setAreaId(-1);
//                } else {
                mProject.setAreaId(areaList.get(position).getId());
//                }
            }
        });
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @OnClick(R.id.tv_accept_date)
    public void onViewClicked() {
        pvTime.show();
    }

    private void setViewData() {
        mTopBar.setTitle(R.string.create_project_title);
        mTvCommissionUnit.setText("" + currentCompany.getName());
    }

    private void savaProject() {
        /*if (mEtItemCode.getText().toString().trim().equals("")) {
            Toast.makeText(ProjectAddActivity.this, "委托单编号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }*/
        if (mEtItemName.getText().toString().trim().equals("")) {
            Toast.makeText(ProjectAddActivity.this, "项目名称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        mProject.setId(IDUtil.getId()); ////当前：（年、月、日、时、分、秒、毫秒）*10000
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
        mProject.setAcceptDate(mTvAcceptDate.getText().toString().trim());
        mProject.setStatus(1);//新建
        mProject.setEntrustId(currentCompany.getId());
        mProject.setDesc(mEtDesc.getText().toString().trim());
        mProject.setIsselected(0);
        GreenDaoManager.getInstance().getNewSession().getProjectDao().insert(mProject);
        new MaterialDialog.Builder(ProjectAddActivity.this).title("温馨提示").titleColorRes(R.color.black).content("添加成功")
                .contentColorRes(R.color.black).positiveText("确定").positiveColorRes(R.color.black).show();
//        Toast.makeText(ProjectAddActivity.this, "新建成功", Toast.LENGTH_SHORT).show();
    }


}