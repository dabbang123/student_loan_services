package com.sd.sls.user.bl;

import java.util.Map;

public interface IUserBusinessLogic {
	public Map<String, Boolean> registerUser (Map<String, Object> userValues);
}
