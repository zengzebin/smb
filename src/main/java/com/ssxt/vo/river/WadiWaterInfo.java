package com.ssxt.vo.river;

public class WadiWaterInfo {
	    private  String dateTime; //时间
	    private String stationNo; //测站编码
		private String stationName; //站名
		private String river;  //河名
		private String area;   //地区
		private float waterLevel; //水位
		private float waterPotential; //水势
		private float flow;//流量
		private float alertwaterLevel; //警戒水位
		public String getDateTime() {
			return dateTime;
		}
		public void setDateTime(String dateTime) {
			this.dateTime = dateTime;
		}
		public String getStationNo() {
			return stationNo;
		}
		public void setStationNo(String stationNo) {
			this.stationNo = stationNo;
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
		public String getArea() {
			return area;
		}
		public void setArea(String area) {
			this.area = area;
		}
		public float getWaterLevel() {
			return waterLevel;
		}
		public void setWaterLevel(float waterLevel) {
			this.waterLevel = waterLevel;
		}
		public float getWaterPotential() {
			return waterPotential;
		}
		public void setWaterPotential(float waterPotential) {
			this.waterPotential = waterPotential;
		}
		public float getFlow() {
			return flow;
		}
		public void setFlow(float flow) {
			this.flow = flow;
		}
		public float getAlertwaterLevel() {
			return alertwaterLevel;
		}
		public void setAlertwaterLevel(float alertwaterLevel) {
			this.alertwaterLevel = alertwaterLevel;
		}
		
}
