package com.sd.sls.loan.application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sd.sls.constants.ISQLStatements;
import com.sd.sls.loan.application.model.LoanApplication;

@Repository
public class LoanApplicationDAO implements ILoanApplicationDAO{
	
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
		return jdbcTemplate.queryForObject(ISQLStatements.GET_LOAN_APPLICATION, new BeanPropertyRowMapper<>(LoanApplication.class), new Object[] {applicantName, loanAmount});
	}
}
