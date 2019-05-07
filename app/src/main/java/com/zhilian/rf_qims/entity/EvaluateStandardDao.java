package com.zhilian.rf_qims.entity;

import com.zhilian.App;
import com.zhilian.rf_qims.dao.EvaluateStandardJsonDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 诚信评估配置表（Dao）
 * Created by YiFan on 2017/5/10.
 */
public class EvaluateStandardDao {
    /**
     * 增加数据（多个）
     * 如果有重复则覆盖
     */
    public static void insertOrReplaceInTx(List<EvaluateStandardJson> evaluateStandardList){
        App.getDaoInstant().getEvaluateStandardJsonDao().insertOrReplaceInTx(evaluateStandardList);
    }

    /**
     * 删除数据（全部）
     */
    public static void deleteAll(){
        App.getDaoInstant().getEvaluateStandardJsonDao().deleteAll();
    }

    /**
     * 查询数据
     * @param level 条目级别
     * @param type 企业类型
     * @return 返回对应企业类型对应条目级别下的模块名称
     */
    public static List<EvaluateStandardJson> query(int level, int type) {
        return App.getDaoInstant().getEvaluateStandardJsonDao().queryBuilder()
                       .where(EvaluateStandardJsonDao.Properties.Level.eq(level))
                       .where(EvaluateStandardJsonDao.Properties.Type.eq(type))
                       .orderAsc(EvaluateStandardJsonDao.Properties.Sn).list();
    }

    /**
     * 查询数据
     * @param level 条目级别
     * @param type 企业类型
     * @param sn 条款
     * @return
     */
    public static List<EvaluateStandardJson> query1(int level, int type, String sn) {
        return App.getDaoInstant().getEvaluateStandardJsonDao().queryBuilder()
                       .where(EvaluateStandardJsonDao.Properties.Level.eq(level))
                       .where(EvaluateStandardJsonDao.Properties.Type.eq(type))
                       .where(EvaluateStandardJsonDao.Properties.Sn.like(sn))
                       .orderAsc(EvaluateStandardJsonDao.Properties.Sn).list();// 升序
    }
}
