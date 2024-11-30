package com.sd.sls.loanoffer.bs;

import com.sd.sls.loanoffer.model.LoanOffer;

import java.util.List;
import java.util.Map;

public interface ILoanOfferBS {
    public Map<String, Boolean> generateOffer(Map<String, Object> userValues);

    public List<LoanOffer> getAllOffers ();
}
