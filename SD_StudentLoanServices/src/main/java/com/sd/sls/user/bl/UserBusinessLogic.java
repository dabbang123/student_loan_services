package com.sd.sls.user.bl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sd.sls.user.constants.UserConstants;
import com.sd.sls.user.dao.IUserDAO;
import com.sd.sls.user.model.User;

@Service
public class UserBusinessLogic implements IUserBusinessLogic{

	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public Map<String, Boolean> registerUser (Map<String, Object> userValues)
	{
		Map<String, Boolean> returnMap = new HashMap<>();
		User user = createUser(userValues);
		if (checkIfUserAlreadyExists(user)) 
		{
			returnMap.put(UserConstants.USER_ALREADY_REGISTERED, true);
			return returnMap;
		}
		
		if (userDAO.registerUser(user) == 1)
		{
			returnMap.put(UserConstants.USER_REGISTERED, true);
		}
		return returnMap;
	}
	
	public boolean loginUser(String email, String password) 
	{
		User user = userDAO.findByUserName(email);
		return user != null && passwordEncoder.matches(password, user.getPassword());
	}
	
	private User createUser(Map<String, Object> userValues)
	{
		User user = new User();
		user.setUserName(Objects.toString(userValues.get("userName")));
		user.setEmail(Objects.toString(userValues.get("email")));
		user.setPassword(passwordEncoder.encode(Objects.toString(userValues.get("password"))));
		user.setPhoneNumber(Long.valueOf(Objects.toString(userValues.get("phoneNumber"))));
		return user;
	}
	
	private boolean checkIfUserAlreadyExists(User user) {
		return userDAO.checkIfUserAlreadyExists(user);
	}
}
