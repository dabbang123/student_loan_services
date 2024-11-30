package com.sd.sls.loan.offer.decorator;

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
