package com.ssxt.vo.rain;

import java.math.BigDecimal;

public class MonthCumulation {
	
	private String sitename;
	
	private int year;
	
	private int month;
	
	private BigDecimal dypcummulation;
	
	private String province;

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public BigDecimal getDypcummulation() {
		return dypcummulation;
	}

	public void setDypcummulation(BigDecimal dypcummulation) {
		this.dypcummulation = dypcummulation;
	}

	

}
