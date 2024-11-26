package com.sd.sls.bank.representative.controller;
/*
 * Author: Rushabh Botadra
 */

import java.util.List;
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

import com.sd.sls.bank.representative.bs.IBankRepresentativeBS;
import com.sd.sls.bank.representative.constant.BankRepresentativeConstants;
import com.sd.sls.bank.representative.model.BankRepresentative;
import com.sd.sls.loan.application.bs.ILoanApplicationBS;
import com.sd.sls.loan.application.model.LoanApplication;
import com.sd.sls.user.service.IUserBusinessService;


@RequestMapping("/bankRepresentative")
@RestController
public class BankRepresentativeController {
	
	@Autowired
	private IUserBusinessService userBusinessService;
	
	@Autowired
	private ILoanApplicationBS loanApplicationBS;
	
	@Autowired
	private IBankRepresentativeBS bankRepresentativeBS;
	
	@PostMapping("/login")
	public ResponseEntity<String> loginRepresentative(@RequestBody Map<String, Object> userValues){
		String email = Objects.toString(userValues.get(BankRepresentativeConstants.EMAIL));
		String password = Objects.toString(userValues.get(BankRepresentativeConstants.PASSWORD));
		return userBusinessService.loginUser(email, password) == true
				? new ResponseEntity<>(BankRepresentativeConstants.LOGGED_IN_SUCCESSFULLY, HttpStatus.OK)
				: new ResponseEntity<>(BankRepresentativeConstants.INVALID_CREDENTIALS, HttpStatus.BAD_REQUEST);
	}
	
// Get the Applications which are in the Draft State and Under Review State	
	@GetMapping("/getApplications")
	public ResponseEntity<List<LoanApplication>> getApplications(){
		List<LoanApplication> loanApplications = loanApplicationBS.getAllLoanApplications();
		if (loanApplications == null) 
		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else 
		{
			return ResponseEntity.ok(loanApplications);
		}		
	}
	
	@GetMapping("getBankRepresentatives")
	public ResponseEntity<List<BankRepresentative>> getAllBankRepresentatives()
	{
		List<BankRepresentative> bankRepresentatives = bankRepresentativeBS.getAllBankRepresentatives();
		if(bankRepresentatives == null)
		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else
		{
			return ResponseEntity.ok(bankRepresentatives);
		}
	}
	
	@PutMapping("assignApplication/{applicationId}")
	public ResponseEntity<String> assignApplication(@PathVariable("applicationId") int applicationId, @RequestBody Map<String,Object> userValues)
	{
		userValues.put(BankRepresentativeConstants.APPLICATION_ID, applicationId);
		Map<String, Boolean> resultMap = bankRepresentativeBS.assignApplication(userValues);
		if(resultMap.containsKey(BankRepresentativeConstants.APPLICATION_UPDATED_SUCCESSFULLY))
		{
			return ResponseEntity.ok(BankRepresentativeConstants.APPLICATION_UPDATED_SUCCESSFULLY);
		}
		else
		{
			return new ResponseEntity<>(BankRepresentativeConstants.UPDATION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/reviewGuarantor/{applicationId}")
	public ResponseEntity<String> reviewGuarantor(@PathVariable("applicationId") int applicationId)
	{
		Map<String, Boolean> resultMap = bankRepresentativeBS.reviewGuarantor(applicationId);
		if(resultMap.containsKey(BankRepresentativeConstants.GUARANTOR_REVIEWED_SUCCESSFULLY))
		{
			return ResponseEntity.ok(BankRepresentativeConstants.GUARANTOR_REVIEWED_SUCCESSFULLY);
		}
		else
		{
			return new ResponseEntity<>(BankRepresentativeConstants.GUARANTOR_REVIEWED_FAILED, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}
