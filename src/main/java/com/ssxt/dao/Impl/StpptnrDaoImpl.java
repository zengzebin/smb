package com.ssxt.dao.Impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ssxt.common.tools.Page;
import com.ssxt.dao.StpptnrDao;
import com.ssxt.dao.Impl.BaseDaoImpl;
import com.ssxt.model.StPptnR;
import com.ssxt.vo.rain.DailyDayRainFall;

@Repository("stpptnrDao")
public class StpptnrDaoImpl extends BaseDaoImpl<StPptnR> implements StpptnrDao {
 
//	public List  getRainfallDay(String sql) {
//		// TODO Auto-generated method stub
//		return  this.getByHql(sql);
//	}
	/**
	 * 获取数据时间范围
	 * @param time
	 * @return
	 */
	 public List getDateTime(String hql) {
		// TODO Auto-generated method stub
	  
		 
		 return   this.getByHql(hql);
	}
	 
	 /**
	  * 那个逐日日雨量与分页
	  */
	public Page findStpptnr(Page p, String sqlcount, String sql) {
		// TODO Auto-generated method stub
		return  this.getPageBySQL(p, sqlcount, sql);
	}
	
  /**
   *  多个测站逐日日雨量
   */
	public List findStpptnrMany(String sql) {
		// TODO Auto-generated method stub
		return this.getListBySQL(sql);
	}
	
  /**
   * 当天各测试站日雨量
   */
public List getDayRainFall(String hql) {
	// TODO Auto-generated method stub
//    try{
//    	List <DailyDayRainFall> d=null;
//       d= this.getHibernateTemplate().execute(new HibernateCallback<List<DailyDayRainFall>>(){
//		 public List<DailyDayRainFall> doInHibernate(Session seesion)
//				throws HibernateException, SQLException {
//			return seesion.createSQLQuery(hql).addScalar("timeframe", Hibernate.STRING).addScalar("dataType", Hibernate.STRING).addScalar("channelId", Hibernate.STRING)
// 				.addScalar("onlineMaxNum", Hibernate.STRING).addScalar("onlineTime", Hibernate.STRING).setResultTransformer(Transformers.aliasToBean(DailyDayRainFall.class)).list();
//		}
//		
//	});
// 
//    }catch (Exception e) {
//		e.printStackTrace();
//	//	throw new DaoException(e.getMessage(),e);
//	}
	 return   this.getListByHql(hql);
}
		/**
		 * 平均值
		 */
	public List findStpptnrCalculateAverage(String hql) {
		// TODO Auto-generated method stub
		return this.getListByHql(hql);
	}

	public List findStpptnrIntervals24(String sql) {
		// TODO Auto-generated method stub
		return this.getByHql(sql);
	}

	/**
	 * 统计项目
	 */
	public List findStpptnrDayRainFall(String hql) {
		// TODO Auto-generated method stub
		return this.getListByHql(hql);
	}

	public List getByHQL(String hql) {
		// TODO Auto-generated method stub
		return this.getByHql(hql);
	}
	

}
