package com.sd.sls.applicant.controller;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sd.sls.applicant.bs.IApplicantBS;
import com.sd.sls.applicant.constants.ApplicantConstants;

@RequestMapping("/applicant")
@RestController
public class ApplicantController {
	
	@Autowired
	private IApplicantBS applicantBS;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerApplicant(@RequestBody Map<String, Object> userValues) {
		Map<String, Boolean> resultMap = applicantBS.registerApplicant(userValues);
		if (resultMap.containsKey(ApplicantConstants.APPLICANT_REGISTERED)) {
			return resultMap.get(ApplicantConstants.APPLICANT_REGISTERED) == true
					? new ResponseEntity<>("Applicant is registered", HttpStatus.OK)
					: new ResponseEntity<>("Applicant registeration failed", HttpStatus.NOT_FOUND);
		} else if (resultMap.containsKey(ApplicantConstants.APPLICANT_ALREADY_REGISTERED)) {
			return new ResponseEntity<>("Applicant is already registered", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}

}
