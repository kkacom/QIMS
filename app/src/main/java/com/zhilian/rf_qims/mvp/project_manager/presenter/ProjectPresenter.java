package com.zhilian.rf_qims.mvp.project_manager.presenter;


import android.content.Context;
import android.util.Log;

import com.colin.utils.LogUtil;
import com.zhilian.rf_qims.dao.CompanyDao;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.entity.Company;
import com.zhilian.rf_qims.entity.Project;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.mvp.project_manager.model.IProjectModel;
import com.zhilian.rf_qims.mvp.project_manager.model.ProjectModel;
import com.zhilian.rf_qims.mvp.project_manager.view.IProjectManagerView;
import com.zhilian.rf_qims.util.ToastUtils;

import java.util.List;

/**
 * Created by Administrator on 2017-9-22.
 */
public class ProjectPresenter implements IProjectPresenter, IProjectModel.ProjectCallBack {
    private IProjectManagerView view;
    private IProjectModel model;
    public boolean downLoadJudge;


    public ProjectPresenter(IProjectManagerView view) {
        this.view = view;
        model = new ProjectModel();
    }


    @Override
    public void onGetEnterpriseSizeSuccess(Integer size) {
        size = size % 10 != 0 ? size / 10 + 1 : size / 10;
//		for (int i = 0; i < size; i++) {
        if (size > 0) {
            model.getServerEnterprises(1, this, size);
        } else {
            view.hideDiaProgress();
        }
//		}
    }


    /**
     * 通过企业名称模糊查询企业
     *
     * @param enterprises 查询结果
     */
    @Override
    public void onSearchEnterprise(List<Company> enterprises) {
        for (Company bean : enterprises) {
            LogUtil.e("bean = " + bean);
        }
        //刷新公司列表
        view.updateEnterpriseList(enterprises);
    }

    @Override
    public void onFindEnterpriseLikeEnterpriseNameFailure(String message) {
        //view.showFindEnterpriseError(message);
    }

    @Override
    public void onSaveAllProjectsSuccess() {
        model.loadCacheEnterpriseList(this);
        //        view.refreshExpandableListViewAdapter();
    }


    /**
     * <4>、成功加载本地数据库中的所有企业
     *
     * @param list
     */
    @Override
    public void onLoadCacheEnterpriseListSuccess(List<Company> list) {
        /**
         * 1、加载本地数据库中的所有企业数据后，向view中传值。
         */
        view.updateEnterpriseList(list);
        /**
         * 2、根据企业名称，加载本地数据库中的所有项目及样品。
         *//*
        model.loadCacheProjectsByEnterpriseName();*/
    }

    @Override
    public void onLoadCacheProjectsByEid(List<Project> list) {
        view.updateProjectsList(list);
    }

    @Override
    public void onLoadCacheSamplesSuccess(long pid, List<Sample> list) {
        //view.setCacheSamples(pid,list);
    }

    @Override
    public void onGetServerSampleListSuccess(long pid, List<Sample> list) {
//		LogUtil.e("pid : "+pid+"下载完成！");
    }

    @Override
    public void onLoadDefaultEnterprise(Company company) {
//        view.setCurrentCompany(company);
    }

    @Override
    public void onLoadServerData(int curpage, int size) {
//		view.updateAllView();
        if (curpage > size) {
            //获取所有项目
            model.getServerProjectSize(this);
        } else {
            model.getServerEnterprises(curpage, this, size);
        }
    }

    @Override
    public void onRemoveProject() {
        view.onRemoveProject();
    }

	@Override
	public void onCloseDialog() {
		view.hideDiaProgress();
        downLoadJudge = false;
	}

	@Override
    public void onGetServerSamplesSuccess(int cus, List<Project> projects, int curpage, int totalpage) {
        if (curpage > totalpage) {
            if (cus >= projects.size() - 1) {
                Log.d("server", "sample:" + "下载数据完毕");
                view.hideDiaProgress();
                view.updateAllByServerData();
                ToastUtils.show("下载完成");
            } else {
                model.getServerSamples(cus, projects, 1, this);
            }
        } else {
            model.getServerSamples(cus, projects, curpage, this);
        }
    }


    @Override
    public void onGetSeverProjectSizeSuccess(int size) {
        size = size % 10 != 0 ? size / 10 + 1 : size / 10;
//        for (int i = 0; i < size; i++) {
        if (size > 0) {
            model.getServerProjects(1, this, size);
        } else {
            view.hideDiaProgress();
            ToastUtils.show("下载完成");
        }

//        }
    }

    /**
     * 成功获取所有项目的回调
     */
    @Override
    public void onGetServerProjectListSuccess(int curpage, int totalpage) {
        //view.setCacheProjects(list);
        //TODO 缓存项目列表到数据库
//        for (Project project : list) {
//            model.getServerSampleListSize(project.getId(), this);
        if (curpage > totalpage) {
            //获取每一个项目的所有样品信息的长度
            model.getServerSampleListSize(0, this, GreenDaoManager.getInstance().getNewSession().getProjectDao().queryBuilder().list());
        } else {
            //继续请求下一页
            model.getServerProjects(curpage, this, totalpage);
        }
    }

    @Override
    public void onGetServerSampleListSizeSuccess(int curpos, List<Project> projects) {
//        size = size % 10 != 0 ? size / 10 + 1 : size / 10;
//        for (int i = 0; i < size; i++) {
////			LogUtil.e("查询样品列表："+(i+1)+" 次！");
//            model.getServerSamples(pid, i + 1, this);
//        }
        if (curpos > projects.size() - 1) {
            model.getServerSamples(0, projects, 1, this);
        } else {
            model.getServerSampleListSize(curpos, this, projects);
        }
    }

    /**
     * 加载缓存数据
     */
    @Override
    public void loadCacheData(long eid) {
        model.loadCacheData(eid, this);
    }

    /**
     * 下载网络数据:从业评估的考评实操人员、配置、人员
     */
    public void downloadAssessData() {
        model.getServerAssessData(this);
    }

    /**
     * 下载网络数据:企业，项目，样品
     */
    public void downloadServerData() {
//        model.getServerProjectSize(this);
        model.getServerEnterpriseSize(this);
    }

    @Override
    public void searchEnterpriseByCondition(String condition) {
        model.searchEnterpriseByCondition(condition, this);
    }


    /**
     * 上传数据到服务器
     */
    @Override
    public void uploadData() {
        /**
         * 一、上传所有项目数据
         */
        model.uploadLocalProjects();
        /**
         *  二、上传所有样品数据
         */
        model.uploadLocalSamples();
    }

    /**
     * 通过企业名称查找企业信息
     *
     * @param eid
     * @return
     */
    @Override
    public Company findEnterpriseByEID(long eid) {
        return GreenDaoManager.getInstance()
                .getNewSession()
                .getCompanyDao()
                .queryBuilder()
                .where(CompanyDao.Properties.Id.eq(eid))
                .build()
                .unique();
    }

    @Override
    public void updateViewData() {
        //view.clearViewCacheData();
        //view.showSelectEnterpriseView();
    }

    @Override
    public void loadProjectsByEid(long eid) {
        model.loadProjectsByEID(eid, this);
    }

    @Override
    public void saveProject(Project project) {
        GreenDaoManager.getInstance().getNewSession().getProjectDao().insert(project);
        model.loadProjectsByEID(project.getEntrustId(), this);
    }


//    public void loadDefaultEnterprise() {
//        model.loadDefaultEnterprise(this);
//    }

    public void loadAllEnterprises() {
        model.loadCacheEnterpriseList(this);
    }

    public void removeProject(Project item) {
        model.removeProject(item, this);
    }
}
