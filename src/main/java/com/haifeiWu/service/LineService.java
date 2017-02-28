package com.haifeiWu.service;

import com.haifeiWu.entity.PHCSMP_Suspect;

/**
 * 回路服务
 * 
 * @author WXY
 *
 */
public interface LineService {
	
	/**
	 * 开启一路录像，判断是否饱和，并维护RecordVideo_State字段,置0或1
	 */
	public void startLine(String bandID,String cardReader_ID);
	/**
	 * 释放一路录像
	 */
	public void closeLine(String bandID,String cardReader_ID);
	
	
}
