package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/16.
 */
@Entity
public class CheckUser implements Serializable {

    private static final long serialVersionUID = -422695690133606014L;
    /**
     * NAME : 唐甸华
     * ID : 19
     */

    private String NAME;
    @Id
    private Long ID;

    @Generated(hash = 375229022)
    public CheckUser(String NAME, Long ID) {
        this.NAME = NAME;
        this.ID = ID;
    }

    @Generated(hash = 1065819587)
    public CheckUser() {
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}
