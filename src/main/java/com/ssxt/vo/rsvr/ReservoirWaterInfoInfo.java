package com.ssxt.vo.rsvr;

public class ReservoirWaterInfoInfo {
	        private String stationNo; //测站编码
			private String stationName; //站名
			private String riverName;  //河名
			private float waterLevel; //库水位
			private int waterPotential; //水势
			private float inFlow;//入库流量
			private float waterStorageCapacity; //蓄水量
			private float damHeight;//堤坝高程
			private String dateTime;
			
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
			public String getRiverName() {
				return riverName;
			}
			public void setRiverName(String riverName) {
				this.riverName = riverName;
			}
			public float getWaterLevel() {
				return waterLevel;
			}
			public void setWaterLevel(float waterLevel) {
				this.waterLevel = waterLevel;
			}
			public int getWaterPotential() {
				return waterPotential;
			}
			public void setWaterPotential(int waterPotential) {
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
