package com.sd.sls.loan.application.factory;

/*
 * @Author: Abhishek Vishwakarma
 */


import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sd.sls.loan.application.status.state.ApprovedState;
import com.sd.sls.loan.application.status.state.DisbursedState;
import com.sd.sls.loan.application.status.state.DraftState;
import com.sd.sls.loan.application.status.state.IApplicationStatusState;
import com.sd.sls.loan.application.status.state.RejectedState;
import com.sd.sls.loan.application.status.state.SanctionedState;
import com.sd.sls.loan.application.status.state.SubmittedState;
import com.sd.sls.loan.application.status.state.UnderReviewState;
import com.sd.sls.loan.application.status.state.WithdrawState;

@Component
public class ApplicationStatusFactory {
	
	@Autowired
	private ApprovedState approvedState;
	
	@Autowired
	private DisbursedState disbursedState;
	
	@Autowired
	private DraftState draftState;
	
	@Autowired
	private RejectedState rejectedState;
	
	@Autowired
	private SanctionedState sanctionedState;
	
	@Autowired
	private SubmittedState submittedState;
	
	@Autowired
	private UnderReviewState underReviewState;
	
	@Autowired
	private WithdrawState withdrawState;
	
	public IApplicationStatusState getApplicationStatusFactory (Map<String, Object> userValues)
	{
		switch (Objects.toString(userValues.get("action"))) {
			case "approved": 
				return approvedState;
				
			case "disbursed": 
				return disbursedState;
				
			case "draft": 
				return draftState;
				
			case "reject": 
				return rejectedState;
				
			case "sanctioned": 
				return sanctionedState;
				
			case "submitted": 
				return submittedState;
				
			case "underReview": 
				return underReviewState;
				
			case "withdraw": 
				return withdrawState;
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + userValues.get("action"));
		}
		
	}
}
