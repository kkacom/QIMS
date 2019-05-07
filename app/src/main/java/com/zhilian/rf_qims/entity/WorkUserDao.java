package com.zhilian.rf_qims.entity;

import com.zhilian.App;

import java.util.ArrayList;
import java.util.List;

/**
 * 从业评估用户表（Dao）
 * Created by YiFan on 2017/4/25.
 */
public class WorkUserDao {
    /**
     * 增加数据（多个）
     * 如果有重复则覆盖
     */
    public static void insertOrReplaceInTx(List<WorkUserJson> userList){
        App.getDaoInstant().getWorkUserJsonDao().insertOrReplaceInTx(userList);
    }

    /**
     * 删除数据（全部）
     */
    public static void deleteAll(){
        App.getDaoInstant().getWorkUserJsonDao().deleteAll();
    }

    /**
     * 查询数据（全部）
     */
    public static List<WorkUserJson> loadAll() {
        return App.getDaoInstant().getWorkUserJsonDao().loadAll();
    }
}
