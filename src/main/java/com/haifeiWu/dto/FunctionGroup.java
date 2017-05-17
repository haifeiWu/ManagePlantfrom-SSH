package com.haifeiWu.dto;

import java.io.Serializable;
import java.util.List;

import com.haifeiWu.entity.PHCSMP_Function;

public class FunctionGroup implements Serializable {
	private PHCSMP_Function titleFunction;
	private List<PHCSMP_Function> childFunctions;

	public PHCSMP_Function getTitleFunction() {
		return titleFunction;
	}

	public void setTitleFunction(PHCSMP_Function titleFunction) {
		this.titleFunction = titleFunction;
	}

	public List<PHCSMP_Function> getChildFunctions() {
		return childFunctions;
	}

	public void setChildFunctions(List<PHCSMP_Function> childFunctions) {
		this.childFunctions = childFunctions;
	}

	@Override
	public String toString() {
		return "FunctionGroup [titleFunction=" + titleFunction
				+ ", childFunctions=" + childFunctions + "]";
	}

}
