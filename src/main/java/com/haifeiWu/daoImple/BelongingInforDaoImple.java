package com.haifeiWu.daoImple;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.BelongingInforDao;
import com.haifeiWu.entity.PHCSMP_BelongingS;
import com.haifeiWu.utils.MySessionFactory;
/**
 * 随身物品检查记录的dao层代码实现
 * @author wuhaifei
 * @d2016年8月16日
 */
@Repository("belongingInforDao")
public class BelongingInforDaoImple extends DaoSupportImpl<PHCSMP_BelongingS> implements BelongingInforDao {

	private Transaction tx = null;
	private Session session = null;
	
	@Override
	public void savesaveBelongInforList(List<PHCSMP_BelongingS> belongs) {
		int i=0;
		session = this.getSession();
		tx = session.beginTransaction();//开启事务
		for (PHCSMP_BelongingS belong : belongs) {
			i++;
			session.save(belong);
		    if(i%5==0){//以每5个数据作为一个处理单元
		        session.flush();
		        session.clear();
		    }
		}
		tx.commit();//提交事务
	}

}
