package com.demo.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloWorldController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		//1、收集参数、验证参数
		//2、绑定参数到命令对象
		//3、将命令对象传入业务对象进行业务处理
		//4、选择下一个页面
		ModelAndView mv = new ModelAndView();
		//添加模型数据 可以是任意的POJO对象
		mv.addObject("message", "Hello World!");
		//设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
		mv.setViewName("hello");
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
        ServletContext servletContext = webApplicationContext.getServletContext();  
        String path = servletContext.getRealPath("/");
        
        mv.addObject("webpath", path);
        path = this.getClass().getResource("/").getPath();
        mv.addObject("classpath", path);
        String SAMLRequest = request.getParameter("SAMLRequest");
        mv.addObject("SAMLRequest", request.getParameter("SAMLRequest"));
        System.out.println(SAMLRequest);
//		AuthenticationManagerBeanDefinitionParser dd = new AuthenticationManagerBeanDefinitionParser();
//		DefaultLoginPageGeneratingFilter dd = new DefaultLoginPageGeneratingFilter();
		return mv;
	}
	

}