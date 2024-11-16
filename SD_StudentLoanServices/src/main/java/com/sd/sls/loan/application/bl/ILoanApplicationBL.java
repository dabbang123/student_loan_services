package com.sd.sls.loan.application.bl;

import java.util.Map;

public interface ILoanApplicationBL {
	public Map<String, Boolean> submitApplication (Map<String, Object> userValues);
}
