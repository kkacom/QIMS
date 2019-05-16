package com.zhilian.rf_qims.mvp.leave.view;

import com.zhilian.rf_qims.base.IBaseView;
import com.zhilian.rf_qims.base.IDetailBaseView;
import com.zhilian.rf_qims.bean.LeaveDetailBean;

/**
 * Created by Administrator on 2018-1-2.
 */

public interface ILeaveDetailView extends IDetailBaseView {

    void onGetLeaveDetailSuccess(LeaveDetailBean bean);

    void onSaveOpinionSuccess(String result);

    void onSaveOpinionFailure(String result);

    void updateDayt(String dayt);

	void onDisconnected(String message);

	void onDeleted();
}
