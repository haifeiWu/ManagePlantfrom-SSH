package com.haifeiWu.daoImple;

import java.util.List;

import org.hibernate.Query;
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
// repository标注数据访问组件Dao，默认作用域是单例
@Repository("activityRecordDao")
public class ActivityRecordDaoImple extends
		DaoSupportImpl<PHCSMP_Activity_Record> implements ActivityRecordDao {

	private Transaction tx = null;
	private Session session = null;
	private String hql = null;

	/**
	 * 批量保存询问讯问等活动记录
	 */
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

	/**
	 * 查询记录，此方法和DaoSupport有重复，没必要写
	 */
	@Override
	public List<PHCSMP_Activity_Record> selectActivityRecordInfor(
			String suspectId) {
		session = getSession();
		tx = session.beginTransaction();// 开启事务

		hql = "from PHCSMP_Activity_Record where suspect_ID=?";

		Query query = session.createQuery(hql);
		query.setParameter(0, suspectId);
		@SuppressWarnings("unchecked")
		List<PHCSMP_Activity_Record> activities = query.list();
		tx.commit();// 提交事务
		return activities;
	}

	@Override
	public void updatevedio_Number(String vedio_Number, int band_ID,
			String identifyCard_Number) {
		hql = "update PHCSMP_Activity_Record s set s.vedio_Number=? ,s.band_ID=? where s.identifyCard_Number=?";
		update(hql, vedio_Number, band_ID, identifyCard_Number);

	}

}
