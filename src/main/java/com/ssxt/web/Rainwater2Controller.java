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
import com.ssxt.dao.RsvrDao;
import com.ssxt.dao.Impl.RiverDaoImpl;
import com.ssxt.dao.Impl.RsvrDaoImpl;
import com.ssxt.model.City;
import com.ssxt.model.StPptnRNew;
import com.ssxt.model.StRiverR;
import com.ssxt.model.StRsvrR;
import com.ssxt.model.StStbprpB;
import com.ssxt.service.RiverSerivce;
import com.ssxt.service.RsvrSerivce;
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
import com.ssxt.vo.rain.RealTimeDayRainFall;
import com.ssxt.vo.river.RealTimeWaidWaterState;
import com.ssxt.vo.rsvr.RealTimeReservoirWaterState;

@Controller
@RequestMapping(value = "/rainwater2")
public class Rainwater2Controller {
	// 测站业务类
	@Autowired
	StstbprpbService ststbprpbService;

	// 雨量业务类
	@Autowired
	StpptnrService stpptnrService;

	@Autowired
	StpptnrNewService stpptnrnewService;

	@Autowired
	RiverSerivce riverSerivce;

	@Autowired
	RsvrSerivce rsvrSerivce;
	// 公共
	// @Autowired
	// commonService commonService;
	@Autowired
	RiverDaoImpl riverDao;
	
	@Autowired
	RsvrDaoImpl rsvrDao;
	

	/**
	 * 获取流域列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/rivers", method = RequestMethod.GET)
	public void rivers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String type="";
		if(request.getParameter("type")!=null && request.getParameter("type").trim().length()>0){
			type=request.getParameter("type").trim();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		PrintWriter out = response.getWriter();
		List<String> lis=ststbprpbService.getAllLiuyuNames(type);
		map.put("volley", lis);
		map.put("error", 0);
		jsonObject = JSONObject.fromObject(map);
		out.print(jsonObject.toString());
		out.flush();
	}	
	
	/**
	 * 通过流域名称，获得相关站点
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/sites", method = RequestMethod.GET)
	public void sites(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		PrintWriter out = response.getWriter();
		String liuyuName=request.getParameter("name").trim();
		String type="";
		if(request.getParameter("type")!=null && request.getParameter("type").trim().length()>0){
			type=request.getParameter("type").trim();
		}
		
		List<StStbprpB> lis=ststbprpbService.getSitesInLiuyu(liuyuName, type);
		map.put("sites", lis);
		map.put("error", 0);
		jsonObject = JSONObject.fromObject(map);
		out.print(jsonObject.toString());
		out.flush();
	}		
	

	/**
	 * 获取各数据表检索条件
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/condition", method = RequestMethod.GET)
	public void findConditionByTable(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		PrintWriter out = response.getWriter();
		String Action = request.getParameter("Action");
		String Table = request.getParameter("Table");

		if (Action.equals("GetRetrievalCondition")) {
			if (Table.equals("Rain")) {
 			List<String> addvcdlist = stpptnrnewService.getCondition("addvcd");
				List<String> bsnmlist = stpptnrnewService.getCondition("bsnm");
				map.put("adminAreaList", addvcdlist);
				map.put("volley", bsnmlist);
				map.put("error", 0);
				jsonObject = JSONObject.fromObject(map);
				out.print(jsonObject.toString());
				out.flush();
			} else if (Table.equals("Waid")) {
				List<String> addvcdlist = riverSerivce.getCondition("addvcd");
				List<String> bsnmlist = riverSerivce.getCondition("bsnm");
				map.put("adminAreaList", addvcdlist);
				map.put("volley", bsnmlist);
				map.put("error", 0);
				jsonObject = JSONObject.fromObject(map);
				out.print(jsonObject.toString());
				out.flush();
			} else if (Table.equals("Reservoir")) {
				List<String> addvcdlist = rsvrSerivce.getCondition("addvcd");
				List<String> bsnmlist = rsvrSerivce.getCondition("bsnm");
				map.put("adminAreaList", addvcdlist);
				map.put("volley", bsnmlist);
				map.put("error", 0);
				jsonObject = JSONObject.fromObject(map);
				out.print(jsonObject.toString());
				out.flush();
			} else {
				map.put("adminAreaList", "null");
				map.put("volley", "null");
				map.put("error", 1);
				jsonObject = JSONObject.fromObject(map);
				out.print(jsonObject.toString());
				out.flush();
			}

		} else {
			map.put("adminAreaList", "null");
			map.put("volley", "null");
			map.put("error", 1);
			jsonObject = JSONObject.fromObject(map);
			out.print(jsonObject.toString());
			out.flush();
		}
	}

	/**
	 * 获取实时日雨量
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/stpptnrnew", method = RequestMethod.GET)
	public void findStPptnRNew(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		PrintWriter out = response.getWriter();
		String Action = request.getParameter("Action");

		if (Action.equals("GetRealTimeDayRainFall")) {
			String DateTime = request.getParameter("DateTime");
			String AdminArea = request.getParameter("AdminArea");
			String Volley = request.getParameter("Volley");
			if(AdminArea != null && !AdminArea.equals("null")){
				AdminArea = new String((request.getParameter("AdminArea")).getBytes("iso-8859-1"),"utf-8");
			}else{
				AdminArea = "null";
			}
			if(Volley != null && !Volley.equals("null")){
				Volley = new String((request.getParameter("Volley")).getBytes("iso-8859-1"),"utf-8");
			}else{
				Volley = "null";
			}
			if(DateTime == null || DateTime.equals("null")){
				DateTime = stpptnrnewService.getNewestDatetime(AdminArea, Volley, "null");
			}
			List<RealTimeDayRainFall> infoList = stpptnrnewService.getStpptnrNewBySearch(DateTime, AdminArea, Volley);
			map.put("infoList", infoList);
			map.put("dateTime", DateTime);
			map.put("error", 0);
			jsonObject = JSONObject.fromObject(map);
			out.print(jsonObject.toString());
			out.flush();
		} else {
			map.put("infoList", "null");
			map.put("dateTime", "null");
			map.put("error", 1);
			jsonObject = JSONObject.fromObject(map);
			out.print(jsonObject.toString());
			out.flush();
		}

	}
	
	/**
	 * 统计日雨量
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/stpptnrnewacc", method = RequestMethod.GET)
	public void findStPptnRNewAcc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		PrintWriter out = response.getWriter();
		String Action = request.getParameter("Action");

		if (Action.equals("GetRealTimeAccDayRainFall")) {
			String DateTime = request.getParameter("DateTime");
			String DayNumber = request.getParameter("DayNumber");
			int DayNum = 0;
			String AdminArea = request.getParameter("AdminArea");
			String Volley = request.getParameter("Volley");
			String StationName = request.getParameter("StationName");
			if(DayNumber != null){
				DayNum = Integer.parseInt(DayNumber);
			}
			if(AdminArea != null && !AdminArea.equals("null")){
				AdminArea = new String((request.getParameter("AdminArea")).getBytes("iso-8859-1"),"utf-8");
			}else{
				AdminArea = "null";
			}
			if(Volley != null && !Volley.equals("null")){
				Volley = new String((request.getParameter("Volley")).getBytes("iso-8859-1"),"utf-8");
			}else{
				Volley = "null";
			}
			if(StationName != null && !StationName.equals("null")){
				StationName = new String((request.getParameter("StationName")).getBytes("iso-8859-1"),"utf-8");
			}else{
				StationName = "null";
			}
			if(DateTime == null || DateTime.equals("null")){
				DateTime = stpptnrnewService.getNewestDatetime(AdminArea, Volley, StationName);
			}
			List infoList = stpptnrnewService.getStpptnrNewAccBySearch(DateTime, AdminArea, Volley,StationName,DayNum);
			String[] datetimelist =(String[])  infoList.get(0);
			String[] dyplist =(String[]) infoList.get(1);

			int count = Integer.parseInt((String)infoList.get(2));
			if(DayNum == count && count!=0){
				map.put("dayRainFall", dyplist);
				map.put("dateTime", datetimelist);
			}else{
				map.put("dayRainFall", "null");
				map.put("dateTime", "null");
			}
			map.put("error", 0);
			jsonObject = JSONObject.fromObject(map);
			out.print(jsonObject.toString());
			out.flush();
		} else {
			map.put("dayRainFall", "null");
			map.put("dateTime", "null");
			map.put("error", 1);
			jsonObject = JSONObject.fromObject(map);
			out.print(jsonObject.toString());
			out.flush();
		}

	}
	
	/**
	 * 获取实时河道水情
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/river", method = RequestMethod.GET)
	public void findRiver(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		PrintWriter out = response.getWriter();
		String Action = request.getParameter("Action");

		if (Action.equals("GetRealTimeWaidWaterState")) {
			String DateTime = request.getParameter("DateTime");
			String AdminArea = request.getParameter("AdminArea");
			String Volley = request.getParameter("Volley");
			if(AdminArea != null && !AdminArea.equals("null")){
				AdminArea = new String((request.getParameter("AdminArea")).getBytes("iso-8859-1"),"utf-8");
			}else{
				AdminArea = "null";
			}
			if(Volley != null && !Volley.equals("null")){
				Volley = new String((request.getParameter("Volley")).getBytes("iso-8859-1"),"utf-8");
			}else{
				Volley = "null";
			}
			if(DateTime == null || DateTime.equals("null")){
				DateTime = riverSerivce.getNewestDatetime(AdminArea, Volley);
			}
			List<RealTimeWaidWaterState> infoList = riverSerivce.getRiverBySearch(DateTime, AdminArea, Volley);
			map.put("infoList", infoList);
			map.put("dateTime", DateTime);
			map.put("error", 0);
			jsonObject = JSONObject.fromObject(map);
			out.print(jsonObject.toString());
			out.flush();
		} else {
			map.put("infoList", "null");
			map.put("dateTime", "null");
			map.put("error", 1);
			jsonObject = JSONObject.fromObject(map);
			out.print(jsonObject.toString());
			out.flush();
		}

	}
	
	/**
	 * 获取实时河道水情，逐日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/riverdays", method = RequestMethod.GET)
	public void riverdays(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		PrintWriter out = response.getWriter();
		
		String[] DateTime = request.getParameter("DateTime").trim().split(",");
		String stationNo = request.getParameter("stationNo");
		String startTime=DateTime[0]+" 00:00:00";
		String endTime=DateTime[1]+" 23:59:59";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1=df.parse(startTime);
		Date d2=df.parse(endTime);
		long days=(d2.getTime()-d1.getTime())/(1000*60*60*24);
		if(days>10){
			map.put("error", 1);
			map.put("msg", "日期范围不允许超过10天。");
		}else if(days<0){
			map.put("error", 1);
			map.put("msg", "结束日期必须在开始日期之后。");
		}else{
			List<StRiverR> list = riverDao.getWaterState(stationNo, startTime, endTime, null);
			map.put("infoList", list);
			map.put("error", 0);
		}
		jsonObject = JSONObject.fromObject(map);
		out.print(jsonObject.toString());
		out.flush();

	}	
	
	
	/**
	 * 获取实时水库水情
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/rsvr", method = RequestMethod.GET)
	public void findRsvr(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		PrintWriter out = response.getWriter();
		String Action = request.getParameter("Action");

		if (Action.equals("GetRealTimeReservoirWaterState")) {
			String DateTime = request.getParameter("DateTime");
			String AdminArea = request.getParameter("AdminArea");
			String Volley = request.getParameter("Volley");
			if(AdminArea != null && !AdminArea.equals("null")){
				AdminArea = new String((request.getParameter("AdminArea")).getBytes("iso-8859-1"),"utf-8");
			}else{
				AdminArea = "null";
			}
			if(Volley != null && !Volley.equals("null")){
				Volley = new String((request.getParameter("Volley")).getBytes("iso-8859-1"),"utf-8");
			}else{
				Volley = "null";
			}
			if(DateTime == null || DateTime.equals("null")){
				DateTime = rsvrSerivce.getNewestDatetime(AdminArea, Volley);
			}
			List<RealTimeReservoirWaterState> infoList = rsvrSerivce.getRsvrBySearch(DateTime, AdminArea, Volley);
			map.put("infoList", infoList);
			map.put("dateTime", DateTime);
			map.put("error", 0);
			jsonObject = JSONObject.fromObject(map);
			out.print(jsonObject.toString());
			out.flush();
		} else {
			map.put("infoList", "null");
			map.put("dateTime", "null");
			map.put("error", 1);
			jsonObject = JSONObject.fromObject(map);
			out.print(jsonObject.toString());
			out.flush();
		}
	}
	
	/**
	 * 获取实时水库水情,时间范围
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/rsvrdays", method = RequestMethod.GET)
	public void rsvrdays(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		PrintWriter out = response.getWriter();
		String[] DateTime = request.getParameter("DateTime").trim().split(",");
		String stationNo = request.getParameter("stationNo");
		String startTime=DateTime[0]+" 00:00:00";
		String endTime=DateTime[1]+" 23:59:59";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1=df.parse(startTime);
		Date d2=df.parse(endTime);
		long days=(d2.getTime()-d1.getTime())/(1000*60*60*24);
		if(days>10){
			map.put("error", 1);
			map.put("msg", "日期范围不允许超过10天。");
		}else if(days<0){
			map.put("error", 1);
			map.put("msg", "结束日期必须在开始日期之后。");
		}else{
			List<StRsvrR> list = rsvrDao.getWaterState(stationNo, startTime, endTime, null);
			map.put("infoList", list);
			map.put("error", 0);
		}
		jsonObject = JSONObject.fromObject(map);
		out.print(jsonObject.toString());
		out.flush();

	}	

}