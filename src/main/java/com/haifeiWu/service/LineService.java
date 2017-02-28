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
	 * 判断是否饱和
	 */
	public boolean isFull();
	
	/**
	 * 开启一路录像
	 */
	public void startLine();
	/**
	 * 释放一路录像
	 */
	public void closeLine();
	
	
}
