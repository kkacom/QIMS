package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/22.
 */
@Entity
public class SignPic implements Serializable{
    private static final long serialVersionUID = -7088263319533233146L;
    @Id(autoincrement = true)
    private Long id;//ID
    private String path;
    private int isupload;//0已上传 1未上传
    private Long sid;//样品id

    @Generated(hash = 706885637)
    public SignPic(Long id, String path, int isupload, Long sid) {
        this.id = id;
        this.path = path;
        this.isupload = isupload;
        this.sid = sid;
    }

    @Generated(hash = 1889774118)
    public SignPic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getIsupload() {
        return isupload;
    }

    public void setIsupload(int isupload) {
        this.isupload = isupload;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }
}
