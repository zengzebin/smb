package com.ssxt.dao.Impl;

 
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Cache;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
 import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.exception.JDBCConnectionException;
 import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssxt.common.tools.Page;
import com.ssxt.dao.BaseDao;
import com.ssxt.model.User;

 
 
 

/**
 * 实现了BaseDao接口。
 * @author Administrator
 *
 * @param <T>
 */
  
public class BaseDaoImpl<T> implements BaseDao<T> {

	private static Logger log = Logger.getLogger(BaseDaoImpl.class);
 
 	 // @Autowired
	 private HibernateTemplate hibernateTemplate;
	 
	 @Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
	  this.hibernateTemplate = hibernateTemplate;
	 }
// 
	
 

	public T get(Class<T> clazz, int id) {
		if(id < 1){
			return null;
		}
 
		System.out.println(hibernateTemplate);
		if(id < 1){
			return null;
		}
		return hibernateTemplate.get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getByHql(String hql) {
		
		List<T> entities = (List<T>)hibernateTemplate.find(hql);
		return entities;
	}
	
 
	 
	
	public String getStringByHql(String hql) {
		Object obj = this.getObjectByHql(hql);
		if(obj == null){
			return null;
		}
		return (String)obj;
	}

	
	
	public long countRecord(String hql) {
		Object obj = this.getObjectByHql(hql);
		if(obj == null){
			return 0;
		}
		return (Long)obj;
	}

	 @SuppressWarnings("unchecked")
	public Page getByObjectPage(Page page, String countHql, final String selHql) {
		page.setTotalCount(this.countRecord(countHql));
		final int first = page.getFirst();
		final int pageSize = page.getPageSize();
		if(page.getTotalCount() > 0){
			
			List rs = hibernateTemplate.executeFind(new HibernateCallback() {
				public List doInHibernate(Session session) throws HibernateException,
						SQLException {
					//LogUtil.log.info("getByObjectPage:  "+selHql);
					List results = session.createQuery(selHql).setFirstResult(first-1)
										.setMaxResults(pageSize).list();
					return results;
				}
			});
			page.setResult(rs);
		}else
			page.setResult(null);
		return page;
	}

	@SuppressWarnings("unchecked")
	public Page<T> getByPage(Page<T> page,String countHql, final String selHql) {
		page.setTotalCount(this.countRecord(countHql));
		final int first = page.getFirst();
		final int pageSize = page.getPageSize();
		if(page.getTotalCount() > 0){
			
			List<T> rs = hibernateTemplate.executeFind(new HibernateCallback<List<T>>() {
				public List<T> doInHibernate(Session session) throws HibernateException,
						SQLException {
					////LogUtil.log.info("getByPage:  "+selHql);
					List<T> results = (List<T>)session.createQuery(selHql).setFirstResult(first-1)
										.setMaxResults(pageSize).list();
					return results;
				}
			});
			page.setResult(rs);
		}else
			page.setResult(null);
		return page;
	}
	
	

	public Integer getIntegerByHql(String hql) {
		Object obj = this.getObjectByHql(hql);
		if(obj == null){
			return 0;
		}
		return (Integer)obj;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	public Object getObjectByHql(final String hql) {
//		if(StringUtils.isEmpty(hql)){
//			return null;
//		}
		return this.hibernateTemplate.execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.createQuery(hql).setFirstResult(0).setMaxResults(1).uniqueResult();
			}
		});
	}

	
	@SuppressWarnings("unchecked")
	public List getListByHql(String hql) {
        List list = hibernateTemplate.find(hql);
        return list;
	}

 

	@SuppressWarnings("unchecked")
	public List getByParams(String hql, String[] paramNames,
			Object[] paramValues) {
		List list = getHibernateTemplate().findByNamedParam(hql, paramNames, paramValues);
		return list;
	}
	
	public Page getPageBySQL(Page page,String countSql,final String selSql){
		page.setTotalCount(this.countSQLRecord(countSql));
		final int first = page.getFirst();
		final int pageSize = page.getPageSize();
		if(page.getTotalCount() > 0){
			
			List rs = hibernateTemplate.executeFind(new HibernateCallback<List>() {
				public List doInHibernate(Session session) throws HibernateException,
						SQLException {
					log.info("getPageBySQL:  "+selSql);
					return session.createSQLQuery(selSql).setFirstResult(first-1)
							.setMaxResults(pageSize).list();
				}
			});
			page.setResult(rs);
		}
		return page;
	}
	
	
	
	
	 
	
	
	public List getListBySQL(final String sql){
	 
		return this.hibernateTemplate.execute(new HibernateCallback<List>(){
			public List doInHibernate(Session session) throws HibernateException,
					SQLException {
				log.info("getListBySQL:  "+sql);
				return session.createSQLQuery(sql).list();
			}
		});
	}
	 

	public Object getObjectBySQL(final String sql){
//		if(StringUtils.isEmpty(sql)){
//			return null;
//		}
		return this.hibernateTemplate.execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				log.info("getObjectBySQL:  "+sql);
				return session.createSQLQuery(sql).setFirstResult(0).setMaxResults(1).uniqueResult();
			}
		});
	}
	
	public long countSQLRecord(String sql){
		log.info("countSQLRecord:  "+sql);
		BigInteger bigCount = (BigInteger)this.getObjectBySQL(sql);
		if(bigCount == null){
			return 0;
		}
		return bigCount.longValue();
	}

	@SuppressWarnings("unchecked")
	public List<Object> getListLimitSize(final String selHql, final int size) {
		List<Object> rs = hibernateTemplate.executeFind(new HibernateCallback<List<Object>>() {
			public List<Object> doInHibernate(Session session) throws HibernateException,
					SQLException {
				List<Object> results = (List<Object>)session.createQuery(selHql).setFirstResult(0)
									.setMaxResults(size).list();
				return results;
			}
		});
		return rs;
	}

	
//	=============================   ======================================
	
	protected Session getSession(){
		return getHibernateTemplate().getSessionFactory().getCurrentSession();	
	}
	
	public SQLQuery createSqlQuery(String sql){
		return getSession().createSQLQuery(sql);
	}
	
	public Query createQuery(String hql){
//		return getHibernateTemplate().execute(new HibernateCallback() {
//			public Object doInHibernate(Session session) throws HibernateException, SQLException {
//				return session;
//			}
//		});
		
//		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Session session = getSession();

		return session.createQuery(hql);
	}
	
	
	 

	 

	public int getTotalCounts(String hql, List<Object> list) {
		try {
			Query query = createQuery(hql);
			if(list != null){
				for (int i = 0,len = list.size(); i < len; i++){
					query.setParameter(i, list.get(i));
				}
			}
			return (int)((Long) query.iterate().next()).intValue();
		} catch (HibernateException e) {
			log.error("getTotalCount()执行失败...", e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> list(String hql, int startPageNum, int pageSize,
			List<Object> lists) {
		Query query = createQuery(hql);
		if(lists != null){
			for (int i = 0,len = lists.size(); i < len; i++){
				query.setParameter(i, lists.get(i));
			}
		}
		List list = new ArrayList();
		try {
			list = query.setFirstResult(startPageNum).setMaxResults(pageSize).list();
		} catch (HibernateException e) {
			log.error("list()执行失败...", e);
			throw e;
		}
		return list;
	}

	public List<T> queryAll(Class<T> clazz) {
		try {
			return (List<T>)getHibernateTemplate().loadAll(clazz);
		} catch (DataAccessException e) {
			log.error("queryAll()执行失败...", e);
			throw e;
		}
	}

	
	@SuppressWarnings("all")
	public List<T> queryAll(Class<T> clazz, String where) {
		try {
			String className = clazz.getSimpleName();
			String queryString = "from " + className + " " + where;
			
		//	System.out.print(className+""+queryString);
			return (List<T>)getHibernateTemplate().find(queryString);
		} catch (DataAccessException e) {
			log.error("queryAll_where()执行失败...", e);
			throw e;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object> queryAll(String hql, List<Object> lists){
		Query query = createQuery(hql);
		if(lists != null){
			for (int i = 0,len = lists.size(); i < len; i++){
				query.setParameter(i, lists.get(i));
			}
		}
		return query.list();
	}
	

	 

	 

	public T getEntity(Class<T> clazz, Serializable id) {
		try {
			return (T) getHibernateTemplate().get(clazz, id);
		} catch (DataAccessException e) {
			log.error("getEntity()执行失败...", e);
			throw e;
		}
	}

	
	@SuppressWarnings("unchecked")
	public T getEntity(Class<T> clazz, String where) {
		String className = clazz.getSimpleName();
		String queryString = "from " + className + " " + where;
		Query query = createQuery(queryString);
		try {
			return (T)query.uniqueResult();
		} catch (HibernateException e) {
			log.error("getEntity()执行失败，结果可能不只一个...", e);
			throw e;
		}
	}
	
	
	public Object getEntity(String hql, List<Object> lists){
		Query query = createQuery(hql);
		if(lists != null){
			for (int i = 0,len = lists.size(); i < len; i++){
				query.setParameter(i, lists.get(i));
			}
		}
		return (Object)query.uniqueResult();
		
//		return queryAll(hql, lists).get(0);
		
	}
	
	
	/**
	 * jdbc查询
	 * @param sql
	 * @param orderSql 
	 * @param length
	 * @return
	 */
	public List<Object[]> getByJdbcSQL(String sql, String orderSql, int start, int pageSize, int length){
		
		//List list=	null;
//		Session session = null;
//		session = this.getHibernateTemplate().getSessionFactory().openSession();
	
//		//Connection conn =getConnection(null); 
//		ResultSet rs =null;   
//		CallableStatement call;
//		String procedureSql = "CALL selectKpi()";
//		//SQLQuery query = session.createSQLQuery("{Call proc(?)}");
//		try {
//			//call = conn.prepareCall("");
//			Query query = session.createSQLQuery(procedureSql);
//	     	list =query.list();
////			call.setString(0,"abc");
////			call.executeQuery();
//			session.close();
//		} catch(Exception e){
//			e.printStackTrace();
//		} finally {
//			
//		}
			
//		if(StringUtils.isEmpty(sql)){
//			return null;
//		}
		Session session = null;
		List<Object[]> list = new ArrayList<Object[]>();
		Connection conn = null;
		try {
			session = this.getHibernateTemplate().getSessionFactory().openSession();
//			Transaction tx = session.beginTransaction();
//			tx.begin();
 		//conn = session.connection();
			Statement ps = conn.createStatement();
			
//			if(StringUtil.notEmpty(orderSql)) {
//				sql += " " + orderSql;
//			}
			if(!sql.toLowerCase().contains("limit") && pageSize > 0) {
				sql += " limit " + start +  ", " + pageSize + " ";
			}
			log.info("getByJdbcSQL:"+sql);
			ps.executeQuery("set @num=0;");
		//	ResultSet rs=ps.executeQuery("select @num;");
			ResultSet rs = ps.executeQuery(sql);
			//ps.executeQuery("set @num=9;");
			//tx.commit();
			while(rs.next()) {
				Object[] objects = new Object[length];
				for(int i=1; i<=length; i++) {
				//	System.out.print(rs.getObject(i).toString());
					objects[i-1] = rs.getObject(i);
				}
				list.add(objects);
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(session != null) {
				try{
					session.close();
				}catch(Exception e){}
			}
			if(conn != null) {
				try{
					
					conn.close();
				}catch(Exception e){}
			}
		}
		
		return list;
	}
	

	public Page getPageByJdbcSQL(Page page,String countSql,final String selSql, String orderSql, int length){
		page.setTotalCount(this.countSQLRecord(countSql));
		final int first = page.getFirst();
		final int pageSize = page.getPageSize();
		if(page.getTotalCount() > 0){
			
			int start = page.getFirst();
			if(start >= 1) {
				start = start-1;
			}
			List rs = this.getByJdbcSQL(selSql, orderSql, start  , page.getPageSize(), length);
			page.setResult(rs);
		}
		return page;
	}
	
	public Page getPageByJdbcSQL1000(Page page, String selSql, String orderSql, int length){
		page.setTotalCount(1000);
		final int first = page.getFirst();
		final int pageSize = page.getPageSize();
		if(page.getTotalCount() > 0){
			
			int start = page.getFirst();
			if(start >= 1) {
				start = start-1;
			}
			List rs = this.getByJdbcSQL(selSql, orderSql, start  , page.getPageSize(), length);
			page.setResult(rs);
		}
		return page;
	}

 
}