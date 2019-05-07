package com.zhilian.rf_qims.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.workassessmentmodule.TechnicalPersonnelAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 从业评估 所有描述列表的基类
 */
public abstract class BaseListActivity extends AppCompatActivity {
	@BindView(R.id.technical_personnel_list)
	ListView mListView;// 描述列表
	@BindView(R.id.module_title)
	TextView mModuleTitle;// 模块标题
	TechnicalPersonnelAdapter mAdapter;// 描述列表的适配器
	@BindView(R.id.total_score)
	TextView mTotalScore;//总得分

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_technical_personnel);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		ButterKnife.bind(this);

		initView();
	}

	/**初始化*/
	private void initView(){
		mModuleTitle.setText(moduleTitle());
		mTotalScore.setText(totalScore());
		mAdapter = new TechnicalPersonnelAdapter(this, mList(), Common.getWid());
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(listener);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * ListView的item点击事件监听
	 */
	private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			TextView item = view.findViewById(R.id.item);//获取item  2.？
			TextView description = view.findViewById(R.id.description);//获取描述
			int viewType = CaConfigDao.query(String.valueOf(item.getText())).get(0).getVIEW_TYPE(); //区分加载图形视图
			onListener(position, viewType, String.valueOf(item.getText()), String.valueOf(description.getText()));
		}
	};

	@Override
	protected void onResume() {
		super.onResume();
		initView();
	}

	/**
	 * 定义需要实现的接口
	 */
	public abstract String moduleTitle();// 模块标题

	public abstract String totalScore();// 总得分

	public abstract List<CaConfigJson> mList();// 描述列表的数据

	public abstract void onListener(int position, int viewType, String item, String description);// 描述列表的事件监听

}
