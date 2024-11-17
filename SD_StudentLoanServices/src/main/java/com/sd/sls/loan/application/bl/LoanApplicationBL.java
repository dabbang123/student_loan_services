package com.sd.sls.loan.application.bl;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.sls.applicant.bl.IApplicantBL;
import com.sd.sls.guarantor.model.Guarantor;
import com.sd.sls.loan.application.constants.LoanApplicationConstants;
import com.sd.sls.loan.application.dao.ILoanApplicationDAO;
import com.sd.sls.loan.application.model.LoanApplication;
import com.sd.sls.loan.application.status.ApplicationStatus;

@Service
public class LoanApplicationBL implements ILoanApplicationBL 
{
	@Autowired
	private ILoanApplicationDAO loanApplicationDAO;
	
	@Autowired
	private IApplicantBL applicantBL;
	
	@Override
	public Map<String, Boolean> submitApplication (Map<String, Object> userValues)
	{
		Map<String, Boolean> returnMap = new HashMap<>();
		LoanApplication loanApplication = createLoanApplication(userValues);
		if (checkIfLoanExistWithApplicant(loanApplication))
		{
			returnMap.put(loanApplication.getApplicant().getFirstName() + LoanApplicationConstants.EXISTING_LOAN_EXISTS, true);
			return returnMap;
		}
		
		if (loanApplicationDAO.submitApplication(loanApplication) == 1)
		{
			loanApplication = loanApplicationDAO.getLoanApplication(loanApplication.getApplicant().getApplicantId(), loanApplication.getLoanAmount());
			returnMap.put(LoanApplicationConstants.LOAN_SUBMITTED_SUCCESSFULLY + loanApplication.getApplicationId(), true);
		}
		
		return returnMap;
	}
	
	@Override
	public Long getApplicationId (String name)
	{
		return Long.valueOf(loanApplicationDAO.getApplicationId(name).getApplicationId());
	}

	@Override
	public Map<String, Boolean> updateApplication(Map<String, Object> userValues) 
	{
		Map<String, Boolean> returnMap = new HashMap<String, Boolean>();
		
		return returnMap;
	}
	
	private LoanApplication createLoanApplication (Map<String, Object> userValues)
	{
		LoanApplication loanApplication = new LoanApplication();
		loanApplication.setApplicant(applicantBL.getApplicantDetailsByName(Objects.toString(userValues.get(LoanApplicationConstants.FIRST_NAME)), Objects.toString(userValues.get(LoanApplicationConstants.LAST_NAME))));
		loanApplication.setGuarantor(new Guarantor());
		loanApplication.getGuarantor().setName(Objects.toString(userValues.get(LoanApplicationConstants.GUARANTOR)));
		loanApplication.setApplicationDate(new Date(new java.util.Date().getTime()));
		loanApplication.setLoanAmount(Long.valueOf(Objects.toString(userValues.get(LoanApplicationConstants.LOAN_AMOUNT))));
		loanApplication.setPurpose(Objects.toString(userValues.get(LoanApplicationConstants.PURPOSE)));
		loanApplication.setStatus(Objects.toString(ApplicationStatus.DRAFT.getStatus()));
		loanApplication.setAssigneId(null);
		return loanApplication;
	}
	
	private boolean checkIfLoanExistWithApplicant(LoanApplication loanApplication)
	{
		return loanApplicationDAO.checkIfLoanExistWithApplicant(loanApplication);
	}
}
