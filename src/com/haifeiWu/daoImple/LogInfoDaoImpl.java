package com.haifeiWu.daoImple;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.haifeiWu.dao.LogInfoDao;
import com.haifeiWu.entity.PHCSMP_LogInfo;
import com.haifeiWu.utils.MyHibernateSessionFactory;


public class LogInfoDaoImpl implements LogInfoDao  {  
    /** 
     * 通过hql语句得到数据库中记录总数 
     */  
    @Override  
    public int getAllRowCount(String hql)  
    {  
        Session session = MyHibernateSessionFactory.getCurrentSession();  
        Transaction tx = null;  
        int allRows = 0;  
        try  
        {  
            tx = session.beginTransaction();  
              
            Query query = session.createQuery(hql);  
              
            allRows = query.list().size();  
              
            tx.commit();  
              
        }  
        catch (Exception e)  
        {  
            if(tx != null)  
            {  
                tx.rollback();  
            }  
              
            e.printStackTrace();  
        }  
       
          
        return allRows;  
    }  
    /** 
     * 使用hibernate提供的分页功能，得到分页显示的数据 
     */  
    @SuppressWarnings("unchecked")  
    @Override  
    public List<PHCSMP_LogInfo> queryByPage(String hql, int offset, int pageSize)  
    {  
        Session session =MyHibernateSessionFactory.getCurrentSession();   
        Transaction tx = null;  
        List<PHCSMP_LogInfo> list = null;  
          
        try  
        {  
            tx = session.beginTransaction();  
              
            Query query = session.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize);  
              
            list = query.list();  
              
            tx.commit();  
              
        }  
        catch (Exception e)  
        {  
            if(tx != null)  
            {  
                tx.rollback();  
            }  
              
            e.printStackTrace();  
        }          
     return list;  
    }  
}  