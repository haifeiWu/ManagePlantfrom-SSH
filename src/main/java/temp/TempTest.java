package temp;

import java.util.List;

import org.junit.Test;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.SuspectDao;
import com.haifeiWu.daoImple.SuspectDaoImple;
import com.haifeiWu.entity.PHCSMP_Dic_Action_Cause;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.serviceImple.SuspectServiceImple;

public class TempTest {
	
	public static void main(String[] args) {
//		SuspectService ss=new SuspectServiceImple();
//		List<PHCSMP_Dic_Action_Cause> list=ss.findAllSuspectCause();
//		for(PHCSMP_Dic_Action_Cause ele :list){
//			System.out.println(ele.toString());
//		}
		SuspectDao dao=new SuspectDaoImple();
		dao.findAllSuspectCause();
//		DaoSupport<PHCSMP_Dic_Action_Cause> dao=new DaoSupportImpl<PHCSMP_Dic_Action_Cause>();
//		List<PHCSMP_Dic_Action_Cause> list=dao.findAllInfor();
//		for(PHCSMP_Dic_Action_Cause ele :list){
//			System.out.println(ele.toString());
//		}
		
	}
//	@Test
//	public void tt(){
//		DaoSupportImpl<PHCSMP_Dic_Action_Cause> dao=new DaoSupportImpl<PHCSMP_Dic_Action_Cause>();
//		List<PHCSMP_Dic_Action_Cause> list=dao.findAllInfor();
//		for(PHCSMP_Dic_Action_Cause ele :list){
//			System.out.println(ele.toString());
//		}

//	}
}
