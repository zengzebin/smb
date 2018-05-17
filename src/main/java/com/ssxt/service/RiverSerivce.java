package com.ssxt.service;

import java.util.List;

import com.ssxt.vo.river.WadiWaterInfo;
import com.ssxt.vo.river.RealTimeWaidWaterState;
public interface RiverSerivce {
 
	/**
	 * 返回河道最新数据
	 * @param StationNo 测站码
	 * @param Area 行政区域
	 * @return
	 */
	public List<WadiWaterInfo> findWadiWaterInfo(String []StationNo ,String  Area);
	
	public List<String> getCondition(String type);	
	
	public List<RealTimeWaidWaterState> getRiverBySearch(String DateTime,String AdminArea,String Volley);
	
	public String getNewestDatetime(String AdminArea,String Volley);
}
