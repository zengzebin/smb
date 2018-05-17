package com.ssxt.web;

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
import com.ssxt.model.StStbprpB;
import com.ssxt.service.RiverSerivce;
import com.ssxt.service.RsvrSerivce;
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
import com.ssxt.vo.river.WadiWaterInfo;
import com.ssxt.vo.rsvr.ReservoirWaterInfoInfo;


//import com.dsoft.model.Address;
//import com.dsoft.model.Identity;
//import com.smart.dao.AddressDao;
//import com.smart.dao.IdentityDao;

@Controller
@RequestMapping(value = "/rsvr")
public class RsvrController {
	 
	//水库业务类
	@Autowired
	RsvrSerivce rsvrSerivce;
	
 
    
	@RequestMapping(value = "/getRsvrInfo",method =RequestMethod.GET) 
   public void findRsvrInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json"); 
			String Action=request.getParameter("Action");
			  if(Action.equals("GetReservoirWaterInfo")){
			  PrintWriter out = response.getWriter();
			  Map<String,Object> map =new HashMap<String,Object>();
			  String Area=new String((request.getParameter("Area")).getBytes("iso-8859-1"),"utf-8");
			   String []StationNo =request.getParameter("StationNo").split(",");
			 List<ReservoirWaterInfoInfo>  listinfo=rsvrSerivce.findRsvrInfo(StationNo, null);
 		     map.put("infoList", listinfo);
  		     map.put("error", "0");
			  JSONObject jsonObject = JSONObject.fromObject(map);
	 		  out.print(jsonObject.toString());
			  } 
			 
   }
	 
	
	
	 
    	

}