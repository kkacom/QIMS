package com.zhilian.rf_qims.mvp.work_assessment.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 联系人（待完善）
 */
public class ContactsFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.contacts_address_list)
	LinearLayout mAddressList;// 通讯录
    @BindView(R.id.contacts_my_group)
	LinearLayout mMyGroup;// 我的群组
    @BindView(R.id.contacts_create_group)
	LinearLayout mCreateGroup;// 创建团队
    @BindView(R.id.contacts_common)
	LinearLayout mCommon;// 常用联系人

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_layout, null);
        ButterKnife.bind(this, view);

        // 设置事件监听
        mAddressList.setOnClickListener(this);
        mMyGroup.setOnClickListener(this);
        mCreateGroup.setOnClickListener(this);
        mCommon.setOnClickListener(this);

        return view;
    }

    /**
     * 事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.contacts_address_list:// 通讯录
            case R.id.contacts_my_group:// 我的群组
            case R.id.contacts_create_group:// 创建团队
            case R.id.contacts_common:// 常用联系人
                ToastUtils.show("未开通权限");
                break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
