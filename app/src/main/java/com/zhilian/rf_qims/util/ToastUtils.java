package com.zhilian.rf_qims.util;

import android.widget.Toast;

/**
 * Toast管理类
 * Created by luocong on 2016/12/7.
 */
public class ToastUtils {
    private static Toast toast;
    public static void show(String text){
        if (toast==null){
            toast = Toast.makeText(CommonUtils.getContext(),text, Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();
    }

    public static void showLong(String text){
        if (toast==null){
            toast = Toast.makeText(CommonUtils.getContext(),text, Toast.LENGTH_LONG);
        }
        toast.setText(text);
        toast.show();
    }
    //显示资源id的文本
    public static void show(int textResId){
        if (toast==null){
            toast = Toast.makeText(CommonUtils.getContext(),textResId, Toast.LENGTH_SHORT);
        }
        toast.setText(textResId);
        toast.show();
    }

}
