package com.sd.sls.user.controller;

import java.util.Map;
import java.util.Objects;

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
		Map<String, Boolean> resultMap = userBusinessService.registerUser(userValues);
		if (resultMap.containsKey(UserConstants.USER_REGISTERED)) {
			return resultMap.get(UserConstants.USER_REGISTERED) == true
					? new ResponseEntity<>("User is registered", HttpStatus.OK)
					: new ResponseEntity<>("User registeration failed", HttpStatus.NOT_FOUND);
		} else if (resultMap.containsKey(UserConstants.USER_ALREADY_REGISTERED)) {
			return new ResponseEntity<>("User is already registered", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody Map<String, Object> userValues) {
		String userName = Objects.toString(userValues.get("email"));
		String password = Objects.toString(userValues.get("password"));
		return userBusinessService.loginUser(userName, password) == true
				? new ResponseEntity<>("User Logged In", HttpStatus.OK)
				: new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
	}
}
