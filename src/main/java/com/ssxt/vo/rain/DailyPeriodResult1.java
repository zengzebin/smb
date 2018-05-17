package com.ssxt.vo.rain;

import java.util.List;

public class DailyPeriodResult1 {
	  int error;
	   List<DailyPeriodStationEveryDayInfo> infoList;
	 
		private long count;//总共几条记录
		
		public long getCount() {
			return count;
		}
		public void setCount(long count) {
			this.count = count;
		}
		public DailyPeriodResult1(){
			
		}
		public DailyPeriodResult1(int error, List<DailyPeriodStationEveryDayInfo> infoList, int pageNo, long pageCount) {
			super();
			this.error = error;
			this.infoList = infoList;
		 
			this.count = pageCount;
		}
		public int getError() {
			return error;
		}
		public void setError(int error) {
			this.error = error;
		}
		public List<DailyPeriodStationEveryDayInfo> getInfoList() {
			return infoList;
		}
		public void setInfoList(List<DailyPeriodStationEveryDayInfo> infoList) {
			this.infoList = infoList;
		}
	 
		
	   
 
 
}
