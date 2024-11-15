package com.sd.sls.applicant.bl;

import java.util.Map;

public interface IApplicantBL {
	public Map<String, Boolean> registerApplicant(Map<String, Object> userValues);
}
