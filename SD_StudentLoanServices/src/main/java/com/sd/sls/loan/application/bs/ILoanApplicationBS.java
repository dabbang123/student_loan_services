package com.sd.sls.loan.application.bs;

import com.sd.sls.loan.application.model.LoanApplication;

public interface ILoanApplicationBS {

	public LoanApplication findApplicationID(String first_name, String last_name, String email);

	
}
