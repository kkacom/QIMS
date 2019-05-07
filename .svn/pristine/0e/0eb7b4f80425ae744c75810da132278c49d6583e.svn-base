package com.zhilian.rf_qims.mvp.SamplePic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.adapter.FullyGridLayoutManager;
import com.zhilian.rf_qims.adapter.GridImageAdapter;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.dao.SampleCheckDao;
import com.zhilian.rf_qims.dao.SampleDao;
import com.zhilian.rf_qims.dao.ScenePicDao;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.entity.SampleCheck;
import com.zhilian.rf_qims.entity.ScenePic;

import java.util.ArrayList;
import java.util.List;

public class SamplePicActivity extends AppCompatActivity {
    private ImageView ivTakePic;
    private List<LocalMedia> selectList = new ArrayList<>();
    private List<ScenePic> scenePics = new ArrayList<>();
    private RecyclerView recyclerView;
    private GridImageAdapter adapter;
    private Sample sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_pic);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sample = (Sample) getIntent().getSerializableExtra("sample");
        ivTakePic = findViewById(R.id.iv_takepic);
        ivTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 单独拍照
                PictureSelector.create(SamplePicActivity.this)
                        .openCamera(0)// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                        .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles
                        .compress(true)// 是否压缩
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        //查找图片
        scenePics = GreenDaoManager.getInstance().getNewSession().getScenePicDao().queryBuilder().where(ScenePicDao.Properties.Sid.eq(sample.getId())).orderDesc(ScenePicDao.Properties.Id).list();

        for (int i = 0; i < scenePics.size(); i++) {
            LocalMedia localMedia = new LocalMedia();
            localMedia.setPath(scenePics.get(i).getPath());
            selectList.add(localMedia);
        }
        FullyGridLayoutManager manager = new FullyGridLayoutManager(SamplePicActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(SamplePicActivity.this);
        adapter.setList(scenePics);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
//                PictureSelector.create(SamplePicActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(position, selectList);

                PictureSelector.create(SamplePicActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(position, selectList);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
//                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
//                    for (LocalMedia media : selectList) {
//                        Log.d("图片", media.getPath());
//                    }
                    ScenePic scenePic = new ScenePic();
                    scenePic.setIsupload(1);
                    scenePic.setSid(sample.getId());
                    scenePic.setPath(PictureSelector.obtainMultipleResult(data).get(0).getCompressPath());
                    scenePics.add(0, scenePic);
                    GreenDaoManager.getInstance().getNewSession().getScenePicDao().insert(scenePic);
                    //更新sample状态
                    if (sample.getStatus()==2){
                        SampleCheck sampleCheck=GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().queryBuilder().where(SampleCheckDao.Properties
                                .Id.eq(sample.getId())).unique();
                        sampleCheck.setStatus(0);
                        Sample sample1=GreenDaoManager.getInstance().getNewSession().getSampleDao().queryBuilder().where(SampleDao.Properties
                                .Id.eq(sample.getId())).unique();
                        sample1.setStatus(0);
                        GreenDaoManager.getInstance().getNewSession().getSampleDao().update(sample1);
                        GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().update(sampleCheck);
                    }

                    LocalMedia localMedia = new LocalMedia();
                    localMedia.setPath(scenePic.getPath());
                    selectList.add(0, localMedia);
                    adapter.setList(scenePics);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }
}
