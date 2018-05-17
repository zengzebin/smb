package com.ssxt.dao;

import java.util.List;

public interface RsvrDao {
	public List findRsvrInfo(String sql);

	public List findListByHql(String hql);
	
	public List findListBySQL(String sql);
}
