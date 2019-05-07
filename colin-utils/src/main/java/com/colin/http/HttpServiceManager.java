package com.colin.http;


import java.util.concurrent.TimeUnit;

import io.reactivex.android.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2017-9-28.
 */

public class HttpServiceManager {
    private static HttpServiceManager instance = null;

    public synchronized static HttpServiceManager getInstance() {
        return instance != null ? instance : new HttpServiceManager();
    }

    private HttpServiceManager() {

    }

    private OkHttpClient client = new OkHttpClient()
            .newBuilder().connectTimeout(600, TimeUnit.SECONDS)
            .writeTimeout(600, TimeUnit.SECONDS)
            .readTimeout(600, TimeUnit.SECONDS)
            .addInterceptor(new HttpLoggingInterceptor()
                    .setLevel(BuildConfig.DEBUG ?
                            HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))

            .build();
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Ip.getIp())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    private HttpService mHttpService = retrofit.create(HttpService.class);

    public HttpService getHttpService() {
        return mHttpService;
    }
}