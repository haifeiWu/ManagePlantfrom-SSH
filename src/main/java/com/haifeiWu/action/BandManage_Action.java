package com.haifeiWu.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haifeiWu.entity.BanfList;
import com.haifeiWu.entity.PHCSMP_Band;
import com.haifeiWu.service.BandService;


@Controller
@RequestMapping("/band")
@Scope("prototype")
public class BandManage_Action {
	
	@Autowired
	private BandService bandService; // 手环信息
	
	private List<PHCSMP_Band> bandList;
/**
 * 查询所有手环
 * @param request
 * @return
 */
	@RequestMapping(value = "/bandFindAll")
	public String bandFindAll(HttpServletRequest request){
		System.out.println("查找所有的手环信息");
		List<PHCSMP_Band> bandCheckInfo = bandService.findAllBundInfor();
		if (bandCheckInfo == null) {
			return "NULL";// 处理空的情况
		} else {
			request.setAttribute("bandCheckInfo", bandCheckInfo);
			for (PHCSMP_Band pHCSMP_Band : bandCheckInfo) {
				System.out.println(pHCSMP_Band.toString());
			}
		}
			return "/WEB-INF/jsp/bandmanage/band";
		
		
	}
	/**
	 * 修改所有手环
	 * @return 
	 * SpringMVC框架不能直接传入List，当页面以<c:foreach>提交时，spring会自动打包成一个对象，所以必须
	 * 先创建一个list实体类再通过对象传入方法，再通过get方法得到list属性，才可以遍历
	 */
	@RequestMapping(value ="/updateBand")
	public String updateBand(BanfList bandList,HttpServletRequest request){
		System.out.println("进入手环初始化方法");
		for(PHCSMP_Band pHCSMP_Band :bandList.getBandList()){
			System.out.println(pHCSMP_Band.getBand_ID());
			System.out.println(pHCSMP_Band.getRemark());
			System.out.println(pHCSMP_Band.getBand_Type());
			bandService.updateRemarkAndTypeById(pHCSMP_Band.getRemark(), pHCSMP_Band.getBand_Type(), pHCSMP_Band.getBand_ID());
		}
		return bandFindAll(request);
	}
	public List<PHCSMP_Band> getBandList() {
		return bandList;
	}
	public void setBandList(List<PHCSMP_Band> bandList) {
		this.bandList = bandList;
	}
	
}
