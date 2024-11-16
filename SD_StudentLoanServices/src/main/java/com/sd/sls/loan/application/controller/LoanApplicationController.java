package com.sd.sls.loan.application.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sd.sls.loan.application.bs.ILoanApplicationBS;

@RequestMapping("/loanApplication")
@RestController
public class LoanApplicationController 
{
	@Autowired
	private ILoanApplicationBS loanApplicationBS;
	
	@PostMapping("/submitApplication")
	public ResponseEntity<String> submitApplication(@RequestBody Map<String, Object> userValues)
	{
		return ResponseEntity.ok("Submitted");
	}
}
