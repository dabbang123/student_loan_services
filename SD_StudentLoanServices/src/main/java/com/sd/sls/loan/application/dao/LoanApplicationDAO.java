package com.sd.sls.loan.application.dao;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sd.sls.constants.ISQLStatements;
import com.sd.sls.loan.application.model.LoanApplication;
import com.sd.sls.loan.application.status.ApplicationStatus;

@Repository
public class LoanApplicationDAO implements ILoanApplicationDAO
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int submitApplication (LoanApplication loanApplication)
	{
		return jdbcTemplate.update(ISQLStatements.SUBMIT_LOAN_APPLICATION, new Object[] {loanApplication.getApplicant().getApplicantId(), loanApplication.getGuarantor().getName(), loanApplication.getApplicationDate(), loanApplication.getStatus(), loanApplication.getLoanAmount(), loanApplication.getPurpose(), loanApplication.getAssigneId()});
	}
	
	@Override
	public boolean checkIfLoanExistWithApplicant(LoanApplication loanApplication)
	{
		return jdbcTemplate.queryForList(ISQLStatements.CHECK_LOAN_APPLICATION, loanApplication.getApplicant().getApplicantId()).size() > 0 ? true : false;
	}
	
	@Override
	public LoanApplication getLoanApplication(int applicantName, Long loanAmount)
	{
		List<LoanApplication> loanApplicationList = jdbcTemplate.query(ISQLStatements.GET_LOAN_APPLICATION_FOR_DRAFT, new BeanPropertyRowMapper<>(LoanApplication.class), new Object[] {applicantName, loanAmount});
		return loanApplicationList.size() > 0 ? loanApplicationList.get(0) : null;
	}
	
	@Override
	public LoanApplication getApplicationId (String email)
	{
		List<LoanApplication> loanApplicationList = jdbcTemplate.query(ISQLStatements.GET_LOAN_APPLICATION_FOR_APPLICANT, new BeanPropertyRowMapper<>(LoanApplication.class), email);
		return loanApplicationList.size() > 0 ? loanApplicationList.get(0) : null;
	}
	
	@Override
	public int updateApplication (LoanApplication application)
	{
		return jdbcTemplate.update(buildUpdateQuery(application), application.getApplicationId());
	}
	
	@Override
	public int withdrawApplication (Long applicationId)
	{
		return jdbcTemplate.update(ISQLStatements.UPDATE_LOAN_APPLICATION_STATUS, new Object[] {ApplicationStatus.WITHDRAWN.getStatus(), applicationId});
	}
	
	private String buildUpdateQuery(LoanApplication application) 
	{
		boolean first = true;
		StringBuffer updateQuery = new StringBuffer("UPDATE LOAN_APPLICATION SET ");
		
		if (application.getGuarantor().getName() != null) {
			updateQuery.append(" GUARANTOR_NAME = '").append(application.getGuarantor().getName()).append("'");
			first = false;
		}
		
		if (application.getPurpose() != null) {
			if (!first) {
				updateQuery.append(", ");
			}
			updateQuery.append(" PURPOSE = '").append(application.getPurpose()).append("'");
			first = false;
		}

		if (application.getLoanAmount() != null) {
			if (!first) {
				updateQuery.append(", ");
			}
			updateQuery.append(" LOAN_AMOUNT = '").append(application.getLoanAmount()).append("'");
			first = false;
		}
		
		updateQuery.append(" WHERE APPLICATION_ID = ?");
		
		return updateQuery.toString();
	}

	public LoanApplication getApplicationById (int getApplicationId)
	{
		List<LoanApplication> loanApplicationList = jdbcTemplate.query(ISQLStatements.GET_LOAN_APPLICATION_BY_ID, new BeanPropertyRowMapper<>(LoanApplication.class), getApplicationId);
		return loanApplicationList.size() > 0 ? loanApplicationList.get(0) : null;
	}

	@Override
	public List<LoanApplication> getApprovedApplications() {
		List<LoanApplication> loanApplicationList = jdbcTemplate.query(ISQLStatements.GET_ALL_APPROVED_APPLICATIONS, new BeanPropertyRowMapper<>(LoanApplication.class));
		return !loanApplicationList.isEmpty() ? loanApplicationList : null;
	}
}
