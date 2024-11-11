package com.sd.sls.applicant.model;

import java.sql.Date;

import com.sd.sls.user.model.User;

public class Applicant {
	
	private int applicantId;
	
	private User user;
	
	private String firstName;
	
	private String lastName;
	
	private Date dateOfBirth;
	
	private String address;
	
	private String educationDetails;
	
	private String membershipType;
	
	public Applicant() {
		// TODO Auto-generated constructor stub
	}

	public Applicant(int applicantId, User user, String firstName, String lastName, Date dateOfBirth, String address,
			String educationDetails, String membershipType) {
		super();
		this.applicantId = applicantId;
		this.user = user;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.educationDetails = educationDetails;
		this.membershipType = membershipType;
	}

	public int getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEducationDetails() {
		return educationDetails;
	}

	public void setEducationDetails(String educationDetails) {
		this.educationDetails = educationDetails;
	}

	public String getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}

	@Override
	public String toString() {
		return "Applicant [applicantId=" + applicantId + ", user=" + user + ", firstName=" + firstName + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", address=" + address + ", educationDetails="
				+ educationDetails + ", membershipType=" + membershipType + "]";
	}
}
