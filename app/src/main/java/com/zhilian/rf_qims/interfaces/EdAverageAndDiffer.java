package com.zhilian.rf_qims.interfaces;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;

/**
 * Created by Administrator on 2018/5/12.
 */

public class EdAverageAndDiffer implements TextWatcher {
    private EditText ed1;
    private EditText ed2;
    private EditText ed3;
    private EditText differEd;
    private EditText averageEd;

    public EdAverageAndDiffer(EditText ed1, EditText ed2, EditText ed3, EditText differEd, EditText averageEd) {
        this.ed1 = ed1;
        this.ed2 = ed2;
        this.ed3 = ed3;
        this.differEd = differEd;
        this.averageEd = averageEd;
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
            differEd.setTextColor(Color.parseColor("#000000"));
            differEd.setText("");
            averageEd.setTextColor(Color.parseColor("#000000"));
            averageEd.setText("");
        } else {//求差值和平均值

            NumberFormat ddf1=NumberFormat.getNumberInstance() ;
            ddf1.setMaximumFractionDigits(2);
            differEd.setText(ddf1.format((getMax(ed1str, ed2str, ed3str) - getMin(ed1str, ed2str, ed3str))).replace(",",""));
            differEd.setTextColor(Color.parseColor("#000000"));
            if (getMax(ed1str, ed2str, ed3str) - getMin(ed1str, ed2str, ed3str) > 2) {//如果极差值大于2
                averageEd.setText("");
                averageEd.setTextColor(Color.parseColor("#000000"));
            } else {
                Double average = (Double.parseDouble(ed1str) + Double.parseDouble(ed2str) + Double.parseDouble(ed3str)) / 3;
                NumberFormat ddf2 = NumberFormat.getNumberInstance() ;
                ddf2.setMaximumFractionDigits(1);
//                averageEd.setText(ddf2.format(average));
                NumberFormat ddf3 = NumberFormat.getNumberInstance();
                ddf3.setMaximumFractionDigits(0);
                Double d= Double.valueOf(ddf2.format(average).replace(",",""))
                    - Double.valueOf(ddf3.format(average).replace(",",""));
                if (d>0.5){
                    averageEd.setText((Double.valueOf(ddf3.format(average).replace(",",""))+1)+"");
                }else if (d*2>0.5){
                    averageEd.setText((Double.valueOf(ddf3.format(average).replace(",",""))+0.5)+"");
                }else {
                    averageEd.setText((Double.valueOf(ddf3.format(average).replace(",","")))+"");
                }
                averageEd.setTextColor(Color.parseColor("#000000"));
            }

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

    public double getMax(String s1, String s2, String s3) {
        Double a = Double.parseDouble(s1);
        Double b = Double.parseDouble(s2);
        Double c = Double.parseDouble(s3);
        Double max;

        if (a > b) {
            if (a > c) {
                max = a;
            } else {
                max = c;
            }
        } else {
            if (b > c) {
                max = b;
            } else {
                max = c;
            }
        }
        return max;
    }

    //    public Double getMin(String s1, String s2, String s3) {
//        Double a = Double.parseDouble(s1);
//        Double b = Double.parseDouble(s2);
//        Double c = Double.parseDouble(s3);
//        //定义最小值
//        Double min;
//        //假如a>b 先设置a为最大值，b为最小值
//        if (a > b) {
//            min = b;
//        } else {
//            //否则先设置b为最大值，a为最小值
//            min = a;
//        }
//
//        //如果b大于c,那最小值肯定是c了
//        if (b > c) {
//            min = c;
//        }
//        return min;
//    }
//
//    public static int max(int a,int b,int c){
//        int max;
//        max=a;
//        max=(b>max)?b:max;
//        max=(c>max)?c:max;
//        return max;
//    }
    public Double getMin(String s1, String s2, String s3) {
        Double a = Double.parseDouble(s1);
        Double b = Double.parseDouble(s2);
        Double c = Double.parseDouble(s3);
        Double min;
        min = a;
        min = (b < min) ? b : min;
        min = (c < min) ? c : min;
        return min;
    }

}