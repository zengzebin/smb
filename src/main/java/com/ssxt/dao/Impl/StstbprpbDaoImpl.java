package com.ssxt.dao.Impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.ssxt.dao.StstbprpbDao;

import com.ssxt.model.StStbprpB;
import com.ssxt.model.StcdType;
import com.ssxt.vo.rain.MonthCumulation;

@Repository("ststbprpbDao")
public class StstbprpbDaoImpl extends BaseDaoImpl<StStbprpB>implements StstbprpbDao {

	public List<StStbprpB> findgetStstbprp(String[] code) {
		// TODO Auto-generated method stub
		System.out.println(code.length);
		StringBuffer hql = new StringBuffer("from StStbprpB  where addvcd ");
		for (int i = 0; i < code.length; i++) {
			if (i == 0)
				hql.append("like'" + code[0] + "%'  ");
			if (i != 0)
				hql.append(" or addvcd like'" + code[i] + "%' ");
		}
		List<StStbprpB> list = this.getByHql(hql.toString());
		return list; // return null;
	}

	public List<StStbprpB> findgetStstbprp(String[] code, String type) {
		// TODO Auto-generated method stub
		System.out.println(code.length);
		StringBuffer hql = new StringBuffer("from StStbprpB  where addvcd ");
		for (int i = 0; i < code.length; i++) {
			if (i == 0)
				hql.append("like'" + code[0] + "%'  ");
			if (i != 0)
				hql.append(" or addvcd like'" + code[i] + "%' ");
		}
		if (!type.equals("")) {
			hql.append(" AND stcdtype.type='" + type + "'");
		}
		List<StStbprpB> list = this.getByHql(hql.toString());
		return list; // return null;
	}

	public List<StStbprpB> findgetStstbprp3(String[] bounds, String type) {
		// TODO Auto-generated method stub
		// System.out.println(code.length);
		StringBuffer hql = new StringBuffer("from StStbprpB  where lgtd BETWEEN " + bounds[0] + " AND " + bounds[2]
				+ " AND (lttd BETWEEN  " + bounds[1] + " AND " + bounds[3] + ")");
		if (!type.equals("")) {
			hql.append(" AND stcdtype.type='" + type + "'");
		}
		List<StStbprpB> list = this.getByHql(hql.toString());
		return list; // return null;
	}

	public List<String> getAllLiuyuNames(String type) {
		// TODO Auto-generated method stub
		String sql = "select distinct(BSNM) from ST_STBPRP_B";
		if (!type.equals(""))
			sql += " WHERE STTP='" + type + "'";
		List list = this.getListBySQL(sql.toString());
		return list;
	}

	public List<StStbprpB> getSitesInLiuyu(String liuyuName, String type) {
		// TODO Auto-generated method stub
		String hql = "from StStbprpB o WHERE o.bsnm='" + liuyuName + "'";
		if (!type.equals(""))
			hql += " AND o.stcdtype.type='" + type + "'";
		List list = this.getByHql(hql);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<MonthCumulation> sitestatistics(String sitename, String startTime, String endTime) throws Exception {
		Session ses = this.getHibernateTemplate().getSessionFactory().openSession();
		// Session ses=this.getSession(true);
		String sql = "SELECT ADDVCD as province,STMN as sitename,YEAR(TM) AS year,MONTH(TM) as month,SUM(DYP) as dypcummulation "
				+ "FROM rainwater.ST_PPTN_R_NEW WHERE STMN='" + sitename + "' ";

		if (startTime != null) {
			sql += " and TM>='" + startTime + "' ";
		}

		if (endTime != null) {
			sql += " and TM<='" + endTime + "' ";
		}

		sql += "GROUP BY YEAR(TM),MONTH(TM)";
		SQLQuery query = ses.createSQLQuery(sql);
		// System.out.println(sql);
		query.setResultTransformer(Transformers.aliasToBean(MonthCumulation.class));
		List<MonthCumulation> lis = null;
		try {
			lis = query.list();
		} catch (Exception e) {
			// e.printStackTrace();
			throw e;
		} finally {
			if (ses != null) {
				ses.close();
			}
		}
		return lis;
	}

}
