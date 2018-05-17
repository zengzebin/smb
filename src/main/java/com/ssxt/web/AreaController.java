package com.ssxt.web;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssxt.dao.Impl.AreaDaoImpl;
import com.ssxt.model.Groups;
import com.ssxt.vo.rain.AdminAreaInfo;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/area")
public class AreaController {
	
	@Autowired
	private AreaDaoImpl areaDao;
	
	@RequestMapping(value = "/getprovince",method =RequestMethod.GET) 
	   public void getprovince(HttpServletRequest request, HttpServletResponse response) throws Exception {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json"); 
		 		  AdminAreaInfo info=new AdminAreaInfo();
		 		  String hql="from Groups o WHERE o.code LIKE '%0000' ORDER BY o.code";
	    			 List<Groups> list=areaDao.getListByHql(hql);
	    			  info.setInfoList2(list);
	    			  info.setError(0);

	              JSONObject jsonObject = JSONObject.fromObject(info);
	              System.out.println(jsonObject.toString());		
			      PrintWriter out = response.getWriter();
			      out.print(jsonObject.toString());  
			       out.flush();
	   }
	
	  @RequestMapping(value = "/getareaunder",method =RequestMethod.GET) 
	   public void getareaunder(HttpServletRequest request, HttpServletResponse response) throws Exception {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json"); 
				String areaNo=request.getParameter("areaNo").trim();
		 		  AdminAreaInfo info=new AdminAreaInfo();
		 		  String hql="from Groups o WHERE o.code LIKE '"+areaNo+"%' ORDER BY o.code";
	    			 List<Groups> list=areaDao.getListByHql(hql);
	    			  info.setInfoList2(list);
	    			  info.setError(0);

	              JSONObject jsonObject = JSONObject.fromObject(info);
	              System.out.println(jsonObject.toString());		
			      PrintWriter out = response.getWriter();
			      out.print(jsonObject.toString());  
			       out.flush();
	   }
	  
	  @RequestMapping(value = "/getareaunder2",method =RequestMethod.GET) 
	   public void getareaunder2(HttpServletRequest request, HttpServletResponse response) throws Exception {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json"); 
				String pid=request.getParameter("pid").trim();
		 		  AdminAreaInfo info=new AdminAreaInfo();
		 		  String hql="from Groups o WHERE o.parentId='"+pid+"'";
	    			 List<Groups> list=areaDao.getListByHql(hql);
	    			  info.setInfoList2(list);
	    			  info.setError(0);
	              JSONObject jsonObject = JSONObject.fromObject(info);
	              System.out.println(jsonObject.toString());		
			      PrintWriter out = response.getWriter();
			      out.print(jsonObject.toString());  
			       out.flush();
	   }

}
