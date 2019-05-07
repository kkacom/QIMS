package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 从业评估配置表（Json）
 * Created by YiFan on 2017/4/24.
 */
@Entity
public class CaConfigJson {
    @Id(autoincrement = true)
    private Long ID;

    private String SN;// 序号
    private String TITLE;// 标题
    private String SCORE;// 分值
    private Integer NUMBER;// 数量
    private String EACH;// 单计
    private String MEMO;// 计分规则
    private String NAME;// 名称
    private Integer TYPE;// 条目类型
    private Integer WETYPE;// 人防设备类别
    private Integer LEVEL;// 条目级别
    private Integer VIEW_TYPE;// 界面加载类别（2.？）
    private Integer COMMON_TYPE;// 人员和设备区分类别（加载界面）
    @Generated(hash = 342336107)
    public CaConfigJson(Long ID, String SN, String TITLE, String SCORE,
            Integer NUMBER, String EACH, String MEMO, String NAME, Integer TYPE,
            Integer WETYPE, Integer LEVEL, Integer VIEW_TYPE, Integer COMMON_TYPE) {
        this.ID = ID;
        this.SN = SN;
        this.TITLE = TITLE;
        this.SCORE = SCORE;
        this.NUMBER = NUMBER;
        this.EACH = EACH;
        this.MEMO = MEMO;
        this.NAME = NAME;
        this.TYPE = TYPE;
        this.WETYPE = WETYPE;
        this.LEVEL = LEVEL;
        this.VIEW_TYPE = VIEW_TYPE;
        this.COMMON_TYPE = COMMON_TYPE;
    }
    @Generated(hash = 1592407972)
    public CaConfigJson() {
    }
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public String getSN() {
        return this.SN;
    }
    public void setSN(String SN) {
        this.SN = SN;
    }
    public String getTITLE() {
        return this.TITLE;
    }
    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }
    public String getSCORE() {
        return this.SCORE;
    }
    public void setSCORE(String SCORE) {
        this.SCORE = SCORE;
    }
    public Integer getNUMBER() {
        return this.NUMBER;
    }
    public void setNUMBER(Integer NUMBER) {
        this.NUMBER = NUMBER;
    }
    public String getEACH() {
        return this.EACH;
    }
    public void setEACH(String EACH) {
        this.EACH = EACH;
    }
    public String getMEMO() {
        return this.MEMO;
    }
    public void setMEMO(String MEMO) {
        this.MEMO = MEMO;
    }
    public String getNAME() {
        return this.NAME;
    }
    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
    public Integer getTYPE() {
        return this.TYPE;
    }
    public void setTYPE(Integer TYPE) {
        this.TYPE = TYPE;
    }
    public Integer getWETYPE() {
        return this.WETYPE;
    }
    public void setWETYPE(Integer WETYPE) {
        this.WETYPE = WETYPE;
    }
    public Integer getLEVEL() {
        return this.LEVEL;
    }
    public void setLEVEL(Integer LEVEL) {
        this.LEVEL = LEVEL;
    }
    public Integer getVIEW_TYPE() {
        return this.VIEW_TYPE;
    }
    public void setVIEW_TYPE(Integer VIEW_TYPE) {
        this.VIEW_TYPE = VIEW_TYPE;
    }
    public Integer getCOMMON_TYPE() {
        return this.COMMON_TYPE;
    }
    public void setCOMMON_TYPE(Integer COMMON_TYPE) {
        this.COMMON_TYPE = COMMON_TYPE;
    }

}
