package com.zhilian.rf_qims.entity;

import java.util.List;

/**
 * 从业评估所有表（List）
 * Created by YiFan on 2017/4/26.
 */
public class AbilityPackage {
	private List<CaConfigJson> caConfigList;// 配置表
	private List<EntEquipmentJson> entEquipmentList;// 设备表
	private List<EnterpriseInfoJson> enterpriseInfoList;// 企业名单表
	private List<PersonnelJson> personnelList;// 人员表
	private List<WorkUserJson> userList;// 用户表
	private List<WorkAbilityJson> workAbilityList;// 现场评估表

	public List<CaConfigJson> getCaConfigList() {
		return caConfigList;
	}

	public void setCaConfigList(List<CaConfigJson> caConfigList) {
		this.caConfigList = caConfigList;
	}

	public List<EntEquipmentJson> getEntEquipmentList() {
		return entEquipmentList;
	}

	public void setEntEquipmentList(List<EntEquipmentJson> entEquipmentList) {
		this.entEquipmentList = entEquipmentList;
	}

	public List<EnterpriseInfoJson> getEnterpriseInfoList() {
		return enterpriseInfoList;
	}

	public void setEnterpriseInfoList(List<EnterpriseInfoJson> enterpriseInfoList) {
		this.enterpriseInfoList = enterpriseInfoList;
	}

	public List<PersonnelJson> getPersonnelList() {
		return personnelList;
	}

	public void setPersonnelList(List<PersonnelJson> personnelList) {
		this.personnelList = personnelList;
	}

	public List<WorkUserJson> getUserList() {
		return userList;
	}

	public void setUserList(List<WorkUserJson> userList) {
		this.userList = userList;
	}

	public List<WorkAbilityJson> getWorkAbilityList() {
		return workAbilityList;
	}

	public void setWorkAbilityList(List<WorkAbilityJson> workAbilityList) {
		this.workAbilityList = workAbilityList;
	}
}
