package com.sd.sls.bankadmin.bl;

/*
 * @Author: Nikunj Panchal
 */

import com.sd.sls.bankadmin.model.BankAdmin;

import java.util.List;

public interface IBankAdminBL {
    public Double calculateSanctionAmount (int applicationId);

    public List<BankAdmin> getBankAdmins ();

    public boolean disburseLoanOffer(int offerID);
}
