package com.whfstudio.test;

import java.util.List;

import org.junit.Test;

import com.manageplantfrom.entity.PHCSMP_Suspect;
import com.manageplantfrom.service.SuspectService;
import com.manageplantfrom.serviceImple.SuspectServiceImple;

public class testFind {
	@Test
	public void test01(){
		SuspectService suspectService = new SuspectServiceImple();
//		suspectService.findInforBySuspetcId("201610141609");
		PHCSMP_Suspect suspect = suspectService.findInforBySuspetcId("201610141609");
		System.out.println(suspect.toString());
	}
	@Test
	public void test02(){
		SuspectService suspectService = new SuspectServiceImple();
		List list = suspectService.findAllIdentifyCardType();
		for (Object obj : list) {
			System.out.println(obj.toString());
		}
	}
	
	@Test
	public void test03(){
		SuspectService suspectService = new SuspectServiceImple();
		List list = suspectService.findAllSuspectCause();
		for (Object obj : list) {
			System.out.println(obj.toString());
		}
	}
	
	@Test
	public void test04(){
		SuspectService suspectService = new SuspectServiceImple();
		PHCSMP_Suspect list = suspectService.selectPersonInforByBandID(1);
		System.out.println(list.toString());
	}

}
