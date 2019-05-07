package com.zhilian.rf_qims.mvp.login.presenter;

import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.dao.UserInfoDao;
import com.zhilian.rf_qims.entity.UserInfo;
import com.zhilian.rf_qims.mvp.login.model.ILoginModel;
import com.zhilian.rf_qims.mvp.login.model.LoginModel;
import com.zhilian.rf_qims.mvp.login.view.ILoginView;

/**
 * Created by Administrator on 2017-9-22.
 */
public class LoginPresenter implements ILoginPresenter, ILoginModel.LoginCallBack {
    private ILoginView view;
    private ILoginModel model;

    public LoginPresenter(ILoginView view) {
        this.view = view;
        model = new LoginModel();
    }

    @Override
    public void login(UserInfo userInfo) {
        view.showDiaProgress();
        model.confirmUser(userInfo, this);
    }

    @Override
    public void forgotPwd() {
        model.forgotPwd();
        view.forgotPwd();
    }

    @Override
    public void cacheUser(UserInfo user) {
        UserInfoDao dao = GreenDaoManager.getInstance()
                .getNewSession()
                .getUserInfoDao();
        UserInfo userCache = dao.load(1L);
        if (null == userCache) {
            dao.insert(user);
        } else {
            if (user.getId() == userCache.getId()) {
                dao.update(user);
            }
        }

    }


    @Override
    public void loadCacheData() {
        if (model.getCacheUserInfoSize() > 0) {
            view.showDiaProgress();
            model.loadCacheUserInfo(this);
//            view.hideDiaProgress();
        } else {

        }

    }


    @Override
    public void onSuccess() {
        view.hideDiaProgress();
        view.login();
    }

    @Override
    public void onFailure(String errmsg) {
        view.hideDiaProgress();
        view.loginErrMsg(errmsg);
    }


}
