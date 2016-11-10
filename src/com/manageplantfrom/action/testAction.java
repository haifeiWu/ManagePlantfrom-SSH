package com.manageplantfrom.action;

import com.opensymphony.xwork2.ActionSupport;

public class testAction extends ActionSupport{
	@Override
	public String execute() throws Exception {
		System.out.println("wuhaifei,nihao!");
		return SUCCESS;
	}
}
