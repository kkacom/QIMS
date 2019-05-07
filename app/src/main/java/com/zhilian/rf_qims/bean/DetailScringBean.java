package com.zhilian.rf_qims.bean;

/**
 * Created by luocong on 2017/4/1.
 */

public class DetailScringBean {
    private String describe;
    private String input;
    private String scoreType;



    public DetailScringBean() {
    }

    public DetailScringBean(String describe, String input, String scoreType) {
        this.describe = describe;
        this.input = input;
        this.scoreType = scoreType;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

}
