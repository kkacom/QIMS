package com.zhilian.rf_qims.mvp.office;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.internal.http.multipart.FilePart;
import com.android.internal.http.multipart.Part;
import com.android.internal.http.multipart.StringPart;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.colin.constant.ResConstants;
import com.colin.http.Ip;
import com.venusic.handwrite.view.HandWriteView;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.dao.SampleCheckDao;
import com.zhilian.rf_qims.dao.SampleDao;
import com.zhilian.rf_qims.dao.SignPicDao;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.entity.SampleCheck;
import com.zhilian.rf_qims.entity.SignPic;
import com.zhilian.rf_qims.service.MultipartRequest;
import com.zhilian.rf_qims.widget.DialogProgress;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.topbar)
    RelativeLayout topbar;
    @BindView(R.id.signview)
    HandWriteView signview;
    @BindView(R.id.btn_clear)
    Button btnClear;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.ll)
    LinearLayout ll;
    //    public String path = getFilesDir() + File.separator + UUID.randomUUID().toString() + "sign.png";
    public String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/rf_sign";
    public String path ;
    Sample sample;
    DialogProgress dialogProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        path=getFileStreamPath("rfsign").getAbsolutePath()+"/" + UUID.randomUUID().toString() + "rf_sign.png";
        dialogProgress = new DialogProgress(SignActivity.this, "正在上传中");
        sample = (Sample) getIntent().getSerializableExtra("sample");
        signview.setPaintColor(Color.BLACK);
        //新建一个File，传入文件夹目录
//        File file = new File(dir);
       File file= getFileStreamPath("rfsign");
        //判断文件夹是否存在，如果不存在就创建，否则不创建
        if (!file.exists()) {
            //通过file的mkdirs()方法创建目录中包含却不存在的文件夹
            file.mkdirs();
        }
    }

    @OnClick({R.id.iv_back, R.id.btn_clear, R.id.btn_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_clear:
                signview.clear();
                break;
            case R.id.btn_save:
                if (signview.isSign())
                    try {
                        signview.save(path);
//                        setResult(100);
//                        finish();
//                        Log.d("path", path);
//                        //更新sample状态
                        if (sample.getStatus() == 2) {
                            SampleCheck sampleCheck = GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().queryBuilder().where(SampleCheckDao.Properties
                                    .Id.eq(sample.getId())).unique();
                            sampleCheck.setStatus(0);
                            Sample sample1 = GreenDaoManager.getInstance().getNewSession().getSampleDao().queryBuilder().where(SampleDao.Properties
                                    .Id.eq(sample.getId())).unique();
                            sample1.setStatus(0);
                            GreenDaoManager.getInstance().getNewSession().getSampleDao().update(sample1);
                            GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().update(sampleCheck);
                        }
                        SignPic signPic = GreenDaoManager.getInstance().getNewSession().getSignPicDao().queryBuilder().where(SignPicDao.Properties.Sid.eq(sample.getId())).unique();
                        if (signPic != null) {
                            GreenDaoManager.getInstance().getNewSession().getSignPicDao().delete(signPic);
                        }
                        SignPic signPic1 = new SignPic();
                        signPic1.setSid(sample.getId());
                        signPic1.setPath(path);
                        GreenDaoManager.getInstance().getNewSession().getSignPicDao().insert(signPic1);
//                        Toast.makeText(SignActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
//                        imageUpload();
                        new MaterialDialog.Builder(SignActivity.this).title("温馨提示").titleColorRes(R.color.black).content("保存成功")
                                .contentColorRes(R.color.black).positiveText("确定").positiveColorRes(R.color.black).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                else {
                    Toast.makeText(SignActivity.this, "还没有签名！", Toast.LENGTH_SHORT).show();
                }
                //uploadimg

                break;
        }
    }

    private void imageUpload() {
        dialogProgress.show();
        // 构造参数列表
        List<Part> partList = new ArrayList<Part>();
        partList.add(new StringPart("id", sample.getId() + ""));
        partList.add(new StringPart("type", "1"));
        try {
            File tempFile = new File(path);
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
                Toast.makeText(SignActivity.this, "签名上传成功", Toast.LENGTH_SHORT).show();
                dialogProgress.dismiss();
                Log.d("MultipartRequest", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 处理失败错误信息
                dialogProgress.dismiss();
                Log.d("MultipartRequest", error.getMessage() + error);
//                Toast.makeText(getApplication(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        // 将请求加入队列
        requestQueue.add(profileUpdateRequest);
    }
}