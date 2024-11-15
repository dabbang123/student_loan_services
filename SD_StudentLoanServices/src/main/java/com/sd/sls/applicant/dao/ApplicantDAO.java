package com.sd.sls.applicant.dao;

/*
 * @Author: Abhishek Vishwakarma
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sd.sls.applicant.model.Applicant;
import com.sd.sls.constants.ISQLStatements;

@Repository
public class ApplicantDAO implements IApplicantDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int registerApplicant(Applicant applicant)
	{
		return jdbcTemplate.update(ISQLStatements.REGISTER_APPLICANT, new Object[] {applicant.getUser().getUserId(), applicant.getFirstName(), applicant.getLastName(), applicant.getDateOfBirth(), applicant.getAddress(), applicant.getEducationDetails(), applicant.getMembershipType(), applicant.getEmail()});
	}

	public boolean checkIfApplicantAlreadyExists(Applicant applicant)
	{
		return jdbcTemplate.queryForList(ISQLStatements.CHECK_APPLICANT, new Object[] {applicant.getFirstName(), applicant.getLastName(), applicant.getEmail()}).size() > 0 ? true : false;
	}
}