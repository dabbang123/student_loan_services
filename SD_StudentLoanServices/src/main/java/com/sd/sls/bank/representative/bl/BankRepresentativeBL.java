package com.sd.sls.bank.representative.bl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.sls.bank.representative.constant.BankRepresentativeConstants;
import com.sd.sls.bank.representative.dao.IBankRepresentativeDAO;
import com.sd.sls.bank.representative.model.BankRepresentative;
import com.sd.sls.external.db.dao.IExternalDbDAO;
import com.sd.sls.guarantor.constants.GuarantorConstants;
import com.sd.sls.guarantor.dao.IGuarantorDAO;
import com.sd.sls.guarantor.model.Guarantor;
import com.sd.sls.loan.application.dao.ILoanApplicationDAO;
import com.sd.sls.loan.application.model.LoanApplication;

/*
 * Author: Rushabh Botadra
 */

@Service
public class BankRepresentativeBL implements IBankRepresentativeBL {
	
	@Autowired
	private ILoanApplicationDAO loanApplicationDAO;
	
	@Autowired
	private IBankRepresentativeDAO bankRepresentativeDAO;
	
	@Autowired
	private IGuarantorDAO guarantorDAO;
	
	@Autowired
	private IExternalDbDAO externalDbDAO;

	@Override
	public Map<String, Boolean> assignApplication(Map<String, Object> userValues)  
	{
		Map<String, Boolean>returnMap = new HashMap<>();
		if(bankRepresentativeDAO.assignApplication(userValues) == 1)
		{
			returnMap.put(BankRepresentativeConstants.APPLICATION_UPDATED_SUCCESSFULLY, true);
		}
		else
		{
			returnMap.put(BankRepresentativeConstants.UPDATION_FAILED, false);
		}
		return returnMap;
	}	
	
	@Override
	public List<BankRepresentative> getAllBankRepresentatives()
	{
		return bankRepresentativeDAO.getAllBankRepresentatives();
	}
	
	@Override
	public Map<String, Boolean> reviewGuarantor(int applicationId)
	{
		/*
		 * Steps:
		 * S1: Get the Application and Guarantor Details from the Application Id.
		 * S2: Review Guarantor.
		 * S3: If the Occupation of Guarantor is BUSINESS then, get the list of ITR for the last consecutive 3 years
		 * S4: If the Occupation of Guarantor is SALARIED then, check whether guarantor has filled ITR for the latest month. 
		 */
		
		Map<String, Boolean> returnMap = new HashMap<>();
		LoanApplication application = loanApplicationDAO.getApplicationById(applicationId);
		Guarantor guarantor = guarantorDAO.getGuarantorByAppId(applicationId); 
		if(guarantor.getOccupation().equals(GuarantorConstants.BUSINESS))
		{
			if(externalDbDAO.checkItrForBusiness(guarantor.getUinNo()))
			{
				returnMap.put(BankRepresentativeConstants.GUARANTOR_REVIEWED_SUCCESSFULLY, true);
			}
			else
			{
				returnMap.put(BankRepresentativeConstants.GUARANTOR_REVIEWED_FAILED, false);
			}
		}
		else
		{
			if(externalDbDAO.checkItrForSalaried(guarantor.getUinNo()))
			{
				returnMap.put(BankRepresentativeConstants.GUARANTOR_REVIEWED_SUCCESSFULLY, true);				
			}
			else
			{
				returnMap.put(BankRepresentativeConstants.GUARANTOR_REVIEWED_FAILED, false);
			}
		}
		return returnMap;
	}

}
