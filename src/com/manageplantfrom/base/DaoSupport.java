package com.manageplantfrom.base;

import java.util.List;


public interface DaoSupport<T> {

	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	void update(T entity);
	
	/**
	 * 根据档案编号查找嫌疑人信息
	 * @param suspectId
	 * @return
	 */
	public T findInforBySuspetcId(String suspectId);

}
