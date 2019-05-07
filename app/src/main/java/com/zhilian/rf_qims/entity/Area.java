package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by colin on 2018/3/28 16:32 .
 */
@Entity
public class Area {
	@Id
	private long id;
	private String name;

	@Generated(hash = 1512810350)
	public Area(long id, String name) {
					this.id = id;
					this.name = name;
	}

	@Generated(hash = 179626505)
	public Area() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
