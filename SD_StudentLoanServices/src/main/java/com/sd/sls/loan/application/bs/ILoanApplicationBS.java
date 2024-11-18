package com.sd.sls.loan.application.bs;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.Map;

public interface ILoanApplicationBS {
	public Map<String, Boolean> submitApplication (Map<String, Object> userValues);
	
	public Long getApplicationId (String name);
	
	public String updateApplication(Map<String, Object> userValues);
	
	public String withdrawApplication (Long applicationId);
}
