package com.ssxt.vo.rsvr;

public class RealTimeReservoirWaterState {
	private String adminArea;  //行政区
    private String volley;     //流域
	private String stationName;    //站名
	private String river;    //河名
   	private float waterLevel; // 库水位
	private float waterPotential; // 水势
	private float inFlow;// 入库流量
	private float waterStorageCapacity; // 蓄水量
	private float damHeight;// 堤坝高程
	
	public RealTimeReservoirWaterState() {
	}

	public RealTimeReservoirWaterState(String adminArea,String volley,String stationName,String river,float waterLevel, float waterPotential,float inFlow,float waterStorageCapacity,float damHeight) {
		super();
		this.adminArea = adminArea;
		this.volley = volley;
		this.stationName = stationName;
		this.river = river;
		this.waterLevel = waterLevel;
		this.waterPotential =  waterPotential;
		this.inFlow = inFlow;
		this.waterStorageCapacity = waterStorageCapacity;
		this.damHeight = damHeight;

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
	public float getInFlow() {
		return inFlow;
	}
	public void setInFlow(float inFlow) {
		this.inFlow = inFlow;
	}
	public float getWaterStorageCapacity() {
		return waterStorageCapacity;
	}
	public void setWaterStorageCapacity(float waterStorageCapacity) {
		this.waterStorageCapacity = waterStorageCapacity;
	}
	public float getDamHeight() {
		return damHeight;
	}
	public void setDamHeight(float damHeight) {
		this.damHeight = damHeight;
	}

}
