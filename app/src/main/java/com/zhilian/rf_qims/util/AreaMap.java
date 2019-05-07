package com.zhilian.rf_qims.util;

import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.entity.AreaMes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by colin on 2018/11/16 9:47 .
 */
public class AreaMap {
	private static Map<Long,String> map = new HashMap<>();
	public AreaMap(){
		List<AreaMes> list = GreenDaoManager.getInstance().getNewSession().getAreaMesDao().loadAll();
		for (AreaMes areaMes : list){
			map.put(areaMes.getId(),areaMes.getName());
		}
	}

	public static Map<Long, String> getMap() {
		return map;
	}
}
