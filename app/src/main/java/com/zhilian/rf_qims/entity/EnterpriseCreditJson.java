package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * 诚信评估企业名单表（json）
 * Created by YiFan on 2017/5/10.
 */
@Entity
public class EnterpriseCreditJson {
	@Id(autoincrement = true)
	private Long id;

	private Integer oid;// 怪异的ID
	private Integer eid;// 企业ID
	private String unit_name;// 企业名称
	private Date submit_date;// 提交时间
	private Integer status;// 状态码（1为已提交）
	private Integer type;// 企业类型
	private Integer cid;// 考核次数
	private Integer ctstatus;// 评估至审核状态（1已提交 2已考评 3已审核 4考评中 5审核不通过）
	private String qrank;// 资质等级
	private float total;// 总分
	private String annual;//年份

	@Generated(hash = 587580447)
	public EnterpriseCreditJson(Long id, Integer oid, Integer eid, String unit_name,
								Date submit_date, Integer status, Integer type, Integer cid,
								Integer ctstatus, String qrank, float total, String annual) {
					this.id = id;
					this.oid = oid;
					this.eid = eid;
					this.unit_name = unit_name;
					this.submit_date = submit_date;
					this.status = status;
					this.type = type;
					this.cid = cid;
					this.ctstatus = ctstatus;
					this.qrank = qrank;
					this.total = total;
					this.annual = annual;
	}

	@Generated(hash = 669068021)
	public EnterpriseCreditJson() {
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
	public Integer getEid() {
					return this.eid;
	}
	public void setEid(Integer eid) {
					this.eid = eid;
	}
	public String getUnit_name() {
					return this.unit_name;
	}
	public void setUnit_name(String unit_name) {
					this.unit_name = unit_name;
	}
	public Date getSubmit_date() {
					return this.submit_date;
	}
	public void setSubmit_date(Date submit_date) {
					this.submit_date = submit_date;
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
	public Integer getCid() {
					return this.cid;
	}
	public void setCid(Integer cid) {
					this.cid = cid;
	}
	public Integer getCtstatus() {
					return this.ctstatus;
	}
	public void setCtstatus(Integer ctstatus) {
					this.ctstatus = ctstatus;
	}
	public String getQrank() {
					return this.qrank;
	}
	public void setQrank(String qrank) {
					this.qrank = qrank;
	}
	public float getTotal() {
					return this.total;
	}
	public void setTotal(float total) {
					this.total = total;
	}
	public String getAnnual() {
					return this.annual;
	}
	public void setAnnual(String annual) {
					this.annual = annual;
	}
}
