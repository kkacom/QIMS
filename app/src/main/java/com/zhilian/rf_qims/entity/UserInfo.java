package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-9-23.
 */
@Entity
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 564564;
    @Id
    private long id;
    private int userId;
    private String loginName;//登陆名
    private String realName;//真实名(移植湖南项目要求，暂放登录ID)
    private String accessToken;
    private String pwd;//(移植湖南项目要求，暂放本地自动生成的加密密码)
    private String status;
    private String limit;
    @Generated(hash = 868799592)
    public UserInfo(long id, int userId, String loginName, String realName,
            String accessToken, String pwd, String status, String limit) {
        this.id = id;
        this.userId = userId;
        this.loginName = loginName;
        this.realName = realName;
        this.accessToken = accessToken;
        this.pwd = pwd;
        this.status = status;
        this.limit = limit;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getLoginName() {
        return this.loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getRealName() {
        return this.realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getAccessToken() {
        return this.accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getPwd() {
        return this.pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getLimit() {
        return this.limit;
    }
    public void setLimit(String limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
            "id=" + id +
            ", userId=" + userId +
            ", loginName='" + loginName + '\'' +
            ", realName='" + realName + '\'' +
            ", accessToken='" + accessToken + '\'' +
            ", pwd='" + pwd + '\'' +
            ", status='" + status + '\'' +
            ", limit='" + limit + '\'' +
            '}';
    }
}
