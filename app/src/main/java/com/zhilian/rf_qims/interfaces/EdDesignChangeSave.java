package com.zhilian.rf_qims.interfaces;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.dao.SampleCheckDao;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.entity.SampleCheck;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/5/12.
 */

public class EdDesignChangeSave implements TextWatcher {
    Common common = new Common();
    private String methodname;
    private EditText editText;
    private Sample sample;
    String oldtext;
    public EdDesignChangeSave(String methodname, Sample sample,String oldtext, EditText editText) {
        this.methodname = methodname;
        this.sample = sample;
        this.editText=editText;
        if (oldtext!=null){
            this.oldtext = oldtext;
        }else {
            this.oldtext="";
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String str=editText.getText().toString().trim();
            Class clazz = sample.getClass();
            try {
                Method method = clazz.getDeclaredMethod(methodname, String.class);
                method.invoke(sample,editText.getText().toString().trim());
                if ((!oldtext.equals(editText.getText().toString().trim())&&sample.getStatus()==2)) {
                    sample.setStatus(0);
                    SampleCheck sampleCheck=GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().queryBuilder().where(SampleCheckDao.Properties
                            .Id.eq(sample.getId())).unique();
                    sampleCheck.setStatus(0);
                    GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().update(sampleCheck);
                    common.setSample(sample);
                    GreenDaoManager.getInstance().getNewSession().getSampleDao().update(sample);
                }else {
                    common.setSample(sample);
                    GreenDaoManager.getInstance().getNewSession().getSampleDao().update(sample);
                }

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

    }
}
