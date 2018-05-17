package com.ssxt.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssxt.common.gather.Cloud;
import com.ssxt.common.gather.Radar;
import com.ssxt.common.gather.Rain;
import com.ssxt.common.gather.River;
import com.ssxt.common.gather.Rsvr;
import com.ssxt.common.tools.Page;
import com.ssxt.common.tools.StringTools;
import com.ssxt.model.StPptnRNew;
import com.ssxt.service.StpptnrNewService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/gather")
public class GatherController {
	
	//@Autowired
	//StpptnrNewService stpptnrNewService;

     	@RequestMapping(value = "/openWaterGather",method =RequestMethod.GET) 
	   public void openWaterGather(HttpServletRequest request, HttpServletResponse response) throws IOException{
     		response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json"); 
			
			new Rain().run();
			
			
//     		Calendar calendar = Calendar.getInstance();  
//     		   calendar.set(Calendar.HOUR_OF_DAY, 18); // 控制时  
//     		    calendar.set(Calendar.MINUTE, 30); // 控制分  
//     		    calendar.set(Calendar.SECOND, 00); // 控制秒  
//     		    Timer timer = new Timer();  
//     		    Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00  
//     		    //间隔：1小时
//     	        //long period = 1000 * 60 * 60;    
//     	        //测试时间每分钟一次
//     		    long period =1000 * 60 * 3;      
//     		    System.out.println("=========================调用=========================");
//     		    Rain t=new Rain();
//     		  //  timer.schedule(t, time,period); 
			 //间隔：2小时
			    PrintWriter out = response.getWriter();
			    out.print("雨水情采集开启请关闭窗口");  
			    out.flush();
  	           long period = 1000 * 60 * 60 * 2;    
			/*   while (true){
				   new Rain().run();
				   new River().run();
				   new Rsvr().run();
				   try {
					Thread.sleep(period);
				 } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }*/
     		   
	
	}
     	
     	//云图采集
     	@RequestMapping(value = "/openCloudGather",method =RequestMethod.GET) 
 	   public void openCloudGather(HttpServletRequest request, HttpServletResponse response) throws IOException{
      		response.setCharacterEncoding("UTF-8");
 			response.setContentType("application/json"); 
			  String dateTime = request.getParameter("dateTime"); //20160301084500

                PrintWriter out = response.getWriter();
 			    out.print("云图采集开启请关闭窗口");  
 			    out.flush();
   	           long period = 35*60*1000;  //30分钟  
   	           
 			   while (true){ 				 
 				   try {
 					   String starttime = dateTime;//指定时间
 		 		       String addtime=starttime; //开始时间
 		 		     while(true){
 		 		         addtime= StringTools.addMin(addtime,30);//加30分钟
 		 		         String urltime=addtime+"000";
 		  		         System.out.println("after:"+urltime); 
 		  		         String url="http://pi.weather.com.cn/i/product/pic/m/sevp_nsmc_wxcl_asc_e99_achn_lno_py_"+urltime+".jpg";
 		  		        // String url="http://baidu.com";
 		  		         Cloud.saveToFile(url,StringTools.addHour(addtime,8));
 		  		         Thread.sleep(period);
 		 	          }
 					
 				 } catch (InterruptedException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 			  }
 	}
     	
     	
     	
      	
     	//雷达区域采集
     	@RequestMapping(value = "/openRadarGather",method =RequestMethod.GET) 
  	   public void openRadarGather(HttpServletRequest request, HttpServletResponse response) throws IOException{
       		response.setCharacterEncoding("UTF-8");
  			response.setContentType("application/json"); 
  		  String dateTime = request.getParameter("dateTime");//20160301090000
                 PrintWriter out = response.getWriter();
  			    out.print("雷达区域采集开启请关闭窗口");  
  			    out.flush();
    	           long period = 13*60*1000;   
    	       	try{
   					    String starttime =dateTime;//指定时间
    			        String addtime=starttime; //开始时间
    			         while(true){
    			            addtime= StringTools.addMin(addtime,10);//加30分钟
    			            String urltime=addtime+"001";
    				         System.out.println("after:"+urltime); 
    				          Radar  ra = new Radar(urltime,2); 
    				         ra.start();
    		  			    Thread.sleep(period);
    			          }
    			 
    				} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
  		 
       		   
  	
  	}
     	
     	//雷达城市采集
     	@RequestMapping(value = "/openRadarAreaGather",method =RequestMethod.GET) 
   	   public void openRadarAreaGather(HttpServletRequest request, HttpServletResponse response) throws IOException{
        		response.setCharacterEncoding("UTF-8");
   			   response.setContentType("application/json"); 
   			   
   			  String dateTime = request.getParameter("dateTime");//20160301092000
                  PrintWriter out = response.getWriter();
   			    out.print("雷达城市采集开启请关闭窗口");  
   			    out.flush();
     	           
   				try{
   					
   			       long period = 8*60*1000;   
   				   String starttime =dateTime;//指定时间
   			        String addtime=starttime; //开始时间
   			         while(true){
   			            addtime= StringTools.addMin(addtime,5);//加30分钟
   			            String urltime=addtime+"000";
   				         System.out.println("after:"+urltime); 
   				          Radar  ra = new Radar(urltime,1); 
   				         ra.start();
   		  			    Thread.sleep(period);
   			          }
   			 
   				} catch (InterruptedException e) {
   						// TODO Auto-generated catch block
   						e.printStackTrace();
   					}
        		   
   	
   	}
     	
     	
     	
     	
 	   //跳转到JSP页面的演示
 	   @RequestMapping(value = "/dateTable")
     public void dateTable(@RequestParam String aoData ,HttpServletRequest request, HttpServletResponse response) throws IOException{
 		   System.out.println("111");
 		  response.setCharacterEncoding("UTF-8");
		  response.setContentType("application/json"); 
 	    JSONArray jsonarray = JSONArray.fromObject(aoData);
 	    System.out.println(jsonarray.toString());
 	//  String time= request.getAttribute("time").toString();
 	    String sEcho = null;
 	     int iDisplayStart = 0; // 起始索引
 	     int iDisplayLength = 0; // 每页显示的行数
 	     String from =null;
 	    String end=null;
  	     for (int i = 0; i < jsonarray.size(); i++) {
 	         JSONObject obj = (JSONObject) jsonarray.get(i);
 	         if (obj.get("name").equals("sEcho"))
 	             sEcho = obj.get("value").toString();
 	  
 	         if (obj.get("name").equals("iDisplayStart"))
 	             iDisplayStart = obj.getInt("value");
 	  
 	         if (obj.get("name").equals("iDisplayLength"))
 	             iDisplayLength = obj.getInt("value");
 	        if (obj.get("name").equals("from"))
 	        	from = obj.getString("value");
 	         if (obj.get("name").equals("end"))
	             end = obj.getString("value");
 	       
 	     }
  	      Page page=new Page();
  	      page.setPageSize(iDisplayLength);//一页几条数据
		  page.setPageNo(iDisplayStart/iDisplayLength+1);//查询第几页
		  //page=stpptnrNewService.getPageStpptnrNews(page,from, end);
		  
		  System.out.println("第几页"+page.getPageNo());
		  System.out.println("一页几行数据"+page.getPageSize());
		  List <StPptnRNew>list=page.getResult();
		  List<String[]> lst = new ArrayList<String[]>();
			char  rain;
			String wth;
		  for(int j=0;j<list.size();j++){
			  StPptnRNew s=list.get(j);
			  rain=s.getWth();
			   switch (rain) {
			case '5':
				wth="雪";
				 break;
			case '6':
				wth="雨夹雪";
				 break;
			case '7':
				wth="雨";
				 break;
			case '8':
				wth="阴";
				break;
            case '9':
            	wth="晴";
				 break;
            default:
            	wth="无";
				break;
			}
			  String[] d = {s.getStmn(), s.getRvnm() , s.getBsnm(), s.getAddvcd(), s.getTm().toString(),s.getDyp().toString(),wth};
			  lst.add(d);
		  }
		  
		  
		  
// 	     // 鐢熸垚20鏉℃祴璇曟暟鎹?
// 	     List<String[]> lst = new ArrayList<String[]>();
// 	     for (int i = 0; i < 100; i++) {
// 	         String[] d = { "co1_data" + i, "col2_data" + i };
// 	         lst.add(d);
// 	     }
 	        PrintWriter out = response.getWriter();
		  
		 
 	     JSONObject getObj = new JSONObject();
 	     getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
 	     getObj.put("iTotalRecords", page.getTotalCount());//实际的行数
 	     getObj.put("iTotalDisplayRecords",  page.getTotalCount());//显示的行数,这个要和上面写的一样
 	     getObj.put("aaData", lst);//要以JSON格式返回

 	     System.out.println(getObj.toString());
 	    System.out.println(iDisplayStart+" "+iDisplayLength);
 	       getObj.toString();
 	      out.println(getObj.toString());
 	      out.flush();
 	 }
 

}
