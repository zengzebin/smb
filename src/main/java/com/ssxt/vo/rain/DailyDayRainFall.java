package com.ssxt.vo.rain;

public class DailyDayRainFall {
	    private  String stcd;//站点
	    private  String dateTime; //时间
		private  double dayRainFall; //各站当天日雨量数据
	 
		public DailyDayRainFall(String stcd, String dateTime, double dayRainFall) {
			super();
			this.stcd = stcd;
			this.dateTime = dateTime;
			this.dayRainFall = dayRainFall;
		}
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
		public String getDateTime() {
			return dateTime;
		}
		public void setDateTime(String dateTime) {
			this.dateTime = dateTime;
		}
		 

}
