package com.sd.sls.bankrepresentative.dao;

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
import com.sd.sls.loanapplication.status.ApplicationStatus;
import com.sd.sls.bankrepresentative.constant.BankRepresentativeConstants;
import com.sd.sls.bankrepresentative.model.BankRepresentative;

@Repository
public class BankRepresentativeDAO implements IBankRepresentativeDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<BankRepresentative> getAllBankRepresentatives()
	{
		List<BankRepresentative> bankRepresentatives = jdbcTemplate.query(ISQLStatements.GET_ALL_BANK_REPRESENTATIVE, new BeanPropertyRowMapper<>(BankRepresentative.class));
		return bankRepresentatives.size() > 0 ? bankRepresentatives : null;
	}
	
}
