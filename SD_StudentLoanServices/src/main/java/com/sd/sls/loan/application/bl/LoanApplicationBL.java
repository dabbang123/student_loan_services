package com.sd.sls.loan.application.bl;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.sls.applicant.bl.IApplicantBL;
import com.sd.sls.guarantor.model.Guarantor;
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
			returnMap.put(loanApplication.getApplicant().getFirstName() + " has already applied for a loan, Please complete that loan", true);
			return returnMap;
		}
		
		if (loanApplicationDAO.submitApplication(loanApplication) == 1)
		{
			loanApplication = loanApplicationDAO.getLoanApplication(loanApplication.getApplicant().getApplicantId(), loanApplication.getLoanAmount());
			returnMap.put("Loan Application Submitted Successfully.\nYour Loan Application Id is: " + loanApplication.getApplicationId(), true);
		}
		
		return returnMap;
	}
	
	private LoanApplication createLoanApplication (Map<String, Object> userValues)
	{
		LoanApplication loanApplication = new LoanApplication();
		loanApplication.setApplicant(applicantBL.getApplicantDetailsByName(Objects.toString(userValues.get("firstName")), Objects.toString(userValues.get("lastName"))));
		loanApplication.setGuarantor(new Guarantor());
		loanApplication.getGuarantor().setName(Objects.toString(userValues.get("guarantor")));
		loanApplication.setApplicationDate(new Date(new java.util.Date().getTime()));
		loanApplication.setLoanAmount(Long.valueOf(Objects.toString(userValues.get("loanAmount"))));
		loanApplication.setPurpose(Objects.toString(userValues.get("purpose")));
		loanApplication.setStatus(Objects.toString(ApplicationStatus.DRAFT.getStatus()));
		loanApplication.setAssigneId(null);
		return loanApplication;
	}
	
	private boolean checkIfLoanExistWithApplicant(LoanApplication loanApplication)
	{
		return loanApplicationDAO.checkIfLoanExistWithApplicant(loanApplication);
	}
}
