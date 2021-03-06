package com.preclaim.dao;

import com.preclaim.models.CaseMovement;

import java.util.List;

import com.preclaim.models.CaseHistory;

public interface Case_movementDao {

	String CreatecaseMovement(CaseMovement caseMovement);
	CaseMovement getCaseById(long caseId);
	String updateCaseMovement(CaseMovement caseMovement);
	String BulkupdateCaseMovement(CaseMovement caseMovement,String list);
	List<CaseHistory> getCaseMovementHistory(long caseId);
}
