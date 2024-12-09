package com.sd.sls.bankrepresentative.bl;

/*
 * Author: Rushabh Botadra
 */

import java.util.Map;
import java.util.List;

import com.sd.sls.bankrepresentative.model.BankRepresentative;

public interface IBankRepresentativeBL {
	
	public Map<String, Boolean> assignApplication(Map<String, Object> userValues); 
	
	public List<BankRepresentative> getAllBankRepresentatives();
	
	public Map<String, Boolean> reviewGuarantor(int applicationId);
}
