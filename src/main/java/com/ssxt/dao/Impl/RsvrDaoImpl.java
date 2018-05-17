package com.ssxt.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssxt.dao.RiverDao;
import com.ssxt.dao.RsvrDao;
import com.ssxt.model.StPptnR;
import com.ssxt.model.StRiverR;
import com.ssxt.model.StRsvrR;
import com.ssxt.vo.river.WadiWaterInfo;
import com.ssxt.vo.river.test;

@Repository("rsvrDao")
public class RsvrDaoImpl extends BaseDaoImpl<StRsvrR> implements   RsvrDao {

	public List findRsvrInfo(String sql) {
		// TODO Auto-generated method stub
		//WadiWaterInfo book=(WadiWaterInfo)getSession().createSQLQuery("").addEntity(WadiWaterInfo.class);	 
      //	StRiverR  book=(StRiverR) getSession().createSQLQuery("SELECT  *  FROM ST_RIVER_R WHERE id=7633 ").addEntity(StRiverR.class).list();	 		
		return this.getListBySQL(sql);
	}
	
	
	public List findListByHql(String hql) {
		// TODO Auto-generated method stub

		return this.getListByHql(hql);
	}
	
	public List findListBySQL(String sql) {
		// TODO Auto-generated method stub

		return this.getListBySQL(sql);
	}
	
	public List<StRsvrR> getWaterState(String stationNo, String startTime, String endTime, Object object) {
		// TODO Auto-generated method stub
		String hql="from StRsvrR o WHERE o.tm BETWEEN '"+startTime+"' AND '"+endTime
				+"' AND o.stcd='"+ stationNo +"' ORDER BY o.tm";
		return this.getListByHql(hql);
	}

}
