package com.zhilian.rf_qims.mvp.project_manager.model;


import android.content.Context;

import com.zhilian.rf_qims.entity.Company;
import com.zhilian.rf_qims.entity.Project;
import com.zhilian.rf_qims.entity.Sample;

import java.util.List;

/**
 * Created by Administrator on 2017-9-22.
 */
public interface IProjectModel {
    //获取服务器企业和项目的所有信息
    void getAllServerData();

    /**
     * 2、getEnterpriseListSize 获取企业总条数
     */
    void getServerAssessData(ProjectCallBack callBack);

    /**
     * 3、getEnterpriseListSize 获取企业总条数
     *
     * @param callBack
     */
    void getServerEnterpriseSize(ProjectCallBack callBack);

    /**
     * 4、getEnterpriseList 获取所有企业信息
     *
     * @param pageNumber
     * @param callBack
     */
    void getServerEnterprises(int pageNumber, ProjectCallBack callBack, int totalsize);

    /**
     * 5、getCommissionSize 获取所有项目总条数
     *
     * @param callBack
     */
    void getServerProjectSize(final ProjectCallBack callBack);

    /**
     * 6、getProjectCodeFHlist 获取所有项目信息
     *
     * @param pageNumber
     * @param callBack
     */
    void getServerProjects(int pageNumber, ProjectCallBack callBack, int totalpage);

    /**
     * 7、getSampleListSize 获取选中项目所有样品总条数
     *
     * @param pid
     * @param pageNumber
     * @param callBack
     */
    void getServerSamples(int cus, List<Project> projects, int pageNumber, ProjectCallBack callBack);

    /**
     * 8、getSampleList 获取选中项目的所有样品信息
     *
     * @param id       项目ID
     * @param callBack
     */
    void getServerSampleListSize(int curpos, ProjectCallBack callBack, List<Project> projects);


    /**
     * <1>、通过企业名称查询企业信息
     *
     * @param enterpriseName
     * @param callBack
     */
    void searchEnterpriseByCondition(String enterpriseName, ProjectCallBack callBack);


    /**
     * <3>、加载选中项目的所有样品
     *
     * @param pid
     * @param callBack
     */
    void loadCacheSamples(long pid, ProjectCallBack callBack);

    /**
     * <4>、重新加载选中企业的所有项目
     *
     * @param callBack
     */
    void loadCacheEnterpriseList(ProjectCallBack callBack);

    /**
     * <5>、根据企业名称，重新加载本地缓存数据中的项目及样品数据
     *
     * @param name
     * @param callback
     */
    void loadCacheProjectsByEnterpriseName(String name, ProjectCallBack callback);

    /**
     * 一、上传所有项目数据
     */
    void uploadLocalProjects();

    /**
     * 二、上传所有样品数据
     */
    void uploadLocalSamples();

    void loadProjectsByEID(long eid, ProjectCallBack callback);

    void loadCacheData(long eid, ProjectCallBack callback);

    void loadDefaultEnterprise(ProjectCallBack callback);

    void removeProject(Project item, ProjectCallBack callBack);


    interface ProjectCallBack {
        /**
         * 1、成功获取企业总数的回调
         *
         * @param size
         */
        void onGetEnterpriseSizeSuccess(Integer size);

        /**
         * 4、成功获取项目总数的回调
         *
         * @param size
         */
        void onGetSeverProjectSizeSuccess(int size);


        /**
         * 5、成功获取所有项目的回调
         *
         *
         */
        void onGetServerProjectListSuccess(int curpage, int totalpage);

        /**
         * 6、成功获取选中项目的所有样品的回调
         *
         * @param pid        项目ID
         * @param pageNumber
         */
        void onGetServerSampleListSizeSuccess(int cus, List<Project> projects);

        /**
         * （1）、成功保存所有项目的回调
         */
        void onSaveAllProjectsSuccess();

        /**
         * <1>、成功通过企业名称模糊查询企业列表的回调
         *
         * @param enterprises
         */
        void onSearchEnterprise(List<Company> enterprises);

        /**
         * <2>、通过企业名称模糊查询企业列表失败的回调
         *
         * @param message
         */
        void onFindEnterpriseLikeEnterpriseNameFailure(String message);

        /**
         * <4>、成功加载本地数据库中的所有企业的回调
         *
         * @param list
         */
        void onLoadCacheEnterpriseListSuccess(List<Company> list);

        /**
         * <5>、 成功加载本地缓存数据中的项目
         *
         * @param list
         */
        void onLoadCacheProjectsByEid(List<Project> list);

        /**
         * <6>、 成功加载本地缓存数据中的样品
         *
         * @param pid
         * @param list
         */
        void onLoadCacheSamplesSuccess(long pid, List<Sample> list);

        void onGetServerSampleListSuccess(long pid, List<Sample> list);

        void onLoadDefaultEnterprise(Company company);

        //完成服务器数据下载
        void onLoadServerData(int curpage, int size);

        void onRemoveProject();

        void onCloseDialog();

        void onGetServerSamplesSuccess(int cus, List<Project> projects, int curpage, int totalsize);
    }
}
