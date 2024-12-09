package com.sd.sls.bankrepresentative.bl;

/*
 * Author: Rushabh Botadra
 */

import java.util.Map;

import com.sd.sls.bankrepresentative.model.BankRepresentative;

import java.util.List;

public interface IBankRepresentativeBL {
	
	public List<BankRepresentative> getAllBankRepresentatives();
	
	public Map<String, Boolean> reviewGuarantor(int applicationId);
}
