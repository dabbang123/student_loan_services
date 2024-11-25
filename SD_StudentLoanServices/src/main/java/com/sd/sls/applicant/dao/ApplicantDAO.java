package com.sd.sls.applicant.dao;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.List;

/*
 * @Author: Abhishek Vishwakarma
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sd.sls.applicant.model.Applicant;
import com.sd.sls.constants.ISQLStatements;

@Repository
public class ApplicantDAO implements IApplicantDAO
{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int registerApplicant(Applicant applicant)
	{
		return jdbcTemplate.update(ISQLStatements.REGISTER_APPLICANT, new Object[] {applicant.getUser().getUserId(), applicant.getFirstName(), applicant.getLastName(), applicant.getDateOfBirth(), applicant.getAddress(), applicant.getEducationDetails(), applicant.getMembershipType(), applicant.getEmail()});
	}
	
	@Override
	public int registerApplicantDraft(Applicant applicant)
	{
		return jdbcTemplate.update(ISQLStatements.REGISTER_APPLICANT_DRAFT, new Object[] {applicant.getUser().getUserId(), applicant.getFirstName(), applicant.getLastName(), applicant.getDateOfBirth(), applicant.getAddress(), applicant.getEducationDetails(), applicant.getMembershipType(), applicant.getEmail()});
	}

	@Override
	public boolean checkIfApplicantAlreadyExists(Applicant applicant)
	{
		return jdbcTemplate.queryForList(ISQLStatements.CHECK_APPLICANT, new Object[] {applicant.getFirstName(), applicant.getLastName(), applicant.getEmail()}).size() > 0 ? true : false;
	}
	
	@Override
	public Applicant getApplicantDetailsByName (String firstName, String lastName)
	{
		List<Applicant> applicantList = jdbcTemplate.query(ISQLStatements.FIND_APPLICANT_BY_NAME, new BeanPropertyRowMapper<>(Applicant.class), new Object[] {firstName, lastName});
		return applicantList.size() > 0 ? applicantList.get(0) : null;
	}
	
	@Override
	public Applicant getApplicantDetailsByNameFromDraft (String firstName, String lastName)
	{
		List<Applicant> applicantList = jdbcTemplate.query(ISQLStatements.FIND_APPLICANT_DRAFT_BY_NAME, new BeanPropertyRowMapper<>(Applicant.class), new Object[] {firstName, lastName});
		return applicantList.size() > 0 ? applicantList.get(0) : null;
	}

	@Override
	public Applicant getApplicantDetailsByUserIdFromDraft(Long userId) {
		List<Applicant> applicantList = jdbcTemplate.query(ISQLStatements.FIND_APPLICANT_DRAFT_BY_ID, new BeanPropertyRowMapper<>(Applicant.class), new Object[] {userId});
		return applicantList.size() > 0 ? applicantList.get(0) : null;
	}
	
	@Override
	public int deleteApplicantFromDraft(int userId)
	{
		return jdbcTemplate.update(ISQLStatements.DELETE_APPLICANT_FROM_DRAFT, new Object[] {userId});
	}
}
