package com.zhilian.rf_qims.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.zhilian.App;

/**
 * Created by luocong on 2016/3/7.
 * 创建一个通用的工具类,让工具类代理Application的功能,用户不需要知道Application具体怎么实现
 *
 * - 把xml布局实例化view对象
 - 取得资源   String  Drawable  Color
 - 取得字符串资源
 - 取得字符串数组资源
 - 取图片资源
 - 取得颜色资源
 - dp转px   ： 参照当前屏幕密度与标准密度的比例来转化
 - px转dp
 - 判断当前是否为主线程
 - 在主线程执行语句任务 ：比如操作都是与UI控件相关的
 */

public class CommonUtils {
    public static Context getContext(){
        return App.getAppContext();
    }
    public static Handler getHander(){
        return App.getHandler();
    }
    public static Thread getMainThread(){
        return App.getMainThread();
    }
    public static int getMainThreadId(){
        return App.getMainThreadId();
    }
    //把xml布局实例化view对象
    public static View getView(int layoutResId){
        return View.inflate(getContext(),layoutResId,null);
    }
    //取得资源   String  Drawable  Color
    public static Resources getResource(){
        return getContext().getResources();
    }
    //- 取得字符串资源
    public static String getStringRes(int strRes){
        return getResource().getString(strRes);
    }
    //取得字符串数组资源
    public static String[] getStringArray(int strResArr){
        return getResource().getStringArray(strResArr);

    }
    //取图片资源
    public static Drawable getPhotoRes(int drawableResId){
        return getResource().getDrawable(drawableResId);
    }

    //获取颜色资源
    public static int getColorRes(int colorResId){
        return  getResource().getColor(colorResId);
    }
    //dp转px   ： 参照当前屏幕密度与标准密度的比例来转化
    public static int dp2px(int dp){
        float density = getResource().getDisplayMetrics().density;
        return (int) (dp*density+0.5);
    }
    //- px转dp
    public static int px2dp(int px){
        float density = getResource().getDisplayMetrics().density;
        return (int) (px/density+0.5);
    }
   // - 判断当前是否为主线程
    public static boolean isMainThread(){
        return getMainThreadId() == Process.myTid();
    }
    //获取LayoutInflater
    public static LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(getContext());
    }
    /**
     * 确保当前的语句任务在主线程运行
     *  - 使用场景 ： 操作的UI控件的语句任务
     */
    public static void isMainThreadRuning(Runnable runnable){
        //是主线程,可以直接操作Ui,不需要添加到Handler
        if (isMainThread()){
            runnable.run();
        }else {
            //不是主线程,要添加到Handler
            getHander().post(runnable);
        }
    }

    /**
     * 检测当的网络（WLAN、3G/2G）状态
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * float保留两位小数
     * @param a 传入float值
     * @return 返回保留两位小数的float值
     */
    public static float floatTwo(float a){
        float b = (float)(Math.round(a * 100)) / 100;
        return b;
    }

    /**
     * 获取当前程序的版本名
     */
    public static String getVersionName(Context context) throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        Log.e("TAG","版本号"+packInfo.versionCode);
        Log.e("TAG","版本名"+packInfo.versionName);
        return packInfo.versionName;
    }

    /**
     * 获取当前程序的版本号
     */
    public static int getVersionCode(Context context) throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        Log.e("TAG","版本号"+packInfo.versionCode);
        Log.e("TAG","版本名"+packInfo.versionName);
        return packInfo.versionCode;
    }
}
