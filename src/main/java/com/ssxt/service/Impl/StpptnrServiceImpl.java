package com.ssxt.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssxt.common.tools.Page;
import com.ssxt.common.tools.StringTools;
import com.ssxt.dao.StpptnrDao;
import com.ssxt.dao.StpptnrNewsDao;
import com.ssxt.model.StPptnR;
import com.ssxt.model.StPptnRNew;
import com.ssxt.service.StpptnrService;
import com.ssxt.service.commonService;
import com.ssxt.vo.rain.AccDailyDayRainFall;
import com.ssxt.vo.rain.Average;
import com.ssxt.vo.rain.DailyDayRainFall;
import com.ssxt.vo.rain.DailyDayRainFallOfEveryStation;
import com.ssxt.vo.rain.DailyPeriodRainFallOfEveryStation;
import com.ssxt.vo.rain.DailyPeriodResult1;
import com.ssxt.vo.rain.DailyPeriodStationEveryDayInfo;

import bsh.StringUtil;
@Service("stpptnrService")
public class StpptnrServiceImpl  implements StpptnrService{
	
     
	@Autowired
	StpptnrDao stpptnrDao;
	
	@Autowired
	StpptnrNewsDao stpptnrNewDao;
     
	
	/**
	 * 当天各测试站日雨量
	 * @param sql
	 * @return
	 */
	public   List <DailyDayRainFallOfEveryStation> getDayRainFall(String StationNo,String time,String SortWay) {
		// TODO Auto-generated method stub
		String  [] No=StationNo.split(",");
    	 String order="";
//	 	String sql="SELECT  stcd, DATE_FORMAT(TM, '%Y-%m-%d') as tt,"
//	 			+ "SUM(CASE WHEN DATE_FORMAT(TM, '%H:%i:%s') >= '00:00:00' AND DATE_FORMAT(TM, '%H:%i:%s') < '23:59:59' THEN drp  END) "
//	 			+ "FROM  StPptnR WHERE TM >= '"+time+" 00:00:00' AND TM <= '"+time+" 23:59:59' and    stcd in(";
//	 	 
	  String sql="SELECT  stcd,"
	 			+ "SUM(dyp)"
	 			+ "FROM  StPptnR WHERE TM= '"+time+" 08:00:00'  and  stcd in(";
	 	 for (int i = 0; i < No.length; i++) {
	 		 if(i+1!=No.length)
			 sql+="'"+No[i].trim()+"',";
	 		 else
	 	     sql+="'"+No[i].trim()+"'";
		}
	//	 sql=sql.substring(0, sql.lastIndexOf(","));
		 
//		 if(SortWay.equals("TimeASC")){
//       	  order=" order by TM asc";
//         }
//		   if(SortWay.equals("TimeDESC")){
//			   order=" order by TM desc";
//			          }
//		   if(SortWay.equals("NoASC")){
//			   order=" order by stcd asc";
//		   }
//		   if(SortWay.equals("NoDESC")){
//			   order=" order by stcd desc";
//		   }
	   sql=sql+") group by stcd"+order;
       List list=stpptnrDao.getDayRainFall(sql);
       System.out.println(sql);
       List <DailyDayRainFallOfEveryStation>dayList=new ArrayList<DailyDayRainFallOfEveryStation>();
       for (int i = 0; i < list.size(); i++) {
    	    Object [] o=(Object [])list.get(i);
    	 
    	   DailyDayRainFallOfEveryStation d=new DailyDayRainFallOfEveryStation(o[0].toString(),StringTools.convertDouble(o[1]));
    	   dayList.add(d);
       }
       return dayList;
//       List <DailyDayRainFall>dayList=new ArrayList<DailyDayRainFall>(); 
//       for (int i = 0; i < list.size(); i++) {
//		Object [] o=(Object [])list.get(i);
//	     DailyDayRainFall day=new DailyDayRainFall(o[0].toString(), o[1].toString(),StringTools.convertDouble(o[2].toString()));
//	     dayList.add(day);
//	   }
	}
	


	/**
	 * 获取数据时间范围
	 * @param time
	 * @return
	 */
	public String getDateTime(String time,String No) {
		 String  [] date=time.split(",");
		String  [] No1=No.split(",");
		String stcd="";
		String sql="SELECT MIN(DATE_FORMAT(TM, '%Y-%m-%d')),  MAX(DATE_FORMAT(TM, '%Y-%m-%d')) "
				+ "FROM StPptnR WHERE tm >= '"+date[0]+" 00:00:00' AND tm <= '"+date[1]+" 23:59:59' and    stcd IN(";
		 
		for (int i = 0; i < No1.length; i++) {
			stcd+="'"+No1[i].trim()+"',";
		}
		stcd=stcd.substring(0, stcd.lastIndexOf(","));
		sql+=stcd+")";
	    List list=stpptnrDao.getDateTime(sql.toString());
	  
	    System.out.println(list.size());
	    Object o[]=(Object[]) list.get(0);
	    if(o[0]==null) return "empty";
	    String interval=o[0].toString()+","+o[1].toString();
		return interval;
	}

   /**
    * 逐日时段雨量分页一个测站
    */
	public DailyPeriodResult1 findStpptnrOne(String[] stcd, int pageNo, String form, String end, String SortWay) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		  
		  Page page=new Page();
		  page.setPageSize(4);//一页几条数据
		  page.setPageNo(pageNo);//查询第几页
		   DailyPeriodResult1 Daily=new DailyPeriodResult1();
		   String order=null;
		 
		          StringBuffer sqlcount=new StringBuffer("SELECT COUNT(*) FROM (SELECT    stcd , DATE_FORMAT(TM,'%Y-%m-%d') DATETIME");
		           StringBuffer sql=new StringBuffer("SELECT    stcd , DATE_FORMAT(TM,'%Y-%m-%d') DATETIME,")
	 			  .append("SUM(CASE  WHEN   DATE_FORMAT(TM,'%H:%i:%s')>='08:00:00' AND  DATE_FORMAT(TM,'%H:%i:%s')<'14:00:00'  THEN DRP END  )eight_fourteen,")
				  .append("SUM(CASE  WHEN   DATE_FORMAT(TM,'%H:%i:%s')>='14:00:00' AND  DATE_FORMAT(TM,'%H:%i:%s')<'20:00:00'  THEN DRP END  )fourteen_twenty ,")
				  .append("SUM(CASE  WHEN   DATE_FORMAT(TM,'%H:%i:%s')>='20:00:00' OR  DATE_FORMAT(TM,'%H:%i:%s')<'02:00:00'  THEN DRP END  )twenty_two ,")
				  .append("SUM(CASE  WHEN   DATE_FORMAT(TM,'%H:%i:%s')>='02:00:00' AND  DATE_FORMAT(TM,'%H:%i:%s')<'08:00:00'  THEN DRP END  ) two_eight,")
				  .append("SUM(CASE  WHEN   DATE_FORMAT(TM,'%H:%i:%s')>='00:00:00' AND  DATE_FORMAT(TM,'%H:%i:%s')<='23:00:00'  THEN DRP END  )  dayRainFall")
	     	      .append(" FROM ST_PPTN_R WHERE stcd IN (");
		  sqlcount.append(" FROM ST_PPTN_R WHERE stcd IN (");
		          for(int i=0;i<stcd.length;i++){
		        	  sql.append("'").append(stcd[i].toString()).append("',");
		        	  sqlcount.append("'").append(stcd[i].toString()).append("',");
		          }
		         String sql1 =sql.substring(0, sql.toString().lastIndexOf(","));
		         String sqlcount1 =sqlcount.substring(0, sqlcount.toString().lastIndexOf(","));
		         sql=new StringBuffer(sql1);
		         sqlcount=new StringBuffer(sqlcount1);
		          sql.append(") and tm>='").append(form).append(" 00:00:00'"); 
		          sql.append(" and tm<='").append(end).append(" 23:59:59'"); 
		          sql.append("GROUP BY stcd,DATETIME");
		         
		          if(SortWay.equals("TimeASC")){
		        	  order=" order by DATETIME asc";
		          }
				   if(SortWay.equals("TimeDESC")){
					   order=" order by DATETIME desc";
					          }
				   if(SortWay.equals("NoASC")){
					   order=" order by stcd asc";
				   }
				   if(SortWay.equals("NoDESC")){
					   order=" order by stcd desc";
				   }
				   sql.append(order.toString());
		          sqlcount.append(") and tm>='").append(form).append(" 00:00:00'"); 
		          sqlcount.append(" and tm<='").append(end).append(" 23:59:59'"); 
		          sqlcount.append("GROUP BY stcd,DATETIME) as b");
		          
	          page=stpptnrDao.findStpptnr(page, sqlcount.toString(), sql.toString());
		    List<DailyPeriodStationEveryDayInfo> infoList=new ArrayList<DailyPeriodStationEveryDayInfo>();
	 		for (int i = 0; page.getResult()!=null&&i < page.getResult().size(); i++) {
	 			Object [] o=(Object[]) page.getResult().get(i);
	 			//System.out.println(o[0]+"  "+o[1]+"  "+o[2]+"  "+o[3]+"  "+o[4]+"  "+o[5]);
	 			DailyPeriodStationEveryDayInfo 	dailyList=new DailyPeriodStationEveryDayInfo(
	 		    o[0].toString(), o[1].toString(),
	 			StringTools.convertDouble(o[2]) , StringTools.convertDouble(o[3]),
	 			StringTools.convertDouble(o[4]),StringTools.convertDouble(o[5]),
	 			StringTools.convertDouble(o[6]));
	 			infoList.add(dailyList);
			}
	 		Daily.setInfoList(infoList);
	 		Daily.setCount(page.getTotalCount());
	  	
	 		return Daily;
	}

	/**
     * 逐日时段雨量多个测站
     * @param stcd 测站
     * @param time 时间
     * @param SortWay 排序
     * @return DailyPeriodResult1
     */
    public DailyPeriodResult1 findStpptnrMany(String[] stcd, String time, String SortWay) {
	// TODO Auto-generated method stub
		   String order=null;
		   DailyPeriodResult1 Daily=new DailyPeriodResult1();

            StringBuffer sql=new StringBuffer("SELECT    stcd , DATE_FORMAT(TM,'%Y-%m-%d') DATETIME,")
		  .append("SUM(CASE  WHEN   DATE_FORMAT(TM,'%H:%i:%s')>='08:00:00' AND  DATE_FORMAT(TM,'%H:%i:%s')<'14:00:00'  THEN DRP END  )eight_fourteen,")
		  .append("SUM(CASE  WHEN   DATE_FORMAT(TM,'%H:%i:%s')>='14:00:00' AND  DATE_FORMAT(TM,'%H:%i:%s')<'20:00:00'  THEN DRP END  )fourteen_twenty ,")
		  .append("SUM(CASE  WHEN   DATE_FORMAT(TM,'%H:%i:%s')>='20:00:00' OR  DATE_FORMAT(TM,'%H:%i:%s')<'02:00:00'  THEN DRP END  )twenty_two ,")
		  .append("SUM(CASE  WHEN   DATE_FORMAT(TM,'%H:%i:%s')>='02:00:00' AND  DATE_FORMAT(TM,'%H:%i:%s')<'08:00:00'  THEN DRP END  ) two_eight,")
		  .append("SUM(CASE  WHEN   DATE_FORMAT(TM,'%H:%i:%s')>='00:00:00' AND  DATE_FORMAT(TM,'%H:%i:%s')<='23:00:00'  THEN DRP END  )  dayRainFall")
 	      .append(" FROM ST_PPTN_R WHERE stcd IN (");
            for(int i=0;i<stcd.length;i++){
	        	  sql.append("'").append(stcd[i].toString()).append("',");
	        	  
	          }
            String sql1 =sql.substring(0, sql.toString().lastIndexOf(","));
            sql=new StringBuffer(sql1);
            sql.append(") and tm>='").append(time).append(" 00:00:00'"); 
	        sql.append(" and tm<='").append(time).append(" 23:59:59'"); 
	        sql.append("GROUP BY stcd,DATETIME");
	         
	          if(SortWay.equals("TimeASC")){
	        	  order=" order by DATETIME asc";
	          }
			   if(SortWay.equals("TimeDESC")){
				   order=" order by DATETIME desc";
				          }
			   if(SortWay.equals("NoASC")){
				   order=" order by stcd asc";
			   }
			   if(SortWay.equals("NoDESC")){
				   order=" order by stcd desc";
			   }
			   sql.append(order.toString());
		List list=stpptnrDao.findStpptnrMany(sql.toString());
		List<DailyPeriodStationEveryDayInfo> infoList=new ArrayList<DailyPeriodStationEveryDayInfo>();
 		for (int i = 0; list!=null&&i < list.size(); i++) {
 			Object [] o=(Object[]) list.get(i);
 			//System.out.println(o[0]+"  "+o[1]+"  "+o[2]+"  "+o[3]+"  "+o[4]+"  "+o[5]);
 			DailyPeriodStationEveryDayInfo 	dailyList=new DailyPeriodStationEveryDayInfo(
 		    o[0].toString(), o[1].toString(),
 			StringTools.convertDouble(o[2]) , StringTools.convertDouble(o[3]),
 			StringTools.convertDouble(o[4]),StringTools.convertDouble(o[5]),
 			StringTools.convertDouble(o[6]));
 			infoList.add(dailyList);
		}
 		Daily.setInfoList(infoList);
 		Daily.setCount(infoList.size());
   	  return Daily;
		
 
}
     /**
     * 平均值
     * @param stcd 测站
     * @param from 开始时间
     * @param end  结束时间
     * @return
     */
	public List<Average> findStpptnrCalculateAverage(String  stcd, String from, String end,int type) {
		// TODO Auto-generated method stub
		  String  [] No=stcd.split(",");
		String stcds="";
	   List <String>dateTime=new ArrayList<String>();
	   String startTime=from;
	   while(true){
		   startTime=StringTools.getSpecifiedDayAfter(startTime);
		   if(!startTime.equals(end)&&!from.equals(end)){
			   dateTime.add(startTime);
		   }else{
			   break;
		   }
		 
		}
	   //	 			+ "SUM(CASE WHEN DATE_FORMAT(TM, '%Y-%m-%d') >= '00:00:00' AND DATE_FORMAT(TM, '%H:%i:%s') < '23:00:00' THEN drp  END)/4 as a ,"
//     + "SUM(CASE WHEN DATE_FORMAT(TM, '%H:%i:%s') >= '00:00:00' AND DATE_FORMAT(TM, '%H:%i:%s') < '23:00:00' THEN drp  END) as b "
		//hql+=stcds+") group by stcd, DATE_FORMAT(TM, '%Y-%m-%d') ";

		StringBuffer hql=new StringBuffer("SELECT  stcd,");
		hql.append("SUM(CASE WHEN DATE_FORMAT(TM, '%Y-%m-%d') ='"+from+"' THEN drp  END) as start");
        for (int i = 0;dateTime!=null&&i < dateTime.size(); i++) {
        	hql.append(",SUM(CASE WHEN DATE_FORMAT(TM, '%Y-%m-%d') ='"+dateTime.get(i).toString()+" ' THEN drp  END) as d"+i);
       	}
        if(!from.equals(end))
		hql.append(",SUM(CASE WHEN DATE_FORMAT(TM, '%Y-%m-%d') ='"+end+"' THEN drp  END) as startend ");
       
		hql.append(" FROM  StPptnR WHERE TM >= '"+from+" 00:00:00' AND TM <= '"+end+" 23:00:00' and    stcd in("); 
	 	 
		for (int i = 0; i < No.length; i++) {
			stcds+="'"+No[i].trim()+"',";
		}
		stcds=stcds.substring(0, stcds.lastIndexOf(","));
		hql.append(stcds).append(")   GROUP BY stcd  ORDER BY TM ASC  ");
		
		List list= stpptnrDao.findStpptnrCalculateAverage(hql.toString());
		if(type==2) return list;
  	      List <Average> listAverage=new ArrayList<Average>();
 		    for (int i = 0; i < list.size(); i++) {
		    		Object[]o=(Object[])list.get(i);
		         	Average avg=new Average();
		         	double [] averagePeriodRainFall=new double[o.length-1]; //每天平均时段雨量
		         	double [] DayRainFall=new double[o.length-1]; //每天日雨量 
		    		int len=0;
		    		for (int j = 0; j < o.length; j++) {
		    			avg.setStcd(o[0].toString());
		    			if(o[j]==null)o[j]=0;
						if(j >=1){
							averagePeriodRainFall[len]=StringTools.convertDouble(o[j])/4;
							DayRainFall[len]=StringTools.convertDouble(o[j]);
							len++;
						}  
						if(j==o.length-1){
							avg.setAveragePeriodRainFall(averagePeriodRainFall);
							avg.setDayRainFall(DayRainFall);
							listAverage.add(avg);
						}	 
					 
					
					}
		      }
 		    
 		   
		 return listAverage;
		
	}


	/**
     * 24小时雨量
     * @param stcd 测站
     * @param from 开始时间
     * @param end  结束时间
     *  @param type   1表示平均时段与平均日雨 2平均日雨
     * @return   
     */
	public List<DailyPeriodRainFallOfEveryStation> findStpptnrIntervals24(String [] stcd, String from, String end) {
		// TODO Auto-generated method stub
		String STCD="";
		for (int i = 0; i < stcd.length; i++) {
			STCD+="'"+stcd[i]+"',";
		}
		if(stcd.length>0)
		STCD=STCD.substring(0, STCD.length()-1);
		StringBuffer hql=new StringBuffer("SELECT   stcd,");
//		hql.append("SUM(CASE WHEN DATE_FORMAT(TM,'%H:%i:%s')>='08:00:00' and DATE_FORMAT(TM,'%H:%i:%s')<='08:59:59'  THEN drp  END)");
 	    for (int i = 0; i <24; i++) {
        hql.append("SUM(CASE WHEN DATE_FORMAT(TM,'%H')='");
      
        if(i==0)
        hql.append("00");
        else if(i<10)
            hql.append("0"+i);
        else
         hql.append(i);
        if(i!=23)
           hql.append("' THEN drp ELSE 0  END) as h"+i+",") ;
        else
          hql.append("' THEN drp  ELSE 0 END) as h"+i) ;
		}
 	 //  String sqlcut=hql.substring(0, hql.length()-1);
 	    
		hql.append(" from StPptnR    WHERE    tm>='"+from+" 08:00:00' AND  tm<'"+end+" 08:00:00'  and  stcd IN("+STCD+") GROUP BY stcd");
		System.out.println(hql.toString());
	    List list=stpptnrDao.findStpptnrIntervals24(hql.toString());
	    List<DailyPeriodRainFallOfEveryStation> list24=new ArrayList<DailyPeriodRainFallOfEveryStation> ();
	 
	    for (int i = 0; i < list.size(); i++) {
	    	DailyPeriodRainFallOfEveryStation  hourRian=new DailyPeriodRainFallOfEveryStation();
	    	   double [] dpr=new double[24];
	    	Object []o=(Object[]) list.get(i);
	    
	    	for (int j = 0; j < 24; j++) {
	    		dpr[j]=StringTools.convertDouble(o[j+1]);
			}
	    	hourRian.setStationNo(o[0].toString());
	    	hourRian.setRainFallOfEveryHour(dpr);
	    	list24.add(hourRian);
		}
//		StringBuffer hql=new StringBuffer("SELECT     stcd,");
//		  hql.append("SUM(CASE WHEN DATE_FORMAT(TM, '%H:%i:%s') >= '00:00:00' AND DATE_FORMAT(TM, '%H:%i:%s') < '23:59:59' THEN drp  END") ;
//		//hql.append("SUM(CASE  WHEN   DATE_FORMAT(TM,'%H:%i:%s')>='08:00:00' AND  DATE_FORMAT(TM,'%H:%i:%s')<'08:59:59'  THEN DRP    END  )");
//		hql.append(" from StPptnR    WHERE    TM='2015-06-13 08:00:00' and  stcd IN('10800100') GROUP BY stcd");
//		System.out.println(hql.toString());
//	    List list=stpptnrDao.findStpptnrIntervals24(hql.toString());
	   
	  //  select stcd from StPptnR where      tm='2015-06-13 08:00:00' and  stcd IN('10800100')
		return list24;
	}


    /**
     * 统计项目
     */
	public List<AccDailyDayRainFall> findStpptnrDayRainFall(String[] StationNo, String DateTime, int DayNumber) {
		// TODO Auto-generated method stub
		
		StringBuffer hql=new StringBuffer("select  stcd,tm,");
		StringBuffer whereday=new StringBuffer();
		String  STCD=new String();
		for (int i = 0; i < StationNo.length; i++) {
			STCD+="'"+StationNo[i]+"',";
		}
		if(StationNo.length>0)
			STCD=STCD.substring(0, STCD.length()-1);
		
 		for(int i=1;i<=DayNumber;i++){
 			String time=StringTools.getSpecifiedDayAfter(DateTime, "yyy-mm-dd", i);
 			if(i!=DayNumber){
 				hql.append("sum(CASE  WHEN  tm='"+time+" 08:00:00' THEN dyp ELSE 0.0 END) as d"+i+",");
 	            whereday.append(" tm='"+time+" 08:00:00' OR");
 			}else{
 				hql.append("sum(CASE  WHEN  tm='"+time+" 08:00:00' THEN dyp ELSE 0.0 END) as d"+i+" ");
 	            whereday.append(" tm='"+time+" 08:00:00'");
 			}
           
          }    
 		hql.append(" from StPptnR where stcd in(").append(STCD).append(") and ").append("(").append(whereday).append(") group by stcd ORDER BY TM");
 		System.out.println(hql.toString());
 
        List list=stpptnrDao.findStpptnrDayRainFall(hql.toString());
        List<AccDailyDayRainFall> listInfo=new ArrayList<AccDailyDayRainFall>();
         for (int i = 0; i < list.size(); i++) {
			  //double []totaldRain=new double[DayNumber];
        	 float []totaldRain=new float[DayNumber];
			  Object []o=(Object[]) list.get(i);
			  for (int j = 0; j < o.length; j++) {
				  if(j>1)
				  totaldRain[j-2]=StringTools._Float(o[j]);
			}
			  AccDailyDayRainFall day=new AccDailyDayRainFall(totaldRain, o[0].toString());
			  listInfo.add(day);
		}
		return listInfo;
	}

	


    
	 
}
