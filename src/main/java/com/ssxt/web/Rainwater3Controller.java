package com.ssxt.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssxt.model.StStbprpB;
import com.ssxt.service.StpptnrNewService;
import com.ssxt.service.StpptnrService;
import com.ssxt.service.StstbprpbService;
import com.ssxt.vo.rain.MonthCumulation;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/rw3")
public class Rainwater3Controller {
	
	// 测站业务类
	@Autowired
	StstbprpbService ststbprpbService;

	// 雨量业务类
	@Autowired
	StpptnrService stpptnrService;

	@Autowired
	StpptnrNewService stpptnrnewService;
	
	/**
	 * 通过省份，获得相关站点
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
		String addvcdhead=request.getParameter("sf").trim();
		String[] areaNo=new String[]{addvcdhead};
		List<StStbprpB> lis=ststbprpbService.findgetStstbprp(areaNo, "PP");
//		List<StStbprpB> lis=ststbprpbService.getSitesInLiuyu(liuyuName, type);
		map.put("sites", lis);
		map.put("error", 0);
		jsonObject = JSONObject.fromObject(map);
		out.print(jsonObject.toString());
		out.flush();
	}	
	
	/**
	 * 通过站点名称，获得各月雨量累加
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws Exception
	 */
	@RequestMapping(value = "/sitestatistics", method = RequestMethod.GET)
	public void sitestatistics(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		PrintWriter out = response.getWriter();
		String sitename=request.getParameter("sitename").trim();
		List<MonthCumulation> lis=null;
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		try {
			lis = ststbprpbService.sitestatistics(sitename,startTime,endTime);
			map.put("sites", lis);
			map.put("error", 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
			map.put("sites", lis);
			map.put("error", 1);
		}
		jsonObject = JSONObject.fromObject(map);
		out.print(jsonObject.toString());
		out.flush();
	}

}
