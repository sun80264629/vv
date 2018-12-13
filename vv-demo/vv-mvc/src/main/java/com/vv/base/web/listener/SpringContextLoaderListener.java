package com.vv.base.web.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vv.base.web.context.SpringApplicationContext;

public class SpringContextLoaderListener extends ContextLoaderListener{
	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		new SpringApplicationContext().setApplicationContext(WebApplicationContextUtils.getWebApplicationContext(event.getServletContext()));
	}
}
