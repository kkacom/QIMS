package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * 诚信评估加分表（json）
 * Created by YiFan on 2017/5/10.
 */
@Entity
public class EvaluateAddJson {
    @Id(autoincrement = true)
    private Long id;

    private Integer eid;// 企业ID
    private Integer cid;// 考核次数
    private String item;// 条目序号
    private float score;// 分值
    private Date getDate;// 获奖日期
    private String add_url;// 获奖附件url
    private Integer status;// 控制信息的有效性（0 有效 1 失效）
    private Integer type;// 企业类型（1 防护企业 2 监理企业 3 设计企业）
    private String item_name;// 获奖项目
    private String prize_unit;// 颁奖单位
    private String remark;// 备注

    @Generated(hash = 122687854)
    public EvaluateAddJson(Long id, Integer eid, Integer cid, String item,
						   float score, Date getDate, String add_url, Integer status, Integer type,
						   String item_name, String prize_unit, String remark) {
        this.id = id;
        this.eid = eid;
        this.cid = cid;
        this.item = item;
        this.score = score;
        this.getDate = getDate;
        this.add_url = add_url;
        this.status = status;
        this.type = type;
        this.item_name = item_name;
        this.prize_unit = prize_unit;
        this.remark = remark;
    }

    @Generated(hash = 510599604)
    public EvaluateAddJson() {
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
    public Date getGetDate() {
        return this.getDate;
    }
    public void setGetDate(Date getDate) {
        this.getDate = getDate;
    }
    public String getAdd_url() {
        return this.add_url;
    }
    public void setAdd_url(String add_url) {
        this.add_url = add_url;
    }
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getType() {
        return this.type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getItem_name() {
        return this.item_name;
    }
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
    public String getPrize_unit() {
        return this.prize_unit;
    }
    public void setPrize_unit(String prize_unit) {
        this.prize_unit = prize_unit;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }


}
