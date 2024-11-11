package com.sd.sls.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sd.sls.user.constants.UserConstants;
import com.sd.sls.user.service.IUserBusinessService;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private IUserBusinessService userBusinessService;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Map<String, Object> userValues) {
		if (userBusinessService.registerUser(userValues).get(UserConstants.USER_REGISTERED) == true) {
			return new ResponseEntity<>("User is registered", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("User registeration failed", HttpStatus.NOT_FOUND);
		}
	}
}
