package com.sd.sls.applicant.bs;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.sls.applicant.bl.IApplicantBL;

public class ApplicantBS implements IApplicantBS {
	
	@Autowired
	private IApplicantBL applicantBL;

	@Override
	public Map<String, Boolean> registerApplicant(Map<String, Object> userValues) {
		return applicantBL.registerApplicant(userValues);
	}

}
