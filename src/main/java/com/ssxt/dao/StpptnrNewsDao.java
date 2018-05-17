package com.ssxt.dao;

import java.util.List;

import com.ssxt.common.tools.Page;
import com.ssxt.model.StPptnR;
import com.ssxt.model.StPptnRNew;
import com.ssxt.vo.rain.Average;
import com.ssxt.vo.rain.DailyPeriodResult1;

public interface StpptnrNewsDao {
	 /**
	  * 返回StpptnrNew
	  * @param hql
	  * @return
	  */
	public List<StPptnRNew> getStpptnrNew(String hql);
	
	public Page<StPptnRNew> getPageStpptnrNews(Page page,String countHql,String selHql) ;
	
	public List findListByHql(String hql);
	
	public List findListBySQL(String sql);
}
