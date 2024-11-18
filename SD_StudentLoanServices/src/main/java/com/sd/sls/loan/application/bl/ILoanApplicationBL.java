package com.sd.sls.loan.application.bl;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.Map;

public interface ILoanApplicationBL {
	public Map<String, Boolean> submitApplication (Map<String, Object> userValues);
	
	public Long getApplicationId (String name);
	
	public String updateApplication(Map<String, Object> userValues);
	
	public String withdrawApplication (Long applicationId);
}
