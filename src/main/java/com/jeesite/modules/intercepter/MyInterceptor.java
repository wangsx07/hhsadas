package com.jeesite.modules.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class MyInterceptor implements HandlerInterceptor{
	 @Override
	   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		/* String path=request.getContextPath();
		 int code=response.getStatus();
			System.out.println("--------postHandle------------>"+code+":"+path);
		 if(code==302) {
			 response.sendRedirect("http://221.238.40.75:8188/hhscar/admin/login"); 
		 }*/
	   }
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		String path=request.getContextPath();
//		 int code=response.getStatus();
//			System.out.println("--------afterCompletion------------>"+code+":"+path);
//	   int code=response.getStatus();
//		System.out.println("--------------------->"+code);
/*			if(code==200) {
			response.addHeader("location", "http://221.238.40.75:8188/hhscar/admin/login");
			response.setHeader("Location", "https://www.baidu.com");
			//response.sendRedirect("https://www.baidu.com"); 
		}
*/	
	}

}
