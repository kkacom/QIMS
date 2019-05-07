package com.zhilian.rf_qims.entity;

import com.zhilian.App;
import com.zhilian.rf_qims.dao.EvaluateDeductJsonDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 诚信评估扣分表（Dao）
 * Created by YiFan on 2017/5/10.
 */
public class EvaluateDeductDao {
    /**
     * 增加数据（多个）
     * 如果有重复则覆盖
     */
    public static void insertOrReplaceInTx(List<EvaluateDeductJson> evaluateDeductList){
        App.getDaoInstant().getEvaluateDeductJsonDao().insertOrReplaceInTx(evaluateDeductList);
    }

    /**
     * 删除数据（全部）
     */
    public static void deleteAll(){
        App.getDaoInstant().getEvaluateDeductJsonDao().deleteAll();
    }

    /**
     * 查询数据
     * @param item 条款
     * @param eid 企业ID
     */
    public static List<EvaluateDeductJson> query(String item, int eid) {// 待改，这样查询估计不准确
        return App.getDaoInstant().getEvaluateDeductJsonDao().queryBuilder()
                       .where(EvaluateDeductJsonDao.Properties.Item.eq(item))
                       .where(EvaluateDeductJsonDao.Properties.Eid.eq(eid)).list();
    }
}
