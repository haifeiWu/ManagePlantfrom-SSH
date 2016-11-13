package com.haifeiWu.interceptor;

import org.apache.struts2.ServletActionContext;

import com.haifeiWu.entity.PHCSMP_Staff;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 检查登录状态的拦截器
 * @author wuhaifei
 * @d2016年11月13日
 */
public class CheackLoginStateInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("检查登录状态的拦截器----->");
		PHCSMP_Staff user = (PHCSMP_Staff) ServletActionContext.getContext().getSession().get("user");//从session域中获取user
		
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String actionUrl = namespace + actionName; // 对应的权限URL
		
		System.out.println("登录状态检测："+actionUrl);
		
		if(user == null){
			if(actionUrl.startsWith("/user_login")){
				return invocation.invoke();//如果去登陆,放行去登录
			}else{
				return "unLoginState";//未登录状态
			}
		}else{
			return invocation.invoke();//直接放行
		}
	}

}
