package com.zhilian.rf_qims.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018-1-4.
 */

public class TodoItemBean implements Serializable {
    private static final long serialVersionUID = 579187196161339338L;
    /**
     * active : 申请
     * approvedate : 2018-02-03
     * dayt : 1.0
     * docid : 71
     * name : 林小峰
     * pid : 1522
     * starttime : 2018-02-03 09:27:20
     * tempcolumn : 0
     * temprownumber : 1
     * type : null
     * unit : 防办领导
     */

    private String active;
    private String approvedate;
    private String dayt;
    private String docid;
    private String name;
    private String pid;
    private String starttime;
    private int tempcolumn;
    private int temprownumber;
    private Object type;
    private String unit;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getApprovedate() {
        return approvedate;
    }

    public void setApprovedate(String approvedate) {
        this.approvedate = approvedate;
    }

    public String getDayt() {
        return dayt;
    }

    public void setDayt(String dayt) {
        this.dayt = dayt;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public int getTempcolumn() {
        return tempcolumn;
    }

    public void setTempcolumn(int tempcolumn) {
        this.tempcolumn = tempcolumn;
    }

    public int getTemprownumber() {
        return temprownumber;
    }

    public void setTemprownumber(int temprownumber) {
        this.temprownumber = temprownumber;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
