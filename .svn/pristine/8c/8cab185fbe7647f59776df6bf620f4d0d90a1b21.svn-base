package com.zhilian.rf_qims.util;

import android.widget.EditText;

/**
 * Created by Administrator on 2018/5/8.
 */

public class EdUtil {
    public static String getText(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static String mergeText(EditText... editTexts) {
        String mergetext = "";
        for (int i = 0; i < editTexts.length; i++) {
            mergetext += editTexts[i].getText().toString().trim() + ",";
        }
        return mergetext.substring(0, mergetext.length() - 1);
    }

    public static String[] split(String text) {
        if (text==null||text.equals("")){
            return new String[]{"","","","","","","","","","","","","","","","","","","","",""};
        }
        return text.split(",",-1);
    }
}
