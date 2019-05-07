package com.zhilian.rf_qims.bean;

/**
 * 评估打分描述的实体类
 */
public class AssessmentBean {
    private String description;// 描述
    private float score;// 分数

    public AssessmentBean(String description, float score) {
        this.description = description;
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
