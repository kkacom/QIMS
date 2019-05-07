package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/28 0028.
 */

@Entity
public class SamplePage implements Serializable {
    private static final long serialVersionUID = -6870593010357845897L;
    @Id
    private long id;
    private int pid;
    private int samplepage;

    @Override
    public String toString() {
        return "SamplePage{" +
                "id=" + id +
                ", pid=" + pid +
                ", samplepage=" + samplepage +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getSamplepage() {
        return samplepage;
    }

    public void setSamplepage(int samplepage) {
        this.samplepage = samplepage;
    }


    @Generated(hash = 1058837252)
    public SamplePage(long id, int pid, int samplepage) {
        this.id = id;
        this.pid = pid;
        this.samplepage = samplepage;
    }

    @Generated(hash = 965434204)
    public SamplePage() {
    }
}
