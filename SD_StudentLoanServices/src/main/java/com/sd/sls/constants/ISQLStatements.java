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
	
	public final static String REGISTER_APPLICANT_DRAFT = "INSERT INTO APPLICANT_DRAFT(USER_ID, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, ADDRESS, EDUCATION_DETAILS, MEMBERSHIP_TYPE, EMAIL) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static final String CHECK_APPLICANT = "SELECT APPLICANT_ID AS \"applicantId\", USER_ID AS \"userId\", FIRST_NAME AS \"firstName\", LAST_NAME AS \"lastName\", DATE_OF_BIRTH AS \"dateOfBirth\", ADDRESS AS \"address\", EDUCATION_DETAILS AS \"educationDetails\", MEMBERSHIP_TYPE AS \"membershipType\", EMAIL AS \"email\" FROM APPLICANT WHERE FIRST_NAME = ? AND LAST_NAME = ? AND EMAIL = ?";
	
	public static final String FIND_APPLICANT_BY_NAME = "SELECT APPLICANT_ID AS \"applicantId\", USER_ID AS \"userId\", FIRST_NAME AS \"firstName\", LAST_NAME AS \"lastName\", DATE_OF_BIRTH AS \"dateOfBirth\", ADDRESS AS \"address\", EDUCATION_DETAILS AS \"educationDetails\", MEMBERSHIP_TYPE AS \"membershipType\", EMAIL AS \"email\" FROM APPLICANT WHERE FIRST_NAME = ? AND LAST_NAME = ?";
	
	public static final String FIND_APPLICANT_DRAFT_BY_NAME = "SELECT APPLICANT_ID AS \"applicantId\", USER_ID AS \"userId\", FIRST_NAME AS \"firstName\", LAST_NAME AS \"lastName\", DATE_OF_BIRTH AS \"dateOfBirth\", ADDRESS AS \"address\", EDUCATION_DETAILS AS \"educationDetails\", MEMBERSHIP_TYPE AS \"membershipType\", EMAIL AS \"email\" FROM APPLICANT_DRAFT WHERE FIRST_NAME = ? AND LAST_NAME = ?";
	
	public static final String FIND_APPLICANT_DRAFT_BY_ID = "SELECT APPLICANT_ID AS \"applicantId\", USER_ID AS \"userId\", FIRST_NAME AS \"firstName\", LAST_NAME AS \"lastName\", DATE_OF_BIRTH AS \"dateOfBirth\", ADDRESS AS \"address\", EDUCATION_DETAILS AS \"educationDetails\", MEMBERSHIP_TYPE AS \"membershipType\", EMAIL AS \"email\" FROM APPLICANT_DRAFT WHERE USER_ID = (SELECT USER_ID FROM \"USER\" WHERE USER_ID = ?)";
	
	public static final String SUBMIT_LOAN_APPLICATION = "INSERT INTO LOAN_APPLICATION (APPLICANT_ID, GUARANTOR_NAME, APPLICATION_DATE, APPLICATION_STATUS, LOAN_AMOUNT, PURPOSE, ASSIGNEE_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	public static final String CHECK_LOAN_APPLICATION = "SELECT * FROM LOAN_APPLICATION WHERE APPLICANT_ID = ? AND APPLICATION_STATUS NOT IN ('D', 'S', 'WD')";
	
	public static final String GET_LOAN_APPLICATION_FOR_DRAFT = "SELECT APPLICATION_ID AS \"applicationId\", APPLICANT_ID AS \"applicantId\", GUARANTOR_NAME AS \"guarantorName\", APPLICATION_DATE AS \"applicationDate\", APPLICATION_STATUS AS \"status\", LOAN_AMOUNT AS \"loanAmount\", PURPOSE AS \"purpose\", ASSIGNEE_ID AS \"assigneeId\" FROM LOAN_APPLICATION WHERE APPLICANT_ID = ? AND LOAN_AMOUNT = ? AND APPLICATION_STATUS = 'DR'";
	
	public static final String GET_LOAN_APPLICATION_FOR_APPLICANT = "SELECT APPLICATION_ID AS \"applicationId\", APPLICANT_ID AS \"applicantId\", GUARANTOR_NAME AS \"guarantorName\", APPLICATION_DATE AS \"applicationDate\", APPLICATION_STATUS AS \"status\", LOAN_AMOUNT AS \"loanAmount\", PURPOSE AS \"purpose\", ASSIGNEE_ID AS \"assigneeId\" FROM LOAN_APPLICATION WHERE APPLICANT_ID = (SELECT APPLICANT_ID FROM APPLICANT WHERE EMAIL = ?)";
	
	public static final String UPDATE_LOAN_APPLICATION_STATUS = "UPDATE LOAN_APPLICATION SET APPLICATION_STATUS = ? WHERE APPLICATION_ID = ?";
	
	public static final String SEND_NOTIFICATION = "INSERT INTO NOTIFICATION(USER_ID, MESSAGE, TIMESTAMP, NOTIFICATION_STATUS, SENDER_ID, SENDER_TYPE) VALUES(?, ?, ?, ?, ?, ?)";
	
	public static final String READ_NOTIFICATION = "SELECT NOTIFICATION_ID AS \"notificationId\", USER_ID AS \"userId\", MESSAGE AS \"message\", TIMESTAMP AS \"timeStamp\", NOTIFICATION_STATUS AS \"notificationStatus\", SENDER_ID AS \"senderId\", SENDER_TYPE AS \"senderType\" FROM NOTIFICATION WHERE NOTIFICATION_STATUS = 'S' ORDER BY TIMESTAMP DESC";
	
	public static final String GET_USER_FOR_NOTIFICATION = "SELECT * FROM \"USER\" WHERE USER_ID = (SELECT USER_ID FROM NOTIFICATION WHERE MESSAGE = ?)";
	
	public static final String DELETE_APPLICANT_FROM_DRAFT = "DELETE FROM APPLICANT_DRAFT WHERE APPLICANT_ID = ?";
}
