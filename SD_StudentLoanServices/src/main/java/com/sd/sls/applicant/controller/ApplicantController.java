package com.sd.sls.applicant.controller;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sd.sls.user.controller.UserController;

@RequestMapping("/applicant")
@RestController
public class ApplicantController extends UserController {
	
	@Override
	public ResponseEntity<String> registerUser(Map<String, Object> userValues) {
		
		return super.registerUser(userValues);
	}

}
