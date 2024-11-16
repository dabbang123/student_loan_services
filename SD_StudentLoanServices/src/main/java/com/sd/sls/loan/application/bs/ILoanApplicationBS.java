package com.sd.sls.loan.application.bs;

import java.util.Map;

public interface ILoanApplicationBS {
	public Map<String, Boolean> submitApplication (Map<String, Object> userValues);
}
