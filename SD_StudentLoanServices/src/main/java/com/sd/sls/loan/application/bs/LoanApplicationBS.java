package com.sd.sls.loan.application.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.sls.loan.application.bl.ILoanApplicationBL;
import com.sd.sls.loan.application.model.LoanApplication;

@Service
public class LoanApplicationBS implements ILoanApplicationBS 
{
	@Autowired
	private ILoanApplicationBL loanApplicationBL;


	@Override
	public LoanApplication findApplicationID(String first_name, String last_name, String email) {
		// TODO Auto-generated method stub
		return loanApplicationBL.findApplicationID(first_name, last_name, email);
	}

}
