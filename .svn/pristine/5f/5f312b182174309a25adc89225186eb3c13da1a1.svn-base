package com.zhilian.rf_qims.mvp.sample_manager.presenter;

import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.mvp.sample_manager.model.ISampleModel;
import com.zhilian.rf_qims.mvp.sample_manager.model.SampleModel;
import com.zhilian.rf_qims.mvp.sample_manager.view.ISampleView;

import java.util.List;

/**
 * Created by colin on 2018/2/6 10:34 .
 */

public class SamplePresenter implements ISampleModel.CallBack {
	private ISampleModel mModel;
	private ISampleView mView;

	public SamplePresenter(ISampleView view) {
		mView = view;
		mModel = new SampleModel();
	}


	public void loadLocalSamplesByPid(long id) {
		mModel.loadLocalSamplesByPid(id,this);
	}

	@Override
	public void onLoadCacheData(List<Sample> list) {
		mView.loadCacheDataSuccess(list);
	}

	@Override
	public void onRemoveSample() {
		mView.onRemoveSample();
	}

	public void removeSample(Sample sample) {
		mModel.removeSample(sample,this);
	}
}
