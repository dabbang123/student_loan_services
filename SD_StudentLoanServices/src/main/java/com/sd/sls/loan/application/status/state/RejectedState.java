package com.sd.sls.loan.application.status.state;
/*
 * @Author: Abhishek Vishwakarma
 */
public class RejectedState implements IApplicationStatusState {

	@Override
	public int updateStatus(Long loanApplicationId) {
		return 0;
	}

}
