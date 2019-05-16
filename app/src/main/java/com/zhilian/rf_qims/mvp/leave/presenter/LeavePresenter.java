package com.zhilian.rf_qims.mvp.leave.presenter;


import com.zhilian.rf_qims.bean.LeaveDoneBean;
import com.zhilian.rf_qims.bean.LeaveMineBean;
import com.zhilian.rf_qims.bean.TodoItemBean;
import com.zhilian.rf_qims.mvp.leave.model.ILeaveModel;
import com.zhilian.rf_qims.mvp.leave.model.LeaveModel;
import com.zhilian.rf_qims.mvp.leave.view.ILeaveView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017-12-28.
 */

public class LeavePresenter implements ILeavePresenter,ILeaveModel.CallBack1<List<TodoItemBean>>,ILeaveModel.CallBack2<List<LeaveDoneBean.DoneItemBean>>{
    private ILeaveModel mModel;
    private ILeaveView mView;

    public LeavePresenter(ILeaveView view) {
        mView = view;
        mModel = new LeaveModel();
    }

    @Override
    public void initApproves(HashMap<String, String> map) {
        mModel.loadServerApproves(map,this);
    }

    @Override
    public void initApplies(HashMap<String, String> map) {
        mModel.loadServerApplies(map,this);
    }

    @Override
    public void initMyApplies(HashMap<String, String> map) {
        mModel.loadServerMyApplies(map,this);
    }

    @Override
    public void loadApplies(List<TodoItemBean> list) {
        mView.onInitAppliesSuccess(list);
    }

    @Override
    public void loadApproves(List<LeaveDoneBean.DoneItemBean> list) {
        mView.onInitApprovesSuccess(list);
    }

    @Override
    public void onDisconnected() {
        mView.onDisConnected();
    }

    @Override
    public void loadMyApplies(List<LeaveMineBean.ItemBean> list) {
        mView.onInitMyAppliesSuccess(list);
    }


}
