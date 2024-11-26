package com.sd.sls.loan.application.bl;

/*
 * @Author: Abhishek Vishwakarma
 */

import com.sd.sls.loan.application.model.LoanApplication;

import java.util.List;
import java.util.Map;

public interface ILoanApplicationBL {
	public Map<String, Boolean> submitApplication (Map<String, Object> userValues);
	
	public Long getApplicationId (String name);
	
	public String updateApplication(Map<String, Object> userValues);
	
	public String withdrawApplication (Map<String, Object> userValues);
	
	public List<LoanApplication> getApprovedApplications ();
}
