package com.ssxt.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/rs/obj")//演示CONTEXT顶级
public class RestController {
	
	//http://localhost:8080/smb3/rs/obj/2323，这个是类似的测试地址
	//根据ID获取不同的内容，通过@PathVariable 获得属性  
    @RequestMapping(value="/{id}",method=RequestMethod.GET)  
    public String read(@PathVariable String id,HttpServletRequest request ,HttpServletResponse response) throws IOException{  
        request.setAttribute("msg", "Hello, This is a example of Spring3 RESTful get! ID:"+id+"");  
//		PrintWriter out = response.getWriter();
//		out.print("doing get... id="+id);        
//		out.flush();
        return "rest";  
//        return null;  
    }  
    
    
    /**　保存新增，用POST　*/
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView create(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
//		response.setContentType("application/json"); 
		PrintWriter out = response.getWriter();
		out.print("您调用了POST方法，这是一个最简单的例子... "+request.getParameter("txt1"));        
		out.flush();
		System.out.println("您调用了POST方法，这是一个最简单的例子... "+request.getParameter("txt1"));
    	return null;
    }
    
    /**　保存更新　*/
    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public ModelAndView update(@PathVariable Long id,HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
//		response.setContentType("application/json"); 
		PrintWriter out = response.getWriter();
		out.print("您调用了PUT方法，这是一个最简单的例子... "+request.getParameter("txt1"));        
		out.print("正在更新对象，ID="+id);        
		out.flush();
    	return null;
    }
    
//    /**　保存新增，用POST　*/
//    @RequestMapping(method=RequestMethod.POST)
//    public　ModelAndView　create(HttpServletRequest request,HttpServletResponse response,UserInfo userInfo)　throws　Exception　{
//    	userInfoManager.save(userInfo);
//    	return　new　ModelAndView(LIST_ACTION);
//    }

}
