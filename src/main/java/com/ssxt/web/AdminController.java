package com.ssxt.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class AdminController {
	
	   //实际开发中，不要在这个admin下面放实际业务，这只是个蜜蜂洞。蜜蜂洞可以有很多个。
	   @RequestMapping("/admin")
	   public void admin(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		   request.setCharacterEncoding("UTF-8");
//			response.setCharacterEncoding("GBK");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			//这一句打印，直接打印有时候返回的是中文乱码。但如果是页面上的AJAX调用，估计不会乱码。
			out.print("哥，你成功登录了。。");        
			out.flush();
	   }

}
