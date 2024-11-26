package com.sd.sls.bank.representative.dao;

/*
 * Author: Rushabh Botadra
 */

import java.util.Map;
import java.util.List;

import com.sd.sls.bank.representative.model.BankRepresentative;

public interface IBankRepresentativeDAO {
	
	public List<BankRepresentative> getAllBankRepresentatives();
	
	public int assignApplication(Map<String, Object> userValues);
	
}
