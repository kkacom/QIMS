package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;
import java.nio.channels.WritePendingException;

/**
 * 从业评估人员表（Json）
 * Created by YiFan on 2017/4/25.
 */
@Entity

public class PersonnelJson implements Serializable{
    private static final long serialVersionUID = 8479561338844338012L;
    @Id(autoincrement = true)
    private Long ID;

    private int UPLOADSTATUS;//上传状态，0未上传，1已上传，修改数据需再次上传，2已上传不需上传

    private Integer EID;// 企业ID
    private Integer CID;// 检查记录ID
    private String POST_TYPE;// 人员类别
    private String PERNAME;// 姓名
    private String IDNUMBER;// 身份证
    private String CHOOSE;// 个人选项
    private String SCORE;// 个人得分
    private String STATUS;// 状态
    private String REMARK;// 备注
    private String PHONE;// 手机号
    private String HIREDATE;// 入职时间
    private String PROFESSIONAL;// 专业
    private Integer WORK_TYPE;//工作类别（区别加载界面）
    // 1.4
    public PersonnelJson(Long ID, Integer EID, Integer CID, String POST_TYPE, String PERNAME, String CHOOSE, String SCORE, String PROFESSIONAL, int UPLOADSTATUS) {
        this.ID = ID;
        this.EID = EID;
        this.CID = CID;
        this.POST_TYPE = POST_TYPE;
        this.PERNAME = PERNAME;
        this.CHOOSE = CHOOSE;
        this.SCORE = SCORE;
        this.PROFESSIONAL = PROFESSIONAL;
        this.UPLOADSTATUS = UPLOADSTATUS;
    }

    // 创建人员
    public PersonnelJson(Integer EID, Integer CID, String POST_TYPE, String PERNAME, String IDNUMBER, String PHONE, String HIREDATE, Integer WORK_TYPE, int UPLOADSTATUS) {
        this.EID = EID;//企业ID
        this.CID = CID;//服务器项目的主id（本地Wid）
        this.POST_TYPE = POST_TYPE;//item
        this.PERNAME = PERNAME;//姓名
        this.IDNUMBER = IDNUMBER;//身份证
        this.PHONE = PHONE;//手机
        this.HIREDATE = HIREDATE;//入职时间
        this.WORK_TYPE = WORK_TYPE;//加载界面标记
        this.UPLOADSTATUS = UPLOADSTATUS;//上传状态
    }

    // 2.1
    public PersonnelJson(Integer EID, Integer CID, String POST_TYPE, String PERNAME, String IDNUMBER, String CHOOSE, String SCORE, String REMARK, String PHONE, String HIREDATE, int UPLOADSTATUS) {
        this.EID = EID;
        this.CID = CID;
        this.POST_TYPE = POST_TYPE;
        this.PERNAME = PERNAME;
        this.IDNUMBER = IDNUMBER;
        this.CHOOSE = CHOOSE;
        this.SCORE = SCORE;
        this.REMARK = REMARK;
        this.PHONE = PHONE;
        this.HIREDATE = HIREDATE;
        this.UPLOADSTATUS = UPLOADSTATUS;
    }

    // 2.1
    public PersonnelJson(Long ID, Integer EID, Integer CID, String POST_TYPE, String PERNAME, String IDNUMBER, String CHOOSE, String SCORE, String REMARK, String PHONE, String HIREDATE, int UPLOADSTATUS) {
        this.ID = ID;
        this.EID = EID;
        this.CID = CID;
        this.POST_TYPE = POST_TYPE;
        this.PERNAME = PERNAME;
        this.IDNUMBER = IDNUMBER;
        this.CHOOSE = CHOOSE;
        this.SCORE = SCORE;
        this.REMARK = REMARK;
        this.PHONE = PHONE;
        this.HIREDATE = HIREDATE;
        this.UPLOADSTATUS = UPLOADSTATUS;
    }

    public PersonnelJson(Long ID, Integer EID, Integer CID, String POST_TYPE, String PERNAME, String IDNUMBER, String CHOOSE, String SCORE, String STATUS, String REMARK, String PHONE, String HIREDATE, int UPLOADSTATUS, int WORK_TYPE) {
        this.ID = ID;
        this.EID = EID;
        this.CID = CID;
        this.POST_TYPE = POST_TYPE;
        this.PERNAME = PERNAME;
        this.IDNUMBER = IDNUMBER;
        this.CHOOSE = CHOOSE;
        this.SCORE = SCORE;
        this.STATUS = STATUS;
        this.REMARK = REMARK;
        this.PHONE = PHONE;
        this.HIREDATE = HIREDATE;
        this.UPLOADSTATUS = UPLOADSTATUS;
        this.WORK_TYPE = WORK_TYPE;
    }

    @Generated(hash = 71158730)
    public PersonnelJson(Long ID, int UPLOADSTATUS, Integer EID, Integer CID, String POST_TYPE, String PERNAME, String IDNUMBER, String CHOOSE, String SCORE, String STATUS, String REMARK, String PHONE, String HIREDATE,
            String PROFESSIONAL, Integer WORK_TYPE) {
        this.ID = ID;
        this.UPLOADSTATUS = UPLOADSTATUS;
        this.EID = EID;
        this.CID = CID;
        this.POST_TYPE = POST_TYPE;
        this.PERNAME = PERNAME;
        this.IDNUMBER = IDNUMBER;
        this.CHOOSE = CHOOSE;
        this.SCORE = SCORE;
        this.STATUS = STATUS;
        this.REMARK = REMARK;
        this.PHONE = PHONE;
        this.HIREDATE = HIREDATE;
        this.PROFESSIONAL = PROFESSIONAL;
        this.WORK_TYPE = WORK_TYPE;
    }

    @Generated(hash = 1025676004)
    public PersonnelJson() {
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

    public Integer getCID() {
        return this.CID;
    }

    public void setCID(Integer CID) {
        this.CID = CID;
    }

    public String getPOST_TYPE() {
        return this.POST_TYPE;
    }

    public void setPOST_TYPE(String POST_TYPE) {
        this.POST_TYPE = POST_TYPE;
    }

    public String getPERNAME() {
        return this.PERNAME;
    }

    public void setPERNAME(String PERNAME) {
        this.PERNAME = PERNAME;
    }

    public String getIDNUMBER() {
        return this.IDNUMBER;
    }

    public void setIDNUMBER(String IDNUMBER) {
        this.IDNUMBER = IDNUMBER;
    }

    public String getCHOOSE() {
        return this.CHOOSE;
    }

    public void setCHOOSE(String CHOOSE) {
        this.CHOOSE = CHOOSE;
    }

    public String getSCORE() {
        return this.SCORE;
    }

    public void setSCORE(String SCORE) {
        this.SCORE = SCORE;
    }

    public String getSTATUS() {
        return this.STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getREMARK() {
        return this.REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getPHONE() {
        return this.PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getHIREDATE() {
        return this.HIREDATE;
    }

    public void setHIREDATE(String HIREDATE) {
        this.HIREDATE = HIREDATE;
    }

    public String getPROFESSIONAL() {
        return this.PROFESSIONAL;
    }

    public void setPROFESSIONAL(String PROFESSIONAL) {
        this.PROFESSIONAL = PROFESSIONAL;
    }

    public Integer getWORK_TYPE() {
        return this.WORK_TYPE;
    }

    public void setWORK_TYPE(Integer WORK_TYPE) {
        this.WORK_TYPE = WORK_TYPE;
    }

}
