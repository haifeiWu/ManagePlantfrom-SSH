package com.haifeiWu.entity;

import java.util.List;

/**
 * 设备初始化需向后台传list，spring后台不能直接接收list，所以定义
 * 一个带list成员的实体类，通过在后台定义与此实体类成员bandList同名的
 * 对象实例，便可将将传到后台的list注入成员bandList中
 * @author 20586
 *
 */
public class BanfList {
private List<PHCSMP_Band> bandList;

public List<PHCSMP_Band> getBandList() {
	return bandList;
}

public void setBandList(List<PHCSMP_Band> bandList) {
	this.bandList = bandList;
}

}
