package com.jeesite.modules.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       /* HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("this is MyFilter,url :"+request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
        HttpServletResponse res= (HttpServletResponse)servletResponse;
        int code=res.getStatus();
        System.out.println("==doffilter="+code);
        if(code==302) {
        	System.out.println(res.getHeader("Location"));
        	res.setHeader("Location", "https://www.baidu.com");
        	res.addHeader("test", "https://www.baidu.com");
        	System.out.println("test"+":"+res.getHeader("test"));
        }*/
    }

    @Override
    public void destroy() {
    	
    }
}
