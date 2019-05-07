package com.zhilian.rf_qims.base;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.common.Common;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 从业评估 所有模块的基类
 */
public abstract class BaseModuleActivity extends AppCompatActivity {
    @BindView(R.id.unfinished)
    public Button unfinished;// 未完成
    @BindView(R.id.finish)
    public Button finish;// 完成
    @BindView(R.id.close)
    public Button close;// 关闭
    @BindView(R.id.photo)
    public Button photo;// 拍照
    @BindView(R.id.video)
    public Button video;// 摄像

    public String item, title, eid, cid, item1;// 条款
    private Bitmap mBitmap;
    public int flag; //此标记用于在点击未完成和完成时才会提示保存提示，而在点击item进入打分时因为单选按钮的赋值触发saveStatus方法则不提示

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        item = getIntent().getStringExtra("item");
        title = getIntent().getStringExtra("title");
        eid = String.valueOf(Common.getEid());
        cid = String.valueOf(Common.getWid());
        if (item != null && item.length() >= 4){
            item1 = item.substring(0,3);
        }

        initView(this);
        initData();
        listener();
    }

    public void initView(Activity activity){

        // 关闭按钮的的事件监听
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 未完成按钮的事件监听
        unfinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                saveStatus("2");
            }
        });

        // 完成按钮的事件监听
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                saveStatus("1");
            }
        });

        // 拍照
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT > 22) {
                    if (ContextCompat.checkSelfPermission(BaseModuleActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        //先判断有没有权限 ，没有就在这里进行权限的申请
                        int camera_ok = 1;
                        ActivityCompat.requestPermissions(BaseModuleActivity.this,
                            new String[]{Manifest.permission.CAMERA}, camera_ok);
                    } else {
                        photo();
                        /*PhotoOrVideo.photo(BaseModuleActivity.this,
                            null, "khxc", "1", 0, String.valueOf(cid));*/
                    }
                } else {
                    /*PhotoOrVideo.photo(BaseModuleActivity.this,
                        null, "khxc", "1", 0, String.valueOf(cid));*/
                    photo();
                }
            }
        });

        // 摄像
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT > 22) {
                    if (ContextCompat.checkSelfPermission(BaseModuleActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        //先判断有没有权限 ，没有就在这里进行权限的申请
                        int camera_ok = 0;
                        ActivityCompat.requestPermissions(BaseModuleActivity.this,
                            new String[]{Manifest.permission.CAMERA}, camera_ok);
                    } else {
                        video();
                        /*PhotoOrVideo.photo(BaseModuleActivity.this,
                            null, "khxc", "1", 0, String.valueOf(cid));*/
                    }
                } else {
                    /*PhotoOrVideo.photo(BaseModuleActivity.this,
                        null, "khxc", "1", 0, String.valueOf(cid));*/
                    video();
                }
            }
        });

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    /*Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    startActivityForResult(intent, 2);*/
                    /*PhotoOrVideo.video(BaseModuleActivity.this,
                        null, "khxc", "1", 0, String.valueOf(cid));*/
                    video();
                } else {
                    //这里是拒绝给APP摄像头权限
                    Toast.makeText(BaseModuleActivity.this, "请手动打开所需权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 3);*/
                    /*PhotoOrVideo.photo(this, null, "khxc", "1", 0, String.valueOf(cid));*/
                    photo();
                } else {
                    //这里是拒绝给APP摄像头权限
                    Toast.makeText(BaseModuleActivity.this, "请手动打开所需权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    protected abstract int getLayoutRes();// Activity的布局文件
    protected abstract void initData();// 初始化数据
    protected abstract void saveStatus(String status);// 保存和更改状态
    protected abstract void listener();// 事件监听
    protected abstract void photo();//拍照
    protected abstract void video();//录像

}
