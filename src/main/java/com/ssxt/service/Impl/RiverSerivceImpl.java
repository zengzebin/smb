package com.ssxt.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssxt.common.tools.StringTools;
import com.ssxt.dao.RiverDao;
import com.ssxt.dao.RsvrDao;
import com.ssxt.service.RiverSerivce;
import com.ssxt.vo.river.WadiWaterInfo;
import com.ssxt.vo.river.RealTimeWaidWaterState;
 @Service("riverSerivce")
public class RiverSerivceImpl implements RiverSerivce {
	
	@Autowired
	RiverDao   riverDao;

	/**
	 * 返回河道最新数据
	 * @param StationNo 测站码
	 * @param Area 行政区域
	 * @return
	 */
	public List<WadiWaterInfo> findWadiWaterInfo(String[] StationNo, String Area) {
		// TODO Auto-generated method stub
		String  STCDSQL=new String();

		for (int i = 0; i < StationNo.length; i++) {
	 		 if(i+1!=StationNo.length)
	 			STCDSQL+="'"+StationNo[i].trim()+"',";
	 		 else
	 			STCDSQL+="'"+StationNo[i].trim()+"'";
		}
		StringBuffer sql=new StringBuffer("SELECT  DATE_FORMAT(a.TM,'%Y-%m-%d'), a.STCD, a.Z,"
				+ "a.WPTN,a.Q,c.WRZ, b.STMN, b.RVNM, '"+Area+"' addvcd "
				+ "FROM ST_RIVER_R a, "
				+ "ST_STBPRP_B b,ST_RVFCCH_B c,"
				+ "(SELECT  max(tm) TM, stcd STCD FROM ST_RIVER_R GROUP BY stcd) d "
				+ "WHERE (a.TM = d.TM AND a.STCD = d.STCD)    AND "
				+ "(  a.STCD = c.STCD    AND a.TM = c.MODITIME ) AND   "
				+ "a.STCD = b.STCD AND "
				+ "a.stcd IN ("+STCDSQL+")");
		
  
		List<WadiWaterInfo> infolist=new ArrayList<WadiWaterInfo>();
	   List list=riverDao.findWadiWaterInfo(sql.toString());
	   for (int i = 0; i < list.size(); i++) {
		   Object []o=(Object[]) list.get(i);
		   WadiWaterInfo info=new WadiWaterInfo();
		   info.setDateTime(o[0].toString());
		   info.setStationNo(o[1].toString());
		   info.setWaterLevel(StringTools._Float(o[2]));
		   info.setWaterPotential(StringTools._Float(o[3]));
 		   info.setFlow(StringTools._Float(o[4])); 
		   info.setAlertwaterLevel(StringTools._Float(o[5])); 
		   info.setStationName(o[6].toString());
		   info.setRiver(o[7].toString());
		   info.setArea(o[8].toString());
		   infolist.add(info);
	      }
	 
		
		return infolist;
	}
	
	public List<String> getCondition(String type)
	{
		List<String> infolist = new ArrayList<String>();
		if (type.equals("addvcd")) {
//			StringBuffer sql1 = new StringBuffer(
//					"SELECT distinct(IF(f.ParentId is null,f.Name,g.Name)) as Name FROM (SELECT d.ParentId as old,e.* FROM (SELECT distinct(c.ParentId) FROM ST_RIVER_R a,ST_STBPRP_B b,GROUPS c WHERE a.STCD = b.STCD AND b.ADDVCD = c.Code AND b.ADDVCD IS NOT NULL) d,GROUPS e  WHERE d.ParentId = e.Parent) f LEFT JOIN GROUPS g on f.ParentId = g.Parent");

			StringBuffer sql2 = new StringBuffer("SELECT a.Name FROM GROUPS a WHERE a.Code Like '%0000'");
			List list = riverDao.findListBySQL(sql2.toString());
			for (int i = 0; i < list.size(); i++) {
				infolist.add((String) list.get(i));
			}
		} else if (type.equals("bsnm")) {
			StringBuffer sql = new StringBuffer(
					"SELECT distinct(b.BSNM) FROM ST_RIVER_R a,ST_STBPRP_B b WHERE a.STCD = b.STCD AND b.BSNM IS NOT NULL");
			List list = riverDao.findListBySQL(sql.toString());
			for (int i = 0; i < list.size(); i++) {
				infolist.add((String) list.get(i));
			}

		}

		return infolist;
	}
	
	public List<RealTimeWaidWaterState> getRiverBySearch(String DateTime, String AdminArea, String Volley) {

		StringBuffer sql = new StringBuffer(
				"select f.Name,f.BSNM,f.STMN,f.RVNM,e.Z,e.WPTN,e.Q,f.WRZ,e.TM,e.STCD,f.mt from (select c.*,ifnull(d.WRZ,-1) as WRZ,d.MODITIME as mt from (select b.Name,a.* from (select * from ST_STBPRP_B ");

		if (!Volley.equals("null")) {
			sql.append("where BSNM in ( '");
			sql.append(Volley.replaceAll(",", "','"));
			sql.append("')");
		}
		sql.append(") a ,(SELECT * FROM GROUPS where Code Like '%0000' ");
		
		if (!AdminArea.equals("null")) {
			sql.append("and Name in ( '");
			sql.append(AdminArea.replaceAll(",", "','"));
			sql.append("')");
		}
		sql.append(") b where left(a.ADDVCD,2) = left(b.Code,2)) c left join (select * from (select * from ST_RVFCCH_B order by MODITIME desc) r group by STCD order by MODITIME desc)d on c.STCD = d.STCD) f inner join (select * from (select * from ST_RIVER_R ");
		if (!DateTime.equals("null")) {
			sql.append("where TM>='").append(DateTime).append(" 00:00:00'");
			sql.append(" and TM<='").append(DateTime).append(" 23:59:59'");
		}

		sql.append(" order by TM desc)r group by STCD order by TM desc) e on e.STCD = f.STCD ");
		List<RealTimeWaidWaterState> infolist = new ArrayList<RealTimeWaidWaterState>();
		List list = riverDao.findListBySQL(sql.toString());
		for (int i = 0; i < list.size(); i++) {
			Object[] o = (Object[]) list.get(i);
			RealTimeWaidWaterState info = new RealTimeWaidWaterState();
			info.setAdminArea(o[0].toString());
			info.setVolley(o[1].toString());
			info.setStationName(o[2].toString());
			info.setRiver(o[3].toString());
			info.setWaterLevel(StringTools._Float(o[4]));
			info.setWaterPotential(StringTools._Float(o[5]));
			info.setFlow(StringTools._Float(o[6]));
			info.setAlertwaterLevel(StringTools._Float(o[7]));
			infolist.add(info);
		}

		return infolist;
	}
	public String getNewestDatetime(String AdminArea,String Volley){
		String str = "";
		StringBuffer sql = new StringBuffer(
				"select date_format(e.TM,'%Y-%m-%d') from (select c.*,ifnull(d.WRZ,-1) as WRZ,d.MODITIME as mt from (select b.Name,a.* from (select * from ST_STBPRP_B ");

		if (!Volley.equals("null")) {
			sql.append("where BSNM in ( '");
			sql.append(Volley.replaceAll(",", "','"));
			sql.append("')");
		}
		sql.append(") a ,(SELECT * FROM GROUPS where Code Like '%0000' ");
		
		if (!AdminArea.equals("null")) {
			sql.append("and Name in ( '");
			sql.append(AdminArea.replaceAll(",", "','"));
			sql.append("')");
		}
		sql.append(") b where left(a.ADDVCD,2) = left(b.Code,2)) c left join (select * from (select * from ST_RVFCCH_B order by MODITIME desc) r group by STCD order by MODITIME desc)d on c.STCD = d.STCD) f inner join (select * from (select * from ST_RIVER_R ");
		sql.append(" order by TM desc)r group by STCD order by TM desc) e on e.STCD = f.STCD order by TM desc limit 1");
		List list = riverDao.findListBySQL(sql.toString());
		for (int i = 0; i < list.size(); i++) {
			String o = (String) list.get(i);
			str = o.toString();
		}

		return str;
	}
}
