package com.sd.sls.loan.offer.decorator;

/*
 * Author: Rushabh Botadra
 */
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
