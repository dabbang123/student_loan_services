package com.sd.sls.bankrepresentative.bl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.sls.bankrepresentative.constant.BankRepresentativeConstants;
import com.sd.sls.bankrepresentative.dao.IBankRepresentativeDAO;
import com.sd.sls.bankrepresentative.model.BankRepresentative;
import com.sd.sls.command.dp.GuarantorReviewCommand;
import com.sd.sls.command.factory.dp.GuarantorReviewCommandFactory;
import com.sd.sls.external.db.dao.IExternalDbDAO;
import com.sd.sls.guarantor.dao.IGuarantorDAO;
import com.sd.sls.guarantor.model.Guarantor;
import com.sd.sls.loanapplication.constants.LoanApplicationConstants;
import com.sd.sls.loanapplication.status.context.ApplicationStatusContext;

/*
 * Author: Rushabh Botadra
 */

@Service
public class BankRepresentativeBL implements IBankRepresentativeBL 
{
	@Autowired
	private IBankRepresentativeDAO bankRepresentativeDAO;
	
	@Autowired
	private IGuarantorDAO guarantorDAO;
	
	@Autowired
	private IExternalDbDAO externalDbDAO;
	
	@Autowired
	private ApplicationStatusContext applicationStatusContext;
	
	@Autowired
	private GuarantorReviewCommandFactory reviewCommandFactory;

	@Override
	public Map<String, Boolean> assignApplication(Map<String, Object> userValues)  
	{
		Map<String, Boolean>returnMap = new HashMap<>();
		if(bankRepresentativeDAO.assignApplication(userValues) == 1)
		{
			//Used State Pattern to Update the State to Under Review
			applicationStatusContext.setState(userValues);
			if(applicationStatusContext.updateLoanApplicationStatus(Integer.parseInt(Objects.toString(userValues.get(LoanApplicationConstants.APPLICATION_ID)))) == 1 );
			{
				returnMap.put(BankRepresentativeConstants.APPLICATION_UPDATED_SUCCESSFULLY, true);
			}
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
		//Changed by Abhishek
		Guarantor guarantor = guarantorDAO.getGuarantorByAppId(applicationId); 
		//Using Command Design Pattern
	    GuarantorReviewCommand command = reviewCommandFactory.getCommand(guarantor.getOccupation());
		return command.execute(guarantor);
	}
}
