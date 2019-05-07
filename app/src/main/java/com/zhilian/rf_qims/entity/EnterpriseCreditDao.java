package com.zhilian.rf_qims.entity;

import com.zhilian.App;
import com.zhilian.rf_qims.dao.EnterpriseCreditJsonDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 诚信评估企业名单表（Dao）
 * Created by YiFan on 2017/5/10.
 */
public class EnterpriseCreditDao {
    /**
     * 增加数据（多个）
     * 如果有重复则覆盖
     */
    public static void insertOrReplaceInTx(List<EnterpriseCreditJson> enterpriseCreditList){
        App.getDaoInstant().getEnterpriseCreditJsonDao().insertOrReplaceInTx(enterpriseCreditList);
    }

    /**
     * 删除数据（全部）
     */
    public static void deleteAll(){
        App.getDaoInstant().getEnterpriseCreditJsonDao().deleteAll();
    }

    /**
     * 删除某个企业的数据
     * @param eid 企业ID
     * @param cid 考核次数
     */
    public static void delete(int eid, int cid){
        App.getDaoInstant().getEnterpriseCreditJsonDao().getDatabase()
                .execSQL("DELETE FROM ENTERPRISE_CREDIT_JSON WHERE EID = "+eid+" AND CID = "+cid);
    }

    /**
     * 更新数据（单个）
     */
    public static void update(EnterpriseCreditJson creditJson) {
        App.getDaoInstant().getEnterpriseCreditJsonDao().update(creditJson);
    }

    /**
     * 查询数据（全部）
     */
    public static List<EnterpriseCreditJson> loadAll() {
        return App.getDaoInstant().getEnterpriseCreditJsonDao().loadAll();
    }

    /**
     * 查询数据
     * @param type 企业类型
     * @return 返回相应企业类型的所有企业列表
     */
    public static List<EnterpriseCreditJson> query(int type) {
        return App.getDaoInstant().getEnterpriseCreditJsonDao().queryBuilder().where(EnterpriseCreditJsonDao.Properties.Type.eq(type)).list();
    }

    /**
     * 查询数据
     * @param eid 企业ID
     * @param cid 考核次数
     */
    public static List<EnterpriseCreditJson> query1(int eid, int cid) {
        return App.getDaoInstant().getEnterpriseCreditJsonDao().queryBuilder()
                       .where(EnterpriseCreditJsonDao.Properties.Eid.eq(eid))
                       .where(EnterpriseCreditJsonDao.Properties.Cid.eq(cid)).list();
    }

    /**
     * 查询数据
     * 查询所有状态为已考评2的企业
     */
    public static List<EnterpriseCreditJson> query2() {
        return App.getDaoInstant().getEnterpriseCreditJsonDao().queryBuilder()
                       .where(EnterpriseCreditJsonDao.Properties.Ctstatus.eq(2)).list();
    }

}
