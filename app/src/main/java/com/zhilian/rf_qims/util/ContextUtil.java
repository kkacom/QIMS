package com.zhilian.rf_qims.util;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * @Author Andersen
 * mail: yawen199@163.com
 * Date: 2016-09-12 11:10
 */
public class ContextUtil extends Application {
    private static ContextUtil instance;

    public static ContextUtil getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
