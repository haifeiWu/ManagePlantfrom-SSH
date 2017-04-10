package com.haifeiWu.utils;

import java.lang.reflect.Field;

/**
 * 完整性检查的工具类
 * 
 * @author wuhaifei
 * @d2016年9月26日
 */
public class CompleteCheck {

	/**
	 * 返回等于空的字段的个数
	 * 
	 * @Description : 判断类中所有属性值是否为空
	 * @param obj
	 *            操作对象
	 * @param c
	 *            操作类，用于获取类中的方法
	 * @return 返回实体类中值不为空的字段的个数
	 */
	public static int IsEqualsNull(Object obj, Class<?> c) {

		int count = 0;// 计数值，用来获取实体类中空字段的个数
		// 获取类中的所有定义字段
		Field[] fields = c.getDeclaredFields();
		// 循环遍历字段，获取字段对应的属性值
		for (Field field : fields) {
			// 如果不为空，设置可见性，然后返回
			field.setAccessible(true);
			try {

				System.out.println(field.getName() + ":" + field.get(obj));

				if (field.get(obj) == null || field.get(obj).equals("")) {
					count++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	/**
	 * 统计该类中所有的字段
	 * 
	 * @Description : 判断类中所有属性值是否为空
	 * @param obj
	 *            操作对象
	 * @param c
	 *            操作类，用于获取类中的方法
	 * @return 返回实体类中字段的个数
	 */
	public static int getFieldsNumber(Object obj, Class<?> c) {
		int count = 0;// 计数值，用来获取实体类中空字段的个数
		// 获取类中的所有定义字段
		Field[] fields = c.getDeclaredFields();
		// 循环遍历字段，获取字段对应的属性值
		for (Field field : fields) {
			// 如果不为空，设置可见性，然后返回
			field.setAccessible(true);
			try {
				count++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	/**
	 * 完整性检查，传入一个对象，传入该对象对应的类 Class<?> c =
	 * Class.forName(PHCSMP_Suspect.class.getName());
	 * 
	 * @param obj
	 * @param c
	 */

	/*
	 * public static int completeCheck(Object obj,Class<?> c,int n){ int
	 * IsEqualsNull=IsEqualsNull( obj, c)-n; int
	 * getFieldsNumber=getFieldsNumber( obj, c)-n; int
	 * completePercent=(int)((float
	 * )(getFieldsNumber-IsEqualsNull)/getFieldsNumber*100); return
	 * completePercent; }
	 */

	// public static int completeCheck(Object obj, Class<?> c, int n) {
	// int totalField = getFieldsNumber(obj, c) - n;// 应该填写的总数
	// int fillField = getFieldsNumber(obj, c) - IsEqualsNull(obj, c) - n;//
	// 填写的总数
	// //
	// System.out.println(fillField+"字段++++++++++++++"+totalField+"字段++++++++++++++");
	// int completePercent = (int) ((float) fillField / totalField * 100);
	// return completePercent;
	// }

	// public static <T> int completeCheck(T obj, Class<?> c) {
	// //空的字段的个数
	// int count = CompleteCheck.IsEqualsNull(obj, c);// 获取model对象为空的字段的个数
	// //字段
	// int fieldsNumber = CompleteCheck.getFieldsNumber(obj, c);// 返回实体类中总字段数
	// obj.setFill_record(fieldsNumber - count - 3);//
	// // 设置已填写的字段数，，，3是除去主键、FillRecord、TotalRecord
	// obj.setTotal_record(fieldsNumber - 3);// 设置应填写的字段
	// }

}
