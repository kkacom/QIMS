package com.zhilian.rf_qims.mvp.sample_manager.view;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.colin.http.HttpServiceManager;
import com.colin.http.HttpUtil;
import com.colin.http.Ip;
import com.colin.utils.LogUtil;
import com.google.gson.Gson;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.adapter.SampleAdapter;
import com.zhilian.rf_qims.base.BaseActivity;
import com.zhilian.rf_qims.bean.UploadMesBean;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.dao.SampleCheckDao;
import com.zhilian.rf_qims.dao.SampleDao;
import com.zhilian.rf_qims.dao.ScenePicDao;
import com.zhilian.rf_qims.dao.SignPicDao;
import com.zhilian.rf_qims.entity.Project;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.entity.SampleCheck;
import com.zhilian.rf_qims.entity.ScenePic;
import com.zhilian.rf_qims.entity.SignPic;
import com.zhilian.rf_qims.mvp.office.PdfSeeActivity;
import com.zhilian.rf_qims.mvp.sample_data.DataActivity;
import com.zhilian.rf_qims.mvp.sample_detail.view.SampleDetailActivity2;
import com.zhilian.rf_qims.mvp.sample_detail.view.SampleUpdateActivity;
import com.zhilian.rf_qims.mvp.sample_manager.presenter.SamplePresenter;
import com.zhilian.rf_qims.widget.HorizontalProgress;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.ResponseBody;

/**
 * Created by colin on 2018/2/6 10:34 .
 */

public class SampleManagerActivity extends BaseActivity implements ISampleView {
    private Toolbar mToolbar;
    private SwipeMenuListView mListView;

    private SamplePresenter mPresenter;
    private Project mProject;
    private List<Sample> mSamples = new ArrayList<>();
    private SampleAdapter mAdapter;

    private int currentPosition;//当前操作的标记
    HorizontalProgress horizontalProgress;
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0x12) {
            mPresenter.loadLocalSamplesByPid(mProject.getId());
        }
    }

    @Override
    protected int loadLayoutResource() {
        return R.layout.activity_sample;
    }

    @Override
    protected void initView() {
        horizontalProgress = new HorizontalProgress(SampleManagerActivity.this, null);
        mListView = findViewById(R.id.list);
        mAdapter = new SampleAdapter(this, mSamples);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SampleManagerActivity.this, DataActivity.class);
                intent.putExtra("project", mProject);
                intent.putExtra("sample", mSamples.get(i));
                Log.e("DataActicity传递的值","project："+mProject+"\n"+"sample："+mSamples.get(i));
//				GlobalConstants.currentSample = mSamples.get(i);
                startActivityForResult(intent, 0x12, ActivityOptionsCompat.makeSceneTransitionAnimation(SampleManagerActivity.this).toBundle());
                //startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(SampleManagerActivity.this).toBundle());
            }
        });
        mListView.setMenuCreator(creator);
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Sample sample = mSamples.get(position);
                switch (index) {
                    case 0:  //更新修改
//						Intent intent = new Intent(SampleManagerActivity.this,SampleDetailActivity2.class);
//						intent.putExtra("project",mProject);
//						intent.putExtra("sample",sample);
//						intent.putExtra("status",1);
//						startActivityForResult(intent,0x11,ActivityOptionsCompat.makeSceneTransitionAnimation(SampleManagerActivity.this).toBundle());

                        Intent intent = new Intent(SampleManagerActivity.this, SampleUpdateActivity.class);
                        intent.putExtra("project", mProject);
                        intent.putExtra("sample", sample);
//				GlobalConstants.currentSample = mSamples.get(i);
                        startActivityForResult(intent, 0x12, ActivityOptionsCompat.makeSceneTransitionAnimation(SampleManagerActivity.this).toBundle());
                        break;
                    case 1:
                        SampleCheck sampleCheck = GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().queryBuilder().where(SampleCheckDao.Properties.Id.eq(sample.getId())).unique();
                        if (sampleCheck != null) {
                            GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().delete(sampleCheck);
                        }
                        GreenDaoManager.getInstance().getNewSession().getSampleDao().delete(sample);

                        //删除样品图片
                        List<ScenePic> scenePics = GreenDaoManager.getInstance().getNewSession().getScenePicDao().queryBuilder().where(ScenePicDao
                                .Properties.Sid.eq(sample.getId())).list();
                        for (int j = 0; j < scenePics.size(); j++) {
                            GreenDaoManager.getInstance().getNewSession().getScenePicDao().delete(scenePics.get(j));
                        }
                        //删除签名图片
                        SignPic signPic = GreenDaoManager.getInstance().getNewSession().getSignPicDao().queryBuilder().where(SignPicDao
                                .Properties.Sid.eq(sample.getId())).unique();
                        if (signPic != null) {
                            GreenDaoManager.getInstance().getNewSession().getSignPicDao().delete(signPic);
                        }

                        mPresenter.loadLocalSamplesByPid(mProject.getId());
                        break;
                }
                return true;
            }
        });
        mPresenter = new SamplePresenter(this);
        Intent intent = getIntent();
        mProject = (Project) intent.getSerializableExtra("project");
        mPresenter.loadLocalSamplesByPid(mProject.getId());
        mToolbar = findViewById(R.id.toolbar);
        initToolBar();
        mToolbar.setTitle("委托编号 : " + mProject.getItemCode());
        mToolbar.setSubtitle("项目名称 : " + mProject.getItemName());
        mToolbar.setSubtitleTextColor(getResources().getColor(R.color.black));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_toolbar, menu);
        return true;
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        mToolbar.setNavigationIcon(R.drawable.md_nav_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add:
                        /**
                         * 1、添加项目
                         */
                        Intent intent = new Intent(SampleManagerActivity.this, SampleDetailActivity2.class);
//						intent.putExtra("status",0);
                        intent.putExtra("project", mProject);
                        startActivityForResult(intent, 0x11, ActivityOptionsCompat.makeSceneTransitionAnimation(SampleManagerActivity.this).toBundle());
                        break;

                    case R.id.print_report:
                        //presenter.updateViewData();//更新页数数据
                        List<Sample> samples = GreenDaoManager.getInstance().getNewSession().getSampleDao().queryBuilder().where(SampleDao.Properties.Pid.eq(mProject.getId()), SampleDao.Properties.Isprinted.eq(1)).list();
                        if (samples != null && samples.size() != 0) {
                            String sampleid="";
                            for (int i = 0; i < samples.size(); i++) {
                                Log.d("hello", samples.get(i).getId() + "//");
                                sampleid += samples.get(i).getId() + ",";
                            }
                            getFileName(1,sampleid.substring(0, sampleid.length() - 1));
                        } else {
                            Toast.makeText(SampleManagerActivity.this, "请选择需要打印的样品", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.print_record:
                        List<Sample> samples2 = GreenDaoManager.getInstance().getNewSession().getSampleDao().queryBuilder().where(SampleDao.Properties.Pid.eq(mProject.getId()), SampleDao.Properties.Isprinted.eq(1)).list();
                        if (samples2 != null && samples2.size() != 0) {
                            String sampleid="";
                            for (int i = 0; i < samples2.size(); i++) {
                                Log.d("hello", samples2.get(i).getId() + "//");
                                sampleid += samples2.get(i).getId() + ",";
                            }
                            getFileName(2,sampleid.substring(0, sampleid.length() - 1));
                        } else {
                            Toast.makeText(SampleManagerActivity.this, "请选择需要打印的样品", Toast.LENGTH_SHORT).show();
                        }
                        break;

                }
                return true;
            }
        });
    }

    @Override
    public void loadCacheDataSuccess(List<Sample> list) {

//        if (null != mSamples) {
        mSamples.clear();
//        }
        for (int i = 0; i < list.size(); i++) {
            mSamples.add(list.get(i));
            LogUtil.e("date = " + list.get(i).getMadeDate());
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRemoveSample() {
//		Toast.makeText(SampleManagerActivity.this, R.string.sample_remove, Toast.LENGTH_SHORT).show();
//		mSamples.remove(currentPosition);
//		mAdapter.notifyDataSetChanged();
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }


    public void getFileName(int type,String sampleids) {
//        Sample sample = (Sample) SampleManagerActivity.this.getIntent().getSerializableExtra("sample");
        Map<String, String> params = new HashMap<>();
        params.put("type", type + "");//1是报告 //2是原始记录
        params.put("id", sampleids);//样品id
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
                                File file=getFileStreamPath("rfpdf");
                                if (!file.exists()) {
                                    //通过file的mkdirs()方法创建目录中包含却不存在的文件夹
                                    file.mkdirs();
                                }
                                String path=file.getAbsolutePath()+"/"+uploadMesBean.getMsg() + ".pdf";
                                if (new File(path).exists()) {
                                    //打开
                                    Intent intent = new Intent(SampleManagerActivity.this, PdfSeeActivity.class);
                                    intent.putExtra("path", path);
                                    intent.putExtra("type", 1);
//                                    intent.putExtra("sample", sample);
                                    startActivity(intent);
                                } else {
                                    //下载
                                    downloadPdf(type, uploadMesBean.getMsg());
                                }
                            } else {//记录 /File/OriginRecord/originRecordFH1526632103365.pdf
//                                File file = new File(Environment.getExternalStorageDirectory() + "/rf_pdf/" + uploadMesBean.getMsg().substring(uploadMesBean.getMsg().lastIndexOf("/") + 1));
                                File file=getFileStreamPath("rfpdf");
                                if (!file.exists()) {
                                    //通过file的mkdirs()方法创建目录中包含却不存在的文件夹
                                    file.mkdirs();
                                }
                                String path=file.getAbsolutePath()+"/"+uploadMesBean.getMsg().substring(uploadMesBean.getMsg().lastIndexOf("/") + 1);
                                if (new File(path).exists()) {
                                    Intent intent = new Intent(SampleManagerActivity.this, PdfSeeActivity.class);
                                    intent.putExtra("path", path);
                                    intent.putExtra("type", 2);
//                                    intent.putExtra("sample", sample);
                                    startActivity(intent);
                                } else {
                                    //下载
                                    downloadPdf(type, uploadMesBean.getMsg());
                                }
                            }
                        } else {
//                            File file = new File(Environment.getExternalStorageDirectory() + "/originRecordFH1526869178902.pdf");
//                            if (file.exists()) {
//                                Intent intent = new Intent(SampleManagerActivity.this, PdfSeeActivity.class);
//                                intent.putExtra("path", file.getAbsolutePath());
//                                intent.putExtra("type", 2);
//                                intent.putExtra("sample", sample);
//                                startActivity(intent);
//                            }
                            Toast.makeText(SampleManagerActivity.this, uploadMesBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("upload", "获取名字" + throwable.getMessage());
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
        horizontalProgress=new HorizontalProgress(SampleManagerActivity.this, null);
        horizontalProgress.show();
        String finalFilename = filename;
        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack(getFileStreamPath("rfpdf").getAbsolutePath()+"/", finalFilename) {
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
                        Intent intent = new Intent(SampleManagerActivity.this, PdfSeeActivity.class);
                        intent.putExtra("path", getFileStreamPath("rfpdf").getAbsolutePath()+"/" + finalFilename);
                        intent.putExtra("type", type);
//                        intent.putExtra("sample", sample);
                        startActivity(intent);
                    }
                });
    }
}
