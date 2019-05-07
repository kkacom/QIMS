package com.zhilian.rf_qims.entity;

import com.zhilian.App;
import com.zhilian.rf_qims.dao.IntegrityUserJsonDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 诚信评估用户表（Dao）
 * Created by YiFan on 2017/5/24.
 */
public class IntegrityUserDao {
    /**
     * 添加数据（单个）
     * 如果有重复则覆盖
     */
    public static void insertOrReplace(IntegrityUserJson userJson) {
        App.getDaoInstant().getIntegrityUserJsonDao().insertOrReplace(userJson);
    }

    /**
     * 增加数据（多个）
     * 如果有重复则覆盖
     */
    public static void insertOrReplaceInTx(List<IntegrityUserJson> userList){
        App.getDaoInstant().getIntegrityUserJsonDao().insertOrReplaceInTx(userList);
    }

    /**
     * 删除数据（全部）
     */
    public static void deleteAll(){
        App.getDaoInstant().getIntegrityUserJsonDao().deleteAll();
    }

    /**
     * 查询数据（全部）
     */
    public static List<IntegrityUserJson> loadAll() {
        return App.getDaoInstant().getIntegrityUserJsonDao().loadAll();
    }

    /**
     * 查询数据
     * @param dlid 登录ID（用户名）
     */
    public static List<IntegrityUserJson> query(String dlid) {
        return App.getDaoInstant().getIntegrityUserJsonDao().queryBuilder().where(IntegrityUserJsonDao.Properties.Dlid.eq(dlid)).list();
    }

    /**
     * 查询数据
     * @param name 后台返回的姓名
     */
    public static List<IntegrityUserJson> queryName(String name) {
        return App.getDaoInstant().getIntegrityUserJsonDao().queryBuilder().where(IntegrityUserJsonDao.Properties.Name.eq(name)).list();
    }
}
