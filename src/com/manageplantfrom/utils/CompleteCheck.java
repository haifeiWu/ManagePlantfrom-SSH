package com.manageplantfrom.utils;

import java.lang.reflect.Field;

import com.whfstudio.test.reflectTest;

/**
 * 完整性检查的工具类
 * @author wuhaifei
 * @d2016年9月26日
 */
public class CompleteCheck {
	/**
	 * @Description : 判断类中所有属性值是否为空 
	 * @param obj 操作对象 
	 * @param c 操作类，用于获取类中的方法 
	 * @return 返回实体类中值不为空的字段的个数
	 */
	public static int IsEqualsNull(Object obj, Class< ? > c ){
		
		int count = 0;//计数值，用来获取实体类中空字段的个数
		// 获取类中的所有定义字段  
		Field[ ] fields = c.getDeclaredFields();  
		// 循环遍历字段，获取字段对应的属性值  
		for ( Field field : fields ){  
		    // 如果不为空，设置可见性，然后返回  
		    field.setAccessible(true);
			try {
				
				System.out.println(field.getName()+":"+field.get(obj));
				
				if(field.get(obj)==null||field.get(obj).equals("")){
					count++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
//		count = count+1;//减去实体类中主键id为零的情况，即不统计每个实体类的主键
		return count;
	}
	
	
	/**
	 * @Description : 判断类中所有属性值是否为空 
	 * @param obj 操作对象 
	 * @param c 操作类，用于获取类中的方法 
	 * @return 返回实体类中字段的个数
	 */
	public static int getFieldsNumber(Object obj, Class< ? > c){
		int count = 0;//计数值，用来获取实体类中空字段的个数
		// 获取类中的所有定义字段  
		Field[ ] fields = c.getDeclaredFields( );  
		// 循环遍历字段，获取字段对应的属性值  
		for ( Field field : fields ){  
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
}
