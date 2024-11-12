package com.sd.sls.user.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sd.sls.constants.ISQLStatements;
import com.sd.sls.user.model.User;

@Repository
public class UserDAO implements IUserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int registerUser (User user)
	{
		return jdbcTemplate.update(ISQLStatements.REGISTER_USER, new Object[] {user.getUserName(), user.getEmail(), user.getPassword(), user.getPhoneNumber()});
	}
	
	@Override
	public boolean checkIfUserAlreadyExists (User user)
	{
		return jdbcTemplate.query(ISQLStatements.CHECK_USER, new BeanPropertyRowMapper<>(User.class), new Object[] {user.getUserName(), user.getEmail(), user.getPhoneNumber()}).size() > 0 ? true : false;
	}
}
