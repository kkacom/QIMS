package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 从业评估用户表（Json）
 * Created by YiFan on 2017/4/25.
 */
@Entity
public class WorkUserJson {
    @Id(autoincrement = true)
    private Long ID;

    private String NAME;// 姓名
    private String DLID;// 登录名
    private String PWD;// 密码
    @Generated(hash = 137784506)
    public WorkUserJson(Long ID, String NAME, String DLID, String PWD) {
        this.ID = ID;
        this.NAME = NAME;
        this.DLID = DLID;
        this.PWD = PWD;
    }
    @Generated(hash = 143800990)
    public WorkUserJson() {
    }
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public String getNAME() {
        return this.NAME;
    }
    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
    public String getDLID() {
        return this.DLID;
    }
    public void setDLID(String DLID) {
        this.DLID = DLID;
    }
    public String getPWD() {
        return this.PWD;
    }
    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

}
