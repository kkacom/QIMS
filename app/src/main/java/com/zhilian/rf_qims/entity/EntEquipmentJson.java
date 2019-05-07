package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * 从业能力设备表（Json）
 * Created by YiFan on 2017/4/25.
 */
@Entity
public class EntEquipmentJson implements Serializable{
    private static final long serialVersionUID = 4837824442585208518L;
    @Id(autoincrement = true)
    private Long ID;

    private int UPLOADSTATUS;// 上传状态，0未上传，1已上传，需再次上传，2已上传

    private Integer EID;// 企业ID
    private String CID;// 检查记录ID
    private String ETYPE;// 设备类型
    private String ENAME;// 设备名称
    private String ECODE;// 设备编号
    private String EMODEL;// 设备型号
    private String SCORE;// 得分
    private String CHOOSE;// 得分选项
    private String REMARK;// 备注
    private String FIELD;// 文本数据存这里，如：起吊高度，吨位

    //创建设备
    public EntEquipmentJson(int eid, String cid, String eType, String eName, String eCode, String eModel, int uploadStatus){
        this.EID = eid;
        this.CID = cid;
        this.ETYPE = eType;
        this.ENAME = eName;
        this.ECODE = eCode;
        this.EMODEL = eModel;
        this.UPLOADSTATUS = uploadStatus;
    }

    //创建设备+打分
    public EntEquipmentJson(int eid, String cid, String eType, String eName, String eCode, String eModel,
                            String score, String choose, String remark, int uploadStatus){
        this.EID = eid;
        this.CID = cid;
        this.ETYPE = eType;
        this.ENAME = eName;
        this.ECODE = eCode;
        this.EMODEL = eModel;
        this.SCORE = score;
        this.CHOOSE = choose;
        this.REMARK = remark;
        this.UPLOADSTATUS = uploadStatus;
    }

    public EntEquipmentJson(Long id, int eid, String cid, String eType, String eName, String eCode, String eModel,
                            String score, String choose, String remark, int uploadStatus){
        this.ID = id;
        this.EID = eid;
        this.CID = cid;
        this.ETYPE = eType;
        this.ENAME = eName;
        this.ECODE = eCode;
        this.EMODEL = eModel;
        this.SCORE = score;
        this.CHOOSE = choose;
        this.REMARK = remark;
        this.UPLOADSTATUS = uploadStatus;
    }

    public EntEquipmentJson(Long id, int eid, String cid, String eType, String eName, String eCode, String eModel,
                                String score, String choose, String remark, String field, int uploadStatus){
        this.ID = id;
        this.EID = eid;
        this.CID = cid;
        this.ETYPE = eType;
        this.ENAME = eName;
        this.ECODE = eCode;
        this.EMODEL = eModel;
        this.SCORE = score;
        this.CHOOSE = choose;
        this.REMARK = remark;
        this.FIELD = field;
        this.UPLOADSTATUS = uploadStatus;
    }

    @Generated(hash = 1623907905)
    public EntEquipmentJson(Long ID, int UPLOADSTATUS, Integer EID, String CID,
            String ETYPE, String ENAME, String ECODE, String EMODEL, String SCORE,
            String CHOOSE, String REMARK, String FIELD) {
        this.ID = ID;
        this.UPLOADSTATUS = UPLOADSTATUS;
        this.EID = EID;
        this.CID = CID;
        this.ETYPE = ETYPE;
        this.ENAME = ENAME;
        this.ECODE = ECODE;
        this.EMODEL = EMODEL;
        this.SCORE = SCORE;
        this.CHOOSE = CHOOSE;
        this.REMARK = REMARK;
        this.FIELD = FIELD;
    }
    @Generated(hash = 1394725443)
    public EntEquipmentJson() {
    }
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public int getUPLOADSTATUS() {
        return this.UPLOADSTATUS;
    }
    public void setUPLOADSTATUS(int UPLOADSTATUS) {
        this.UPLOADSTATUS = UPLOADSTATUS;
    }
    public Integer getEID() {
        return this.EID;
    }
    public void setEID(Integer EID) {
        this.EID = EID;
    }
    public String getCID() {
        return this.CID;
    }
    public void setCID(String CID) {
        this.CID = CID;
    }
    public String getETYPE() {
        return this.ETYPE;
    }
    public void setETYPE(String ETYPE) {
        this.ETYPE = ETYPE;
    }
    public String getENAME() {
        return this.ENAME;
    }
    public void setENAME(String ENAME) {
        this.ENAME = ENAME;
    }
    public String getECODE() {
        return this.ECODE;
    }
    public void setECODE(String ECODE) {
        this.ECODE = ECODE;
    }
    public String getEMODEL() {
        return this.EMODEL;
    }
    public void setEMODEL(String EMODEL) {
        this.EMODEL = EMODEL;
    }
    public String getSCORE() {
        return this.SCORE;
    }
    public void setSCORE(String SCORE) {
        this.SCORE = SCORE;
    }
    public String getCHOOSE() {
        return this.CHOOSE;
    }
    public void setCHOOSE(String CHOOSE) {
        this.CHOOSE = CHOOSE;
    }
    public String getREMARK() {
        return this.REMARK;
    }
    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }
    public String getFIELD() {
        return this.FIELD;
    }
    public void setFIELD(String FIELD) {
        this.FIELD = FIELD;
    }

}
