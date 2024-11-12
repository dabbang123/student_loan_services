package com.sd.sls.user.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.sls.user.bl.IUserBusinessLogic;

@Service
public class UserBusinessService implements IUserBusinessService {

	@Autowired
	private IUserBusinessLogic userBusinessLogic;
	
	public Map<String, Boolean> registerUser (Map<String, Object> userValues)
	{
		return userBusinessLogic.registerUser(userValues);
	}
	
	@Override
	public boolean loginUser(String email, String password)
	{
		return userBusinessLogic.loginUser(email, password);
	}
}
