package com.haifeiWu.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.haifeiWu.service.LogInfoService;

@Controller
@Scope("prototype")
public class Log_Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private LogInfoService logInfoService;
	//
	// @Override
	// public String execute() throws Exception {
	// // 表示每页显示5条记录，page表示当前网页
	// PageBean pageBean = logInfoService.getPageBean(10, page);
	//
	// HttpServletRequest request = ServletActionContext.getRequest();
	//
	// request.setAttribute("pageBean", pageBean);
	//
	// return SUCCESS;
	// }

	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
