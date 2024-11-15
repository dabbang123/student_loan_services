package com.sd.sls.constants;

/*
 * @Author: Abhishek Vishwakarma
 */

public class ISQLStatements {
	public final static String REGISTER_USER = "INSERT INTO \"USER\"(USER_NAME, EMAIL, PASSWORD, PHONE_NUMBER) VALUES (?, ?, ?, ?)";

	public static final String CHECK_USER = "SELECT USER_ID AS \"userId\", USER_NAME AS \"userName\", EMAIL AS \"email\", PASSWORD AS \"password\", PHONE_NUMBER AS \"phoneNumber\" FROM \"USER\" u WHERE u.USER_NAME = ? and u.EMAIL = ? and u.PHONE_NUMBER = ?";
	
	public static final String FIND_USER_BY_EMAIL = "SELECT USER_ID AS \"userId\", USER_NAME AS \"userName\", EMAIL AS \"email\", PASSWORD AS \"password\", PHONE_NUMBER AS \"phoneNumber\" FROM \"USER\" u WHERE u.EMAIL = ?";
	
	public static final String UPDATE_USER_PROFILE = "UPDATE \"USER\" SET ";
	
	public final static String REGISTER_APPLICANT = "INSERT INTO APPLICANT(USER_ID, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, ADDRESS, EDUCATION_DETAILS, MEMBERSHIP_TYPE, EMAIL) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static final String CHECK_APPLICANT = "SELECT APPLICANT_ID AS \"applicantId\", USER_ID AS \"userId\", FIRST_NAME AS \"firstName\", LAST_NAME AS \"lastName\", DATE_OF_BIRTH AS \"dateOfBirth\", ADDRESS AS \"address\", EDUCATION_DETAILS AS \"educationDetails\", MEMBERSHIP_TYPE AS \"membershipType\", EMAIL AS \"email\" FROM APPLICANT WHERE FIRST_NAME = ? AND LAST_NAME = ? AND EMAIL = ?";
}
