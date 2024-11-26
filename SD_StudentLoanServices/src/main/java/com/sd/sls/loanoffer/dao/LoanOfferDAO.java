package com.sd.sls.loanoffer.dao;

/*
 * @Author: Nikunj Panchal
 */

import com.sd.sls.constants.ISQLStatements;
import com.sd.sls.loan.application.bs.LoanApplicationBS;
import com.sd.sls.loanoffer.model.LoanOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoanOfferDAO implements ILoanOfferDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private LoanApplicationBS loanApplicationBS;

    @Override
    public int generateOffer (LoanOffer loanOffer) {
        return jdbcTemplate.update(ISQLStatements.GENERATE_LOAN_OFFER,  new Object[] {loanOffer.getLoanApplication().getApplicationId(), loanOffer.getSanctionedAmount(), loanOffer.getInterestRate(), loanOffer.getOfferStatus(), loanOffer.getDisbursedDate()});
    }

    @Override
    public LoanOffer getLoanOffer(int offerID) {
        List<LoanOffer> loanOfferList = jdbcTemplate.query(ISQLStatements.GET_LOAN_OFFER, new BeanPropertyRowMapper<>(LoanOffer.class), new Object[] {offerID});
        return !loanOfferList.isEmpty() ? loanOfferList.get(0) : null;
    }

    @Override
    public List<LoanOffer> getAllOffers() {
        List<LoanOffer> loanOfferList = jdbcTemplate.query(ISQLStatements.GET_ALL_LOAN_OFFERS, new BeanPropertyRowMapper<>(LoanOffer.class));
        return !loanOfferList.isEmpty() ? loanOfferList : null;
    }

    @Override
    public boolean checkIfOfferExists (LoanOffer loanOffer) {
        return !jdbcTemplate.queryForList(ISQLStatements.CHECK_LOAN_OFFER, loanOffer.getOfferID()).isEmpty();
    }

    @Override
    public boolean updateApplicationStatus(int applicationID) {
        return jdbcTemplate.update(ISQLStatements.UPDATE_LOAN_APPLICATION_STATUS, new Object[]{'S', applicationID}) > 0;
    }
}
