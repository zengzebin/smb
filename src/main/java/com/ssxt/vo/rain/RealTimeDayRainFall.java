package com.ssxt.vo.rain;

public class RealTimeDayRainFall {
	private String adminArea; // 行政区
	private String volley; // 流域
	private String stationName; // 站名
	private String river; // 河名
	private float dayRainFall; // 日雨量
	
	public RealTimeDayRainFall(){
		
	}
	
	public RealTimeDayRainFall(String adminArea, String volley, String stationName, String river, float dayRainFall) {
		super();
		this.adminArea = adminArea;
		this.volley = volley;
		this.stationName = stationName;
		this.river = river;
		this.dayRainFall = dayRainFall;
	}

	public String getAdminArea() {
		return adminArea;
	}

	public void setAdminArea(String adminArea) {
		this.adminArea = adminArea;
	}

	public String getVolley() {
		return volley;
	}

	public void setVolley(String volley) {
		this.volley = volley;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getRiver() {
		return river;
	}

	public void setRiver(String river) {
		this.river = river;
	}

	public float getDayRainFall() {
		return dayRainFall;
	}

	public void setDayRainFall(float dayRainFall) {
		this.dayRainFall = dayRainFall;
	}

}
