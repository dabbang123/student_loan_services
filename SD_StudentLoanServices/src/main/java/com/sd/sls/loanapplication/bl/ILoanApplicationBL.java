package com.sd.sls.loanapplication.bl;

/*
 * @Author: Abhishek Vishwakarma
 */

import com.sd.sls.loanapplication.model.LoanApplication;

import java.util.List;
import java.util.Map;

public interface ILoanApplicationBL {
	public Map<String, Object> submitApplication (Map<String, Object> userValues);
	
	public Long getApplicationId (String name);
	
	public String updateApplication(Map<String, Object> userValues);
	
	public String withdrawApplication (Map<String, Object> userValues);
	
	public List<LoanApplication> getApprovedApplications ();
	
//Added the method in the Interface to access it from the Bank RepresentativeBL Class in order to update the application - RB 	
	public LoanApplication createLoanApplication (Map<String, Object> userValues);
		
// Get All the Applications - RB		
	public List<LoanApplication> getAllLoanApplications();	
	
// Approve Loan Application - RB
	public String approveApplication (Map<String, Object> userValues);
		
// Reject Loan Application - RB
	public String rejectApplication (Map<String, Object> userValues);
}
