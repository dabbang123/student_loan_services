package com.sd.sls.loan.offer.controller.decorator;

import org.springframework.stereotype.Component;


public class GoldMemberShipDecorator extends InterestRateDecorator{
	
	public GoldMemberShipDecorator(IInterestRate decoratedInterestRate)
	{
		super(decoratedInterestRate);
	}
	
	public double getRate()
	{
		return decoratedInterestRate.getRate() - 2.0;
	}
}
