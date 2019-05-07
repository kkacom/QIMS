package com.zhilian.rf_qims.entity;

/**
 * 考核标准一级目录
 * @author Administrator
 *
 */
public class RulesCategoryJson {

	private String sn;//序号
	private String category;//考核行为类别
	private String eType;//企业类型
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String geteType() {
		return eType;
	}
	public void seteType(String eType) {
		this.eType = eType;
	}
	
	
}
