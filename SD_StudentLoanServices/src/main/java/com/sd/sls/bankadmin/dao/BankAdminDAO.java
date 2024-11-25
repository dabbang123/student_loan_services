package com.sd.sls.bankadmin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BankAdminDAO implements IBankAdminDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

}
