package com.zhilian.rf_qims.mvp.work_assessment.view.activity.pfassessment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.base.CommonExpandableListAdapter;
import com.zhilian.rf_qims.base.WorkBaseActivity;
import com.zhilian.rf_qims.entity.EnterpriseCreditDao;
import com.zhilian.rf_qims.entity.EnterpriseCreditJson;
import com.zhilian.rf_qims.entity.EvaluateStandardDao;
import com.zhilian.rf_qims.entity.EvaluateStandardJson;
import com.zhilian.rf_qims.entity.EvaluateTestDao;
import com.zhilian.rf_qims.entity.EvaluateTestJson;
import com.zhilian.rf_qims.util.CommonUtils;
import com.zhilian.rf_qims.util.ToastUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 诚信评估（一、二级条款模块）
 * Created by YiFan on 2017/5/11.
 */
public class IntegrityModuleActivity extends WorkBaseActivity {
    static final int RECORD_AUDIO = 1;
    @BindView(R.id.tv_record)
	LinearLayout mTvRecord;// 现场录音
    @BindView(R.id.tv_video)
	LinearLayout mLLVideo;// 现场录像
    @BindView(R.id.tv_photo)
	LinearLayout mLLPhoto;// 现场拍照
    @BindView(R.id.tv_signature)
	LinearLayout mLLSignature;// 手写签名
    @BindView(R.id.special_event)
	LinearLayout mSpecialEvent;// 一票否决的不良行为
    @BindView(R.id.group_score)
	TextView mGroupScore;// 一票否决
    @BindView(R.id.name)
	TextView mName;// 当前公司
    @BindView(R.id.company_type)
	TextView mCompanyType;// 企业类型
    @BindView(R.id.total_score)
	TextView mTotalScore;// 总分
    @BindView(R.id.submit)
	TextView mSubmit;// 提交
    @BindView(R.id.list)
	ExpandableListView mList;
    CommonExpandableListAdapter<EvaluateStandardJson, EvaluateStandardJson> mAdapter;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_pf_assess_detail;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);

        final List<EnterpriseCreditJson> creditList = EnterpriseCreditDao.query1(mEid, mCid);
        List<EvaluateTestJson> testList = EvaluateTestDao.query1(mEid, mCid, 3, "0.0%");
        // 状态为已考评2时，不能再次提交
        if(creditList.get(0).getCtstatus() == 1){// 已申报1
            mSubmit.setVisibility(View.GONE);
        }else if(creditList.get(0).getCtstatus() == 2){// 已考评2
            mSubmit.setVisibility(View.GONE);
        }else{// 考核中4
            mSubmit.setVisibility(View.VISIBLE);
        }
        // 提交按钮的点击事件
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(IntegrityModuleActivity.this);
                AlertDialog dialog = null;// 对话框对象

                builder.setMessage("考评完成后，将不可修改考评数据，是否确认考评完成？");
                builder.setCancelable(false);// 不能取消这个对话框，点击了对话框里的按钮会自动关闭

                // 确定
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EnterpriseCreditJson creditJson = new EnterpriseCreditJson();
                        creditJson.setId(creditList.get(0).getId());
                        creditJson.setOid(creditList.get(0).getOid());
                        creditJson.setEid(creditList.get(0).getEid());
                        creditJson.setUnit_name(creditList.get(0).getUnit_name());
                        creditJson.setSubmit_date(creditList.get(0).getSubmit_date());
                        creditJson.setStatus(creditList.get(0).getStatus());
                        creditJson.setType(creditList.get(0).getType());
                        creditJson.setCid(creditList.get(0).getCid());
                        creditJson.setCtstatus(2);// 企业考评状态改成已考评2
                        creditJson.setQrank(creditList.get(0).getQrank());
                        creditJson.setTotal(creditList.get(0).getTotal());
                        creditJson.setAnnual(creditList.get(0).getAnnual());
                        EnterpriseCreditDao.update(creditJson);
                        ToastUtils.show("考核完成");
                        finish();
                    }
                });

                // 取消
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog = builder.create();
                dialog.show();
            }
        });

        // 只有防护企业有一票否决考核项
        if(mType.equals("1")){
            mSpecialEvent.setVisibility(View.VISIBLE);
            // 一票否决
            float score1 = testList.get(0).getAudit_mark();
            float score2 = testList.get(1).getAudit_mark();
            float score3 = testList.get(2).getAudit_mark();
            if(score1 == 100f && score2 == 100f && score3 == 100f){
                mGroupScore.setText("Y");
            }else{
                mGroupScore.setText("N");
            }
        }else{
            mSpecialEvent.setVisibility(View.GONE);
        }

        // 企业类型
        if(mType.equals("1")){
            mCompanyType.setText("防护企业");
        }else if(mType.equals("4")){
            mCompanyType.setText("设计企业");
        }else if(mType.equals("6")){
            mCompanyType.setText("监理企业");
        }else{
            // 都不是的情况
        }
        mName.setText(creditList.get(0).getUnit_name());// 当前公司
        mTotalScore.setText(creditList.get(0).getTotal() + "");// 总分

        // 一票否决的点击事件
        mSpecialEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntegrityModuleActivity.this, SpecialEventActivity.class);
                intent.putExtra("eid", mEid);// 企业ID
                intent.putExtra("cid", mCid);// 考核次数
                intent.putExtra("type", Integer.parseInt(mType));// 企业类型
                //intent.putExtra("item", sn);// 条款
                startActivityForResult(intent, 6);
            }
        });

        // 给ExpandableListView设置适配器
        mList.setAdapter(mAdapter = new CommonExpandableListAdapter<EvaluateStandardJson, EvaluateStandardJson>
                                                (this, R.layout.child_layout, R.layout.group_layout) {
            @Override// 子层
            protected void getChildView(ViewHolder holder, int groupPosition, int childPosition, boolean isLastChild, EvaluateStandardJson data) {
                List<EvaluateTestJson> testList = EvaluateTestDao.query(mEid, mCid, data.getSn());
                TextView tvPosition = holder.getView(R.id.child_position);
                TextView tvTitle = holder.getView(R.id.child_title);
                TextView tvScore = holder.getView(R.id.child_score);

                tvPosition.setText(data.getSn());// 条目
                tvTitle.setText(data.getTitle().trim() + "（" + (int)data.getScore() +"）");// 条款
                tvScore.setText(testList.get(0).getAudit_mark() + "");// 考核得分
                Log.e("test", data.getTitle());
            }

            @Override// 父层
            protected void getGroupView(ViewHolder holder, int groupPosition, boolean isExpanded, EvaluateStandardJson data) {
                List<EvaluateTestJson> testList = EvaluateTestDao.query(mEid, mCid, data.getSn());
                TextView tvPosition = holder.getView(R.id.group_position);
                TextView tvTitle = holder.getView(R.id.group_title);
                TextView tvScore = holder.getView(R.id.group_score);
                //View view = holder.getView(R.id.group_view);

                tvPosition.setText((groupPosition + 1) + "");// 条目
                tvTitle.setText(data.getTitle().trim() +"（"+ (int)data.getScore() +"）");// 条款+分值
                tvScore.setText(testList.get(0).getAudit_mark() + "");// 考核得分
                //view.setBackgroundColor(isExpanded ? Color.parseColor("#6495ED") : Color.parseColor("#DCDCDC"));
            }
        });
        //mList.setAdapter(mAdapter);

        addData();// 添加数据

        // 设置ExpandableListView展开一组，其他组关闭
        mList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < mAdapter.getGroupCount(); i++) {
                    if (groupPosition != i) {
                        mList.collapseGroup(i);
                    }
                }
            }
        });

        // 子层的点击事件
        mList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String sn = mAdapter.getChildrenData().get(groupPosition).get(childPosition).getSn();
                Intent intent = new Intent(IntegrityModuleActivity.this, IntegrityMarkActivity.class);
                intent.putExtra("eid", mEid);// 企业ID
                intent.putExtra("cid", mCid);// 考核次数
                intent.putExtra("type", Integer.parseInt(mType));// 企业类型
                intent.putExtra("item", sn);// 条款

                startActivityForResult(intent, 5);
                return false;
            }
        });
    }

    /**
     * 添加数据
     */
    private void addData() {
        List<EvaluateStandardJson> list1 = EvaluateStandardDao.query(1, Integer.parseInt(mType));

        // 父层数据（一级条款）
        for(int i = 0; i < list1.size(); i++){
            EvaluateStandardJson json = new EvaluateStandardJson();
            json = list1.get(i);
            mAdapter.getGroupData().add(json);
        }
        // 子层数据（二级条款）
        for (int i = 0; i < list1.size(); i++) {
            List<EvaluateStandardJson> list2 = EvaluateStandardDao.query1(2, Integer.parseInt(mType), (i+1) + ".%");
            List<EvaluateStandardJson> temp = new ArrayList<>();
            for (int j = 0; j < list2.size(); j++) {
                EvaluateStandardJson childData = new EvaluateStandardJson();
                childData = list2.get(j);
                temp.add(childData);
            }
            mAdapter.getChildrenData().add(temp);
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initListener() {
        // 现场录音
        mTvRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                startActivityForResult(intent, 1);
            }
        });

        // 现场录像
        mLLVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(intent, 2);
            }
        });

        // 现场拍照
        mLLPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // 启动相机
                startActivityForResult(intent, 3);
            }
        });

        // 手写签名
        mLLSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntegrityModuleActivity.this, DtSignAndPhoto.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 回调事件处理
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            // 验证请求码是否一至，也就是startActivityForResult的第二个参数
            switch (requestCode) {
                case 1:// 现场录音
                    break;
                case 2:// 现场录像
                    saveCameraVieo(data);
                   /* saveCameraImage(data);*/
                    break;
                case 3:// 现场拍照
                    saveCameraImage(data);
                    break;
                case 4:// 现场签名
                    saveSign(data);
                default:
                    break;
            }
        }

        // 一票否决的数据回调刷新
        if(requestCode == 6 && resultCode == 100){
            List<EvaluateTestJson> testList = EvaluateTestDao.query1(mEid, mCid, 3, "0.0%");
            // 这几项分值的取值和判定最好改成动态的，后续可优化
            float score1 = testList.get(0).getAudit_mark();
            float score2 = testList.get(1).getAudit_mark();
            float score3 = testList.get(2).getAudit_mark();
            if(score1 == 100f && score2 == 100f && score3 == 100f){
                mGroupScore.setText("Y");
            }else{
                mGroupScore.setText("N");
            }
            // 修改企业考评状态
            updateStatus();
        }

        // 考评打分的数据回调刷新
        if(requestCode == 5 && resultCode == 100){
            // 二级条款
            String item = data.getStringExtra("item");
            List<EvaluateTestJson> testList = EvaluateTestDao.query1(mEid, mCid, 3, item+"%");
            List<EvaluateTestJson> testList2 = EvaluateTestDao.query(mEid, mCid, item);

            float value = 0;// 二级条款分值
            for(int i = 0;i < testList.size();i++){
                value += testList.get(i).getAudit_mark();
            }
            EvaluateTestJson testJson = new EvaluateTestJson();
            testJson.setId(testList2.get(0).getId());// id
            testJson.setOid(testList2.get(0).getOid());
            testJson.setCid(testList2.get(0).getCid());
            testJson.setEid(testList2.get(0).getEid());
            testJson.setItem(testList2.get(0).getItem());
            testJson.setMark(testList2.get(0).getMark());
            testJson.setWremark(testList2.get(0).getWremark());
            testJson.setUnit_name(testList2.get(0).getUnit_name());
            testJson.setType(testList2.get(0).getType());
            testJson.setAudit_mark(CommonUtils.floatTwo(value));// 考核打分
            testJson.setLevel(testList2.get(0).getLevel());
            testJson.setScore_type(testList2.get(0).getScore_type());
            testJson.setEremark(testList2.get(0).getEremark());// 备注
            EvaluateTestDao.update(testJson);
            mAdapter.notifyDataSetChanged();

            // 一级条款
            String item2 = data.getStringExtra("item").substring(0, 1);
            List<EvaluateTestJson> testList1 = EvaluateTestDao.query1(mEid, mCid, 2, item2+".%");
            List<EvaluateTestJson> testList3 = EvaluateTestDao.query(mEid, mCid, item2);
            float value2 = 0;// 一级条款分值
            for(int i = 0;i < testList1.size();i++){
                value2 += testList1.get(i).getAudit_mark();
            }
            System.out.println("长度——"+testList1.size());
            System.out.println("分——》"+value2);
            EvaluateTestJson testJson2 = new EvaluateTestJson();
            testJson2.setId(testList3.get(0).getId());// id
            testJson2.setOid(testList3.get(0).getOid());
            testJson2.setCid(testList3.get(0).getCid());
            testJson2.setEid(testList3.get(0).getEid());
            testJson2.setItem(testList3.get(0).getItem());
            testJson2.setMark(testList3.get(0).getMark());
            testJson2.setWremark(testList3.get(0).getWremark());
            testJson2.setUnit_name(testList3.get(0).getUnit_name());
            testJson2.setType(testList3.get(0).getType());
            testJson2.setAudit_mark(CommonUtils.floatTwo(value2));// 考核打分
            testJson2.setLevel(testList3.get(0).getLevel());
            testJson2.setScore_type(testList3.get(0).getScore_type());
            testJson2.setEremark(testList3.get(0).getEremark());// 备注
            EvaluateTestDao.update(testJson2);

            // 总分
            List<EvaluateTestJson> testList4 = EvaluateTestDao.query2(mEid, mCid, 1);
            List<EnterpriseCreditJson> testList5 = EnterpriseCreditDao.query1(mEid, mCid);
            float value3 = 0;// 总分
            for(int i = 0;i < testList4.size();i++){
                value3 += testList4.get(i).getAudit_mark();
            }
            EnterpriseCreditJson creditJson = new EnterpriseCreditJson();
            creditJson.setId(testList5.get(0).getId());
            creditJson.setOid(testList5.get(0).getOid());
            creditJson.setEid(testList5.get(0).getEid());
            creditJson.setUnit_name(testList5.get(0).getUnit_name());
            creditJson.setSubmit_date(testList5.get(0).getSubmit_date());
            creditJson.setStatus(testList5.get(0).getStatus());
            creditJson.setType(testList5.get(0).getType());
            creditJson.setCid(testList5.get(0).getCid());
            int ctstatus = testList5.get(0).getCtstatus();// 状态
            if(ctstatus == 1){// 如果是已申报1状态，就改成考评中4
                creditJson.setCtstatus(4);
                mSubmit.setVisibility(View.VISIBLE);
            }else{// 如果是考评中4状态，或已考评2状态，就什么也不用改
                creditJson.setCtstatus(testList5.get(0).getCtstatus());
            }
            creditJson.setQrank(testList5.get(0).getQrank());
            creditJson.setTotal(CommonUtils.floatTwo(value3));
            creditJson.setAnnual(testList5.get(0).getAnnual());
            EnterpriseCreditDao.update(creditJson);
            mTotalScore.setText(value3 + "");

            mAdapter.notifyDataSetChanged();
            //ToastUtils.show(item2+"回调了——》" + value+item);
        }
    }

    /**
     * 修改企业考评状态
     */
    private void updateStatus(){
        List<EnterpriseCreditJson> testList5 = EnterpriseCreditDao.query1(mEid, mCid);
        EnterpriseCreditJson creditJson = new EnterpriseCreditJson();
        creditJson.setId(testList5.get(0).getId());
        creditJson.setOid(testList5.get(0).getOid());
        creditJson.setEid(testList5.get(0).getEid());
        creditJson.setUnit_name(testList5.get(0).getUnit_name());
        creditJson.setSubmit_date(testList5.get(0).getSubmit_date());
        creditJson.setStatus(testList5.get(0).getStatus());
        creditJson.setType(testList5.get(0).getType());
        creditJson.setCid(testList5.get(0).getCid());
        int ctstatus = testList5.get(0).getCtstatus();// 状态
        if(ctstatus == 1){// 如果是已申报1状态，就改成考评中4
            creditJson.setCtstatus(4);
            mSubmit.setVisibility(View.VISIBLE);
        }else{// 如果是考评中4状态，或已考评2状态，就什么也不用改
            creditJson.setCtstatus(testList5.get(0).getCtstatus());
        }
        creditJson.setQrank(testList5.get(0).getQrank());
        creditJson.setTotal(testList5.get(0).getTotal());
        creditJson.setAnnual(testList5.get(0).getAnnual());
        EnterpriseCreditDao.update(creditJson);
    }

    private void saveSign(Intent data) {

    }

    /**
     * 保存录像
     */
    private void saveCameraVieo(Intent intent) {
        // 录制完成
        try {
            AssetFileDescriptor videoAsset = getContentResolver()
                    .openAssetFileDescriptor(intent.getData(), "r");
            FileInputStream fis = videoAsset.createInputStream();
           /* File tmpFile = new File(
                    Environment.getExternalStorageDirectory(),
                    "recordvideo.mp4");*/
            File file = new File("/mnt/sdcard/test/");
            file.mkdirs();// 创建文件夹
            String name = new DateFormat().format("yyyy-MM-dd-hh-mm",
                    Calendar.getInstance(Locale.CHINA))
                    + ".avi";
            String fileName = "/mnt/sdcard/test/" + name;// 保存路径
            FileOutputStream fos = new FileOutputStream(fileName);

            byte[] buf = new byte[1024];
            int len;
            while ((len = fis.read(buf)) > 0) {
                fos.write(buf, 0, len);
            }
            fis.close();
            fos.close();
            /*// 文件写完之后删除/sdcard/dcim/CAMERA/XXX.MP4
            deleteDefaultFile(intent.getData());*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存相机的图片
     **/
    private void saveCameraImage(Intent data) {
        // 检查sd card是否存在
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d("TAG", "sd card is not avaiable/writeable right now.");
            return;
        }

        // 为图片命名啊
        String name = new DateFormat().format("yyyy-MM-dd-hh-mm",
                Calendar.getInstance(Locale.CHINA))
                + ".jpg";
        Log.d("bug", name);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");// 解析返回的图片成bitmap
        // 保存文件
        FileOutputStream fos = null;
        File file = new File("/mnt/sdcard/test/");
        file.mkdirs();// 创建文件夹
        String fileName = "/mnt/sdcard/test/" + name;// 保存路径

        try {// 写入SD card
            fos = new FileOutputStream(fileName);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }// 显示图片
    }

    @Override
    public void initData() {

    }

    /**
     * 头部栏的标题（企业名称）
     */
    @Override
    protected String getCompanyName() {
        return "企业考评";
    }

}
