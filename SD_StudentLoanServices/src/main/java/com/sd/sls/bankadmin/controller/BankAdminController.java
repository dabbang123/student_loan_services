package com.sd.sls.bankadmin.controller;

/*
 * @Author: Nikunj Panchal
 */

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.sd.sls.bankadmin.bs.IBankAdminService;
import com.sd.sls.bankadmin.constants.BankAdminConstants;
import com.sd.sls.bankadmin.model.BankAdmin;
import com.sd.sls.loan.application.bs.LoanApplicationBS;
import com.sd.sls.loan.application.model.LoanApplication;
import com.sd.sls.loanoffer.bs.ILoanOfferBS;
import com.sd.sls.user.service.IUserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sd.sls.applicant.bs.IApplicantBS;
import com.sd.sls.applicant.constants.ApplicantConstants;
import com.sd.sls.notification.bs.INotificationBS;
import com.sd.sls.notification.model.Notification;

@RequestMapping("/bankadmin")
@RestController
public class BankAdminController {
	
	@Autowired
	private IApplicantBS applicantBS;
	
	@Autowired
	private INotificationBS notificationBS;

	@Autowired
	private IUserBusinessService userBusinessService;

	@Autowired
	IBankAdminService bankAdminService;

	@Autowired
	private ILoanOfferBS loanOfferBS;

	@Autowired
	private LoanApplicationBS loanApplicationBS;
	
	@GetMapping("/checkNotifications")
	public ResponseEntity<List<Notification>> checkNotifications()
	{
		return ResponseEntity.ok(notificationBS.checkNotifications());
	}
	
	@PostMapping("/approveRegisteration/{userId}")
	public ResponseEntity<String> approveRegisterationsForApplicant(@PathVariable("userId") Long userId)
	{
		Map<String, Boolean> resultMap = applicantBS.registerApplicant(userId);
		if (resultMap.containsKey(ApplicantConstants.APPLICANT_REGISTERED)) 
		{
			return resultMap.get(ApplicantConstants.APPLICANT_REGISTERED) == true
					? new ResponseEntity<>("Applicant Registration Approved", HttpStatus.OK)
					: new ResponseEntity<>(ApplicantConstants.APPLICANT_REGISTERED_FAILED, HttpStatus.NOT_FOUND);
		}
		else 
		{
			return new ResponseEntity<>(ApplicantConstants.BAD_REQUEST, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody Map<String, Object> userValues) {
		String userName = Objects.toString(userValues.get(BankAdminConstants.EMAIL));
		String password = Objects.toString(userValues.get(BankAdminConstants.PASSWORD));
		return userBusinessService.loginUser(userName, password)
				? new ResponseEntity<>(BankAdminConstants.BANK_ADMIN_LOGGED_IN_SUCCESSFULLY, HttpStatus.OK)
				: new ResponseEntity<>(BankAdminConstants.INVALID_CREDENTIALS, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getApplications")
	public ResponseEntity<List<LoanApplication>> getApprovedApplications() {
		List<LoanApplication> applications = loanApplicationBS.getApprovedApplications();
		if (applications.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else {
			return ResponseEntity.ok(applications);
		}
	}

	@PostMapping("/generateOffer/{applicationId}")
	public ResponseEntity<String> generateOffer(@PathVariable("applicationId") int applicationId,  @RequestBody Map<String, Object> userValues) {
		userValues.put("applicationId", applicationId);
		Map<String, Boolean> resultMap = loanOfferBS.generateOffer(userValues);

		String key = "";
		for (Map.Entry<String, Boolean> entry : resultMap.entrySet()) {
			key = entry.getKey();
		}
		return ResponseEntity.ok(key);
	}

	@GetMapping("/getBankAdmins")
	public ResponseEntity<List<BankAdmin>> getBankAdmins() {
		List<BankAdmin> bankAdminList = bankAdminService.getBankAdmins();
		if (bankAdminList.isEmpty()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else {
			return ResponseEntity.ok(bankAdminList);
		}
	}

	@PutMapping("/disburseLoan/{offerID}")
	public ResponseEntity<String> disburseLoanOffer (@PathVariable("offerID") Long offerID, @RequestBody Map<String, Object> userValues) {
		userValues.put("offerID", offerID);
		boolean resultMap = bankAdminService.disburseLoanOffer(userValues.size());

		if (resultMap) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
