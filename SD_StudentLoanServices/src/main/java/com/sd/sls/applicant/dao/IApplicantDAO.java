package com.sd.sls.applicant.dao;

/*
 * @Author: Abhishek Vishwakarma
 */

import com.sd.sls.applicant.model.Applicant;

public interface IApplicantDAO {
	public int registerApplicant(Applicant applicant);
	
	public boolean checkIfApplicantAlreadyExists(Applicant applicant);
	
	public Applicant getApplicantDetailsByName (String firstName, String lastName);
}
