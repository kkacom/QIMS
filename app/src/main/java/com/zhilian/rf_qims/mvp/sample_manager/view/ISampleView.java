package com.zhilian.rf_qims.mvp.sample_manager.view;

import com.zhilian.rf_qims.entity.Sample;

import java.util.List;

/**
 * Created by colin on 2018/2/6 10:34 .
 */

public interface ISampleView {
	void loadCacheDataSuccess(List<Sample> list);

	void onRemoveSample();
}
