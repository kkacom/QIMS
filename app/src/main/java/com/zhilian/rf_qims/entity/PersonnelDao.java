package com.zhilian.rf_qims.entity;

import com.zhilian.App;
import com.zhilian.rf_qims.dao.PersonnelJsonDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 从业评估人员表（Dao）
 * Created by LiYan on 2017/4/25.
 */
public class PersonnelDao {
    /**
     * 增加数据（单个）
     * 如果有重复则覆盖
     */
    public static void insertOrReplace(PersonnelJson personnelJson){
        App.getDaoInstant().getPersonnelJsonDao().insertOrReplace(personnelJson);
    }
    /**
     * 增加数据（多个）
     * 如果有重复则覆盖
     */
    public static void insertOrReplaceInTx(List<PersonnelJson> personnelList){
        App.getDaoInstant().getPersonnelJsonDao().insertOrReplaceInTx(personnelList);
    }

    /**
     * 删除数据（全部）
     */
    public static void deleteAll(){
        App.getDaoInstant().getPersonnelJsonDao().deleteAll();
    }

    /**
     * 更新数据（单个）
     */
    public static void update(PersonnelJson personnelJson) {
        App.getDaoInstant().getPersonnelJsonDao().update(personnelJson);
    }

    /**
     * 更新数据（单个）
     */
    public static void updateList(List<PersonnelJson> list) {
        App.getDaoInstant().getPersonnelJsonDao().updateInTx(list);
    }

    /**
     * 查询数据(创建人员时判断是否存在数据)
     * @param cid 企业记录ID
     * @param item 条款
     * @param card 身份证
     */
    public static List<PersonnelJson> queryJudgePerson(int cid, String item, String card) {
        return App.getDaoInstant().getPersonnelJsonDao().queryBuilder()
            .where(PersonnelJsonDao.Properties.CID.eq(cid))
            .where(PersonnelJsonDao.Properties.POST_TYPE.eq(item))
            .where(PersonnelJsonDao.Properties.IDNUMBER.eq(card)).list();
    }

    /**
     * 查询数据
     * @param eid 企业ID
     * @param cid 企业记录ID
     * @param item 条款
     */
    public static List<PersonnelJson> query(String eid, String cid, String item) {
        return App.getDaoInstant().getPersonnelJsonDao().queryBuilder()
            .where(PersonnelJsonDao.Properties.EID.eq(eid))
            .where(PersonnelJsonDao.Properties.CID.eq(cid))
            .where(PersonnelJsonDao.Properties.POST_TYPE.eq(item)).list();
    }

    /**
     * 查询数据(算总分)
     * @param eid 企业ID
     * @param cid 企业记录ID
     * @param item 条款
     */
    public static List<PersonnelJson> queryLike(String eid, String cid, String item) {
        return App.getDaoInstant().getPersonnelJsonDao().queryBuilder().where(PersonnelJsonDao.Properties.EID.eq(eid)).where(PersonnelJsonDao.Properties.CID.eq(cid)).where(PersonnelJsonDao.Properties.POST_TYPE.like(item)).list();
    }
}
