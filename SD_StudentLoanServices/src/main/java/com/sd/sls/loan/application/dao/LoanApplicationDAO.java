package com.sd.sls.loan.application.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sd.sls.constants.ISQLStatements;
import com.sd.sls.loan.application.model.LoanApplication;
import com.sd.sls.user.model.User;

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

	@Override
	public LoanApplication findApplicationID(String first_name, String last_name, String email) {
		// TODO Auto-generated method stub
		List<LoanApplication> loanApplicationList = jdbcTemplate.query(ISQLStatements.GET_APPLICATION_ID,
				new BeanPropertyRowMapper<>(LoanApplication.class), new Object[] {email});
		return loanApplicationList.size() > 0 ? loanApplicationList.get(0) : null;
	}

}
