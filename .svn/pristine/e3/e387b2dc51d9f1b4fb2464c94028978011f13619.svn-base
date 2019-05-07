package com.zhilian.rf_qims.mvp.project_detail.presenter;

import com.zhilian.rf_qims.entity.Area;
import com.zhilian.rf_qims.entity.Project;
import com.zhilian.rf_qims.mvp.project_detail.model.ProjectDetailModel;
import com.zhilian.rf_qims.mvp.project_detail.view.ProjectDetailActivity;

import java.util.List;

/**
 * Created by colin on 2018/3/12 15:56 .
 */

public class ProjectDetailPresenter implements ProjectDetailModel.Callback {
	private ProjectDetailModel mModel;
	private ProjectDetailActivity mView;

	public ProjectDetailPresenter(ProjectDetailActivity view) {
		mView = view;
		mModel = new ProjectDetailModel();
	}

	public void findLocalAreas() {
		mModel.findLocalAreas(this);
	}

	@Override
	public void onFindLocalAreas(List<Area> areas) {
		mView.setAreas(areas);
	}

	@Override
	public void onSaveProject(String msg) {
		mView.onSaveProject(msg);
	}

	public void saveProject(Project project) {
		mModel.saveProject(project,this);
	}
}
