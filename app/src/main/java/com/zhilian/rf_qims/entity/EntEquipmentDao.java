package com.zhilian.rf_qims.entity;

import com.zhilian.App;
import com.zhilian.rf_qims.dao.EntEquipmentJsonDao;

import java.util.List;

/**
 * 从业能力设备表（Dao）
 * Created by YiFan on 2017/4/25.
 */
public class EntEquipmentDao {
    /**
     * 增加数据（多个）
     * 如果有重复则覆盖
     */
    public static void insertOrReplaceInTx(List<EntEquipmentJson> entEquipmentList){
        App.getDaoInstant().getEntEquipmentJsonDao().insertOrReplaceInTx(entEquipmentList);
    }

    /**
     * 增加数据（多个）
     * 如果有重复则覆盖
     */
    public static void insertOrReplace(EntEquipmentJson entEquipment){
        App.getDaoInstant().getEntEquipmentJsonDao().insertOrReplace(entEquipment);
    }

    /**
     * 删除数据（全部）
     */
    public static void deleteAll(){
        App.getDaoInstant().getEntEquipmentJsonDao().deleteAll();
    }

    /**
     * 更新（一条）
     */
    public static void update(EntEquipmentJson entEquipmentJson){
        App.getDaoInstant().getEntEquipmentJsonDao().update(entEquipmentJson);
    }

    /**
     * 更新（List）
     */
    public static void updateList(List<EntEquipmentJson> list){
        App.getDaoInstant().getEntEquipmentJsonDao().updateInTx(list);
    }

    /**
     * 查询（多台设备）
     */
    public static List<EntEquipmentJson> query(String eid, String cid, String item){
        return App.getDaoInstant().getEntEquipmentJsonDao().queryBuilder().
            where(EntEquipmentJsonDao.Properties.EID.eq(eid)).
            where(EntEquipmentJsonDao.Properties.CID.eq(cid)).
            where(EntEquipmentJsonDao.Properties.ETYPE.eq(item)).list();
    }

    /**
     * 查询（判断是否有此设备）
     */
    public static List<EntEquipmentJson> queryJudgeEquipment(String cid, String item, String code){
        return App.getDaoInstant().getEntEquipmentJsonDao().queryBuilder().
            where(EntEquipmentJsonDao.Properties.CID.eq(cid)).
            where(EntEquipmentJsonDao.Properties.ETYPE.eq(item)).
            where(EntEquipmentJsonDao.Properties.ECODE.eq(code)).list();
    }
}
