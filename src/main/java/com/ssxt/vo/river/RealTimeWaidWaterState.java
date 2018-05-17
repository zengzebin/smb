package com.ssxt.vo.river;

public class RealTimeWaidWaterState {
	private String adminArea; // 行政区
	private String volley; // 流域
	private String stationName; // 站名
	private String river; // 河名
	private float waterLevel; // 水位
	private float waterPotential; // 水势
	private float flow;// 流量
	private float alertwaterLevel; // 警戒水位
	public RealTimeWaidWaterState() {
	}

	public RealTimeWaidWaterState(String adminArea,String volley,String stationName,String river,float waterLevel, float waterPotential,float flow,float alertwaterLevel) {
		super();
		this.adminArea = adminArea;
		this.volley = volley;
		this.stationName = stationName;
		this.river = river;
		this.waterLevel = waterLevel;
		this.waterPotential = waterPotential;
		this.flow = flow;
		this.alertwaterLevel = alertwaterLevel;
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
