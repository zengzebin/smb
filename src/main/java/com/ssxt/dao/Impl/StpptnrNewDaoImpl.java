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
import com.ssxt.dao.StpptnrNewsDao;
import com.ssxt.dao.Impl.BaseDaoImpl;
import com.ssxt.model.StPptnR;
import com.ssxt.model.StPptnRNew;
import com.ssxt.vo.rain.DailyDayRainFall;

@Repository("stpptnrNewDao")
public class StpptnrNewDaoImpl extends BaseDaoImpl<StPptnRNew> implements StpptnrNewsDao {

	public List<StPptnRNew> getStpptnrNew(String hql) {
		// TODO Auto-generated method stub
		return this.getByHql(hql);
	}

	public Page getPageStpptnrNews(Page page, String countHql, String selHql) {
		// TODO Auto-generated method stub

		return this.getByObjectPage(page, countHql, selHql);
	}
	
	public List findListByHql(String hql) {
		// TODO Auto-generated method stub

		return this.getListByHql(hql);
	}
	
	public List findListBySQL(String sql) {
		// TODO Auto-generated method stub

		return this.getListBySQL(sql);
	}

}
