package com.zhilian.rf_qims.mvp.leave.view;


import com.zhilian.rf_qims.bean.LeaveDoneBean;
import com.zhilian.rf_qims.bean.LeaveMineBean;
import com.zhilian.rf_qims.bean.TodoItemBean;

import java.util.List;

/**
 * Created by Administrator on 2017-12-28.
 */

public interface ILeaveView {
    void onInitAppliesSuccess(List<TodoItemBean> list);
    void onInitApprovesSuccess(List<LeaveDoneBean.DoneItemBean> root);
    void onDisConnected();

	void onInitMyAppliesSuccess(List<LeaveMineBean.ItemBean> list);
}
