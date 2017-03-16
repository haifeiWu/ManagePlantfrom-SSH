package com.haifeiWu.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.haifeiWu.entity.PHCSMP_Staff;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpSession session=ServletActionContext.getRequest().getSession();
		PHCSMP_Staff user=(PHCSMP_Staff) session.getAttribute("user");
		if(user!=null){
			return invocation.invoke();
		}
		return "toLogin";
	}

}
