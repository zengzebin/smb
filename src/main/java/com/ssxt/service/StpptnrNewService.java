package com.ssxt.service;

import java.util.List;

import com.ssxt.common.tools.Page;
import com.ssxt.model.StPptnRNew;
import com.ssxt.vo.rain.RealTimeDayRainFall;
 

public interface StpptnrNewService {
	 /**
	  * 返回StpptnrNew
	  * @param hql
	  * @return
	  */
	public List<StPptnRNew> getStpptnrNew(String hql);
	
	public Page<StPptnRNew> getPageStpptnrNews(Page page,String from ,String end);
	
	public List<String> getCondition(String type);
	
	public List<RealTimeDayRainFall> getStpptnrNewBySearch(String DateTime,String AdminArea,String Volley);
	
	public List getStpptnrNewAccBySearch(String DateTime,String AdminArea,String Volley,String StationName,int DayNumber);
	
	public String getNewestDatetime(String AdminArea,String Volley,String StationName);
	
	/**
	 * 当天各测试站日雨量，临时接口
	 * @param object 
	 * @param sql
	 * @return
	 */
	public List<StPptnRNew> getDayRainFal2(String StationName, String bsnm, String startTime, String endTime, String SortWay);

}
