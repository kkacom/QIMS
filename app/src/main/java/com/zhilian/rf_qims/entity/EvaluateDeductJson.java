package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * 诚信评估扣分表（json）
 * Created by YiFan on 2017/5/10.
 */
@Entity
public class EvaluateDeductJson {
    @Id(autoincrement = true)
    private Long id;

    private Integer eid;// 企业ID
    private Integer cid;// 考核次数
    private String item;// 条目序号
    private float score;// 分值
    private Date dodate;// 违反时间
    private String unit_name;// 企业名称
    private Integer type;// 企业类型（1 防护企业 2 监理企业 3 设计企业）
    private Integer status;// 控制信息的有效性（0 有效 1 失效）
    private String deduct_url;// 附件
    private String project_name;// 项目名称
    private String executable_unit;// 处罚单位
    private String remark;// 备注

    @Generated(hash = 806790483)
    public EvaluateDeductJson(Long id, Integer eid, Integer cid, String item,
							  float score, Date dodate, String unit_name, Integer type,
							  Integer status, String deduct_url, String project_name,
							  String executable_unit, String remark) {
        this.id = id;
        this.eid = eid;
        this.cid = cid;
        this.item = item;
        this.score = score;
        this.dodate = dodate;
        this.unit_name = unit_name;
        this.type = type;
        this.status = status;
        this.deduct_url = deduct_url;
        this.project_name = project_name;
        this.executable_unit = executable_unit;
        this.remark = remark;
    }

    @Generated(hash = 1475754434)
    public EvaluateDeductJson() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getEid() {
        return this.eid;
    }
    public void setEid(Integer eid) {
        this.eid = eid;
    }
    public Integer getCid() {
        return this.cid;
    }
    public void setCid(Integer cid) {
        this.cid = cid;
    }
    public String getItem() {
        return this.item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public float getScore() {
        return this.score;
    }
    public void setScore(float score) {
        this.score = score;
    }
    public Date getDodate() {
        return this.dodate;
    }
    public void setDodate(Date dodate) {
        this.dodate = dodate;
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
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getDeduct_url() {
        return this.deduct_url;
    }
    public void setDeduct_url(String deduct_url) {
        this.deduct_url = deduct_url;
    }
    public String getProject_name() {
        return this.project_name;
    }
    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }
    public String getExecutable_unit() {
        return this.executable_unit;
    }
    public void setExecutable_unit(String executable_unit) {
        this.executable_unit = executable_unit;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
