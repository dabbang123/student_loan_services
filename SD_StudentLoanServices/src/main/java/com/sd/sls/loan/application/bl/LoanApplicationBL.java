package com.sd.sls.loan.application.bl;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.sls.applicant.bl.IApplicantBL;
import com.sd.sls.guarantor.model.Guarantor;
import com.sd.sls.loan.application.dao.ILoanApplicationDAO;
import com.sd.sls.loan.application.model.LoanApplication;
import com.sd.sls.loan.application.status.ApplicationStatus;
import com.sd.sls.user.constants.UserConstants;
import com.sd.sls.user.model.User;

@Service
public class LoanApplicationBL implements ILoanApplicationBL 
{
	@Autowired
	private ILoanApplicationDAO loanApplicationDAO;
	

	@Override
	public LoanApplication findApplicationID(String first_name, String last_name, String email) {
		// TODO Auto-generated method stub
		return loanApplicationDAO.findApplicationID(first_name, last_name, email);
	}
	

	
}
