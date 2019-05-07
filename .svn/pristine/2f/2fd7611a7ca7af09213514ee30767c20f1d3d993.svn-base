package com.zhilian.rf_qims.mvp.project_manager.model;


import android.util.Log;

import com.colin.constant.Constants;
import com.colin.http.HttpServiceManager;
import com.colin.http.HttpUtil;
import com.colin.utils.LogUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhilian.rf_qims.bean.CompanyBean;
import com.zhilian.rf_qims.bean.CompanyRootBean;
import com.zhilian.rf_qims.bean.ProjectBean;
import com.zhilian.rf_qims.bean.ProjectRootBean;
import com.zhilian.rf_qims.bean.SampleBean;
import com.zhilian.rf_qims.bean.SampleRootBean;
import com.zhilian.rf_qims.dao.CompanyDao;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.dao.ProjectDao;
import com.zhilian.rf_qims.dao.SampleCheckDao;
import com.zhilian.rf_qims.dao.SampleDao;
import com.zhilian.rf_qims.dao.SamplePageDao;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
import com.zhilian.rf_qims.entity.Company;
import com.zhilian.rf_qims.entity.EntEquipmentDao;
import com.zhilian.rf_qims.entity.EntEquipmentJson;
import com.zhilian.rf_qims.entity.PersonnelDao;
import com.zhilian.rf_qims.entity.PersonnelJson;
import com.zhilian.rf_qims.entity.Project;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.entity.SampleCheck;
import com.zhilian.rf_qims.entity.SamplePage;
import com.zhilian.rf_qims.entity.WorkUserDao;
import com.zhilian.rf_qims.entity.WorkUserJson;
import com.zhilian.rf_qims.util.ToastUtils;
import com.zhilian.rf_qims.widget.DialogProgress;

import org.greenrobot.greendao.query.QueryBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017-9-22.
 */
public class ProjectModel implements IProjectModel {
    private DialogProgress dialogProgress;

    @Override
    public void getAllServerData() {

    }

    /**2、获取从业评估基础数据（配置表）*/
    @Override
    public void getServerAssessData(ProjectCallBack callBack) {
        HttpServiceManager.getInstance().getHttpService()
            .getServerData(HttpUtil.initUrl(), HttpUtil.initQueryMethod("getCaConfigList"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>() {
                @Override
                public void accept(ResponseBody responseBody) throws Exception {
                    String json = responseBody.string();
                    Type type = new TypeToken<List<CaConfigJson>>(){}.getType();
                    List<CaConfigJson> list = new Gson().fromJson(json, type);
                    CaConfigDao.insertOrReplaceInTx(list);
                    getServerPersonnelData(callBack);
                }
            }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                ToastUtils.show("下载从业评估配置数据失败");
                callBack.onCloseDialog();
                //LogUtil.e("getServerAssessData:" + throwable.getMessage());
            }
        });
    }
    //从业评估人员数据
    public void getServerPersonnelData(ProjectCallBack callBack) {
        HttpServiceManager.getInstance().getHttpService()
            .getServerData(HttpUtil.initUrl(), HttpUtil.initQueryMethod("getPersonnelList"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>() {
                @Override
                public void accept(ResponseBody responseBody) throws Exception {
                    String json = responseBody.string();
                    Type type = new TypeToken<List<PersonnelJson>>(){}.getType();
                    List<PersonnelJson> list = new Gson().fromJson(json, type);
                    for (PersonnelJson personnelJson : list){
                        personnelJson.setUPLOADSTATUS(2);
                    }
                    PersonnelDao.insertOrReplaceInTx(list);
                    getServerUserData(callBack);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    ToastUtils.show("下载从业评估人员数据失败");
                    callBack.onCloseDialog();
                    LogUtil.e("getServerAssessData:" + throwable.getMessage());
                }
            });
    }
    //从业考核人员数据
    public void getServerUserData(ProjectCallBack callBack) {
        HttpServiceManager.getInstance().getHttpService()
            .getServerData(HttpUtil.initUrl(), HttpUtil.initQueryMethod("getUserList1"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>() {
                @Override
                public void accept(ResponseBody responseBody) throws Exception {
                    String json = responseBody.string();
                    Type type = new TypeToken<List<WorkUserJson>>(){}.getType();
                    List<WorkUserJson> list = new Gson().fromJson(json, type);
                    WorkUserDao.insertOrReplaceInTx(list);
                    getServerEquipmentData(callBack);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    ToastUtils.show("下载从业评估考评人员数据失败");
                    callBack.onCloseDialog();
                    LogUtil.e("getServerAssessData:" + throwable.getMessage());
                }
            });
    }
    //从业设备数据
    public void getServerEquipmentData(ProjectCallBack callBack) {
        HttpServiceManager.getInstance().getHttpService()
            .getServerData(HttpUtil.initUrl(), HttpUtil.initQueryMethod("getEntEquipmentList"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>() {
                @Override
                public void accept(ResponseBody responseBody) throws Exception {
                    String json = responseBody.string();
                    Type type = new TypeToken<List<EntEquipmentJson>>(){}.getType();
                    List<EntEquipmentJson> list = new Gson().fromJson(json, type);
                    for (EntEquipmentJson equipmentJson : list){
                        equipmentJson.setUPLOADSTATUS(2);
                    }
                    EntEquipmentDao.insertOrReplaceInTx(list);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    ToastUtils.show("下载从业评估设备数据失败");
                    callBack.onCloseDialog();
                    LogUtil.e("getServerAssessData:" + throwable.getMessage());
                }
            });
    }

    /**
     * 3、getEnterpriseListSize 获取企业总条数
     *
     * @param callBack
     */
    @Override
    public void getServerEnterpriseSize(final ProjectCallBack callBack) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNumber", "1");
        HttpServiceManager.getInstance()
                .getHttpService()
                .getServerData(HttpUtil.initUrl(), HttpUtil.initQueryParams("query", Constants.QUERY_ENTERPRISE_SIZE, params))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        Log.d("server", "en--" + json);
                        callBack.onGetEnterpriseSizeSuccess(Integer.valueOf(json));

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e("getServerEnterpriseSize:" + throwable.getMessage());
                    }
                });


    }

    /**
     * 4、getEnterpriseList 获取所有企业信息
     *
     * @param pageNumber
     * @param callBack
     */
    @Override
    public void getServerEnterprises(int pageNumber, final ProjectCallBack callBack, int size) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNumber", "" + pageNumber);
        HttpServiceManager.getInstance()
                .getHttpService()
                .getServerData(HttpUtil.initUrl(), HttpUtil.initQueryParams(Constants.QUERY_ENTERPRISE_List, params))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        Log.d("server", "en--" + json);
                        //LogUtil.e("getEnterpriseList = " + json);
                        CompanyRootBean root = new Gson().fromJson(json, CompanyRootBean.class);
                        //LogUtil.e("size : "+ (null == root));
                        CompanyDao dao = GreenDaoManager.getInstance().getNewSession().getCompanyDao();
                        for (CompanyBean bean : root.getList()) {
                            //LogUtil.e("bean:"+bean);
                            Company company = new Company();
                            company.setId(bean.getEID());//存eid
                            company.setMainId(bean.getID());//id
                            company.setName(bean.getNAME());
                            company.setNumber(bean.getNUMBER());
                            company.setAddr(bean.getADDRESS());
                            company.setArea(bean.getAREA());
                            company.setContacts(bean.getCONTACTS1());
                            company.setPhone(bean.getCONTACTS1_TE());
                            try {
                                dao.insert(company);
                            } catch (Exception e) {
                                dao.update(company);
                            }
                        }
                        callBack.onLoadServerData(pageNumber + 1, size);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e("getEnterpriseList:" + throwable.getMessage());
                    }
                });
    }

    /**
     * 5、getCommissionSize 获取所有项目总条数
     *
     * @param callBack
     */
    @Override
    public void getServerProjectSize(final ProjectCallBack callBack) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNumber", "" + 1);
        HttpServiceManager.getInstance()
                .getHttpService()
                .getServerData(HttpUtil.initUrl(), HttpUtil.initQueryParams(Constants.QUERY_PROJECT_SIZE, params))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
//					LogUtil.e("getServerProjectSize = " + json);
                        Log.d("server", "pro--" + json);
                        callBack.onGetSeverProjectSizeSuccess(Integer.valueOf(json));

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e("getEnterpriseList:" + throwable.getMessage());
                    }
                });
    }

    /**
     * 6、getProjectCodeFHlist 获取所有项目信息
     *
     * @param pageNumber
     * @param callBack
     */
    @Override
    public void getServerProjects(int pageNumber, final ProjectCallBack callBack, int totalpage) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNumber", "" + pageNumber);
        HttpServiceManager.getInstance()
                .getHttpService()
                .getServerData(HttpUtil.initUrl(), HttpUtil.initQueryParams("getProjectCodeFHlist", params))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        Log.d("server", "pro--" + json);
//					LogUtil.e("getEnterpriseList = " +json);
                        List<ProjectBean> list = new Gson().fromJson(json, ProjectRootBean.class).getList();
                        List<Project> data = new ArrayList<>();
                        ProjectDao dao = GreenDaoManager.getInstance().getmDaoMaster().newSession().getProjectDao();
                        for (ProjectBean bean : list) {
//						LogUtil.e(bean.toString());
                            List<Project> result = dao.queryBuilder().where(ProjectDao.Properties.ItemCode.eq(bean.getITEMCODE())).list();
                            Project project = null;
                            if (result.isEmpty()) {
                                project = new Project();
                            } else {
                                project = result.get(0);
                            }
                            project.setId(bean.getID());
                            project.setEntrustId(bean.getENTRUSTUNITID());
                            project.setItemCode(bean.getITEMCODE());
                            project.setItemName(bean.getITEMNAME());
                            project.setProjectAddr(bean.getPROJECTADDRESS());
                            project.setSampleSource(bean.getSAMPLESOURCE());
                            project.setAreaId(bean.getAREAID());
                            project.setAcceptDate(bean.getACCEPTDATE());
                            project.setSender(bean.getSENDER());
                            project.setAcceptor(bean.getACCEPTERNAME());
                            project.setCommissionCategory(bean.getENTRUSTTYPE());//(以下实体2019.1.4.18:19点新增)
                            project.setCommissionShape(bean.getENTRUSTFORM());
                            project.setProjectCode(bean.getPROJECTNUMBER());
                            project.setWitnessUnit(bean.getWITNESSUNIT());
                            project.setWitnessUnitPhone(bean.getWITNESSUNITPHONE());
                            project.setWitness(bean.getWITNESS());
                            project.setWitnessPhone(bean.getWITNESSPHONE());
                            project.setSenderPhone(bean.getSENDERPHONE());
                            project.setSupervisionUnit(bean.getSUPERVISIONUNIT());
                            project.setSupervisor(bean.getSUPERVISOR());
                            project.setSecret(bean.getSECRECY());
                            project.setDesc(bean.getREMARK());
                            project.setStatus(0x0);
                            project.setIsselected(0);
                            data.add(project);
                            try {
                                dao.insert(project);

                            } catch (Exception e) {
                                dao.update(project);
                            }


                        }
                        callBack.onGetServerProjectListSuccess(pageNumber + 1, totalpage);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e("getServerProjects:" + throwable.getMessage());
                    }
                });
    }

    /**
     * 7、getSampleListSize 获取选中项目所有样品总条数
     *
     * @param callBack
     */
    @Override
    public void getServerSampleListSize(int curpos, final ProjectCallBack callBack, List<Project> projects) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNumber", "" + 1);
        params.put("id", "" + projects.get(curpos).getId());
        HttpServiceManager.getInstance()
                .getHttpService()
                .getServerData(HttpUtil.initUrl(), HttpUtil.initQueryParams("query", "getSampleListSize", params))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
//					LogUtil.e("getEnterpriseList = " + responseBody.string());
                        String json = responseBody.string();
//					LogUtil.e("getSampleListSize = " + json);
                        //// TODO: 2018/4/28 0028  增加project -sample-size数据库
                        int size = Integer.valueOf(json);
                        Log.d("server", "pid--sample:" + size + "--" + projects.get(curpos).getId());
                        size = size % 10 != 0 ? size / 10 + 1 : size / 10;
                        SamplePage samplePage = new SamplePage();
                        samplePage.setId(projects.get(curpos).getId());
                        samplePage.setSamplepage(size);
                        SamplePageDao samplePageDao = GreenDaoManager.getInstance().getNewSession().getSamplePageDao();
                        try {
                            samplePageDao.insert(samplePage);
                        } catch (Exception e) {
                            samplePageDao.update(samplePage);
                        }
                        callBack.onGetServerSampleListSizeSuccess(curpos + 1, projects);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e("getSampleListSize:" + throwable.getMessage());
                    }
                });
    }


    /**
     * 8、getSampleList 获取选中项目的所有样品信息
     *
     * @param
     * @param pageNumber
     * @param callBack
     */
    @Override
    public void getServerSamples(int cus, List<Project> projects, int pageNumber, final ProjectCallBack callBack) {
        Long id = projects.get(cus).getId();
        if (GreenDaoManager.getInstance().getNewSession().getSamplePageDao().load(id).getSamplepage() == 0) {
            if (cus+1 == projects.size() - 1){ //2019.3.6:17:18分添加解决无法下载数据问题
                getServerSamples(cus + 1, projects, pageNumber, callBack);
            }else {
                callBack.onGetServerSamplesSuccess(cus+1, projects, 1, GreenDaoManager.getInstance().getNewSession().getSamplePageDao().load(id).getSamplepage());
            }
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("pageNumber", "" + pageNumber);
        params.put("id", "" + projects.get(cus).getId());
        HttpServiceManager.getInstance()
                .getHttpService()
                .getServerData(HttpUtil.initUrl(), HttpUtil.initQueryParams("query", "getSampleList", params))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        //LogUtil.e("getServerSamples = " + json);
                        Log.d("server", "sample:" + json);
                        List<SampleBean> list = new Gson().fromJson(json, SampleRootBean.class).getList();
                        List<Sample> data = new ArrayList<>();
                        SampleDao dao = GreenDaoManager.getInstance().getmDaoMaster().newSession().getSampleDao();
                        SampleCheckDao sampleCheckDao=GreenDaoManager.getInstance().getNewSession().getSampleCheckDao();
                        for (SampleBean bean : list) {
                            //	LogUtil.e("sample:"+bean);
                            Sample sample = new Sample();
                            sample.setId(bean.getID());
                            sample.setPid(bean.getRID());
                            sample.setPart(bean.getUSEPART());
                            sample.setInstallDate(bean.getREQUIREMENTDATE());
                            sample.setSampleState(bean.getSTATUS());
                            sample.setSampleNo(bean.getNUMBER());
                            sample.setDesc(bean.getSPECIFICATION());
                            sample.setSampleType(bean.getTYPE());
                            sample.setMadeDate(bean.getPRODUCTIONDATE());
                            sample.setSampleModel(bean.getSMODE());
                            sample.setStatus(0x0);
                            sample.setIsselected(0);
                            SampleCheck sampleCheck=new SampleCheck();
                            sampleCheck.setId(bean.getID());
                            sampleCheck.setPid(bean.getRID());
                            sampleCheck.setStatus(0x0);
                            //	LogUtil.e("sample:"+sample);
                            //	LogUtil.e("--------------------------------------------");
                            //	LogUtil.e("--------------------------------------------");
                            data.add(sample);
                            try {
                                dao.insert(sample);
                                sampleCheckDao.insert(sampleCheck);
                            } catch (Exception e) {
                                dao.update(sample);
                                sampleCheckDao.update(sampleCheck);
                            }
                        }

                        SamplePageDao samplePageDao = GreenDaoManager.getInstance().getNewSession().getSamplePageDao();
                        SamplePage samplePage = samplePageDao.load(projects.get(cus).getId());
                        callBack.onGetServerSamplesSuccess(cus + 1, projects, pageNumber + 1, samplePage.getSamplepage());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e("getServerSamples:" + throwable.getMessage());
                    }
                });
    }


    /**
     * <1>、通过企业名称查询企业信息
     *
     * @param condition
     * @param callBack
     */
    @Override
    public void searchEnterpriseByCondition(String condition, final ProjectCallBack callBack) {
        LogUtil.e("condition = " + condition);
        List<Company> list = GreenDaoManager.getInstance()
                .getNewSession()
                .getCompanyDao()
                .queryBuilder()
                .where(CompanyDao.Properties.Name.like("%" + condition + "%"))
                .list();

        if (!list.isEmpty()) {
            callBack.onSearchEnterprise(list);
        }
    }


    /**
     * <5>、根据企业名称，重新加载本地缓存数据中的项目及样品数据
     *
     * @param name
     * @param callback
     */
    @Override
    public void loadCacheProjectsByEnterpriseName(String name, final ProjectCallBack callback) {

    }

    /**
     * 一、上传所有项目数据
     */
    @Override
    public void uploadLocalProjects() {

    }

    /**
     * 二、上传所有样品数据
     */
    @Override
    public void uploadLocalSamples() {

    }

    @Override
    public void loadProjectsByEID(long eid, final ProjectCallBack callback) {

        List<Project> list = GreenDaoManager.getInstance()
                .getNewSession()
                .getProjectDao()
                .queryBuilder()
                .where(ProjectDao.Properties.EntrustId.eq(eid))
                .list();
        if (!list.isEmpty()) {
            callback.onLoadCacheProjectsByEid(list);
        } else {
            callback.onLoadCacheProjectsByEid(new ArrayList<Project>());
        }
    }

    @Override
    public void loadCacheData(long eid, final ProjectCallBack callback) {
        QueryBuilder<Project> builder = GreenDaoManager.getInstance().getNewSession().getProjectDao().queryBuilder();
        builder.where(ProjectDao.Properties.EntrustId.eq(eid));
        List<Project> list = builder.list();
        if (null != list && 0 < list.size()) {
            callback.onLoadCacheProjectsByEid(list);
        }
    }

    @Override
    public void loadDefaultEnterprise(ProjectCallBack callback) {
        try {
            Company company = GreenDaoManager.getInstance()
                    .getNewSession()
                    .getCompanyDao()
                    .queryBuilder()
                    .list()
                    .get(0);
            if (null != company) {
                callback.onLoadDefaultEnterprise(company);
            }
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
    }

    @Override
    public void removeProject(Project item, ProjectCallBack callBack) {
        GreenDaoManager.getInstance().getNewSession().getProjectDao().delete(item);
        callBack.onRemoveProject();
    }


    /**
     * <3>、加载选中项目的所有样品
     *
     * @param pid
     * @param callBack
     */
    @Override
    public void loadCacheSamples(final long pid, final ProjectCallBack callBack) {
        QueryBuilder<Sample> builder = GreenDaoManager.getInstance().getNewSession().getSampleDao().queryBuilder();
        builder.where(SampleDao.Properties.Pid.eq(pid));
        List<Sample> list = builder.list();
        if (null != list && 0 < list.size()) {
            callBack.onLoadCacheSamplesSuccess(pid, list);
        }
    }

    @Override
    public void loadCacheEnterpriseList(final ProjectCallBack callBack) {
        QueryBuilder<Company> builder = GreenDaoManager.getInstance().getNewSession().getCompanyDao().queryBuilder();
        List<Company> list = builder.list();
        if (null != list && 0 < list.size()) {
            callBack.onLoadCacheEnterpriseListSuccess(list);
        }
    }


}
