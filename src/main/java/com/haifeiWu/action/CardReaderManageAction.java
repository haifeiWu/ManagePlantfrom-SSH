package com.haifeiWu.action;


import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haifeiWu.entity.PHCSMP_CardReader;
import com.haifeiWu.service.CardReaderService;
@Controller
@RequestMapping("/CardReaderManage")
@Scope("prototype")

public class CardReaderManageAction {
	private static final long serialVersionUID = 1201107017949225716L;
	
	
	@Autowired
	private CardReaderService cardReaderService;
	
	@RequestMapping(value="/updateCardReader" ,method=RequestMethod.POST)
	public String updateCardReader(@RequestParam List<Integer> cardReader_ID,@RequestParam List<String> cardReader_Name,@RequestParam List<Integer> cardReader_Type, HttpServletRequest request){
		System.out.println("++++++++++++++进入函数+++++++++++++++++++++++++++++++");
		
		for(int i=0;i<cardReader_ID.size();i++){
			
			cardReaderService.update(cardReader_Name.get(i), cardReader_Type.get(i), cardReader_ID.get(i));
		
		}
		
		return loadInfor(request);
	}
	@RequestMapping(value="/loadInfor")	
	public String loadInfor(HttpServletRequest request){
		List<PHCSMP_CardReader> cardReaderList =cardReaderService.findAllCardReader();
		request.setAttribute("cardReaderlist", cardReaderList);
		return "/WEB-INF/jsp/devicesManage/cardReader";
	}
	


	

}
