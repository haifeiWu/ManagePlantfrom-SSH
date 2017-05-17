package com.haifeiWu.service;

import java.util.List;

import com.haifeiWu.entity.Temporary_Leave;

public interface TemporaryLeaveService {
	Temporary_Leave IsTemporaryLeaveReturn(String suspectId);

	void saveTemporaryLeaveInfo(Temporary_Leave obj);

	// void updateTemporaryLeaveInfo(Temporary_Leave obj);

	void updateReturnTime(String return_Time, String suspect_ID);

	void updateManager(int manager,String suspect_ID);
	
	public List<Temporary_Leave> findTempLeaveListBySuspectID(String suspectId);

	Temporary_Leave IsTemporaryReturn(String suspectId);

}
