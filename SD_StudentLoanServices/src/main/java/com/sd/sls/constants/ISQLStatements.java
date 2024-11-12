package com.sd.sls.constants;

public class ISQLStatements {
	public final static String REGISTER_USER = "INSERT INTO \"USER\"(USER_NAME, EMAIL, PASSWORD, PHONE_NUMBER) VALUES (?, ?, ?, ?)";

	public static final String CHECK_USER = "SELECT USER_ID AS \"userId\", USER_NAME AS \"userName\", EMAIL AS \"email\", PASSWORD AS \"password\", PHONE_NUMBER AS \"phoneNumber\" FROM \"USER\" u WHERE u.USER_NAME = ? and u.EMAIL = ? and u.PHONE_NUMBER = ?";
	
	public static final String FIND_USER_BY_USERNAME = "SELECT USER_ID AS \"userId\", USER_NAME AS \"userName\", EMAIL AS \"email\", PASSWORD AS \"password\", PHONE_NUMBER AS \"phoneNumber\" FROM \"USER\" u WHERE u.EMAIL = ?";
}
