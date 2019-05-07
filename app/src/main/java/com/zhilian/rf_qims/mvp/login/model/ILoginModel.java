package com.zhilian.rf_qims.mvp.login.model;


import com.zhilian.rf_qims.entity.UserInfo;

/**
 * Created by Administrator on 2017-9-22.
 */
public interface ILoginModel {
    //    @Deprecated
//    void confirmUser(String user, String pwd, LoginCallBack callBack);
    void confirmUser(UserInfo user, LoginCallBack callBack);

    void forgotPwd();

    void loadCacheUserInfo(LoginCallBack callBack);

    interface LoginCallBack {
        void onSuccess();

        void onFailure(String errmsg);
    }
    int getCacheUserInfoSize();
}
