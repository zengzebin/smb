package com.ssxt.model;

public class AdminCode {
	 
	
	String cityName;  //城市
	public AdminCode() {
		super();
	}
	public AdminCode(String cityName, String code) {
		super();
		this.cityName = cityName;
		this.code = code;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	String code; //行政区划码
	 

}
