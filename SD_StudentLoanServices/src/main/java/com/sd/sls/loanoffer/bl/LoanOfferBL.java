package com.sd.sls.loanoffer.bl;

import com.sd.sls.applicant.dao.IApplicantDAO;
import com.sd.sls.applicant.model.Applicant;

/*
 * Author: Nikunj Panchal
 */

import com.sd.sls.bankadmin.bl.IBankAdminBL;
import com.sd.sls.bankadmin.constants.BankAdminConstants;
import com.sd.sls.loan.application.constants.LoanApplicationConstants;
import com.sd.sls.loan.application.dao.ILoanApplicationDAO;
import com.sd.sls.loan.application.model.LoanApplication;
import com.sd.sls.loan.offer.decorator.GoldMemberShipDecorator;
import com.sd.sls.loan.offer.decorator.IInterestRate;
import com.sd.sls.loan.offer.decorator.NormalInterestRate;
import com.sd.sls.loan.offer.decorator.SilverMemberShipDecorator;
import com.sd.sls.loan.offer.factory.InterestRateFactory;
import com.sd.sls.loanapplication.status.context.ApplicationStatusContext;
import com.sd.sls.loanoffer.constants.LoanOfferConstants;
import com.sd.sls.loanoffer.dao.ILoanOfferDAO;
import com.sd.sls.loanoffer.model.LoanOffer;
import com.sd.sls.loanoffer.status.LoanOfferStatus;
import com.sd.sls.loanoffer.status.context.LoanOfferStatusContext;
import com.sd.sls.membershiptype.MembershipType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class LoanOfferBL implements ILoanOfferBL{

    @Autowired
    private ILoanOfferDAO loanOfferDAO;

    @Autowired
    private ILoanApplicationDAO loanApplicationDAO;

    @Autowired
    private IBankAdminBL bankAdminBL;
    
    @Autowired
    private IApplicantDAO applicantDAO;

    @Autowired
    private LoanOfferStatusContext loanOfferStatusContext;

    @Autowired
    private ApplicationStatusContext applicationStatusContext;

    @Override
    public List<LoanOffer> getAllOffers () {
        return loanOfferDAO.getAllOffers();
    }

    private LoanOffer createLoanOffer (Map<String, Object> userValues)
    {
        LoanOffer loanOffer = new LoanOffer();
        LoanApplication loanApplication = loanApplicationDAO
                .getApplicationById((Integer) userValues.get(LoanApplicationConstants.APPLICATION_ID));
        loanApplication.setApplicant(applicantDAO.getApplicantDetailsByApplId(loanApplication.getApplicationId()));
        loanOffer.setLoanApplication(loanApplication);
        loanOffer.setDisbursedDate(null);
        
        // Used Decorator Design Pattern to get the Interest Rate - RB
        IInterestRate rate = InterestRateFactory.getInterestRateFactory(loanApplication.getApplicant().getMembershipType());
        loanOffer.setInterestRate(rate.getRate());
        loanOffer.setSanctionedAmount(bankAdminBL.calculateSanctionAmount((Integer) userValues.get(LoanApplicationConstants.APPLICATION_ID)));
//        loanOffer.setOfferStatus(LoanOfferStatus.GENERATED);
        return loanOffer;
    }

    @Override
    public Map<String, Boolean> generateOffer (Map<String, Object> userValues){
        Map<String, Boolean> returnMap = new HashMap<>();
        LoanOffer loanOffer = createLoanOffer(userValues);
        if (checkIfOfferExists(userValues))
        {
            returnMap.put(LoanOfferConstants.LOAN_OFFER_EXISTS, true);
            return returnMap;
        }
        else {
            handleLoanOffer(loanOffer, userValues, returnMap);
        }

        return returnMap;
    }

    private boolean checkIfOfferExists(Map<String, Object> userValues) {
        return loanOfferDAO.checkIfOfferExists(userValues);
    }

    public void handleLoanOffer(LoanOffer loanOffer, Map<String, Object> userValues, Map<String, Boolean> returnMap) {
        try {
            if (generateOffer(loanOffer)) {
                if (updateApplicationStatus(userValues)) {
                    returnMap.put(LoanOfferConstants.OFFER_GENERATED_SUCCESSFULLY + "\n" + LoanOfferConstants.APPLICATION_UPDATED, true);
                }
                else {
                    returnMap.put("Error occurred while updating the loan offer", false);
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while processing the loan offer: " + e.getMessage());
            returnMap.put("Error occurred while processing the loan offer", false);
        }
    }

    private boolean generateOffer(LoanOffer loanOffer) {
        try {
            return loanOfferDAO.generateOffer(loanOffer) == 1;
        } catch (Exception e) {
            System.err.println("An error occurred while generating the loan offer: " + e.getMessage());
            throw e;
        }
    }

    private boolean updateApplicationStatus(Map<String, Object> userValues) {
        try {
//            loanOfferStatusContext.setState(userValues);
//
//            boolean x = loanOfferStatusContext.updateLoanOfferStatus(
//                    (Integer) userValues.get(LoanApplicationConstants.APPLICATION_ID)) == 1;

            userValues.replace(BankAdminConstants.ACTION, LoanApplicationConstants.SANCTIONED);
            applicationStatusContext.setState(userValues);

            return applicationStatusContext.updateLoanApplicationStatus(
                    (Long) userValues.get(LoanApplicationConstants.APPLICATION_ID)) == 1;
//            return loanOfferDAO.updateApplicationStatus(loanOffer.getLoanApplication().getApplicationId());
        } catch (Exception e) {
            System.err.println("An error occurred while updating the application status: " + e.getMessage());
            throw e;
        }
    }

//    private LoanOffer fetchLoanOffer(String offerID) {
//        try {
//            return loanOfferDAO.getLoanOffer(Integer.parseInt(offerID));
//        } catch (Exception e) {
//            System.err.println("An error occurred while fetching the loan offer: " + e.getMessage());
//            throw e;
//        }
//    }

//    private double fetchInterestRate(int applicationId)
//    {
//    	Applicant applicant = applicantDAO.getApplicantDetailsByApplId(applicationId);
//    	if(applicant.getMembershipType().equals(MembershipType.GOLD.getMembershipType()))
//    	{
//    		IInterestRate interestRate = new GoldMemberShipDecorator(new NormalInterestRate());
//    		return interestRate.getRate();
//    	}
//    	else if(applicant.getMembershipType().equals(MembershipType.SILVER.getMembershipType()))
//    	{
//    		IInterestRate interestRate = new SilverMemberShipDecorator(new NormalInterestRate());
//    		return interestRate.getRate();
//    	}
//    	else
//    	{
//    		IInterestRate interestRate = new NormalInterestRate();
//    		return interestRate.getRate();
//    	}
//    }
}