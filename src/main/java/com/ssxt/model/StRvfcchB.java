package com.ssxt.model;
// default package
// Generated 2016-2-22 14:50:58 by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import java.util.Date;

/**
 * StRvfcchB generated by hbm2java
 */
public class StRvfcchB implements java.io.Serializable {

	private Integer id;
	private String stcd;
	private BigDecimal wrz;
	private Date moditime;

	public StRvfcchB() {
	}

	public StRvfcchB(String stcd, BigDecimal wrz, Date moditime) {
		this.stcd = stcd;
		this.wrz = wrz;
		this.moditime = moditime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStcd() {
		return this.stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public BigDecimal getWrz() {
		return this.wrz;
	}

	public void setWrz(BigDecimal wrz) {
		this.wrz = wrz;
	}

	public Date getModitime() {
		return this.moditime;
	}

	public void setModitime(Date moditime) {
		this.moditime = moditime;
	}

}
