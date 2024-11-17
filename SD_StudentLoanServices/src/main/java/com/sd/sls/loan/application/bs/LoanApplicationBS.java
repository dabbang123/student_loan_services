package com.sd.sls.loan.application.bs;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.sls.loan.application.bl.ILoanApplicationBL;

@Service
public class LoanApplicationBS implements ILoanApplicationBS 
{
	@Autowired
	private ILoanApplicationBL loanApplicationBL;
	
	@Override
	public Map<String, Boolean> submitApplication(Map<String, Object> userValues) 
	{
		return loanApplicationBL.submitApplication(userValues);
	}

	@Override
	public Long getApplicationId (String name)
	{
		return loanApplicationBL.getApplicationId(name);
	}
	
	@Override
	public Map<String, Boolean> updateApplication(Map<String, Object> userValues)
	{
		return loanApplicationBL.updateApplication(userValues);
	}
}
