package com.zhilian.rf_qims.mvp.sample_manager.model;

import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.dao.SampleDao;
import com.zhilian.rf_qims.entity.Sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colin on 2018/2/6 11:28 .
 */

public class SampleModel implements ISampleModel {
	@Override
	public void loadLocalSamplesByPid(long id, CallBack callBack) {
		List<Sample> list = GreenDaoManager.getInstance().getNewSession().getSampleDao().queryBuilder().where(
			SampleDao.Properties.Pid.eq(id)).list();
		if (null != list && 0 < list.size()){
			callBack.onLoadCacheData(list);
		}else {
			callBack.onLoadCacheData(new ArrayList<Sample>());
		}
	}

	@Override
	public void removeSample(Sample sample, CallBack callBack) {
		GreenDaoManager.getInstance().getNewSession().getSampleDao().delete(sample);
		callBack.onRemoveSample();
	}
}
