package com.sd.sls.loan.offer.model;

import java.sql.Date;

import com.sd.sls.loan.application.model.LoanApplication;

/*
 *	@Author: Abhishek Vishwakarma 
 */

public class LoanOffer 
{
	private int offerId;
	
	private LoanApplication application;
	
	private int sanctionedAmount;
	
	private Double interestRate;
	
	private String offerStatus;
	
	private Date disbursedDate;
	
	public LoanOffer() {
		// TODO Auto-generated constructor stub
	}

	public LoanOffer(int offerId, LoanApplication application, int sanctionedAmount, Double interestRate,
			String offerStatus, Date disbursedDate) {
		super();
		this.offerId = offerId;
		this.application = application;
		this.sanctionedAmount = sanctionedAmount;
		this.interestRate = interestRate;
		this.offerStatus = offerStatus;
		this.disbursedDate = disbursedDate;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public LoanApplication getApplication() {
		return application;
	}

	public void setApplication(LoanApplication application) {
		this.application = application;
	}

	public int getSanctionedAmount() {
		return sanctionedAmount;
	}

	public void setSanctionedAmount(int sanctionedAmount) {
		this.sanctionedAmount = sanctionedAmount;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public String getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}

	public Date getDisbursedDate() {
		return disbursedDate;
	}

	public void setDisbursedDate(Date disbursedDate) {
		this.disbursedDate = disbursedDate;
	}

	@Override
	public String toString() {
		return "LoanOffer [offerId=" + offerId + ", application=" + application + ", sanctionedAmount="
				+ sanctionedAmount + ", interestRate=" + interestRate + ", offerStatus=" + offerStatus
				+ ", disbursedDate=" + disbursedDate + "]";
	}
}
