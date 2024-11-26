package com.sd.sls.loan.offer.controller.decorator;

import org.springframework.stereotype.Component;

public class SilverMemberShipDecorator extends InterestRateDecorator{
	
	public SilverMemberShipDecorator(IInterestRate decoratedInterestRate)
	{
		super(decoratedInterestRate);
	}
	
	public double getRate()
	{
		return decoratedInterestRate.getRate() - 1.0;
	}
}
