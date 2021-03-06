package com.ssxt.model;
// default package
// Generated 2016-3-1 14:07:13 by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * StRsvrR generated by hbm2java
 */
public class StRsvrR implements java.io.Serializable {

	private Integer id;
	private String stcd;
	private Date tm;
	
	private BigDecimal rz;
	private BigDecimal w;
	private BigDecimal inq;
	private String rwptn;
	private String rsvrName;
	private BigDecimal altitude;
	private Date addTime;
	
	

	public StRsvrR() {
	}

	public StRsvrR(String stcd, Date tm, BigDecimal rz, BigDecimal w, BigDecimal inq, String rwptn, String rsvrName,
			BigDecimal altitude, Date addTime) {
		this.stcd = stcd;
		this.tm = tm;
		this.rz = rz;
		this.w = w;
		this.inq = inq;
		this.rwptn = rwptn;
		this.rsvrName = rsvrName;
		this.altitude = altitude;
		this.addTime = addTime;
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

	public Date getTm() {
		return this.tm;
	}

	public void setTm(Date tm) {
		this.tm = tm;
	}

	public BigDecimal getRz() {
		return this.rz;
	}

	public void setRz(BigDecimal rz) {
		this.rz = rz;
	}

	public BigDecimal getW() {
		return this.w;
	}

	public void setW(BigDecimal w) {
		this.w = w;
	}

	public BigDecimal getInq() {
		return this.inq;
	}

	public void setInq(BigDecimal inq) {
		this.inq = inq;
	}

	public String getRwptn() {
		return this.rwptn;
	}

	public void setRwptn(String rwptn) {
		this.rwptn = rwptn;
	}

	public String getRsvrName() {
		return this.rsvrName;
	}

	public void setRsvrName(String rsvrName) {
		this.rsvrName = rsvrName;
	}

	public BigDecimal getAltitude() {
		return this.altitude;
	}

	public void setAltitude(BigDecimal altitude) {
		this.altitude = altitude;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	private String tms;
	public String getTms() {
		return sdf.format(tm);
	}
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

}
