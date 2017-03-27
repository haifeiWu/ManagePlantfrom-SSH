package com.haifeiWu.base;

import java.util.List;

public interface DaoSupport<T> {

	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	public void save(T entity);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	// public void update(T entity);
	public void update(String hql, Object... args);

	/**
	 * 删除实体
	 * 
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * 删除实体
	 * 
	 * @param entity
	 */
	public void deleteBySuspectID(String suspect_ID);

	/**
	 * 批量保存
	 * 
	 * @param list
	 */
	public void saveBatch(List<T> list);

	/**
	 * 注意该方法的使用，只能用于一张表中一个suspectID对应一条记录
	 * 
	 * @param suspectId
	 * @return
	 * @throws Exception
	 */
	public T findSuspectPublicById(String suspectId);

	// /**
	// * 注意该方法的使用，用于一张表中一个suspectID对应一条记录
	// *
	// * @param suspectId
	// * @return
	// */
	// public T findTemporaryLeaveInfoById(String suspectId);

	/**
	 * 通过属性名和属性值去查询
	 * 
	 * @param PropertyName
	 *            要查询的属性名称
	 * @param value
	 *            该属性的值
	 * @return
	 */
	public T findByPropertyName(String propertyName, Object value);

	/**
	 * 查询该表所有信息
	 * 
	 * @return
	 */
	public List<T> findAllInfor();

	/**
	 * 通过一个属性查询多条记录
	 * 
	 * @param PropertyName
	 * @param value
	 * @return
	 */
	public List<T> findListByPropertyName(String propertyName, Object value);

	/**
	 * 需要的话再加一个通过两个属性名和属性值查询的 可增加一个更新方法，传入更新的属性和根据哪个属性更新，再传入对应的值
	 * 
	 */
	/* public void updateSuspect(PHCSMP_Suspect suspect); */
}
