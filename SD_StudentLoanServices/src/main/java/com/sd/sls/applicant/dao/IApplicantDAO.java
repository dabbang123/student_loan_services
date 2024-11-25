package com.sd.sls.applicant.dao;

/*
 * @Author: Abhishek Vishwakarma
 */

import com.sd.sls.applicant.model.Applicant;

public interface IApplicantDAO {
	public int registerApplicant(Applicant applicant);
	
	public int registerApplicantDraft(Applicant applicant);
	
	public boolean checkIfApplicantAlreadyExists(Applicant applicant);
	
	public Applicant getApplicantDetailsByName (String firstName, String lastName);
	
	public Applicant getApplicantDetailsByNameFromDraft (String firstName, String lastName);
	
	public Applicant getApplicantDetailsByUserIdFromDraft (Long userId);
	
	public int deleteApplicantFromDraft(int userId);
}
