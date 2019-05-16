package com.zhilian.rf_qims.base;

import com.zhilian.rf_qims.api.ParaMap;
import com.zhilian.rf_qims.api.Sign;
import com.zhilian.rf_qims.common.BusinessContant;

import com.zhilian.rf_qims.util.RequestUtil;

import java.util.Map;

public class HttpBase {
    public HttpBase(String url){
    String token = "1lj4hbato30kl1ppytwa1ueqdn";
    final String encodingAesKey = "InVjlo7czsOWrCSmTPgEUXBzlFnmqpNMQU3ZfilULHyHZiRjVUhxxWpexhYH6f4i";
    Map<String, String> ret = Sign.sign(url, token, encodingAesKey);
    String signature = ret.get("signature");
    String nonceStr = ret.get("nonceStr");
    String timestamp = ret.get("timestamp");
    Map<String, String> queryParas = ParaMap.create("accessToken", token)
            .put("nonce", nonceStr)
            .put("timestamp", timestamp)
            .put("signature", signature)
            .getData();
    url = RequestUtil.buildUrlWithQueryString(url, queryParas);
    }
}
