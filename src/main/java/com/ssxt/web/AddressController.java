package com.ssxt.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


//import com.dsoft.model.Address;
//import com.dsoft.model.Identity;
//import com.smart.dao.AddressDao;
//import com.smart.dao.IdentityDao;

@Controller
@RequestMapping(value = "/addr")
public class AddressController {
	 
	 
//	@Autowired
//	private AddressDao addressDao;
//	
//	@Autowired
//	private IdentityDao identityDao;
	
	//直接返回内容的演示
   @RequestMapping("/hello")
   public void saveAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
     	response.setContentType("application/json");        
		PrintWriter out = response.getWriter();

		out.print("hello!!");        
		out.flush();
   }
   
   //跳转到JSP页面的演示
   @RequestMapping(value = "/demo")
   public ModelAndView demo(HttpServletRequest request, HttpServletResponse response) throws IOException {
//       List<Lottery> lots=lotDao.getAll();

       return new ModelAndView("demo");
   }   

}