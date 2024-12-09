package com.sd.sls.bankrepresentative.dao;

/*
 * Author: Rushabh Botadra
 */

import java.util.Map;
import java.util.List;

import com.sd.sls.bankrepresentative.model.BankRepresentative;

public interface IBankRepresentativeDAO {
	
	public List<BankRepresentative> getAllBankRepresentatives();
	
	public int assignApplication(Map<String, Object> userValues);
	
}
