package com.sd.sls.loan.application.bs;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.Map;

public interface ILoanApplicationBS {
	public Map<String, Object> submitApplication (Map<String, Object> userValues);
	
	public Long getApplicationId (String email);
	
	public String updateApplication(Map<String, Object> userValues);
	
	public String withdrawApplication (Map<String, Object> userValues);
}
