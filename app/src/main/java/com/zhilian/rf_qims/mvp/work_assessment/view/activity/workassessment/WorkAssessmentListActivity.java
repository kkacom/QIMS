package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.dao.CompanyDao;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.entity.Company;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.workassessment.WorkAssessmentListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 从业评估（企业名单列表）
 */
public class WorkAssessmentListActivity extends AppCompatActivity {
    @BindView(R.id.work_assessment_list)
	ListView mListView;// 企业列表
    @BindView(R.id.search)
	EditText mSearch;// 搜索
    List<Company> mCompanies = new ArrayList<Company>();
    WorkAssessmentListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(R.style.ActionBarBase);
        setContentView(R.layout.activity_work_assessment_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        initView();
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

    private void initView() {
        // 从本地数据库查询所有数据
        mCompanies = GreenDaoManager.getInstance().getNewSession().getCompanyDao().loadAll();

        mAdapter = new WorkAssessmentListAdapter(this, mCompanies);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(listener1);

        // 搜索的事件监听
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCompanies = GreenDaoManager.getInstance().getNewSession().getCompanyDao().queryBuilder().where(
                    CompanyDao.Properties.Name.like(s.toString())).list();
                mAdapter = new WorkAssessmentListAdapter(WorkAssessmentListActivity.this, mCompanies);
                mListView.setAdapter(mAdapter);
                mListView.setOnItemClickListener(listener1);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * List的点击事件监听
     */
    private AdapterView.OnItemClickListener listener1 = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String name = mCompanies.get(position).getName();// 企业名称
            String number = mCompanies.get(position).getNumber();// 企业编号
            Intent intent = new Intent();
            intent.putExtra("name", name);
            intent.putExtra("number", number);
            setResult(100, intent);
            finish();
        }
    };

}
