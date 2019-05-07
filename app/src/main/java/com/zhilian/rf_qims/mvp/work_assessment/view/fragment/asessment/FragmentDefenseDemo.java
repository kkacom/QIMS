package com.zhilian.rf_qims.mvp.work_assessment.view.fragment.asessment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luck.picture.lib.rxbus2.Subscribe;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.api.InSaveMsg;
import com.zhilian.rf_qims.base.BaseListFragment;
import com.zhilian.rf_qims.entity.EnterpriseCreditDao;
import com.zhilian.rf_qims.entity.EnterpriseCreditJson;
import com.zhilian.rf_qims.entity.EvaluateTestDao;
import com.zhilian.rf_qims.entity.EvaluateTestJson;
import com.zhilian.rf_qims.entity.IntegrityUserDao;
import com.zhilian.rf_qims.entity.IntegrityUserJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.pfassessment.IntegrityModuleActivity;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.DemoAdapter;
import com.zhilian.rf_qims.util.CommonUtils;
import com.zhilian.rf_qims.util.EventUtil;
import com.zhilian.rf_qims.util.PreferenceUtils;
import com.zhilian.rf_qims.util.ToastUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 防护企业
 * Created by YiFan on 2017/5/27.
 */
public class FragmentDefenseDemo extends BaseListFragment {
    List<EnterpriseCreditJson> creditList = new ArrayList<EnterpriseCreditJson>();
    DemoAdapter mAdapter;
    Handler myhandler = new Handler();

    @Subscribe
    public void onEvent(EventUtil event) {
        System.out.println("---->>"+event.getMsg());
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        myhandler.post(eChanged);
    }

    Runnable eChanged = new Runnable() {
        @Override
        public void run() {
            creditList.clear();
            creditList.addAll(EnterpriseCreditDao.query(1));
            mAdapter = new DemoAdapter(getActivity(), creditList);
            mListView.setAdapter(mAdapter);
            if(creditList.size() > 0){
                mListBg.setVisibility(View.GONE);
            }else{
                mListBg.setVisibility(View.VISIBLE);
            }
            mAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void initView() {
        data();
    }

    @Override
    protected void listener(int position) {
        Intent intent = new Intent(getActivity(), IntegrityModuleActivity.class);
        intent.putExtra("type", String.valueOf(creditList.get(position).getType()));// 企业类型
        intent.putExtra("eid", creditList.get(position).getEid());// 企业ID
        intent.putExtra("cid", creditList.get(position).getCid());// 考核次数
        startActivity(intent);
    }

    @Override
    protected void longListener(final int position) {
        View view = CommonUtils.getView(R.layout.popupwindow_layout);
        final Dialog dialog = new Dialog(getActivity());
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
                List<EnterpriseCreditJson> testList = EnterpriseCreditDao.query1(creditList.get(position).getEid(), creditList.get(position).getCid());
                // 判断该企业的考核状态是否是已考评2
                if(testList.get(0).getCtstatus() == 2){
                    // 判断该企业数据是否上传过
                    if(testList.get(0).getStatus() == 0){
                        upload(creditList.get(position).getEid(), creditList.get(position).getCid());
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
                delete(creditList.get(position).getEid(), creditList.get(position).getCid());
                //EventBus.getDefault().post(creditList.get(position).getType());

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
    }

    /**
     * 初始化数据
     */
    private void data(){
        creditList = EnterpriseCreditDao.query(1);
        mAdapter = new DemoAdapter(getActivity(), creditList);
        mListView.setAdapter(mAdapter);
        if(creditList.size() > 0){
            mListBg.setVisibility(View.GONE);
        }else{
            mListBg.setVisibility(View.VISIBLE);
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 单个上传
     */
    private void upload(final int eid, final int cid){
        String name = PreferenceUtils.getString(getActivity(), "userName");// 拿到账号
        List<IntegrityUserJson> userList = IntegrityUserDao.query(name);// 根据账号，获取当前用户的数据
        InSaveMsg inSaveMsg = new InSaveMsg(1348831860, "save");
        inSaveMsg.setKey(userList.get(0).getPwd2());// key
        inSaveMsg.setModelName("EnterpriseCreditSave");
        // 判断所有企业都已考评后，才能将所有数据全部上传
        List<EvaluateTestJson> testList = EvaluateTestDao.query3(eid, cid);
        List<EnterpriseCreditJson> creditList = EnterpriseCreditDao.query1(eid, cid);
        inSaveMsg.setCreditListProperty(creditList);// 企业名单
        inSaveMsg.setTestListProperty(testList);// 打分表

        /*EvaluatePackage mPackage = new EvaluatePackage();
        mPackage.setEnterpriseCreditList(creditList);// 企业名单
        mPackage.setEvaluateTestList(testList);// 打分表
        map.put("EvaluatePackage", mPackage);*/

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
                //mAdapter.notifyDataSetChanged();
                myhandler.post(eChanged);
            }

        });
    }

    /**
     * 单个删除企业资料和打分数据
     */
    private void delete(int eid, int cid){
        EnterpriseCreditDao.delete(eid, cid);
        EvaluateTestDao.delete(eid, cid);
        creditList = EnterpriseCreditDao.query(1);
        mAdapter = new DemoAdapter(getActivity(), creditList);
        mListView.setAdapter(mAdapter);
        if(creditList.size() > 0){
            mListBg.setVisibility(View.GONE);
        }else{
            mListBg.setVisibility(View.VISIBLE);
        }
        mAdapter.notifyDataSetChanged();
        ToastUtils.show("删除成功");
    }

    @Override
    public void onResume() {
        super.onResume();
        // 打完分后回来刷新界面，这种方式好像不是很好，后续可优化
        data();
    }

}
