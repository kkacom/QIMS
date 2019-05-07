package com.zhilian.rf_qims.mvp.project_manager.presenter;


import com.zhilian.rf_qims.entity.Company;
import com.zhilian.rf_qims.entity.Project;

/**
 * Created by Administrator on 2017-9-22.
 */
public interface IProjectPresenter {
	void loadCacheData(long eid);
	void searchEnterpriseByCondition(String condition);

	void uploadData();

	/**
	 * 通过企业ID查找企业信息
	 * @param eid
	 * @return
	 */
	Company findEnterpriseByEID(long eid);

	/**
	 * 更新页面数据
	 */
	void updateViewData();

	void loadProjectsByEid(long eid);



	void saveProject(Project project);


}
