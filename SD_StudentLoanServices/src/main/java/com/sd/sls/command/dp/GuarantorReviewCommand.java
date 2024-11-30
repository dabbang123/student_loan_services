package com.sd.sls.command.dp;
/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.Map;

import com.sd.sls.guarantor.model.Guarantor;


public interface GuarantorReviewCommand {
	public Map<String, Boolean> execute(Guarantor guarantor);
}
