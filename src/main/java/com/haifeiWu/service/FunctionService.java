package com.haifeiWu.service;

import java.util.List;

import com.haifeiWu.entity.PHCSMP_Function;

public interface FunctionService {
	List<PHCSMP_Function> findAllFunctions();

	List<PHCSMP_Function> findParentFunctions();

	List<PHCSMP_Function> findChildFunctions(Integer parentId);

	PHCSMP_Function findByFuncId(Integer function_id);
}
