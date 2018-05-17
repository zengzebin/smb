package com.ssxt.vo.rain;

public class DailyDayRainFallOfEveryStation {
	private double dayRainFall; //日雨量
	public DailyDayRainFallOfEveryStation( String stcd,double dayRainFall) {
		super();
		this.dayRainFall = dayRainFall;
		this.stcd = stcd;
	}
	private String stcd; //测站编码

	public double getDayRainFall() {
		return dayRainFall;
	}
	public void setDayRainFall(double dayRainFall) {
		this.dayRainFall = dayRainFall;
	}
	public String getStcd() {
		return stcd;
	}
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	
}
