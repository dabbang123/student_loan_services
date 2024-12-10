package com.sd.sls.loanoffer.bl;

import java.util.List;
import java.util.Map;

import com.sd.sls.loanoffer.model.LoanOffer;

public interface ILoanOfferBL {
    public Map<String, Boolean> generateOffer (Map<String, Object> userValues);

    public List<LoanOffer> getAllOffers();
}