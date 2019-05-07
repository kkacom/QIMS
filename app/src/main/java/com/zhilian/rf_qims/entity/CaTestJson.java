package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * 从业评估打分表（Json）
 * Created by YiFan on 2017/4/25.
 */
@Entity
public class CaTestJson implements Serializable{
    private static final long serialVersionUID = -1133351711246383657L;
    @Id(autoincrement = true)
    private Long ID;

    private Long CTID;//服务器返回主ID
    private int UPLOADSTATUS;//上传状态0未上传，1上传过，修改数据需再上传，2已上传

    private String CID;// 检查记录ID
    private String ITEM;// 考核项
    private String SCORE;// 得分
    private String CHOOSE;// 得分选项
    private String REMARK;// 备注
    private String STATUS;// 状态
    private String FIELD1;// 拓展字段1
    private String FIELD2;// 拓展字段2
    private String FIELD3;// 拓展字段3
    private String WREMARK;// 扣分详细

    // 1.1 1.2
    public CaTestJson(String cid, String item, String choose, String status, String field1, int upLoadStatus) {
        this.CID = cid;
        this.ITEM = item;
        this.CHOOSE = choose;
        this.STATUS = status;
        this.FIELD1 = field1;
        this.UPLOADSTATUS = upLoadStatus;
    }
    public CaTestJson(Long id, String cid, String item, String choose, String status, String field1, int upLoadStatus) {
        this.ID = id;
        this.CID = cid;
        this.ITEM = item;
        this.CHOOSE = choose;
        this.STATUS = status;
        this.FIELD1 = field1;
        this.UPLOADSTATUS = upLoadStatus;
    }

    // 1.3
    public CaTestJson(Long id, String cid, String item, String choose, String status, String field1, String field2, String field3, int upLoadStatus) {
        this.ID = id;
        this.CID = cid;
        this.ITEM = item;
        this.CHOOSE = choose;
        this.STATUS = status;
        this.FIELD1 = field1;
        this.FIELD2 = field2;
        this.FIELD3 = field3;
        this.UPLOADSTATUS = upLoadStatus;
    }
    public CaTestJson(String cid, String item, String score, String choose, String remark, String status, String field1, int upLoadStatus) {
        this.CID = cid;
        this.ITEM = item;
        this.SCORE = score;
        this.CHOOSE = choose;
        this.REMARK = remark;
        this.STATUS = status;
        this.FIELD1 = field1;
        this.UPLOADSTATUS = upLoadStatus;
    }

    public CaTestJson(Long id, String cid, String item, String score, String choose, String remark, String status, String field1, String field2, int upLoadStatus) {
        this.ID = id;
        this.CID = cid;
        this.ITEM = item;
        this.SCORE = score;
        this.CHOOSE = choose;
        this.REMARK = remark;
        this.STATUS = status;
        this.FIELD1 = field1;
        this.FIELD2 = field2;
        this.UPLOADSTATUS = upLoadStatus;
    }

    // 2.1~
    public CaTestJson(Long id, String cid, String item, String score, String choose, String remark, String status, int upLoadStatus) {
        this.ID = id;
        this.CID = cid;
        this.ITEM = item;
        this.SCORE = score;
        this.CHOOSE = choose;
        this.REMARK = remark;
        this.STATUS = status;
        this.UPLOADSTATUS = upLoadStatus;
    }
    //2.1.5~
    public CaTestJson(String cid, String item, String score, String choose, String remark, String status, int upLoadStatus) {
        this.CID = cid;
        this.ITEM = item;
        this.SCORE = score;
        this.CHOOSE = choose;
        this.REMARK = remark;
        this.STATUS = status;
        this.UPLOADSTATUS = upLoadStatus;
    }

    //2.1 ~。。。。。
    public CaTestJson(String cid, String item, String choose, String status, int upLoadStatus) {
        this.CID = cid;
        this.ITEM = item;
        this.CHOOSE = choose;
        this.STATUS = status;
        this.UPLOADSTATUS = upLoadStatus;
    }

    @Generated(hash = 83257487)
    public CaTestJson(Long ID, Long CTID, int UPLOADSTATUS, String CID, String ITEM, String SCORE, String CHOOSE, String REMARK, String STATUS,
            String FIELD1, String FIELD2, String FIELD3, String WREMARK) {
        this.ID = ID;
        this.CTID = CTID;
        this.UPLOADSTATUS = UPLOADSTATUS;
        this.CID = CID;
        this.ITEM = ITEM;
        this.SCORE = SCORE;
        this.CHOOSE = CHOOSE;
        this.REMARK = REMARK;
        this.STATUS = STATUS;
        this.FIELD1 = FIELD1;
        this.FIELD2 = FIELD2;
        this.FIELD3 = FIELD3;
        this.WREMARK = WREMARK;
    }
    @Generated(hash = 631669871)
    public CaTestJson() {
    }
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public Long getCTID() {
        return this.CTID;
    }
    public void setCTID(Long CTID) {
        this.CTID = CTID;
    }
    public int getUPLOADSTATUS() {
        return this.UPLOADSTATUS;
    }
    public void setUPLOADSTATUS(int UPLOADSTATUS) {
        this.UPLOADSTATUS = UPLOADSTATUS;
    }
    public String getCID() {
        return this.CID;
    }
    public void setCID(String CID) {
        this.CID = CID;
    }
    public String getITEM() {
        return this.ITEM;
    }
    public void setITEM(String ITEM) {
        this.ITEM = ITEM;
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
    public String getSTATUS() {
        return this.STATUS;
    }
    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
    public String getFIELD1() {
        return this.FIELD1;
    }
    public void setFIELD1(String FIELD1) {
        this.FIELD1 = FIELD1;
    }
    public String getFIELD2() {
        return this.FIELD2;
    }
    public void setFIELD2(String FIELD2) {
        this.FIELD2 = FIELD2;
    }
    public String getFIELD3() {
        return this.FIELD3;
    }
    public void setFIELD3(String FIELD3) {
        this.FIELD3 = FIELD3;
    }
    public String getWREMARK() {
        return this.WREMARK;
    }
    public void setWREMARK(String WREMARK) {
        this.WREMARK = WREMARK;
    }
}
