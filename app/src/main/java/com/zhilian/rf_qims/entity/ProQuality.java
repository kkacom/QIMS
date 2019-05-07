package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by colin on 2018/3/30 16:49 .
 */
@Entity
public class ProQuality {
	@Id
	private long id;
	private long rid;
	private String gbhd;
	private String gjfb;
	private String hltqd;
	private String jgzl;
	private String qmzl;
	private String xbm;
	@Generated(hash = 1230620239)
	public ProQuality(long id, long rid, String gbhd, String gjfb, String hltqd,
									String jgzl, String qmzl, String xbm) {
					this.id = id;
					this.rid = rid;
					this.gbhd = gbhd;
					this.gjfb = gjfb;
					this.hltqd = hltqd;
					this.jgzl = jgzl;
					this.qmzl = qmzl;
					this.xbm = xbm;
	}
	@Generated(hash = 691899317)
	public ProQuality() {
	}
	public long getId() {
					return this.id;
	}
	public void setId(long id) {
					this.id = id;
	}
	public long getRid() {
					return this.rid;
	}
	public void setRid(long rid) {
					this.rid = rid;
	}
	public String getGbhd() {
					return this.gbhd;
	}
	public void setGbhd(String gbhd) {
					this.gbhd = gbhd;
	}
	public String getGjfb() {
					return this.gjfb;
	}
	public void setGjfb(String gjfb) {
					this.gjfb = gjfb;
	}
	public String getHltqd() {
					return this.hltqd;
	}
	public void setHltqd(String hltqd) {
					this.hltqd = hltqd;
	}
	public String getJgzl() {
					return this.jgzl;
	}
	public void setJgzl(String jgzl) {
					this.jgzl = jgzl;
	}
	public String getQmzl() {
					return this.qmzl;
	}
	public void setQmzl(String qmzl) {
					this.qmzl = qmzl;
	}
	public String getXbm() {
					return this.xbm;
	}
	public void setXbm(String xbm) {
					this.xbm = xbm;
	}

}
