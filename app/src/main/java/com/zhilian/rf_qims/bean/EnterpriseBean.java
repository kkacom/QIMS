package com.zhilian.rf_qims.bean;

/**
 * Created by luocong on 2017/3/30.
 */

public class EnterpriseBean {
    private String number;
    private String EnterPriseName;
    private String contact;
    private String contactPhone;
    private String EnterPriseAddress;
    private String relatedWebsite;

    public EnterpriseBean(String number, String enterPriseName, String contact, String contactPhone, String enterPriseAddress, String relatedWebsite) {
        this.number = number;
        this.EnterPriseName = enterPriseName;
        this.contact = contact;
        this.contactPhone = contactPhone;
        this.EnterPriseAddress = enterPriseAddress;
        this.relatedWebsite = relatedWebsite;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEnterPriseName() {
        return EnterPriseName;
    }

    public void setEnterPriseName(String enterPriseName) {
        EnterPriseName = enterPriseName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getEnterPriseAddress() {
        return EnterPriseAddress;
    }

    public void setEnterPriseAddress(String enterPriseAddress) {
        EnterPriseAddress = enterPriseAddress;
    }

    public String getRelatedWebsite() {
        return relatedWebsite;
    }

    public void setRelatedWebsite(String relatedWebsite) {
        this.relatedWebsite = relatedWebsite;
    }
}
