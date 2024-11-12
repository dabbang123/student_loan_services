package com.sd.sls.constants;

public class ISQLStatements {
	public static final String REGISTER_USER = "INSERT INTO \"USER\"(USER_NAME, EMAIL, PASSWORD, PHONE_NUMBER) VALUES (?, ?, ?, ?)";
	
	public static final String CHECK_USER = "SELECT USER_ID AS \"userId\", USER_NAME AS \"userName\", EMAIL AS \"email\", PASSWORD AS \"password\", PHONE_NUMBER AS \"phoneNumber\" FROM \"USER\" u WHERE u.USER_NAME = ? and u.EMAIL = ? and u.PHONE_NUMBER = ?";
}
