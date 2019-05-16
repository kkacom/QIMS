package com.zhilian.rf_qims.mvp.leave.presenter;

import android.app.Activity;

import com.zhilian.rf_qims.base.IBasePresenter;
import com.zhilian.rf_qims.mvp.leave.view.LeaveDetailActivity;
import com.zhilian.rf_qims.bean.LeaveDetailBean;
import com.zhilian.rf_qims.mvp.leave.model.LeaveDetailModel;
import com.zhilian.rf_qims.mvp.leave.view.ILeaveDetailView;

/**
 * Created by Administrator on 2018-1-4.
 */

public class LeaveDetailPresenter implements IBasePresenter ,LeaveDetailModel.CallBack,LeaveDetailModel.CallBack1,LeaveDetailModel.CallBack2{
    private ILeaveDetailView mView;
    private LeaveDetailModel mModel;

    public LeaveDetailPresenter(ILeaveDetailView view) {
        mView = view;
        mModel = new LeaveDetailModel();
    }

    public void getLeaveDetail(String docid,String isdone) {
        mModel.getLeaveDetail(docid,isdone,this);
    }

    @Override
    public void onGetLeaveDetailSuccess(LeaveDetailBean bean) {
        mView.onGetLeaveDetailSuccess(bean);
    }

    @Override
    public void onDisconnected(String message) {
        mView.onDisconnected(message);
    }

    @Override
    public void onDeleted() {
        mView.onDeleted();
    }

    public void saveOpinion(String saveModel, LeaveDetailBean temp) {
        mModel.saveOpinion(saveModel,temp,this);
    }

    @Override
    public void onSaveOpinionSuccess(String result) {
        mView.onSaveOpinionSuccess(result);
    }

    @Override
    public void onSaveOpinionFailure(String result) {
        mView.onSaveOpinionFailure(result);
    }

    public void getLeaveDayt(String sdate,String bm,String edate,String em) {
        mModel.getLeaveDayt(sdate,bm,edate,em,this);
    }

    @Override
    public void onGetLeaveDaytSuccess(String dayt) {
        mView.updateDayt(dayt);
    }

    public void deleteApply(LeaveDetailBean leave, LeaveDetailActivity leaveDetailActivity) {
        mModel.deleteApply(leave,this, leaveDetailActivity);
    }
}
