package com.zhilian.rf_qims.mvp.work_assessment.view.activity.pfassessment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.constant.BusinessContant;
import com.zhilian.rf_qims.entity.EvaluateStandardDao;
import com.zhilian.rf_qims.entity.EvaluateStandardJson;
import com.zhilian.rf_qims.entity.EvaluateTestDao;
import com.zhilian.rf_qims.entity.EvaluateTestJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.MyViewPagerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 诚信评估（一票否决的不良行为）
 * Created by YiFan on 2017/5/20.
 */
public class SpecialEventActivity extends AppCompatActivity {
    int type;// 企业类型
    int eid;// 企业ID
    int cid;// 考核次数
    @BindView(R.id.viewpager)
	ViewPager mViewPager;
    MyViewPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.integrity_mark_layout);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor(BusinessContant.InfoTitle), true);
        ButterKnife.bind(this);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);

        type = getIntent().getIntExtra("type", 0);// 企业类型
        eid = getIntent().getIntExtra("eid", 0);// 企业ID
        cid = getIntent().getIntExtra("cid", 0);// 考核次数

        setResult(100);

        initViewPager();
        initPagerData();
        init();
        mViewPager.setCurrentItem(0);
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
     * 初始化ViewPager
     */
    private void initViewPager(){
        mPagerAdapter = new MyViewPagerAdapter();
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 配置表（对应的所有三级条款）
                final List<EvaluateStandardJson> standardList = EvaluateStandardDao.query1(3, type, "0.0%");
                // 打分表（当前页面的条款）
                final List<EvaluateTestJson> testList = EvaluateTestDao.query(eid, cid, standardList.get(position).getSn());
                // 拿到当前选择的position对应的页面和控件
                View view = mPagerAdapter.getViewList().get(position);
                final RadioButton pass = (RadioButton) view.findViewById(R.id.pass);
                RadioButton notPass = (RadioButton) view.findViewById(R.id.not_pass);
                final EditText etRemark = (EditText) view.findViewById(R.id.et_remark);

                // 通过
                pass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            EvaluateTestJson testJson = new EvaluateTestJson();
                            testJson.setId(testList.get(0).getId());// id
                            testJson.setOid(testList.get(0).getOid());
                            testJson.setCid(testList.get(0).getCid());
                            testJson.setEid(testList.get(0).getEid());
                            testJson.setItem(testList.get(0).getItem());
                            testJson.setMark(testList.get(0).getMark());
                            testJson.setWremark(testList.get(0).getWremark());
                            testJson.setUnit_name(testList.get(0).getUnit_name());
                            testJson.setType(testList.get(0).getType());
                            testJson.setAudit_mark(Float.parseFloat("100"));// 考核打分
                            testJson.setLevel(testList.get(0).getLevel());
                            testJson.setScore_type(testList.get(0).getScore_type());
                            testJson.setEremark(etRemark.getText().toString());// 备注
                            EvaluateTestDao.update(testJson);
                        }
                    }
                });

                // 不通过
                notPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            EvaluateTestJson testJson = new EvaluateTestJson();
                            testJson.setId(testList.get(0).getId());// id
                            testJson.setOid(testList.get(0).getOid());
                            testJson.setCid(testList.get(0).getCid());
                            testJson.setEid(testList.get(0).getEid());
                            testJson.setItem(testList.get(0).getItem());
                            testJson.setMark(testList.get(0).getMark());
                            testJson.setWremark(testList.get(0).getWremark());
                            testJson.setUnit_name(testList.get(0).getUnit_name());
                            testJson.setType(testList.get(0).getType());
                            testJson.setAudit_mark(Float.parseFloat("0"));// 考核打分
                            testJson.setLevel(testList.get(0).getLevel());
                            testJson.setScore_type(testList.get(0).getScore_type());
                            testJson.setEremark(etRemark.getText().toString());// 备注
                            EvaluateTestDao.update(testJson);
                        }
                    }
                });

                // 备注的文字监听
                etRemark.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String auditMark;// 考核得分
                        if(pass.isChecked()){
                            auditMark = "100";
                        }else{
                            auditMark = "0";
                        }
                        EvaluateTestJson testJson = new EvaluateTestJson();
                        testJson.setId(testList.get(0).getId());// id
                        testJson.setOid(testList.get(0).getOid());
                        testJson.setCid(testList.get(0).getCid());
                        testJson.setEid(testList.get(0).getEid());
                        testJson.setItem(testList.get(0).getItem());
                        testJson.setMark(testList.get(0).getMark());
                        testJson.setWremark(testList.get(0).getWremark());
                        testJson.setUnit_name(testList.get(0).getUnit_name());
                        testJson.setType(testList.get(0).getType());
                        testJson.setAudit_mark(Float.parseFloat(auditMark));// 考核打分
                        testJson.setLevel(testList.get(0).getLevel());
                        testJson.setScore_type(testList.get(0).getScore_type());
                        testJson.setEremark(s.toString());// 备注
                        EvaluateTestDao.update(testJson);
                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化数据
     */
    private void initPagerData(){
        List<EvaluateStandardJson> standardList = EvaluateStandardDao.query1(3, type, "0.0%");
        for(int i = 0;i < standardList.size();i++){
            List<EvaluateTestJson> testList = EvaluateTestDao.query(eid, cid, standardList.get(i).getSn());
            String scoreType;// 分值类型
            float score = testList.get(0).getAudit_mark();// 考核得分
            View view = getLayoutInflater().inflate(R.layout.special_event_layout, null);
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
            TextView tvPage = (TextView) view.findViewById(R.id.tv_page);
            TextView tvScore = (TextView) view.findViewById(R.id.tv_score);
            TextView tvScoreType = (TextView) view.findViewById(R.id.tv_score_type);
            TextView tvMemo = (TextView) view.findViewById(R.id.tv_memo);
            EditText etRemark = (EditText) view.findViewById(R.id.et_remark);
            RadioButton pass = (RadioButton) view.findViewById(R.id.pass);
            RadioButton notPass = (RadioButton) view.findViewById(R.id.not_pass);

            if(standardList.get(i).getScore_type() == 0){
                scoreType = "加分";
            }else{
                scoreType = "扣分";
            }
            if(score == 0.0){
                notPass.setChecked(true);
                System.out.println("不通过——》"+standardList.get(i).getSn());
            }else{
                pass.setChecked(true);
                System.out.println("通过——》"+standardList.get(i).getSn());
            }
            tvTitle.setText(standardList.get(i).getSn().trim() + " " + standardList.get(i).getTitle().trim());// 标题
            tvPage.setText((i+1) + "/" + standardList.size());// 页码
            tvScore.setText(standardList.get(i).getScore() + "");// 分值
            tvScoreType.setText(scoreType);// 分值类型
            tvMemo.setText(standardList.get(i).getMemo().trim());// 考核描述
            etRemark.setText(testList.get(0).getEremark());// 备注

            mPagerAdapter.addViewToAdapter(view);
            mPagerAdapter.notifyDataSetChanged();
        }
    }

    /**
     * setOnPageChangeListener()初始化时不会触发onPageSelected事件
     * 使用setCurrentItem()来触发onPageSelected事件
     * 但由于配合notifyDataSetChanged()的使用，使得setCurrentItem()无效
     * 这样就会出现，对第一条款打分和备注的数据没有保存
     * 为解决这种情况，这里暂时先用这用办法（可优化）
     */
    private void init(){
        // 配置表（对应的所有三级条款）
        final List<EvaluateStandardJson> standardList = EvaluateStandardDao.query1(3, type, "0.0%");
        // 打分表（当前页面的条款）
        final List<EvaluateTestJson> testList = EvaluateTestDao.query(eid, cid, standardList.get(0).getSn());
        // 拿到当前选择的position对应的页面和控件
        View view = mPagerAdapter.getViewList().get(0);
        final RadioButton pass = (RadioButton) view.findViewById(R.id.pass);
        RadioButton notPass = (RadioButton) view.findViewById(R.id.not_pass);
        final EditText etRemark = (EditText) view.findViewById(R.id.et_remark);

        // 通过
        pass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    EvaluateTestJson testJson = new EvaluateTestJson();
                    testJson.setId(testList.get(0).getId());// id
                    testJson.setOid(testList.get(0).getOid());
                    testJson.setCid(testList.get(0).getCid());
                    testJson.setEid(testList.get(0).getEid());
                    testJson.setItem(testList.get(0).getItem());
                    testJson.setMark(testList.get(0).getMark());
                    testJson.setWremark(testList.get(0).getWremark());
                    testJson.setUnit_name(testList.get(0).getUnit_name());
                    testJson.setType(testList.get(0).getType());
                    testJson.setAudit_mark(Float.parseFloat("100"));// 考核打分
                    testJson.setLevel(testList.get(0).getLevel());
                    testJson.setScore_type(testList.get(0).getScore_type());
                    testJson.setEremark(etRemark.getText().toString());// 备注
                    EvaluateTestDao.update(testJson);
                }
            }
        });

        // 不通过
        notPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    EvaluateTestJson testJson = new EvaluateTestJson();
                    testJson.setId(testList.get(0).getId());// id
                    testJson.setOid(testList.get(0).getOid());
                    testJson.setCid(testList.get(0).getCid());
                    testJson.setEid(testList.get(0).getEid());
                    testJson.setItem(testList.get(0).getItem());
                    testJson.setMark(testList.get(0).getMark());
                    testJson.setWremark(testList.get(0).getWremark());
                    testJson.setUnit_name(testList.get(0).getUnit_name());
                    testJson.setType(testList.get(0).getType());
                    testJson.setAudit_mark(Float.parseFloat("0"));// 考核打分
                    testJson.setLevel(testList.get(0).getLevel());
                    testJson.setScore_type(testList.get(0).getScore_type());
                    testJson.setEremark(etRemark.getText().toString());// 备注
                    EvaluateTestDao.update(testJson);
                }
            }
        });

        // 备注的文字监听
        etRemark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String auditMark;// 考核得分
                if(pass.isChecked()){
                    auditMark = "100";
                }else{
                    auditMark = "0";
                }
                EvaluateTestJson testJson = new EvaluateTestJson();
                testJson.setId(testList.get(0).getId());// id
                testJson.setOid(testList.get(0).getOid());
                testJson.setCid(testList.get(0).getCid());
                testJson.setEid(testList.get(0).getEid());
                testJson.setItem(testList.get(0).getItem());
                testJson.setMark(testList.get(0).getMark());
                testJson.setWremark(testList.get(0).getWremark());
                testJson.setUnit_name(testList.get(0).getUnit_name());
                testJson.setType(testList.get(0).getType());
                testJson.setAudit_mark(Float.parseFloat(auditMark));// 考核打分
                testJson.setLevel(testList.get(0).getLevel());
                testJson.setScore_type(testList.get(0).getScore_type());
                testJson.setEremark(s.toString());// 备注
                EvaluateTestDao.update(testJson);
            }
        });
    }
}
