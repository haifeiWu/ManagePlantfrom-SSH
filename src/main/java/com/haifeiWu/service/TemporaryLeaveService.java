package com.haifeiWu.service;

import com.haifeiWu.entity.Temporary_Leave;

public interface TemporaryLeaveService {
	Temporary_Leave IsTemporaryLeaveReturn(String suspectId);

	void saveTemporaryLeaveInfo(Temporary_Leave obj);

	// void updateTemporaryLeaveInfo(Temporary_Leave obj);

	void updateReturnTime(String return_Time, String suspect_ID);
}
