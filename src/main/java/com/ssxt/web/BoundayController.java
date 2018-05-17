package com.ssxt.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
 import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ssxt.common.tools.FileTools;
import com.ssxt.common.tools.StringTools;
import com.ssxt.model.AdminCode;
import com.ssxt.model.City;
import com.ssxt.vo.Boundary.CityBoundary;
import com.ssxt.vo.Boundary.ProvinceBoundary;

import net.sf.json.JSONObject;
import test.TestLog;


//import com.dsoft.model.Address;
//import com.dsoft.model.Identity;
//import com.smart.dao.AddressDao;
//import com.smart.dao.IdentityDao;

@Controller
@RequestMapping(value = "/lunkuo")
public class BoundayController {
	
   
  private 	Logger log = Logger.getLogger(BoundayController.class);
  
  
  
//省市轮廓返回json数据
	@RequestMapping(value = "/getProvinceBoundary",method =RequestMethod.GET) 
   public void getProvinceBoundary(HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json"); 
			 //@PathVariable
			 // String	provinceNmae=request.getParameter("provinceNmae");
			
 			  String json=new String();
 			  String Action= request.getParameter("Action");
             if(Action.equals("GetProvinceBoundary")){
            		List <ProvinceBoundary>listinfo=new ArrayList<ProvinceBoundary>();
                String path=request.getSession().getServletContext().getRealPath(System.getProperty("file.separator")+"WEB-INF"+System.getProperty("file.separator") +"sunkingWater"+
        				System.getProperty("file.separator") +"provinceBoundyDatas");
                File file=new File(path);
                File[] tempList = file.listFiles();
           	  System.out.println("该目录下对象个数："+tempList.length);
          	  for (int i = 0; i <tempList.length; i++) {
          	   if (tempList[i].isFile()) {
          	 //  System.out.println("文     件："+tempList[i]);
          	     path=tempList[i].toString();
                  ProvinceBoundary p= FileTools.getProvinceBoundary(path);
                 listinfo.add(p);
                  }
          	  }
          	  Map<String,Object> map =new HashMap<String,Object>();
                map.put("error", "0");
                map.put("infoList", listinfo);
                JSONObject jsonObject = JSONObject.fromObject(map);
                 PrintWriter out = response.getWriter();
 			    out.print(jsonObject.toString());  
 			    out.flush();
            	  }
 			
			
   }
   
  
     
 	//省市轮廓返回json数据
	@RequestMapping(value = "/getCityBoundary",method =RequestMethod.GET) 
   public void findCityBoundary(HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json"); 
			 //@PathVariable
			 // String	provinceNmae=request.getParameter("provinceNmae");
			
 			  String json=new String();
   			 String ProvinceName=new String((request.getParameter("ProvinceName")).getBytes("iso-8859-1"),"utf-8");
 			
 			  String Action=new String((request.getParameter("Action")).getBytes("iso-8859-1"),"utf-8");
 			  ProvinceName=ProvinceName.replace('省', ' ').trim();
 			  ProvinceName=ProvinceName.replace('市', ' ').trim();
 			  if(Action.equals("GetCityBoundary")){
		   String path=request.getSession().getServletContext().getRealPath(System.getProperty("file.separator")+"WEB-INF"+System.getProperty("file.separator") +"sunkingWater"+
    				System.getProperty("file.separator") +"cityBoundyDatas"+System.getProperty("file.separator")+ProvinceName);
		   File file=new File(path);
		   File[] tempList = file.listFiles();
    		  List<CityBoundary> infoList=new ArrayList<CityBoundary>();
    	    	//  System.out.println("该目录下对象个数："+tempList.length);
    	   	   for (int i = 0; i <tempList.length; i++) {
    	   	   if (tempList[i].isFile()) {
    	   	    String path1=tempList[i].toString();
    			CityBoundary c=FileTools.findCity(path1);
    			infoList.add(c);
    	   	        }
    	   	     }
    	       Map<String,Object> map =new HashMap<String,Object>();
    	   	  map.put("error", "0");
              map.put("infoList", infoList);
              map.put("province", ProvinceName);
              JSONObject jsonObject = JSONObject.fromObject(map);
               PrintWriter out = response.getWriter();
			    out.print(jsonObject.toString());  
			    out.flush();
 			  }
			 
   }
  
	@RequestMapping(value = "/findcode",method =RequestMethod.GET) 
	   public void findCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json"); 
				 //@PathVariable
				 
				 String json=new String();
 	 			 String Province=StringTools.convertUTF8(request.getParameter("Province"));
	 			 String Action=StringTools.convertUTF8(request.getParameter("Action"));
	 			 String City=StringTools.convertUTF8(request.getParameter("City"));

	 			  
	 			 Province=Province.replace('省', ' ').trim();
	 			 Province=Province.replace('市', ' ').trim();
                  if(Action.equals("GetAdminCode")){
	            	  String path=request.getSession().getServletContext().getRealPath("/");
	             List <AdminCode>code= FileTools.FileCode(path, Province, City);
	              
	             Map<String,Object> map =new HashMap<String,Object>();
	             map.put("error", "0");
	             map.put("cityCode", code);
	     
	             JSONObject jsonObject = JSONObject.fromObject(map);
	             json=jsonObject.toString();
	           
	    		  }else{
	    			  json="{\"error\":1}";
	    		  }
	 			
				  PrintWriter out = response.getWriter();
				  out.print(json);  
				  out.flush();
	}
 

}