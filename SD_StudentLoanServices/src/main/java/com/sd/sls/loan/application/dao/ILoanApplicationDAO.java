package com.sd.sls.loan.application.dao;

/*
 * @Author: Abhishek Vishwakarma
 */

import com.sd.sls.loan.application.model.LoanApplication;

import java.util.List;

public interface ILoanApplicationDAO {
	public int submitApplication (LoanApplication loanApplication);
	
	public boolean checkIfLoanExistWithApplicant(LoanApplication loanApplication);
	
	public LoanApplication getLoanApplication(int applicantName, Long loanAmount);
	
	public LoanApplication getApplicationId (String name);
	
	public int updateApplication (LoanApplication application);
	
	public int withdrawApplication (Long applicationId);
	
	public LoanApplication getApplicationById (int getApplicationId);

	public List<LoanApplication> getApprovedApplications();
}
