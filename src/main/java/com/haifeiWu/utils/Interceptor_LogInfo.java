package com.haifeiWu.utils;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.haifeiWu.action.Activity_Record_Action;
import com.haifeiWu.action.Information_Collection_Action;
import com.haifeiWu.action.Leave_Recod_Action;
import com.haifeiWu.action.PHCSMP_BelongingS_Action;
import com.haifeiWu.action.PHCSMP_Personal_Check_Action;
import com.haifeiWu.action.PHCSMP_Suspect_Action;
import com.haifeiWu.action.UserAction;
import com.haifeiWu.entity.PHCSMP_LogInfo;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class Interceptor_LogInfo extends AbstractInterceptor {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String Operation_Info;  
	 private String Operation_Time;
	 private String Operation_Model;
	 private String Staff_Name1;
	 private String Staff_Name;
	 
	 @Autowired
	 private SessionFactory sessionFactory;
	 
	 private PHCSMP_LogInfo logInfo =new PHCSMP_LogInfo();
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
	   System.out.println("--------------->日志拦截器");
		
		Map session = invocation.getInvocationContext().getSession();  
        Object action = invocation.getAction();  
        String method = invocation.getProxy().getMethod();    
        if(StringUtils.isBlank(method)) method = "method";  
      //如果有下一个拦截器执行下一个拦截器，否则执行目标action
        invocation.invoke();
       System.out.println("进来没有");
       PHCSMP_Staff sysUser = (PHCSMP_Staff)session.get("user");  
       
       System.out.println(sysUser.getStaff_Name());
       if(sysUser!=null){
       String Staff_ID2=sysUser.getStaff_Name()+"";
    
       Staff_Name=Staff_ID2;            
       } 
           
        if (action instanceof UserAction) { 
        	
        	 if(method.equals("login")){  
             	 Operation_Time=getDate();
        		 Operation_Info= "登录";
        		 Operation_Model="用户登入";
        		 Staff_Name1=Staff_Name;
        		 addSysLog(Operation_Time,Staff_Name,Operation_Info,Operation_Model);  
        	 }
        	
        	 if(method.equals("logout")){  
        		System.out.println("注销");
        		 Operation_Time=getDate();
        		 Operation_Info= "注销";
        		 Operation_Model="用户注销";
        		 addSysLog(Operation_Time,Staff_Name1,Operation_Info,Operation_Model);  
        	 }
        }
        
        if(action instanceof Activity_Record_Action){
        	String Suspect_ID = (String)session.get("Suspect_ID");  
//            System.out.println(Suspect_ID);
              
        	if(method.equals("loadInfor")){
        		Operation_Info="增加"+Suspect_ID+"号嫌疑人信息";
        		Operation_Time=getDate();
        		Operation_Model="询问讯问记录";
        		 addSysLog(Operation_Time,Staff_Name,Operation_Info,Operation_Model);  		
        	}
        	
        }
        
        if(action instanceof PHCSMP_BelongingS_Action){
        	 String Suspect_ID = (String)session.get("Suspect_ID");  
             System.out.println(Suspect_ID);
             if(method.equals("addBelongingInfor")){
            	 Operation_Info="增加"+Suspect_ID+"号嫌疑人信息";
         		 Operation_Time=getDate();
         		 Operation_Model="安全检查-随身物品登记";
         		 addSysLog(Operation_Time,Staff_Name,Operation_Info,Operation_Model);           	 
             }
        }
        
        if(action instanceof PHCSMP_Personal_Check_Action){
        	 String Suspect_ID = (String)session.get("Suspect_ID");  
             System.out.println(Suspect_ID);
        	if(method.equals("loadInfor")){
        		 Operation_Info="增加"+Suspect_ID+"号嫌疑人信息";
          		 Operation_Time=getDate();
          		 Operation_Model="安全检查-人生检查";
          		 addSysLog(Operation_Time,Staff_Name,Operation_Info,Operation_Model); 
        	}
        }
        
        if(action instanceof PHCSMP_Suspect_Action){
        	String Suspect_ID = (String)session.get("Suspect_ID");  
            System.out.println(Suspect_ID);
       	if(method.equals("addSuspectInfor")){
       		Operation_Info="增加"+Suspect_ID+"号嫌疑人信息";
     		Operation_Time=getDate();
     		Operation_Model="入区人员信息登记";
     		addSysLog(Operation_Time,Staff_Name,Operation_Info,Operation_Model);
     		}
       	}
        
        if(action instanceof Leave_Recod_Action){
          	 String Suspect_ID = (String)session.get("Suspect_ID");  
               System.out.println(Suspect_ID);
          	if(method.equals("addLeaveRecordInfor")){
      		    Operation_Info="增加"+Suspect_ID+"号嫌疑人信息";
        		Operation_Time=getDate();
        		Operation_Model="出区信息登记";
        		addSysLog(Operation_Time,Staff_Name,Operation_Info,Operation_Model); 
          	}
          	
          }
        
        if(action instanceof Information_Collection_Action){
         	 String Suspect_ID = (String)session.get("Suspect_ID");  
              System.out.println(Suspect_ID);
         	if(method.equals("loadInfor")){
         		Operation_Info="增加"+Suspect_ID+"号嫌疑人信息";
           		Operation_Time=getDate();
           		Operation_Model="信息采集";
           		addSysLog(Operation_Time,Staff_Name,Operation_Info,Operation_Model); 
         	}
         	
         }
        return null;
	}
	/**
	 * 插入系统日志
	 * @param @param logName
	 * @param @param logContent   
	 * @author wuhaifei
	 * @date 2016年7月26日
 */
	private Transaction tx = null;
	private Session session = null;
	private void addSysLog(String Operation_Time,String Staff_Name,String Operation_Info,String Operation_Model) {
		session=this.sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		logInfo.setOperation_Info(Operation_Info);
		logInfo.setOperation_Model(Operation_Model);
		logInfo.setOperation_Time(Operation_Time);
		logInfo.setStaff_Name(Staff_Name);
		session.save(logInfo);
		tx.commit();//开始提交
	}
	

	 /*
     * 获取时间
     */
	private String getDate(){
        java.util.Date  date=new java.util.Date();
        java.sql.Date  logData=new java.sql.Date(date.getTime());//当前时间
        Operation_Time=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(logData);  //date转String
        return Operation_Time;
	}
}
