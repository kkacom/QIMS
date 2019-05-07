package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 从业能力现场评估表（Json）
 * Created by YiFan on 2017/4/24.
 */
@Entity
public class WorkAbilityJson {
    @Id(autoincrement = true)
    private Long ID;

    private Long WID;//服务器返回主键
    private int STATUS;//是否上传状态 0未上传，1上传过，修改过数据需再上传，2已上传

    private Integer EID;// 企业id
    private String TESTDATE;// 考核日期
    private String CONFIRMER;// 企业见证人
    private String VC;// 协会副会长
    private String CCAD;// 人防办
    private String TESTER;// 考评实操人员
    private String ASSISTANT;// 企业协助人员
    private String ADMINISTRATOR;// 企业负责人
    private String TOTAL;// 总分

    private String ITEM;// 必备条件
    private String ITEM1_1;// 企业资质
    private String ITEM1_2;// 质量体系
    private String ITEM1_3;// 环境设施
    private String ITEM1_4;// 关键人员

    private String ITEM2;// 从业能力
    private String ITEM2_1;// 关键人员
    private String ITEM2_2;// 技术人员
    private String ITEM2_3;// 生产人员
    private String ITEM2_4;// 管理制度
    private String ITEM2_5;// 场地条件
    private String ITEM2_6;// 大型设备
    private String ITEM2_7;// 加工设备
    private String ITEM2_8;// 质检工具

    private String ITEM3;// 生产管理
    private String ITEM3_1;// 经营管理
    private String ITEM3_2;// 产品质量
    private String ITEM3_3;// 安装质量

    private String ITEM4;// 安装管理

    private String WTYPE;// 企业类型
    private String WCODE;// 考核编号
    private String ENTERPRISE_NAME;// 企业名称

    public WorkAbilityJson(String TESTDATE, String CONFIRMER, String VC, String CCAD, String TESTER, String ASSISTANT, String ADMINISTRATOR,
                           String ENTERPRISE_NAME, String WCODE, String WTYPE, int STATUS, Integer EID){
        this.TESTDATE = TESTDATE;
        this.CONFIRMER = CONFIRMER;
        this.VC = VC;
        this.CCAD = CCAD;
        this.TESTER = TESTER;
        this.ASSISTANT = ASSISTANT;
        this.ADMINISTRATOR = ADMINISTRATOR;
        this.ENTERPRISE_NAME = ENTERPRISE_NAME;
        this.WCODE = WCODE;
        this.WTYPE = WTYPE;
        this.STATUS = STATUS;
        this.EID = EID;
    }

    @Generated(hash = 1869735072)
    public WorkAbilityJson(Long ID, Long WID, int STATUS, Integer EID,
            String TESTDATE, String CONFIRMER, String VC, String CCAD,
            String TESTER, String ASSISTANT, String ADMINISTRATOR, String TOTAL,
            String ITEM, String ITEM1_1, String ITEM1_2, String ITEM1_3,
            String ITEM1_4, String ITEM2, String ITEM2_1, String ITEM2_2,
            String ITEM2_3, String ITEM2_4, String ITEM2_5, String ITEM2_6,
            String ITEM2_7, String ITEM2_8, String ITEM3, String ITEM3_1,
            String ITEM3_2, String ITEM3_3, String ITEM4, String WTYPE,
            String WCODE, String ENTERPRISE_NAME) {
        this.ID = ID;
        this.WID = WID;
        this.STATUS = STATUS;
        this.EID = EID;
        this.TESTDATE = TESTDATE;
        this.CONFIRMER = CONFIRMER;
        this.VC = VC;
        this.CCAD = CCAD;
        this.TESTER = TESTER;
        this.ASSISTANT = ASSISTANT;
        this.ADMINISTRATOR = ADMINISTRATOR;
        this.TOTAL = TOTAL;
        this.ITEM = ITEM;
        this.ITEM1_1 = ITEM1_1;
        this.ITEM1_2 = ITEM1_2;
        this.ITEM1_3 = ITEM1_3;
        this.ITEM1_4 = ITEM1_4;
        this.ITEM2 = ITEM2;
        this.ITEM2_1 = ITEM2_1;
        this.ITEM2_2 = ITEM2_2;
        this.ITEM2_3 = ITEM2_3;
        this.ITEM2_4 = ITEM2_4;
        this.ITEM2_5 = ITEM2_5;
        this.ITEM2_6 = ITEM2_6;
        this.ITEM2_7 = ITEM2_7;
        this.ITEM2_8 = ITEM2_8;
        this.ITEM3 = ITEM3;
        this.ITEM3_1 = ITEM3_1;
        this.ITEM3_2 = ITEM3_2;
        this.ITEM3_3 = ITEM3_3;
        this.ITEM4 = ITEM4;
        this.WTYPE = WTYPE;
        this.WCODE = WCODE;
        this.ENTERPRISE_NAME = ENTERPRISE_NAME;
    }
    @Generated(hash = 1136331122)
    public WorkAbilityJson() {
    }
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public Long getWID() {
        return this.WID;
    }
    public void setWID(Long WID) {
        this.WID = WID;
    }
    public int getSTATUS() {
        return this.STATUS;
    }
    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }
    public Integer getEID() {
        return this.EID;
    }
    public void setEID(Integer EID) {
        this.EID = EID;
    }
    public String getTESTDATE() {
        return this.TESTDATE;
    }
    public void setTESTDATE(String TESTDATE) {
        this.TESTDATE = TESTDATE;
    }
    public String getCONFIRMER() {
        return this.CONFIRMER;
    }
    public void setCONFIRMER(String CONFIRMER) {
        this.CONFIRMER = CONFIRMER;
    }
    public String getVC() {
        return this.VC;
    }
    public void setVC(String VC) {
        this.VC = VC;
    }
    public String getCCAD() {
        return this.CCAD;
    }
    public void setCCAD(String CCAD) {
        this.CCAD = CCAD;
    }
    public String getTESTER() {
        return this.TESTER;
    }
    public void setTESTER(String TESTER) {
        this.TESTER = TESTER;
    }
    public String getASSISTANT() {
        return this.ASSISTANT;
    }
    public void setASSISTANT(String ASSISTANT) {
        this.ASSISTANT = ASSISTANT;
    }
    public String getADMINISTRATOR() {
        return this.ADMINISTRATOR;
    }
    public void setADMINISTRATOR(String ADMINISTRATOR) {
        this.ADMINISTRATOR = ADMINISTRATOR;
    }
    public String getTOTAL() {
        return this.TOTAL;
    }
    public void setTOTAL(String TOTAL) {
        this.TOTAL = TOTAL;
    }
    public String getITEM() {
        return this.ITEM;
    }
    public void setITEM(String ITEM) {
        this.ITEM = ITEM;
    }
    public String getITEM1_1() {
        return this.ITEM1_1;
    }
    public void setITEM1_1(String ITEM1_1) {
        this.ITEM1_1 = ITEM1_1;
    }
    public String getITEM1_2() {
        return this.ITEM1_2;
    }
    public void setITEM1_2(String ITEM1_2) {
        this.ITEM1_2 = ITEM1_2;
    }
    public String getITEM1_3() {
        return this.ITEM1_3;
    }
    public void setITEM1_3(String ITEM1_3) {
        this.ITEM1_3 = ITEM1_3;
    }
    public String getITEM1_4() {
        return this.ITEM1_4;
    }
    public void setITEM1_4(String ITEM1_4) {
        this.ITEM1_4 = ITEM1_4;
    }
    public String getITEM2() {
        return this.ITEM2;
    }
    public void setITEM2(String ITEM2) {
        this.ITEM2 = ITEM2;
    }
    public String getITEM2_1() {
        return this.ITEM2_1;
    }
    public void setITEM2_1(String ITEM2_1) {
        this.ITEM2_1 = ITEM2_1;
    }
    public String getITEM2_2() {
        return this.ITEM2_2;
    }
    public void setITEM2_2(String ITEM2_2) {
        this.ITEM2_2 = ITEM2_2;
    }
    public String getITEM2_3() {
        return this.ITEM2_3;
    }
    public void setITEM2_3(String ITEM2_3) {
        this.ITEM2_3 = ITEM2_3;
    }
    public String getITEM2_4() {
        return this.ITEM2_4;
    }
    public void setITEM2_4(String ITEM2_4) {
        this.ITEM2_4 = ITEM2_4;
    }
    public String getITEM2_5() {
        return this.ITEM2_5;
    }
    public void setITEM2_5(String ITEM2_5) {
        this.ITEM2_5 = ITEM2_5;
    }
    public String getITEM2_6() {
        return this.ITEM2_6;
    }
    public void setITEM2_6(String ITEM2_6) {
        this.ITEM2_6 = ITEM2_6;
    }
    public String getITEM2_7() {
        return this.ITEM2_7;
    }
    public void setITEM2_7(String ITEM2_7) {
        this.ITEM2_7 = ITEM2_7;
    }
    public String getITEM2_8() {
        return this.ITEM2_8;
    }
    public void setITEM2_8(String ITEM2_8) {
        this.ITEM2_8 = ITEM2_8;
    }
    public String getITEM3() {
        return this.ITEM3;
    }
    public void setITEM3(String ITEM3) {
        this.ITEM3 = ITEM3;
    }
    public String getITEM3_1() {
        return this.ITEM3_1;
    }
    public void setITEM3_1(String ITEM3_1) {
        this.ITEM3_1 = ITEM3_1;
    }
    public String getITEM3_2() {
        return this.ITEM3_2;
    }
    public void setITEM3_2(String ITEM3_2) {
        this.ITEM3_2 = ITEM3_2;
    }
    public String getITEM3_3() {
        return this.ITEM3_3;
    }
    public void setITEM3_3(String ITEM3_3) {
        this.ITEM3_3 = ITEM3_3;
    }
    public String getITEM4() {
        return this.ITEM4;
    }
    public void setITEM4(String ITEM4) {
        this.ITEM4 = ITEM4;
    }
    public String getWTYPE() {
        return this.WTYPE;
    }
    public void setWTYPE(String WTYPE) {
        this.WTYPE = WTYPE;
    }
    public String getWCODE() {
        return this.WCODE;
    }
    public void setWCODE(String WCODE) {
        this.WCODE = WCODE;
    }
    public String getENTERPRISE_NAME() {
        return this.ENTERPRISE_NAME;
    }
    public void setENTERPRISE_NAME(String ENTERPRISE_NAME) {
        this.ENTERPRISE_NAME = ENTERPRISE_NAME;
    }

}
