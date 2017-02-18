package com.haifeiWu.AVUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.haifeiWu.dao.BandInforDao;
import com.haifeiWu.dao.RoomInforDao;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.utils.HttpRequest;
import com.haifeiWu.utils.PropertiesReadUtils;

/**
 * 录像指令的实现类,此类没有修改数据库中的信息，像recordVideo_state,请在相应的action中维护
 * 
 * @author WXY
 *
 */
public class VideoImpl implements Video {
	
	@Autowired
	private SuspectService suspectService;
	@Autowired
	private RoomInforDao roomInforDao;// 查询房间号的dao
	@Autowired
	private BandInforDao bandInforDao;
	
	@Override
	public String startRecording(int bandID, int cardReader_ID) throws IOException {
		
		return videoSupport(bandID, cardReader_ID, "StartRecording");
	}
	@Override
	public String stopRecording(int bandID, int cardReader_ID) throws IOException {
		return videoSupport(bandID, cardReader_ID, "StopRecording");
	}

	@Override
	public String pauseRecording(int bandID, int cardReader_ID) throws IOException  {
		return videoSupport(bandID, cardReader_ID, "PauseRecording");
	}

	@Override
	public String restartRecording(int bandID, int cardReader_ID) throws IOException  {
		return videoSupport(bandID, cardReader_ID, "RestartRecording");
	}
	private String videoSupport(int bandID, int cardReader_ID,String command) throws IOException {
		String result="";
		if(isValid()){
			String identificationCard=findIdentificationCard(bandID);//通过bandID找到嫌疑人身份ID
			int roomId=findRoomId(cardReader_ID);//通过读卡器的设备号找到对应的房间
			
			String json=packjson(cardReader_ID, identificationCard);//封装json数据
			
			if(command.equals("StartRecording")||command.equals("RestartRecording")){//切换录制源
				result=switchRecording(cardReader_ID, identificationCard, roomId);
			}
			//调用相应的录像指令
			result=HttpRequest.sendOkMCVPost(PropertiesReadUtils.getString(command), json);
		}
		return result;
	}

	
	
	/**
	 * 切换录制源，不对外暴露
	 * @return
	 * @throws IOException 
	 */
	private String switchRecording(int cardReader_ID,String identificationCard,int roomId) throws IOException {
		String result="";
		String json=packjson(cardReader_ID, identificationCard, roomId);
		result=HttpRequest.sendOkMCVPost(PropertiesReadUtils.getString("switchRecording"), json);
		return result;
	}
	/**
	 * 封装json
	 * @param cardReader_ID
	 * @param identificationCard
	 * @return
	 */
	private String packjson(int cardReader_ID,String identificationCard){
		
		Map<String, Object> map = new HashMap<String, Object>();//存放的是设备ID和身份证号
		map.put("policeId", cardReader_ID);//设备ID
		map.put("identificationCard", identificationCard);//身份证号
		String json = JSON.toJSONString(map);
		return json;
	}
	/**
	 * 封装json
	 * @param cardReader_ID
	 * @param identificationCard
	 * @param roomID
	 * @return
	 */
	private String packjson(int cardReader_ID,String identificationCard,int roomId){
		
		Map<String, Object> map = new HashMap<String, Object>();//存放的是设备ID和身份证号
		map.put("policeId", cardReader_ID);//设备ID
		map.put("identificationCard", identificationCard);//身份证号
		map.put("roomId", roomId);//身份证号
		String json = JSON.toJSONString(map);
		return json;
	}

	/**
	 * 通过手环ID去查找身份信息，然后返回身份证号
	 * @return
	 */
	private String findIdentificationCard(int bandID){
		
		return "";
	}
	/**
	 * 通过设备号，查找设备对应的roomId
	 * @param cardReader_ID
	 * @return
	 */
	private int findRoomId(int cardReader_ID) {
		
		return 0;
	}
	/**
	 * 有效则可以调用摄像头，无效则不能调用摄像头
	 * 
	 */
	private boolean isValid(){
		DateTimeFormatter format = DateTimeFormat
				.forPattern("yyyy-MM-dd HH:mm:ss");
		DateTime endTime = DateTime.parse(
				PropertiesReadUtils.getString("time"), format);//endtime
		DateTime startTime = new DateTime();
		int hours = Hours.hoursBetween(startTime, endTime).getHours();
		//有效则可以调用摄像头，无效则不能调用摄像头
		return hours>2;
	} 

}
