package com.sd.sls.loan.offer.controller.decorator;

public class NormalInterestRate implements IInterestRate{

	@Override
	public double getRate() 
	{
		return 10.0;
	}
}
