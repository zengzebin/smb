package com.ssxt.vo.rain;

public class Average {
	private String stcd;   // 测站
	private double [] averagePeriodRainFall; //每天平均时段雨量
	private double [] DayRainFall; //每天日雨量 
	public double[] getDayRainFall() {
		return DayRainFall;
	}
	public void setDayRainFall(double[] dayRainFall) {
		DayRainFall = dayRainFall;
	}
	public String getStcd() {
		return stcd;
	}
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	public double[] getAveragePeriodRainFall() {
		return averagePeriodRainFall;
	}
	public void setAveragePeriodRainFall(double[] averagePeriodRainFall) {
		this.averagePeriodRainFall = averagePeriodRainFall;
	}
	 
}
