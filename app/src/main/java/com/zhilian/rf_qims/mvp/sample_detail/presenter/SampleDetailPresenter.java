package com.zhilian.rf_qims.mvp.sample_detail.presenter;

import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.mvp.sample_detail.model.SampleDetailModel;
import com.zhilian.rf_qims.mvp.sample_detail.view.ISampleDetailView;

/**
 * Created by colin on 2018/3/12 15:53 .
 */

public class SampleDetailPresenter implements SampleDetailModel.Callback{
	private SampleDetailModel mModel;
	private ISampleDetailView mView;

	public SampleDetailPresenter(ISampleDetailView view) {
		mView = view;
		mModel = new SampleDetailModel();
	}

	public void saveSample(Sample sample) {
		mModel.saveSample(sample,this);
	}

	@Override
	public void onSaveSample(String msg	) {
		mView.onSaveSample(msg);
	}
}
