package com.ssxt.vo.rain;

public class DailyPeriodRainFallOfEveryStation {
	 private String stationNo;  //测站编码
	 private double []rainFallOfEveryHour; //每小时雨量,从第一天8时开始到第二天7时,数组长度为24
	 public String getStationNo() {
		return stationNo;
	}
	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}
	public double[] getRainFallOfEveryHour() {
		return rainFallOfEveryHour;
	}
	public void setRainFallOfEveryHour(double[] rainFallOfEveryHour) {
		this.rainFallOfEveryHour = rainFallOfEveryHour;
	}
	
}
