package com.zhilian.rf_qims.bean;


/**
 * Created by sl on 2017-10-20.
 */

public class SampleBean{

	/**
	 * REQUIREMENTDATE : 安装日期
	 * RID : 306
	 * STATUS : 样品状态
	 * HANDLEDESCRIPTION : 型号
	 * USEPART : 使用部位
	 * NUMBER : 样品编号：RFXM17-0002
	 * PRODUCTIONDATE : 制作日期
	 * ID : 1050
	 * SPECIFICATION : 说明
	 * QUANTITY : 同类数量
	 * TYPE : XM
	 */

	private String REQUIREMENTDATE;// 安装日期
	private long RID;// 项目ID
	private String STATUS;//样品状态
	private String HANDLEDESCRIPTION;
	private String USEPART;//使用部位
	private String NUMBER;//样品编号
	private String PRODUCTIONDATE;//制作日期
	private long ID;//样品ID
	private String SPECIFICATION;//说明
	private String QUANTITY;//同类数量
	private String TYPE;//XM
	private String SMODE;//型号

	public String getREQUIREMENTDATE() {
		return REQUIREMENTDATE;
	}

	public void setREQUIREMENTDATE(String REQUIREMENTDATE) {
		this.REQUIREMENTDATE = REQUIREMENTDATE;
	}

	public long getRID() {
		return RID;
	}

	public void setRID(long RID) {
		this.RID = RID;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String STATUS) {
		this.STATUS = STATUS;
	}

	public String getHANDLEDESCRIPTION() {
		return HANDLEDESCRIPTION;
	}

	public void setHANDLEDESCRIPTION(String HANDLEDESCRIPTION) {
		this.HANDLEDESCRIPTION = HANDLEDESCRIPTION;
	}

	public String getUSEPART() {
		return USEPART;
	}

	public void setUSEPART(String USEPART) {
		this.USEPART = USEPART;
	}

	public String getNUMBER() {
		return NUMBER;
	}

	public void setNUMBER(String NUMBER) {
		this.NUMBER = NUMBER;
	}

	public String getPRODUCTIONDATE() {
		return PRODUCTIONDATE;
	}

	public void setPRODUCTIONDATE(String PRODUCTIONDATE) {
		this.PRODUCTIONDATE = PRODUCTIONDATE;
	}

	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public String getSPECIFICATION() {
		return SPECIFICATION;
	}

	public void setSPECIFICATION(String SPECIFICATION) {
		this.SPECIFICATION = SPECIFICATION;
	}

	public String getQUANTITY() {
		return QUANTITY;
	}

	public void setQUANTITY(String QUANTITY) {
		this.QUANTITY = QUANTITY;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String TYPE) {
		this.TYPE = TYPE;
	}

	public String getSMODE() {
		return SMODE;
	}

	public void setSMODE(String SMODE) {
		this.SMODE = SMODE;
	}

	@Override
	public String toString() {
		return "SampleBean{" +
			"REQUIREMENTDATE='" + REQUIREMENTDATE + '\'' +
			", RID=" + RID +
			", STATUS='" + STATUS + '\'' +
			", HANDLEDESCRIPTION='" + HANDLEDESCRIPTION + '\'' +
			", USEPART='" + USEPART + '\'' +
			", NUMBER='" + NUMBER + '\'' +
			", PRODUCTIONDATE='" + PRODUCTIONDATE + '\'' +
			", ID=" + ID +
			", SPECIFICATION='" + SPECIFICATION + '\'' +
			", QUANTITY='" + QUANTITY + '\'' +
			", TYPE='" + TYPE + '\'' +
			", SMODE='" + SMODE + '\'' +
			'}';
	}
}
