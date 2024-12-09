package com.sd.sls.loan.application.status.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sd.sls.loan.application.dao.ILoanApplicationDAO;

/*
 * @Author: Abhishek Vishwakarma
 */
@Component
public class WithdrawState implements IApplicationStatusState 
{
	@Autowired
	private ILoanApplicationDAO loanApplicationBS;

	@Override
	public int updateStatus(Long loanApplicationId) {
		return loanApplicationBS.withdrawApplication(loanApplicationId);
	}
}
