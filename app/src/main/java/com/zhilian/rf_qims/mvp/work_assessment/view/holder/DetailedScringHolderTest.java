package com.zhilian.rf_qims.mvp.work_assessment.view.holder;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.entity.RulesDetailsJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.BaseHolder;
import com.zhilian.rf_qims.mvp.work_assessment.view.adpater.MyBaseAdapter;
import com.zhilian.rf_qims.util.CommonUtils;
import com.zhilian.rf_qims.util.SPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luocong on 2017/4/1.
 */
public class DetailedScringHolderTest extends BaseHolder<RulesDetailsJson> {
    /**
     * 这是弹出考核详情的按钮
     */
    @BindView(R.id.iv_note)
	TextView mIvNote;
    /**
     * 具体的评分列表数
     */
    @BindView(R.id.tv_postion)
	TextView mTvPostion;
 /*   *//**Item的拍照功能的按钮*//*
    @BindView(R.id.iv_photo)
    ImageView mIvPicture;*/
    /**
     * 显示在列表的评分项
     */
    @BindView(R.id.tv_scroe)
	TextView mTvScroe;
    @BindView(R.id.tv_dt_decr)
	TextView mTvDtDecr;
    private EditText mFeedback;
    private SPUtil mSpUtil;
    private EditText mEdScore;
    private String mTemp;
    private TextView mDescibe;
    private TextView mType;

    private static int mEid;
    private static int mCid;

    public DetailedScringHolderTest(Activity mActivity, Context context, ViewGroup parent, MyBaseAdapter<RulesDetailsJson> adapter, int position, RulesDetailsJson bean) {
        super(mActivity, context, parent, adapter, position, bean);
    }
    public static void setId(int eid, int cid){
        mEid = eid;
        mCid = cid;
    }


    @Override
    public View onCreateView(final Context context, ViewGroup parent, final int position, RulesDetailsJson bean) {
        View detailView = CommonUtils.getView(R.layout.detailsrcingtest);
        ButterKnife.bind(this, detailView);
        mSpUtil = new SPUtil(context);

        return detailView;

    }

    /**
     * 刷新界面控件UI,以及设置评分评分项
     */
    @Override
    protected void onRefreshView(RulesDetailsJson bean, final int position) {
       /* if (mEid == bean.getEid()) {*/
        String sn = bean.getSn();
        String substring = sn.substring(0, 1);

            mTvPostion.setText(bean.getSn());
            mTvDtDecr.setText(bean.getMemo());
            mTemp = position + "";
            mTvScroe.setText(mSpUtil.getString(mTemp + "score", ""));
        /*}*/

       /* mIvPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                context.startActivity(intent);
            }
        });*/

        mIvNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailScring(mTemp);
            }
        });

    }

    /**
     * 做考核评分和备注的填写和保存
     */
    private void detailScring(String position) {
        final String temp = position;
        View view = CommonUtils.getView(R.layout.detail_test);
        final Dialog dialog = new Dialog(DetailedScringHolderTest.this.context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        window.setContentView(view);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = 700;
        params.height = 900 ;
        dialog.getWindow().setAttributes(params);
        dialog.show();
        TextView clear = (TextView) view.findViewById(R.id.clear);
        TextView save = (TextView) view.findViewById(R.id.save);
        ImageView close = (ImageView) view.findViewById(R.id.iv_close);
        mFeedback = (EditText) view.findViewById(R.id.ed_feedback);
        mEdScore = (EditText) view.findViewById(R.id.ed_score);
        mDescibe = (TextView) view.findViewById(R.id.tv_as_descibe);
        mType = (TextView) view.findViewById(R.id.tv_srtype);
        String savaData = mSpUtil.getString(temp, "");
        mFeedback.setText(savaData);
        mEdScore.setText(mSpUtil.getString(temp + "score", ""));
        mType.setText(bean.getMarkType());
        //mDescibe.setText(bean.getAuditMark());
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFeedback.setText("");
                mEdScore.setText("");
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = mFeedback.getText().toString();
                mSpUtil.putString(temp, data);

                String score = mEdScore.getText().toString();
                mTvScroe.setText(score);
                mSpUtil.putString(temp + "score", score);
                dialog.dismiss();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
