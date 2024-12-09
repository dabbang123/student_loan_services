package com.sd.sls.loanapplication.dao;

import java.util.List;
import java.util.Map;

import com.sd.sls.loanapplication.model.LoanApplication;

public interface ILoanApplicationDAO {
	public int submitApplication (LoanApplication loanApplication);
	
	public boolean checkIfLoanExistWithApplicant(LoanApplication loanApplication);
	
	public LoanApplication getLoanApplication(int applicantName, Long loanAmount);
	
	public LoanApplication getApplicationId (String name);
	
	public int updateApplication (LoanApplication application);
	
	public int withdrawApplication (Long applicationId);

	public List<LoanApplication> getApprovedApplications();
	
	public int assignApplication(Map<String, Object> userValues);
	
// Get all the Loan Applications - RB	
	public List<LoanApplication> getAllLoanApplications();
			
// Get Loan Application By Id - RB
	public LoanApplication getApplicationById (int applicationId);		
	
// Approve Application - RB
	public int approveApplication(Long applicationId);
		
// Reject Application - RB
	public int rejectApplication(Long applicationId);
	
// Change the Application Status to Under Review
	public int underReviewApplication(Long applicationId);
}
