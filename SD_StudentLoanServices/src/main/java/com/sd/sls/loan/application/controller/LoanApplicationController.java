package com.sd.sls.loan.application.controller;

import java.util.HashMap;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sd.sls.loan.application.bs.ILoanApplicationBS;
import com.sd.sls.loan.application.constants.LoanApplicationConstants;
import com.sd.sls.loan.application.status.context.ApplicationStatusContext;
import com.sd.sls.loan.application.status.state.WithdrawState;

@RequestMapping("/loanApplication")
@RestController
public class LoanApplicationController 
{
	@Autowired
	private ILoanApplicationBS loanApplicationBS;
	
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
	public ResponseEntity<String> getApplicationId (@RequestParam String email)
	{
		String applicationId = Objects.toString(loanApplicationBS.getApplicationId(email));
		return ResponseEntity.ok(applicationId == LoanApplicationConstants.NULL ? LoanApplicationConstants.NO_LOAN_APPLICATION_FOUND : LoanApplicationConstants.LOAN_APPLICATION_FOUND + applicationId);
	}
	
	@PutMapping("/updateApplication/{applicationId}")
	public ResponseEntity<String> updateApplication (@PathVariable(LoanApplicationConstants.APPLICATION_ID) Long applicationId, @RequestBody Map<String, Object> userValues)
	{
		userValues.put("applicationId", applicationId);
		return ResponseEntity.ok(loanApplicationBS.updateApplication(userValues));
	}
	
	@PutMapping("/withdrawApplication/{applicationId}")
	public ResponseEntity<String> withdrawApplication(@PathVariable(LoanApplicationConstants.APPLICATION_ID) Long applicationId)
	{
		Map<String, Object> userValues = new HashMap<String, Object>();
		userValues.put(LoanApplicationConstants.APPLICATION_ID, applicationId);
		userValues.put(LoanApplicationConstants.ACTION, LoanApplicationConstants.WITHDRAW);
		return ResponseEntity.ok(loanApplicationBS.withdrawApplication(userValues));
	}
}
