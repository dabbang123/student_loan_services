package com.sd.sls.loan.application.dao;

import com.sd.sls.loan.application.model.LoanApplication;

public interface ILoanApplicationDAO {
	public int submitApplication (LoanApplication loanApplication);
	
	public boolean checkIfLoanExistWithApplicant(LoanApplication loanApplication);
	
	public LoanApplication getLoanApplication(int applicantName, Long loanAmount);

	public LoanApplication findApplicationID(String first_name, String last_name, String email);
}
