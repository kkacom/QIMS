package com.zhilian.rf_qims.mvp.main.presenter;


import com.zhilian.rf_qims.bean.WorkResBean;
import com.zhilian.rf_qims.entity.UserInfo;
import com.zhilian.rf_qims.mvp.main.model.IMainModel;
import com.zhilian.rf_qims.mvp.main.model.MainModel;
import com.zhilian.rf_qims.mvp.main.view.IMainView;

import java.util.List;

/**
 * Created by Administrator on 2017-9-22.
 */
public class MainPresenter implements IMainPresenter<UserInfo> {
    private IMainView view;
    private IMainModel model;

    public MainPresenter(IMainView view) {
        this.view = view;
        model = new MainModel();
      //  model.startPollingService(App.getAppContext());
    }

    @Override
    public void checkVersion() {
       // model.chechVersion(this);

    }

    @Override
    public void destroyUserPwd() {
        model.clearUserPwd();
        view.reLogin();
    }


    @Override
    public void loadCacheData() {

    }

    public void loadView() {
        List<WorkResBean> list = model.initViewRes();
        view.initAdapter(list);
    }
}
