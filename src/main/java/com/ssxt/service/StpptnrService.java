package com.ssxt.service;

import java.util.List;

import com.ssxt.model.StPptnR;
import com.ssxt.model.StPptnRNew;
import com.ssxt.vo.rain.AccDailyDayRainFall;
import com.ssxt.vo.rain.Average;
import com.ssxt.vo.rain.DailyDayRainFall;
import com.ssxt.vo.rain.DailyDayRainFallOfEveryStation;
import com.ssxt.vo.rain.DailyPeriodRainFallOfEveryStation;
import com.ssxt.vo.rain.DailyPeriodResult1;

public interface StpptnrService {
	
	/**
	 * 当天各测试站日雨量
	 * @param sql
	 * @return
	 */
	   public    List <DailyDayRainFallOfEveryStation>     getDayRainFall(String StationNo,String time,String SortWay);
	   
 	/**
	 * 获取数据时间范围
	 * @param time
	 * @return
	 */
	public String getDateTime(String time,String stcd);
	
     /**
      * 逐日时段雨量分页一个测站
      */
    public DailyPeriodResult1 findStpptnrOne(String [] stcd,int pageNo,String form,String end,String SortWay);
    
    /**
     * 逐日时段雨量多个测站
     * @param stcd 测站
     * @param time 时间
     * @param SortWay 排序
     * @return
     */
    public DailyPeriodResult1 findStpptnrMany(String [] stcd,String time,String SortWay);
    
    
    /**
     * 平均值
     * @param stcd 测站
     * @param from 开始时间
     * @param end  结束时间
     *  @param type   1表示平均时段与平均日雨 2平均日雨
     * @return   
     */
    public List<Average> findStpptnrCalculateAverage(String  stcd,String from,String end,int type);
    
    
    /**
     * 获取24小时时段雨量
     * @param stcd 测站
     * @param from 开始时间
     * @param end 结束时间
     * @return
     */
   public  List <DailyPeriodRainFallOfEveryStation> findStpptnrIntervals24(String [] stcd,String from,String end);
 
   /**
    * 统计项目
    * @param StationNo 测站
    * @param DateTime  时间
    * @param DayNumber 统计天数
    * @return
    */
   public List<AccDailyDayRainFall> findStpptnrDayRainFall(String [] StationNo,String DateTime,int DayNumber);
}
