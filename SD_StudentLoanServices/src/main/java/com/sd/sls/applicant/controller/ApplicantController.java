package com.sd.sls.applicant.controller;

import java.util.HashMap;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sd.sls.applicant.bs.IApplicantBS;
import com.sd.sls.applicant.constants.ApplicantConstants;
import com.sd.sls.interceptor.dp.Context;
import com.sd.sls.interceptor.dp.InterceptorFramework;
import com.sd.sls.interceptor.dp.LoggingInterceptor;
import com.sd.sls.loan.application.bs.ILoanApplicationBS;
import com.sd.sls.loan.application.constants.LoanApplicationConstants;
import com.sd.sls.loan.application.model.LoanApplication;
import com.sd.sls.notification.bs.AdminNotificationService;
import com.sd.sls.observer.dp.Subject;
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
	
	@Autowired
	private Subject subject;
	
	@Autowired
	private LoggingInterceptor interceptor;
	
	@Autowired
	private InterceptorFramework interceptorFramework;

	@Autowired
	public ApplicantController(Subject subject, AdminNotificationService adminNotificationService) {
		this.subject = subject;
		this.subject.addObserver(adminNotificationService);
	}
	
	//Added by Ranatosh Sarkar
	@PostMapping("/register")
	public ResponseEntity<String> registerApplicant(@RequestBody Map<String, Object> userValues) 
	{
		Map<String, Boolean> resultMap = applicantBS.registerApplicantDraft(userValues);
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
	
	//Added by Ranatosh Sarkar
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
		Map<String, Object> resultMap = loanApplicationBS.submitApplication(userValues);
		String key = "";
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) 
		{
            key = entry.getKey();
        }
		
		if (key.equals(LoanApplicationConstants.LOAN_SUBMITTED_SUCCESSFULLY))
		{
			LoanApplication application = (LoanApplication) resultMap.get(LoanApplicationConstants.LOAN_SUBMITTED_SUCCESSFULLY);
			
			//Interceptor Design Pattern
			interceptorFramework.registerInterceptor(interceptor);
			Context context = new Context();
			context.put("applicationDetails", application);
			interceptorFramework.execute(context);
			return ResponseEntity.ok(key + application.getApplicationId());
		}
		return ResponseEntity.ok(key);
	}
	
	@GetMapping("/getApplicationId")
	public ResponseEntity<String> getApplicationId (@RequestParam String email)
	{
		String applicationId = Objects.toString(loanApplicationBS.getApplicationId(email));
		return ResponseEntity.ok(applicationId == "null" ? LoanApplicationConstants.NO_LOAN_APPLICATION_FOUND : LoanApplicationConstants.LOAN_APPLICATION_FOUND + applicationId);
	}
	
	@PutMapping("/updateApplication/{applicationId}")
	public ResponseEntity<String> updateApplication (@PathVariable(ApplicantConstants.APPLICATION_ID) Long applicationId, @RequestBody Map<String, Object> userValues)
	{
		userValues.put("applicationId", applicationId);
		return ResponseEntity.ok(loanApplicationBS.updateApplication(userValues));
	}
	
	@PutMapping("/withdrawApplication/{applicationId}")
	public ResponseEntity<String> withdrawApplication(@PathVariable(ApplicantConstants.APPLICATION_ID) Long applicationId)
	{
		Map<String, Object> userValues = new HashMap<String, Object>();
		userValues.put(ApplicantConstants.APPLICATION_ID, applicationId);
		userValues.put(LoanApplicationConstants.ACTION, LoanApplicationConstants.WITHDRAW);
		return ResponseEntity.ok(loanApplicationBS.withdrawApplication(userValues));
	}
}
