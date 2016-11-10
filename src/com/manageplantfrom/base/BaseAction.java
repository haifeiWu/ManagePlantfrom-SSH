package com.manageplantfrom.base;

import java.lang.reflect.ParameterizedType;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author wuhaifei
 *
 * @date 2016年8月10日
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>,ServletRequestAware,
												ServletResponseAware,ServletContextAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected T model;
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext application;
	//继承的问题？？？？
	public BaseAction(){
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
		// 通过反射创建model的实例
		try {
			model = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	@Override
	public void setServletContext(ServletContext application) {
		this.application = application;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public T getModel() {
		return model;
	}

}
