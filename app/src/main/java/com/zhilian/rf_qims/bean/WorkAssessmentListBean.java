package com.zhilian.rf_qims.bean;

/**
 * 从业评估（企业名单列表）实体类
 */
public class WorkAssessmentListBean {
    private String companyName;// 公司名称
    private String number;// 编号
    private String city;// 地市
    private String contacts;// 联系人
    private String phone;// 联系电话
    private String companyAddress;// 企业地址
    private String networkMap;// 网络导航

    public WorkAssessmentListBean() {
    }

    public WorkAssessmentListBean(String companyName, String number, String city,
								  String contacts, String phone, String companyAddress,
								  String networkMap) {
        this.companyName = companyName;
        this.number = number;
        this.city = city;
        this.contacts = contacts;
        this.phone = phone;
        this.companyAddress = companyAddress;
        this.networkMap = networkMap;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getNetworkMap() {
        return networkMap;
    }

    public void setNetworkMap(String networkMap) {
        this.networkMap = networkMap;
    }
}
