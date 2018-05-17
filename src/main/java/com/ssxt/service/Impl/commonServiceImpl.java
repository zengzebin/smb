package com.ssxt.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssxt.common.tools.Page;
import com.ssxt.common.tools.StringTools;
import com.ssxt.dao.commonDao;
import com.ssxt.model.User;
import com.ssxt.service.commonService;
import com.ssxt.vo.rain.DailyPeriodResult1;
import com.ssxt.vo.rain.DailyPeriodStationEveryDayInfo;

//@Service("commonService")
public class commonServiceImpl implements commonService{
   
//  @Autowired
//  private commonDao commonDao;
//
//public User find(int id) {
//	// TODO Auto-generated method stub
//	return null;
//}
//
//public DailyPeriodResult1 findStpptnr(String[] stcd, int pageNo, String form, String end, String SortWay) {
//	// TODO Auto-generated method stub
//	  System.out.println("========common========");
//	  Page page=new Page();
//	  page.setPageSize(4);//一页几条数据
//	  page.setPageNo(pageNo);//查询第几页
//	   DailyPeriodResult1 Daily=new DailyPeriodResult1();
//	   String order=null;
//	 
//	          StringBuffer sqlcount=new StringBuffer("SELECT COUNT(*) FROM (SELECT    stcd , DATE_FORMAT(TM,'%Y-%m-%d') DATETIME");
//	           StringBuffer sql=new StringBuffer("SELECT    stcd , DATE_FORMAT(TM,'%Y-%m-%d') DATETIME,")
// 			  .append("SUM(CASE  WHEN   DATE_FORMAT(TM,'%H:%i:%s')>='08:00:00' AND  DATE_FORMAT(TM,'%H:%i:%s')<'14:00:00'  THEN DRP END  )eight_fourteen,")
//			  .append("SUM(CASE  WHEN   DATE_FORMAT(TM,'%H:%i:%s')>='14:00:00' AND  DATE_FORMAT(TM,'%H:%i:%s')<'20:00:00'  THEN DRP END  )fourteen_twenty ,")
//			  .append("SUM(CASE  WHEN   DATE_FORMAT(TM,'%H:%i:%s')>='20:00:00' OR  DATE_FORMAT(TM,'%H:%i:%s')<'02:00:00'  THEN DRP END  )fourteen_twenty ,")
//			  .append("SUM(CASE  WHEN   DATE_FORMAT(TM,'%H:%i:%s')>='02:00:00' AND  DATE_FORMAT(TM,'%H:%i:%s')<'08:00:00'  THEN DRP END  ) two_eight,")
//			  .append("SUM(CASE  WHEN   DATE_FORMAT(TM,'%H:%i:%s')>='00:00:00' AND  DATE_FORMAT(TM,'%H:%i:%s')<'23:00:00'  THEN DRP END  )  dayRainFall")
//     	      .append(" FROM ST_PPTN_R WHERE stcd IN (");
//	  sqlcount.append(" FROM ST_PPTN_R WHERE stcd IN (");
//	          for(int i=0;i<stcd.length;i++){
//	        	  sql.append("'").append(stcd[i].toString()).append("',");
//	        	  sqlcount.append("'").append(stcd[i].toString()).append("',");
//	          }
//	         String sql1 =sql.substring(0, sql.toString().lastIndexOf(","));
//	         String sqlcount1 =sqlcount.substring(0, sqlcount.toString().lastIndexOf(","));
//	         sql=new StringBuffer(sql1);
//	         sqlcount=new StringBuffer(sqlcount1);
//	          sql.append(") and tm>='").append(form).append(" 00:00:00'"); 
//	          sql.append(" and tm<='").append(end).append(" 23:59:59'"); 
//	          sql.append("GROUP BY stcd,DATETIME");
//	         
//	          if(SortWay.equals("TimeASC")){
//	        	  order=" order by DATETIME asc";
//	          }
//			   if(SortWay.equals("TimeDESC")){
//				   order=" order by DATETIME desc";
//				          }
//			   if(SortWay.equals("NoASC")){
//				   order=" order by stcd asc";
//			   }
//			   if(SortWay.equals("NoDESC")){
//				   order=" order by stcd desc";
//			   }
//			   sql.append(order.toString());
//	          sqlcount.append(") and tm>='").append(form).append(" 00:00:00'"); 
//	          sqlcount.append(" and tm<='").append(end).append(" 23:59:59'"); 
//	          sqlcount.append("GROUP BY stcd,DATETIME) as b");
//	          
//          page=commonDao.findStpptnr(page, sqlcount.toString(), sql.toString());
//	    List<DailyPeriodStationEveryDayInfo> infoList=new ArrayList<DailyPeriodStationEveryDayInfo>();
// 		for (int i = 0; page.getResult()!=null&&i < page.getResult().size(); i++) {
// 			Object [] o=(Object[]) page.getResult().get(i);
// 			System.out.println(o[0]+"  "+o[1]+"  "+o[2]+"  "+o[3]+"  "+o[4]+"  "+o[5]);
// 			DailyPeriodStationEveryDayInfo 	dailyList=new DailyPeriodStationEveryDayInfo(
// 		    o[0].toString(), o[1].toString(),
// 			StringTools.convertDouble(o[2]) , StringTools.convertDouble(o[3]),
// 			StringTools.convertDouble(o[4]),StringTools.convertDouble(o[5]),
// 			StringTools.convertDouble(o[6]));
// 			infoList.add(dailyList);
//		}
// 		Daily.setInfoList(infoList);
// 		Daily.setCount(page.getTotalCount());
//  	
// 		return Daily;
//	 
//}
//  
	 

}
