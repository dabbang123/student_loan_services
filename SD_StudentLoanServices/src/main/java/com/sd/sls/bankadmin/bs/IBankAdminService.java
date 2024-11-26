package com.sd.sls.bankadmin.bs;

/*
 * @Author: Nikunj Panchal
 */

import com.sd.sls.bankadmin.model.BankAdmin;

import java.util.List;
import java.util.Map;

public interface IBankAdminService {
    public List<BankAdmin> getBankAdmins ();

    public boolean disburseLoanOffer(int offerID);
}
