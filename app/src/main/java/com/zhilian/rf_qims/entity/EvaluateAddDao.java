package com.zhilian.rf_qims.entity;

import com.zhilian.App;
import com.zhilian.rf_qims.dao.EvaluateAddJsonDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 诚信评估加分表（Dao）
 * Created by YiFan on 2017/5/10.
 */
public class EvaluateAddDao {
    /**
     * 增加数据（多个）
     * 如果有重复则覆盖
     */
    public static void insertOrReplaceInTx(List<EvaluateAddJson> evaluateAddList){
        App.getDaoInstant().getEvaluateAddJsonDao().insertOrReplaceInTx(evaluateAddList);
    }

    /**
     * 删除数据（全部）
     */
    public static void deleteAll(){
        App.getDaoInstant().getEvaluateAddJsonDao().deleteAll();
    }

    /**
     * 查询数据
     * @param item 条款
     * @param eid 企业ID
     */
    public static List<EvaluateAddJson> query(String item, int eid) {// 待改，这样查询估计不准确
        return App.getDaoInstant().getEvaluateAddJsonDao().queryBuilder()
                       .where(EvaluateAddJsonDao.Properties.Item.eq(item))
                       .where(EvaluateAddJsonDao.Properties.Eid.eq(eid)).list();
    }
}
