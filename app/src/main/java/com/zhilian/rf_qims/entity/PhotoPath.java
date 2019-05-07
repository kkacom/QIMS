package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by colin on 2018/12/1 18:29 .
 */
@Entity
public class PhotoPath implements Serializable{
	private static final long serialVersionUID = -4922932665758834795L;

	@Id(autoincrement = true)
	private Long id;

	private String status;//1完成，2未完成
	private int uploadStatus;//0未上传，1已上传需再上传，2已上传

	private String type;//类型
	private String relid;//对象id
	private String photo;//图片名
	private String photoPath;//图片路劲
	@Generated(hash = 766601725)
	public PhotoPath(Long id, String status, int uploadStatus, String type,
			String relid, String photo, String photoPath) {
		this.id = id;
		this.status = status;
		this.uploadStatus = uploadStatus;
		this.type = type;
		this.relid = relid;
		this.photo = photo;
		this.photoPath = photoPath;
	}
	@Generated(hash = 1895854510)
	public PhotoPath() {
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUploadStatus() {
		return this.uploadStatus;
	}
	public void setUploadStatus(int uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRelid() {
		return this.relid;
	}
	public void setRelid(String relid) {
		this.relid = relid;
	}
	public String getPhoto() {
		return this.photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPhotoPath() {
		return this.photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

}
