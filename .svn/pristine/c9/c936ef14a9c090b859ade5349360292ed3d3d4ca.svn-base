package com.zhilian.rf_qims.interfaces;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.entity.Project;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/5/12.
 */

public class EdProjectSaveChange implements TextWatcher {

    private String methodname;
    private EditText editText;
    private Project project;
    private String curvalue;
    public EdProjectSaveChange(String methodname,String curvalue, Project project, EditText editText) {
        this.methodname = methodname;
        this.editText = editText;
        this.project = project;
        this.curvalue=curvalue;
    }

//    public EdProjectSaveChange(String methodname, SampleCheck sampleCheck, EditText... editTexts) {
//        this.methodname = methodname;
//        this.editTexts = editTexts;
//        this.sampleCheck = sampleCheck;
//    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        Class clazz = project.getClass();
        try {
            Method method = clazz.getDeclaredMethod(methodname, String.class);
            if (project.getStatus()==2&&!curvalue.equals(editText.getText().toString().trim())){//已上传的做了修改
                project.setStatus(0);//更新状态
            }
            method.invoke(project, editText.getText().toString().trim());
            GreenDaoManager.getInstance().getNewSession().getProjectDao().update(project);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
