package com.jeesite.modules.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
public class InterceptorConfigure extends WebMvcConfigurerAdapter{
	 @Override
     public void addInterceptors(InterceptorRegistry registry) {
         // super.addInterceptors(registry);
         //静态资源不用管，springboot已做好
         registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")
                 .excludePathPatterns("/login.html","/","/toindex","/toLogin","/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");
     }
 }
