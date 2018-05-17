package com.ssxt.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")//演示CONTEXT顶级
public class LoginController {

	   @RequestMapping("/login")
	   public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print("doing login!!");        
			out.flush();
	   }
	   
	   @RequestMapping("/low")
	   public void loginWeb(HttpServletRequest request, HttpServletResponse response) throws IOException {
			response.setCharacterEncoding("UTF-8");
//			response.setContentType("application/json");   
			String loginID=request.getParameter("name");
			String pass=request.getParameter("psw");
			PrintWriter out = response.getWriter();
			out.print("doing login!!credential="+loginID+pass);        
			out.flush();
	   }
	   
	   //跳转到JSP页面的演示
	   @RequestMapping(value = "/main")
	   public ModelAndView demo(HttpServletRequest request, HttpServletResponse response) throws IOException {
//	       List<Lottery> lots=lotDao.getAll();

	       return new ModelAndView("main");
//	       return new ModelAndView("main2");
	   }   
	   
}
