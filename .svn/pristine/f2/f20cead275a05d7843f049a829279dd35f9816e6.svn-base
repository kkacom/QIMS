package com.zhilian.rf_qims.mvp.sample_detail.model;

import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.dao.SampleDao;
import com.zhilian.rf_qims.entity.Sample;

import java.util.List;

/**
 * Created by colin on 2018/3/12 15:53 .
 */

public class SampleDetailModel {
	public void saveSample(Sample sample, Callback callback) {
		SampleDao dao = GreenDaoManager.getInstance().getNewSession().getSampleDao();
		List<Sample> list = dao.queryBuilder().where(SampleDao.Properties.SampleNo.eq(sample.getSampleNo())).list();
		if (list.isEmpty()) {
			dao.insert(sample);
			callback.onSaveSample("保存数据成功！");
		} else {
			dao.update(sample);
			callback.onSaveSample("更新数据成功！");
		}
	}

	public interface Callback {

		void onSaveSample(String msg);
	}
}
