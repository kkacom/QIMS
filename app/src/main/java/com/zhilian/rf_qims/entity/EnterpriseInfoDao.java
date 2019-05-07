package com.zhilian.rf_qims.entity;

import com.zhilian.App;
import com.zhilian.rf_qims.dao.EnterpriseInfoJsonDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业名单列表（Dao）
 * Created by YiFan on 2017/4/25.
 */
public class EnterpriseInfoDao {
    /**
     * 增加数据（多个）
     * 如果有重复则覆盖
     */
    public static void insertOrReplaceInTx(List<EnterpriseInfoJson> enterpriseInfoList){
        App.getDaoInstant().getEnterpriseInfoJsonDao().insertOrReplaceInTx(enterpriseInfoList);
    }


    /**
     * 删除数据（全部）
     */
    public static void deleteAll(){
        App.getDaoInstant().getEnterpriseInfoJsonDao().deleteAll();
    }


    /**
     * 查询数据（全部）
     */
    public static List<EnterpriseInfoJson> loadAll() {
        return App.getDaoInstant().getEnterpriseInfoJsonDao().loadAll();
    }

    public static List<EnterpriseInfoJson> query(String name) {
        return App.getDaoInstant().getEnterpriseInfoJsonDao().queryBuilder().where(EnterpriseInfoJsonDao.Properties.Name.like("%"+name+"%")).list();
    }
}
