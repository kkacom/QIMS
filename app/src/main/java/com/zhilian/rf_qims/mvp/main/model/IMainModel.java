package com.zhilian.rf_qims.mvp.main.model;

import com.zhilian.rf_qims.bean.WorkResBean;

import java.util.List;

/**
 * Created by Administrator on 2017-10-10.
 */

public interface IMainModel {
    void clearUserPwd();
    List<WorkResBean> initViewRes();
}
