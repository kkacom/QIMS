package com.colin.http;

import com.colin.constant.Constants;
import com.google.gson.GsonBuilder;
import com.zhilian.api.InQueryMsg;
import com.zhilian.api.InSaveMsg;
import com.zhilian.api.ParaMap;
import com.zhilian.api.RequestUtil;
import com.zhilian.api.Sign;
import com.zhilian.api.StrKit;

import java.util.Map;

/**
 * Created by colin on 2017-12-23.
 */

public class HttpUtil {
    public static String initQueryMethod(String method){
        InQueryMsg inQueryMsg = new InQueryMsg(1111111111, "query", Constants.accessToken);
        inQueryMsg.setQueryName(method);
        String postData = new GsonBuilder().disableHtmlEscaping().create().toJson(inQueryMsg);
        return StrKit.notBlank(postData)?postData:"";
    }
    public static String initQueryParams(String method, Map<String, String> params){
        InQueryMsg inQueryMsg = new InQueryMsg(1111111111, "query", Constants.accessToken);
        inQueryMsg.setQueryPara(params);
        inQueryMsg.setQueryName(method);
        String postData = new GsonBuilder().disableHtmlEscaping().create().toJson(inQueryMsg);
        return StrKit.notBlank(postData)?postData:"";
    }
    public static String initQueryParams(String type, String method, Map<String, String> params){
        InQueryMsg inQueryMsg = new InQueryMsg(1111111111, type, Constants.accessToken);
        inQueryMsg.setQueryPara(params);
        inQueryMsg.setQueryName(method);
        String postData = new GsonBuilder().disableHtmlEscaping().create().toJson(inQueryMsg);
        return StrKit.notBlank(postData)?postData:"";
    }
    public static String initSaveParams(String type,String method,Map<String,String> params){
        InSaveMsg msg = new InSaveMsg(1111111111,type, Constants.accessToken);
        msg.setModelName(method);
        msg.setModelProperty(params);
        String postData = new GsonBuilder().disableHtmlEscaping().create().toJson(msg);
        return StrKit.notBlank(postData)?postData:"";
    }
    public static String initUrl(){
        String token = "1lj4hbato30kl1ppytwa1ueqdn";
        String encodingAesKey = "InVjlo7czsOWrCSmTPgEUXBzlFnmqpNMQU3ZfilULHyHZiRjVUhxxWpexhYH6f4i";
        Map<String, String> ret = Sign.sign(Constants.url, token, encodingAesKey);
        String signature = ret.get("signature");
        String nonceStr = ret.get("nonceStr");
        String timestamp = ret.get("timestamp");
        Map<String, String> queryParas = ParaMap.create("accessToken", token)
                .put("nonce", nonceStr)
                .put("timestamp", timestamp)
                .put("signature", signature)
                .getData();
        return RequestUtil.buildUrlWithQueryString(Ip.getIp()+"Api", queryParas);
    }

}
