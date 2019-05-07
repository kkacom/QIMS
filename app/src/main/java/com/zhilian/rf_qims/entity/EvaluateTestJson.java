package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 诚信评估打分表（json）
 * Created by YiFan on 2017/5/10.
 */
@Entity
public class EvaluateTestJson {
    @Id(autoincrement = true)
    private Long id;

    private Integer oid;// 怪异的ID
    private Integer cid;// 检查记录ID
    private Integer eid;// 企业ID
    private String item;// 考核项
    private float mark;// 企业自评得分
    private String wremark;// 扣分详细
    private String unit_name;// 企业名称
    private Integer type;// 企业类型
    private float audit_mark;// 审核得分
    private Integer level;// 条目级别
    private Integer score_type;// 分值类型（0 加分 1 扣分）
    private String eremark;// 企业备注

    @Generated(hash = 946820175)
    public EvaluateTestJson(Long id, Integer oid, Integer cid, Integer eid,
							String item, float mark, String wremark, String unit_name, Integer type,
							float audit_mark, Integer level, Integer score_type, String eremark) {
        this.id = id;
        this.oid = oid;
        this.cid = cid;
        this.eid = eid;
        this.item = item;
        this.mark = mark;
        this.wremark = wremark;
        this.unit_name = unit_name;
        this.type = type;
        this.audit_mark = audit_mark;
        this.level = level;
        this.score_type = score_type;
        this.eremark = eremark;
    }

    @Generated(hash = 447747577)
    public EvaluateTestJson() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getOid() {
        return this.oid;
    }
    public void setOid(Integer oid) {
        this.oid = oid;
    }
    public Integer getCid() {
        return this.cid;
    }
    public void setCid(Integer cid) {
        this.cid = cid;
    }
    public Integer getEid() {
        return this.eid;
    }
    public void setEid(Integer eid) {
        this.eid = eid;
    }
    public String getItem() {
        return this.item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public float getMark() {
        return this.mark;
    }
    public void setMark(float mark) {
        this.mark = mark;
    }
    public String getWremark() {
        return this.wremark;
    }
    public void setWremark(String wremark) {
        this.wremark = wremark;
    }
    public String getUnit_name() {
        return this.unit_name;
    }
    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }
    public Integer getType() {
        return this.type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public float getAudit_mark() {
        return this.audit_mark;
    }
    public void setAudit_mark(float audit_mark) {
        this.audit_mark = audit_mark;
    }
    public Integer getLevel() {
        return this.level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }
    public Integer getScore_type() {
        return this.score_type;
    }
    public void setScore_type(Integer score_type) {
        this.score_type = score_type;
    }
    public String getEremark() {
        return this.eremark;
    }
    public void setEremark(String eremark) {
        this.eremark = eremark;
    }
}
