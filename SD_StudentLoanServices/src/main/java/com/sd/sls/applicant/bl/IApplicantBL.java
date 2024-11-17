package com.sd.sls.applicant.bl;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.Map;

import com.sd.sls.applicant.model.Applicant;

public interface IApplicantBL 
{
	public Map<String, Boolean> registerApplicant(Map<String, Object> userValues);
	
	public Applicant getApplicantDetailsByName (String firstName, String lastName);
}
