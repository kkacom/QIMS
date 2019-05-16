package com.zhilian.rf_qims.mvp.leave.model;

import android.app.Activity;

import com.google.gson.Gson;
import com.zhilian.rf_qims.base.IBaseModel;
import com.zhilian.rf_qims.mvp.leave.view.LeaveDetailActivity;
import com.zhilian.rf_qims.util.LogUtil;
import com.zhilian.rf_qims.util.StrKit;
import com.zhilian.rf_qims.api.RxHttpServiceManager;
import com.zhilian.rf_qims.util.RxHttpUtil;
import com.zhilian.rf_qims.bean.LeaveDetailBean;
import com.zhilian.rf_qims.constant.Constants;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018-1-4.
 */

public class LeaveDetailModel implements IBaseModel {
    public void getLeaveDetail(String docid, String isdone, final LeaveDetailModel.CallBack callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("docid", docid);
        map.put("isdone", isdone);
        //  LogUtil.e(docid);
        //  LogUtil.e(isdone);
        RxHttpServiceManager.getInstance()
                .getRxApiService()
                .getServerData(RxHttpUtil.initUrl(), RxHttpUtil.initQueryParams(Constants.TYPE_QUERY, Constants.QUERY_LEAVE_DETAIL, map))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String json = new String(responseBody.bytes());
                        LogUtil.e("detail: " + json);
                        if (StrKit.notBlank(json)) {
                            LeaveDetailBean bean = new Gson().fromJson(json, LeaveDetailBean.class);
                            callback.onGetLeaveDetailSuccess(bean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e("throwable: " + throwable.getMessage());
                    }
                });
    }

    public void saveOpinion(String saveModel, LeaveDetailBean json, final CallBack1 callBack1) {
        HashMap<String, String> map = new HashMap<>();
        map.put("itemid", String.valueOf(json.getWf().getItemid()));
        String opinionfield = json.getOpinionfield();
        if (opinionfield.equals("opinion1")) {
            map.put("opinion", json.getOpinion1());
        } else if (opinionfield.equals("opinion2")) {
            map.put("opinion", json.getOpinion2());
        }
//        LogUtil.e("json.getWf().getId() = "+json.getWf().getId());
        map.put("pid", String.valueOf(json.getWf().getId()));
        map.put("isreceiveopinion", "0");
        map.put("docid", json.getId());
        LogUtil.e("url params = " + RxHttpUtil.initSaveParams("save", saveModel, map));
        RxHttpServiceManager.getInstance()
                .getRxApiService()
                .getServerData(RxHttpUtil.initUrl(), RxHttpUtil.initSaveParams("save", saveModel, map))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String result = new String(responseBody.bytes());
                        callBack1.onSaveOpinionSuccess(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack1.onSaveOpinionFailure(throwable.getCause().getMessage());
                    }
                });
    }

    public void getLeaveDayt(String bdate, String bm, String edate, String em, final CallBack2 callBack) {
        HashMap<String, String> map = new HashMap<>();
        //TODO sdate-->> bdate ,edate,bm,em 待修改
        map.put("bdate", bdate);
        map.put("bm", bm);
        map.put("em", em);
        map.put("edate", edate);
        RxHttpServiceManager.getInstance()
                .getRxApiService()
                .getServerData(RxHttpUtil.initUrl(), RxHttpUtil.initQueryParams(Constants.TYPE_QUERY, Constants.QUERY_COUNT_WORKING_DAY, map))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String result = new String(responseBody.bytes());
                        LogUtil.e("getdayt = " + result);
                        callBack.onGetLeaveDaytSuccess(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        // callBack1.onSaveOpinionFailure(throwable.getCause().getMessage());
                        LogUtil.e("getdayt = " + throwable.getMessage());
                    }
                });
    }

    public void deleteApply(LeaveDetailBean leave, CallBack callBack, LeaveDetailActivity leaveDetailActivity) {
        HashMap<String, String> map = new HashMap<>();

        map.put("id", String.valueOf(leave.getWf().getId()));
        String url = RxHttpUtil.initUrl();
        LogUtil.e("url = " + url);
        String data = RxHttpUtil.initDelParams(map);
        LogUtil.e("data = " + data);
        RxHttpServiceManager.getInstance()
                .getRxApiService()
                .delServerData(url, RxHttpUtil.initDelParams(map))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody s) throws Exception {
                        //LogUtil.e("msg = " + s.string());
                        String json = s.string();
                        if (json.equals("删除成功！")) {
                            leaveDetailActivity.setResult(Constants.UNDO);
                            callBack.onDeleted();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e(throwable.getMessage());
                        leaveDetailActivity.mCustomPopDialog.dismiss();
                    }
                });
    }

    public interface CallBack {

        void onGetLeaveDetailSuccess(LeaveDetailBean bean);

        void onDisconnected(String message);

        void onDeleted();
    }

    public interface CallBack1 {
        void onSaveOpinionFailure(String message);

        void onSaveOpinionSuccess(String result);
    }

    public interface CallBack2 {
        void onGetLeaveDaytSuccess(String result);
    }
}
