package com.zhilian.rf_qims.util;

import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaTestDao;
import com.zhilian.rf_qims.entity.CaTestJson;
import com.zhilian.rf_qims.entity.WorkAbilityDao;
import com.zhilian.rf_qims.entity.WorkAbilityJson;

import java.util.Arrays;
import java.util.List;

/**
 * 算法管理类
 * Created by YiFan on 2017/5/8.
 */
public class ScoreUtil {
    /**
     * 零分
     */
    public static String SCORE_NOT = "0.00";

    /**
     * 个人得分（不扣）
     */
    public static String scoreAll(String str){
        double score = Double.parseDouble(str);
        String s = String.format("%.2f", score);
        return s;
    }

    /**
     * 个人得分（扣一半分）
     */
    public static String scoreHalf(String str){
        double score = Double.parseDouble(str);
        String s = String.format("%.2f", score*0.5);
        return s;
    }

    /**
     * 个人得分（扣成负数则为0）
     */
    public static String scoreNegative(String str, double value){
        double score = Double.parseDouble(str);
        double result = score - value;
        String s = String.format("%.2f", result > 0 ? result : 0.00);
        return s;
    }

    /**
     * 算总分
     * @param each 单计
     * @param number 数量
     */
    public static String scoreTotal(String each, int number){

        String s = String.format("%.2f", 0);
        return s;
    }

    /**
     * 算总分
     * @param workAbilityJson 项目实体类
     * @param status 状态
     * @param cid wid列，服务器项目主id
     * @param item 菜单项
     * @param oldScore 未修改数据前分数
     * */
    public static void total(WorkAbilityJson workAbilityJson, String status, String cid, String item, double oldScore, int eid) {
        String itemLike = item;
        String itemTailor = item.substring(0, 3);
        List<CaTestJson> caTestJsonList = CaTestDao.query(cid, itemLike);
        double total = 0.00;
        double[] allScore = new double[caTestJsonList.size()];
        for (int i = 0; i < caTestJsonList.size(); i++) {
            if (caTestJsonList.get(i).getSCORE() != null){
                allScore[i] = caTestJsonList.get(i).getSCORE().equals("") ? 0.00 : Double.valueOf(caTestJsonList.get(i).getSCORE());
            }else {
                allScore[i] = 0.00;
            }
        }
        if (allScore != null){
            if (allScore.length > 0){
                Arrays.sort(allScore); //升序排序
                int number = CaConfigDao.query(item).get(0).getNUMBER(); //规定人数
                if (allScore.length >= number && number != 0){ //当创建的人数大于规定人数
                    for(int i = 0; i < number; i++){
                        total = DoubleUtil.add(total, allScore[allScore.length - (i+1)]);
                    }
                }else { 						//当创建的人数不足规定人数
                    for(int i = 0; i < allScore.length; i++){
                        total = DoubleUtil.add(total, allScore[i]);
                    }
                }
            }
        }
        CaTestJson caTestJson = CaTestDao.queryOne(cid, itemTailor);
        if (caTestJson == null) {
            caTestJson = new CaTestJson(cid, itemTailor, String.valueOf(total), "", "", status, 0);
        } else {
            caTestJson.setSCORE(String.valueOf(DoubleUtil.add(DoubleUtil.sub(Double.valueOf(caTestJson.getSCORE()),oldScore), total)));
            caTestJson.setSTATUS(status);
        }
        CaTestDao.insertOrReplace(caTestJson);
        //-------------------------------------------------------------------------------------------下面存item2
        itemLike = item.substring(0, 2);//取item的前2获取所有2.的人，如2.1.11 = 2.
        itemTailor = item.substring(0, 1);
        caTestJsonList = CaTestDao.queryLike(cid, itemLike+"%");
        double total2 = 0.00;
        for (CaTestJson caTestJson3 : caTestJsonList) {
            if (caTestJson3.getSCORE() != null && !caTestJson3.getSCORE().equals("")){
                if (caTestJson3.getITEM().length() == 3){ //只计算item为x.x这样的
                    total2 = DoubleUtil.add(total2, Double.valueOf(caTestJson3.getSCORE()));
                }
            }
        }
        CaTestJson caTestJson2 = CaTestDao.queryOne(cid, itemTailor);
        if (caTestJson2 == null) {
            caTestJson2 = new CaTestJson(cid, itemTailor, String.valueOf(total2), "", "", status, 0);
        } else {
            caTestJson2.setSCORE(String.valueOf(total2));
            caTestJson2.setSTATUS(status);
        }

        CaTestDao.insertOrReplace(caTestJson2);

        if (status.equals("1")) {
            workAbilityJson.setSTATUS(1);
        }

        itemTailor = item.substring(0, 3);
        if (itemTailor.equals("2.1")){
            workAbilityJson.setITEM2_1(String.valueOf(caTestJson.getSCORE()));
        }else if (itemTailor.equals("2.2")){
            workAbilityJson.setITEM2_2(String.valueOf(caTestJson.getSCORE()));
        }else if (itemTailor.equals("2.3")){
            workAbilityJson.setITEM2_3(String.valueOf(caTestJson.getSCORE()));
        }else if (itemTailor.equals("2.4")){
            workAbilityJson.setITEM2_4(String.valueOf(caTestJson.getSCORE()));
        }else if (itemTailor.equals("2.5")){
            workAbilityJson.setITEM2_5(String.valueOf(caTestJson.getSCORE()));
        }else if (itemTailor.equals("2.6")){
            workAbilityJson.setITEM2_6(String.valueOf(caTestJson.getSCORE()));
        }else if (itemTailor.equals("2.7")){
            workAbilityJson.setITEM2_7(String.valueOf(caTestJson.getSCORE()));
        }else if (itemTailor.equals("2.8")){
            workAbilityJson.setITEM2_8(String.valueOf(caTestJson.getSCORE()));
        }else if (itemTailor.equals("3.1")){
            workAbilityJson.setITEM3_1(String.valueOf(caTestJson.getSCORE()));
        }else if (itemTailor.equals("3.2")){
            workAbilityJson.setITEM3_2(String.valueOf(caTestJson.getSCORE()));
        }else if (itemTailor.equals("3.3")){
            workAbilityJson.setITEM3_3(String.valueOf(caTestJson.getSCORE()));
        }

        if (item.substring(0,1).equals("2")){
            workAbilityJson.setITEM2(String.valueOf(total2));
        }else {
            workAbilityJson.setITEM3(String.valueOf(total2));
        }
        WorkAbilityDao.insertOrReplace(workAbilityJson);

        //所有总分(WorkAbilityJson表的item2 + item3)
        WorkAbilityJson workAbilityJson1 = WorkAbilityDao.queryPoint(Long.parseLong(cid), eid);
        double item2 = 0.0, item3;
        if (workAbilityJson1.getITEM2() != null && !workAbilityJson1.getITEM2().equals("")){
            item2 = Double.parseDouble(workAbilityJson1.getITEM2());
            workAbilityJson1.setTOTAL(String.valueOf(item2));
            WorkAbilityDao.update(workAbilityJson);
        }
        if (workAbilityJson1.getITEM3() != null && !workAbilityJson1.getITEM3().equals("")){
            item3 = Double.parseDouble(workAbilityJson1.getITEM3());
            workAbilityJson1.setTOTAL(String.valueOf(DoubleUtil.add(item2, item3)));
            WorkAbilityDao.update(workAbilityJson);
        }
    }
}
