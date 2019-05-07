package com.zhilian.rf_qims.bean;

/**
 * Created by luocong on 2017/3/30.
 */

public class PfAssessmentBean {
    private String companyName;
    private String score;
    private String ipDate;
    private String status;

    public PfAssessmentBean() {

    }

    public PfAssessmentBean(String companyName, String score, String ipDate, String status) {
        this.companyName = companyName;
        this.score = score;
        this.ipDate = ipDate;
        this.status = status;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getIpDate() {
        return ipDate;
    }

    public void setIpDate(String ipDate) {
        this.ipDate = ipDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
