package com.zhilian.rf_qims.mvp.launcher;

import com.zhilian.rf_qims.bean.UpdateBean;

/**
 * Created by colin on 2018/3/26 15:25 .
 */

public class LauncherPresenter  implements LauncherModel.Callback{
	private LauncherActivity view;
	private LauncherModel model;

	public LauncherPresenter(LauncherActivity view) {
		this.view = view;
		model = new LauncherModel();
	}

	public void checkVersion() {
		model.checkVersion(this);
	}

	@Override
	public void onCheckVersion(UpdateBean model) {
		view.onCheckVersion(model);
	}
}
