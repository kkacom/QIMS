package com.zhilian.rf_qims.mvp.work_assessment.view.activity.pfassessment;

import android.content.Intent;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.constant.BusinessContant;
import com.zhilian.rf_qims.entity.EnterpriseCreditDao;
import com.zhilian.rf_qims.entity.EnterpriseCreditJson;
import com.zhilian.rf_qims.entity.EvaluateAddDao;
import com.zhilian.rf_qims.entity.EvaluateAddJson;
import com.zhilian.rf_qims.entity.EvaluateDeductDao;
import com.zhilian.rf_qims.entity.EvaluateDeductJson;
import com.zhilian.rf_qims.entity.EvaluateStandardDao;
import com.zhilian.rf_qims.entity.EvaluateStandardJson;
import com.zhilian.rf_qims.entity.EvaluateTestDao;
import com.zhilian.rf_qims.entity.EvaluateTestJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.MyViewPagerAdapter;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.pfassessment.RecordAddAdapter;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.pfassessment.RecordDeductAdapter;
import com.zhilian.rf_qims.util.CommonUtils;
import com.zhilian.rf_qims.util.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 诚信评估（三级条款、打分）
 * Created by YiFan on 2017/5/11.
 */
public class IntegrityMarkActivity extends AppCompatActivity {
    int type;// 企业类型
    String item;// 条款
    int eid;// 企业ID
    int cid;// 考核次数
    @BindView(R.id.viewpager)
	ViewPager mViewPager;
    MyViewPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View view = CommonUtils.getView(getLayoutRes());
        setContentView(R.layout.integrity_mark_layout);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor(BusinessContant.InfoTitle), true);
        ButterKnife.bind(this);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);

        type = getIntent().getIntExtra("type", 0);// 企业类型
        item = getIntent().getStringExtra("item");// 条款
        eid = getIntent().getIntExtra("eid", 0);// 企业ID
        cid = getIntent().getIntExtra("cid", 0);// 考核次数

        Intent intent = new Intent();
        intent.putExtra("item", item);
        setResult(100, intent);

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
            public void onPageSelected(final int position) {
                // 配置表（二级条款下对应的所有三级条款）
                final List<EvaluateStandardJson> standardList = EvaluateStandardDao.query1(3, type, item + "%");
                // 打分表（当前页面的条款）
                final List<EvaluateTestJson> testList = EvaluateTestDao.query(eid, cid, standardList.get(position).getSn());
                // 拿到当前选择的position对应的页面和控件
                final View view = mPagerAdapter.getViewList().get(position);
                final EditText etAuditMark = (EditText) view.findViewById(R.id.et_audit_mark);
                final EditText etRemark = (EditText) view.findViewById(R.id.et_remark);

                // 判断该企业是否上传，上传的不能再进行打分编辑
                List<EnterpriseCreditJson> creditList = EnterpriseCreditDao.query1(eid, cid);
                if(creditList.get(0).getStatus() == 0){
                    // 考核得分的文字监听
                    etAuditMark.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            // 输入类型的判断，和重置输入值
                            float score = standardList.get(position).getScore();// 分值
                            if(!s.toString().isEmpty()){// 1 输入值不能为空
                                if(start == 0 && s.toString().equals(".")){// 2 输入类型无误
                                    etAuditMark.setText("0.0");
                                    ToastUtils.show("输入类型错误");
                                }else if(Float.parseFloat(s.toString()) > score){// 3 打分不大于分值
                                    etAuditMark.setText("0.0");
                                    ToastUtils.show("打分不能大于分值");
                                }
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            float score = standardList.get(position).getScore();// 分值
                            float mark = 0;// 打分
                            if(!s.toString().isEmpty()){// 输入值不能为空
                                if(!s.toString().equals(".")){// 类型不能有误
                                    if(Float.parseFloat(s.toString()) > score){// 且必须小于等于分值
                                        mark = Float.parseFloat("0");
                                    }else{
                                        mark = Float.parseFloat(s.toString());
                                    }
                                }else{
                                    mark = Float.parseFloat("0");
                                }
                            }else{
                                mark = Float.parseFloat("0");
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
                            testJson.setAudit_mark(CommonUtils.floatTwo(mark));// 考核打分
                            testJson.setLevel(testList.get(0).getLevel());
                            testJson.setScore_type(testList.get(0).getScore_type());
                            testJson.setEremark(etRemark.getText().toString());// 备注
                            EvaluateTestDao.update(testJson);
                            //mPagerAdapter.notifyDataSetChanged();
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
                            testJson.setAudit_mark(Float.parseFloat(etAuditMark.getText().toString()));// 考核打分
                            testJson.setLevel(testList.get(0).getLevel());
                            testJson.setScore_type(testList.get(0).getScore_type());
                            testJson.setEremark(s.toString());// 备注
                            EvaluateTestDao.update(testJson);
                        }
                    });
                }else{
                    etAuditMark.setEnabled(false);
                    etRemark.setEnabled(false);
                }

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
        // 配置表（二级条款下对应的所有三级条款）
        List<EvaluateStandardJson> standardList = EvaluateStandardDao.query1(3, type, item + "%");
        // 循环动态加载所有二级条款对应下的所有三级条款的页面、控件和数据
        for(int i = 0;i < standardList.size();i++){
            // 打分表（当前页面的条款）
            List<EvaluateTestJson> testList = EvaluateTestDao.query(eid, cid, standardList.get(i).getSn());
            // 加分表（当前条款的良好行为记录）
            List<EvaluateAddJson> addList = EvaluateAddDao.query(standardList.get(i).getSn(), eid);
            // 减分表（当前条款的不良行为记录）
            List<EvaluateDeductJson> deductList = EvaluateDeductDao.query(standardList.get(i).getSn(), eid);
            String scoreType;// 分值类型

            View view = getLayoutInflater().inflate(R.layout.integrity_mark_item, null);
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
            TextView tvPage = (TextView) view.findViewById(R.id.tv_page);
            TextView tvScore = (TextView) view.findViewById(R.id.tv_score);
            TextView tvScoreType = (TextView) view.findViewById(R.id.tv_score_type);
            TextView tvMemo = (TextView) view.findViewById(R.id.tv_memo);
            TextView tvMark = (TextView) view.findViewById(R.id.tv_mark);
            EditText etAuditMark = (EditText) view.findViewById(R.id.et_audit_mark);
            EditText etRemark = (EditText) view.findViewById(R.id.et_remark);
            ListView list = (ListView) view.findViewById(R.id.lv_record);// 记录列表
            TextView tvRecord = (TextView) view.findViewById(R.id.tv_record);// 记录栏目
            LinearLayout tvNot = (LinearLayout) view.findViewById(R.id.tv_not);// 无

            // 根据分值类型，区分良好行为记录和不良行为记录（0 加分 1 减分）
            if(standardList.get(i).getScore_type() == 0){// 加分（良好行为记录）
                scoreType = "加分";
                tvRecord.setText("良好行为记录");
                if(addList.size() == 0){
                    tvNot.setVisibility(View.GONE);
                }else{
                    tvNot.setVisibility(View.VISIBLE);
                }
                list.setAdapter(new RecordAddAdapter(this, addList));
            }else{// 扣分（不良行为记录）
                scoreType = "扣分";
                tvRecord.setText("不良行为记录");
                if(deductList.size() == 0){
                    tvNot.setVisibility(View.GONE);
                }else{
                    tvNot.setVisibility(View.VISIBLE);
                }
                list.setAdapter(new RecordDeductAdapter(this, deductList));
            }
            tvTitle.setText(standardList.get(i).getSn().trim() + " " + standardList.get(i).getTitle().trim());// 标题
            tvScore.setText(standardList.get(i).getScore() + "");// 分值
            tvPage.setText((i+1) + "/" + standardList.size());// 页码
            tvScoreType.setText(scoreType);// 分值类型
            tvMemo.setText(standardList.get(i).getMemo().trim());// 考核描述
            tvMark.setText(testList.get(0).getMark() + "");// 企业日常得分
            etAuditMark.setText(testList.get(0).getAudit_mark() + "");// 考核得分
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
        // 配置表（二级条款下对应的所有三级条款）
        final List<EvaluateStandardJson> standardList = EvaluateStandardDao.query1(3, type, item + "%");
        // 打分表（当前页面的条款）
        final List<EvaluateTestJson> testList = EvaluateTestDao.query(eid, cid, standardList.get(0).getSn());// 打分表
        // 拿到当前选择的position对应的页面和控件
        final View view = mPagerAdapter.getViewList().get(0);
        final EditText etAuditMark = (EditText) view.findViewById(R.id.et_audit_mark);
        final EditText etRemark = (EditText) view.findViewById(R.id.et_remark);

        // 判断该企业是否上传，上传的不能再进行打分编辑
        List<EnterpriseCreditJson> creditList = EnterpriseCreditDao.query1(eid, cid);
        if(creditList.get(0).getStatus() == 0){
            // 考核得分的文字监听
            etAuditMark.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // 输入类型的判断，和重置输入值
                    float score = standardList.get(0).getScore();// 分值
                    if(!s.toString().isEmpty()){// 1 输入值不能为空
                        if(start == 0 && s.toString().equals(".")){// 2 输入类型无误
                            etAuditMark.setText("0.0");
                            ToastUtils.show("输入类型错误");
                        }else if(Float.parseFloat(s.toString()) > score){// 3 打分不大于分值
                            etAuditMark.setText("0.0");
                            ToastUtils.show("打分不能大于分值");
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    float score = standardList.get(0).getScore();// 分值
                    float mark = 0;// 打分
                    if(!s.toString().isEmpty()){// 输入值不能为空
                        if(!s.toString().equals(".")){// 类型不能有误
                            if(Float.parseFloat(s.toString()) > score){// 且必须小于等于分值
                                mark = Float.parseFloat("0");
                            }else{
                                mark = Float.parseFloat(s.toString());
                            }
                        }else{
                            mark = Float.parseFloat("0");
                        }
                    }else{
                        mark = Float.parseFloat("0");
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
                    testJson.setAudit_mark(CommonUtils.floatTwo(mark));// 考核打分
                    testJson.setLevel(testList.get(0).getLevel());
                    testJson.setScore_type(testList.get(0).getScore_type());
                    testJson.setEremark(etRemark.getText().toString());// 备注
                    EvaluateTestDao.update(testJson);
                    //mPagerAdapter.notifyDataSetChanged();
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
                    testJson.setAudit_mark(Float.parseFloat(etAuditMark.getText().toString()));// 考核打分
                    testJson.setLevel(testList.get(0).getLevel());
                    testJson.setScore_type(testList.get(0).getScore_type());
                    testJson.setEremark(s.toString());// 备注
                    EvaluateTestDao.update(testJson);
                }
            });
        }else{
            // 已上传，不可对打分数据进行编辑
            etAuditMark.setEnabled(false);
            etRemark.setEnabled(false);
            // TODO 可优化，把输入框样式改掉，或者给相应的提示
        }

    }

}
