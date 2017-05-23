package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.stereotype.Service;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.entity.PHCSMP_Function;
import com.haifeiWu.service.FunctionService;

@Service("functionService")
public class FunctionServiceImple extends DaoSupportImpl<PHCSMP_Function>
		implements FunctionService {

	@Override
	public List<PHCSMP_Function> findAllFunctions() {
		return findAllInfor();
	}

	@Override
	public List<PHCSMP_Function> findParentFunctions() {
		return findListByPropertyName("function_parentId", 0);
	}

	@Override
	public List<PHCSMP_Function> findChildFunctions(Integer parentId) {
		return findListByPropertyName("function_parentId", parentId);
	}

	@Override
	public PHCSMP_Function findByFuncId(Integer function_id) {
		// TODO Auto-generated method stub
		return findByPropertyName("function_id", function_id);
	}

}
