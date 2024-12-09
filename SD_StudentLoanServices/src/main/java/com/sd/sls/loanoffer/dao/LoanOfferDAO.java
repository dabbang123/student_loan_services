package com.sd.sls.loanoffer.dao;

/*
 * @Author: Nikunj Panchal
 */

import com.sd.sls.constants.ISQLStatements;
import com.sd.sls.loan.application.bs.LoanApplicationBS;
import com.sd.sls.loan.application.constants.LoanApplicationConstants;
import com.sd.sls.loanoffer.model.LoanOffer;
import com.sd.sls.loanoffer.status.LoanOfferStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class LoanOfferDAO implements ILoanOfferDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private LoanApplicationBS loanApplicationBS;

    @Override
    public int generateOffer (LoanOffer loanOffer) {
        return jdbcTemplate.update(
                ISQLStatements.GENERATE_LOAN_OFFER,
                new Object[] {
                        loanOffer.getLoanApplication().getApplicationId(),
                        loanOffer.getSanctionedAmount(),
                        loanOffer.getInterestRate(),
                        LoanOfferStatus.GENERATED.toString(),
                        null
                });
    }

    @Override
    public LoanOffer getLoanOffer(int offerID) {
        List<LoanOffer> loanOfferList = jdbcTemplate.query(
                ISQLStatements.GET_LOAN_OFFER, new BeanPropertyRowMapper<>(LoanOffer.class), new Object[] {offerID});
        return !loanOfferList.isEmpty() ? loanOfferList.get(0) : null;
    }

    @Override
    public List<LoanOffer> getAllOffers() {
        List<LoanOffer> loanOfferList = jdbcTemplate.query(
                ISQLStatements.GET_ALL_LOAN_OFFERS, new BeanPropertyRowMapper<>(LoanOffer.class));
        return !loanOfferList.isEmpty() ? loanOfferList : null;
    }

    @Override
    public boolean checkIfOfferExists (Map<String, Object> userValues) {
        return jdbcTemplate.query(
                ISQLStatements.CHECK_LOAN_OFFER,
                new BeanPropertyRowMapper<>(LoanOffer.class),
                new Object[] {
                        userValues.get(LoanApplicationConstants.APPLICATION_ID)
                }).isEmpty();
    }

    @Override
    public int disburseLoanOffer(int offerId) {
        return jdbcTemplate.update(
                ISQLStatements.UPDATE_LOAN_OFFER_STATUS, new Object[]{LoanOfferStatus.DISBURSED.toString(), offerId});
    }

    @Override
    public int generateLoanOfferStatus (int applicationID) {
        return jdbcTemplate.update(
                ISQLStatements.UPDATE_LOAN_OFFER_STATUS, new Object[]{LoanOfferStatus.GENERATED.toString(), applicationID});
    }
}
