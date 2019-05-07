package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.colin.http.HttpServiceManager;
import com.colin.http.HttpUtil;
import com.colin.utils.LogUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.bean.CompanyBean;
import com.zhilian.rf_qims.dao.CompanyDao;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
import com.zhilian.rf_qims.entity.CaTestDao;
import com.zhilian.rf_qims.entity.CaTestJson;
import com.zhilian.rf_qims.entity.Company;
import com.zhilian.rf_qims.entity.EntEquipmentDao;
import com.zhilian.rf_qims.entity.EntEquipmentJson;
import com.zhilian.rf_qims.entity.PersonnelDao;
import com.zhilian.rf_qims.entity.PersonnelJson;
import com.zhilian.rf_qims.entity.WorkAbilityDao;
import com.zhilian.rf_qims.entity.WorkAbilityJson;
import com.zhilian.rf_qims.entity.WorkUserDao;
import com.zhilian.rf_qims.entity.WorkUserJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.workassessment.WorkAssessmentFieldAdapter;
import com.zhilian.rf_qims.util.DialogUtil;
import com.zhilian.rf_qims.util.ToastUtils;
import com.zhilian.rf_qims.util.UploadParams;
import com.zhilian.rf_qims.widget.DialogProgress;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 从业评估（从业能力现场评估表）
 */
public class WorkAssessmentFieldActivity extends AppCompatActivity {
    @BindView(R.id.list_bg)
	LinearLayout mListBg;// 列表没有内容时，显示的背景
    @BindView(R.id.download)
	TextView mDownload;// 下载
    @BindView(R.id.upload)
	TextView mUpload;// 上传
    @BindView(R.id.all_upload)
	TextView mAllUpload;// 全部上传
    @BindView(R.id.work_assessment_field)
	ListView mListView;// 现场评估表
    @BindView(R.id.company_name)
	TextView mCompanyName;// 受检企业
    @BindView(R.id.select)
	ImageView mSelect;// 选择企业

    WorkAssessmentFieldAdapter mAdapter;
    DialogProgress mDialogProgress;
    int eid, i = 0, p = 0, e = 0;
    String testdate,cid;
    List<WorkAbilityJson> workAbilityList;
    List<PersonnelJson> mPersonnelJsonList = null;
    List<EntEquipmentJson> mEquipmentJsonList = null;
    WorkAbilityJson mWorkAbilityJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_assessment_field);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);// 指定Toolbar上的视图文件
        return true;
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
        workAbilityList = WorkAbilityDao.loadAll();
        if(workAbilityList.size() == 0){
            mListBg.setVisibility(View.VISIBLE);
        }

        mAdapter = new WorkAssessmentFieldAdapter(this, workAbilityList,this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(itemListener);
        mListView.setOnItemLongClickListener(longListener);

        mSelect.setOnClickListener(listener);

        onListener();
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.search:// 搜索
                    ToastUtils.show("搜索");
                    break;
                case R.id.add:// 添加
                    if(!mCompanyName.getText().toString().isEmpty()){
                        //
                        Intent intent = new Intent(WorkAssessmentFieldActivity.this, WorkAssessmentUpdate.class);
                        intent.putExtra("flag", "add");//add和update公用界面，标记
                        intent.putExtra("ep", mCompanyName.getText());//企业名称
                        startActivityForResult(intent, 2);
                    }else{
                        ToastUtils.show("提示：选择企业后才能添加");
                    }
					break;
                case R.id.download: //同步基本数据
                    mDialogProgress = new DialogProgress(WorkAssessmentFieldActivity.this, "正在下载中");
                    mDialogProgress.show();
                    WorkUserDao.deleteAll(); // 考评人员？
                    CaConfigDao.deleteAll(); // 配置表
                    PersonnelDao.deleteAll(); // 人员表
                    EntEquipmentDao.deleteAll(); // 设备表
                    getEnterprisesData();
                    break;
            }

            return true;
        }
    };

    // 选择企业的事件监听
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(WorkAssessmentFieldActivity.this, WorkAssessmentListActivity.class);
            startActivityForResult(intent, 1);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 100){  //选择企业结果
            String name = data.getStringExtra("name");// 企业名称
            String number = data.getStringExtra("number");// 企业编号
            mCompanyName.setText(name);
            // 根据所选企业进行筛选，显示相应企业的考核记录
            getWorkAbility(name);
            workAbilityList = WorkAbilityDao.query(number);
            if(workAbilityList.size() > 0){
				mListBg.setVisibility(View.GONE);
            }else {
                mListBg.setVisibility(View.VISIBLE);
            }
            mAdapter = new WorkAssessmentFieldAdapter(this, workAbilityList,this);
            mListView.setAdapter(mAdapter);
            mListView.setOnItemClickListener(itemListener);
        }else if (requestCode == 2 && resultCode == 100){   //创建新的工程就重置
            mCompanyName.setText("");
            List<WorkAbilityJson> workAbilityList = WorkAbilityDao.loadAll();
            mListBg.setVisibility(View.GONE);
            mAdapter = new WorkAssessmentFieldAdapter(this, workAbilityList,this);
            mListView.setAdapter(mAdapter);
            mListView.setOnItemClickListener(itemListener);
        }
    }

    // 现场评估表item的点击事件监听
    AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            workAbilityList = WorkAbilityDao.loadAll();
            mCompanyName.setText(workAbilityList.get(position).getENTERPRISE_NAME());
            eid = workAbilityList.get(position).getEID();
            testdate = workAbilityList.get(position).getTESTDATE();
            cid = String.valueOf(workAbilityList.get(position).getWID());
            mWorkAbilityJson = workAbilityList.get(position);
        }
    };

    // 现场评估表item的长按事件监听
    AdapterView.OnItemLongClickListener longListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            DialogUtil.createAlertDialog(WorkAssessmentFieldActivity.this);
            return true;
        }
    };

    /**
     * 事件监听
     */
    private void onListener(){
        // 下载
        mDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(mCompanyName.getText());
                if (!name.isEmpty() || !name.equals("")){
                    new AlertDialog.Builder(WorkAssessmentFieldActivity.this)
                        .setMessage("下载同步数据将覆盖原有数据,请确认？")
                        .setCancelable(false).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getCaTestdata();// 测试接口
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtils.show("取消下载");
                        }
                    }).show();
                }else {
                    ToastUtils.showLong("选择企业并选中项目才能下载");
                }
            }
        });

        // 上传
        mUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWorkAbilityJson != null){
                    if (mWorkAbilityJson.getSTATUS() != 2){
                        mDialogProgress = new DialogProgress(WorkAssessmentFieldActivity.this, "正在上传中");
                        mDialogProgress.show();
                        uploadSingleWorkAbility(mWorkAbilityJson);
                    }else {
                        ToastUtils.show("此项目状态为已上传");
                    }
                }else {
                    ToastUtils.show("请先点击需要上传的项目");
                }
            }
        });

        // 全部上传
        mAllUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**下载企业基础数据*/
    private void getEnterprisesData(){
        HttpServiceManager.getInstance().getHttpService()
            .getServerData(HttpUtil.initUrl(),HttpUtil.initQueryMethod("getEnterpriseLists"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>() {
                @Override
                public void accept(ResponseBody responseBody) throws Exception {
                    String json = responseBody.string();
                    Type type = new TypeToken<List<CompanyBean>>(){}.getType();
                    List<CompanyBean> list = new Gson().fromJson(json, type);
                    CompanyDao dao = GreenDaoManager.getInstance().getNewSession().getCompanyDao();
                    for (CompanyBean bean : list) {
                        Company company = new Company();
                        company.setId(bean.getEID());//存eid
                        company.setMainId(bean.getID());//id
                        company.setName(bean.getNAME());
                        company.setNumber(bean.getNUMBER());
                        company.setAddr(bean.getADDRESS());
                        company.setArea(bean.getAREA());
                        company.setContacts(bean.getCONTACTS1());
                        company.setPhone(bean.getCONTACTS1_TE());
                        try {
                            dao.insert(company);
                        } catch (Exception e) {
                            dao.update(company);
                        }
                    }
                    getServerAssessData();
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    ToastUtils.show("下载企业基础数据失败");
                    mDialogProgress.dismiss();
                    LogUtil.e("getEnterprisesData:" + throwable.getMessage());
                }
            });
    }

    /**下载从业评估配置*/
    public void getServerAssessData() {
        HttpServiceManager.getInstance().getHttpService()
            .getServerData(HttpUtil.initUrl(), HttpUtil.initQueryMethod("getCaConfigList"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>() {
                @Override
                public void accept(ResponseBody responseBody) throws Exception {
                    String json = responseBody.string();
                    Type type = new TypeToken<List<CaConfigJson>>(){}.getType();
                    List<CaConfigJson> list = new Gson().fromJson(json, type);
                    CaConfigDao.insertOrReplaceInTx(list);
                    getServerPersonnelData();
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    ToastUtils.show("下载从业评估配置数据失败");
                    mDialogProgress.dismiss();
                    LogUtil.e("getServerAssessData:" + throwable.getMessage());
                }
            });
    }

    /**从业评估人员数据*/
    public void getServerPersonnelData() {
        HttpServiceManager.getInstance().getHttpService()
            .getServerData(HttpUtil.initUrl(), HttpUtil.initQueryMethod("getPersonnelList"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>() {
                @Override
                public void accept(ResponseBody responseBody) throws Exception {
                    String json = responseBody.string();
                    Type type = new TypeToken<List<PersonnelJson>>(){}.getType();
                    List<PersonnelJson> list = new Gson().fromJson(json, type);
                    for (PersonnelJson personnelJson : list){
                        personnelJson.setUPLOADSTATUS(2);
                    }
                    PersonnelDao.insertOrReplaceInTx(list);
                    getServerUserData();
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    ToastUtils.show("下载从业评估人员数据失败");
                    mDialogProgress.dismiss();
                    LogUtil.e("getServerAssessData:" + throwable.getMessage());
                }
            });
    }

    /**从业考核人员数据*/
    public void getServerUserData() {
        HttpServiceManager.getInstance().getHttpService()
            .getServerData(HttpUtil.initUrl(), HttpUtil.initQueryMethod("getUserList1"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>() {
                @Override
                public void accept(ResponseBody responseBody) throws Exception {
                    String json = responseBody.string();
                    Type type = new TypeToken<List<WorkUserJson>>(){}.getType();
                    List<WorkUserJson> list = new Gson().fromJson(json, type);
                    WorkUserDao.insertOrReplaceInTx(list);
                    getServerEquipmentData();
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    ToastUtils.show("下载从业评估考评人员数据失败");
                    mDialogProgress.dismiss();
                    LogUtil.e("getServerAssessData:" + throwable.getMessage());
                }
            });
    }

    /**从业设备数据*/
    public void getServerEquipmentData() {
        HttpServiceManager.getInstance().getHttpService()
            .getServerData(HttpUtil.initUrl(), HttpUtil.initQueryMethod("getEntEquipmentList"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>() {
                @Override
                public void accept(ResponseBody responseBody) throws Exception {
                    String json = responseBody.string();
                    Type type = new TypeToken<List<EntEquipmentJson>>(){}.getType();
                    List<EntEquipmentJson> list = new Gson().fromJson(json, type);
                    for (EntEquipmentJson equipmentJson : list){
                        equipmentJson.setUPLOADSTATUS(2);
                    }
                    EntEquipmentDao.insertOrReplaceInTx(list);
                    mDialogProgress.dismiss();
                    ToastUtils.show("下载完成");
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    ToastUtils.show("下载从业评估设备数据失败");
                    mDialogProgress.dismiss();
                    LogUtil.e("getServerAssessData:" + throwable.getMessage());
                }
            });
    }

    /**下载所选能力项的打分数据*/
    private void getCaTestdata(){
        Map<String,String> params = new HashMap<>();
        params.put("eid", String.valueOf(eid));
        params.put("testdate", String.valueOf(testdate));
        HttpServiceManager.getInstance().getHttpService()
            .getServerData(HttpUtil.initUrl(), HttpUtil.initQueryParams("getCaTestList",params))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>() {
                @Override
                public void accept(ResponseBody responseBody) throws Exception {
                    String json = responseBody.string();
                    Type type = new TypeToken<List<CaTestJson>>(){}.getType();
                    List<CaTestJson> list = new Gson().fromJson(json, type);
                    if (list != null){
                        if (list.size() > 0){
                            CaTestDao.update(list.get(0).getCID(), list);//先删除再插入，不是update
                            ToastUtils.show("下载成功");
                        }else {
                            ToastUtils.show("服务器无此项目数据,不覆盖");
                        }
                    }else{
                        ToastUtils.show("服务器无此项目数据,不覆盖");
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    ToastUtils.show("获取其他打分项列表失败,检查网络或重新登录");
                    Log.e("获取其他打分项列表失败",throwable.getMessage());
                }
            });
    }

    /**获取从业能力*/
    private void getWorkAbility(String name){
        Map<String, String> params = new HashMap<>();
        params.put("enterprise_name", name);
        HttpServiceManager.getInstance().getHttpService()
            .getServerData(HttpUtil.initUrl(), HttpUtil.initQueryParams("getWorkAbilityList",params))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>() {
                @Override
                public void accept(ResponseBody responseBody) throws Exception {
                    String json = responseBody.string();
                    Type type = new TypeToken<List<WorkAbilityJson>>(){}.getType();
                    List<WorkAbilityJson> list = new Gson().fromJson(json, type);
                    if (list != null ){
                        if (list.size() > 0){
                            WorkAbilityDao.synInsertOrReplaceInTx(list);
                            mListBg.setVisibility(View.GONE);
                            mAdapter = new WorkAssessmentFieldAdapter(WorkAssessmentFieldActivity.this, list,WorkAssessmentFieldActivity.this);
                            mListView.setAdapter(mAdapter);
                        }else {
                            ToastUtils.show("服务器无数据,请创建");
                        }
					}else {
                    	ToastUtils.show("服务器无数据,请创建");
					}
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    ToastUtils.showLong("获取从业能力列表失败,检查网络或重新登录");
                    Log.e("获取从业能力列表失败",throwable.getMessage());
                }
            });
    }

    /**上传从业能力单条(创建)*/
    private void uploadSingleWorkAbility(WorkAbilityJson workAbilityJson){
        Map<String, String> params = UploadParams.getSingleWorkAbilityParams(workAbilityJson);
        HttpServiceManager.getInstance().getHttpService()
            .getServerData(HttpUtil.initUrl(), HttpUtil.initSaveParams("save","workAbilitySave",params))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>() {
                @Override
                public void accept(ResponseBody responseBody) throws Exception {
                    String json = responseBody.string();
                    if (json.length() > 10){
                        mDialogProgress.dismiss();
                        ToastUtils.show("登录超时，请重新登录");
                        return;
                    }else {
                        //workAbilityJson.setSTATUS(1);// 先存1，防止上传过程失败导致能力上传，打分未上传完却因为能力为2无法上传打分
                        List<CaTestJson> caTestJsonList = CaTestDao.queryCidAll(cid,"1");//1完成，2未完成。
                        if (caTestJsonList.size() > 0){
                            String str = json.substring(0,1);
                            if (str.equals("\"")){ // 判断返回的json是否多了""
                                String js = json.substring(json.indexOf("\"") + 1, json.lastIndexOf("\""));
                                uploadCaTest(caTestJsonList, js, workAbilityJson);
                            }else {
                                uploadCaTest(caTestJsonList, json, workAbilityJson);
                            }
                        }else {
                            mDialogProgress.dismiss();
                            ToastUtils.show("没有需要上传的打分项");
                        }
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    mDialogProgress.dismiss();
                    ToastUtils.showLong("上传从业能力数据失败,检查网络或重新登录");
                    Log.e("上传从业能力数据失败",throwable.getMessage());
                }
            });
    }

    /**上传单条从业能力之后所属其他打分项*/
    private void uploadCaTest(List<CaTestJson> caTestJsonList, String arg1, WorkAbilityJson workAbilityJson){
        Map<String, String> params = UploadParams.getCaTestParams(caTestJsonList.get(i), arg1);
        HttpServiceManager.getInstance().getHttpService()
            .getServerData(HttpUtil.initUrl(), HttpUtil.initSaveParams("save","caTestSave",params))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>() {
                @Override
                public void accept(ResponseBody responseBody) throws Exception {
                    String json = responseBody.string();
                    caTestJsonList.get(i).setCTID(Long.valueOf(json));
                    caTestJsonList.get(i).setUPLOADSTATUS(1);
                    if (caTestJsonList.size() - 1 > i){
                        String itemSubstring = caTestJsonList.get(i).getITEM().substring(0,2);
                        i = i+1;
                        if (itemSubstring.equals("2.1") || itemSubstring.equals("2.2") || itemSubstring.equals("2.3")){//跳转上传人员
                            List<PersonnelJson> list = PersonnelDao.query(String.valueOf(workAbilityJson.getEID()),
                                    String.valueOf(workAbilityJson.getWID()),itemSubstring);
                            mPersonnelJsonList = list;
                            uploadPersonnel(caTestJsonList, workAbilityJson, list, arg1);
                        }else if (itemSubstring.equals("2.6") || itemSubstring.equals("2.7")){//跳转上传设备
                            List<EntEquipmentJson> list = EntEquipmentDao.query(String.valueOf(workAbilityJson.getEID()),
                                String.valueOf(workAbilityJson.getWID()),itemSubstring);
                            mEquipmentJsonList = list;
                            uploadEquipment(caTestJsonList, workAbilityJson, list, arg1);
                        }
                        uploadCaTest(caTestJsonList, arg1, workAbilityJson);
                    }else {
                       if (mEquipmentJsonList != null){// 更新设备本地上传状态
                           for (EntEquipmentJson equipmentJson : mEquipmentJsonList){
                               equipmentJson.setUPLOADSTATUS(2);
                           }
                           EntEquipmentDao.updateList(mEquipmentJsonList);
                           mEquipmentJsonList = null;
                           e = 0;
                       }
                       if (mPersonnelJsonList != null){// 更新人员本地上传状态
                           for (PersonnelJson personnelJson : mPersonnelJsonList){
                               personnelJson.setUPLOADSTATUS(2);
                           }
                           PersonnelDao.updateList(mPersonnelJsonList);
                           p = 0;
                           mPersonnelJsonList = null;
                       }
                       for (CaTestJson caTestJson : caTestJsonList){
                           caTestJson.setUPLOADSTATUS(2);
                       }
                       CaTestDao.updateList(caTestJsonList);
                       i = 0;
                       workAbilityJson.setSTATUS(2);
                       WorkAbilityDao.update(workAbilityJson);
                       mDialogProgress.dismiss();
                       ToastUtils.show("上传成功");
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    mDialogProgress.dismiss();
                    ToastUtils.showLong("上传其他打分项数据失败,检查网络或重新登录");
                    Log.e("上传其他打分项数据失败",throwable.getMessage());
                }
            });
    }

    /**上传所属人员表*/
    private void uploadPersonnel(List<CaTestJson> caTestJsonList, WorkAbilityJson workAbilityJson, List<PersonnelJson> personnelJsonList, String arg1){
        Map<String, String> params = UploadParams.getPersonnelParams(personnelJsonList.get(p));
        HttpServiceManager.getInstance().getHttpService()
            .getServerData(HttpUtil.initUrl(), HttpUtil.initSaveParams("save","personnelSave",params))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>() {
                @Override
                public void accept(ResponseBody responseBody) throws Exception {
                    String json = responseBody.string();
                    personnelJsonList.get(p).setUPLOADSTATUS(2);
                    if (personnelJsonList.size() - 1 > p){
                        p = p+1;
                        uploadPersonnel(caTestJsonList, workAbilityJson, personnelJsonList, arg1);
                    }else {
                        if (caTestJsonList.size() - 1 > i){
                            uploadCaTest(caTestJsonList, arg1, workAbilityJson);
                        }else {
                            if (mEquipmentJsonList != null){// 更新设备本地上传状态
                                for (EntEquipmentJson equipmentJson : mEquipmentJsonList){
                                    equipmentJson.setUPLOADSTATUS(2);
                                }
                                EntEquipmentDao.updateList(mEquipmentJsonList);
                                mEquipmentJsonList = null;
                                e = 0;
                            }
                            if (mPersonnelJsonList != null){// 更新人员本地上传状态
                                for (PersonnelJson personnelJson : mPersonnelJsonList){
                                    personnelJson.setUPLOADSTATUS(2);
                                }
                                PersonnelDao.updateList(mPersonnelJsonList);
                                p = 0;
                                mPersonnelJsonList = null;
                            }
                            for (CaTestJson caTestJson : caTestJsonList){// 更新打分表本地上传状态
                                caTestJson.setUPLOADSTATUS(2);
                            }
                            CaTestDao.insertOrReplaceInTx(caTestJsonList);
                            i = 0;
                            workAbilityJson.setSTATUS(2);
                            WorkAbilityDao.update(workAbilityJson);
                            mDialogProgress.dismiss();
                            ToastUtils.show("上传成功");
                        }
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    mDialogProgress.dismiss();
                    ToastUtils.showLong("上传人员数据失败,检查网络或重新登录");
                    Log.e("上传人员数据失败",throwable.getMessage());
                }
            });
    }

    /**上传所属设备表*/
    private void uploadEquipment(List<CaTestJson> caTestJsonList, WorkAbilityJson workAbilityJson, List<EntEquipmentJson> equipmentJsonList, String arg1){
        Map<String, String> params = UploadParams.getEquipmentlParams(equipmentJsonList.get(e));
        HttpServiceManager.getInstance().getHttpService()
            .getServerData(HttpUtil.initUrl(), HttpUtil.initSaveParams("save","entEquipmentSave",params))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>() {
                @Override
                public void accept(ResponseBody responseBody) throws Exception {
                    String json = responseBody.string();
                    equipmentJsonList.get(p).setUPLOADSTATUS(2);
                    if (equipmentJsonList.size() - 1 > e){
                        e = e+1;
                        uploadEquipment(caTestJsonList, workAbilityJson, equipmentJsonList, arg1);
                    }else {
                        if (caTestJsonList.size() - 1 > i){
                            uploadCaTest(caTestJsonList, arg1, workAbilityJson);
                        }else {
                            if (mEquipmentJsonList != null){// 更新设备本地上传状态
                                for (EntEquipmentJson equipmentJson : mEquipmentJsonList){
                                    equipmentJson.setUPLOADSTATUS(2);
                                }
                                EntEquipmentDao.updateList(mEquipmentJsonList);
                                mEquipmentJsonList = null;
                                e = 0;
                            }
                            if (mPersonnelJsonList != null){// 更新人员本地上传状态
                                for (PersonnelJson personnelJson : mPersonnelJsonList){
                                    personnelJson.setUPLOADSTATUS(2);
                                }
                                PersonnelDao.updateList(mPersonnelJsonList);
                                p = 0;
                                mPersonnelJsonList = null;
                            }
                            for (CaTestJson caTestJson : caTestJsonList){// 更新打分表本地上传状态
                                caTestJson.setUPLOADSTATUS(2);
                            }
                            CaTestDao.insertOrReplaceInTx(caTestJsonList);
                            i = 0;
                            workAbilityJson.setSTATUS(2);
                            WorkAbilityDao.update(workAbilityJson);
                            mDialogProgress.dismiss();
                            ToastUtils.show("上传成功");
                        }
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    mDialogProgress.dismiss();
                    ToastUtils.showLong("上传设备数据失败,检查网络或重新登录");
                    Log.e("上传设备数据失败",throwable.getMessage());
                }
            });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }
}
