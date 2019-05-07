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
import com.colin.utils.ArrayUtil;
import com.colin.utils.SharedPreferencesUtil;
import com.colin.utils.StrKit;
import com.google.gson.Gson;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.dao.AreaMesDao;
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

public class ProjectUpdateActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_project_update);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
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
        mCommissionCategories = getResources().getStringArray(R.array.commission_category);
        mSampleSources = getResources().getStringArray(R.array.sample_source);
        mSecrets = getResources().getStringArray(R.array.secret);
        mCommissionShapes = getResources().getStringArray(R.array.shapes);
        SharedPreferencesUtil util = new SharedPreferencesUtil(this, Constants.XML_NAME);
        currentCompany = new Gson().fromJson(util.getObject("company"), Company.class);
        mProject = (Project) getIntent().getSerializableExtra("project");
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
                if (position==0){
                    mProject.setSampleSource(1+"");
                }else {
                    mProject.setSampleSource(2+"");
                }
            }
        });
        mSpSecret.setOnItemSelectedListener(new OnItemSelectedListenerImpl() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (0 != position) {
                    mProject.setSecret((position-1)+"");
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
        mTopBar.setTitle(R.string.modify_project_title);
        mTvCommissionUnit.setText("" + currentCompany.getName());
        mEtItemCode.setText(StrKit.notBlank(mProject.getItemCode()) ? mProject.getItemCode() : "");
        mEtProjectCode.setText(StrKit.notBlank(mProject.getProjectCode()) ? mProject.getProjectCode() : "");
        mTvCommissionUnit.setText("" + currentCompany.getName());
        mEtItemName.setText(StrKit.notBlank(mProject.getItemName()) ? mProject.getItemName() : "");
        mEtItemAddr.setText(StrKit.notBlank(mProject.getProjectAddr()) ? mProject.getProjectAddr() : "");
//        mSpItemSource.setSelection(ArrayUtil.indexOf(mProject.getSampleSource(), mSampleSources));
        if (mProject.getSampleSource().equals("1")){
            mSpItemSource.setSelection(0);
        }else {
            mSpItemSource.setSelection(1);
        }
        //样品区域  // TODO: 2018/5/13 样品区域
        mTvAcceptDate.setText(StrKit.notBlank(mProject.getAcceptDate()) ? mProject.getAcceptDate() : "");
        mSpCommissionShape.setSelection(ArrayUtil.indexOf(mProject.getCommissionShape(), mCommissionShapes));
        mEtWitnessUnit.setText(StrKit.notBlank(mProject.getWitnessUnit()) ? mProject.getWitnessUnit() : "");
        mEtWitnessUnitPhone.setText(StrKit.notBlank(mProject.getWitnessUnitPhone()) ? mProject.getWitnessUnitPhone() : "");
        mEtWitness.setText(StrKit.notBlank(mProject.getWitness()) ? mProject.getWitness() : "");
        mEtWitnessPhone.setText(StrKit.notBlank(mProject.getWitnessPhone()) ? mProject.getWitnessPhone() : "");
        mEtSender.setText(StrKit.notBlank(mProject.getSender()) ? mProject.getSender() : "");
        mEtSenderPhone.setText(StrKit.notBlank(mProject.getSenderPhone()) ? mProject.getSenderPhone() : "");
        mEtAcceptor.setText(StrKit.notBlank(mProject.getAcceptor()) ? mProject.getAcceptor() : "");
        mSpCommissionCategory.setSelection(ArrayUtil.indexOf(mProject.getCommissionCategory(), mCommissionCategories));
        mEtSupervisionUnit.setText(StrKit.notBlank(mProject.getSupervisionUnit()) ? mProject.getSupervisionUnit() : "");
        mEtSupervisor.setText(StrKit.notBlank(mProject.getSupervisor()) ? mProject.getSupervisor() : "");
        if (mProject.getSecret().equals("0")){
            mSpSecret.setSelection(1);
        }else if (mProject.getSecret().equals("1")){
            mSpSecret.setSelection(2);
        }
        mEtDesc.setText(StrKit.notBlank(mProject.getDesc()) ? mProject.getDesc() : "");
//        if (mProject.getAreaId()==-1){
//            mSpItemArea.setSelection(0);
//        }else {
        AreaMes areaMes = GreenDaoManager.getInstance().getNewSession().getAreaMesDao().queryBuilder().where(AreaMesDao.Properties.Id.eq(mProject.getAreaId())).unique();
        if (areaMes != null) {
            mSpItemArea.setSelection(areastrs.indexOf(areaMes.getName()));
        }

//        }
    }

    private void savaProject() {
        /*if (mEtItemCode.getText().toString().trim().equals("")){
            Toast.makeText(ProjectUpdateActivity.this, "委托单编号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }*/
        if (mEtItemName.getText().toString().trim().equals("")){
            Toast.makeText(ProjectUpdateActivity.this, "项目名称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        //// TODO: 2018/5/13 信息填写
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
        mProject.setDesc(mEtDesc.getText().toString().trim());


        if (mProject.getStatus() == 2) {//已上传过的委托单
            mProject.setStatus(0);
        }
        GreenDaoManager.getInstance().getNewSession().getProjectDao().update(mProject);
        new MaterialDialog.Builder(ProjectUpdateActivity.this).title("温馨提示").titleColorRes(R.color.black).content("修改成功")
                .contentColorRes(R.color.black).positiveText("确定").positiveColorRes(R.color.black).show();

    }
}
