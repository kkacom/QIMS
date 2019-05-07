package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 诚信评估配置表（json）
 * Created by YiFan on 2017/5/10.
 */
@Entity
public class EvaluateStandardJson {
    @Id(autoincrement = true)
    private Long id;

    private String sn;// 序号
    private String title;// 标题
    private float score;// 分值
    private Integer number;// 数量
    private String each;// 单计
    private Integer level;// 条目级别
    private String memo;// 计分规则
    private Integer type;// 类型（1防护企业，6监理企业，4设计企业）
    private Integer score_type;// 分值类型（0是加分 1是扣分）

    @Generated(hash = 442167353)
    public EvaluateStandardJson(Long id, String sn, String title, float score,
								Integer number, String each, Integer level, String memo, Integer type,
								Integer score_type) {
        this.id = id;
        this.sn = sn;
        this.title = title;
        this.score = score;
        this.number = number;
        this.each = each;
        this.level = level;
        this.memo = memo;
        this.type = type;
        this.score_type = score_type;
    }

    @Generated(hash = 1546088069)
    public EvaluateStandardJson() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSn() {
        return this.sn;
    }
    public void setSn(String sn) {
        this.sn = sn;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public float getScore() {
        return this.score;
    }
    public void setScore(float score) {
        this.score = score;
    }
    public Integer getNumber() {
        return this.number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public String getEach() {
        return this.each;
    }
    public void setEach(String each) {
        this.each = each;
    }
    public Integer getLevel() {
        return this.level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }
    public String getMemo() {
        return this.memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public Integer getType() {
        return this.type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getScore_type() {
        return this.score_type;
    }
    public void setScore_type(Integer score_type) {
        this.score_type = score_type;
    }

}
