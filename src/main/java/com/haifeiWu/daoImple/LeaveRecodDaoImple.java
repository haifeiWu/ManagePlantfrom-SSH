package com.haifeiWu.daoImple;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.LeaveRecodDao;
import com.haifeiWu.entity.PHCSMP_Leave_Record;
import com.haifeiWu.entity.Temporary_Leave;

@Repository("leaveRecodDao")
public class LeaveRecodDaoImple extends DaoSupportImpl<PHCSMP_Leave_Record>
		implements LeaveRecodDao {

	private Transaction tx = null;
	private Session session = null;
	/**
	 * 批量保存
	 */
	@Override
	public void saveLeaveRecordInfor(List<Temporary_Leave> temporaryLeaves) {
		int i = 0;
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务
		for (Temporary_Leave temporaryLeave : temporaryLeaves) {
			i++;
			session.save(temporaryLeave);
			if (i % 5 == 0) {// 以每5个数据作为一个处理单元
				session.flush();
				session.clear();
			}
		}
		tx.commit();// 提交事务
	}

}
