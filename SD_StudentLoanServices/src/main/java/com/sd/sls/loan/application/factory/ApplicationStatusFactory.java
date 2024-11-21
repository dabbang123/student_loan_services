package com.sd.sls.loan.application.factory;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.sls.loan.application.status.state.IApplicationStatusState;
import com.sd.sls.loan.application.status.state.WithdrawState;

@Service
public class ApplicationStatusFactory {
	
	@Autowired
	private WithdrawState withdrawState;
	
	public IApplicationStatusState getApplicationStatusFactory (Map<String, Object> userValues)
	{
		if (userValues.get("action").equals("withdraw"))
		{
			return withdrawState;
		}
		return null;
	}
}
