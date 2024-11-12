package com.sd.sls.user.dao;

import com.sd.sls.user.model.User;

public interface IUserDAO {
	public int registerUser (User user);
	
	public boolean checkIfUserAlreadyExists(User user);
}
