package com.zhilian.rf_qims.mvp.login.presenter;

import com.zhilian.rf_qims.entity.UserInfo;

/**
 * Created by Administrator on 2017-9-22.
 */
public interface ILoginPresenter {
    //void login(String user, String pwd);
    void login(UserInfo userInfo);
    void forgotPwd();
    void cacheUser(UserInfo user);
    void loadCacheData();
}
