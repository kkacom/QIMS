package com.zhilian.rf_qims.bean;

/**
 * 从业评估（从业能力现场评估表）实体类
 */
public class WorkAssessmentFieldBean {
    private String companyName;// 公司名称
    private String assessmentNumber;// 考核编号
    private String totalScore;// 总分
    private String assessmentDate;// 考核日期
    private String witnessesPerson;// 企业见证人
    private String assistPerson;// 企业协助人员
    private String responsiblePerson;// 企业负责人
    private String association;// 协会
    private String defenseOffice;// 人防办
    private String operationPerson;// 考评实操人员

    public WorkAssessmentFieldBean() {
    }

    public WorkAssessmentFieldBean(String companyName, String assessmentNumber,
								   String totalScore, String assessmentDate, String witnessesPerson,
								   String assistPerson, String responsiblePerson, String association,
								   String defenseOffice, String operationPerson) {
        this.companyName = companyName;
        this.assessmentNumber = assessmentNumber;
        this.totalScore = totalScore;
        this.assessmentDate = assessmentDate;
        this.witnessesPerson = witnessesPerson;
        this.assistPerson = assistPerson;
        this.responsiblePerson = responsiblePerson;
        this.association = association;
        this.defenseOffice = defenseOffice;
        this.operationPerson = operationPerson;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAssessmentNumber() {
        return assessmentNumber;
    }

    public void setAssessmentNumber(String assessmentNumber) {
        this.assessmentNumber = assessmentNumber;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(String assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public String getWitnessesPerson() {
        return witnessesPerson;
    }

    public void setWitnessesPerson(String witnessesPerson) {
        this.witnessesPerson = witnessesPerson;
    }

    public String getAssistPerson() {
        return assistPerson;
    }

    public void setAssistPerson(String assistPerson) {
        this.assistPerson = assistPerson;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public String getDefenseOffice() {
        return defenseOffice;
    }

    public void setDefenseOffice(String defenseOffice) {
        this.defenseOffice = defenseOffice;
    }

    public String getOperationPerson() {
        return operationPerson;
    }

    public void setOperationPerson(String operationPerson) {
        this.operationPerson = operationPerson;
    }
}
