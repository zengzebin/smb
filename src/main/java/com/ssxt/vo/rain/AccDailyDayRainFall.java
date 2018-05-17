package com.ssxt.vo.rain;

public class AccDailyDayRainFall {
	private float dayRainFall[]; //日雨量
	private String stcd; //测站编码
	public AccDailyDayRainFall(float[] dayRainFall, String stcd) {
		super();
		this.dayRainFall = dayRainFall;
		this.stcd = stcd;
	}
	public float[] getDayRainFall() {
		return dayRainFall;
	}
	public void setDayRainFall(float[] dayRainFall) {
		this.dayRainFall = dayRainFall;
	}
	public String getStcd() {
		return stcd;
	}
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	 
}
