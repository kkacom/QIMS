package com.zhilian.rf_qims.util;

import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaTestDao;
import com.zhilian.rf_qims.entity.CaTestJson;

import java.util.Arrays;
import java.util.List;

/**
 * Created by colin on 2019/1/8 11:07 .
 */
public class Compute {
	/**先计算当前item旧的总分*/
	public static double compute(String cid, String item, double oldScore){
		oldScore = 0.00;
		List<CaTestJson> caTestJsonList = CaTestDao.query(cid, item);
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
						oldScore = DoubleUtil.add(oldScore, allScore[allScore.length - (i+1)]);
					}
				}else { 						//当创建的人数不足规定人数
					for(int i = 0; i < allScore.length; i++){
						oldScore = DoubleUtil.add(oldScore, allScore[i]);
					}
				}
			}
		}
		return oldScore;
	}
}
