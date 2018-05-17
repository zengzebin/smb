package com.ssxt.common.tools;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
//import util.aop.SysContent;

public class SetCharacterEncodingFilter implements Filter {
//    protected final Logger log = Logger.getLogger(getClass());
	protected String encoding = null;
	protected String igno = null;

	protected FilterConfig filterConfig = null;

	protected boolean ignore = true;

	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res=(HttpServletResponse)response;
		HttpServletRequest rq=(HttpServletRequest) request;
		RequestDispatcher rd = rq.getRequestDispatcher("/");
		String uri=rq.getRequestURL().toString();
		
      //  log.debug(uri);
//        if(uri.contains("china960")){
//            res.sendRedirect("http://www.baidu.com");
//            return;
//        }
//        else{
            if (ignore || (request.getCharacterEncoding() == null)) {
                String encoding = selectEncoding(request);
                if (encoding != null) {
                    request.setCharacterEncoding(encoding);
                  // System.out.println(request.getParameter("provinceNmae").toString());
                }
            }
            try{
                chain.doFilter(request, response);
            }catch (Exception e){
              //  e.printStackTrace();
                if(e instanceof org.springframework.web.util.NestedServletException){
                //   log.debug("NestedServletException caught.");
                //   log.debug(e.getCause());
                   res.sendRedirect(rq.getContextPath());
//                   res.sendRedirect("http://106.3.45.27:88");
                }else {}//log.debug("not a NestedServletException: "+e.getMessage());
            }
//        }
	}

	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		this.igno = filterConfig.getInitParameter("igno");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null)
			this.ignore = true;
		else if (value.equalsIgnoreCase("true"))
			this.ignore = true;
		else if (value.equalsIgnoreCase("yes"))
			this.ignore = true;
		else
			this.ignore = false;
	}

	protected String selectEncoding(ServletRequest request) {

		return (this.encoding);
	}

}
