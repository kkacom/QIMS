package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 诚信评估用户表（Json）
 * Created by YiFan on 2017/5/24.
 */
@Entity
public class IntegrityUserJson {
    @Id(autoincrement = true)
    private Long id;

    private String name;// 姓名
    private String dlid;// 登录名
    private String pwd1;// 密码（自己生成的）
    private String pwd2;// 密码（保存后台的）

    @Generated(hash = 205464294)
    public IntegrityUserJson(Long id, String name, String dlid, String pwd1,
							 String pwd2) {
        this.id = id;
        this.name = name;
        this.dlid = dlid;
        this.pwd1 = pwd1;
        this.pwd2 = pwd2;
    }

    @Generated(hash = 2115371466)
    public IntegrityUserJson() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDlid() {
        return this.dlid;
    }
    public void setDlid(String dlid) {
        this.dlid = dlid;
    }
    public String getPwd1() {
        return this.pwd1;
    }
    public void setPwd1(String pwd1) {
        this.pwd1 = pwd1;
    }
    public String getPwd2() {
        return this.pwd2;
    }
    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }
}
