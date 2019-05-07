package com.zhilian.rf_qims.mvp.work_assessment.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.base.MyBaseFragment;
import com.zhilian.rf_qims.entity.IntegrityUserDao;
import com.zhilian.rf_qims.entity.IntegrityUserJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.AboutUsActivity;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.LoginActivity;
import com.zhilian.rf_qims.util.PreferenceUtils;
import com.zhilian.rf_qims.util.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 用户
 * Created by luocong on 2017/3/27.
 */
public class UserFragment extends MyBaseFragment implements View.OnClickListener{
    @BindView(R.id.user_safe)
	LinearLayout mUserSafe;// 账号与安全
    @BindView(R.id.message_push)
	LinearLayout mMessagePush;// 消息推送
    @BindView(R.id.my_do_not_disturb)
	LinearLayout mDisturb;// 勿扰模式
    @BindView(R.id.my_privacy)
	LinearLayout mPrivacy;// 隐私
    @BindView(R.id.setting)
	LinearLayout mSetting;// 通用
    @BindView(R.id.about_soft)
	LinearLayout mAboutSoft;// 关于本软件
    @BindView(R.id.my_exit_login)
	RelativeLayout mExitLogin;// 退出登录
    @BindView(R.id.tv_username)
	TextView mUsername;// 姓名
    Unbinder unbinder;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_integrity;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this, mRoot);

        String dlid = PreferenceUtils.getString(getActivity(), "userName");// 账号3
        // 根据账号查询相应用户数据
        List<IntegrityUserJson> userList = IntegrityUserDao.query(dlid);
        // 显示当前登录的用户名字
        mUsername.setText(userList.get(0).getName());
    }

    @Override
    public void initListener() {
        mUserSafe.setOnClickListener(this);
        mMessagePush.setOnClickListener(this);
        mDisturb.setOnClickListener(this);
        mPrivacy.setOnClickListener(this);
        mSetting.setOnClickListener(this);
        mAboutSoft.setOnClickListener(this);
        mExitLogin.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_safe:// 账号与安全
            case R.id.message_push:// 消息推送
            case R.id.my_do_not_disturb:// 勿扰模式
            case R.id.my_privacy:// 隐私
            case R.id.setting:// 通用
                ToastUtils.show("未开通权限");
                break;
            case R.id.about_soft:// 关于本软件
                Intent intent = new Intent(mActivity, AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.my_exit_login:// 退出登录
                exitLogin();
                break;
        }
    }

    /**
     * 退出登录
     */
    private void exitLogin(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("确认退出登录？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                arg0.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // 将登录密码置空
                PreferenceUtils.putString(getActivity(), "userPassword", "");
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        builder.create().show();
    }
}
