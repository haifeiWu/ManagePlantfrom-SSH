package com.manageplantfrom.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 主页面 的设计
 * @author wuhaifei
 *
 * @date 2016年8月9日
 */
public class HomeAction extends ActionSupport {
	
	public String top(){
		return "top";
	}
	
	public String left(){
		return "left";
	}
	
	public String index(){
		return "index";
	}
	
	public String main(){
		return "main";
	}
}
