package com.ssxt.service;

import java.util.List;

import com.ssxt.vo.river.RealTimeWaidWaterState;
import com.ssxt.vo.river.WadiWaterInfo;
import com.ssxt.vo.rsvr.RealTimeReservoirWaterState;
import com.ssxt.vo.rsvr.ReservoirWaterInfoInfo;


public interface RsvrSerivce {
 
	/**
	 * 返回水库最新数据
	 * @param StationNo 测站码
	 * @param Area 行政区域
	 * @return
	 */
	public List<ReservoirWaterInfoInfo> findRsvrInfo(String []StationNo ,String  Area);
	
	public List<String> getCondition(String type);
	
	public List<RealTimeReservoirWaterState> getRsvrBySearch(String DateTime,String AdminArea,String Volley);
	
	public String getNewestDatetime(String AdminArea,String Volley);
}
