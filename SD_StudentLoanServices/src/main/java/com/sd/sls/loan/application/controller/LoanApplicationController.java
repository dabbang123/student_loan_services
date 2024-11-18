package com.sd.sls.loan.application.controller;

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
	public ResponseEntity<String> getApplicationId (@RequestParam String name)
	{
		String applicationId = Objects.toString(loanApplicationBS.getApplicationId(name));
		return ResponseEntity.ok(applicationId == "null" ? LoanApplicationConstants.NO_LOAN_APPLICATION_FOUND : LoanApplicationConstants.LOAN_APPLICATION_FOUND + applicationId);
	}
	
	@PutMapping("/updateApplication/{applicationId}")
	public ResponseEntity<String> updateApplication (@PathVariable("applicationId") Long applicationId, @RequestBody Map<String, Object> userValues)
	{
		userValues.put("applicationId", applicationId);
		return ResponseEntity.ok(loanApplicationBS.updateApplication(userValues));
	}
	
	@PutMapping("/withdrawApplication/{applicationId}")
	public ResponseEntity<String> withDrawApplication(@PathVariable("applicationId") Long applicationId)
	{
		return ResponseEntity.ok(loanApplicationBS.withdrawApplication(applicationId));
	}
}
