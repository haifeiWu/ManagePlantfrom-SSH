package com.haifeiWu.daoImple;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.ActivityRecordDao;
import com.haifeiWu.entity.PHCSMP_Activity_Record;

/**
 * 询问、讯问、辨认、等候、休息、饮食等活动记录Dao层
 * 
 * @author wuhaifei
 * @d2016年8月16日
 */
@Repository("activityRecordDao")
public class ActivityRecordDaoImple extends
		DaoSupportImpl<PHCSMP_Activity_Record> implements ActivityRecordDao {

	private Transaction tx = null;
	private Session session = null;

	@Override
	public void saveActivitysInfor(List<PHCSMP_Activity_Record> activitys) {
		int i = 0;
		session = getSession();
		tx = session.beginTransaction();// 开启事务
		for (PHCSMP_Activity_Record activity : activitys) {
			i++;
			session.save(activity);
			if (i % 5 == 0) {// 以每5个数据作为一个处理单元
				session.flush();
				session.clear();
			}
		}
		tx.commit();// 提交事务
	}

}
