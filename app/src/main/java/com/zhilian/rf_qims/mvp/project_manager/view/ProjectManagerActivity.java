package com.zhilian.rf_qims.mvp.project_manager.view;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.internal.http.multipart.FilePart;
import com.android.internal.http.multipart.Part;
import com.android.internal.http.multipart.StringPart;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.colin.constant.Constants;
import com.colin.http.HttpServiceManager;
import com.colin.http.HttpUtil;
import com.colin.http.Ip;
import com.colin.listener.TextWatcherImpl;
import com.colin.utils.LogUtil;
import com.colin.utils.SharedPreferencesUtil;
import com.google.gson.Gson;
import com.zhilian.api.StrKit;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.adapter.CompanyAdapter;
import com.zhilian.rf_qims.adapter.ProjectAdapter;
import com.zhilian.rf_qims.base.BaseActivity;
import com.zhilian.rf_qims.bean.UploadMesBean;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.dao.ProjectDao;
import com.zhilian.rf_qims.dao.SampleCheckAlterLogDao;
import com.zhilian.rf_qims.dao.SampleCheckDao;
import com.zhilian.rf_qims.dao.SampleDao;
import com.zhilian.rf_qims.dao.ScenePicDao;
import com.zhilian.rf_qims.dao.SignPicDao;
import com.zhilian.rf_qims.entity.Company;
import com.zhilian.rf_qims.entity.Project;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.entity.SampleCheck;
import com.zhilian.rf_qims.entity.SampleCheckAlterLog;
import com.zhilian.rf_qims.entity.ScenePic;
import com.zhilian.rf_qims.entity.SignPic;
import com.zhilian.rf_qims.mvp.project_detail.view.ProjectAddActivity;
import com.zhilian.rf_qims.mvp.project_detail.view.ProjectUpdateActivity;
import com.zhilian.rf_qims.mvp.project_manager.presenter.ProjectPresenter;
import com.zhilian.rf_qims.mvp.sample_manager.view.SampleManagerActivity;
import com.zhilian.rf_qims.service.MultipartRequest;
import com.zhilian.rf_qims.util.NetJudge;
import com.zhilian.rf_qims.util.ToastUtils;
import com.zhilian.rf_qims.util.UploadParams;
import com.zhilian.rf_qims.widget.DialogProgress;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import static com.zhilian.rf_qims.R.id.upload;

public class ProjectManagerActivity extends BaseActivity<ProjectPresenter> implements IProjectManagerView {
    private Context mContext = this;
    @BindView(R.id.list)
    SwipeMenuListView mListView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private Company mCurrentCompany;
    private List<Project> mProjects = new ArrayList<>();
    private ProjectAdapter mProjectAdapter;
    DialogProgress muploadDialog;
    SwipeMenuCreator creator = new SwipeMenuCreator() {

        @Override
        public void create(SwipeMenu menu) {
            // create "delete" item
            SwipeMenuItem updateItem = new SwipeMenuItem(
                    getApplicationContext());
            updateItem.setTitle("更新");
            updateItem.setTitleSize(18);
            updateItem.setTitleColor(Color.WHITE);
            // set item background
            updateItem.setBackground(new ColorDrawable(Color.rgb(0x93,
                    0xe5, 0xe8)));
            // set item width
            updateItem.setWidth(dp2px(90));
            // set a icon
            //deleteItem.setIcon(R.drawable.ic_delete);
            // add to menu
            menu.addMenuItem(updateItem);
            // create "delete" item
            SwipeMenuItem deleteItem = new SwipeMenuItem(
                    getApplicationContext());
            deleteItem.setTitle("删除");
            deleteItem.setTitleSize(18);
            deleteItem.setTitleColor(Color.WHITE);
            // set item background
            deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                    0x3E, 0x25)));
            // set item width
            deleteItem.setWidth(dp2px(90));
            // set a icon
            //deleteItem.setIcon(R.drawable.ic_delete);
            // add to menu
            menu.addMenuItem(deleteItem);
        }
    };


    @Override
    protected int loadLayoutResource() {
        return R.layout.activity_project;
    }

    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (0x12 == resultCode) {
            /*switch (requestCode){
                case 0x11:

					break;
				case 0x12:
					break;
			}*/
            presenter.loadProjectsByEid(mCurrentCompany.getId());
        }
    }

    //选中公司更新project
    @Override
    public void updateProjectsList(List<Project> list) {
        mProjects.clear();
        for (Project bean : list) {
            mProjects.add(bean);
        }
        mProjectAdapter.notifyDataSetChanged();
    }

    //搜索刷新公司
    @Override
    public void updateEnterpriseList(List<Company> list) {
        mCompanies = list;
        mLvEnterprise.setAdapter(new CompanyAdapter(this, list));
    }

    @Override
    public void onRemoveProject() {
        //// TODO: 2018/4/29 删除项目
//        Toast.makeText(ProjectManagerActivity.this, R.string.project_delete, Toast.LENGTH_SHORT).show();
        presenter.loadProjectsByEid(mCurrentCompany.getId());
    }

    @Override
    public void hideDiaProgress() {
        dialogProgress.dismiss();
    }

    //网络数据全部下载完毕
    @Override
    public void updateAllByServerData() {
        if (GreenDaoManager.getInstance().getNewSession().getCompanyDao().queryBuilder().list().size() > 0) {
            mCurrentCompany = GreenDaoManager.getInstance().getNewSession().getCompanyDao().queryBuilder().list().get(0);
            mToolbar.setTitle(mCurrentCompany.getName());
            presenter.loadProjectsByEid(mCurrentCompany.getId());
            new SharedPreferencesUtil(ProjectManagerActivity.this, Constants.XML_NAME)
                    .saveObject("company", new Gson().toJson(mCurrentCompany));
        } else {
            Toast.makeText(ProjectManagerActivity.this, "没有任何信息", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void initView() {
        muploadDialog = new DialogProgress(ProjectManagerActivity.this, "正在上传中");
        initToolBar();
        mProjectAdapter = new ProjectAdapter(this, mProjects);
        mListView.setAdapter(mProjectAdapter);
        // set creator
        mListView.setMenuCreator(creator);// step 2. listener item click event
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Project item = mProjects.get(position);
                switch (index) {
                    case 0:
                        Intent intent = new Intent(mContext, ProjectUpdateActivity.class);
//                        intent.putExtra("status", 1);
                        intent.putExtra("project", item);
                        startActivityForResult(intent, 0x11, ActivityOptionsCompat.makeSceneTransitionAnimation(ProjectManagerActivity.this).toBundle());
                        break;
                    case 1:
//                        presenter.removeProject(item);
                        List<Sample> samples = GreenDaoManager.getInstance().getNewSession().getSampleDao().queryBuilder().where(SampleDao.Properties.Pid.eq(item.getId())).list();
                        if (samples != null && samples.size() > 0) {
                            GreenDaoManager.getInstance().getNewSession().getSampleDao().deleteInTx(samples);
                        }
                        //删除图片
                        for (int i = 0; i < samples.size(); i++) {
                            List<ScenePic> scenePics = GreenDaoManager.getInstance().getNewSession().getScenePicDao().queryBuilder().where(ScenePicDao
                                    .Properties.Sid.eq(samples.get(i).getId())).list();
                            for (int j = 0; j < scenePics.size(); j++) {
                                GreenDaoManager.getInstance().getNewSession().getScenePicDao().delete(scenePics.get(j));
                            }
                            //删除签名图片
                            SignPic signPic = GreenDaoManager.getInstance().getNewSession().getSignPicDao().queryBuilder().where(SignPicDao
                                    .Properties.Sid.eq(samples.get(i).getId())).unique();
                            if (signPic != null) {
                                GreenDaoManager.getInstance().getNewSession().getSignPicDao().delete(signPic);
                            }
                        }

                        List<SampleCheck> sampleChecks = GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().queryBuilder().where(SampleCheckDao.Properties.Pid.eq(item.getId())).list();
                        if (sampleChecks != null && sampleChecks.size() > 0) {
                            GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().deleteInTx(sampleChecks);
                        }
                        GreenDaoManager.getInstance().getNewSession().getProjectDao().delete(item);
                        presenter.loadProjectsByEid(mCurrentCompany.getId());
                        break;
                }
                return true;
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ProjectManagerActivity.this, SampleManagerActivity.class);
                intent.putExtra("project", mProjects.get(i));
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(ProjectManagerActivity.this).toBundle());
            }
        });

        SharedPreferencesUtil util = new SharedPreferencesUtil(this, Constants.XML_NAME);
        String company = util.getObject("company");
        if (StrKit.notBlank(company)) {
            mCurrentCompany = new Gson().fromJson(company, Company.class);
            LogUtil.e(mCurrentCompany.toString());
            mToolbar.setTitle(mCurrentCompany.getName());
        }
        if (null != mCurrentCompany) {
            presenter.loadProjectsByEid(mCurrentCompany.getId());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.project_toolbar, menu);
        return true;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        mToolbar.setNavigationIcon(R.drawable.md_nav_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add:
                        /**
                         * 1、添加项目
                         */
                        if (null != mCurrentCompany) {
                            Intent intent = new Intent(ProjectManagerActivity.this, ProjectAddActivity.class);
//                            intent.putExtra("status", 0);
//                            startActivityForResult(intent, 0x10);
                            startActivityForResult(intent, 0x10, ActivityOptionsCompat.makeSceneTransitionAnimation(ProjectManagerActivity.this).toBundle());
                        } else {
                            Toast.makeText(ProjectManagerActivity.this, "请先点击最右边下载基础数据", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.download:
                        new AlertDialog.Builder(ProjectManagerActivity.this).setMessage("确认需要下载吗?")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (NetJudge.judge(ProjectManagerActivity.this)){ //判断是否有网络
                                        dialogProgress.show();
                                        GreenDaoManager.getInstance().getNewSession().getProjectDao().deleteAll();
                                        GreenDaoManager.getInstance().getNewSession().getSampleDao().deleteAll();
                                        GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().deleteAll();
                                        GreenDaoManager.getInstance().getNewSession().getScenePicDao().deleteAll();
                                        GreenDaoManager.getInstance().getNewSession().getSignPicDao().deleteAll();
                                        presenter.downloadServerData();//同步协会检测项目数据
                                    }else {
                                        ToastUtils.show("现在是离线状态,请登录");
                                    }
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                        break;
                    case R.id.select:
                        if (mCurrentCompany == null) {
                            Toast.makeText(ProjectManagerActivity.this, "请先点击最右边下载基础数据", Toast.LENGTH_SHORT).show();
                        } else {
                            openEnterpriseList();
                        }

                        break;
                    case upload:
                        if (NetJudge.judge(ProjectManagerActivity.this)) { //判断是否有网络
                            uploadServerMes();
                        }else {
                            ToastUtils.show("现在是离线状态,请登录");
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void uploadServerMes() {
        //先上传服务器的委托单
        if (mCurrentCompany == null) {
            Toast.makeText(ProjectManagerActivity.this, "请先点击最右边下载基础数据", Toast.LENGTH_SHORT).show();
        } else {
            //选中的项目
            mServerProjects = GreenDaoManager.getInstance()
                    .getNewSession().getProjectDao().queryBuilder()
                    .where(ProjectDao.Properties.EntrustId.eq(mCurrentCompany.getId()),
                            ProjectDao.Properties.Isselected.eq(1)).list();
            if (mServerProjects != null && mServerProjects.size() <= 0) {
//                presenter.loadProjectsByEid(mCurrentCompany.getId());
                Toast.makeText(ProjectManagerActivity.this, "没有选中项目上传", Toast.LENGTH_SHORT).show();
                return;
            }

            for (int i = 0; i < mServerProjects.size(); i++) {
                List<Sample> samples = GreenDaoManager.getInstance().getNewSession().getSampleDao().queryBuilder()
                        .where(SampleDao.Properties.Pid.eq(mServerProjects.get(i).getId()),
                            SampleDao.Properties.Isselected.eq(1)).list();
                if (samples != null && samples.size() > 0) {

                } else {
                    Toast.makeText(ProjectManagerActivity.this, "请确保每个委托单都有可上传的样品检测信息", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            proindex = 0;
            muploadDialog.show();
            uploadEdSamles.clear();
            uploadScenepics.clear();
            uploadSignPics.clear();
            uploadServerProject(mServerProjects.get(proindex));

        }
    }

    /**
     * 打开待检企业列表
     */
    PopupWindow popupWindow;
    EditText mEtSearch;
    ImageView mIvDel;
    TextView mTvSearch;
    ListView mLvEnterprise;
    List<Company> mCompanies;
    TextView mTvCancel;

    private void openEnterpriseList() {
        if (popupWindow == null) {
            View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_enterprise, null);
            mEtSearch = (EditText) contentView.findViewById(R.id.et_search);
            mIvDel = (ImageView) contentView.findViewById(R.id.iv_del);
            mTvSearch = (TextView) contentView.findViewById(R.id.tv_search);
            mLvEnterprise = (ListView) contentView.findViewById(R.id.lv_enterprise);
            mTvCancel = (TextView) contentView.findViewById(R.id.tv_cancel);
            presenter.loadAllEnterprises();
            mTvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (popupWindow != null)
                        popupWindow.dismiss();
                }
            });
            mEtSearch.addTextChangedListener(new TextWatcherImpl() {
                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() == 0) {
                        mIvDel.setVisibility(View.GONE);//当文本框为空时，则叉叉消失
                    } else {
                        mTvSearch.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                searchEnterpriseByCondition(mEtSearch.getText().toString());
                            }
                        });
                        mIvDel.setVisibility(View.VISIBLE);//当文本框不为空时，出现叉叉
                    }
                }
            });
            mIvDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEtSearch.setText("");
                }
            });
            popupWindow = new PopupWindow(contentView);
            popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            //popupWindow.setBackgroundDrawable(new BitmapDrawable());

            mLvEnterprise.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (popupWindow != null)
                        popupWindow.dismiss();
                    mCurrentCompany = mCompanies.get(position);
                    new SharedPreferencesUtil(ProjectManagerActivity.this, Constants.XML_NAME)
                            .saveObject("company", new Gson().toJson(mCurrentCompany));
                    mToolbar.setTitle(mCurrentCompany.getName());
                    presenter.loadProjectsByEid(mCurrentCompany.getId());
                }
            });
        }
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(mToolbar);
    }


    public void searchEnterpriseByCondition(String condition) {
        presenter.searchEnterpriseByCondition(condition);
    }

    List<Project> mServerProjects = new ArrayList<>();
    List<Sample> mServerSamples = new ArrayList<>();
    private Project mcurrentProject = new Project();
    List<Sample> uploadEdSamles = new ArrayList<>();
    List<ScenePic> uploadScenepics = new ArrayList<>();
    List<SignPic> uploadSignPics = new ArrayList<>();
    int samindex;
    int proindex;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mServerSamples = GreenDaoManager.getInstance().getNewSession().getSampleDao().queryBuilder()
                            .where(SampleDao.Properties.Pid.eq(mcurrentProject.getId()),
                                   SampleDao.Properties.Isselected.eq(1)).list();
//                    mServerSampleChecks = GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().queryBuilder()
//                            .where(SampleCheckDao.Properties.Pid.eq(mcurrentProject.getId()), SampleCheckDao.Properties.Status.notEq(2)).list();
//                    for (int i = 0; i < mServerSamples.size(); i++) {
                    Log.d("upload", mServerProjects.size() + "//" + mServerSamples.size() + "//");
                    samindex = 0;
                    uploadSampleMes(mServerSamples.get(samindex));
//                        break;
//                    }
                    break;
                case 1:
                    if (samindex >= mServerSamples.size() - 1) { //判断是否上传完当前委托单所有样品
                        if (proindex >= mServerProjects.size() - 1) {   //判断是否存在下一个委托单
                            //已把服务的委托单全部上传完毕
//                            muploadDialog.dismiss();
//                            Toast.makeText(ProjectManagerActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
//                            presenter.loadProjectsByEid(mCurrentCompany.getId());
                            //最后上传样品图片
                            uploadSamplePic();
                            return;
                        } else {
                            proindex++;
                            uploadServerProject(mServerProjects.get(proindex));
                        }
                    } else { //上传下一个样品
                        samindex++;
                        uploadSampleMes(mServerSamples.get(samindex));
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        }
    };

    //上传样品图片
    private void uploadSamplePic() {
        for (int i = 0; i < uploadEdSamles.size(); i++) {
            List<ScenePic> scenePics = GreenDaoManager.getInstance().getNewSession().getScenePicDao().queryBuilder()
                    .where(ScenePicDao.Properties.Sid.eq(uploadEdSamles.get(i).getId()), ScenePicDao.Properties.Isupload.notEq(0)).list();
            for (int j = 0; j < scenePics.size(); j++) {
                uploadScenepics.add(scenePics.get(j));
            }
        }
        if (uploadScenepics.size() == 0) {
            muploadDialog.dismiss();
            Toast.makeText(ProjectManagerActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
            presenter.loadProjectsByEid(mCurrentCompany.getId());
            return;
//            uploadSignPic();
        } else {
            imageSampleUpload(uploadScenepics.get(0), 0, uploadScenepics.size());
        }

    }

//    //上传签名图片
//    private void uploadSignPic() {
//        // 构造参数列表
//        for (int i = 0; i < uploadEdSamles.size(); i++) {
//            SignPic signPic = GreenDaoManager.getInstance().getNewSession().getSignPicDao().queryBuilder()
//                    .where(SignPicDao.Properties.Sid.eq(uploadEdSamles.get(i).getId())).unique();
//            if (signPic != null) {
//                uploadSignPics.add(signPic);
//            }
//        }
//        if (uploadSignPics.size() == 0) {
//            muploadDialog.dismiss();
//            Toast.makeText(ProjectManagerActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
//            presenter.loadProjectsByEid(mCurrentCompany.getId());
//            return;
//        }
//        imageSignUpload(uploadSignPics.get(0), 0, uploadSignPics.size());
//    }

    private void uploadSampleCheck(SampleCheck sampleCheck, Sample sample) {
        Map<String, String> params = UploadParams.getSampleCheckParams(sampleCheck);
        HttpServiceManager.getInstance().getHttpService()
                .getServerData(HttpUtil.initUrl(), HttpUtil.initSaveParams("save", "updateDetectFH", params))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        Log.d("upload", "samplecheck" + samindex + "//" + json);
                        UploadMesBean uploadMesBean = new Gson().fromJson(json, UploadMesBean.class);
                        if (uploadMesBean.getStatus().equals("0")) {
                            muploadDialog.dismiss();
                            Toast.makeText(ProjectManagerActivity.this, uploadMesBean.getMsg(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        List<SampleCheckAlterLog> list = GreenDaoManager.getInstance().getNewSession().getSampleCheckAlterLogDao().queryBuilder().where(
                            SampleCheckAlterLogDao.Properties.SampleCheckId.eq(sampleCheck.getId()),
                            SampleCheckAlterLogDao.Properties.Statu.notEq(1)).list();
                        if (list.size() > 0){ //样品检测是否存在修改日志
                            List<SampleCheckAlterLog> logList = new ArrayList<>();
                            for (SampleCheckAlterLog sampleCheckAlterLog : list){
                                sampleCheckAlterLog.setSampleid(String.valueOf(sampleCheck.getId()));
                                logList.add(sampleCheckAlterLog);
                            }
                            GreenDaoManager.getInstance().getNewSession().getSampleCheckAlterLogDao().updateInTx(logList);
                            uploadSampleCheckAlterLog(sampleCheck, sample, logList, 0);
                        }else {
                            sampleCheck.setStatus(2);
                            sample.setStatus(2);
                            GreenDaoManager.getInstance().getNewSession().getSampleDao().update(sample);
                            GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().update(sampleCheck);
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        muploadDialog.dismiss();
                        Log.d("upload", "samplecheck--" + throwable.getMessage());
                    }
                });
    }
    //上传样品检测修改日志（2019.3.28）
    private void uploadSampleCheckAlterLog(SampleCheck sampleCheck, Sample sample, List<SampleCheckAlterLog> logs, int num){
        Map<String,String> params = UploadParams.getSampleCheckAlterLogParams(logs.get(num));
        HttpServiceManager.getInstance().getHttpService()
            .getServerData(HttpUtil.initUrl(),HttpUtil.initSaveParams("save","alterLogSave",params))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>() {
                @Override
                public void accept(ResponseBody responseBody) throws Exception {
                    String json = responseBody.string();
                    if (json.equals("true")) {
                        int number;
                        if (logs.size()-1 > num){
                            number = num + 1;
                            uploadSampleCheckAlterLog(sampleCheck, sample, logs, number);
                        }else {
                            sampleCheck.setStatus(2);
                            sample.setStatus(2);
                            List<SampleCheckAlterLog> sampleCheckAlterLogs = new ArrayList<>();
                            for (SampleCheckAlterLog sampleCheckAlterLog : logs){
                                sampleCheckAlterLog.setStatu(1);
                                sampleCheckAlterLogs.add(sampleCheckAlterLog);
                            }
                            GreenDaoManager.getInstance().getNewSession().getSampleDao().update(sample);
                            GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().update(sampleCheck);
                            GreenDaoManager.getInstance().getNewSession().getSampleCheckAlterLogDao().updateInTx(sampleCheckAlterLogs);
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        }
                    }else {
                        muploadDialog.dismiss();
                        Toast.makeText(ProjectManagerActivity.this, "上传失败！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    muploadDialog.dismiss();
                    Log.d("upload", "uploadSampleCheckAlterLog--" + throwable.getMessage());
                }
            });
    }

    private void uploadSampleMes(Sample sample) {
        Map<String, String> params = UploadParams.getSampleParams(sample, mCurrentCompany);
        Log.d("upload", HttpUtil.initUrl());
        Log.d("upload", HttpUtil.initSaveParams("save", "updateSampleFH", params));
        HttpServiceManager.getInstance()
                .getHttpService()
                .getServerData(HttpUtil.initUrl(), HttpUtil.initSaveParams("save", "updateSampleFH", params))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        UploadMesBean uploadMesBean = new Gson().fromJson(json, UploadMesBean.class);
                        if (uploadMesBean.getStatus().equals("0")) {
                            muploadDialog.dismiss();
                            Toast.makeText(ProjectManagerActivity.this, uploadMesBean.getMsg(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Log.d("upload", "samplemes" + samindex + "//" + json);
                        if (sample.getStatus() == 1) {//更改样品信息id
                            //更改样品图片sid
                            List<ScenePic> scenePics = GreenDaoManager.getInstance().getNewSession().getScenePicDao().queryBuilder().
                                    where(ScenePicDao.Properties.Sid.eq(sample.getId())).list();
                            if (scenePics != null && scenePics.size() > 0) {
                                for (int i = 0; i < scenePics.size(); i++) {
                                    ScenePic scenePic = scenePics.get(i);
                                    scenePic.setSid(uploadMesBean.getId());
                                    GreenDaoManager.getInstance().getNewSession().getScenePicDao().update(scenePic);
                                }
                            }
                            //更改签名sid
                            SignPic signPic = GreenDaoManager.getInstance().getNewSession().getSignPicDao().queryBuilder().where(SignPicDao.Properties.Sid
                                    .eq(sample.getId())).unique();
                            if (signPic != null) {
                                signPic.setSid(uploadMesBean.getId());
                                GreenDaoManager.getInstance().getNewSession().getSignPicDao().update(signPic);
                            }
                            //更改检测信息id
                            SampleCheck sampleCheck = GreenDaoManager.getInstance().getNewSession().getSampleCheckDao()
                                    .queryBuilder().where(SampleCheckDao.Properties.Id.eq(sample.getId())).unique();
                            List<SampleCheckAlterLog> sampleCheckAlterLog = GreenDaoManager.getInstance().getNewSession().getSampleCheckAlterLogDao()
                                    .queryBuilder().where(SampleCheckAlterLogDao.Properties.SampleCheckId.eq(sample.getId())).list();
                            GreenDaoManager.getInstance().getNewSession().getSampleDao().delete(sample);
                            GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().delete(sampleCheck);
                            GreenDaoManager.getInstance().getNewSession().getSampleCheckAlterLogDao().deleteInTx(sampleCheckAlterLog);
                            Sample sample1 = new Sample();
                            sample1 = sample;
                            sample1.setId(uploadMesBean.getId());

                            sample1.setStatus(2);
                            SampleCheck sampleCheck1 = new SampleCheck();
                            sampleCheck1 = sampleCheck;
                            sampleCheck1.setId(sample1.getId());
                            List<SampleCheckAlterLog> sampleCheckAlterLogs = new ArrayList<>();
                            sampleCheckAlterLogs = sampleCheckAlterLog;
                            for (SampleCheckAlterLog sampleCheckAlterLog1 : sampleCheckAlterLogs){
                                sampleCheckAlterLog1.setSampleCheckId(String.valueOf(sample1.getId()));
                            }
                            GreenDaoManager.getInstance().getNewSession().getSampleDao().insert(sample1);
                            GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().insert(sampleCheck1);
                            GreenDaoManager.getInstance().getNewSession().getSampleCheckAlterLogDao().insertInTx(sampleCheckAlterLogs);
//                            uploadSampleCheck(sampleCheck1, sample1);
                            uploadEdSamles.add(sample1);
                            uploadSign(sample1);
                        } else {
//                            sample.setStatus(2);
                            uploadEdSamles.add(sample);
                            GreenDaoManager.getInstance().getNewSession().getSampleDao().update(sample);
                            uploadSign(sample);
//                            uploadSampleCheck(GreenDaoManager.getInstance().getNewSession().getSampleCheckDao()
//                                    .queryBuilder().where(SampleCheckDao.Properties.Id.eq(sample.getId())).unique(), sample);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        muploadDialog.dismiss();
                        Log.d("upload", "samplemes" + throwable.getMessage());
                    }
                });
    }


    public void uploadServerProject(Project project) {
        Map<String, String> params = UploadParams.getProjectParams(project, mCurrentCompany);
        HttpServiceManager.getInstance()
                .getHttpService()
                .getServerData(HttpUtil.initUrl(), HttpUtil.initSaveParams("save", "updateCommissionFH", params))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        Log.d("upload", "项目上传" + json);
                        UploadMesBean uploadMesBean = new Gson().fromJson(json, UploadMesBean.class);
                        if (uploadMesBean.getStatus().equals("0")) {
                            muploadDialog.dismiss();
                            Toast.makeText(ProjectManagerActivity.this, uploadMesBean.getMsg(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (project.getStatus() == 1) {// 更改样品的pid
                            List<Sample> samples = GreenDaoManager.getInstance().getNewSession().getSampleDao().queryBuilder()
                                    .where(SampleDao.Properties.Pid.eq(project.getId())).list();
                            List<SampleCheck> sampleChecks = GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().queryBuilder()
                                    .where(SampleCheckDao.Properties.Pid.eq(project.getId())).list();
                            for (int i = 0; i < samples.size(); i++) {
                                Sample sample = samples.get(i);
                                sample.setPid(uploadMesBean.getId());
                                SampleCheck sampleCheck = sampleChecks.get(i);
                                sampleCheck.setPid(uploadMesBean.getId());
                                GreenDaoManager.getInstance().getNewSession().getSampleDao().update(sample);
                                GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().update(sampleCheck);
                            }
                            GreenDaoManager.getInstance().getNewSession().getProjectDao().delete(project);
                            Project project1 = new Project();//更改项目id
                            project1 = project;
                            project1.setId(uploadMesBean.getId());
                            project1.setStatus(2);
                            mcurrentProject = project1;
                            GreenDaoManager.getInstance().getNewSession().getProjectDao().insert(project1);
                        } else {
                            project.setStatus(2);
                            GreenDaoManager.getInstance().getNewSession().getProjectDao().update(project);
                            mcurrentProject = project;
                        }
                        Message message = new Message();
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        muploadDialog.dismiss();
                        Log.d("upload", "项目上传" + throwable.getMessage());
                    }
                });
    }

    private void imageSampleUpload(ScenePic scenePic, int index, int size) {

        // 构造参数列表
        List<Part> partList = new ArrayList<Part>();
        partList.add(new StringPart("id", scenePic.getSid() + ""));
        partList.add(new StringPart("type", "1"));
        try {
            File tempFile = new File(scenePic.getPath());
            partList.add(new FilePart("file", tempFile));// path
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 获取队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = Ip.getIp() + "Main/Upload/uploadAppPhoto";
        // 生成请求
        MultipartRequest profileUpdateRequest = new MultipartRequest(url, partList.toArray(new Part[partList.size()]), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                UploadMesBean uploadMesBean = new Gson().fromJson(response, UploadMesBean.class);
                if (uploadMesBean.getStatus().equals("0")) {
                    muploadDialog.dismiss();
                    Toast.makeText(ProjectManagerActivity.this, uploadMesBean.getMsg(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("upload", "图片上传" + index + "//" + response);
                if (index >= size - 1) {
                    scenePic.setIsupload(0);
                    GreenDaoManager.getInstance().getNewSession().getScenePicDao().update(scenePic);
                    //成功了
                    muploadDialog.dismiss();
                    Toast.makeText(ProjectManagerActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                    presenter.loadProjectsByEid(mCurrentCompany.getId());
                } else {
                    int i = index;
                    i++;
                    scenePic.setIsupload(0);
                    GreenDaoManager.getInstance().getNewSession().getScenePicDao().update(scenePic);
                    imageSampleUpload(uploadScenepics.get(i), i, uploadScenepics.size());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                muploadDialog.dismiss();
                Log.d("MultipartRequest", error.getMessage() + error);
            }
        });
        // 将请求加入队列
        requestQueue.add(profileUpdateRequest);
    }

//    private void imageSignUpload(SignPic signPic, int index, int size) {
//        // 构造参数列表
//        List<Part> partList = new ArrayList<Part>();
//        partList.add(new StringPart("id", signPic.getSid() + ""));
//        partList.add(new StringPart("type", "1"));
//        try {
//            File tempFile = new File(signPic.getPath());
//            partList.add(new FilePart("file", tempFile));// path
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        // 获取队列
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        String url = Ip.getIp() + "Main/Upload/uploadSignature";
//        // 生成请求
//        MultipartRequest profileUpdateRequest = new MultipartRequest(url, partList.toArray(new Part[partList.size()]), new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                UploadMesBean uploadMesBean = new Gson().fromJson(response, UploadMesBean.class);
//                if (uploadMesBean.getStatus().equals("0")) {
//                    muploadDialog.dismiss();
//                    Toast.makeText(ProjectManagerActivity.this, uploadMesBean.getMsg(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Log.d("upload", "签名图片上传" + index + "//" + response);
//                if (index >= size - 1) {
//                    //成功了
//                    muploadDialog.dismiss();
//                    Toast.makeText(ProjectManagerActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
//                    presenter.loadProjectsByEid(mCurrentCompany.getId());
//                } else {
//                    int i = index;
//                    i++;
//                    imageSignUpload(uploadSignPics.get(i), i, uploadSignPics.size());
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                muploadDialog.dismiss();
//                Log.d("MultipartRequest", error.getMessage() + error);
//            }
//        });
//        // 将请求加入队列
//        requestQueue.add(profileUpdateRequest);
//    }

    public void uploadSign(Sample sample) {
        SignPic signPic = GreenDaoManager.getInstance().getNewSession().getSignPicDao().queryBuilder()
                .where(SignPicDao.Properties.Sid.eq(sample.getId())).unique();
        if (signPic == null) {
            uploadSampleCheck(GreenDaoManager.getInstance().getNewSession().getSampleCheckDao()
                    .queryBuilder().where(SampleCheckDao.Properties.Id.eq(sample.getId())).unique(), sample);
            return;
        } else {
            // 构造参数列表
            List<Part> partList = new ArrayList<Part>();
            partList.add(new StringPart("id", signPic.getSid() + ""));
            partList.add(new StringPart("type", "1"));
            try {
                File tempFile = new File(signPic.getPath());
                partList.add(new FilePart("file", tempFile));// path
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 获取队列
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String url = Ip.getIp() + "Main/Upload/uploadSignature";
            // 生成请求
            MultipartRequest profileUpdateRequest = new MultipartRequest(url, partList.toArray(new Part[partList.size()]), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("upload", "签名" + "//" + response);
                    UploadMesBean uploadMesBean = new Gson().fromJson(response, UploadMesBean.class);
                    if (uploadMesBean.getStatus().equals("0")) {
                        muploadDialog.dismiss();
                        Toast.makeText(ProjectManagerActivity.this, uploadMesBean.getMsg(), Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        uploadSampleCheck(GreenDaoManager.getInstance().getNewSession().getSampleCheckDao()
                                .queryBuilder().where(SampleCheckDao.Properties.Id.eq(sample.getId())).unique(), sample);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    muploadDialog.dismiss();
                    Log.d("MultipartRequest", error.getMessage() + error);
                }
            });
            // 将请求加入队列
            requestQueue.add(profileUpdateRequest);
        }


    }
}