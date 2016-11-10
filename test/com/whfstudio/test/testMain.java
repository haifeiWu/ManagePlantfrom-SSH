package com.whfstudio.test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.manageplantfrom.dao.BandInforDao;
import com.manageplantfrom.dao.RoomInforDao;
import com.manageplantfrom.daoImple.BandInforDaoImple;
import com.manageplantfrom.daoImple.RoomInforDaoImple;
import com.manageplantfrom.service.SuspectService;
import com.manageplantfrom.serviceImple.SuspectServiceImple;
import com.manageplantfrom.utils.HttpRequest;

public class testMain {
public static void main(String[] args) throws IOException, InterruptedException {
	Map<String, String> map = new HashMap<String, String>();
    map.put("policeId", "123wuhaifei");
    map.put("identificationCard", "123133546456");
    String json = JSON.toJSONString(map);
    
    Map<String,Object> map1 =  new HashMap<String, Object>();
    Map<String,String> map2 =  new HashMap<String, String>();
    map1.put("enableMp", "0");
    map1.put("showMpMode", "0");
    
    map2.put("expandMode", "0");
    map2.put("input", "4.4");
    map1.put("subPicInfo", map2);
    
    String json2 = JSON.toJSONString(map1);
    json2  =json2.substring(0,31)+"["+json2.substring(31,63)+"]"+json2.substring(63,79);
//    System.out.println(json2);
    
    Date nowTime = new Date();
    System.out.println(nowTime);
    
//	String str1 = HttpRequest.sendOkMCVPost("http://192.168.1.96:8765/SxSetSplitType.psp",json2);
//	System.out.println(str1);
    
//	String str1 = HttpRequest.sendOkMCVPost("http://192.168.1.96:8765/SxStartRecording.psp",json);
//	System.out.println(str1);
    
    
//    String str1 = HttpRequest.sendMCVPost("http://192.168.1.96:8765/SxStopRecording.psp",null);
//	System.out.println(str1);
    
	
//	wait(10000);
	
//	String str = HttpRequest.sendOkMCVPost("http://192.168.1.96:8765/SxStopRecording.psp",null);
//	System.out.println(str);
    
//    RoomInforDao roomInfor = new RoomInforDaoImple();
//	int roomId =  roomInfor.findRoomIDByDeviceId("01");
//	System.out.println(roomId);
}
@Test
public void test02(){
//	BandInforDao bandInforDao = new BandInforDaoImple();
	SuspectService suspectService = new SuspectServiceImple();
	int count = suspectService.updateSuspectInforByBandId(2,5);
//	int bandId = bandInforDao.findBandIdByWristId("E2004134721301400260F380 ");
	System.out.println("更改数据条数："+count);
}
	@Test
	public void test03(){
		Integer a=1000,b=1000;
		Integer c=100,d=100;
		System.out.println(a==b);
		System.out.println(c==d);
	}
	@Test
	public void test04() throws Exception{
		//取出IntegerCache缓存中所有的值，-128~127
		Class cache = Integer.class.getDeclaredClasses()[0]; //1
	    Field myCache = cache.getDeclaredField("cache"); //2
	    myCache.setAccessible(true);//设置字段可见性
	    Integer[] newCache = (Integer[]) myCache.get(cache); //4
	    for(int i = 0; i < newCache.length; i++){
	        System.out.printf("index[%d]-->vlaue[%d]\n",i,newCache[i]);
	    }
	}
	@Test
	public void test05(){
		int i,j,k;
		for (i = 0; i < 3; i++) 
		{
		for(j = 1; j < 4; j++)
		{
		for(k = 2; k < 5; k++) 
		{
		if((i == j) && (j == k))
		System.out.println(i);
		}}}
	}
}

