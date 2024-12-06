DROP TABLE "USER"
/
CREATE TABLE "USER" (
    USER_ID NUMBER(10) GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
    USER_NAME VARCHAR2(255),
    EMAIL VARCHAR2(255),
    PASSWORD VARCHAR2(255),
    PHONE_NUMBER NUMBER(10)
)
/
DROP TABLE APPLICANT
/
CREATE TABLE APPLICANT(
    APPLICANT_ID NUMBER(10) GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
    USER_ID NUMBER(10),
    FIRST_NAME VARCHAR2(255),
    LAST_NAME VARCHAR2(255),
    DATE_OF_BIRTH DATE,
    ADDRESS VARCHAR2(255),
    EDUCATION_DETAILS VARCHAR2(255),
    MEMBERSHIP_TYPE VARCHAR2(255),
    FOREIGN KEY (USER_ID) REFERENCES "USER"(USER_ID)
)
/
ALTER TABLE APPLICANT ADD EMAIL VARCHAR2(255)
/
DROP TABLE BANK_ADMIN
/
CREATE TABLE BANK_ADMIN(
    ADMIN_ID NUMBER(10) GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
    USER_ID NUMBER(10),
    AUTHORITY_LEVEL VARCHAR2(255),
    FOREIGN KEY (USER_ID) REFERENCES "USER"(USER_ID)
)
/
DROP TABLE BANK_REPRESENTATIVE
/
CREATE TABLE BANK_REPRESENTATIVE(
    EMPLOYEE_ID NUMBER(10) GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
    USER_ID NUMBER(10),
    FOREIGN KEY (USER_ID) REFERENCES "USER"(USER_ID)
)
/
DROP TABLE LOAN_APPLICATION
/
CREATE TABLE LOAN_APPLICATION (
  APPLICATION_ID NUMBER(10) GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  APPLICANT_ID NUMBER(10),
  GUARANTOR_NAME VARCHAR2(255),
  APPLICATION_DATE date,
  APPLICATION_STATUS VARCHAR2(255),
  LOAN_AMOUNT int,
  PURPOSE VARCHAR2(255),
  ASSIGNEE_ID NUMBER(10),
  FOREIGN KEY (APPLICANT_ID) REFERENCES APPLICANT(APPLICANT_ID)
)
/
DROP TABLE GUARANTOR
/
CREATE TABLE GUARANTOR (
  GURANTOR_ID NUMBER(10) GENERATED BY DEFAULT AS IDENTITY,
  APPLICANT_ID NUMBER(10),
  APPLICATION_ID NUMBER(10),
  NAME VARCHAR2(255),
  RELATIONSHIP VARCHAR2(255),
  OCCUPATION VARCHAR2(255),
  ANNUAL_INCOME NUMBER,
  ADDRESS VARCHAR2(255),
  UIN_NO VARCHAR2(255),
  MONTHLY_EXPENSE NUMBER,
  PRIMARY KEY (GURANTOR_ID, APPLICANT_ID, APPLICATION_ID),
  FOREIGN KEY (APPLICATION_ID) REFERENCES LOAN_APPLICATION(APPLICATION_ID),
  FOREIGN KEY (APPLICANT_ID) REFERENCES APPLICANT(APPLICANT_ID)
)
/
DROP TABLE LOAN_OFFER
/
CREATE TABLE LOAN_OFFER (
  OFFER_ID NUMBER(10) GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  APPLICATION_ID NUMBER(10),
  SANCTIONED_AMOUNT NUMBER(10),
  INTEREST_RATE NUMBER(10, 5),
  OFFER_STATUS varchar(255),
  DISBURSED_DATE DATE,
  FOREIGN KEY (APPLICATION_ID) REFERENCES LOAN_APPLICATION(APPLICATION_ID)
)
/
CREATE TABLE APPLICANT_DRAFT(
    APPLICANT_ID NUMBER(10) GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
    USER_ID NUMBER(10),
    FIRST_NAME VARCHAR2(255),
    LAST_NAME VARCHAR2(255),
    DATE_OF_BIRTH DATE,
    ADDRESS VARCHAR2(255),
    EDUCATION_DETAILS VARCHAR2(255),
    MEMBERSHIP_TYPE VARCHAR2(255)
)
/
ALTER TABLE APPLICANT_DRAFT ADD EMAIL VARCHAR2(255)
/










