package com.colin.constant;



import android.Manifest;

/**
 * Created by Administrator on 2018-1-4.
 */

public class Constants implements ResConstants,IntentConstants {
    public static String url = null;
    public static String apkName = "hzrf-oa.apk";
    public static String apkUrl = Constants.BASE_URL+"File/App/"+ apkName;
    public static String apkPath = null;
    public static String accessToken = null;
    public static String userName = null;
    public static String userId = null;
    public static String limit = null;

    public static String saveUri = null;

    //当前软件版本(2018.12.25增加使用)
    public static double localVersion = 1.07;
    public static String APP_VERSION = null;

    public static String[] PERMISSIONS = {
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
}


