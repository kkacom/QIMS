package com.zhilian.rf_qims.interfaces;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.dao.SampleDao;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.entity.SampleCheck;
import com.zhilian.rf_qims.util.EdUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/5/12.
 */

public class EdTextChange implements TextWatcher {
    Common mCommon = new Common();
    private String methodname;
    private EditText[] editTexts;
//    private Sample sample;
    private SampleCheck sampleCheck;
    private String oldtext;

//    public EdTextChange(String methodname, Sample sample, EditText... editTexts) {
//        this.methodname = methodname;
//        this.editTexts = editTexts;
//        this.sample = sample;
//    }

//    public EdTextChange(String methodname, SampleCheck sampleCheck, EditText... editTexts) {
//        this.methodname = methodname;
//        this.editTexts = editTexts;
//        this.sampleCheck = sampleCheck;
//    }

    public EdTextChange(String methodname, SampleCheck sampleCheck, String oldtext, EditText... editTexts) {
        this.methodname = methodname;
        this.editTexts = editTexts;
        this.sampleCheck = sampleCheck;
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
            Class clazz = sampleCheck.getClass();
            try {
                Method method = clazz.getDeclaredMethod(methodname, String.class);
                method.invoke(sampleCheck, EdUtil.mergeText(editTexts));
                if ((!oldtext.equals(EdUtil.mergeText(editTexts))&&sampleCheck.getStatus()==2)) {
                    sampleCheck.setStatus(0);
                    Sample sample=GreenDaoManager.getInstance().getNewSession().getSampleDao().queryBuilder().where(SampleDao.Properties
                    .Id.eq(sampleCheck.getId())).unique();
                    sample.setStatus(0);
                    GreenDaoManager.getInstance().getNewSession().getSampleDao().update(sample);
                    mCommon.setSampleCheck(sampleCheck);
                    GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().update(sampleCheck);
                }else {
                    mCommon.setSampleCheck(sampleCheck);
                    GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().update(sampleCheck);
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
