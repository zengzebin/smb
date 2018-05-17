package com.ssxt.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssxt.common.tools.StringTools;
import com.ssxt.dao.RiverDao;
import com.ssxt.dao.RsvrDao;
import com.ssxt.service.RsvrSerivce;
import com.ssxt.vo.river.RealTimeWaidWaterState;
import com.ssxt.vo.river.WadiWaterInfo;
import com.ssxt.vo.rsvr.RealTimeReservoirWaterState;
import com.ssxt.vo.rsvr.ReservoirWaterInfoInfo;


@Service("rsvrService")
public class RsvrServiceImpl implements RsvrSerivce{

	
	@Autowired
	RsvrDao    rsvrDao;

	public List<ReservoirWaterInfoInfo> findRsvrInfo(String[] StationNo, String Area) {
		// TODO Auto-generated method stub
		String  STCDSQL=new String();

		for (int i = 0; i < StationNo.length; i++) {
	 		 if(i+1!=StationNo.length)
	 			STCDSQL+="'"+StationNo[i].trim()+"',";
	 		 else
	 			STCDSQL+="'"+StationNo[i].trim()+"'";
		}
		StringBuffer sql=new StringBuffer("SELECT  a.STCD,b.STMN,b.RVNM,a.RZ,a.RWPTN,a.INQ,a.W,a.Altitude,DATE_FORMAT(a.TM,'%Y-%m-%d') tm  "
				+ "FROM ST_RSVR_R a,ST_STBPRP_B b,"
				+ "(SELECT  max(tm) TM, stcd STCD FROM ST_RSVR_R GROUP BY stcd) c  "
				+ "WHERE a.stcd=b.stcd AND  a.tm=c.tm AND a.stcd=c.stcd and  " 
				+ "a.stcd IN ("+STCDSQL+")");
		
  
		List<ReservoirWaterInfoInfo> infolist=new ArrayList<ReservoirWaterInfoInfo>();
	    List list=rsvrDao.findRsvrInfo(sql.toString());
	   for (int i = 0; i < list.size(); i++) {
		   Object []o=(Object[]) list.get(i);
		   ReservoirWaterInfoInfo info=new ReservoirWaterInfoInfo();
		   info.setStationNo(o[0].toString());
		   info.setStationName(o[1].toString());
		   info.setRiverName(o[2].toString());
		   info.setWaterLevel(StringTools._Float(o[3]));
		   info.setWaterPotential(StringTools._Integer(o[4]));
		   info.setInFlow(StringTools._Float(o[5]));
		   info.setWaterStorageCapacity(StringTools._Float(o[6]));
		   info.setDamHeight(StringTools._Float(o[7]));
		   info.setDateTime(o[8].toString());
		   infolist.add(info);
	      }
	 
		
		return infolist;
	}
	
	public List<String> getCondition(String type)
	{
		List<String> infolist = new ArrayList<String>();
		if (type.equals("addvcd")) {
//			StringBuffer sql1 = new StringBuffer(
//					"SELECT distinct(IF(f.ParentId is null,f.Name,g.Name)) as Name FROM (SELECT d.ParentId as old,e.* FROM (SELECT distinct(c.ParentId) FROM ST_RSVR_R a,ST_STBPRP_B b,GROUPS c WHERE a.STCD = b.STCD AND b.ADDVCD = c.Code AND b.ADDVCD IS NOT NULL) d,GROUPS e  WHERE d.ParentId = e.Parent) f LEFT JOIN GROUPS g on f.ParentId = g.Parent");

			StringBuffer sql2 = new StringBuffer("SELECT a.Name FROM GROUPS a WHERE a.Code Like  '%0000'");
			List list = rsvrDao.findListBySQL(sql2.toString());
			for (int i = 0; i < list.size(); i++) {
				infolist.add((String) list.get(i));
			}
		} else if (type.equals("bsnm")) {
			StringBuffer sql = new StringBuffer(
					"SELECT distinct(b.BSNM) FROM ST_RSVR_R a,ST_STBPRP_B b WHERE a.STCD = b.STCD AND b.BSNM IS NOT NULL");
			List list = rsvrDao.findListBySQL(sql.toString());
			for (int i = 0; i < list.size(); i++) {
				infolist.add((String) list.get(i));
			}

		}

		return infolist;
	}

	public List<RealTimeReservoirWaterState> getRsvrBySearch(String DateTime, String AdminArea, String Volley) {

		StringBuffer sql = new StringBuffer(
				"select f.Name,f.BSNM,f.STMN,f.RVNM,e.RZ,e.RWPTN,e.INQ,e.W,e.Altitude,e.TM,e.STCD,f.mt from (select c.*,ifnull(d.WRZ,-1) as WRZ,d.MODITIME as mt from (select b.Name,a.* from (select * from ST_STBPRP_B ");

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
		sql.append(") b where left(a.ADDVCD,2) = left(b.Code,2)) c left join (select * from (select * from ST_RVFCCH_B order by MODITIME desc) r group by STCD order by MODITIME desc)d on c.STCD = d.STCD) f inner join (select * from (select * from ST_RSVR_R ");
		if (!DateTime.equals("null")) {
			sql.append("where TM>='").append(DateTime).append(" 00:00:00'");
			sql.append(" and TM<='").append(DateTime).append(" 23:59:59'");
		}

		sql.append(" order by TM desc)r group by STCD order by TM desc) e on e.STCD = f.STCD ");
		List<RealTimeReservoirWaterState> infolist = new ArrayList<RealTimeReservoirWaterState>();
		List list = rsvrDao.findListBySQL(sql.toString());
		for (int i = 0; i < list.size(); i++) {
			Object[] o = (Object[]) list.get(i);
			RealTimeReservoirWaterState info = new RealTimeReservoirWaterState();
			info.setAdminArea(o[0].toString());
			info.setVolley(o[1].toString());
			info.setStationName(o[2].toString());
			info.setRiver(o[3].toString());
			info.setWaterLevel(StringTools._Float(o[4]));
			info.setWaterPotential(StringTools._Float(o[5]));
			info.setInFlow(StringTools._Float(o[6]));
			info.setWaterStorageCapacity(StringTools._Float(o[7]));
			info.setDamHeight(StringTools._Float(o[8]));
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
		sql.append(") b where left(a.ADDVCD,2) = left(b.Code,2)) c left join (select * from (select * from ST_RVFCCH_B order by MODITIME desc) r group by STCD order by MODITIME desc)d on c.STCD = d.STCD) f inner join (select * from (select * from ST_RSVR_R ");
		sql.append(" order by TM desc)r group by STCD order by TM desc) e on e.STCD = f.STCD order by TM desc limit 1");
		List list = rsvrDao.findListBySQL(sql.toString());
		for (int i = 0; i < list.size(); i++) {
			String o = (String) list.get(i);
			str = o.toString();
		}

		return str;
	}
}
