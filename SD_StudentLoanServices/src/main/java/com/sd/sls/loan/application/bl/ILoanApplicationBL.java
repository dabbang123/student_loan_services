package com.sd.sls.loan.application.bl;

import com.sd.sls.loan.application.model.LoanApplication;

public interface ILoanApplicationBL {

	public LoanApplication findApplicationID(String first_name, String last_name, String email);
	
}
