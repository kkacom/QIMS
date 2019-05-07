package com.zhilian.rf_qims.bean;

/**
 * Created by Administrator on 2017-9-26.
 */
public class DataBean {
    private String text;
    private int resId;

    public DataBean(String text, int resId) {
        this.text = text;
        this.resId = resId;
    }

    public DataBean() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
