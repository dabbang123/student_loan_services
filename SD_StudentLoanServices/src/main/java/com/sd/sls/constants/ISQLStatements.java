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
	
	public static final String GENERATE_LOAN_OFFER = "INSERT INTO LOAN_OFFER (APPLICATION_ID, SANCTIONED_AMOUNT, INTEREST_RATE, OFFER_STATUS, DISBURSED_DATE) VALUES (?, ?, ? ,?, ?)";

	public static final String GET_LOAN_OFFER = "SELECT OFFER_ID AS \"OfferID\", APPLICATION_ID, SANCTIONED_AMOUNT AS \"sanctionedAmount\", INTEREST_RATE AS \"interestRate\", OFFER_STATUS, DISBURSED_DATE \"disbursedDate\" FROM LOAN_OFFER WHERE OFFER_ID = ?";

	public static final String CHECK_LOAN_OFFER = "SELECT * FROM LOAN_OFFER WHERE OFFER_ID = ? AND OFFER_STATUS NOT IN ('DIS')";

	public static final String GET_ALL_APPROVED_APPLICATIONS = "SELECT LOAN_APPLICATION.APPLICATION_ID, APPLICANT.APPLICANT_ID,\\r\\n\" + \"LOAN_APPLICATION.APPLICATION_DATE,\\r\\n\" + \"LOAN_APPLICATION.APPLICATION_STATUS AS status,\" + \"LOAN_APPLICATION.LOAN_AMOUNT,\\r\\n\" + \"LOAN_APPLICATION.PURPOSE,\\r\\n\" + \"LOAN_APPLICATION.ASSIGNEE_ID\\r\\n\" + \"--APPLICANT\\r\\n\" + \"FROM LOAN_APPLICATION, APPLICANT WHERE LOAN_APPLICATION.APPLICANT_ID = applicant.applicant_id AND LOAN_APPLICATION.APPLICATION_STATUS IN ('A')";

	public static final String GET_ALL_BANK_ADMINS = "SELECT ADMIN_ID AS \"adminID\", USER_ID, AUTHORITY_LEVEL AS \"authorityLevel\" FROM BANK_ADMIN";

	public static final String GET_ALL_LOAN_OFFERS = "SELECT OFFER_ID AS \"OfferID\", APPLICATION_ID, SANCTIONED_AMOUNT AS \"sanctionedAmount\", INTEREST_RATE AS \"interestRate\", OFFER_STATUS AS \"offerStatus\", DISBURSED_DATE \"disbursedDate\" FROM LOAN_OFFER";
	
	public static final String UPDATE_LOAN_APPLICATION_STATUS = "UPDATE LOAN_APPLICATION SET APPLICATION_STATUS = ? WHERE APPLICATION_ID = ?";
	
	public static final String SEND_NOTIFICATION = "INSERT INTO NOTIFICATION(USER_ID, MESSAGE, TIMESTAMP, NOTIFICATION_STATUS, SENDER_ID, SENDER_TYPE) VALUES(?, ?, ?, ?, ?, ?)";
	
	public static final String READ_NOTIFICATION = "SELECT NOTIFICATION_ID AS \"notificationId\", USER_ID AS \"userId\", MESSAGE AS \"message\", TIMESTAMP AS \"timeStamp\", NOTIFICATION_STATUS AS \"notificationStatus\", SENDER_ID AS \"senderId\", SENDER_TYPE AS \"senderType\" FROM NOTIFICATION WHERE NOTIFICATION_STATUS = 'S' ORDER BY TIMESTAMP DESC";
	
	public static final String GET_USER_FOR_NOTIFICATION = "SELECT * FROM \"USER\" WHERE USER_ID = (SELECT USER_ID FROM NOTIFICATION WHERE MESSAGE = ?)";
	
	public static final String DELETE_APPLICANT_FROM_DRAFT = "DELETE FROM APPLICANT_DRAFT WHERE APPLICANT_ID = ?";

// Get all the loan applications which are in the Submitted status - RB
	public static final String GET_ALL_LOAN_APPLICATIONS = "SELECT LOAN_APPLICATION.APPLICATION_ID, APPLICANT.APPLICANT_ID, LOAN_APPLICATION.APPLICATION_DATE, LOAN_APPLICATION.APPLICATION_STATUS AS status, LOAN_APPLICATION.LOAN_AMOUNT, LOAN_APPLICATION.PURPOSE, LOAN_APPLICATION.ASSIGNEE_ID APPLICANT FROM LOAN_APPLICATION, APPLICANT WHERE LOAN_APPLICATION.APPLICANT_ID = applicant.applicant_id AND LOAN_APPLICATION.APPLICATION_STATUS IN ('DR', 'UR')";

// Get all the Bank Representatives - RB
	public static final String GET_ALL_BANK_REPRESENTATIVE = "SELECT * FROM BANK_REPRESENTATIVE"; 

// Update Application with Bank Representative Id - RB
	public static final String ASSIGN_APPLICATION = "UPDATE \"LOAN_APPLICATION\" SET ASSIGNEE_ID = ?, APPLICATION_STATUS = ? WHERE APPLICATION_ID = ?";
		
// Get Applicant By ID - RB
	public static final String FIND_APPLICANT_BY_ID = "SELECT APPLICANT_ID AS \"applicantId\", USER_ID AS \"userId\", FIRST_NAME AS \"firstName\", LAST_NAME AS \"lastName\", DATE_OF_BIRTH AS \"dateOfBirth\", ADDRESS AS \"address\", EDUCATION_DETAILS AS \"educationDetails\", MEMBERSHIP_TYPE AS \"membershipType\", EMAIL AS \"email\" FROM APPLICANT WHERE APPLICANT_ID = ?";

// Get Guarantor Details By Application Id - RB
	public static final String FIND_GUARANTOR_BY_APPL_ID = "SELECT GURANTOR_ID AS \"guarantorId\", NAME AS \"name\", RELATIONSHIP AS \"relationship\", OCCUPATION AS \"occupation\", ANNUAL_INCOME AS \"annualIncome\", ADDRESS AS \"address\", UIN_NO AS \"uinNo\", MONTHLY_EXPENSE AS \"monthlyExpense\" FROM GUARANTOR WHERE APPLICATION_ID = ?";

// Get Application By Id
	public static final String GET_LOAN_APPLICATION_BY_ID = "SELECT * FROM LOAN_APPLICATION WHERE APPLICATION_ID = ?";
		
// Get the last 3 years ITR for Business Person 
	public static final String GET_ITR_FOR_BUSINESS = "SELECT UIN_NO AS \"uinNo\", ITR_NO AS \"itrNo\", OCCUPATION AS \"occupation\", ANNUAL_INCOME AS \"annualIncome\", SALARY_RECEIVED_DATE AS \"salaryReceivedDate\", CREDIT_SCORE AS \"creditScore\", REPO_RATE AS \"repoRate\", ITR_YEAR AS \"itrYear\" FROM EXTERNAL_DB a WHERE UIN_NO = ? AND EXISTS (SELECT * FROM EXTERNAL_DB b WHERE a.UIN_NO = b.UIN_NO AND a.ITR_YEAR = b.ITR_YEAR + 1) AND EXISTS ( SELECT * FROM EXTERNAL_DB c WHERE a.UIN_NO = c.UIN_NO AND a.ITR_YEAR = c.ITR_YEAR + 2 )";
				
// Get the current months ITR for Salaried Person
	public static final String GET_ITR_FOR_SALARIED = "SELECT * FROM EXTERNAL_DB WHERE UIN_NO = ? AND SALARY_RECEIVED_DATE BETWEEN SYSDATE - 30 AND SYSDATE";

// Get Applicant by Application Id
	public static final String GET_APPLICANT_BY_APPLICATION_ID = "SELECT * FROM APPLICANT WHERE APPLICANT_ID = ?"; 
}
