package com.zhilian.rf_qims.entity;

import com.zhilian.App;
import com.zhilian.rf_qims.dao.WorkAbilityJsonDao;
import com.zhilian.rf_qims.dao.WorkUserJsonDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 从业能力现场评估表（Dao）
 * Created by YiFan on 2017/4/24.
 */
public class WorkAbilityDao {
    /**
     * 添加数据（单个）
     * 如果有重复则覆盖
     */
    public static void insertOrReplace(WorkAbilityJson creditJson) {
        App.getDaoInstant().getWorkAbilityJsonDao().insertOrReplace(creditJson);
    }
	/**
	 * 插入数据（单个）
	 */
	public static void insert(WorkAbilityJson creditJson) {
		App.getDaoInstant().getWorkAbilityJsonDao().insert(creditJson);
	}
    /**
     * 增加数据（多个）
     * 如果有重复则覆盖
     */
    public static void insertOrReplaceInTx(List<WorkAbilityJson> workAbilityList){
        App.getDaoInstant().getWorkAbilityJsonDao().insertOrReplaceInTx(workAbilityList);
    }

    /**
     * 选择了企业就同步项目数据（多个）
     * 如果有重复则先删除旧数据，然后再插入
     */
    public static void synInsertOrReplaceInTx(List<WorkAbilityJson> workAbilityList){
        List<WorkAbilityJson> list = new ArrayList<>();
        for (WorkAbilityJson workAbilityJson : workAbilityList){
            WorkAbilityJson workAbilityJson1 = App.getDaoInstant().getWorkAbilityJsonDao().queryBuilder().where(
                WorkAbilityJsonDao.Properties.WID.eq(workAbilityJson.getID())).unique();
            if (workAbilityJson1 != null){
                App.getDaoInstant().getWorkAbilityJsonDao().delete(workAbilityJson1);
            }
            workAbilityJson.setWID(workAbilityJson.getID());
            workAbilityJson.setID(null);
            workAbilityJson.setSTATUS(2);//同步服务器默认上传装备为2
            list.add(workAbilityJson);
        }
        App.getDaoInstant().getWorkAbilityJsonDao().insertOrReplaceInTx(list);
        /*List<WorkAbilityJson> list1 = App.getDaoInstant().getWorkAbilityJsonDao().queryBuilder().where(
            WorkAbilityJsonDao.Properties.WID.eq(list.get(0).getWID())).list();*/
    }


    /**
     * 删除数据（单个）
     */
    public static void deleteByKey(long id) {
        App.getDaoInstant().getWorkAbilityJsonDao().deleteByKey(id);
    }
    /**
     * 删除数据（全部）
     */
    public static void deleteAll(){
        App.getDaoInstant().getWorkAbilityJsonDao().deleteAll();
    }


    /**
     * 更新数据（单个）
     */
    public static void update(WorkAbilityJson workAbilityJson) {
        App.getDaoInstant().getWorkAbilityJsonDao().update(workAbilityJson);
    }


    /**
     * 查询数据（全部）
     */
    public static List<WorkAbilityJson> loadAll() {
        return App.getDaoInstant().getWorkAbilityJsonDao().loadAll();
    }

    /**
     * 查询数据
     * @param wcode 考核编号
     * @return 评估表修改页面的相关信息
     */
    public static List<WorkAbilityJson> query(String wcode) {
        return App.getDaoInstant().getWorkAbilityJsonDao().queryBuilder().where(WorkAbilityJsonDao.Properties.WCODE.eq(wcode)).list();
    }

    /**
     * 查询数据
     * @param name 企业名称
     * @return 评估表修改页面的相关信息
     */
    public static WorkAbilityJson queryName(String name) {
        return App.getDaoInstant().getWorkAbilityJsonDao().queryBuilder().where(WorkAbilityJsonDao.Properties.ENTERPRISE_NAME.eq(name)).unique();
    }

    /**
     * 查询数据
     * @param id id主键
     * @return 评估表修改页面的相关信息
     */
    public static WorkAbilityJson queryId(long id) {
        return App.getDaoInstant().getWorkAbilityJsonDao().queryBuilder().where(WorkAbilityJsonDao.Properties.ID.eq(id)).unique();
    }

    /**
     * 查询得分数据
     * @param wid 服务器id主键
     * @param eid 企业id
     * @return 评估表修改页面的相关信息
     */
    public static WorkAbilityJson queryPoint(long wid, int eid) {
        return App.getDaoInstant().getWorkAbilityJsonDao().queryBuilder()
            .where(WorkAbilityJsonDao.Properties.WID.eq(wid))
            .where(WorkAbilityJsonDao.Properties.EID.eq(eid))
            .unique();
    }

}
