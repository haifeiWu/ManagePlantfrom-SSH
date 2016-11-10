package com.whfstudio.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import org.junit.Test;

import com.manageplantfrom.entity.PHCSMP_Activity_Record;

public class reflectTest {
	public static int count = 0;
	public static int total = 0;
	@Test
	public void test01() throws ClassNotFoundException{
		
		PHCSMP_Activity_Record model = new PHCSMP_Activity_Record();
		Class<?> c = Class.forName(PHCSMP_Activity_Record.class.getName());
		model.setActivity_Record("asdasd");
		model.setEnd_Time(new Date());
		
		String str = getString(model , c);
		System.out.println(str);
		System.out.println("不空字段："+reflectTest.count);
		System.out.println("总字段："+reflectTest.total);
	}
	
	 /** 
     * @MethodName : getString 
     * @Description : 获取类中所有属性及属性值 
     * @param o 
     *            操作对象 
     * @param c 
     *            操作类，用于获取类中的方法 
     * @return 
     */  
    public static String getString(Object o, Class< ? > c )  
    {  
        String result = c.getSimpleName( ) + ":";  
  
        // 获取父类，判断是否为实体类  
        if ( c.getSuperclass( ).getName( ).indexOf( "entity" ) >= 0 )  
        {  
            result +="\n<" +getString( o , c.getSuperclass( ) )+">,\n";  
        }  
  
        // 获取类中的所有定义字段  
        Field[ ] fields = c.getDeclaredFields( );  
  
        // 循环遍历字段，获取字段对应的属性值  
        for ( Field field : fields )  
        {  
            // 如果不为空，设置可见性，然后返回  
            field.setAccessible( true );
            
            try  
            {  
            	if(field.get( o )!=null){//只统计字符型变量
            		reflectTest.count++;
            	}
                // 设置字段可见，即可用get方法获取属性值。  
                result += field.getName( ) + "=" + field.get( o ) +",\n"; 
                reflectTest.total++;
            }
            catch ( Exception e )  
            {  
                // System.out.println("error--------"+methodName+".Reason is:"+e.getMessage());  
            }  
        }  
        if(result.indexOf( "," )>=0) result = result.substring( 0 , result.length( )-2 );  
        return result;
    }
	
}
