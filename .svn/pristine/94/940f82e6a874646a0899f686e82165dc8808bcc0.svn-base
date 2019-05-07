package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by sl on 2017-10-17.
 */
@Entity
public class Company implements Serializable{

	private static final long serialVersionUID = 5251488744519183844L;
	@Id
	private long id;//对应json的eid
	private long mainId;//主ID
	private String name;
	private String number;
	private String addr;
	private int area;
	private String contacts;
	private String phone;

	@Generated(hash = 950238010)
	public Company(long id, long mainId, String name, String number, String addr,
			int area, String contacts, String phone) {
		this.id = id;
		this.mainId = mainId;
		this.name = name;
		this.number = number;
		this.addr = addr;
		this.area = area;
		this.contacts = contacts;
		this.phone = phone;
	}

	@Generated(hash = 1096856789)
	public Company() {
	}

	@Override
	public String toString() {
		return "Company{" +
			"id=" + id +
			", name='" + name + '\'' +
			", number='" + number + '\'' +
			", addr='" + addr + '\'' +
			", area='" + area + '\'' +
			", contacts='" + contacts + '\'' +
			", phone='" + phone + '\'' +
			'}';
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMainId() {
		return this.mainId;
	}

	public void setMainId(long mainId) {
		this.mainId = mainId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getArea() {
		return this.area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
