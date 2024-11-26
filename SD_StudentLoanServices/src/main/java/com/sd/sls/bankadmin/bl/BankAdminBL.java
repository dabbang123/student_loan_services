package com.sd.sls.bankadmin.bl;

/*
 * @Author: Nikunj Panchal
 */

import com.sd.sls.bankadmin.constants.BankAdminConstants;
import com.sd.sls.bankadmin.dao.IBankAdminDAO;
import com.sd.sls.bankadmin.model.BankAdmin;
import com.sd.sls.guarantor.dao.IGuarantorDAO;
import com.sd.sls.guarantor.model.Guarantor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BankAdminBL implements IBankAdminBL {

    @Autowired
    private IBankAdminDAO bankAdminDAO;

    @Autowired
    private IGuarantorDAO guarantorDAO;

    @Override
    public Double calculateSanctionAmount (int applicationId) {
        Guarantor guarantor = guarantorDAO.getGuarantorByAppId(applicationId);

        Double LoanMultiplier = 0.0;

        if (Objects.equals(guarantor.getOccupation(), BankAdminConstants.BUSINESS))
            LoanMultiplier = (BankAdminConstants.BUSINESS_RATE * ((double) guarantor.getAnnualIncome() /12));
        else
            LoanMultiplier = (BankAdminConstants.SALARY_RATE * ((double) guarantor.getAnnualIncome() /12));

        return (((guarantor.getAnnualIncome()/12) - guarantor.getMonthlyExpense())/ BankAdminConstants.LOAN_TENURE) * LoanMultiplier;
    }

    @Override
    public List<BankAdmin> getBankAdmins () {
        return bankAdminDAO.getBankAdmins();
    }

    @Override
    public boolean disburseLoanOffer (int offerID) {
        return bankAdminDAO.disburseLoanOffer(offerID);
    }
}
