package com.sd.sls.bankadmin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
