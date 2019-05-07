package com.zhilian.rf_qims.interfaces;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;

/**
 * Created by Administrator on 2018/5/12.
 */

public class EdThreeAverage implements TextWatcher {
    private EditText ed1;
    private EditText ed2;
    private EditText ed3;
    private EditText destEd;

    public EdThreeAverage(EditText ed1, EditText ed2, EditText ed3, EditText destEd) {
        this.ed1 = ed1;
        this.ed2 = ed2;
        this.ed3 = ed3;
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
        String ed1str = ed1.getText().toString().trim();
        String ed2str = ed2.getText().toString().trim();
        String ed3str = ed3.getText().toString().trim();
        if (ed1str.equals("") || appearNumber(ed1str, ".") > 1 || ed1str.startsWith(".") || ed1str.endsWith(".") ||
                ed2str.equals("") || appearNumber(ed2str, ".") > 1 || ed2str.startsWith(".") || ed2str.endsWith(".") ||
                ed3str.equals("") || appearNumber(ed3str, ".") > 1 || ed3str.startsWith(".") || ed3str.endsWith(".")) {//不比较
            destEd.setTextColor(Color.parseColor("#000000"));
            destEd.setText("");
        } else {//求平均值
            Double average = (Double.parseDouble(ed1str) + Double.parseDouble(ed2str) + Double.parseDouble(ed3str)) / 3;
            NumberFormat ddf1 = NumberFormat.getNumberInstance();
            ddf1.setMaximumFractionDigits(1);
            NumberFormat ddf2 = NumberFormat.getNumberInstance();
            ddf2.setMaximumFractionDigits(0);
            Double ddf1Value = Double.valueOf(ddf1.format(average).replace(",",""));
            Double ddf2Value = Double.valueOf(ddf2.format(average).replace(",",""));
            Double d = ddf1Value - ddf2Value;
            if (d > 0.5){
                destEd.setText((Double.valueOf(ddf2.format(average).replace(",",""))+1)+"");
            }else if (d * 2 > 0.5){
                destEd.setText((Double.valueOf(ddf2.format(average).replace(",",""))+0.5)+"");
            }else {
                destEd.setText((Double.valueOf(ddf2.format(average).replace(",","")))+"");
            }
//            destEd.setText(ddf1.format(average));
            destEd.setTextColor(Color.parseColor("#000000"));
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
