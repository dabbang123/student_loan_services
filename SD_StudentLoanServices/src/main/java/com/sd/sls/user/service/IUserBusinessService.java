package com.sd.sls.user.service;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.Map;

public interface IUserBusinessService {
	public Map<String, Boolean> registerUser (Map<String, Object> userValues);

	public boolean loginUser(String email, String password);
}
