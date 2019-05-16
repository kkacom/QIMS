package com.zhilian.rf_qims.bean;

import java.util.List;

/**
 * Created by zhilian on 2018/1/12.
 */

public class LeaveMineBean {

	private List<ItemBean> list;

	public List<ItemBean> getList() {
		return list;
	}

	public void setList(List<ItemBean> list) {
		this.list = list;
	}

	public static class ItemBean {

		private String pstatus;
		private String approvedate;
		private String begindate;
		private String dayg;
		private String days;
		private double dayt;
		private String enddate;
		private String type;
		private String id;

		public String getPstatus() {
			return pstatus;
		}

		public void setPstatus(String pstatus) {
			this.pstatus = pstatus;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getApprovedate() {
			return approvedate;
		}

		public void setApprovedate(String approvedate) {
			this.approvedate = approvedate;
		}

		public String getBegindate() {
			return begindate;
		}

		public void setBegindate(String begindate) {
			this.begindate = begindate;
		}

		public String getDayg() {
			return dayg;
		}

		public void setDayg(String dayg) {
			this.dayg = dayg;
		}

		public String getDays() {
			return days;
		}

		public void setDays(String days) {
			this.days = days;
		}

		public double getDayt() {
			return dayt;
		}

		public void setDayt(double dayt) {
			this.dayt = dayt;
		}

		public String getEnddate() {
			return enddate;
		}

		public void setEnddate(String enddate) {
			this.enddate = enddate;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	}
}
