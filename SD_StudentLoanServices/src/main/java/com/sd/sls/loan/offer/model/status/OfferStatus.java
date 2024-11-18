package com.sd.sls.loan.offer.model.status;

public enum OfferStatus {
	GENERATED("G"),
	ACCEPTED("A"),
	REJECTED("R"),
	DISBURSED("D");
	
	private final String offerStatus;
	
	OfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}
	
	public String getOfferStatus() {
		return offerStatus;
	}

}
