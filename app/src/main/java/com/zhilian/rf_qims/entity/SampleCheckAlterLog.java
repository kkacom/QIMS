package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by colin on 2019/3/26 16:16 .
 * 样品检测新增修改内容日志记录(本来对应t_sample_check_alter_log表，后面整合到一个表是t_exception_deal)
 */
@Entity
public class SampleCheckAlterLog implements Serializable {
	private static final long serialVersionUID = 6624468101294299267L;
	@Id(autoincrement = true)
	private Long id;
	private int statu; 				//上传状态0未上传，1已上传
	private String sampleCheckId; 	//样品检测ID，样品检测本地关联查找日志

	private String field_name;		//变更字段
	private String check_num;		//原始检测值
	private String change_num;		//变更值
	private String change_reason;	//变更原因
	private String check_time;		//修改时间
	private String serial_number;	//序值（同样的检测值，是第几个）
	private String applicant;		//登录人ID
	private String sampleid;		//服务器样品检测ID，用于查找关联此日志属于哪个样品检测

	@Generated(hash = 1128326291)
	public SampleCheckAlterLog(Long id, int statu, String sampleCheckId,
			String field_name, String check_num, String change_num, String change_reason,
			String check_time, String serial_number, String applicant, String sampleid) {
		this.id = id;
		this.statu = statu;
		this.sampleCheckId = sampleCheckId;
		this.field_name = field_name;
		this.check_num = check_num;
		this.change_num = change_num;
		this.change_reason = change_reason;
		this.check_time = check_time;
		this.serial_number = serial_number;
		this.applicant = applicant;
		this.sampleid = sampleid;
	}
	@Generated(hash = 1827696052)
	public SampleCheckAlterLog() {
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getStatu() {
		return this.statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
	public String getSampleCheckId() {
		return this.sampleCheckId;
	}
	public void setSampleCheckId(String sampleCheckId) {
		this.sampleCheckId = sampleCheckId;
	}
	public String getField_name() {
		return this.field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
	public String getCheck_num() {
		return this.check_num;
	}
	public void setCheck_num(String check_num) {
		this.check_num = check_num;
	}
	public String getChange_num() {
		return this.change_num;
	}
	public void setChange_num(String change_num) {
		this.change_num = change_num;
	}
	public String getChange_reason() {
		return this.change_reason;
	}
	public void setChange_reason(String change_reason) {
		this.change_reason = change_reason;
	}
	public String getCheck_time() {
		return this.check_time;
	}
	public void setCheck_time(String check_time) {
		this.check_time = check_time;
	}
	public String getSerial_number() {
		return this.serial_number;
	}
	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}
	public String getApplicant() {
		return this.applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getSampleid() {
		return this.sampleid;
	}
	public void setSampleid(String sampleid) {
		this.sampleid = sampleid;
	}

}
