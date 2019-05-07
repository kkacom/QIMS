package com.zhilian;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Process;

import com.colin.base.ActivityManager;
import com.tencent.smtt.sdk.QbSdk;
import com.zhilian.rf_qims.dao.DaoMaster;
import com.zhilian.rf_qims.dao.DaoSession;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017-9-21.
 */
public class App extends Application {
    public static GreenDaoManager mDaoManager = null;
    private static ActivityManager manager = null;
    private static Context mAppContext = null;
    //设置成静态,在oncreate方法之前创建
    private static android.os.Handler Handler;
    private static Thread mainThread;
    private static int mainThreadId;
    private static DaoSession daoSession;

    public static Context getAppContext() {
        return mAppContext;
    }
    //提供Handler对象
    public static Handler getHandler(){
        return Handler;
    }
    //提供当前线程
    public static Thread getMainThread(){
        return mainThread;
    }
    //提供主线程的Id
    public static int getMainThreadId(){
        return mainThreadId;
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.getInstance(okHttpClient);
//        QbSdk.setDownloadWithoutWifi(true);
//        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
//            @Override
//            public void onCoreInitFinished() {
//
//            }
//
//            @Override
//            public void onViewInitFinished(boolean b) {
//                //这里被回调，并且b=true说明内核初始化并可以使用
//                //如果b=false,内核会尝试安装，你可以通过下面监听接口获知
//            }
//        });
//        QbSdk.setDownloadWithoutWifi(true);
//        QbSdk.setTbsListener(new TbsListener() {
//            @Override
//            public void onDownloadFinish(int i) {
//                //tbs内核下载完成回调
//            }
//
//            @Override
//            public void onInstallFinish(int i) {
//                //内核安装完成回调，
//            }
//
//            @Override
//            public void onDownloadProgress(int i) {
//                //下载进度监听
//            }
//        });
//        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.initX5Environment(this,null);
        mAppContext = getApplicationContext();
        manager =new ActivityManager();
        mDaoManager = GreenDaoManager.getInstance();
        Handler = new Handler();
        mainThread=Thread.currentThread();
        mainThreadId= Process.myTid();

        setupDatabase();

    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库shop.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "hnrfems.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();;
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static ActivityManager getManager() {
        return manager;
    }
}
