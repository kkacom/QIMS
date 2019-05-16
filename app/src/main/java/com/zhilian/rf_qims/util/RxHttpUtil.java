package com.zhilian.rf_qims.util;

import com.google.gson.GsonBuilder;
import com.zhilian.api.InDeleteMsg;
import com.zhilian.api.InQueryMsg;
import com.zhilian.api.InSaveMsg;
import com.zhilian.api.ParaMap;
import com.zhilian.api.RequestUtil;
import com.zhilian.api.Sign;
import com.zhilian.api.StrKit;
import com.zhilian.rf_qims.util.LogUtil;
import com.zhilian.rf_qims.constant.Constants;
import com.zhilian.rf_qims.constant.LocalConstants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.annotations.NonNull;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017-12-23.
 */

public class RxHttpUtil {

    public static String initQueryParams(String type, String method, Map<String, String> params){
        InQueryMsg inQueryMsg = new InQueryMsg(1111111111, type, LocalConstants.APP_KEY);
        inQueryMsg.setQueryPara(params);
        inQueryMsg.setQueryName(method);
        String postData = new GsonBuilder().disableHtmlEscaping().create().toJson(inQueryMsg);
        return StrKit.notBlank(postData)?postData:"";
    }
    public static String initSaveParams(String type,String method,Map<String,String> params){
        InSaveMsg msg = new InSaveMsg(1111111111,type,LocalConstants.APP_KEY);
        msg.setModelName(method);
        msg.setModelProperty(params);
        String postData = new GsonBuilder().disableHtmlEscaping().create().toJson(msg);
        return StrKit.notBlank(postData)?postData:"";
    }
    public static String initUrl(){
        String token = "1lj4hbato30kl1ppytwa1ueqdn";
        String encodingAesKey = "InVjlo7czsOWrCSmTPgEUXBzlFnmqpNMQU3ZfilULHyHZiRjVUhxxWpexhYH6f4i";
        Map<String, String> ret = Sign.sign(Constants.URL, token, encodingAesKey);
        String signature = ret.get("signature");
        String nonceStr = ret.get("nonceStr");
        String timestamp = ret.get("timestamp");
        Map<String, String> params = ParaMap.create("accessToken", token)
                .put("nonce", nonceStr)
                .put("timestamp", timestamp)
                .put("signature", signature)
                .getData();
        return RequestUtil.buildUrlWithQueryString(Constants.BASE_URL+"Api", params);
    }

    public static String initDelParams(HashMap<String, String> map) {
        InDeleteMsg inDeleteMsg = new InDeleteMsg(1348831860, "delete", LocalConstants.APP_KEY);
        inDeleteMsg.setModelName("workflow");
        inDeleteMsg.setEntityId(map.get("id"));// id+""
        String postData = new GsonBuilder().disableHtmlEscaping().create().toJson(inDeleteMsg);
        return StrKit.notBlank(postData)?postData:"";
    }
}
