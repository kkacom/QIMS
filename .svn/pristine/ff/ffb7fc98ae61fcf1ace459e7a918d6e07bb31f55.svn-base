package com.zhilian.rf_qims.mvp.project_detail.model;


import com.zhilian.rf_qims.dao.DBRxManager;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.dao.ProjectDao;
import com.zhilian.rf_qims.entity.Area;
import com.zhilian.rf_qims.entity.Project;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by colin on 2018/3/12 15:55 .
 */

public class ProjectDetailModel {
	public void findLocalAreas(Callback callback) {
		DBRxManager.getInstance().findLocalAreas().subscribe(new Consumer<List<Area>>() {
			@Override
			public void accept(List<Area> areas) throws Exception {
				callback.onFindLocalAreas(areas);
			}
		}, new Consumer<Throwable>() {
			@Override
			public void accept(Throwable throwable) throws Exception {

			}
		});
	}

	public void saveProject(Project project, Callback callback) {
		ProjectDao dao = GreenDaoManager.getInstance().getNewSession().getProjectDao();
		List list = dao.queryBuilder().where(ProjectDao.Properties.ItemCode.eq(project.getItemCode())).list();

		if (list.isEmpty()) {
			dao.insert(project);
			callback.onSaveProject("保存数据成功！");
		} else {
			dao.update(project);
			callback.onSaveProject("更新数据成功！");
		}

	}

	public interface Callback {

		void onFindLocalAreas(List<Area> areas);

		void onSaveProject(String msg);
	}
}
