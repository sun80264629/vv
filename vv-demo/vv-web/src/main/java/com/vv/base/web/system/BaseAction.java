package com.vv.base.web.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Scope(value="prototype")
public class BaseAction extends ActionSupport implements Preparable, ServletRequestAware,
ServletResponseAware{

	/** **/
	private static final long serialVersionUID = -9055941670571663466L;
	
	/** HttpServletRequest **/
	protected HttpServletRequest request;
	
	/** HttpServletResponse **/
	protected HttpServletResponse response;

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO 自动生成的方法存根
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO 自动生成的方法存根
		this.request = request;
	}

	@Override
	public void prepare() throws Exception {
		// TODO 自动生成的方法存根
		
	}
}
