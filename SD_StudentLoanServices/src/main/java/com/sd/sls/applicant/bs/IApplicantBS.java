package com.sd.sls.applicant.bs;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.Map;

public interface IApplicantBS {
	public Map<String, Boolean> registerApplicantDraft(Map<String, Object> userValues);
	
	public Map<String, Boolean> registerApplicant(Long userId);
}
