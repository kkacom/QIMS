package com.zhilian.rf_qims.interfaces;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by Administrator on 2018/5/12.
 */

public class EdCompareDesign implements TextWatcher {
    private EditText destEd;
    private EditText designEd;

    public EdCompareDesign(EditText destEd, EditText designEd) {
        this.designEd = designEd;
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
        String deststr=destEd.getText().toString().trim();
        String designstr=designEd.getText().toString().trim();
        if (deststr.equals("") || designstr.equals("")
                ||appearNumber(deststr,".")>1||appearNumber(designstr,".")>1
                ||designstr.startsWith(".")||deststr.startsWith(".")||designstr.endsWith(".")||deststr.endsWith(".")) {//不比较
            destEd.setTextColor(Color.parseColor("#000000"));
        } else {//比较差值百分比
            double value = Math.abs(Double.parseDouble(destEd.getText().toString().trim()) - Double.parseDouble(designEd.getText().toString().trim()));
            double divisor = value / Double.parseDouble(designEd.getText().toString().trim());
            Log.d("compare", value + "//" + divisor);
            if (divisor > 0.1) {//大于10%
                destEd.setTextColor(Color.parseColor("#ff0000"));
            } else {
                destEd.setTextColor(Color.parseColor("#000000"));
            }
        }
    }


    public  int appearNumber(String srcText, String findText) {
        int count = 0;
        int index = 0;
        while ((index = srcText.indexOf(findText, index)) != -1) {
            index = index + findText.length();
            count++;
        }
        return count;
    }
}