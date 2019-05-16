package com.zhilian.rf_qims.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.zhilian.rf_qims.bean.EgressDetailBean;
import com.zhilian.rf_qims.bean.LeaveDetailBean;
//import com.zhilian.rxapi.bean.VehicleDetailBean;

/**
 * Created by Administrator on 2017-12-29.
 */

public class CacheUtil {
    private Context mContext;
    private SharedPreferences sp;
    private SharedPreferences.Editor mEditor;

    public CacheUtil(Context context, String xmlName) {
        mContext = context;
        sp = mContext.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
    }
    public void saveObject(String key, LeaveDetailBean obj){
        mEditor = sp.edit();
        mEditor.putString(key,  new Gson().toJson(obj));
        mEditor.commit();
        mEditor.clear();
//        Toast.makeText(mContext, "保存成功！", Toast.LENGTH_SHORT).show();
    }
    public LeaveDetailBean getObject(String key, Class clazz) {
        String str = sp.getString(key, "");
        LeaveDetailBean leave = (LeaveDetailBean) new GsonBuilder().create().fromJson(str, clazz);
        return leave;
    }

//    public void saveObject(String key, VehicleDetailBean obj){
//        mEditor = sp.edit();
//        mEditor.putString(key,  new Gson().toJson(obj));
//        mEditor.commit();
//        mEditor.clear();
////        Toast.makeText(mContext, "保存成功！", Toast.LENGTH_SHORT).show();
//    }
//    public VehicleDetailBean getObject1(String key, Class clazz) {
//        String str = sp.getString(key, "");
//        VehicleDetailBean vehicle = (VehicleDetailBean) new GsonBuilder().create().fromJson(str, clazz);
//        return vehicle;
//    }
//
//    public void saveEgress(String key, EgressDetailBean obj){
//        mEditor = sp.edit();
//        mEditor.putString(key,  new Gson().toJson(obj));
//        mEditor.commit();
//        mEditor.clear();
////        Toast.makeText(mContext, "保存成功！", Toast.LENGTH_SHORT).show();
//    }
//    public EgressDetailBean getEgress(String key, Class clazz) {
//        String str = sp.getString(key, "");
//        EgressDetailBean leave = (EgressDetailBean) new GsonBuilder().create().fromJson(str, clazz);
//        return leave;
//    }
}
