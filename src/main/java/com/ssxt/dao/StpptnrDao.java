package com.ssxt.dao;

import java.util.List;

import com.ssxt.common.tools.Page;
import com.ssxt.model.StPptnR;
import com.ssxt.vo.rain.Average;
import com.ssxt.vo.rain.DailyPeriodResult1;

public interface StpptnrDao {
	/**
	 * 当天各测试站日雨量
	 * @param sql
	 * @return
	 */
	   public  List    getDayRainFall(String hql);
	   
	   public  List    getByHQL(String hql);
	 /**
		 * 获取数据时间范围
		 * @param time
		 * @return
		 */
		public List getDateTime(String hql);
		
		/**
		 * 那个逐日日雨量分页
		 * @param p
		 * @param sqlcount
		 * @param sql
		 * @return
		 */
		 public Page	findStpptnr(Page p,String sqlcount,String sql);
		 
		 
		 /**
		  * 逐日时段雨量多个测站
		  * @param sql
		  * @return
		  */
		public  List findStpptnrMany(String hql);
		
		 /**
		  * 平均值
		  * @param sql
		  * @return
		  */
		public  List findStpptnrCalculateAverage(String hql);
		
		
		/**
		 * 获取24小时时段雨量
		 * @param hql
		 * @return
		 */
		public List findStpptnrIntervals24(String hql);
		
		/**
		 * 统计项目
		 * @param hql
		 * @return
		 */
		public List findStpptnrDayRainFall(String hql);
		
}
