package com.ssxt.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssxt.common.tools.Page;
import com.ssxt.common.tools.StringTools;
import com.ssxt.dao.StpptnrNewsDao;

import com.ssxt.model.StPptnRNew;
import com.ssxt.service.StpptnrNewService;
import com.ssxt.vo.rain.RealTimeDayRainFall;
import com.ssxt.vo.river.WadiWaterInfo;

@Service("stpptnrNewService")
public class StpptnrNewServiceImpl implements StpptnrNewService {

	@Autowired
	StpptnrNewsDao stpptnrNewDao;

	public List<StPptnRNew> getStpptnrNew(String hql) {
		// TODO Auto-generated method stub
		return stpptnrNewDao.getStpptnrNew(hql);
	}
	
	/**
	 * 当天各测试站日雨量，临时接口
	 * @param sql
	 * @return
	 */
	public List<StPptnRNew> getDayRainFal2(String StationName, String bsnm, String time01, String endTime, String SortWay) {
		// TODO Auto-generated method stub
		String name=StationName.trim();
		String order="";
		String hql="from StPptnRNew o WHERE o.stmn='"+name+"'";
				if(!bsnm.equals("")){
					hql+=" AND o.bsnm LIKE '%"+bsnm+"%'"; 
				}
				hql+= " AND o.tm BETWEEN '"+time01 +"' AND '"+ endTime +"' ORDER BY o.tm";
//		List list=stpptnrDao.getDayRainFall(sql);
		return stpptnrNewDao.getStpptnrNew(hql);
	}

	public Page getPageStpptnrNews(Page page, String from, String end) {
		// TODO Auto-generated method stub
		String countHql = "select count(*) from StPptnRNew  as a where  TM>='" + from + " 00:00:00' and  TM<='" + end
				+ " 23:59:59'";
		String selHql = "from StPptnRNew  where tm>='" + from + " 00:00:00' and tm<='" + end + " 23:59:59'";

		return stpptnrNewDao.getPageStpptnrNews(page, countHql, selHql);
	}

	public List<String> getCondition(String type) {
		StringBuffer hql = new StringBuffer("select distinct(" + type + ") from StPptnRNew  where " + type + " !='--'");
		List list = stpptnrNewDao.findListByHql(hql.toString());
		List<String> infolist = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			infolist.add((String) list.get(i));
		}

		return infolist;
	}

	public List<RealTimeDayRainFall> getStpptnrNewBySearch(String DateTime, String AdminArea, String Volley) {

		StringBuffer sql = new StringBuffer(
				"select r.ADDVCD,r.BSNM,r.STMN,r.RVNM,r.DYP from (select * from ST_PPTN_R_NEW ");
		if (!DateTime.equals("null") || !AdminArea.equals("null") || !Volley.equals("null")) {
			sql.append("where ");
		}
		if (!DateTime.equals("null")) {
			sql.append("TM>='").append(DateTime).append(" 00:00:00'");
			sql.append(" and TM<='").append(DateTime).append(" 23:59:59' and ");
		}
		if (!AdminArea.equals("null")) {
			sql.append("ADDVCD in ( '");
			sql.append(AdminArea.replaceAll(",", "','"));
			sql.append("') and ");
		}
		if (!Volley.equals("null")) {
			sql.append("BSNM in ( '");
			sql.append(Volley.replaceAll(",", "','"));
			sql.append("') and ");
		}
		if (!DateTime.equals("null") || !AdminArea.equals("null") || !Volley.equals("null")) {
			sql.delete(sql.length()-4, sql.length());
		}
		sql.append("order by TM desc) r group by STMN order by TM desc");
		List<RealTimeDayRainFall> infolist = new ArrayList<RealTimeDayRainFall>();
		List list = stpptnrNewDao.findListBySQL(sql.toString());
		for (int i = 0; i < list.size(); i++) {
			Object[] o = (Object[]) list.get(i);
			RealTimeDayRainFall info = new RealTimeDayRainFall();
			info.setAdminArea(o[0].toString());
			info.setVolley(o[1].toString());
			info.setStationName(o[2].toString());
			info.setRiver(o[3].toString());
			info.setDayRainFall(StringTools._Float(o[4]));
			infolist.add(info);
		}

		return infolist;
	}
	public String getNewestDatetime(String AdminArea,String Volley,String StationName){
		String str = "";
		StringBuffer sql = new StringBuffer(
				"select date_format(TM,'%Y-%m-%d') from ST_PPTN_R_NEW ");
		if (!AdminArea.equals("null") || !Volley.equals("null") || !StationName.equals("null")) {
			sql.append("where ");
		}
		if (!AdminArea.equals("null")) {
			sql.append("ADDVCD in ( '");
			sql.append(AdminArea.replaceAll(",", "','"));
			sql.append("') and ");
		}
		if (!Volley.equals("null")) {
			sql.append("BSNM in ( '");
			sql.append(Volley.replaceAll(",", "','"));
			sql.append("') and ");
		}
		if (!StationName.equals("null")) {
			sql.append("STMN in ( '");
			sql.append(StationName.replaceAll(",", "','"));
			sql.append("') and ");
		}
		if (!AdminArea.equals("null") || !Volley.equals("null") || !StationName.equals("null")) {
			sql.delete(sql.length()-4, sql.length());
		}
		sql.append("order by TM desc limit 1");
		List list = stpptnrNewDao.findListBySQL(sql.toString());
		for (int i = 0; i < list.size(); i++) {
			String o = (String) list.get(i);
			str = o.toString();
		}

		return str;
	}
	
	public List getStpptnrNewAccBySearch(String DateTime,String AdminArea,String Volley,String StationName,int DayNumber) {

		StringBuffer sql = new StringBuffer("select DATE_FORMAT(TM,'%Y-%m-%d') as date,if(r.DYP>0,r.DYP,0) from (select * from ST_PPTN_R_NEW ");
		if (!DateTime.equals("null") || !AdminArea.equals("null") || !Volley.equals("null") || !StationName.equals("null")) {
			sql.append("where ");
		}
		if (!DateTime.equals("null")) {
			sql.append("TM>=").append("date_sub('"+DateTime+" 00:00:00',interval "+(DayNumber-1)+" day)");
			sql.append(" and TM<='").append(DateTime).append(" 23:59:59' and ");
		}
		if (!AdminArea.equals("null")) {
			sql.append("ADDVCD in ( '");
			sql.append(AdminArea.replaceAll(",", "','"));
			sql.append("') and ");
		}
		if (!Volley.equals("null")) {
			sql.append("BSNM in ( '");
			sql.append(Volley.replaceAll(",", "','"));
			sql.append("') and ");
		}
		if (!StationName.equals("null")) {
			sql.append("STMN in ( '");
			sql.append(StationName.replaceAll(",", "','"));
			sql.append("') and ");
		}
		if (!DateTime.equals("null") || !AdminArea.equals("null") || !Volley.equals("null") || !StationName.equals("null")) {
			sql.delete(sql.length()-4, sql.length());
		}
		sql.append(" order by TM desc) r group by DATE_FORMAT(TM,'%Y-%m-%d') order by TM desc");
		List infolist = new ArrayList<Object>();
		List list = stpptnrNewDao.findListBySQL(sql.toString());
		List<String> datetime = new ArrayList<String>();
		List<String> dyp = new ArrayList<String>();

		for (int i = 0; i < list.size(); i++) {
			Object[] o = (Object[]) list.get(i);
			datetime.add(o[0].toString());
			dyp.add(o[1].toString());
		}
	
		infolist.add(datetime.toArray( new String[0] ));
		infolist.add(dyp.toArray( new String[0] ));
		infolist.add(Integer.toString(list.size()));
		return infolist;
	}
}
