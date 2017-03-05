package com.haifeiWu.service;

/**
 * 回路服务
 * 
 * @author WXY
 * 
 */
public interface LineService {
	/**
	 * 判断是否饱和， 不饱和可以录像，返回true
	 */
	public boolean isFull();

	/**
	 * 开启一路录像，判断是否饱和，
	 */
	public void startLine();

	/**
	 * 释放一路录像
	 */
	public void closeLine();

}
