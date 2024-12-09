package com.sd.sls.command.factory.dp;

/*
 * @Author: Abhishek Vishwakarma
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sd.sls.command.dp.BusinessGuarantorReviewCommand;
import com.sd.sls.command.dp.GuarantorReviewCommand;
import com.sd.sls.command.dp.SalariedGuarantorReviewCommand;
import com.sd.sls.guarantor.constants.GuarantorConstants;

@Component
public class GuarantorReviewCommandFactory 
{
	@Autowired
	private BusinessGuarantorReviewCommand businessGuarantorReviewCommand;
	
	@Autowired
	private SalariedGuarantorReviewCommand salariedGuarantorReviewCommand;
	
	public GuarantorReviewCommand getCommand(String occupation) 
	{
        if (GuarantorConstants.BUSINESS.equals(occupation)) 
        {
            return businessGuarantorReviewCommand;
        } 
        else 
        {
            return salariedGuarantorReviewCommand;
        }
    }
}
