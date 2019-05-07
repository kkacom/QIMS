package com.zhilian.rf_qims.entity;


/**
 * 考核标准详情
 * @author Administrator
 *
 */
public class RulesDetailsJson {
	
	private String sn;//序号
	private Integer id;//id
	private Float auditMark;//得分
	private Double mark;//分值
	private String remark;//得分备注
	private String memo;//条目
	private Integer markType;//分值类型
	private Float selfMark;//自评得分
	private String eType;//企业类型
	private Integer eid;//企业id
	private Integer cid;//考核次数
	
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String geteType() {
		return eType;
	}
	public void seteType(String eType) {
		this.eType = eType;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Float getAuditMark() {
		return auditMark;
	}
	public void setAuditMark(Float auditMark) {
		this.auditMark = auditMark;
	}
	public Double getMark() {
		return mark;
	}
	public void setMark(Double mark) {
		this.mark = mark;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Integer getMarkType() {
		return markType;
	}
	public void setMarkType(Integer markType) {
		this.markType = markType;
	}
	public Float getSelfMark() {
		return selfMark;
	}
	public void setSelfMark(Float selfMark) {
		this.selfMark = selfMark;
	}
	
	
	
	
}
