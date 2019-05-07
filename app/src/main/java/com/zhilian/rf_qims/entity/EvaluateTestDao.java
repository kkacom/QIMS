package com.zhilian.rf_qims.entity;

import com.zhilian.App;
import com.zhilian.rf_qims.dao.EvaluateTestJsonDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 诚信评估打分表（json）
 * Created by YiFan on 2017/5/10.
 */
public class EvaluateTestDao {
    /**
     * 增加数据（多个）
     * 如果有重复则覆盖
     */
    public static void insertOrReplaceInTx(List<EvaluateTestJson> evaluateTestList){
        App.getDaoInstant().getEvaluateTestJsonDao().insertOrReplaceInTx(evaluateTestList);
    }

    /**
     * 删除数据（全部）
     */
    public static void deleteAll(){
        App.getDaoInstant().getEvaluateTestJsonDao().deleteAll();
    }

    /**
     * 删除某个企业的数据
     * @param eid 企业ID
     * @param cid 考核次数
     */
    public static void delete(int eid, int cid){
        App.getDaoInstant().getEvaluateTestJsonDao().getDatabase()
                .execSQL("DELETE FROM EVALUATE_TEST_JSON WHERE EID = "+eid+" AND CID = "+cid);
    }

    /**
     * 更新数据（单个）
     */
    public static void update(EvaluateTestJson evaluateTestJson) {
        App.getDaoInstant().getEvaluateTestJsonDao().update(evaluateTestJson);
    }

    /**
     * 查询数据（全部）
     */
    public static List<EvaluateTestJson> loadAll() {
        return App.getDaoInstant().getEvaluateTestJsonDao().loadAll();
    }

    /**
     * 查询数据
     * @param eid 企业ID
     * @param cid 考核次数
     * @param item 条款
     */
    public static List<EvaluateTestJson> query(int eid, int cid, String item) {
        return App.getDaoInstant().getEvaluateTestJsonDao().queryBuilder()
                       .where(EvaluateTestJsonDao.Properties.Eid.eq(eid))
                       .where(EvaluateTestJsonDao.Properties.Cid.eq(cid))
                       .where(EvaluateTestJsonDao.Properties.Item.eq(item))
                       .orderAsc(EvaluateTestJsonDao.Properties.Item).list();
    }

    /**
     * 查询数据
     * @param eid 企业ID
     * @param cid 考核次数
     * @param level 条目级别
     * @param item 条款
     * @return
     */
    public static List<EvaluateTestJson> query1(int eid, int cid, int level, String item) {
        return App.getDaoInstant().getEvaluateTestJsonDao().queryBuilder()
                       .where(EvaluateTestJsonDao.Properties.Eid.eq(eid))
                       .where(EvaluateTestJsonDao.Properties.Cid.eq(cid))
                       .where(EvaluateTestJsonDao.Properties.Level.eq(level))
                       .where(EvaluateTestJsonDao.Properties.Item.like(item)).list();
    }

    /**
     * 查询数据
     * @param eid 企业ID
     * @param cid 考核次数
     * @param level 条目级别
     * @return
     */
    public static List<EvaluateTestJson> query2(int eid, int cid, int level) {
        return App.getDaoInstant().getEvaluateTestJsonDao().queryBuilder()
                       .where(EvaluateTestJsonDao.Properties.Eid.eq(eid))
                       .where(EvaluateTestJsonDao.Properties.Cid.eq(cid))
                       .where(EvaluateTestJsonDao.Properties.Level.eq(level)).list();
    }

    /**
     * 查询数据
     * @param eid 企业ID
     * @param cid 考核次数
     */
    public static List<EvaluateTestJson> query3(int eid, int cid) {
        return App.getDaoInstant().getEvaluateTestJsonDao().queryBuilder()
                       .where(EvaluateTestJsonDao.Properties.Eid.eq(eid))
                       .where(EvaluateTestJsonDao.Properties.Cid.eq(cid)).list();
    }
}
