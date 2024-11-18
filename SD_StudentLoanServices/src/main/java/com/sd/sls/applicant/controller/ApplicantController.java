package com.sd.sls.applicant.controller;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sd.sls.applicant.bs.IApplicantBS;
import com.sd.sls.applicant.constants.ApplicantConstants;
import com.sd.sls.loan.application.bs.ILoanApplicationBS;
import com.sd.sls.user.service.IUserBusinessService;

@RequestMapping("/applicant")
@RestController
public class ApplicantController {
	
	@Autowired
	private IApplicantBS applicantBS;
	
	@Autowired
	private IUserBusinessService userBusinessService;
	
	@Autowired
	private ILoanApplicationBS loanApplicationBS;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerApplicant(@RequestBody Map<String, Object> userValues) 
	{
		Map<String, Boolean> resultMap = applicantBS.registerApplicant(userValues);
		if (resultMap.containsKey(ApplicantConstants.APPLICANT_REGISTERED)) 
		{
			return resultMap.get(ApplicantConstants.APPLICANT_REGISTERED) == true
					? new ResponseEntity<>(ApplicantConstants.APPLICANT_REGISTERED_SUCCESSFULLY, HttpStatus.OK)
					: new ResponseEntity<>(ApplicantConstants.APPLICANT_REGISTERED_FAILED, HttpStatus.NOT_FOUND);
		}
		else if (resultMap.containsKey(ApplicantConstants.APPLICANT_ALREADY_REGISTERED)) 
		{
			return new ResponseEntity<>(ApplicantConstants.APPLICANT_ALREADY_REGISTERED, HttpStatus.OK);
		} 
		else if (resultMap.containsKey(ApplicantConstants.NO_USER_FOUND))
		{
			return new ResponseEntity<>(ApplicantConstants.NO_USER_FOUND, HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<>(ApplicantConstants.BAD_REQUEST, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody Map<String, Object> userValues) {
		String userName = Objects.toString(userValues.get(ApplicantConstants.EMAIL));
		String password = Objects.toString(userValues.get(ApplicantConstants.PASSWORD));
		return userBusinessService.loginUser(userName, password) == true
				? new ResponseEntity<>(ApplicantConstants.APPLICANT_LOGGED_IN_SUCCESSFULLY, HttpStatus.OK)
				: new ResponseEntity<>(ApplicantConstants.INVALID_CREDENTIALS, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/submitApplication")
	public ResponseEntity<String> submitApplication(@RequestBody Map<String, Object> userValues)
	{
		Map<String, Boolean> resultMap = loanApplicationBS.submitApplication(userValues);
		String key = "";
		for (Map.Entry<String, Boolean> entry : resultMap.entrySet()) {
            key = entry.getKey();
        }
		return ResponseEntity.ok(key);
	}
	
	@GetMapping("/getApplicationId")
	public ResponseEntity<Long> getApplicationId (String name)
	{
		return ResponseEntity.ok(loanApplicationBS.getApplicationId(name));
	}
	
	@PutMapping("/updateApplication/{applicationId}")
	public ResponseEntity<String> updateApplication (@PathVariable("applicationId") Long applicationId, @RequestBody Map<String, Object> userValues)
	{
		userValues.put("applicationId", applicationId);
		return ResponseEntity.ok(loanApplicationBS.updateApplication(userValues));
	}
}
