package com.zhilian.rf_qims.interfaces;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Administrator on 2018/5/12.
 */

public class EdDifferenceDesign implements TextWatcher {
    private EditText med1;
    private EditText med2;
    private EditText destEd;

    public EdDifferenceDesign(EditText ed1, EditText ed2, EditText destEd) {
        this.med1 = ed1;
        this.med2 = ed2;
        this.destEd = destEd;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String ed1 = med1.getText().toString().trim();
        String ed2 = med2.getText().toString().trim();
        if (ed1.equals("") || ed2.equals("")
                || appearNumber(ed1, ".") > 1 || appearNumber(ed2, ".") > 1
                || ed1.startsWith(".") || ed2.startsWith(".") || ed1.endsWith(".") || ed2.endsWith(".")) {//不比较
            destEd.setText("");
        } else {//得到差值
            double value = Math.abs(Double.parseDouble(med1.getText().toString().trim()) - Double.parseDouble(med2.getText().toString().trim()));
            destEd.setText(value+"");
        }
    }


    public int appearNumber(String srcText, String findText) {
        int count = 0;
        int index = 0;
        while ((index = srcText.indexOf(findText, index)) != -1) {
            index = index + findText.length();
            count++;
        }
        return count;
    }
}
