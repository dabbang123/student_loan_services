package com.sd.sls.loan.application.bl;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.sls.applicant.bl.IApplicantBL;
import com.sd.sls.applicant.model.Applicant;
import com.sd.sls.guarantor.model.Guarantor;
import com.sd.sls.interceptor.dp.Context;
import com.sd.sls.interceptor.dp.InterceptorDispatcher;
import com.sd.sls.interceptor.dp.LoggingInterceptor;
import com.sd.sls.loan.application.constants.LoanApplicationConstants;
import com.sd.sls.loan.application.dao.ILoanApplicationDAO;
import com.sd.sls.loan.application.model.LoanApplication;
import com.sd.sls.loan.application.status.ApplicationStatus;
import com.sd.sls.loan.application.status.context.ApplicationStatusContext;

@Service
public class LoanApplicationBL implements ILoanApplicationBL 
{
	@Autowired
	private ILoanApplicationDAO loanApplicationDAO;
	
	@Autowired
	private IApplicantBL applicantBL;
	
	@Autowired
	private ApplicationStatusContext applicationStatusContext;
	
	@Autowired
	private InterceptorDispatcher dispatcher;
	
	@Autowired
	private LoggingInterceptor interceptor;
	
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
			Applicant applicant = loanApplication.getApplicant();
			loanApplication = loanApplicationDAO.getLoanApplication(loanApplication.getApplicant().getApplicantId(), loanApplication.getLoanAmount());
			loanApplication.setApplicant(applicant);
			returnMap.put(LoanApplicationConstants.LOAN_SUBMITTED_SUCCESSFULLY + loanApplication.getApplicationId(), true);
			
			//Intercepter Design Pattern
			dispatcher.attach(interceptor);
			Context context = new Context();
			context.put("applicationDetails", loanApplication);
			dispatcher.dispatchEvent(context);
		}
		
		return returnMap;
	}
	
	@Override
	public Long getApplicationId (String email)
	{
		LoanApplication application = loanApplicationDAO.getApplicationId(email);
		return application == null ? null : Long.valueOf(application.getApplicationId());
	}

	@Override
	public String updateApplication(Map<String, Object> userValues) 
	{
		LoanApplication application = createLoanApplication(userValues);
		application.setApplicationId(Integer.valueOf(Objects.toString(userValues.get("applicationId"))));
		if (loanApplicationDAO.updateApplication(application) == 1)
		{
			return LoanApplicationConstants.APPLICATION_UPDATED_SUCCESSFULLY;
		}
		
		return LoanApplicationConstants.APPLICATION_UPDATION_FAILED;
	}
	
	@Override
	public String withdrawApplication (Map<String, Object> userValues)
	{
		//Using State Design Pattern to change the state and set the status of application to Withdraw State
		applicationStatusContext.setState(userValues);
		return applicationStatusContext.updateLoanApplicationStatus(Long.valueOf(Objects.toString(userValues.get(LoanApplicationConstants.APPLICATION_ID)))) == 1 ? LoanApplicationConstants.LOAN_APPLICATION_WITHDRAWN : LoanApplicationConstants.LOAN_APPLICATION_WITHDRAW_FAILED;
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

	@Override
	public List<LoanApplication> getApprovedApplications () {
		return loanApplicationDAO.getApprovedApplications();
	}
}
