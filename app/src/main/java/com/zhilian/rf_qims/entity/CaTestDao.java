package com.zhilian.rf_qims.entity;

import com.zhilian.App;
import com.zhilian.rf_qims.dao.CaTestJsonDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 从业评估打分表（Dao）
 * Created by YiFan on 2017/4/25.
 */
public class CaTestDao {
    /**
     * 增加数据（单个）
     * 如果有重复则覆盖
     */
    public static void insertOrReplace(CaTestJson caTestJson){
        App.getDaoInstant().getCaTestJsonDao().insertOrReplace(caTestJson);
    }

    /**
     * 增加数据（多个）
     * 如果有重复则覆盖
     */
    public static void insertOrReplaceInTx(List<CaTestJson> caTestList){
        App.getDaoInstant().getCaTestJsonDao().insertOrReplaceInTx(caTestList);
    }

    /**
     * 删除数据（全部）
     */
    public static void deleteAll(){
        App.getDaoInstant().getCaTestJsonDao().deleteAll();
    }

    /**
     * 查询数据一条
     * @param cid 企业记录ID
     * @param item 条款
     */
    public static CaTestJson queryOne(String cid, String item) {
        return App.getDaoInstant().getCaTestJsonDao().queryBuilder().where(CaTestJsonDao.Properties.CID.eq(cid)).where(CaTestJsonDao.Properties.ITEM.eq(item)).unique();
    }

    /**
     * 查询数据
     * @param cid 企业记录ID
     * @param item 条款
     */
    public static List<CaTestJson> query(String cid, String item) {
        return App.getDaoInstant().getCaTestJsonDao().queryBuilder().where(CaTestJsonDao.Properties.CID.eq(cid)).where(CaTestJsonDao.Properties.ITEM.eq(item)).list();
    }

    /**
     * item模糊查询数据
     * @param cid 企业记录ID
     * @param item 条款
     */
    public static List<CaTestJson> queryLike(String cid, String item) {
        return App.getDaoInstant().getCaTestJsonDao().queryBuilder().where(CaTestJsonDao.Properties.CID.eq(cid)).where(CaTestJsonDao.Properties.ITEM.like(item)).list();
    }

    /**
     * 查询数据
     * @param cid 企业记录ID
     * @param status 上传状态
     */
    public static List<CaTestJson> queryCidAll(String cid, String status) {
        return App.getDaoInstant().getCaTestJsonDao().queryBuilder().where(CaTestJsonDao.Properties.CID.eq(cid)).where(CaTestJsonDao.Properties.STATUS.eq(status)).list();
    }

    /**
     * 更新数据
     * @param cid 企业记录ID
     * @param list 其他打分项数据
     */
    public static void update(String cid, List<CaTestJson> list) {
        List<CaTestJson> caTestJsonList = App.getDaoInstant().getCaTestJsonDao().queryBuilder().
            where(CaTestJsonDao.Properties.CID.eq(cid)).list();
        for (CaTestJson caTestJson : caTestJsonList){
            App.getDaoInstant().getCaTestJsonDao().deleteByKey(caTestJson.getID());
        }
        List<CaTestJson> list1 = new ArrayList<>();
        CaTestJson caTestJson;
        for (int i = 0; i < list.size() - 1; i++){
            caTestJson = new CaTestJson();
            caTestJson.setCTID(caTestJson.getID());//主id存到服务器返回ID里
            caTestJson.setUPLOADSTATUS(2); //同步下来默认状态是2上传过，修改过数据改成1，代表服务器有数据不插入为更新
            caTestJson.setCID(caTestJson.getCID());
            caTestJson.setITEM(caTestJson.getITEM());
            caTestJson.setSCORE(caTestJson.getSCORE());
            caTestJson.setCHOOSE(caTestJson.getCHOOSE());
            caTestJson.setREMARK(caTestJson.getREMARK());
            caTestJson.setSTATUS(caTestJson.getSTATUS());
            caTestJson.setFIELD1(caTestJson.getFIELD1());
            caTestJson.setFIELD2(caTestJson.getFIELD2());
            caTestJson.setFIELD3(caTestJson.getFIELD3());
            caTestJson.setWREMARK(caTestJson.getWREMARK());
            list1.add(caTestJson);
        }
        App.getDaoInstant().getCaTestJsonDao().insertInTx(list1);
    }

    /**
     * 更新数据
     * @param list 其他打分项数据
     */
    public static void updateList(List<CaTestJson> list) {
        App.getDaoInstant().getCaTestJsonDao().updateInTx(list);
    }

}
