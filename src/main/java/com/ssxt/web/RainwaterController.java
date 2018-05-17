package com.ssxt.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.JSONObject;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ssxt.common.tools.FileTools;
import com.ssxt.common.tools.StringTools;
import com.ssxt.model.City;
import com.ssxt.model.StPptnRNew;
import com.ssxt.model.StStbprpB;
import com.ssxt.service.StpptnrNewService;
import com.ssxt.service.StpptnrService;
import com.ssxt.service.StstbprpbService;
import com.ssxt.service.commonService;
import com.ssxt.vo.rain.AccDailyDayRainFall;
import com.ssxt.vo.rain.AdminAreaInfo;
import com.ssxt.vo.rain.AreaInfo;
import com.ssxt.vo.rain.Average;
import com.ssxt.vo.rain.AverageDayRainFall;
import com.ssxt.vo.rain.DailyDayRainFall;
import com.ssxt.vo.rain.DailyDayRainFallOfEveryStation;
import com.ssxt.vo.rain.DailyPeriodRainFallOfEveryStation;
import com.ssxt.vo.rain.DailyPeriodResult1;


//import com.dsoft.model.Address;
//import com.dsoft.model.Identity;
//import com.smart.dao.AddressDao;
//import com.smart.dao.IdentityDao;

@Controller
@RequestMapping(value = "/rainwater")
public class RainwaterController {
	//测站业务类
	@Autowired
	StstbprpbService  ststbprpbService;
	
	//雨量业务类
	@Autowired
	StpptnrService stpptnrService;
	
	@Autowired	
	StpptnrNewService stpptnrNewService;
	
	//公共
//		@Autowired
//		commonService   commonService;
   /**
    * 测试站信息
    * @param request
    * @param response
    * @throws Exception
    */
	
	@RequestMapping(value = "/ststbprpb",method =RequestMethod.GET) 
   public void findStstbprpbInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json"); 
			 //@PathVariable
			 // String	provinceNmae=request.getParameter("provinceNmae");
			  String json=new String();
			  String AreaNo []=StringTools.convertUTF8(request.getParameter("AreaNo")).split(",");
	 		  String Action=StringTools.convertUTF8(request.getParameter("Action"));
	 		  AdminAreaInfo info=new AdminAreaInfo();
              if(Action.equals("GetStationInfo")){
            	
    			  List <AreaInfo>infoList=new ArrayList<AreaInfo>(); 
    			 List<StStbprpB> list=ststbprpbService.findgetStstbprp(AreaNo);
    			  System.out.println(list.size());
    			  for (int i = 0; i < list.size(); i++) {
    				  StStbprpB s=list.get(i);
    				  AreaInfo a=new AreaInfo();
    				  a.setStationNo(s.getStcd());//测站编码
    				  a.setStationName(s.getStmn());//测站名称
    				  a.setAdminAreaName(s.getGroups().getName()); //行政区划名称
    				  a.setValleyName(s.getBsnm()); //流域名称
    				  a.setStationType(s.getStcdtype().getTypeName());//测站类型
    				  infoList.add(a);
    			}
    			  info.setInfoList(infoList);
    			  info.setError(0);
              }else {
            	  info.setInfoList(null);
    			  info.setError(0);
              }
              JSONObject jsonObject = JSONObject.fromObject(info);
              System.out.println(jsonObject.toString());		
		      PrintWriter out = response.getWriter();
		      out.print(jsonObject.toString());  
		       out.flush();
   }
	
	@RequestMapping(value = "/ststbprpb2",method =RequestMethod.GET) 
	public void findStstbprpbInfo2(HttpServletRequest request, HttpServletResponse response) throws Exception {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json"); 
				 //@PathVariable
				 // String	provinceNmae=request.getParameter("provinceNmae");
				  String type="";
				  if(request.getParameter("type")!=null && request.getParameter("type").trim().length()>0){
					  type=request.getParameter("type").trim();
				  }
				  String AreaNo []=StringTools.convertUTF8(request.getParameter("AreaNo")).split(",");
		 		  String Action=StringTools.convertUTF8(request.getParameter("Action"));
		 		  AdminAreaInfo info=new AdminAreaInfo();
	              if(Action.equals("GetStationInfo")){
	    			 List<AreaInfo> infoList=new ArrayList<AreaInfo>(); 
//	    			 List<StStbprpB> list=ststbprpbService.findgetStstbprp(AreaNo);
	    			 List<StStbprpB> list=ststbprpbService.findgetStstbprp(AreaNo, type);
	    			  System.out.println(list.size());
	    			  info.setInfoList2(list);
	    			  info.setError(0);
	              }else {
	            	  info.setInfoList2(null);
	    			  info.setError(0);
	              }
	              JSONObject jsonObject = JSONObject.fromObject(info);
	              System.out.println(jsonObject.toString());		
			      PrintWriter out = response.getWriter();
			      out.print(jsonObject.toString());  
			       out.flush();
	   }
	
	@RequestMapping(value = "/ststbprpb3",method =RequestMethod.GET) 
	public void ststbprpb3(HttpServletRequest request, HttpServletResponse response) throws Exception {
			int err=0;
			AdminAreaInfo info=new AdminAreaInfo();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json"); 
			  String[] bounds=StringTools.convertUTF8(request.getParameter("bbox")).split(",");
			  String type="";
			  if(request.getParameter("type")!=null && request.getParameter("type").trim().length()>0){
				  type=request.getParameter("type").trim();
			  }
			  if(bounds.length!=4){
				  err=1;
			  }else{
				  try{
					  double left=Double.valueOf(bounds[0].trim());
					  double bottom=Double.valueOf(bounds[1].trim());
					  double right=Double.valueOf(bounds[2].trim());
					  double top=Double.valueOf(bounds[3].trim());
					  if(left>=right || bottom>=top)
						  err=3;
				  }catch(Exception e){
					  err=2;
				  }
			  }
			  info.setError(err);
			  if(err==0){
		 		  String Action=StringTools.convertUTF8(request.getParameter("Action"));
	              if(Action.equals("GetStationInfo")){
//		    			  List<AreaInfo> infoList=new ArrayList<AreaInfo>(); 
	    			 List<StStbprpB> list=ststbprpbService.findgetStstbprp3(bounds, type);
//	    			  System.out.println(list.size());
	    			  info.setInfoList2(list);
	    			  info.setError(0);
	              }else {
	            	  info.setInfoList2(null);
	    			  info.setError(0);
	              }
			  }
              JSONObject jsonObject = JSONObject.fromObject(info);
              System.out.println(jsonObject.toString());		
		      PrintWriter out = response.getWriter();
		      out.print(jsonObject.toString());  
		      out.flush();
	   }
	 
	
	/** 接口抛弃
	 * 获取逐日时段雨量
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/stpptnrIntervals",method =RequestMethod.GET) 
	   public void findStpptnrIntervals(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		 PrintWriter out = response.getWriter();
	
			 String Action=request.getParameter("Action");
	 		 String []StationNo=request.getParameter("StationNo").split(",");
	 		 String []DateTime=request.getParameter("DateTime").split(",");
	 		 String SortWay=request.getParameter("SortWay");
	 		String StationNumber=request.getParameter("StationNumber");
	 		 DailyPeriodResult1 DailyPeriodResult=null;
	 		   JSONObject jsonObject;
	 		if(Action.equals("GetDailyPeriodRainFall")){
	 			 //一个测站按
	 			if(StationNumber.equals("one")){
	 			   int PageNo=Integer.parseInt(request.getParameter("PageNo"));
	 			     DailyPeriodResult=stpptnrService.findStpptnrOne(StationNo, PageNo, DateTime[0],  DateTime[1], SortWay);
	 			      jsonObject = JSONObject.fromObject(DailyPeriodResult);
 	 				out.print(jsonObject.toString());  
	 				 //多个测站
	 			}else if (StationNumber.equals("many")){
	 		     DailyPeriodResult=stpptnrService.findStpptnrMany(StationNo, DateTime[0], SortWay);
	 		        jsonObject = JSONObject.fromObject(DailyPeriodResult);
 				out.print(jsonObject.toString());  
	 			}
	 	      out.flush();
	 		}else {
	 		    DailyPeriodResult=new DailyPeriodResult1();
	 		    DailyPeriodResult.setError(0);
	 		    DailyPeriodResult.setInfoList(null);
	 	        jsonObject = JSONObject.fromObject(DailyPeriodResult);
	 			out.print(jsonObject.toString());  
	 			out.flush();
	 		  }
	 
	
	}*/
	
	
	
	        
 	//   JSONObject jsonObject = JSONObject.fromObject(DailyPeriodResult);
//		 System.out.println(jsonObject.toString());		
//		 PrintWriter out = response.getWriter();
//		 out.print(jsonObject.toString());  
//		 out.flush();
 
	 /**
	  * 获取数据时间段
	  * @param request
	  * @param response
	  * @throws Exception
	  */
    	@RequestMapping(value = "/strrtnrPeriod",method =RequestMethod.GET) 
	   public void findStrrtnrPeriod(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  response.setCharacterEncoding("UTF-8");
		  response.setContentType("application/json");
		  PrintWriter out = response.getWriter();
		  Map<String,String> map =new HashMap<String,String>();
		
		  String Period=request.getParameter("Period");
		  String Action=request.getParameter("Action");
		  String Stcd=request.getParameter("StationNo");
          
		  if(Action.equals("GetPeriod")){
		  String	 time =stpptnrService.getDateTime(Period,Stcd);
		  System.out.println(time.length());
		  map.put("validPeriod", time);
		  map.put("error", "0");
		  JSONObject jsonObject = JSONObject.fromObject(map);
 		  out.print(jsonObject.toString());
		 
		  }
    	 
    	} 
    	
    	 /**
   	  * 获取平均值
   	  * @param request
   	  * @param response
   	  * @throws Exception
   	  */
       	@RequestMapping(value = "/strrtnrCalculateAverage",method =RequestMethod.GET) 
   	   public void findStrrtnrCalculateAverage(HttpServletRequest request, HttpServletResponse response) throws Exception {
   		  response.setCharacterEncoding("UTF-8");
   		  response.setContentType("application/json");
   		  PrintWriter out = response.getWriter();
   		 
   		   String Action=request.getParameter("Action");
   		   if(Action.equals("CalculateAverage")){
   			 String StationNo=request.getParameter("StationNo");
   		  String []DateTime=request.getParameter("DateTime").split(",");
   	   List <Average> listAverage=stpptnrService.findStpptnrCalculateAverage(StationNo, DateTime[0], DateTime[1],1);
     	 Map<String,Object> map =new HashMap<String,Object>();
     	  map.put("AverageList", listAverage);
		  map.put("error", "0");
		  JSONObject jsonObject = JSONObject.fromObject(map);
 		  out.print(jsonObject.toString());
		  //out.println(time);
   		  }
   		   //平均日雨量
   		   else if(Action.equals("GetAverageDayRainFall"))
   		  {
   			String StationNo=request.getParameter("StationNo");
     		String []DateTime=request.getParameter("DateTime").split(",");
          List   list=stpptnrService.findStpptnrCalculateAverage(StationNo, DateTime[0], DateTime[1],2);
           List <AverageDayRainFall>AverageDayRainFallList =new ArrayList<AverageDayRainFall>() ;
           double dayAvg=0;
          for (int i = 0; i < list.size(); i++) {
        	  AverageDayRainFall  AverageDay=new  AverageDayRainFall();
        	   dayAvg=0;
	    		Object[]o=(Object[])list.get(i);
	    	  for (int j = 0; j < o.length; j++) {
	    		  if(o[j]==null)o[j]=0;
					if(j>=1)
				  dayAvg+=StringTools.convertDouble(o[j]);
					if(j==o.length-1) 
						dayAvg=dayAvg/j;
				
				}
	    		AverageDay.setStationNo(o[0].toString());
	    		AverageDay.setAverageDayRainFall(dayAvg);
	    		AverageDayRainFallList.add(AverageDay);
	    	
	         
          }
     	 Map<String,Object> map =new HashMap<String,Object>();
          map.put("averageDayRainFallList", AverageDayRainFallList);
   		  map.put("error", "0");
   		  JSONObject jsonObject = JSONObject.fromObject(map);
    	  out.print(jsonObject.toString());
          
   		  } else{
   			  out.println("{\"error\":1}");
   		  }
       	 
      
       	} 
       	
       	/**  
    	 * 获取逐日时段24小时雨量
    	 * @param request
    	 * @param response
    	 * @throws Exception
    	 */
    	@RequestMapping(value = "/stpptnrIntervals24",method =RequestMethod.GET) 
    	   public void findStpptnrIntervals24(HttpServletRequest request, HttpServletResponse response) throws Exception {
    		response.setCharacterEncoding("UTF-8");
    		response.setContentType("application/json"); 
    		  Map<String,Object> map =new HashMap<String,Object>();
    		  JSONObject jsonObject =null;
    		 PrintWriter out = response.getWriter();
    		 String Action=request.getParameter("Action");
     	
    		 if(Action.equals("GetDailyPeriodRainFall")){
    			 String [] Stcd=request.getParameter("StationNo").split(",");
    			 String [] DateTime=request.getParameter("DateTime").split(",");
    		   List <DailyPeriodRainFallOfEveryStation>list=stpptnrService.findStpptnrIntervals24(Stcd, DateTime[0], DateTime[1]);
    		   map.put("infoList", list);
    	 	    map.put("error", 0);
    	 	      jsonObject= JSONObject.fromObject(map);
    	 		  out.print(jsonObject.toString());
    	 		 out.flush();
    		 }else{
    		   map.put("DailyPeriodRainFallResult", "null");
    		  map.put("error",1);
    		  jsonObject= JSONObject.fromObject(map);
     		  out.print(jsonObject.toString());
     		 out.flush();
    		 }
    		
    		
    	}
    	
    	/**
    	 * 日雨量
    	 * @param request
    	 * @param response
    	 * @throws Exception
    	 */
		@RequestMapping(value = "/stpptnrDayRainFall", method = RequestMethod.GET)
		public void findStpptnrDayRainFall(HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			String Action = request.getParameter("Action");
			Map<String, Object> map = new HashMap<String, Object>();
			if (Action.equals("GetDayRainFall")) {
				String StationNo = request.getParameter("StationNo");
				String[] DateTime = request.getParameter("DateTime").split(",");
				// String SortWay=request.getParameter("SortWay");
				List<DailyDayRainFallOfEveryStation> list = stpptnrService.getDayRainFall(StationNo, DateTime[1], null);
				map.put("infoList", list);
				map.put("error", 0);
				JSONObject jsonObject = JSONObject.fromObject(map);
				out.print(jsonObject.toString());
			}else if(Action.equals("GetDayRainFal2")) {
//				String StationNo = request.getParameter("StationNo");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String StationName = request.getParameter("StationName");
				String bsnm = "";
				if(request.getParameter("bsnm")!=null && request.getParameter("bsnm").trim().length()!=0)
					bsnm=request.getParameter("bsnm").trim();
				String[] DateTime = request.getParameter("DateTime").split(",");
				// String SortWay=request.getParameter("SortWay");
				String startTime=DateTime[0]+" 00:00:00";
				String endTime=DateTime[1]+" 23:59:59";
				Date d1=df.parse(startTime);
				Date d2=df.parse(endTime);
				long days=(d2.getTime()-d1.getTime())/(1000*60*60*24);
				if(days>7){
					map.put("error", 1);
					map.put("msg", "日期范围不允许超过7天。");
				}else if(days<0){
					map.put("error", 1);
					map.put("msg", "结束日期必须在开始日期之后。");
				}else{
					List<StPptnRNew> list = stpptnrNewService.getDayRainFal2(StationName, bsnm, startTime, endTime, null);
					map.put("infoList", list);
					map.put("error", 0);
				}
				JSONObject jsonObject = JSONObject.fromObject(map);
				out.print(jsonObject.toString());
			}
	
		}
		
    	@RequestMapping(value = "/stpptnrAccDayRainFall",method =RequestMethod.GET) 
 	   public void findStpptnrAccDayRainFall(HttpServletRequest request, HttpServletResponse response) throws Exception {
 	
    		response.setCharacterEncoding("UTF-8");
    		response.setContentType("application/json"); 
    		  PrintWriter out = response.getWriter();
    		 String Action=request.getParameter("Action");
    		 
    		  Map<String,Object> map =new HashMap<String,Object>();
    		 
    		 if(Action.equals("GetAccDayRainFall")){
    		  String [] StationNo=request.getParameter("StationNo").split(",");
    	 	  String [] DateTime=request.getParameter("DateTime").split(",");
    	 	  int DayNumber=Integer.parseInt(request.getParameter("DayNumber"));
              List<AccDailyDayRainFall> listInfo=stpptnrService.findStpptnrDayRainFall(StationNo, DateTime[0], DayNumber);
              map.put("infoList", listInfo);
	 	      map.put("error", 0);
	 		  JSONObject jsonObject = JSONObject.fromObject(map);
	 		  out.print(jsonObject.toString());
    		 }
    	}
    	
    	 
}