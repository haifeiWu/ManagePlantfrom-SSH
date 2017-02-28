package com.haifeiWu.daoImple;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.BelongingInforDao;
import com.haifeiWu.entity.PHCSMP_BelongingS;

/**
 * 随身物品检查记录的dao层代码实现
 * 
 * @author wuhaifei
 * @d2016年8月16日
 */
@Repository("belongingInforDao")
public class BelongingInforDaoImple extends DaoSupportImpl<PHCSMP_BelongingS>
		implements BelongingInforDao {

	private Transaction tx = null;
	private Session session = null;
	private String hql = null;
	
	/**
	 * 批量保存
	 */
	@Override
	public void savesaveBelongInforList(List<PHCSMP_BelongingS> belongs) {
		int i = 0;
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务
		for (PHCSMP_BelongingS belong : belongs) {
			i++;
			session.save(belong);
			if (i % 5 == 0) {// 以每5个数据作为一个处理单元
				session.flush();
				session.clear();
			}
		}
		tx.commit();// 提交事务
	}
	/**
	 * 查询多条结果
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PHCSMP_BelongingS> selectBelongInfor(String suspectId) {
		session = getSession();
		tx = session.beginTransaction();// 开启事务

		hql = "from PHCSMP_BelongingS where suspect_ID=?";

		Query query = session.createQuery(hql);
		query.setParameter(0, suspectId);
		List<PHCSMP_BelongingS> belongs = query.list();
		tx.commit();// 提交事务
		return belongs;
	}
}
