package com.zhilian.rf_qims.mvp.sample_manager.model;

import com.zhilian.rf_qims.entity.Sample;

import java.util.List;

/**
 * Created by colin on 2018/2/6 10:33 .
 */

public interface ISampleModel {


	void loadLocalSamplesByPid(long id, CallBack callBack);

	void removeSample(Sample sample, CallBack callBack);

	interface CallBack{
		void onLoadCacheData(List<Sample> list);

		void onRemoveSample();
	}
}