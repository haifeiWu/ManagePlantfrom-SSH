package com.haifeiWu.AVUtils;

import java.io.IOException;

/**
 * 发送录像指令的接口
 * @author WXY
 *
 */
/**
 * 当嫌疑人要进行第一间房时，先切换录制源，然后调用开始录像指令
 * 结束时，调用暂停录像指令。
 * 进入下一间房，调用切换录制源，重新开始录像指令
 * 结束时，暂停
 * ...
 * 进入最后一间房，先切换录制源，重新开始
 * 结束时，停止录像
 * 
 * 
 * @author WXY
 *
 */
public interface Video {
	/**
	 * 开始录像指令，传入手环ID和设备ID，方法里会调用切换录制源的指令
	 * @return 返回的是响应
	 * @throws IOException 
	 */
	public String startRecording(int bandID,int cardReader_ID) throws IOException;
	/**
	 * 停止录像
	 * @return
	 * @throws IOException 
	 */
	public String stopRecording(int bandID,int cardReader_ID) throws IOException;
	/**
	 * 暂停录像
	 * @return
	 */
	public String pauseRecording(int bandID,int cardReader_ID) throws IOException;
	/**
	 * 重新开始录像，传入手环ID和设备ID，方法里会调用切换录制源的指令
	 * @return
	 */
	public String restartRecording(int bandID,int cardReader_ID) throws IOException;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
