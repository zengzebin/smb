package com.ssxt.vo.rain;

public class DailyPeriodStationEveryDayInfo {
	private double eight_fourteen;  //8~14时雨量
	private double fourteen_twenty;  //14~20时雨量
	
	private double twenty_two;  //20~2时雨量
	private  double two_eight;  //2~8时雨量
	private double dayRainFall;  //日雨量
	private String dateTime;  //时间
	private String stationNo;//测试站

	
	
	public DailyPeriodStationEveryDayInfo(){
		
	}
	public DailyPeriodStationEveryDayInfo(String stationNo,String dateTime,double eight_fourteen, double fourteen_twenty, double twenty_two,
			double two_eight, double dayRainFall) {
		super();
		this.eight_fourteen = eight_fourteen;
		this.fourteen_twenty = fourteen_twenty;
		this.twenty_two = twenty_two;
		this.two_eight = two_eight;
		this.dayRainFall = dayRainFall;
		this.dateTime = dateTime;
		this.stationNo = stationNo;
		 
	}
	
	public String getStcd() {
		return stationNo;
	}
	public void setStcd(String stationNo) {
		this.stationNo = stationNo;
	}
	public double getEight_fourteen() {
		return eight_fourteen;
	}
	public void setEight_fourteen(double eight_fourteen) {
		this.eight_fourteen = eight_fourteen;
	}
	public double getFourteen_twenty() {
		return fourteen_twenty;
	}
	public void setFourteen_twenty(double fourteen_twenty) {
		this.fourteen_twenty = fourteen_twenty;
	}
	public double getTwenty_two() {
		return twenty_two;
	}
	public void setTwenty_two(double twenty_two) {
		this.twenty_two = twenty_two;
	}
	public double getTwo_eight() {
		return two_eight;
	}
	public void setTwo_eight(double two_eight) {
		this.two_eight = two_eight;
	}
	public double getDayRainFall() {
		return dayRainFall;
	}
	public void setDayRainFall(double dayRainFall) {
		this.dayRainFall = dayRainFall;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

}
