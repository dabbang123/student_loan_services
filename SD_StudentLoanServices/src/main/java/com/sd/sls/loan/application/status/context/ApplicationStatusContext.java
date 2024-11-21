package com.sd.sls.loan.application.status.context;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sd.sls.loan.application.factory.ApplicationStatusFactory;
import com.sd.sls.loan.application.model.LoanApplication;
import com.sd.sls.loan.application.status.state.IApplicationStatusState;

/*
 * @Author: Abhishek Vishwakarma
 */
@Component
public class ApplicationStatusContext 
{
	@Autowired
	private IApplicationStatusState applicationStatusState;
	
	@Autowired
	private ApplicationStatusFactory applicationStatusFactory;
	
	public void setState(Map<String, Object> userValues)
	{
		//Using Factory Design Pattern here
		this.applicationStatusState = applicationStatusFactory.getApplicationStatusFactory(userValues);
	}
	
	public int updateLoanApplicationStatus(Long loanApplicationId)
	{
		return applicationStatusState.updateStatus(loanApplicationId);
	}
}
