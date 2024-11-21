package com.sd.sls.loan.application.status.state;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.sls.loan.application.dao.ILoanApplicationDAO;

/*
 * @Author: Abhishek Vishwakarma
 */
public class UnderReviewState implements IApplicationStatusState{


	@Override
	public int updateStatus(Long loanApplicationId) {
		return 0;
	}

}
