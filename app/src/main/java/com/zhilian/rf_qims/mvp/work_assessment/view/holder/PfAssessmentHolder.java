package com.zhilian.rf_qims.mvp.work_assessment.view.holder;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.entity.EnterpriseCreditDao;
import com.zhilian.rf_qims.entity.EnterpriseCreditJson;
import com.zhilian.rf_qims.entity.EvaluateTestDao;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.pfassessment.IntegrityModuleActivity;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.BaseHolder;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.MyBaseAdapter;
import com.zhilian.rf_qims.util.CommonUtils;
import com.zhilian.rf_qims.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 企业名单Holder
 * Created by luocong on 2017/3/30.
 */
public class PfAssessmentHolder extends BaseHolder<EnterpriseCreditJson> {
    @BindView(R.id.tv_position)
	TextView mTvPosition;// 条目
    @BindView(R.id.tv_name)
	TextView mTvName;// 企业名称
    @BindView(R.id.tv_total_score)
	TextView mTvTotalScore;// 总分
    @BindView(R.id.tv_year)
	TextView mTvYear;// 年份
    @BindView(R.id.tv_date)
	TextView mTvDate;// 企业申报时间
    @BindView(R.id.tv_status)
	TextView mTvStatus;// 状态

    public PfAssessmentHolder(Activity mActivity, Context context, ViewGroup parent, MyBaseAdapter<EnterpriseCreditJson> adapter, int position, EnterpriseCreditJson bean) {
        super(mActivity, context, parent, adapter, position, bean);
    }

    @Override
    public View onCreateView(final Context context, ViewGroup parent, int position, final EnterpriseCreditJson bean) {
        View pfView = CommonUtils.getView(R.layout.pf_assess_list);
        ButterKnife.bind(this, pfView);
        // ListView的点击事件
        pfView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, IntegrityModuleActivity.class);
                intent.putExtra("type", String.valueOf(bean.getType()));// 企业类型
                intent.putExtra("eid", bean.getEid());// 企业ID
                Log.d("eid", bean.getEid() + "");
                intent.putExtra("cid", bean.getCid());// 考核次数
                context.startActivity(intent);
            }
        });
        // ListView的长按事件
        pfView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View view = CommonUtils.getView(R.layout.popupwindow_layout);
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                Window window = dialog.getWindow();
                window.setContentView(view);
                dialog.show();

                TextView upload = (TextView) view.findViewById(R.id.upload);
                TextView delete = (TextView) view.findViewById(R.id.delete);
                TextView cancel = (TextView) view.findViewById(R.id.cancel);
                // 上传
                upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<EnterpriseCreditJson> testList = EnterpriseCreditDao.query1(bean.getEid(), bean.getCid());
                        // 判断该企业的考核状态是否是已考评2
                        if(testList.get(0).getCtstatus() == 2){
                            // 判断该企业数据是否上传过
                            if(testList.get(0).getStatus() == 0){
                                upload(bean.getEid(), bean.getCid());
                            }else{
                                ToastUtils.show("提示：该企业考评数据已上传过，不能重复上传");
                            }
                            dialog.dismiss();
                        }else{
                            ToastUtils.show("提示：该企业还在考核中，不能上传");
                            dialog.dismiss();
                        }
                    }
                });
                // 删除
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delete(bean.getEid(), bean.getCid());
                        EventBus.getDefault().post(bean.getType());
                        dialog.dismiss();
                    }
                });
                // 取消
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                return true;
            }
        });
        return pfView;
    }

    @Override
    protected void onRefreshView(EnterpriseCreditJson bean, int position) {
        mTvPosition.setText((position+1) + "、");// 条目
        mTvName.setText(bean.getUnit_name());// 企业名称
        mTvTotalScore.setText(bean.getTotal() + "");// 总分
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(bean.getSubmit_date());
        mTvYear.setText(bean.getAnnual());// 年份
        mTvDate.setText(date);// 企业申报时间
        mTvStatus.setText(changeStatus(bean.getCtstatus()));// 状态
    }

    public String changeStatus(int status) {
        if (status == 1) {
            mTvStatus.setTextColor(Color.parseColor("#DC143C"));
            return "已申报";

        } else if (status == 2) {
            mTvStatus.setTextColor(Color.parseColor("#5CB292"));
            return "已考评";

        } else if (status == 3) {
            return "已审核";

        } else if (status == 4) {
            mTvStatus.setTextColor(Color.parseColor("#D2691E"));
            return "考评中";

        } else if (status == 5) {
            return "审核不通过";

        } else {
            return "审核结果出错";
        }
    }

    /**
     * 单个上传
     */
    private void upload(final int eid, final int cid){
        /*String name = PreferenceUtils.getString(context, "userName");// 拿到账号
        List<IntegrityUserJson> userList = IntegrityUserDao.query(name);// 根据账号，获取当前用户的数据
        InSaveMsg inSaveMsg = new InSaveMsg(1348831860, "save", userList.get(0).getPwd2());
        inSaveMsg.setModelName("EnterpriseCreditSave");
        HashMap<String, EvaluatePackage> map = new HashMap<>();
        // 判断所有企业都已考评后，才能将所有数据全部上传
        List<EvaluateTestJson> testList = EvaluateTestDao.query3(eid, cid);
        List<EnterpriseCreditJson> creditList = EnterpriseCreditDao.query1(eid, cid);
        EvaluatePackage mPackage = new EvaluatePackage();
        mPackage.setEnterpriseCreditList(creditList);// 企业名单
        mPackage.setEvaluateTestList(testList);// 打分表
        map.put("EvaluatePackage", mPackage);

        inSaveMsg.setListProperty(map);
        String postData = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            postData = mapper.writeValueAsString(inSaveMsg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        OkHttpClient mOkHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, postData);

        Request request = new Request.Builder()
                                  .url("http://192.168.9.126:8085/Mobile/Api")
                                  .post(requestBody)
                                  .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("请求失败——》" + call.toString());
                ToastUtils.show("网络访问失败,请检查当前网络");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("请求成功——》" + response.body().string());
                ToastUtils.show("上传成功");
                // 更改上传状态（0 未上传 1 已上传）
                List<EnterpriseCreditJson> testList = EnterpriseCreditDao.query1(eid, cid);

                EnterpriseCreditJson creditJson = new EnterpriseCreditJson();
                creditJson.setId(testList.get(0).getId());
                creditJson.setOid(testList.get(0).getOid());
                creditJson.setEid(testList.get(0).getEid());
                creditJson.setUnit_name(testList.get(0).getUnit_name());
                creditJson.setSubmit_date(testList.get(0).getSubmit_date());
                creditJson.setStatus(1);
                creditJson.setType(testList.get(0).getType());
                creditJson.setCid(testList.get(0).getCid());
                creditJson.setCtstatus(testList.get(0).getCtstatus());
                creditJson.setQrank(testList.get(0).getQrank());
                creditJson.setTotal(testList.get(0).getTotal());
                creditJson.setAnnual(testList.get(0).getAnnual());
                EnterpriseCreditDao.update(creditJson);
                //finish();
            }

        });*/
    }

    /**
     * 删除企业资料和打分数据
     */
    private void delete(int eid, int cid){
        EnterpriseCreditDao.delete(eid, cid);
        EvaluateTestDao.delete(eid, cid);
        mActivity.finish();
        ToastUtils.show("删除成功");
    }

}
