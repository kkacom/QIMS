package com.zhilian.rf_qims.mvp.main.model;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.bean.WorkResBean;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.dao.UserInfoDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-10-10.
 */

public class MainModel implements IMainModel {
    @Override
    public void clearUserPwd() {
        UserInfoDao dao = GreenDaoManager.getInstance()
                .getNewSession()
                .getUserInfoDao();
        dao.deleteByKey(0L);
    }

    @Override
    public List<WorkResBean> initViewRes() {
        List<WorkResBean> list = new ArrayList<>();
        list.add(new WorkResBean(R.mipmap.test_product));
        list.add(new WorkResBean(R.mipmap.test_install));
        list.add(new WorkResBean(R.mipmap.assessment));
        list.add(new WorkResBean(R.drawable.icon_leave_item));
//        list.add(new WorkResBean(R.mipmap.employment_evaluation));
//        list.add(new WorkResBean(R.mipmap.integrity_evluation));
//        list.add(new WorkResBean(R.mipmap.icon_feedback));
        return list;
    }
}
