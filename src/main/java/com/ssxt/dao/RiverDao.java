package com.ssxt.dao;

import java.util.List;

public interface RiverDao {
   public  List findWadiWaterInfo(String sql);

	public List findListByHql(String hql);
	
	public List findListBySQL(String sql);
}
