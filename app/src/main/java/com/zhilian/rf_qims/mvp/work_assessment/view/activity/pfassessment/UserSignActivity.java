package com.zhilian.rf_qims.mvp.work_assessment.view.activity.pfassessment;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.util.ToastUtils;
import com.zhilian.rf_qims.widget.LinePathView;

import java.io.File;
import java.io.IOException;

/**
 * 用户签名（手写签名）
 */
public class UserSignActivity extends Activity {
    private static String USER_SIGN_PATH = "";
    LinePathView mPathView;// 手写区
    Button mClear;// 清除
    Button mSave;// 保存
    private String mSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//强制为横屏
        setContentView(R.layout.user_sign_layout);
        setResult(51);
        mSign = getIntent().getStringExtra("sign");
        initView();
    }


    private void initView() {
        mPathView = (LinePathView) findViewById(R.id.graffiti_area);
        mClear = (Button) findViewById(R.id.clear);
        mSave = (Button) findViewById(R.id.save);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPathView.getTouched()) {
                    try {
                        //保存到test文件夹里
                        /*String name = new DateFormat().format("yyyy-MM-dd-hh-mm",
                                java.util.Calendar.getInstance(Locale.CHINA))
                                + ".jpg";
                        String fileName = "/mnt/sdcard/test/" + name;// 保存路径*/
                        USER_SIGN_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + mSign + ".png";
                        /*mPathView.save(fileName, false, 10);*/
                        mPathView.save(USER_SIGN_PATH, false, 10);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    setResult(101);
                    finish();
                }else {
                    ToastUtils.show("您没有签名~");
                }
            }
        });

        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPathView.clear();
            }
        });
    }


}
