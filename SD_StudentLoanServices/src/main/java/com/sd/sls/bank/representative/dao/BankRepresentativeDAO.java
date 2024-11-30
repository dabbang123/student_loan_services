package com.sd.sls.bank.representative.dao;

/*
 * Author: Rushabh Botadra
 */

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sd.sls.constants.ISQLStatements;
import com.sd.sls.loan.application.status.ApplicationStatus;
import com.sd.sls.bank.representative.constant.BankRepresentativeConstants;
import com.sd.sls.bank.representative.model.BankRepresentative;

@Repository
public class BankRepresentativeDAO implements IBankRepresentativeDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<BankRepresentative> getAllBankRepresentatives()
	{
		return jdbcTemplate.query(ISQLStatements.GET_ALL_BANK_REPRESENTATIVE, new BeanPropertyRowMapper<>(BankRepresentative.class));
	}
	
// Update the Application with the Assignee Id - RB  	
	@Override
	public int assignApplication(Map<String, Object> userValues)
	{
		return jdbcTemplate.update(ISQLStatements.ASSIGN_APPLICATION, new Object[] {Objects.toString(userValues.get(BankRepresentativeConstants.ASSIGNEE_ID)), Objects.toString(userValues.get(BankRepresentativeConstants.APPLICATION_ID))});		
	}
}
