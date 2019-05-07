package com.zhilian.rf_qims.service;

import android.content.Context;

import com.colin.http.PollingUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-9-22.
 */
public class ServiceManager {
    private static List<ServiceInfo> services = new ArrayList<ServiceInfo>();
    public static void addServcie(ServiceInfo info){
        services.add(info);
    }
    public static void startService(){
        for (ServiceInfo service : services) {
            PollingUtils.startPollingService(service.context, service.seconds, service.clazz, service.action);
        }
    }
    public static void clear(){
        for (ServiceInfo service : services) {
            PollingUtils.stopPollingService(service.context,service.clazz, service.action);
        }
    }
    public static class ServiceInfo{
        Context context;
        int seconds;
        Class clazz;
        String action;

        public ServiceInfo(Context context, int seconds, Class clazz, String action) {
            this.context = context;
            this.seconds = seconds;
            this.clazz = clazz;
            this.action = action;
        }
    }
}