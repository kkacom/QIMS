package com.zhilian.rf_qims.entity;

import com.zhilian.App;
import com.zhilian.rf_qims.dao.CaConfigJsonDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 从业评估配置表（Dao）
 * Created by YiFan on 2017/4/25.
 */
public class CaConfigDao {
    /**
     * 增加数据（多个）
     * 如果有重复则覆盖
     */
    public static void insertOrReplaceInTx(List<CaConfigJson> caConfigList) {
        App.getDaoInstant().getCaConfigJsonDao().insertOrReplaceInTx(caConfigList);
    }

    /**
     * 删除数据（全部）
     */
    public static void deleteAll() {
        App.getDaoInstant().getCaConfigJsonDao().deleteAll();
    }

    /**
     * 查询数据
     * @param level 级别
     * @param item 条款
     * "SELECT * FROM CA_CONFIG_JSON WHERE LEVEL=1 AND SN LIKE '1%'"
     */
    public static List<CaConfigJson> query(int level, String item) {
        return App.getDaoInstant().getCaConfigJsonDao().queryBuilder().where(CaConfigJsonDao.Properties.LEVEL.eq(level)).where(CaConfigJsonDao.Properties.SN.like(item)).list();
        //return MyApplication.getDaoInstant().getCaConfigJsonDao().queryBuilder().where(new WhereCondition.StringCondition()).build();
    }

    /**
     * 查询数据
     * @param item 条款
     */
    public static List<CaConfigJson> query(String item) {
        return App.getDaoInstant().getCaConfigJsonDao().queryBuilder().where(CaConfigJsonDao.Properties.SN.eq(item)).list();
    }

    /**
     * 查询数据（算总分）
     * @param item 条款
     */
    public static List<CaConfigJson> queryLike(String item) {
        return App.getDaoInstant().getCaConfigJsonDao().queryBuilder().where(CaConfigJsonDao.Properties.SN.like(item)).list();
    }

}
