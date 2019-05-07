package com.zhilian.rf_qims.inter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * 各种控件的各种事件监听管理类
 * Created by YiFan on 2017/5/8.
 */
public abstract class ViewListener {

    /**
     * EditText的事件监听
     * @param et
     */
    private void eTListener(EditText et){
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //String str = s.toString();
                saveData("");

                //ToastUtils.show("suecssess："+str);
            }
        });
    }

    /**
     * RadioButton的事件监听
     * @param rb
     */
    private void rBListener(RadioButton rb){
        rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    protected abstract void saveData(String status);// 保存数据
}
