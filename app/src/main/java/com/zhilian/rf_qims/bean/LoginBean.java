package com.zhilian.rf_qims.bean;

/**
 * Created by luocong on 2017/4/17.
 */

public class LoginBean {


    /**
     * status : 0
     * msg : 用户名或者密码错误！
     */

    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
