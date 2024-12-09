package com.sd.sls.loanoffer.dao;

/**
 * Author: Nikunj Panchal
 */

import com.sd.sls.loanoffer.model.LoanOffer;

import java.util.List;
import java.util.Map;

public interface ILoanOfferDAO {
    public int generateOffer (LoanOffer loanOffer);

    public LoanOffer getLoanOffer(int offerID);

    public boolean checkIfOfferExists(Map<String, Object> userValues);

    public List<LoanOffer> getAllOffers();

    public int disburseLoanOffer(int applicationID);

    public int generateLoanOfferStatus (int applicationID);
}
