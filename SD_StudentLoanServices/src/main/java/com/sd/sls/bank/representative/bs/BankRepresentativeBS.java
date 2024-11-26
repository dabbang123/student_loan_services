package com.sd.sls.bank.representative.bs;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.sls.bank.representative.bl.IBankRepresentativeBL;
import com.sd.sls.bank.representative.model.BankRepresentative;

/*
 * Author: Rushabh Botadra
 */

@Service
public class BankRepresentativeBS implements IBankRepresentativeBS{

	@Autowired
	private IBankRepresentativeBL bankRepresentativeBL;
	
	@Override
	public Map<String, Boolean> assignApplication(Map<String, Object> userValues) 
	{
		return bankRepresentativeBL.assignApplication(userValues);
	}
	
	@Override
	public List<BankRepresentative> getAllBankRepresentatives()
	{
		return bankRepresentativeBL.getAllBankRepresentatives();
	}
	
	@Override
	public Map<String, Boolean> reviewGuarantor(int applicationId)
	{
		return bankRepresentativeBL.reviewGuarantor(applicationId);
	}
}
