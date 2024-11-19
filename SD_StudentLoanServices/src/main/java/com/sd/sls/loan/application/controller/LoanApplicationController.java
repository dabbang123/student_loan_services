package com.sd.sls.loan.application.controller;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sd.sls.loan.application.bs.ILoanApplicationBS;
import com.sd.sls.loan.application.model.LoanApplication;
import com.sd.sls.user.constants.UserConstants;
import com.sd.sls.user.model.User;

@RequestMapping("/loanApplication")
@RestController
public class LoanApplicationController 
{
	@Autowired
	private ILoanApplicationBS loanApplicationBS;
	
	@GetMapping("/viewApplicationId")
	public ResponseEntity<Integer> viewApplicationID(@RequestBody Map<String, Object> userValues) {
		String first_name = Objects.toString(userValues.get("firstName"));
		String last_name = Objects.toString(userValues.get("lastName"));
		String email = Objects.toString(userValues.get("email"));
		LoanApplication loanApplication = loanApplicationBS.findApplicationID(first_name, last_name, email);
		if (loanApplication == null)
		{
			return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(loanApplication.getApplicationId());
	}
}
