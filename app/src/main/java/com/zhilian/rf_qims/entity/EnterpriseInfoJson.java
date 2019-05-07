package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 企业名单列表（Json）
 * Created by YiFan on 2017/4/24.
 */
@Entity
public class EnterpriseInfoJson {
    @Id(autoincrement = true)
    private Long id;

    private String number;// 企业编号
    private String name;// 企业（机构）名称
    private String f_name;// 联系人（法人）
    private String f_te;// 联系电话（法人）
    private String address;// 企业地址

    @Generated(hash = 1548058137)
    public EnterpriseInfoJson(Long id, String number, String name, String f_name,
							  String f_te, String address) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.f_name = f_name;
        this.f_te = f_te;
        this.address = address;
    }

    @Generated(hash = 750736773)
    public EnterpriseInfoJson() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getF_name() {
        return this.f_name;
    }
    public void setF_name(String f_name) {
        this.f_name = f_name;
    }
    public String getF_te() {
        return this.f_te;
    }
    public void setF_te(String f_te) {
        this.f_te = f_te;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
