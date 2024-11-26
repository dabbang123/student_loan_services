package com.sd.sls.loan.offer.controller.decorator;

public abstract class InterestRateDecorator implements IInterestRate{
	
	protected IInterestRate decoratedInterestRate;
	
	public InterestRateDecorator(IInterestRate decoratedInterestRate)
	{
		this.decoratedInterestRate = decoratedInterestRate;
	}
	
	@Override
	public double getRate()
	{
		return decoratedInterestRate.getRate();
	}
}
