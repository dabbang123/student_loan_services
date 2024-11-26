package com.sd.sls.loanoffer.dao;

/**
 * Author: Nikunj Panchal
 */

import com.sd.sls.loanoffer.model.LoanOffer;

import java.util.List;

public interface ILoanOfferDAO {
    public int generateOffer (LoanOffer loanOffer);

    public LoanOffer getLoanOffer(int offerID);

    public boolean checkIfOfferExists(LoanOffer loanOffer);

    public List<LoanOffer> getAllOffers();

    public boolean updateApplicationStatus(int applicationID);
}
